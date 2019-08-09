#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

#define BUFFER_SIZE 1024
#define READ_END    0 
#define WRITE_END   1

int main(){
	pid_t pid;
	int fd[2];
	char msg[BUFFER_SIZE] = "Olá amigos todos, devagarinho isto vai lá\n";
    char msg1[BUFFER_SIZE] = "Tá a chegar a pascoazinha!\n";
	char buf[BUFFER_SIZE];

	if( pipe(fd) == -1 ){
		perror("Pipe failded!");
		return 1;
	}

	pid = fork();
	if( pid == -1 ){
		perror("Fork failed!");
		return 1;
	}
	/* Parent process */
	if( pid > 0 ){
		/* Closes the read end of the pipe */
		close(fd[READ_END]);
        /* Writes the message in the write end of the pipe */
        write(fd[WRITE_END],msg,strlen(msg) + 1);
		/* Delay of 5sec */
		sleep(5);
		/* Writes the message in the write end of the pipe */
		write(fd[WRITE_END],msg1,strlen(msg1) + 1);
		/* Closes de write end of the pipe */
		close(fd[WRITE_END]);
	}
	/* Child process */
	else{
		/* Closes the write end of the pipe */
		close(fd[WRITE_END]);
		/* Waiting writings for the father process */
        while(1){
		    int n = read(fd[READ_END],buf,BUFFER_SIZE);
			if(n <= 0){
				close(fd[READ_END]);
				_exit(0);
				
			}			
			write(1,buf,n);
		}
	}
	return 0;
}