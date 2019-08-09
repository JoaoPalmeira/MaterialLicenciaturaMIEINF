#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h> 
#include <ctype.h> 
#include <time.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#define READ 0
#define WRITE 1

char* trim (char *s) {
   while(isspace(*s)) s++;
   char *back = s + strlen(s);
   while(isspace(*--back));
   *(back+1) = '\0';
   return s;
}

ssize_t readln(int fildes, char* buf, size_t nbyte){
  char *bp = buf;
  ssize_t n;
  while(bp-buf < nbyte && (n=read(fildes,bp,1))>0) {
    if(*bp++ == '\n') 
      return (bp-buf);
  }
  if(n<=0) return -1;
  if(bp-buf == nbyte) {
    char c;
    while(read(fildes,&c,1)>0 && c!='\n');
  }
  return (bp-buf);
}

int mysystem(char *cmd) {
   char *aux = trim(cmd);
   char **args = NULL;
   int id = 0;
   int fdin = -1, stdinCopy = -1;
   int fdout = -1, stdoutCopy = -1;
   int fderr = -1, stderrCopy = -1;
   char *ptr = strtok(aux," ");
   while(ptr != NULL) {
      //command < file - command input from file
      if(!strcmp("<",ptr) && fdin == -1) {
         stdinCopy = dup(0);
         ptr = strtok(NULL," ");
         fdin = open(ptr,O_RDONLY,S_IREAD);
         dup2(fdin,0);
         close(fdin);

         ptr = strtok(NULL," ");
      }
      //command > file - command output to file
      else if(!strcmp(">",ptr) && fdout == -1) {
         stdoutCopy = dup(1);
         ptr= strtok(NULL," ");
         fdout = open(ptr,O_CREAT|O_TRUNC|O_WRONLY,S_IRWXU);
         dup2(fdout,1);
         close(fdout);
         ptr = strtok(NULL," ");
      }
      //command >> file - append command output to file
      else if(!strcmp(">>",ptr) && fdout == -1) {
         stdoutCopy = dup(1);
         ptr = strtok(NULL," ");
         fdout = open(ptr,O_CREAT|O_APPEND|O_WRONLY,S_IRWXU);
         dup2(fdout,1);
         close(fdout);
         ptr = strtok(NULL," ");
      }
      //command 2> file - stderr output to file
      else if(!strcmp("2>",ptr) && fderr == -1) {
         stderrCopy = dup(2);
         ptr = strtok(NULL," ");
         fderr = open(ptr,O_CREAT|O_TRUNC|O_WRONLY,S_IRWXU);
         dup2(fdout,2);
         close(fdout);
         ptr = strtok(NULL," ");
      }
      //command 2>> file - append stderr output to file
      else if(!strcmp("2>>",ptr) && fderr == -1) {
         stderrCopy = dup(2);
         ptr = strtok(NULL," ");
         fderr = open(ptr,O_CREAT|O_TRUNC|O_WRONLY,S_IRWXU);
         dup2(fdout,2);
         close(fdout);
         ptr = strtok(NULL," ");
      }
      //No argument
      else {
         args = (char **) realloc(args,(id+1)*sizeof(char*));
         if(!args) exit(-1);
         args[id] = ptr;
         id++;
         ptr = strtok(NULL," ");
      }
   }

   args =(char **) realloc(args,(id+1)*sizeof(char*));
   args[id] = 0;
   id++;
   pid_t son = fork();
   if(!son) {
      execvp(args[0],args);
      exit(127);
   }
   if(stdinCopy != -1) {
      dup2(stdinCopy,0);
      close(stdinCopy);
   }
   if(stdoutCopy != -1) {
      dup2(stdoutCopy,1);
      close(stdoutCopy);
   }
   if(stderrCopy != -1) {
      dup2(stderrCopy,2);
      close(stderrCopy);
   }
   
   int status;
   waitpid(son,&status,0);

   return (WEXITSTATUS(status));
}

int mybash (char *lnCmd) {
   char **cmd = NULL;
   int i,id = 0;

   char *ptr = strtok(lnCmd,"|");
   while(ptr != NULL) {
      cmd = (char **) realloc(cmd,(id+1)*sizeof(char*));
      cmd[id] = ptr;
      id++;
      ptr = strtok(NULL,"|");
   }

   pid_t son;
   int status;

   int pd[2];
   int fdin = 0;

   for(i=0; i<id; i++){
      pipe(pd);
      if((son=fork())==0) {
         dup2(fdin,0);
         if(i<id-1) {
            dup2(pd[WRITE],1);
         }
         close(pd[READ]);

         int res = mysystem(trim(cmd[i]));
         exit(res);
      }
      else {
         waitpid(son,&status,0);
         close(pd[WRITE]);
         fdin = pd[READ];
         if(WEXITSTATUS(status) == 127) {
            char buff[1024];
            sprintf(buff,"%s - Command not found or incorrect arguments.\n",trim(cmd[i]));
            return 0;
         }
      }
   }
   return 1;
}

