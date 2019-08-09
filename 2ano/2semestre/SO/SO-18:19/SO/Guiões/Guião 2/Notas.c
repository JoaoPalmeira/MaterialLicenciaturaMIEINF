#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */

/**
pid_t getpid(void);
	-> quem sou eu ? 
	-> retorna o valor do meu pid;

pid_t getppid(void);
	-> retorna o pid do meu pai;

pid_t fork(void);
	-> if( valor do returno == 0){
			codigo do filho;
		}
	-> if ( valor do returno != 0){
		codigo do pai;
		}

void _exit(int status);
	-> mata, sem duvida alguma, o programa.

pid_t wait(int *status);
	-> Coloca o pai a espera que qualquer filho morra;
	-> valor de retorno é o pid do filho;
	-> variavel *status, para saber so filho acabou bem ou mal.

pid_t waitpid(pid_t pid, int *status, int options);
int WIFEXITED(int status); -> macro
int WEXITSTATUS(int status); -> macro */

/**
NOTAS:
	-> Ver o comando ps;
	-> ps -a; ps -l; ps -al;
	-> ps aux;
*/
