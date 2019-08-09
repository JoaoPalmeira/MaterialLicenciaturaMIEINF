#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */


//GUIAO 1 
//EX1
#define SIZE_TO_WRITE 10*1024*1024
int main(int argc, char const *argv[]){
	int fd;
	if(argc>1){
		fd = open(argv[1], O_CREAT | O_TRUNC | O_WRONLY, 0777);

		//controlo de erros
		if(fd=-1){
			perror("Erro no file descriptor");
			return -1;
		}

		//escrever
		char* buf = "a";
		for(int i=0; i<SIZE_TO_WRITE; i++){
			write(fd,buf, 1);
		}
		close(fd);
		return 0;
	}
	else{
		printf("ERRO!\n");
		return -1;
	}
}

//EX2 (MYCAT)
int main(int argc, char const *argv[]){
	int n;
	char* buf = (char*) malloc(sizeof(char));
	while((n=read(0,buf,1))>0){
		write(1,buf,1);
	}
}

//EX3 (MYCAT-V2)
int main(int argc, char const *argv[]){
	int n, nbytes=atoi(argv[1]);
	char* buf = (char*) malloc(sizeof(char) * nbytes);
	while((n=read(0,buf,nbytes))>0){
		write(1,buf,n);
	}
}

//EX5
ssize_t readln(int fildes,void *buf, size_t nbyte){
	char c;
	int n, t=0;
	while((n=read(fildes, &c, 1))>0 && &c != '\n'){
		t++;
	}
	return t;
}
int main(int argc, char const *argv[]){
	char* buf;
	int fd = open(argv[1], O_RDONLY);
	int n = readln(fd, buf, 1);
	printf("%d\n",n);
	return 0;
}

//EX6
ssize_t readln2(int fildes,void *buf, size_t nbyte){
	char c;
	int n, t=0;
	while((n=read(fildes, &c, 1))>0 && &c != '\n'){
		buf[t]=c;
		t++;
	}
	return t;
}
int main(int argc, char const *argv[]){
	char* buf = (char*) malloc(sizeof(char)*1024);
	int n, nl=0;
	int fd = open(argv[1], O_RDONLY);
	while((n = readln2(fd, buf, 1))>0){
		nl++;
		printf("linha %d :: %s \n",nl, buf);
	}
	printf("%d\n",n);
	return 0;
}

























