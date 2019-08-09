#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

#define Max 1014

ssize_t readln1(int fildes, char *buf, size_t nbyte){
	
	int i=0;
	
	while(i<nbyte-1 && read(fildes,buf+i,1)>0 && buf[i]!='\n'){
		i++;
	}

	if(i>=nbyte){
		buf[i] = 0;
	}
	else{
		buf[i+1] = 0;
	}
	return i;
}

int main(int argc, char const *argv[])
{
	int fdin;
	char buffer[Max];
	int n;
	int i=1;

	if(argc>2){
		write(1,"Nº de argumentos inválido\n",27);
		return -1;
	}
	else{

		if(argc == 1){

			while((n=readln1(0,buffer,Max))>0){

				write(1,"     ",5);
				write(1,"um",2);
				write(1,"   ",3);
				write(1,buffer,n);
				write(1,"\n",1);
			}

		}
		else{
			fdin = open(argv[1], O_RDONLY);

			while((n=readln1(fdin,buffer,Max))>0){
				
				write(1,"     ",5);
				printf("%d\n",i++);
				write(1,"   ",3);
				write(1,buffer,n);
				write(1,"\n",1);
			}

			close(fdin);
		}
	}
	return 0;
}