#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <stdlib.h>

#define bufS 512
#define max(a,b) ((a) > (b) ? (a) : (b))
#define min(a,b) ((a) < (b) ? (a) : (b))

//Funções auxiliares

char* itoa(int i, char b[]){
    char const digit[] = "0123456789";
    char* p = b;
    if(i<0){
        *p++ = '-';
        i *= -1;
    }
    int shifter = i;
    do{ //Move to where representation ends
        ++p;
        shifter = shifter/10;
    }while(shifter);
    *p = '\0';
    do{ //Move back, inserting digits as u go
        *--p = digit[i%10];
        i = i/10;
    }while(i);
    return b;
}

ssize_t readln(int fildes, char *buf, size_t nbyte) 
{
	int i = 0;

	while(i<nbyte-1 && 
		  read(fildes, buf+i, 1) > 0 &&
		  buf[i] != '\n') {

			i++;
	}

	if(i>=nbyte)
	buf[i] = 0;
	else
	buf[i+1] = 0;

	return i;
}

char** parser(char* str){
	char *pToken = strtok(str,":");
	int i=0;
	char **colunas = (char **)malloc(bufS*sizeof(char));
	while(pToken != NULL){
		colunas[i]= strdup(pToken);
		pToken = strtok(NULL, ":");
		i++;
	}
	return colunas;
}

void escreve(char* buffer, int valor){
	buffer[strlen(buffer)-1]='\0';
	char *str=(char *) malloc(bufS*sizeof(char));
	itoa(valor, str);
	strcat(buffer,":");
	strcat(buffer,str);
	strcat(buffer,"\n");
	write(1, buffer, strlen(buffer)+4);
}


//Funcões Principais

void consts(char* value){
	
	int nBytes,fd;
	char buffer[bufS];	

	fd = open("file.txt",O_RDONLY);
	
	while((nBytes=readln(fd,buffer,bufS))>0) {
			
		buffer[strlen(buffer)-1]='\0';
		strcat(buffer,":");
		strcat(buffer,value);
		strcat(buffer,"\n");
		write(1, buffer, nBytes+4);
	}
	
}

void filtro(int coluna, char* operador, int operando){
  	int nBytes,fd;
  	char **res;
  	char buffer[bufS];
  	fd = open("file.txt",O_RDONLY);

  	while((nBytes=readln(fd,buffer,bufS))>0){
	  	char *aux=strdup(buffer);
	  	res = parser(buffer);
	    if(strcmp(operador, "=")==0){
	     	if(atoi(res[coluna-1])==atoi(res[operando-1])) write(1,aux, nBytes+4);
	    }
	    if(strcmp(operador, ">=")==0){
	     	if(atoi(res[coluna-1])>=atoi(res[operando-1])) write(1,aux, nBytes+4);
	    }
	    if(strcmp(operador, "<=")==0){
	      	if(atoi(res[coluna-1])<=atoi(res[operando-1])) write(1,aux, nBytes+4);
	    }
	    if(strcmp(operador, ">")==0){
	      	if(atoi(res[coluna-1])>atoi(res[operando-1])) write(1,aux, nBytes+4);
	    }
	    if(strcmp(operador, "<")==0){
	      	if(atoi(res[coluna-1])<atoi(res[operando-1])) write(1,aux, nBytes+4);
	    }
	    if(strcmp(operador, "!=")==0){
	      	if(atoi(res[coluna-1])!=atoi(res[operando-1])) write(1,aux, nBytes+4);
	    }
    	free(aux);
	}
}

void window(int coluna, char* operador, int linhas){
	int nBytes,fd;
  	char **res;
  	char buffer[bufS];
  	fd = open("file.txt",O_RDONLY);
  	int i=0, h=0, atl=0, valor=0, f;
  	int valores[linhas];
  	while((nBytes=readln(fd,buffer,bufS))>0){
  		char *aux=strdup(buffer);
  		res = parser(buffer);
  		if(strcmp(operador,"avg")==0){
  			if(i<linhas){
  				valores[i]=atoi(res[coluna-1]);
  				escreve(aux,atl);
  				atl=atoi(res[coluna-1]);
  			}
  			if(i >= linhas){
  				for(f=0; f<linhas; f++){
  					valor +=valores[f];
  				}
  				valor=valor/linhas;
  				escreve(aux,valor);
  				valores[h]=atoi(res[coluna-1]);
  				h++;
  				if(h==linhas) h=0;
  				valor=0;
  			}
  		}
		if(strcmp(operador,"sum")==0){
  			if(i<linhas){
  				valores[i]=atoi(res[coluna-1]);
  				escreve(aux,atl);
  				atl=atoi(res[coluna-1]);
  			}
  			if(i >= linhas){
  				for(f=0; f<linhas; f++){
  					valor +=valores[f];
  				}
  				escreve(aux,valor);
  				valores[h]=atoi(res[coluna-1]);
  				h++;
  				if(h==linhas) h=0;
  				valor=0;
  			}
  		}
  		if(strcmp(operador,"max")==0){
  			if(i<linhas){
  				valores[i]=atoi(res[coluna-1]);
  				escreve(aux,atl);
  				atl=atoi(res[coluna-1]);
  			}
  			if(i >= linhas){
  				for(f=0; f<linhas; f++){
  					if(valor < valores[f]) valor = valores[f];
  				}
  				escreve(aux,valor);
  				valores[h]=atoi(res[coluna-1]);
  				h++;
  				if(h==linhas) h=0;
  				valor=0;
  			}
  		}
  		if(strcmp(operador,"min")==0){
  			if(i<linhas){
  				valores[i]=atoi(res[coluna-1]);
  				escreve(aux,atl);
  				atl=atoi(res[coluna-1]);
  			}
  			if(i >= linhas){
  				valor = valores[0];
  				for(f=0; f<linhas; f++){
  					if(valor > valores[f]) valor = valores[f];
  				}
  				escreve(aux,valor);
  				valores[h]=atoi(res[coluna-1]);
  				h++;
  				if(h==linhas) h=0;
  				valor=0;
  			}
  		}
  		i++;
  	}
}


int main(int argc,char *argv[]){
	int coluna;
	char* operacao;
	int operando;
	
		if (strcmp(argv[1],"const")==0) {

			consts(argv[2]);
		}

		if (strcmp(argv[1],"filter")==0) {
			coluna=atoi(argv[2]);
			operacao=argv[3];
			operando=atoi(argv[4]);
			filtro(coluna,operacao,operando);
		}

		if (strcmp(argv[1],"window")==0){
			coluna=atoi(argv[2]);
			operacao=argv[3];
			operando=atoi(argv[4]);
			window(coluna,operacao,operando);
		}

	return 0;
}
