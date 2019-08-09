#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

//exercicio3
int main(){
	for(int i = 0; i <10; i++){
		if(!fork()){
			printf("meu pid: %d, pid do pai: %d\n", getpid(), getppid());
			_exit(i+1);
		}
		int s;
		wait(&s);
		if(WIFEXITED(s)){
			printf("Filho terminou com:%d\n", WEXITSTATUS(s));
		}
	}
}