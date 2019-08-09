#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 1024

//------------------1as 50 QUESTOES
//----1
int main () {
	int n, x=0;
    scanf ("%d", &n);
    while(n!=0){
    	x+=n;
    	scanf ("%d", &n);
    }
    printf("O resultado é:\n", x);
    return 0;
}

//----2
int main2 (){
	int n, maior=0;
	scanf ("%d", &n);
	while (n!=0){
		scanf ("%d", &n);
		if(n>maior) {
			maior = n;
		}
	}
	printf("O maior número é:\n", maior);
	return 0;
}

//----3
int main3 (){
	int n, soma=0, div=0, m;
	scanf ("%d", &n);
	while (n!=0){
		soma+=n;
		scanf ("%d", &n);
		div++;
	}
	m=soma/div;
	printf("A média é:\n", m);
	return 0;
}

//----4
int main4 (){
	int n, maior=0, sm;
    scanf("%d", &n);
    while (n!=0){
        scanf("%d",&n);
        if(n>maior) {
            sm=maior;
            maior=n;
        }
        else if (n==maior);
        else if (n > sm) sm = n;
    }
    printf("O segundo maior elemento é: %d", sm);
    return 0;
}

//----5
int bitsUm (unsigned int n){
	int count = 0;
    while (n) {
        if (n%2 == 1) count ++;
        n=n/2;
    }
    return count;
}

//----6
int trailingZ (unsigned int n){
	int count = 0;
    while (n && n%2==0) {
        count++;
        n=n/2;
    }
    return count;
}

//----7
int qDig (unsigned int n){
	int count = 1;
    while (n>=10) {
        count ++;
        n=n/10;
    }
    return count;
}

//----8
int strlen2 (char str[]){
	int i;
    for(i=0;str[i]!='\0'; i++);
    return i;
}

//----9
char *strcat2 (char s1[], char s2[]){
	int i, j;
    for (i=strlen (s1), j=0; s2[j]!='\0'; i++,j++) {
        s1[i] = s2[j];
    }
    s1[i]='\0' ;
    return s1;
}

//----10
char *strcpy2 (char *dest, char source[]){
	int i;
    for(i=0;source[i]!='\0';i++) {
        dest[i] = source[i];
    }
    dest[i]='\0';
    return dest;
}

//----11
int strcmp2 (char s1[], char s2[]){
	int i=0;
    while (s1[i]==s2[i] && s1[i]!='\0')
        i++;
    return (s1[i] - s2[i]);
}

//----12
char *strstr2 (char s1[], char s2[]){
	int i, j;
    if(s1[0]=='\0' && s2[0]=='\0') return s1;
    for (i=0; s1[i]!='\0'; i++) {
        int s=0; j=i;
        while (s1[j]==s2[s] && s1[j]!='\0') {
            s++; j++;
        }
        if (s2[s]=='\0') {
            return &s1[i]; 
            break;
        }
    }
    return NULL;
}

//----13
void strrev (char s[]){
	int i, len; char c;
    for (len =0; s[len]!='\0';len++);
    for (i=0; i<len/2; i++) {
        c=s[i];
        s[i]=s[len-1-i];
        s[len-1-i]=c;
    }
}

//----14
void strnoV (char s[]){
	int i, e=0;
    for (i=0; s[i]!='\0'; i++) {
        if (s[i]!='A' && s[i]!='a' && s[i]!='E' && s[i]!='e' && s[i]!='I' && s[i]!='i' && s[i]!='O' && s[i]!='o' && s[i]!='U' && s[i]!='u') {
            s[e]=s[i]; e++;
        }
    }
    s[e]='\0';
}

//----15
void truncW (char t[], int n){
	int i=0, j=0, c=n;
    while (t[i]!='\0') {
        if((t[i])== ' ') {
            t[j] = t[i];
            j++; i++; c=n;
            }
        else {
            if (c!=0) {
                t[j] = t[i];
                j++; i++; c--;
            }
            else i++;
        }
    }
    t[j]='\0';
}

//-----16
char charMaisfreq (char s[]){
	int i, j, c, m=0; char f='0';
    for (i=0; s[i]!='\0'; i++) {
        c=0;
        for (j=0; s[j]!='\0'; j++) {
            if (s[i]==s[j]) c++;
        }
        if (c>m) { m=c; f = s[i];}
    }
    return f;
}

