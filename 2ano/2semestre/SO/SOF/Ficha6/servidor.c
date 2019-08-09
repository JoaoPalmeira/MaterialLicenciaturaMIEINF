#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>


int main(int argc, char **argv){
   	int fd1, fd2, n; 
	char buffer[100];

	mkfifo("log", 0600);


        if(argc!=2){
                printf("falta argumento!\n");
                return 1;
        }
	
	fd2 = open(argv[1], O_CREAT | O_TRUNC | O_WRONLY, 0640);

   	while(1){
        	fd1 = open("log", O_RDONLY);

		while(n=read(fd1, buffer, 100)){
			write(fd2, buffer, n);
		} 


		close(fd1);
   }
	close(fd2);
}
