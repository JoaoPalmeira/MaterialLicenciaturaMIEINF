#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>
#include<string.h>
// Em desenvolvimento, está com conflito a copiar não chega a copsuc!!!!
// buf matriz , apontador, melhorar essa parte e deve funcionar

int main(){
	char *buf[16];
	char aux[16];
	char linha[12] = {"\nbrfc $ \0"};
	int i=0,j,err;
	int r;
	int pid;
	char delimiter = ' ';	
	char* token;
	int status;
	while(1){
		while(i<10){ buf[i] = NULL; i++;}
		write(1,linha,10);
		i=0;
		j=0;
		err =1;
		
		while((r = read(0,&aux[i],1)) > 0 && aux[i]!='\n' && i<16){
		     i++;
		}
		
		
		token = strtok(aux,&delimiter);
		
		while(token!=NULL){
			buf[j] = (char*) malloc(sizeof(int)*16);
			strcpy(buf[j],token);
			token=strtok(NULL,&delimiter);
			j++;
		
		}
		buf[j-1][strlen(buf[j-1])-1] = '\0';		
				
				
		if((strcmp("exit",buf[0])) == 0) exit(1);
		else { pid = fork();
			if(!pid)
		       	 {execvp(buf[0],&buf[0]); exit(0);}
		     wait(&status);	
	  	     }
			   
		     }
	exit(1);

	}

