#include <unistd.h>     /* chamadas ao sistema: defs e decls essenciais */
#include <string.h>
#include <sys/wait.h>
#include <fcntl.h>
/*
int execl(const char *path, const char *arg0, ..., NULL);
int execlp(const char *file, const char *arg0, ..., NULL);
int execv(const char *path, char *const argv[]);
int execvp(const char *file, char *const argv[]);
*/

int mysystem (char* comando){
int i=0;
	char *args[20];
	char *string;
	char *str;
	int ret=-1;
	int status;
	str=" ";
	string=strtok(comando," ");
	//Eliminar espaços
	
	while (string!=NULL){
		args[i]=string;
		string=strtok(NULL," "); // continua a processar a mesma string
		i++;
	}
	args[i]=NULL;
		
		status=fork();
		if (status==0){ // Sou o filho
			ret=execvp(args[0],args);
			_exit(ret);

		}	
		else { // Sou o pai
			ret=wait (&status);

		}

		return 0;
	}

int main(int argc, char const *argv[]){
	char string[100];
	while (read(0,string,100)!=0)
		mysystem(string);
	

	return 0;
}