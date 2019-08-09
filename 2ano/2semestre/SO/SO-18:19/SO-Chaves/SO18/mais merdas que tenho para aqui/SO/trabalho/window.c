#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#define PIPE_BUF 20

int max(int* array, int n)
{
	int res=0,i;
	for(i=0;i<n;i++)
	{
		if(array[i]==0)
			break;
		if(res<array[i])
			res=array[i];
	}
	return res;
}

int min(int* array, int n)
{
	int res=0,i;
	for(i=0;i<n;i++)
	{
		if(array[i]==0)
			break;
		if(res>array[i])
			res=array[i];
	}
	return res;
}

int avg(int* array, int n)
{
	int res=0,i;
	for(i=0;i<n;i++)
	{
		if(array[i]==0)
			break;
		res+=array[i];
	}
	if(i==0)
		return 0;
	return res/i;
}

int sum(int* array, int n)
{
	int res=0,i;
	for(i=0;i<n;i++)
	{
		if(array[i]==0)
			break;
		res+=array[i];
	}
	return res;
}

int main(int argc, char const *argv[])
{
	if(argc!=4)
	{
		perror("Formato: window <coluna> <operação> <linhas>, por exemplo window 4 avg 2\n");
		_exit(-1);
	}
	if(strcmp(argv[2],"max") && strcmp(argv[2],"min") && strcmp(argv[2],"avg") && strcmp(argv[2],"sum"))
	{
		perror("Operador invalido\n");
		_exit(-2);
	}
	char linha[PIPE_BUF];
	char aux[PIPE_BUF];
	char* token;
	int tam=atoi(argv[3]),n,resultado=0,i,j;
	int array[tam];
	for(i=0;i<tam;i++)
		array[i]=0;
	for(i=0;n=read(0,linha,PIPE_BUF);i=(i+1)%tam)
	{
		strncpy(aux,linha,n);
		token=strtok(aux,":");
		for(j=atoi(argv[1])-1;token!=NULL && j>0;j--)
			token=strtok(NULL,":");
		if(!strcmp(argv[2],"max"))
			resultado=max(array,tam);
		if(!strcmp(argv[2],"min"))
			resultado=min(array,tam);
		if(!strcmp(argv[2],"avg"))
			resultado=avg(array,tam);
		if(!strcmp(argv[2],"sum"))
			resultado=sum(array,tam);
		array[i]=atoi(token);
		write(1,linha,n-1);
		write(1,":",1);
		printf("%d\n", resultado);
		//write(1,&resultado,sizeof(int));
	}
	return 0;
}