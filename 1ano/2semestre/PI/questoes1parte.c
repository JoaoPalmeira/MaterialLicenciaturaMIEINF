#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 1024

//---------------------------------------1
int main () {
    int n, x=0;
    scanf ("%d", &n);
    while (n!=0){
        x+=n;
        scanf ("%d", &n);
    }
    printf("O resultado é: %d", x);
    return 0;
}

//alternativa

int main () {
        int n;
        int soma = 0;
    do {scanf("%d", &n);
        soma = soma + n;
    }    
    while (n != 0);
        
    printf("%d\n", soma);                       
    
    return 0;
}


//-----------------------------------------2
int main () {
    int n, x=0;
    scanf("%d", &n);
    while (n!=0){
        scanf("%d",&n);
        if(n>x) x=n;
    }
    printf("O maior elemento é: %d", x);
    return 0;
}


//------------------------------------------3
int main (){
    int n;
    float x=0, y=0;
    scanf("%d", &n);
    while (n!=0){
        x+=n;
        scanf("%d", &n);
        y++;
    }
    x=x/y;
    printf("A média é: %f", x);
    return 0;
}


//------------------------------------------4
int main () {
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


//--------------------------------------------5
int bitsUm (unsigned int n) {
    int count = 0;
    while (n) {
        if (n%2 == 1) count ++;
        n=n/2;
    }
    return count;
}


//-------------------------------------------6
int trailingZ (unsigned int n) {
    int count = 0;
    while (n && n%2==0) {
        count++;
        n=n/2;
    }
    return count;
}


//--------------------------------------------7
int qDig (unsigned int n) {
    int count = 1;
    while (n>=10) {
        count ++;
        n=n/10;
    }
    return count;
}


//---------------------------------------------8
int strlen2 (char str[]) { 
    int i;
    for(i=0;str[i]!='\0'; i++);
    return i;
}

//---------------------------------------------9
char *strcat2 (char s1[], char s2[]) {
    int i, j;
    for (i=strlen (s1), j=0; s2[j]!='\0'; i++,j++) {
        s1[i] = s2[j];
    }
    s1[i]='\0' ;
    return s1;
}


//--------------------------------------------10
char *strcpy2 (char *dest, char source[]) { 
    int i;
    for(i=0;source[i]!='\0';i++) {
        dest[i] = source[i];
    }
    dest[i]='\0';
    return dest;
}


//---------------------------------------------11
int strcmp2 (char s1[], char s2[]) {
    int i=0;
    while (s1[i]==s2[i] && s1[i]!='\0')
        i++;
    return (s1[i] - s2[i]);
}


//-----------------------------------------------12
char *strstr2 (char s1[], char s2[]) {
    int i, j;
    if(s1[0]=='\0' && s2[0]=='\0') return s1;
    for (i=0; s1[i]!='\0'; i++) {
        int s=0; j=i;
        while (s1[j]==s2[s] && s1[j]!='\0') {
            s++; j++;
        }
        if (s2[s]=='\0') {
            return &s1[i]; break;}
    }
    return NULL;
}

//--------------------------------------------------13
void strrev2 (char s[]) {
    int i, len; char c;
    for (len =0; s[len]!='\0';len++);
    for (i=0; i<len/2; i++) {
        c=s[i];
        s[i]=s[len-1-i];
        s[len-1-i]=c;
    }
}


//---------------------------------------------------14
void strnoV2 (char s[]) { //10 em 10
    int i, e=0;
    for (i=0; s[i]!='\0'; i++) {
        if (s[i]!='A' && s[i]!='a' && s[i]!='E' && s[i]!='e' && s[i]!='I' && s[i]!='i' && s[i]!='O' && s[i]!='o' && s[i]!='U' && s[i]!='u') {
            s[e]=s[i]; e++;
        }
    }
    s[e]='\0';
}


//----------------------------------------------------15
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


//------------------------------------------------------16
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


//-------------------------------------------------------17
int iguaisConsecutivos (char s[]) {
    int i, c=1, m=0;
    for (i=0; s[i+1]!='\0'; i++) {
        if (s[i]!=s[i+1]) c=1;
        if (s[i]== s[i+1]) c++;
        if (c>m) m=c;
    }
    return m;
}


//--------------------------------------------------------18
int pertence1 (char s[], char x, int j) {
    int i;
    for(i=0;i<j&&s[i]!=x;i++);
    if(i<j && s[i]==x) return 1;
    return 0;
}

int difConsecutivos (char s[]) {
    int i, j=0, k=1, m=0;
    
    for(i=0;s[i]!='\0';i++);
    
    char aux[i]; i=0;
    
    if(s[0]=='\0') return 0;
    else if(s[1]=='\0') return 1;
    else while(s[i]!='\0') {
            if(i==0){
                aux[0]=s[i];j++; i++; continue;
            }
            else if ((s[i]!=s[i-1] || s[i]!=s[i+1])) {
                    if(pertence1(aux, s[i], j)){
                        if (j>m) {
                            m=j; 
                        }
                        i=k;j=0; k++;
                    }
                    else {
                        aux[j]=s[i];
                        j++; i++;
                    }
                }
                else{
                    if (j>m) m=j; 
                    j=0; i=k; k++;
                }
            }
    return m;
}

//-------------------------------------------------19
int maiorPrefixo (char s1[], char s2[]) {
 int i;
 for (i=0;s1[i]!='\0' && s2[i]!='\0' && s1[i]==s2[i];i++);
 return i;
}


//-------------------------------------------------20
int maiorSufixo (char s1[], char s2[]) { 
    int i, j;
    for(i=strlen(s1)-1, j=strlen(s2)-1; i>=0 && j>=0 && s1[i]==s2[j]; i--,j--);
    return (strlen(s1)-i-1);
}


//-------------------------------------------------21
int sufPref (char s1[], char s2[]) { 
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


//-------------------------------------------------22
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


//-------------------------------------------------23
int contaVogais (char s[]) {
    int i, c=0;
    for (i=0; s[i]!='\0'; i++) {
        if (s[i]=='A' || s[i]=='a' || s[i]=='E' || s[i]=='e' || s[i]=='I' || s[i]=='i' || s[i]=='O' || s[i]=='o' || s[i]=='U' || s[i]=='u') c++;
    }
    return c;
}


//-------------------------------------------------24
int contida (char a[], char b[]) {
    int i, j;
    for (i=0; a[i]!='\0';i++) {
        for(j=0;b[j]!='\0' && a[i]!=b[j];j++);
        if(b[j]=='\0') { return 0; break; }
    }
    return 1;
}


//-------------------------------------------------25
int palindorome (char s[]) {
    int i, j=strlen(s);
    for(i=0; i!=j/2 && s[i]==s[j-i-1]; i++);
    return (i==j/2);
} 


//-------------------------------------------------26
int remRep (char x[]) {
    int i, e=0;
    for(i=0; x[i]!='\0';i++) 
        if (x[i]!=x[i+1]) { 
            x[e]=x[i];
            e++; 
        }
    x[e]='\0';
    return e;
}


//-------------------------------------------------27
int limpaEspacos (char t[]) {
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


//----------------------------------------------------28
void insere (int v[], int N, int x){
    int i;
    for(i=N-1; i>=0 && v[i]>x; i--){
        v[i+1]=v[i];
    }
    v[i+1]=x;
}


//----------------------------------------------------29
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


//----------------------------------------------------30
int crescente (int a[], int i, int j){
    for(i=0;i<j;i++)
        if(a[i]>a[i+1]) return 0;
        return 1;
}


//-----------------------------------------------------31
int retiraNeg (int v[], int N){
    int i, c=0;
    for(i=0; i<N; i++){
        if(v[i]>=0) {v[c]=v[i]; c++;}
    }
    return c;
}


//-----------------------------------------------------32
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


//-----------------------------------------------------33
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


//-----------------------------------------------------34
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


//----------------------------------------------------35
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


//---------------------------------------------------36
int elimRepOrd (int v[], int n) {
    int i, e=0;
    for(i=0;i<n;i++){
        if(v[i]!=v[i+1]) {v[e]=v[i]; e++;}
    }
    return e;
}


//---------------------------------------------------37
int comunsOrd (int a[], int na, int b[], int nb){
    int i, j, c=0;
    for(i=0, j=0 ;i<na && j<nb; i++, j++){
        if(a[i]==b[j]) c++;
        else if(a[i]>b[j]) i--;
        else j--;
    }
    return c;
}


//---------------------------------------------------38
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


//---------------------------------------------------39
int minInd (int v[], int n) {
    int i, min=v[0], imin=0;
    for(i=1;i<n;i++) {
        if(v[i]<min) { min = v[i]; imin =i;}
    }
    return imin;
}


//--------------------------------------------------40
void somasAc (int v[], int Ac [], int N) { 
    int i, soma=0;
    for(i=0;i<N;i++) {
        soma+=v[i];
        Ac[i]=soma;
    }
}


//--------------------------------------------------41
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


//--------------------------------------------------42
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



//---------------------------------------------------43
void addTo (int N, int M, int a [N][M], int b[N][M]) {
    int i, j;
    for(i=0; i<N;i++) {
        for(j=0;j<M;j++) 
            a[i][j]+=b[i][j];
    }
}


//---------------------------------------------------44
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


//---------------------------------------------------45
int questao45() {
    char c='A';
    while (c<='Z' || c<='z') {
        printf ("%c - %d \n", c,c);
        if (c=='Z') c='a';
        else c++;
    }
    return 1;
}


//---------------------------------------------------46
int unionSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]==1 || v2[i]==1) r[i]=1;
        else r[i]=0;
        i++;
    }
    return 1;
}

//---------------------------------------------------47
int intersectSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]==1 && v2[i]==1) r[i]=1;
        else r[i]=0;
        i++;
    }
    return 1;
}

//---------------------------------------------------48
int intersectMSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]!=0 && v2[i]!=0) r[i]=(v1[i]<v2[i] ? v1[i]: v2[i]);
        else r[i]=0;
        i++;
    }
    return 1;
}

//---------------------------------------------------49
int unionMSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]!=0 || v2[i]!=0) r[i]=(v1[i]>v2[i] ? v1[i]: v2[i]);
        else r[i]=0;
        i++;
    }
    return 1;
}


//---------------------------------------------------50
int cardinalMSet (int N, int v[N]){
    int i=0, s=0;
    while(i<N){
        s+=v[i];
        i++;
    }
    return s;
}



























