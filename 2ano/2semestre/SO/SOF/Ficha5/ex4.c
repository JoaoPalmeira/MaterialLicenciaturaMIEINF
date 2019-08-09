#include <stdio.h>
#include <unistd.h>

int main(int argc, char **argv){
	int pfd1[2];
	char *s1;
	char *s2;

	pipe(pfd1);
	if(fork()==0){
		//filho 1
		close(pfd1[0]); // fechar saida
		
		dup2(pfd1[1], 1);
		execlp("ls", "ls", "/etc", NULL);
		//close(pfd1[1]); -> feito pelo exit
		_exit(1);
	}
	else {
		close(pfd1[1]); //fecha entrada

		if(fork()==0){
			//filho 2 -> só herda a saida porque o pai já fechou a entrada
			
			dup2(pfd1[0], 0);
			execlp("wc", "wc", "-l", NULL);
			//close(pfd2[1]);
			_exit(1);
		}
		else {
			close(pfd1[0]);
			wait(NULL);
			wait(NULL);
		}
	}
	return 0;
}