//----17
int iguaisConsecutivos (char s[]){
	int i, c=1, m=0;
    for (i=0; s[i+1]!='\0'; i++) {
        if (s[i]!=s[i+1]) c=1;
        if (s[i]== s[i+1]) c++;
        if (c>m) m=c;
    }
    return m;
}

//----18
int difConsecutivos (char s[]){
	int i, j;
    int count = 1;
    int maximo = 0;
	
	for(i=0; s[i]!= '\0'; i++) {
		for(j=i+1;s[j]!='\0' && s[j]!=s[j-1]; j++)
			count++;
			if (count > maximo) maximo = count;
			count = 1;
	}
	return maximo;
}

//----19
int maiorPrefixo (char s1 [], char s2 []){
	int i;
    for (i=0;s1[i]!='\0' && s2[i]!='\0' && s1[i]==s2[i];i++);
    return i;
}

//----20
int maiorSufixo (char s1[], char s2[]) { 
    int i, j;
    for(i=strlen(s1)-1, j=strlen(s2)-1; i>=0 && j>=0 && s1[i]==s2[j]; i--,j--);
    return (strlen(s1)-i-1);
}

//----21
int sufPref (char s1[], char s2[]){
	int i, ii, j,c=0, m=0;
    for(i=0;s1[i]!='\0';i++) {
        ii=i;
        if (s1[i]==s2[0]) {
            for (j=0;s1[ii]!='\0' && s1[ii]==s2[j] && s2[j]!='\0';j++,ii++) c++;
        }
        if (s1[ii]!='\0') c=0;
        else {
            if (c>m) m=c;
        }
    }
    return m;
}

//----22
int contaPal (char s[]) {
   int i=0,c=0, marcador=0;
    while(s[i]!='\0') {
       marcador = 0;
       while((s[i]!='\0') && (s[i]==' ')) i++;
       while((s[i]!='\0') && (s[i]!=' '))   {
            i++;
            marcador = 1;
        }
        if(marcador == 1) c++;
    }
    return c;
}

//----23
int contaVogais (char s[]){
	int i, c=0;
    for (i=0; s[i]!='\0'; i++) {
        if (s[i]=='A' || s[i]=='a' || s[i]=='E' || s[i]=='e' || s[i]=='I' || s[i]=='i' || s[i]=='O' || s[i]=='o' || s[i]=='U' || s[i]=='u') c++;
    }
    return c;
}

//----24
int contida (char a[], char b[]){
	int i,j;

	for(i=0; a[i] != '\0'; i++){
		for(j=0; b[j] != '\0'; j++){
			if(b[j]==a[i]) break;
		}
		if (b[j] == '\0') return 0;
	}
	return 1;
}

//----25
int palindorome (char s[]){
	int i, j=strlen(s);
    for(i=0; i!=j/2 && s[i]==s[j-i-1]; i++);
    return (i==j/2);
}

//----26
int remRep (char x[]){
	int i, e=0;
    for(i=0; x[i]!='\0';i++) 
        if (x[i]!=x[i+1]) { 
            x[e]=x[i];
            e++; 
        }
    x[e]='\0';
    return e;
}

//----27
int limpaEspacos (char t[]){
	int i, e=0;
    for(i=0; t[i]!='\0';i++) {
        if (!isspace(t[i]) || !isspace(t[i+1])) {
            t[e]=t[i];
            e++;
        }
    }
    t[e]='\0';
    return e;
}

//----28
void insere (int v[], int N, int x){
	int i;
    for(i=N-1; i>=0 && v[i]>x; i--){
        v[i+1]=v[i];
    }
    v[i+1]=x;
}

//----29
void merge (int r [], int a[], int b[], int na, int nb){
	int i = 0, j = 0, e = 0, v = na + nb;
    while(e < v){
        if(j == nb && i < na){
            r[e] = a[i];
            i++;
            e++;
            continue;
        }
        if(i == na && j < nb){
            r[e] = b[j];
            j++;
            e++;
            continue;
        }
        if(a[i] < b[j]){
            r[e] = a[i];
            e++;
            i++;
        }
        else{
            r[e] = b[j];
            j++;
            e++;
        }
    }
}

