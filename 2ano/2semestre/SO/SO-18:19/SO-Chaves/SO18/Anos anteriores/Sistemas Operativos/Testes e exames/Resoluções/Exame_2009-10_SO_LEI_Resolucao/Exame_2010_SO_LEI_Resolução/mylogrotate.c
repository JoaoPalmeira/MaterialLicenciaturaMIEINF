
int pid;
char *name;

void handler_alarm(int signum){
		kill(pid,SIGSTOP);
		if(!fork()){
			execlp("logrotate","logrotate",name);
			exit(EXIT_FAILURE);
		}
		wait(0);
		alarm(600);
		kill(pid,SIGCONT);
}


int main (int argc , char**argv){

	name = (char *) malloc (strlen("/var/log")*strlen(argv[1])*sizeof(char));
	strcpy(name,"/var/log/");
	strcat(name,argv[1]);
	int f = open(name,O_APPEND | O_CREAT);
	int pid;
	signal(SIGALRM,handler_alarm);
	alarm(600);


	if(!(pid = fork())){
		dup2(f,1);
		execlp(argv[1],argv[1],NULL);
		close(f);
		exit(EXIT_FAILURE);
	}
	waitpid(pid,0);
	close(f);
	exit(0);
}