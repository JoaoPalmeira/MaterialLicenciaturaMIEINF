#define _GNU_SOURCE
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <signal.h>
#include <ctype.h>
#include <stdbool.h>

void remover(int i){
	for(int z=0;z<=i;z++){
		char* n4=NULL;
		asprintf(&n4,"%d.txt",z);
		remove(n4);
	}
	remove("result.txt");
}

void process(char const* name){
	int pp[2];
	char c;
	int i=0,rd=0,wt=0,dupinput=0,duppipe=0,cl=0;
	int in=open(name,O_RDONLY,0666),out=open("result.txt",O_CREAT|O_WRONLY|O_TRUNC,0666),err=open("errors.txt",O_CREAT|O_WRONLY|O_APPEND,0666);
	if(err<0){
		perror("Erro no open do err\n");
		remover(i);
		_exit(-2);
	}
	int duperr=dup2(err,2);
	if(duperr<0){
		perror("Erro no dup do stderror\n");
		remover(i);
		_exit(-3);
	}
	write(err,"File: ",6);
	write(err,name,strlen(name));
	write(err,"\n",1);
	if(in<0){
		perror("Erro no open do in\n");
		remover(i);
		_exit(-4);
	}
	if(out<0){
		perror("Erro no open do out\n");
		remover(i);
		_exit(-5);
	}
	while(rd=read(in,&c,1)){
		if(rd<0){
			perror("Erro no read\n");
			remover(i);
			_exit(-6);
		}
		if(c=='/'){
			wt=write(out,&c,1);
			if(wt<0){
				perror("Erro no write\n");
				remover(i);
				_exit(-7);
			}
			while(rd=read(in,&c,1)){
				if(rd<0){
					perror("Erro no read\n");
					remover(i);
					_exit(-8);
				}
				wt=write(out,&c,1);
				if(wt<0){
					perror("Erro no write\n");
					remover(i);
					_exit(-9);
				}
				if(c=='\n')
					break;
			}
		}else if(c=='$'){
			i++;
			wt=write(out,&c,1);
			if(wt<0){
				perror("Erro no write\n");
				remover(i);
				_exit(-10);
			}
			char* tmp=NULL;
			int j=0,flag1=0,flag2=0;
			rd=read(in,&c,1);
			if(rd<0){
				perror("Erro no read\n");
				remover(i);
				_exit(-11);
			}
			int ant=0;
			if(isdigit(c)){
				ant=atoi(&c);
				flag2=1;
			}else if(c=='|')
				flag1=1;
			wt=write(out,&c,1);
			if(wt<0){
				perror("Erro no write\n");
				remover(i);
				_exit(-12);
			}
			while(rd=read(in,&c,1)){
				if(rd<0){
					perror("Erro no read\n");
					remover(i);
					_exit(-13);
				}
				if(c!='\n'){
					write(out,&c,1);
					if(wt<0){
						perror("Erro no write\n");
						remover(i);
						_exit(-14);
					}
					if(c!='|'){
						tmp=(char*)realloc(tmp,(j+1)*sizeof(char*));
						tmp[j]=c;
						j++;
					}
				}
				else
					break;
			}
			tmp[j]='\0';
			int ppflag=pipe(pp);
			if(ppflag<0){
				perror("Erro no pipe\n");
				remover(i);
				_exit(-15);
			}
			int f=fork();
			if(f==0){
				char** cmd=NULL;
				char* token=NULL;
				int k=0;
				for(token=strtok(tmp," ");token;k++,token=strtok(NULL," ")){
					cmd=(char**)realloc(cmd,(k+1)*sizeof(char*));
					cmd[k]=strdup(token);
				}
				if(k==0){
					perror("Comando invalido\n");
					remover(i);
					kill(getppid(),SIGUSR1);
					_exit(-16);
				}
				cmd=(char**)realloc(cmd,(k+1)*sizeof(char*));
				cmd[k]=NULL;
				if(flag1||flag2){
					char* n2=NULL;
					if((i-ant)<1 || (i-1)<1){
						write(err,"Comandos insuficientes\n",23);
						remover(i);
						kill(getppid(),SIGUSR1);
						_exit(-36);
					}
					if(flag1)
						asprintf(&n2,"%d.txt",i-1);
					else
						asprintf(&n2,"%d.txt",i-ant);
					int input=open(n2,O_RDONLY,0666);
					if(input<0){
						perror("Erro no open do input\n");
						remover(i);
						kill(getppid(),SIGUSR1);
						_exit(-17);
					}
					dupinput=dup2(input,0);
					if(dupinput<0){
						perror("Erro no dup do input\n");
						remover(i);
						kill(getppid(),SIGUSR1);
						_exit(-18);
					}
					cl=close(input);
					if(cl<0){
						perror("Erro no close\n");
						remover(i);
						kill(getppid(),SIGUSR1);
						_exit(-19);
					}
				}
				cl=close(pp[0]);
				if(cl<0){
					perror("Erro no close\n");
					remover(i);
					kill(getppid(),SIGUSR1);
					_exit(-20);
				}
				duppipe=dup2(pp[1],1);
				if(duppipe<0){
					perror("Erro no dup do pipe\n");
					remover(i);
					kill(getppid(),SIGUSR1);
					_exit(-21);
				}
				cl=close(pp[1]);
				if(cl<0){
					perror("Erro no close\n");
					remover(i);
					kill(getppid(),SIGUSR1);
					_exit(-22);
				}
				execvp(cmd[0],cmd);
				remover(i);
				perror("Erro no exec\n");
				kill(getppid(),SIGUSR1);
				_exit(-23);
			}else if(f>0){
				wait(&f);
				char* n=NULL;
				asprintf(&n,"%d.txt",i);
				int output=open(n,O_CREAT|O_RDWR|O_TRUNC,0666);
				if(output<0){
					perror("Erro no open do output\n");
					remover(i);
					_exit(-24);
				}
				wt=write(out,"\n>>>\n",5);
				if(wt<0){
					perror("Erro no write\n");
					remover(i);
					_exit(-25);
				}
				cl=close(pp[1]);
				if(cl<0){
					perror("Erro no close\n");
					remover(i);
					_exit(-26);
				}
				while(rd=read(pp[0],&c,1)){
					if(rd<0){
						perror("Erro no read\n");
						remover(i);
						_exit(-27);
					}
					wt=write(output,&c,1);
					if(wt<0){
						perror("Erro no write\n");
						remover(i);
						_exit(-28);
					}
					wt=write(out,&c,1);
					if(wt<0){
						perror("Erro no write\n");
						remover(i);
						_exit(-29);
					}
				}
				wt=write(out,"<<<\n",4);
				if(wt<0){
					perror("Erro no write\n");
					remover(i);
					_exit(-30);
				}
				cl=close(output);
				if(cl<0){
					perror("Erro no close\n");
					remover(i);
					_exit(-31);
				}
			}else{
				perror("Erro no fork\n");
				remover(i);
				_exit(-32);
			}
		}
	}
	cl=close(in);
	if(cl<0){
		perror("Erro no close\n");
		remover(i);
		_exit(-33);
	}
	cl=close(out);
	if(cl<0){
		perror("Erro no close\n");
		remover(i);
		_exit(-34);
	}
	write(err,"\n",1);
	cl=close(err);
	if(cl<0){
		perror("Erro no close\n");
		remover(i);
		_exit(-35);
	}
	for(int z=0;z<=i;z++){
		char* n3=NULL;
		asprintf(&n3,"%d.txt",z);
		remove(n3);
	}
	remove(name);
	rename("result.txt",name);
}

int main(int argc, char const* argv[]){
	if(argc==1){
		printf("No notebook was given\n");
		_exit(-1);
	}
	else{
		int f=0;
		for(int i=1;i<argc;i++){
			f=fork();
			if(f==0){
				process(argv[i]);
				_exit(0);
			}
			else if(f>0)
				wait(&f);
			else
				printf("Erro no fork da main\n");
		}
	}
	return 0;
}