#include <stdio.h>
#include <string.h>

/**
O tipo #tabuleiro designa o tabuleiro da batalha naval.
*/
typedef struct tabuleiro{
/**
O array de caracteres #tab designa o tabuleiro da batalha naval sem os segmentos.
*/
	char tab[100][100];
/**
O inteiro #lin designa a quantidade de linhas do tabuleiro.
*/
	int lin;
/**
O inteiro #col designa a quantidade de colunas do tabuleiro.
*/
	int col;
/**
O array de inteiro #segl designa os segmentos das linhas do tabuleiro da batalha naval.
*/
	int segl[100];
/**
O array de inteiro #segc designa os segmentos das colunas do tabuleiro da batalha naval.
*/
	int segc[100];
/**
O inteiro #val designa se o #tabuleiro é válido ou não.
*/
	int val;
}tabuleiro;

tabuleiro insP (tabuleiro puz);
tabuleiro insV (tabuleiro puz);
tabuleiro insH (tabuleiro puz);
void insM (tabuleiro puz);
tabuleiro lerTab (tabuleiro puz);
void interp (tabuleiro puz);

/**
A função #main chama o interpretador de comandos (#interp), dando-lhe um tabuleiro vazio.
*/
int main(){
	tabuleiro puz;
	interp (puz);
	return 0;
}

/**
A função #interp é o interpretador de comandos. 

-Recebe o tabuleiro e enquanto o comando não for 'q' esta corre um ciclo. Neste ciclo, quando o #tabuleiro for lido este verifica o comando inserido e aplica-lhe as ações correspondentes.
*/
void interp (tabuleiro puz) {
	char c='a';
	int r = 0, checkQ = 0;
	puz.val=1;
	while (checkQ==0) {if (puz.val==0) {r=0;puz.val=1;}
					if (scanf(" %c",&c)==1) {if (c=='c') {r=1;puz=lerTab(puz);}
											else if ((c=='m') && (r==1)) insM(puz);
											else if ((c=='h') && (r==1)) puz=insH(puz);
											else if ((c=='v') && (r==1)) puz=insV(puz);
											else if ((c=='p') && (r==1)) puz=insP(puz);
											else if (c=='q') checkQ=1;
											else printf("Nenhum comando válido foi inserido.\nInicio:\n");}
					else printf("Error on input.\nInicio:\n");}
	printf("Fim.\n");
}

/**
Quando o interpretador de comandos (#interp) lê o comando 'c' chama a função #lerTab que lê o tabuleiro e devolve-o.
*/
tabuleiro lerTab (tabuleiro puz) {
	int i,j;
	if (scanf ("%d %d",&puz.lin,&puz.col)==2){
		for(i=0;i<puz.lin;i++)
			if (scanf(" %d",&puz.segl[i])!=1) {puz.val=0;printf("Error on input.\nInicio:\n");return puz;};
		for(i=0;i<puz.col;i++)
			if (scanf(" %d",&puz.segc[i])!=1) {puz.val=0;printf("Error on input.\nInicio:\n");return puz;};
		for (i=0;i<puz.lin;i++)
			for (j=0;j<puz.col;j++)
				if (scanf(" %c",&puz.tab[i][j])!=1) {puz.val=0;printf("Error on input.\nInicio:\n");return puz;};
		return puz;
	}
	else {puz.val=0;printf("Error on input.\nInicio:\n");return puz;}
}

/**
Quando é inserido o comando 'm' no interpretador de comandos (#interp), este chama a função #insM que imprime o tabuleiro final.
*/
void insM (tabuleiro puz){
	int i,j;
	for (i=0;i<puz.lin;i++){
		for (j=0;j<puz.col;j++)
			printf("%c",puz.tab[i][j]);
		printf("%d\n",puz.segl[i]);
	}
	for (i=0;i<puz.col;i++)
		printf("%d",puz.segc[i]);
	printf("\n");
}

/**
Após inserir o comando 'h' no interpretador de comandos (#interp), a função #insH é chamada e substitui, no #tabuleiro, os valores não determinados, da linha inserida, por água.
*/
tabuleiro insH (tabuleiro puz){
	int i,j;
	if ((scanf(" %d",&j)==1) && (j<=puz.lin)){
		j--;
		for (i=0;i<puz.col;i++)
			if (puz.tab[j][i]=='.') puz.tab[j][i]='~';
		return puz;
	}
	else {printf("Error on input.\n");return puz;}
}

/**
Quando lido o comando 'v' no interpretador de comandos (#interp), a função #insV é chamada e substitui no #tabuleiro, os valores não determinados, da coluna inserida, por água.
*/
tabuleiro insV (tabuleiro puz){
	int i,j;
	if ((scanf(" %d",&j)==1) && (j<=puz.col)){
		j--;
		for (i=0;i<puz.lin;i++)
			if (puz.tab[i][j]=='.') puz.tab[i][j]='~';
		return puz;
	}
	else {printf("Error on input.\n");return puz;}
}

/**
O comando 'p', quando lido no interpretador de comandos (#interp), chama a função #insP, esta substitui o caracter escrito pelo que está na posição dada.
*/
tabuleiro insP (tabuleiro puz){
	char z;
	int i,j;
	if ((scanf(" %c %d %d",&z,&i,&j)==3) && ((z=='.') || (z=='~') || (z=='o') || (z=='O') || (z=='<') || (z=='>') || (z=='#') || (z=='^') || (z=='v')) && (i<=puz.lin) && (j<=puz.col)){
				(puz.tab[--i][--j]=z);
				return puz;
			}
	else {printf("Error on input.\n");return puz;}
}