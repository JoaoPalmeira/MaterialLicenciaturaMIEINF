#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>

#define bufS 512


void consts(char* value){
  char *buffer=(char *)malloc(bufS*sizeof(char));
  size_t bytes;
  //value[strlen(value)-1]='\0';
  while((bytes=read(0, buffer, bufS))>0){
      buffer[strlen(buffer)-1]='\0';
      strcat(buffer,":");
      strcat(buffer,value);
      strcat(buffer,"\n");
      //printf("%d %s\n",t,buffer);
      write(1, buffer, bytes+3);
      free(buffer);
      buffer=(char *)malloc(bufS*sizeof(char));
  }
}

char* campos[4];

char** parser(char* str){
  //char* campos[4];
  int i=0;
  char* pch = strtok (str," : ");
    while (pch != NULL)
    {
      campos[i]=strdup(&pch[i]);
      i++;

      pch = strtok (NULL, " : ");

    }
    return campos;
}

void filtro2(int coluna, char* operador, int operando){
  char *buffer=(char *)malloc(bufS*sizeof(char));
  size_t bytes;
  char** colunas;
  while((bytes=read(0, buffer, bufS))>0){
    colunas = parser(buffer);
    if(strcmp(operador, "=")==0){
      if(atoi(colunas[coluna])==operando) write(1,buffer, bytes);
    }
    else if(strcmp(operador, ">=")==0){
      if(atoi(colunas[coluna])>=operando) write(1,buffer, bytes);
    }
    else if(strcmp(operador, "<=")==0){
      if(atoi(colunas[coluna])<=operando) write(1,buffer, bytes);
    }
    else if(strcmp(operador, ">")==0){
      if(atoi(colunas[coluna])>operando) write(1,buffer, bytes);
    }
    else if(strcmp(operador, "<")==0){
      if(atoi(colunas[coluna])<operando) write(1,buffer, bytes);
    }
    else if(strcmp(operador, "!=")==0){
      if(atoi(colunas[coluna])!=operando) write(1,buffer, bytes);
    }
  }
}


int main(int argc,char *argv[]){
  int coluna;
  char* operador;
  int operando;
  
  if(strcmp(argv[1],"const")==0){
    consts(argv[2]);
  }
  if(strcmp(argv[1], "filter")==0){
    coluna=atoi(argv[2]);
    operador=argv[3];
    operando=atoi(argv[4]);
    filtro2(coluna, operador, operando);
  }
  return 0;
}