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
    
    int c=1, absn=n;
    
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

char *mystrstr (char s1[], char s2[]){
    
    int t1= strlen(s1);
    int t2= strlen(s2);
    int i,j;

    if(t2>t1) 
        return NULL;
    
    for(i=0;i<=t1-t2;i++){
        
        for(j=0;s2[j]!='\0' && s1[i+j]==s2[j];j++);
        
        if(s2[j]=='\0')

            return s1+i;    
    }

    return NULL;
}

/* 13 */

void strreV (char s[]) {
    int i;
	int j=0;
    int k = strlen(s)-1;

    char aux[64];

    for(i=0;s[i]!='\0';i++) {

        aux[j] = s[i];
        j++;

    }

    aux[j] = '\0';

    for(i=0;aux[i]!='\0';i++) {

        s[i] = aux[k];
        k--;
    }

    printf("%s\n",s);
    
}

/* 14 */

int vogais (char s) {

	if (s=='a') return 1;
	else if (s=='A') return 1;
	else if (s=='e') return 1;
	else if (s=='E') return 1;
	else if (s=='i') return 1;
	else if (s=='I') return 1;
	else if (s=='o') return 1;
	else if (s=='O') return 1;
	else if (s=='u') return 1;
	else if (s=='U') return 1;
	else return 0;
}

void strnov1 (char s[]) {

	int i,j=0;

	char aux[64];

	for (i=0; s[i]!='\0'; i++){

		if (!vogais(s[i])) {

			aux[j] = s[i];
			j++;
		}
	}

	aux[j] = '\0';

	printf("String sem vogais correspondete: %s\n",aux);
}

/* 15 */

void truncW (char t[], int n) {

	int i, j=0, n1=n;

	for(i=0;t[i]!='\0';i++) {

		if ((i<n) && (t[i]!='\0')) {

			t[j] = t[i];
			j++;
		}

		else if (t[i]==' ') {

			t[j] = t[i];
			j++;
			n = (i+1)+n1;
		} 
	}

	t[j] = '\0';

	printf("Texto truncado: %s\n",t);
}

/* 16 */

char charMaisfreq (char s[]) {

	int i, j, count, aux[64];

	char resultado;

	if (s[0]=='\0') 
		return '0';

	for (i=0; s[i]!='\0'; i++) {
        
        aux[i]=0;
    }

	for(i=0;s[i]!='\0';i++) {
		count=0;
		for(j=i;s[i]!='\0';j++){

			if (s[i]==s[j]) {
				count++;
				aux[i]=count;
			}
		}
	}

	aux[i]='\0';

	count=0;

	for(i=0;aux[i]!='\0';i++) {

		if(aux[i]>count) {

			resultado=s[i];
			count=aux[i];
		}
	}

	return resultado;
}

/* 17 */

int iguaisConsecutivos (char s[]) {

	int i, maior=1, count=1;

	if (s[0]=='\0') return 0;

	for(i=0;s[i]!='\0';i++){

		if (s[i]==s[i+1]) {

			count++;

			if(count>maior) {

				maior=count;
			}
		}

		else count=1;
	}

	return maior;
}

/* 18 */

int difConsecutivos (char s[]) {

	int i, maiordif=0, count=1;

	for(i=0;s[i]!='\0';i++) {

		if ((s[i+1]!='\0') && (s[i]!=s[i+1]) && (s[i]!=' ')) {

			count++;

			if(count>maiordif) {

				maiordif=count;
			}
		}

		else count=1;

	}

	return maiordif;
}

/* 19 */

int maiorPrefixo (char s1 [], char s2 []) {

	int i , count=0;

	for(i=0;s1[i]!='\0' || s2[i]!='\0';i++) {

		if (s1[i]==s2[i]) {

			count++;
		}

		else 
			break;
	}

	return count;
}

/* 20 */

int maiorSufixo (char s1[], char s2[]) {

	int i = strlen(s1)-1;
	int j = strlen(s2)-1;

	int count=0;

	while(i>=0 && j>=0) {

		if (s1[i]==s2[j]) {

			count++;
		}

		else
			break;

		i--;
		j--;
	}

	return count;
}

/* 21 */

int sufPref (char s1[], char s2[]) {
    int i , j;
    
    for (i=0; s1[i]!='\0'; i++) {
        for(j=0; s2[j] == s1[i] && s1[i]!='\0'; j++,i++);
        if (s1[i] == '\0') return j;
    }

    return 0;
}

