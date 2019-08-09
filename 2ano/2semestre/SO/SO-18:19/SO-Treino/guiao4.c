#include <unistd.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

//GUIAO 4
//EX 1
int main(int argc, char const *argv[]){
	//Cria o descritor e faço uma cópia do standard input
	int fd = open("/etc/passwd",O_RDONLY);
	dup2(fd,0);
	close(fd);
	//Cria descritor saida
	int fdS = open("saida.txt", O_RDONLY | O_WRONLY | O_CREAT);
	dup2(fdS,1);
	close(fdS);
	//Cria descritor erro
	int fdE = open("erros-txt", O_RDONLY | O_WRONLY | O_CREAT);
	dup2(FdE,2);
	close(fdE);

	char buf[1024];
	int n = read(0,buf,1024);
	write(1,buf,n);
	write(2,buf,n);
	return 0;
}

//EX 2
int main(int argc, char const *argv[]){
	//Cria o descritor e faço uma cópia do standard input
	int fd = open("/etc/passwd",O_RDONLY);
	dup2(fd,0);
	close(fd);
	//Cria descritor saida
	int fdS = open("saida.txt", O_RDONLY | O_WRONLY | O_CREAT);
	dup2(fdS,1);
	close(fdS);
	//Cria descritor erro
	int fdE = open("erros-txt", O_RDONLY | O_WRONLY | O_CREAT);
	dup2(FdE,2);
	close(fdE);

	char buf[1024];
	int status;
	pid_t pid = fork();
	if(pid==-1){
		perror("Erro no fork");
		return -1
	}
	//processo pai
	else if(pid>0){
		pid = wait(&status);
		return status;
	}
	//processo filho
	else{
		int n = read(0,buf,1024);
		write(1,buf,n);
		write(2,buf,n);
		_exit(0);
	}
	return 0;
}

//EX 3
int main(int argc, char const *argv[]){
	//Cria o descritor e faço uma cópia do standard input
	int fd = open("/etc/passwd",O_RDONLY);
	dup2(fd,0);
	close(fd);
	//Cria descritor saida
	int fdS = open("saida.txt", O_RDONLY | O_WRONLY | O_CREAT);
	dup2(fdS,1);
	close(fdS);
	//Cria descritor erro
	int fdE = open("erros-txt", O_RDONLY | O_WRONLY | O_CREAT);
	dup2(FdE,2);
	close(fdE);
	//Executar o comando wc
	execlp("wc","wc", NULL);
	return 0;
}

//EX 4
int main(int argc, char const *argv[]){
	if ( argc < 2){
		return -1;
	}

	int fd_in = 0, fd_out = 1;
	int i;

	for(i=1; i<argc; i++){
		if(!strcmp(argv[i], "-i")){
			fd_in = open(argv[i+1], O_RDONLY | O_WRONLY | O_CREAT, 777);
			dup2(fd_in, 0);
		}
		else{ 
			if(!strcmp(argv[i], "-o")){
				fd_out = open(argv[i+1], O_RDONLY | O_WRONLY | O_CREAT, 777);
				dup2(fd_out,1)
			}
			else{
				execvp(argv[5],&argv[5]);
			}
		}
	}
	return 0;
}
























