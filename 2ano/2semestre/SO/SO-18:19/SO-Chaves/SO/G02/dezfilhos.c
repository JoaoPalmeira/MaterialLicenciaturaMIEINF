/* SO2 - Gestão de Processos */

#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char argv[]) {
	int i,status;
	for(i=0; i<5; i++)
	{
		if(fork()==0) {
			printf("Processo Filho --  ID: %d  PARENT: %d\n",getpid(),getppid());
			exit(i);
		}
		else {
			int idFilho = wait(&status);
			printf("Processo Pai   --  ID: %d\n                     DEAD SON  NUM: %d IDS %d\n",getpid(),WEXITSTATUS(status)+1,idFilho);
		}
	}
	wait(NULL);
	return 1;	
}