//----30
int crescente (int a[], int i, int j){
	for(i=0; i<j; i++)
		if(a[i]>a[i+1]) return 0;
	    return 1;
}

//----31
int retiraNeg (int v[], int N){
	int i, c=0;
    for(i=0; i<N; i++){
        if(v[i]>=0) {v[c]=v[i]; c++;}
    }
    return c;
}

//----32
int menosFreq (int v[], int N){
    int r, f, i=0, j=0, c=0, n=1;
    while(i < N){
        while(j<N){
            if(v[i] == v[j]) c++;
            j++;
        }
        if(n==1) f = c, r = v[i];
        if(c<f) f = c, r = v[i];
        j=0;
        c=0;
        i++;
        n=2;
    }
    return r;
}

//----33
int maisFreq (int v[], int N){
 
    int i,j;
    int count = 0;
    int maximo = 0;
    int resultado;

    for(i=0; i < N; i++){
        for(j=i; j < N && v[j]==v[i]; j++) count++;
           
        if (count > maximo){ maximo = count; 
                            resultado = v[i];}
                                
        count = 0;                       
    }
    return resultado;
}

//----34
int maxCresc (int v[], int N) {
    int i, c=1, m=1;
    for (i=0;i-1<N; i++) {
        if (v[i+1]<v[i]) {
            if(c>m) {m=c; c=1;}
        }
        else c++;
    }
    return m;
}

//----35
int elimRep (int v[], int N) {
    int i,j,e;
    for (i=1,e=1; i<N;i++){
        for (j=i-1; j>=0 && v[j]!=v[i]; j--);
            if (j<0) {
                v[e]=v[i];
                e++;
            }
        }
        return e;
}

//----36
int elimRepOrd (int v[], int n) {
    int i, e=0;
    for(i=0;i<n;i++){
        if(v[i]!=v[i+1]) {v[e]=v[i]; e++;}
    }
    return e;
}

//----37
int comunsOrd (int a[], int na, int b[], int nb){
    int i, j, c=0;
    for(i=0, j=0 ;i<na && j<nb; i++, j++){
        if(a[i]==b[j]) c++;
        else if(a[i]>b[j]) i--;
        else j--;
    }
    return c;
}

//----38
int comuns (int a[], int na, int b[], int nb){
    int i=0, j=0, c=0;
    
    while(i<na){
        while(j<nb && a[i] != b[j]) j++;
        if(a[i]==b[j])c++;
        j = 0;
        i++;
    }
    return c;
}

//----39
int minInd (int v[], int n) {
    int i, min=v[0], imin=0;
    for(i=1;i<n;i++) {
        if(v[i]<min) { min = v[i]; imin =i;}
    }
    return imin;
}

//----40
void somasAc (int v[], int Ac [], int N) { 
    int i, soma=0;
    for(i=0;i<N;i++) {
        soma+=v[i];
        Ac[i]=soma;
    }
}

//----41
int triSup (int N, float m [N][N]){
    int i=0, j=0, Bool=1;
    float r=0;
    while(i<N && Bool != 0){
        while(j<N && Bool != 0){
            r=m[i][j];
            if(i > j && r != 0) Bool=0;
            j++;
        }
        j=0;
        i++;
    }
    return Bool;
}

//----42
void transposta (int N, float m[N][N]) {
    int i, j; float aux[N][N];
    for(i=0;i<N;i++) {
        for(j=0;j<N;j++) {
            aux[i][j]=m[i][j];
        }
    }
    for(i=0;i<N;i++) {
        for(j=0;j<N;j++) {
            m[j][i]=aux[i][j];
        }
    }
}

//----43
void addTo (int N, int M, int a [N][M], int b[N][M]) {
    int i, j;
    for(i=0; i<N;i++) {
        for(j=0;j<M;j++) 
            a[i][j]+=b[i][j];
    }
}

//----44
void sumDiag(int N, int m [N][N]){
    int i, j, soma;
    for(i=0;i<N;i++) {
        soma=0;
        for(j=0;j<N;j++) {
            if(i!=j) soma+=m[i][j];
        }
        m[i][i]=soma;
    }
}

