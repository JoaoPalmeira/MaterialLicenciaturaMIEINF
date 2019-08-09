#include <stdio.h>
#define MAX_BUF 1024

void interpretador (char * linha);
int main () {
    char buf[MAX_BUF];
    while(fgets(buf,MAX_BUF,stdin)){
    	interpretador(buf);
    }
    return 0;
}

float somav = 0;
int num = 0;
void inserir (float valor) {
     somav += valor;
     num++;
}

void soma () {
	printf("%f\n",somav);
}

void interpretador (char * linha) {
	char cmd [MAX_BUF];
	float valor;
	int args;
	args = sscanf(linha, "%s %f" ,cmd , &valor);
	//printf("%s %d", cmd , args);

    if (strcmp(cmd,"ins")==0 && args == 2)
    	inserir (valor);

    if (strcmp(cmd,"soma")==0 && args == 1)
    	soma ();
}