/* 22 */

int contaPal (char s[]){
    
    int i, j, count=1;
    
    if(s[0]=='\0') return 0;

    for (i=0; s[i]!='\0'; i++){
        if (s[i]!=' ')
            break;
    }
    
    for (j=i; s[j]!='\0'; j++){
        
        if ((s[j]==' ') && (s[j+1]!=' ') && (s[j+1]!='\0')) {
            
            count++;
            
        }
    }
    
    return count;
}

/* 23 */

int contaVogais(char s[]) {

	int i, count=0;

	for(i=0;s[i]!='\0';i++) {

		if ( s[i]=='a' || s[i]=='e' || s[i]=='i' || s[i]=='o' || s[i]=='u' ||
			s[i]=='A' || s[i]=='E' || s[i]=='I' || s[i]=='O' || s[i]=='U') {

			count++;
		}
	}

	return count;
}

/* 24 */

int elem(char a, char s[]) {

	int i;

	for(i=0;s[i]!='\0';i++) {

		if (a==s[i]) return 1;

		else return 0;
	}

    return 0;
}

int contida(char a[], char b[]) {

	int i, j, resp=1;

	for(i=0;a[i]!='\0';i++){

		if(elem(a[i],b)) resp=resp;

		else resp=0;

	}

	return resp;
}

/* 25 */

int palindroma (char s[]) {

	int i, j=strlen(s)-1, resp=1;
	
	if(s[0]=='\0') resp=resp;

	for(i=0;i<j;i++,j--) {

		if (s[i]==s[j]) resp=resp;

		else resp=0;
	}

	return resp;
}

/*28 */