//----45
int questao45() {
    char c='A';
    while (c<='Z' || c<='z') {
        printf ("%c - %d \n", c,c);
        if (c=='Z') c='a';
        else c++;
    }
    return 1;
}

//----46
int unionSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]==1 || v2[i]==1) r[i]=1;
        else r[i]=0;
        i++;
    }
    return 1;
}

//----47
int intersectSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]==1 && v2[i]==1) r[i]=1;
        else r[i]=0;
        i++;
    }
    return 1;
}

//----48
int intersectMSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]!=0 && v2[i]!=0) r[i]=(v1[i]<v2[i] ? v1[i]: v2[i]);
        else r[i]=0;
        i++;
    }
    return 1;
}

//----49
int unionMSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]!=0 || v2[i]!=0) r[i]=(v1[i]>v2[i] ? v1[i]: v2[i]);
        else r[i]=0;
        i++;
    }
    return 1;
}

//----50
int cardinalMSet (int N, int v[N]){
    int i=0, s=0;
    while(i<N){
        s+=v[i];
        i++;
    }
    return s;
}















//------------------2as 50 QUESTOES

typedef struct lligada {
    int valor;
    struct lligada *prox;
} *LInt;

//----1
int length (LInt l)
{
    int conta=0;
    if(l==NULL) conta=0;
    else
    {
        conta++;
        while(l->prox!=NULL)
        {
            conta++;
            l=l->prox;
        }
    }
    return conta;
}

//----2
void freeL (LInt l)
{
    LInt pt;
    while (l!=NULL)
    {
        pt = l;
        l = l->prox;
        free(pt);
    }
}

//----3
void imprimeL (LInt l)
{
    while (l!=NULL)
    {
        printf("%d\n", l->valor);
        l = l->prox;
    }
}

//----4
LInt reverseL (LInt l){
    LInt r,tmp;
    r=NULL;
    while(l!=NULL)
    {
        tmp=l;
        l=l->prox;
        tmp->prox=r;
        r=tmp;
    }
    return r;
}

//----5
void insertOrd (LInt *l, int x){
    while(((*l)!=NULL)&&((*l)->valor<x))
        l=&((*l)->prox);
    *l=newLInt(x,*l);
}

//----6
int removeOneOrd (LInt *l, int x)
{
    LInt* pt = &((*l)->prox);
    while(((*l)!=NULL)&&((*l)->valor<x)){
        l=&((*l)->prox);
        pt=&((*pt)->prox);
    }
    if((*l)==NULL) return 1;
    if((*l)->valor==x) {*l=*pt;return 0;}
}

//----7
void merge (LInt *r, LInt a, LInt b){
    *r=NULL;
    while(a!=NULL && b!= NULL){
        if(a->valor<=b->valor){
            (*r)=newLInt(a->valor,*r);
            a=a->prox;
        }
        else{
        (*r)=newLInt(b->valor,*r);
            b=b->prox;  
        }
        r=&((*r)->prox);
    }
    if(a==NULL){
        *r=b;
    }
    else *r=a;
}

//----8
void splitMS (LInt l, int x, LInt *mx, LInt *Mx)
{
    while (l != NULL){
        if (l->valor<x) {
            *mx = l;
            mx = &((*mx)->prox);
        }
        else {
            *Mx = l; 
            Mx = &((*Mx)->prox);
        }
        l = l->prox;
    }
    *mx = NULL;
}

//----9
LInt parteAmeio (LInt *l){
    LInt aux1;
    LInt aux2;
    int x;
    aux1=*l;
    aux2=*l;
    int a=0;
    if(*l==NULL || (*l)->prox==NULL) return NULL;
    x= length(*l);
    while(a<(x/2)){
        aux1= *l;
        *l=((*l)->prox);
        a++;
    }
    aux1->prox = NULL;
    return aux2;
}

//----10
int removeAll (LInt *l, int x){
    int a=0;
    while(*l!=NULL){
        if((*l)->valor==x){
            *l=(*l)->prox;
            a++;
        }
        else l= &((*l)->prox);
    }
    return a;
}

//----11
int removeDups (LInt *l){
    int conta=0;
    while(*l != NULL){
        conta+=removeAll(&((*l)->prox),(*l)->valor);
        l = &((*l)->prox);
    }
    return conta;
}

