#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<fcntl.h>





int main(){
	int fd = open("/etc/passwd",O_RDONLY);
	dup2(fd,0); //stdinput para o fd
	close(fd);	
	
	int saida = open("saida.txt",O_WRONLY |O_CREAT | O_TRUNC ,0666);
	dup2(saida,1);
	close(saida);
	
	int erros = open("erros.txt",O_WRONLY | O_CREAT | O_TRUNC ,0666);
	dup2(erros,2);
	close(erros);
	
	int r,p=0;
	
	char buf[1024];
	 while( (r= read(0,&buf[p],1)) && buf[p] != '\n' && p<1024){ p++; write(1,buf,1); write(2,buf,1);}

/* Exercicio 2 ---> fork
	int pid = fork();
	
	if(!pid){
	if(fgets(buf,10,stdin) != NULL)
		buf[10] = '\0';
		printf(" %s",buf);
		exit(0);	
	}	
	int status;	
	wait(&status);	
 _exit(0); 
*/

}
