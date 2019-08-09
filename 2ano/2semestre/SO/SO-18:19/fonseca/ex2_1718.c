#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char const *argv[]){
	int* wt=NULL;
	int* rd=NULL;
	for(int i=1;i<argc;i++){
		wt=realloc(wt,i*sizeof(int));
		wt[i-1]=open(argv[i],O_CREAT | O_WRONLY,0777);
		if(wt[i-1]<0)
			_exit(-1);
		rd=realloc(rd,i*sizeof(int));
		rd[i-1]=open(argv[i],O_RDONLY);
		if(rd[i-1]<0)
			_exit(-1);
		int f=fork();
		if(f==0){
			int d=dup2(wt[i-1],1);
			if(d<0)
				_exit(-1);
			execlp(argv[i],argv[i],NULL);
			_exit(-1);
		}else if(f<0)
			_exit(-1);
	}
	int count=0,r=-1,end=argc-1;
	//array circular
	for(int i=0;end;i=(i+1)%(argc-1),count=0){
		void* buf=malloc(1);
		if(rd[i]!=-1){
			while((r=read(rd[i],buf,1))){
				int w=write(1,buf,1);
				if(strcmp(buf,"\n")==0)
					count++;
				if(count>=10){
					count=0;
					break;
				}
				if(r<0)
					_exit(-1);
				if(w<0)
					_exit(-1);
			}
		}
		//verifica se escreveu na ultima iteraçao
		if(r==0 && rd[i]!=-1){
			rd[i]=-1;
			end--;
		}
	}
	//fecha os ficheiros
	for(int i=1;i<argc;i++){
		int c1=close(wt[i-1]);
		if(c1<0)
			_exit(-1);
		int c2=close(rd[i-1]);
		if(c2<0)
			_exit(-1);
	}
	//liberta a memoria
	free(rd);
	free(wt);
	return 0;
}