//----12
int removeMaiorL (LInt *l){
    int res=0;
    LInt tmp=*l;
    while(tmp!=NULL){
        if(tmp->valor>res) res = tmp->valor;
        tmp = tmp->prox;
    }
    while((*l)->valor!=res){
        l = &((*l)->prox);
    }
    tmp = *l;
    *l = (*l)->prox;
    free(tmp);
    return res;
}

//----13
void init (LInt *l){
    while ((*l)->prox != NULL){
        l = &((*l)->prox);
    }
    free(*l);
    *l = NULL;
}

//----14
void appendL (LInt *l, int x){
    while(*l!=NULL){
        l = &((*l)->prox);
    }
    *l = (LInt) malloc(sizeof(struct lligada));
    (*l)->valor = x;
    (*l)->prox = NULL;
}

//----15
void concatL (LInt *a, LInt b){
    while(*a!=NULL){
        a=&((*a)->prox);
    }
    *a=b;
}

//----16
LInt cloneL (LInt l){
    LInt a;
    if(l==NULL) a=NULL;
    else{
        a=(LInt)malloc(sizeof(struct lligada));
        a->valor=l->valor;
        a->prox=cloneL(l->prox);
    }
    return a;
}

//----17 
LInt cloneRev (LInt l){
    LInt a = NULL;
    while(l != NULL){
        a = newLInt(l->valor,a);
        l = l->prox;
    }
    return a;
}

//----18
int maximo (LInt l){
    int max=0;
    while(l!=NULL){
        if(l->valor>max) max=l->valor;
        l=l->prox;
    }
    return max;
}

//----19
int take (int n, LInt *l){
    int conta=0;
    while(*l!=NULL && conta<n){
        l = &((*l)->prox);
        conta++;
    }
    LInt *tmp;
    while(*l!=NULL){
        tmp = l;
        l = &((*l)->prox);
        free(*tmp);
        *tmp=NULL;
    }
    return conta;
}

//----20
int drop (int n, LInt *l){
    int conta=0;
    while(*l!=NULL && conta<n)
    {
        free(*l);
        *l = (*l)->prox;
        conta++;
    }
    return conta;
}

//----21
LInt NForward (LInt l, int N){
    while(l!=NULL && N>0)
    {
        l=l->prox;
        N--;
    }
    return l;
}

//----22
int listToArray (LInt l, int v[], int N){
    int tamanho=0;
    while(l!=NULL && tamanho<N)
    {
        v[tamanho++]=l->valor;
        l=l->prox;
    }
    return tamanho;
}

//----23
LInt arrayToList (int v[], int N){
    LInt res = NULL;
    N--;
    while (N >=0){
        res = newLInt(v[N],res);
        N--;
    }
    return res;
}

//----24
LInt somasAcL (LInt l){
    int a=0;
    LInt res = NULL;
    LInt *aux = &res;
    while(l != NULL){
        a += l->valor;
        l = l->prox;
        *aux = newLInt(a,NULL);
        aux = &(*aux)->prox;
    }
    return res;
}

//----25
void remreps (LInt l){
    LInt tmp,pt;
    while(l!=NULL){
        tmp=l->prox;
        if(tmp!=NULL && tmp->valor==l->valor){
            pt=tmp;
            tmp=tmp->prox;
            l->prox=tmp;
            free(pt);
        }
        else{
            l=l->prox;
        }
    }
}

//----26
LInt rotateL (LInt l){
    LInt pt;
    if(l==NULL) return l;
    else if(l->prox==NULL) return l;
    else{
        for(pt=l;pt->prox!=NULL;pt=pt->prox);
        pt->prox=l;
        pt=l;
        l=l->prox;
        pt->prox=NULL;
        return l;
    }
}

//----27
LInt parte (LInt l){
    LInt res=NULL, *n, *e;
    n = &res;
    e = &l;
    int pos=0;
    while(*e != NULL){
        if (pos%2!=0){
            *n = *e;
            n = &((*n)->prox);
            *e = (*e)->prox;
        }
        else{
            e = &((*e)->prox);
        }
        pos++;
    }
    *n = NULL;
    return res;
}

