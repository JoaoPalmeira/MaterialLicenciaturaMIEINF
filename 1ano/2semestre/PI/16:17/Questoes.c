#include <stdio.h>
#include <limits.h>
#include <stdlib.h>
#include <string.h>

/* 1 */

int soma() {

	int num=1, soma=0;

	printf("Insira numeros:\n");

		while (num!=0) {

			scanf("%d",&num);
			soma+=num;
		}

	return soma;
}

/* 2 */

int maior() {

	int num=1, maior=0;

	printf("Introduza numeros\n");

		while (num!=0) {

			scanf("%d",&num);

			if (num>maior) maior=num;
		}
	
	return maior;
}

/* 3 */

float media() {

	float media=0, i=0, num=1;

	printf("Introduza numeros\n");

		while (num!=0) {

			scanf("%f",&num);

			media+=num;
		
			i++;
		}

	i--;

	media = media/i;

	return media;
}

/* 4 */

int maior12 (){

    int num=1, maior1=0, maior2=0;

    printf("Introduza numeros\n");

        while(num!=0) {

            scanf("%d",&num);

            if (num>maior1) {

                maior2=maior1;
                maior1=num;

            }

            else if (num>maior2) maior2=num;
        }

    return maior2;
}

/* 5 */ 

int bitsUm (unsigned int n) {

    int cont=0;

    while(n>0) {

        if(n%2!=0) {

            n=n/2;
            cont++;
        }

        else 
            n=n/2;
    }

    return cont;
}

/* 6 */

int trailingZ (unsigned int n) {

    int res=0;
    
    while(n!=0 && (n%2)==0){
        
        res++;
        n/=2;

    }

    return res;
}

/* 7 */

int qDig (unsigned int n) {
    
    int c=1, absn=abs(n);
    
    while (absn>0){
    
        if ((absn/10) > 0){
           
            absn/=10;
            c++;
        }
        else
            break;
    }
    return c;
}

/* 8 */

int strlen1 (char str[]) {

    int i=0;

    while(str[i]!='\0') {

        i++;
    }

    return i;
}

/* 9 */

char *strcat1 (char s1[], char s2[]) {

    int i=0, j=0;

    while(s1[i]!='\0') {

        i++;
    }

    while(s2[j]!='\0') {

        s1[i] = s2[j] ;
        j++;
        i++;
    }

    s1[i] = '\0';

    return s1;
}

/* 10 */

char *strcpy1 (char *dest, char source[]) {

    int i=0;

    while(source[i]!='\0') {

        dest[i] = source[i];
        i++;
    }

    dest[i]='\0';

    return dest;
}

/* 11 */ 

int strcmp1 (char s1[], char s2[]) {

    int i=0;

    while(s1[i]!='\0' || s2[i]!='\0') {

        if (s1[i]<s2[i])
            return -1;
        if (s1[i]>s2[i])
            return 1;
        i++;
    }

    return 0;
} 

/* 12 */ 

char *strstr1 (char s1[], char s2[]) {

	int c1 = strlen(s1,s2);
	int c2 = strlen(s1,s2);

	int i, j;

	if (c2>c1) 
		return NULL;

	for(i=0;i<(c1-c2);i++)
		for(j=0;s2[j]!='\0' && s1[i+j] == s2[j];j++)
			
}

void interpretador(int trigger) {

	if (trigger==1) {printf("Soma: %d\n",soma());}

	if (trigger==2) {printf("Maior: %d\n",maior());}

	if (trigger==3) {printf("Media: %f\n",media());}

    if (trigger==4) {printf("Maior2: %d\n",maior12());}

    if (trigger==5) {

        printf("Introduza numero:\n");
        int num1;
        scanf("%d",&num1);
        printf("Numero de bits (=1): %d\n",bitsUm(num1));
    }

    if (trigger==6) {

        printf("Introduza numero:\n");
        int num2;
        scanf("%d",&num2);
        printf("Numero de bits (=0): %d\n",trailingZ(num2));
    }

    if (trigger==7) {

        printf("Introduza numero:\n");
        int num3;
        scanf("%d",&num3);
        printf("Sao precisos %d digitos para escrever o numero %d\n",qDig(num3),num3);    }

    if (trigger==8) {

        printf("Introduza uma string:\n");
        char aux1[64];
        scanf("%s",aux1);
        printf("A string '%s' tem de comprimento: %d\n",aux1,strlen1(aux1));
    }

    if (trigger==9) {

        char aux2[64];
        char aux3[64];
        printf("Introduza a primeira string:\n");
        scanf("%s",aux2);
        printf("Introduza a segunda string:\n");
        scanf("%s",aux3);
        printf("A string resultante da conactaçao: %s\n",strcat1(aux2,aux3));
    }

     if (trigger==10) {

        char dest[64];
        char source[64];
        printf("Introduza uma string:\n");
        scanf("%s",source);
        printf("A string destino: %s\n",strcpy1(dest,source));
    }

    if (trigger==11) {

        char aux4[64];
        char aux5[64];
        printf("Introduza a primeira string:\n");
        scanf("%s",aux4);
        printf("Introduza a segunda string:\n");
        scanf("%s",aux5);
        printf("Iguais: 0 \ns1 menor: -1 \ns2 menor: 1 \nResposta: %d\n", strcmp1(aux4,aux5));
    }

    else if (trigger<0 || trigger>50) printf("Numero indefenido.\n");

}


int main() {
	
	int trigger=1;
    
    printf("--------------------------------Menu---------------------------------\n");
    printf("1-Soma consecutivas de numeros terminada por 0.\n");
    printf("2-Maior elemento duma sequencia terminada por 0.\n");
    printf("3-Media de uma sequencia de numeros terminada por 0.\n");
    printf("4-Segundo maior numero duma sequencia terminada por 0.\n");
    printf("5-Numero de bits iguais a 1 de um dado numero.\n");
    printf("6-Numero de bits iguais a 0 de um dado numero.\n");
    printf("7-Numero de digitos precisos para escrever um numero em base decimal.\n");
    printf("8-Calcula o comprimento de uma string dada.\n");
    printf("9-Conacta duas strings retornado uma string.\n");
    printf("10-Copia uma string dado para outra string.\n");
    printf("11-Compara duas string lexicograficamente.\n");

	while (trigger!=0) {

		printf("Introduza numero questao (0 para sair):\n");

		scanf("%d",&trigger);

		interpretador(trigger);

	}

	printf("Thank you !!!\n");

	return 0;
}