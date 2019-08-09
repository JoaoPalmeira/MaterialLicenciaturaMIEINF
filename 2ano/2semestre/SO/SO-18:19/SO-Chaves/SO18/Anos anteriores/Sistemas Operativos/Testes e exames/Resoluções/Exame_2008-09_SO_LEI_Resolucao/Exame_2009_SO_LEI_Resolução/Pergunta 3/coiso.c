int main (int argc , char**argv){
	
	char* buf;
	char x[10];
	int lenght = 100;
	int fds[9];
	int act,nread;
	int pdentradaupper[2];
	int pdentradaupper][2];
	int pdsaida[2];
	pipe(pdentradalower);
	pipe(pdentradaupper);
	pipe(pdsaida);
	for(i=0;i<9;i++){
		x = itoa(i,x,10);
		fds[i] = (x,O_APPEND | O_CREATE);
	}

	if(!fork()){
		dup2(pdentradaupper[0],0);
		close(pd[1]);
		dup2(pdsaida[1],1);
		execlp("upper","upper",NULL);
		close(pd[0]);
		exit(0);
	}
	if(!fork()){
		dup2(pdentradalower[0],0);
		close(pdentrada[1]);
		dup2((pdsaida[1],1);
		execlp("lower","lower",NULL);
		close(pd[0]);
		exit(0);
	}

	while(1){
		if(nread = getline(&buf,&lenght,0)){
			act = atoi(buf[0]));
			if(act%2){
				write(pdentradaupper[1],buf,nread);
				read(pdsaida[0],buf,length);
				write(fds[act],buf,strlen(buf));
			}else{
				write(pdentradalower[1],buf,nread);
				read(pdsaida[0],buf,length);
				write(fds[act],buf,strlen(buf));
			}
		}
	}

	for(i=0;i<9;i++){
		close(fds[i]);
	}
	exit(0);
}