//ARVORES
typedef struct nodo {
    int valor;
    struct nodo *esq, *dir;
} *ABin;
//----28
int altura (ABin a){
    int conta=0;
    if(a==NULL) conta+=0;
    else{
        conta++;
        if(a->dir!=NULL && a->esq!=NULL) conta+=altura(a->dir);
        if(a->dir!=NULL && a->esq==NULL) conta+=altura(a->dir);
        if(a->dir==NULL && a->esq!=NULL) conta+=altura(a->esq);
    }
    return conta;
}

//----29
ABin cloneAB (ABin a) {
    ABin res;
    if(a==NULL) res=NULL;
    else{
        res=(ABin)malloc(sizeof(struct nodo));
        res->valor=a->valor;
        res->dir=cloneAB(a->dir);
        res->esq=cloneAB(a->esq);
    }
    return res;
}

//----30
void mirror (ABin *a) 
{
    if(*a!=NULL){
        ABin tmp;
        tmp=(*a)->esq;
        (*a)->esq=(*a)->dir;
        (*a)->dir=tmp;
        ABin *pt;
        if((*a)->dir!=NULL) {
            pt=&((*a)->dir);
            mirror(pt);
        }
        if((*a)->esq!=NULL){
            pt=&((*a)->esq);
            mirror(pt);
        }
    }
}

//----31
LInt inorderAux(ABin a, LInt r){
    if(a!=NULL){   
        r = inorderAux(a->dir,r);
        r = newLInt(a->valor,r);
        r = inorderAux(a->esq,r);
    }
    return r;
}
void inorder(ABin a, LInt *l){
    *l = inorderAux (a,NULL);
}

//----32
LInt preorderAux(ABin a, LInt r){
    if(a!=NULL){   
        r = preorderAux(a->dir,r);
        r = preorderAux(a->esq,r);
        r = newLInt(a->valor,r);
    }
    return r;
}
void preorder (ABin a, LInt * l){
    *l = preorderAux(a,NULL);
}

//----33
LInt posorderAux(ABin a, LInt r){
    if(a!=NULL){   
        r = newLInt(a->valor,r);
        r = posorderAux(a->dir,r);
        r = posorderAux(a->esq,r);
    }
    return r;
}
void posorder (ABin a, LInt * l){
    *l = posorderAux(a,NULL);
}

//----34
int minEsp(int x,int y){
    if (x<=0 && y<=0) return -1;
    else if (y>0 && (x>=y || x<=0)) return y;
    else return x;
}
int depth (ABin a, int x){
    int r=0;
    if(a!=NULL)
{
        if (a->valor == x) r=1;
        else r = 1 + minEsp(depth(a->esq,x),depth(a->dir,x));
    }
    if (r==0) return -1;
    else return r;
}

//----35
int freeAB (ABin a) {
    if (a==NULL) {
        return 0;
    }
    free(a);
    return(1+freeAB(a->esq)+freeAB(a->dir));
}

//----36
int pruneAB (ABin *a, int l){
    int nivel=l;
    int conta=0;
    if(*a==NULL) return 0;
    else if(l==0){
       conta+=freeAB(*a);
       *a = NULL;
    }
    else{
        conta+=pruneAB(&((*a)->esq),nivel-1);
        conta+=pruneAB(&((*a)->dir),l-1);
    }
    return conta;
}

//----37
int iguaisAB (ABin a, ABin b){
    int valido = 1;
    if(a==NULL && b==NULL) return 1;
    else if(a!=NULL && b==NULL) return 0;
    else if(a==NULL && b!=NULL) return 0;
    else if((a->valor==b->valor)&&(iguaisAB(a->dir,b->dir)==1 && iguaisAB(a->esq,b->esq)==1)) valido=1;
    else valido=0;
    return valido;
}

//----38
LInt nivelL (ABin a, int n){
    LInt r, e, d;
    if (a==NULL) r=NULL;
    else if (n==1) r = newLInt(a->valor,NULL);
        else{
            e = nivelL(a->esq,n-1);
            d = nivelL(a->dir,n-1);
            r = concat (e,d);
        }
    return r;
}
LInt concat (LInt a, LInt b){
    if (a==NULL) a=b;
    else {
        LInt aux = a; 
        while (aux->prox!=NULL) aux=aux->prox;
        aux->prox = b;
    }
    return a;  
}

