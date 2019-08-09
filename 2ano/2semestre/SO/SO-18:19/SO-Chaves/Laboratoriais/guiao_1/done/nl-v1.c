#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<string.h>
#include <fcntl.h>

ssize_t readln(int fildes,char *buf,size_t nbyte);
int main(int argc,char* argv[])
{
	int i = 1;
	int j,control;
	int file;
	char aux[2000];
	char buff[1024];
	char *p;
	int r=0;
	char linhas[1024];
	if(argc > 1){
		file = open(argv[1],O_RDONLY);
			
		while((r = readln(file,buff,1024)) > 0){
			
			sprintf(linhas,"\t%d ",i);
			i++;
			write(1,linhas,strlen(linhas));
			buff[r+1] = '\n';
			write(1,buff,r+1);	

			} 
		

		close(file);
			
	}
	else{
		while((r=readln(0,buff,1021)) > 0){
			
		// usar o sprintf
			sprintf(linhas,"\t%d ",i);	
			i++;
			write(1,linhas,strlen(linhas));
			buff[r+1] = '\n';
			write(1,buff,r+1);
		}

	}
	





	_exit(0);
}





ssize_t readln(int fildes,char* buf,size_t nbyte)
	{

		int p =0;
		int r;

		while((r=read(fildes,&buf[p],1)>0) &&(buf[p]!='\0') && (buf[p]!='\n') && p<nbyte){ p++;}
										
	return p; 
	


	
	}