int restore (char *s) {
	char aux[1024];
	getcwd(aux,sizeof(aux));
	char directory[1024];
	strcpy(directory,aux);
	strtok(aux,"/");
	char *user=strtok(NULL," /\n\r\0");


	char *commLs = (char *) calloc(34+strlen(s)+strlen(user),sizeof(char));
	strcpy(commLs,"readlink /home/");
	strcpy(&commLs[15],user);
	strcpy(&commLs[15+strlen(user)],"/.Backup/metadata/");
	strcpy(&commLs[33+strlen(user)],s);
	commLs[33+strlen(user)+strlen(s)]='\0';

	FILE *file = popen(commLs,"r");
	free(commLs);
	char path[1024];
	if (fgets(path,1024,file)){
		path[strlen(path)]='\0';
		char *unGzip = (char *) calloc (14+strlen(path)+strlen(directory)+strlen(s),sizeof(char));
		strcpy(unGzip,"gzip -d < ");
		strcpy(&unGzip[10],path);
		strcpy(&unGzip[9+strlen(path)]," > ");
		strcpy(&unGzip[12+strlen(path)],directory);
		strcpy(&unGzip[12+strlen(path)+strlen(directory)],"/");
		strcpy(&unGzip[13+strlen(path)+strlen(directory)],s);
		strcpy(&unGzip[13+strlen(path)+strlen(directory)+strlen(s)],"\0");
		if (mybash(unGzip)) {
			fclose(file);
			return 1;
		}
	}
	fclose(file);
   return 0;
}

int backup (char *s) {
  char *commLs = (char *) calloc(4+strlen(s),sizeof(char));
  strcpy(commLs,"ls ");
  strcpy(&commLs[3],s);
  FILE *file = popen (commLs,"r");
  rewind(file);
  char *aux = (char *) calloc (1024,sizeof(char));
  if(fgets(aux,1024,file)) {
    char *commShasum = (char *) calloc (strlen(aux)+8,sizeof(char));
    strcpy(commShasum,"shasum ");
    strcpy(&commShasum[7],aux);
    FILE *sha1 = popen(commShasum,"r");
    rewind(sha1);
    char *shasum = (char *) calloc (42,sizeof(char));;
    if (fgets(shasum,42,sha1)) {
      char directory[1024];
      getcwd(directory,sizeof(directory));
      strtok(directory,"/");
      char *user= strtok(NULL," /\n\r\0");
      
      char *path = (char *) calloc (16+strlen(user),sizeof(char));
      strcpy(path,"/home/");
      strcpy(&path[6],user);
      strcpy(&path[6+strlen(user)],"/.Backup/\0");


      char *crateDir1 = (char *) calloc (15+strlen(path),sizeof(char));
      strcpy(crateDir1,"mkdir -p ");
      strcpy(&crateDir1[9],path);
      strcpy(&crateDir1[9+strlen(path)],"data/");

      char *crateDir2 = (char *) calloc (19+strlen(path),sizeof(char));
      strcpy(crateDir2,"mkdir -p ");
      strcpy(&crateDir2[9],path);
      strcpy(&crateDir2[9+strlen(path)],"metadata/");

      char *commGzip = (char *) calloc (57+strlen(aux)+strlen(path),sizeof(char));
      strcpy(commGzip,"gzip < ");
      strcpy(&commGzip[7],aux);
      strcpy(&commGzip[6+strlen(aux)]," > ");
      strcpy(&commGzip[9+strlen(aux)],path);
      strcpy(&commGzip[9+strlen(aux)+strlen(path)],"data/");
      strcpy(&commGzip[14+strlen(aux)+strlen(path)],shasum);

      char *crateLink = (char *) calloc (22+(2*strlen(path))+strlen(shasum)+strlen(aux),sizeof(char));
      strcpy(crateLink,"ln -s -f ");
      strcpy(&crateLink[9],path);
      strcpy(&crateLink[9+strlen(path)],"data/");
      strcpy(&crateLink[14+strlen(path)],shasum);
      strcpy(&crateLink[14+strlen(path)+strlen(shasum)],path);
      strcpy(&crateLink[14+(2*strlen(path))+strlen(shasum)],"metadata/");
      strcpy(&crateLink[23+(2*strlen(path))+strlen(shasum)],aux);
      strcpy(&crateLink[22+(2*strlen(path))+strlen(shasum)+strlen(aux)],"\0");
      
      if(mybash(crateDir1) && mybash(crateDir2) && mybash(commGzip) && mybash(crateLink)){
      	fclose(file);
      	return 1;
      }
    }
  }
  fclose(file);
  return 0;
}

int execute (int tam, char *buf) {
  if ((strstr(buf,"backup")-buf)==0) {
    buf = buf+7;
    return backup(buf);
  }
  else if ((strstr(buf,"restore")-buf)==0) {
    buf = buf+8;
    return restore(buf);
  }
}

char *strrev (char *s) {
	int i=strlen(s), x=0;
	char *res = (char *) calloc (i,sizeof(char));
	for (i-=1;i>=0;i--) {
		res[x++] = s[i];
	}
	res[x]='\0';
	return res;
}

int get_pid (char *buf) {
	int res;
	sscanf(strrev(strtok(strrev(buf)," ")),"%d",&res);
	return res;
}

char *rem_pid (char *s) {
	strtok(strrev(s)," ");
	char *res = strrev(strtok(NULL,"\0"));
	return res;
}

int main(int argc, char *argv[]) {
	mkfifo("pipe",S_IRWXU);
   	int fdin = open("pipe",O_RDONLY,S_IRWXU);
   	int r;
   	char buf[1024];
   	int nPid=0;
   	int pid;
    while(1) {
      pid_t filho;
      if ((nPid<=6) && (filho = fork())==0){
        if ((r=readln(fdin,buf,1024))>0) {
        	pid = get_pid(buf);
        	if (execute(r,trim(rem_pid(buf)))) {
        		kill(pid,SIGRTMAX);
        	}
        	else kill(pid,SIGALRM);
        }
        exit(0);
      }
      else {
        if (nPid>6) {
          wait(NULL);
          nPid--;
        }
        else nPid++;
      }
  }
  close(fdin);
  return 0;
}
