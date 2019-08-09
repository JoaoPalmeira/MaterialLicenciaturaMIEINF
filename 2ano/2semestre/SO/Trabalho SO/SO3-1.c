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

#define bufS 512

#define READ 0
#define WRITE 1
#define max(a,b) ((a) > (b) ? (a) : (b))
#define min(a,b) ((a) < (b) ? (a) : (b))

//Funções auxiliares

char* itoa(int i, char b[]){
    char const digit[] = "0123456789";
    char* p = b;
    if(i<0){
        *p++ = '-';
        i *= -1;
    }
    int shifter = i;
    do{ //Move to where representation ends
        ++p;
        shifter = shifter/10;
    }while(shifter);
    *p = '\0';
    do{ //Move back, inserting digits as u go
        *--p = digit[i%10];
        i = i/10;
    }while(i);
    return b;
}

ssize_t readln(int fildes, char *buf, size_t nbyte) 
{
	int i = 0;

	while(i<nbyte-1 && 
		  read(fildes, buf+i, 1) > 0 &&
		  buf[i] != '\n') {

			i++;
	}

	if(i>=nbyte)
	buf[i] = 0;
	else
	buf[i+1] = 0;

	return i;
}

char** parser(char* str){
	char *pToken = strtok(str,":");
	int i=0;
	char **colunas = (char **)malloc(bufS*sizeof(char));
	while(pToken != NULL){
		colunas[i]= strdup(pToken);
		pToken = strtok(NULL, ":");
		i++;
	}
	return colunas;
}

void escreve(char* buffer, int valor){
  buffer[strlen(buffer)-1]='\0';
  char *str=(char *) malloc(bufS*sizeof(char));
  itoa(valor, str);
  strcat(buffer,":");
  strcat(buffer,str);
  strcat(buffer,"\n");
  write(1, buffer, strlen(buffer)+4);
}


//Funcões Principais

void consts(char* value, char* buffer, int fildes){
  int nBytes;
	while((nBytes=readln(fildes,buffer,bufS))>0) {
      
      buffer[strlen(buffer)-1]='\0';
			strcat(buffer,":");
			strcat(buffer,value);
			strcat(buffer,"\n");
			write(1, buffer, nBytes+9);
  }
}

void filtro(int coluna, char* operador, int operando, char* buffer, int fildes){
  int nBytes;
  char **res;

  	while((nBytes=readln(fildes,buffer,bufS))>0){
    
      char *aux=strdup(buffer);
  	  res = parser(buffer);

      if(strcmp(operador, "=")==0){
     	if(atoi(res[coluna-1])==atoi(res[operando-1])) write(1,aux, nBytes+9);
      }
      if(strcmp(operador, ">=")==0){
       	if(atoi(res[coluna-1])>=atoi(res[operando-1])) write(1,aux, nBytes+9);
      }
      if(strcmp(operador, "<=")==0){
      	if(atoi(res[coluna-1])<=atoi(res[operando-1])) write(1,aux, nBytes+9);
      }
      if(strcmp(operador, ">")==0){
      	if(atoi(res[coluna-1])>atoi(res[operando-1])) write(1,aux, fildes+9);
      }
      if(strcmp(operador, "<")==0){
      	if(atoi(res[coluna-1])<atoi(res[operando-1])) write(1,aux, nBytes+9);
      }
      if(strcmp(operador, "!=")==0){
      	if(atoi(res[coluna-1])!=atoi(res[operando-1])) write(1,aux, nBytes+9);
      }
   }
 }

void window(int coluna, char* operador, int linhas, char* buffer, int fildes){
    int nBytes;
    char **res;
    int i=0, h=0, atl=0, valor=0, f;
    int valores[linhas];
    while((nBytes=readln(fildes,buffer,bufS))>0){
      char *aux=strdup(buffer);
      res = parser(buffer);
      if(strcmp(operador,"avg")==0){
        if(i<linhas){
          valores[i]=atoi(res[coluna-1]);
          escreve(aux,atl);
          atl=atoi(res[coluna-1]);
        }
        if(i >= linhas){
          for(f=0; f<linhas; f++){
            valor +=valores[f];
          }
          valor=valor/linhas;
          escreve(aux,valor);
          valores[h]=atoi(res[coluna-1]);
          h++;
          if(h==linhas) h=0;
          valor=0;
        }
      }
    if(strcmp(operador,"sum")==0){
        if(i<linhas){
          valores[i]=atoi(res[coluna-1]);
          escreve(aux,atl);
          atl=atoi(res[coluna-1]);
        }
        if(i >= linhas){
          for(f=0; f<linhas; f++){
            valor +=valores[f];
          }
          escreve(aux,valor);
          valores[h]=atoi(res[coluna-1]);
          h++;
          if(h==linhas) h=0;
          valor=0;
        }
      }
      if(strcmp(operador,"max")==0){
        if(i<linhas){
          valores[i]=atoi(res[coluna-1]);
          escreve(aux,atl);
          atl=atoi(res[coluna-1]);
        }
        if(i >= linhas){
          for(f=0; f<linhas; f++){
            if(valor < valores[f]) valor = valores[f];
          }
          escreve(aux,valor);
          valores[h]=atoi(res[coluna-1]);
          h++;
          if(h==linhas) h=0;
          valor=0;
        }
      }
      if(strcmp(operador,"min")==0){
        if(i<linhas){
          valores[i]=atoi(res[coluna-1]);
          escreve(aux,atl);
          atl=atoi(res[coluna-1]);
        }
        if(i >= linhas){
          valor = valores[0];
          for(f=0; f<linhas; f++){
            if(valor > valores[f]) valor = valores[f];
          }
          escreve(aux,valor);
          valores[h]=atoi(res[coluna-1]);
          h++;
          if(h==linhas) h=0;
          valor=0;
        }
      }
      i++;
    }
}


char* trim (char *s) {
   while(isspace(*s)) s++;
   char *back = s + strlen(s);
   while(isspace(*--back));
   *(back+1) = '\0';
   return s;
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
      if(!strcmp("const",ptr) && fdin == -1) {
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

int main(int argc, char * argv[])
{
    int coluna;
	   char* operacao;
	   int operando;
	   char buffer[bufS];
      int fildes,nByte;

	fildes = open(argv[1], O_RDONLY);

    if(fildes!=0) {
    	
    	printf("Ficheiro %s carregado com sucesso... \n",argv[1]);
      printf("\n");
    	
    }
    
	if(fildes==-1)
    {
        perror("Não abriu o ficheiro");
        exit(-1);
    }

 		if (strcmp(argv[2],"const")==0) {

				consts(argv[3],buffer,fildes);
				
			}

		if (strcmp(argv[2],"filter")==0) {
			coluna=atoi(argv[3]);
			operacao=argv[4];
			operando=atoi(argv[5]);
			filtro(coluna,operacao,operando,buffer,fildes);
		}

    if (strcmp(argv[2],"window")==0){
      coluna=atoi(argv[3]);
      operacao=argv[4];
      operando=atoi(argv[5]);
      window(coluna,operacao,operando,buffer,fildes);
    }
  
    close(fildes);

    
    return 0;
}