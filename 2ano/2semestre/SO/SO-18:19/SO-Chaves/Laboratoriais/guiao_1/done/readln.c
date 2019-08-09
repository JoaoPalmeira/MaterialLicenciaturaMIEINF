#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>
#include<stdlib.h>
#include<inttypes.h>

ssize_t readln(int fildes,char *buf,size_t nbyte);
int main(int args,char* argv[]){
	
	char b[1024];
	int fd = open(argv[1],O_RDONLY); // apenas para ler
	int r;
	

			if((r = readln(fd,b,1024)) > 0)
					write(1,b,r); // r, ou seja, escreveu quantas leu
		
	close(fd);

	return 0;




}
/*	
ssize_t readln(int fildes,char *buf,size_t nbyte)
	{

		int n;
		int c = 0;	
				for(n = nbyte ; n>0 ;n--)
						 {
						read(fildes,buf,1);
						
						if((*buf) == '\n' || !buf) return c; 
    						

						c++;			
						}
				return c;		
				

	
	}
*/


// versão alternativa
ssize_t readln(int fildes,char* buf,size_t nbyte)
	{

		int p =0;
		int r;

		while((r=read(fildes,&buf[p],1)>0) && (buf[p]!='\n') && p<nbyte){ p++;}
										
	return p; 
	


	
	}
