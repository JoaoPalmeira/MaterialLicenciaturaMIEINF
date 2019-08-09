#include <stdio.h>
#include <unistd.h>
#include <string.h>

int main(){
	char cmd[] = "grep -v ˆ# /etc/passwd | cut -f7 -d: | uniq | wc -l";
	int i, j = 0;
	char *cmds[100];
	char *elem[100];
	cmds[0] = strtok(cmd, "|");
	for(i=1;  (cmds[i] = strtok(NULL, "|"))!=NULL; i++);
	int pants[2];
	int pseg[2];
	for(i=0;  cmds[i]!=NULL; i++){
		printf("cmd[%d]=%s\n", i, cmds[i]);
		if(cmds[i+1]!=NULL){
			pipe(pseg);
		}
		if(fork()==0){
			if(i!=0){
				close(pants[1]);
				//redirecionar entrada
				dup2(pants[0], 0);
				close(pants[0]);
			}

			if(cmds[i+1]!=NULL){
				//redirecionar saida
				close(pseg[0]);
				dup2(pseg[1], 1);
				close(pseg[1]);
			}
			
			elem[0] = strtok(cmds[i], " \n");
			for(j=1; (elem[j]=strtok(NULL," \n"))!=NULL; j++);
			execvp(elem[0], elem);
			perror(cmds[i]);
			_exit(1);
		}

		if(i!=0){
			close(pants[0]);
			close(pants[1]);
		}
		pants[0]=pseg[0];
		pants[1]=pseg[1];
		
				
	}
	for(i=0;  cmds[i]!=NULL; i++) wait(NULL);
}
