

int *pids;
int nprocesses,actual;

int searchnext (){
	i = ((actual+1)%nprocesses);
	while(i!=actual){
		if(pids[i]!=-1) return i;
		i = ((i+1)%nprocesses);
	}
	return -1;
}

void handler_filhos(){
	pids[actual] = -1;
}

int main (int argc , char**argv){


	nprocesses = argc-1;
	int i,len=1024;
	int fd[2];
	char *buf;
	pids = (int*) malloc (nprocesses*sizeof(int));
	int change=0;
	signal(SIGCHLD,handler_filhos);
	pipe(fd);



	for(i=0 ; i<nprocesses ; i++){
		if(!(pids[i] = fork())){
			dup2(fd[1],1);
			close(fd[0]);
			execlp(argv[i+1],argv[i+1],NULL);
			close(fd[1]);
			exit(EXIT_FAILURE);
		}
		kill(pid[i],SIGSTOP);
	}



	i=0;
	while(1){
		actual = i;
		kill(pid[actual],SIGCONT);
		while(!change){
			readline(&buf,&len,fd[0]);
			write(1,buf,strlen(buf));
			if(!strcmp(buf,"OVER\n")) change=1;
		}
		kill(pid[actual],SIGSTOP);
		i = searchnext();
		if(i==-1) break;
		change=0;
	}

	for(i=0 ; i < nprocesses ; i++){wait(0);}
	exit(0);
}