void insere (int v[], int N, int x) 
{
	int i;
	
	for(i=N-1;i>=0 && v[i]>x;i--)
		v[i+1]=v[i];
	
	v[i+1]=x;
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

    	char aux1[64];
        printf("Introduza uma string: ");
        scanf("%s",aux1);
        printf("A string '%s' tem de comprimento: %d\n",aux1,strlen1(aux1));
    }

    if (trigger==9) {

        char aux2[64];
        char aux3[64];
        printf("Introduza a primeira string:\n");
        scanf("%[^\n]s", aux2);
        printf("Introduza a segunda string:\n");
        scanf("%s", aux3);
        printf("A string resultante da conactaçao: %s\n",strcat1(aux2,aux3));
    }

     if (trigger==10) {

        char dest[64];
        char source[64];
        printf("Introduza uma string: ");
        scanf("%s",source);
        printf("A string destino: %s\n",strcpy1(dest,source));
    }

    if (trigger==11) {

        char aux4[64];
        char aux5[64];
        printf("Introduza a primeira string: ");
        scanf("%s", aux4);
        printf("Introduza a segunda string: ");
        scanf("%s",aux5);
        printf("Iguais: 0 \ns1 menor: -1 \ns2 menor: 1 \nResposta: %d\n", strcmp1(aux4,aux5));
    }

     if (trigger==12) {

        char aux6[64];
        char aux7[64];
        printf("Introduza a primeira string:\n");
        scanf("%s",aux6);
        printf("Introduza a segunda string:\n");
        scanf("%s",aux7);
        printf("A posiçao onde s2 ocorre em s1 é: %s\n", mystrstr(aux6,aux7));
    }

     if (trigger==13) {

     	char aux8[64];
        printf("Introduza uma string: ");
        scanf("%s",aux8);
        strreV(aux8);
    }

     if (trigger==14) {

     	char aux9[64];
        printf("Introduza uma string: ");
        scanf("%s", aux9);
        strnov1(aux9);
    }

    if (trigger==15) {

     	char aux10[64];
     	int num=0;   

        printf("Introduza uma string:\n");
		gets(aux10);    

		printf("Introduza numero para truncar:\n");
        scanf("%d",&num);
        
        truncW(aux10,num);

    }

      if (trigger==16) {

     	char aux11[64];
        printf("Introduza uma string: ");
        scanf("%s",aux11);
        printf("E o char mais frequente é: %c\n", charMaisfreq(aux11));
    }

  	  if (trigger==17) {

     	char aux12[64];
        printf("Introduza uma string: ");
        scanf("%s",aux12);
        printf("Numero maior de char consecutivos: %d\n", iguaisConsecutivos(aux12));
    }
	  
	  if (trigger==18) {

     	char aux13[64];
        printf("Introduza uma string: ");
        scanf("%s",aux13);
        printf("Numero maior de char diferentes: %d\n", difConsecutivos(aux13));
    }

      if (trigger==19) {

     	char aux14[64];
        printf("Introduza a primeira string: ");
        scanf("%s",aux14);
        char aux15[64];
        printf("Introduza a segunda string: ");
        scanf("%s",aux15);
        printf("O comprimento de maior prefixo entre as duas strings: %d\n", maiorPrefixo(aux14,aux15));
    }

      if (trigger==20) {

     	char aux16[64];
        printf("Introduza a primeira string: ");
        scanf("%s",aux16);
        char aux17[64];
        printf("Introduza a segunda string: ");
        scanf("%s",aux17);
        printf("O comprimento de maior sufixo entre as duas strings: %d\n", maiorSufixo(aux16,aux17));
    
    }

       if (trigger==21) {

     	char aux18[64];
        printf("Introduza a primeira string: ");
        scanf("%s",aux18);
        char aux19[64];
        printf("Introduza a segunda string: ");
        scanf("%s",aux19);
        printf("O comprimento de maior sufixo entre a 1º e prefixo da 2º: %d\n", sufPref(aux18,aux19));
    }

       if (trigger==22) {

     	char aux20[64];
        printf("Introduza a primeira string: ");
        gets(aux20);
        printf("A string tem %d palavras.\n", contaPal(aux20));
    }



  		if (trigger==23) {

     	char aux21[64];
        printf("Introduza uma string: ");
        scanf("%s",aux21);
        printf("Numero de vogais da string: %d\n", contaVogais(aux21));
    }

     	if (trigger==24) {

     	char aux22[64];
        printf("Introduza a primeira string: ");
        scanf("%s",aux22);
        char aux24[64];
        printf("Introduza a segunda string: ");
        scanf("%s",aux24);
        printf("A primeira string esta contida na segunda 1(Verdade) 0(Falso): %d\n", contida(aux22,aux24));
    }

    	if (trigger==25) {

     	char aux23[64];
        printf("Introduza uma string: ");
        scanf("%s",aux23);
        printf("Palavra palindrome, 1(Verdade) 0(Falso): %d\n", palindroma(aux23));
    }

    	if (trigger==28) {

    		int arr[5] = {1,2,3,5,6};
    		int tam=5;
    		int valor;

    		printf("Introduza elemento\n");
    		scanf("%d",&valor);
    		insere(arr,tam+1,valor);
    		
    		for(int i=0;i<tam+1;i++){

    			printf("posiçao: %d elemento: %d\n",i,arr[i]);
    		}

    	}



    else if (trigger<0 || trigger>50) printf("Numero indefenido.\n");

}


int main() {
	
	int trigger=1;

    printf("\n");
    printf("-----------------------------------------Menu-------------------------------------------\n");
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
    printf("12-Dada duas listas, determina a posiçao onde a segunda ocorre na primeira\n");
    printf("13-Inverte uma string dada\n");
    printf("14-Dada uma string retira as vogais da mesma.\n");
    printf("15-Dada uma string e uma numero, trunca a string com o numero dado.\n");
    printf("16-Dada uma string calcula qual é o char mais frequente da mesma.\n");
    printf("17-Dada uma string calcula o numero de letras iguais consecutivas.\n");
    printf("18-Dada uma string calcula o numero de letras diferente consecutivas.\n");
    printf("19-Dadas duas strings calcula o comprimento do maior prefixo entre as duas.\n");
    printf("20-Dadas duas strings calcula o comprimento do maior sufixo entre as duas.\n");
    printf("21-Dadas duas strings calcula o comprimento maior do sufida da 1º com o prefixo da 2º.\n");
    printf("22-Dada uma string calcula quantas palavras a mesma possui.\n");
    printf("23-Dada uma string conta qas vogais da mesma.\n");
    printf("24-Dada duas strings verifica se a primeira esta contida na segunda.\n");
    printf("25-Dada uma palavra testa se esta é palindroma.\n");
    printf("\n");

	while (trigger!=0) {

		printf("Introduza o numero da questao (0 para sair):\n");

		scanf("%d",&trigger);

		interpretador(trigger);

	}

	printf("Thank you !!!\n");

	return 0;
}