//----39
int nivelV (ABin a, int n, int* v){
    int i=0;
    if(a!=NULL){
        if(n==1){
            v[i]=a->valor;
            i=1;
        }
        else{
            i+=nivelV(a->esq,n-1,&v[i]);
            i+=nivelV(a->dir,n-1,&v[i]);
        }
    }
    return i;
}

//----40
int dumpAbin (ABin a, int v[], int N){
    int i=0;
    if(a!=NULL){
        i+=dumpAbin(a->esq,&v[i],N);
        if(i<N){
            v[i]=a->valor;
            i++;
            i+=dumpAbin(a->dir,&v[i],N-i);
        }
    }
    return i;
}

//----41
ABin somasAcA (ABin a){
    ABin b;
    if (a == NULL) return NULL;
    b = (ABin) malloc (sizeof(struct nodo));
    b->esq = somasAcA(a->esq);
    b->dir = somasAcA(a->dir);
    b->valor = a->valor + intcabeca(b->esq) + intcabeca(b->dir);
    return b;
}
int intcabeca (ABin a){
    if (a == NULL) return 0;
    else return a->valor;
}

//----42
int contaFolhas (ABin a){
    int res=0;
    if(a==NULL) res+=0;
    else{
        if(a->dir==NULL && a->esq==NULL) res+=1;
        else{
            res+=contaFolhas(a->esq);
            res+=contaFolhas(a->dir);
        }
    }
    return res;
}

//----43
ABin cloneMirror (ABin a){
    ABin b=NULL;
    if(a!=NULL){
    b=newABin(a->valor,cloneMirror(a->dir),cloneMirror(a->esq));
    return b;}
}

//----44
int addOrd (ABin *a, int x){
    int r=0;
    while (*a!=NULL) {
        if ((*a)->valor == x) return 1;
        else if ((*a)->valor > x) a = &((*a)->esq);
            else a = &((*a)->dir);
    }
    *a = newABin(x,NULL,NULL);
    return r;
}

//----45
int lookupAB (ABin a, int x){
    int valido=0;
    if(a!=NULL){
        if(a->valor==x) valido=1;
        else if(lookupAB(a->dir,x) || lookupAB(a->esq,x)) valido=1;
    }
    return valido;
}

//----46
int depthOrd (ABin a, int x){
    int res=0;
    if(a!=NULL){
        if(a->valor==x) res+=1;
        else if(a->valor>x) res+=1+depthOrd(a->esq,x);
        else res+=1+depthOrd(a->dir,x);
    }
    if(res==0) res=-1;
    return res;
}

//----47
int maiorAB (ABin a){
    int res=-1;
    if(a!=NULL){
        while(a->dir!=NULL) a=a->dir;
        res=a->valor;
    }
    return res;
}

//----48
void removeMaiorA (ABin *a){
    if(*a==NULL)return ;
    while(((*a)->dir)!=NULL){
        a=&((*a)->dir);
    }
    (*a)=(*a)->esq;
}

//----49
int quantosMaiores (ABin a, int x){
    int res=0;
    if(a!=NULL){
        if(a->valor>x) res+=1;
        if(a->esq!=NULL) res+=quantosMaiores(a->esq,x);
        if(a->dir!=NULL) res+=quantosMaiores(a->dir,x);
    }
    return res;
}

//----50
void listToBTree (LInt l, ABin *a){
    *a=NULL;
    if(l==NULL){
        *a=NULL;
    }
    while (l!=NULL){
        *a=newABin(l->valor,NULL,NULL);
        a=&((*a)->dir);
        l=l->prox;
    }
}

//----51
int deProcura (ABin a){
    int r=0;
    if (a == NULL) r = 1;
    else if( menor(a->esq,a->valor) && maior(a->dir,a->valor) && deProcura(a->dir) && deProcura(a->esq)) r= 1;
    return r;
}
int maior(ABin a , int x){
    int r = 0;
    if(a == NULL) r = 1;
    else if(a->valor > x && maior(a->dir,x) && maior(a->esq,x)) r = 1; return r;
}
int menor(ABin a , int x){
    int r = 0;
    if(a == NULL) r = 1;
    else if(a->valor < x && menor(a->dir,x) && menor(a->esq,x)) r = 1; return r;
}






















