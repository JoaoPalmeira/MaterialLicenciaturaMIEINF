#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 1024


//-----------1

int soma () {
    int n, x=0;
    scanf ("%d", &n);
    while (n!=0){
        x+=n;
        scanf ("%d", &n);
    }
    printf("O resultado é: %d \n", x);
    return 0;
}

//-----------2

int maior () {
	int n, x=0;
	scanf("%d", &n);
	while (n!=0){
		if (x<n) x=n;
	}
	printf("O maior elemento é: %d", x);
    return 0;
}

//-----------3

int media () {
	int n; 
	float x=0, y=0;
    scanf ("%d", &n);
    while (n!=0){
        x+=n;
        scanf ("%d", &n);
        y++;
    }
    x=x/y;
    printf("A média é: %f \n", x);
    return 0;
}

//-----------4

int maior12 () {
	int n, maior1 = 0, maior2=0;
	printf("Insira os elementos: %d \n", n);
	while (n!=0){
		scanf("%n",&n);
		if (n>maior1){
			maior2 = maior1;
			n = maior1;
		}
		else if (n>maior2) n=maior2;
	}
	printf("O Segundo maior elemento é: %d \n", maior2);
	return 0;
}

//-----------5

int bitsUm (unsigned int n){
	int num1=0;
	while (n){
		if(n%2 == 1) num1++;
		n=n/2;
	}
	return num1;
}

//-----------6

int trailingZ (unsigned int n) {
	int num2=0;
	while(n && n%2==0){
		num2++;
		n=n/2;
	}
	return num2;

}

//-----------7

int qDig (unsigned int n){
	int num3 = 1;
	while(n>=10){
		num3++;
		n=n/10;
	}
	return num3;

}

//-----------8

int strlen2 (char str[]){
	int i;
	for(i=0; str[i]!='\0';i++);
	return i;
}

//-----------9

char *strcat2 (char s1[], char s2[]){
	int i, j;
	for(i=strlen (s1), j=0; s2[j]!='\0'; i++, j++){
		s1[i]=s2[j];
	}
	s1[i]='\0';
	return s1;
}

//-----------10

char *strcpy2 (char *dest, char source[]){
	int i;
	for(i=0; source[i]!='\0';i++){
		dest[i]=source[i];
	}
	dest[i]='\0';
	return dest;
}

//-----------11

int strcmp2 (char s1[], char s2[]){
	int i=0;
	while(s1[i]==s2[i] && s1[i]!='\0'){
		i++;
	}
	return (s1[i]-s2[i]);
}

//-----------12

char * strstrr (char s1[], char s2[]){
	int i,j;
	j = 0;
	for (i = 0; s1[i] != '\0' && s2[j] != '\0'; ++i){
		if (s1[i] == s2[j]) ++j;
		else j = 0;
	}
	if (s2[j] == '\0') return s1+ (i-j);
		else return NULL;
}

//-----------13

void strrev2 (char s[]){
	int i, j;
	char c;
	for(i=0; s[i]!='\0'; i++);
	for(j=0; j<i/2; j++){
		c=s[j];
		s[j]= s[i-1-j];
		s[i-1-j]=c;
	}
}

//-----------14

void strnoV2 (char s[]) { 
    int i, e=0;
    for (i=0; s[i]!='\0'; i++) {
        if (s[i]!='A' && s[i]!='a' && s[i]!='E' && s[i]!='e' && s[i]!='I' && s[i]!='i' && s[i]!='O' && s[i]!='o' && s[i]!='U' && s[i]!='u') {
            s[e]=s[i]; e++;
        }
    }
    s[e]='\0';
}

//-----------15

void truncW (char t[], int n){
	int i=0, j=0, c=n;
	while(t[i]!='\0'){
		if(t[i]==' '){
			t[j]=t[i];
			i++; j++; c=n;
		} 
		else {
			if(c!=0){
				t[j]=t[i];
				j++; i++; c--;
			}
			else i++;
		}
	}
	t[j]='\0';
}

//-----------16

char charMaisfreq (char s[]){
    int i, j, c, m=0; char f='\0';
    for (i=0; s[i]!='\0'; i++) {
        c=0;
        for (j=0; s[j]!='\0'; j++) {
            if (s[i]==s[j]) c++;
        }
        if (c>m) { m=c; f = s[i];}
    }
    return f;
}

//-----------17

int iguaisConsecutivos (char s[]) {
    int i,c=0,m=0;
    char Last='\0';
    for (i = 0; s[i]!='\0'; ++i){
    	if (s[i]==Last)
    		c++;
    	else{
    		Last=s[i];
    		if (c > m)
    			m=c;
    		c=1;
    	}

    }
    if (c > m) m=c;
    return m;
}

//-----------18

int difConsecutivos(char s[]) 
{
	int i,count=0,topc=0;
    char Last='\0';
    for (i = 0; s[i]!='\0'; ++i){
    	if (s[i]!=Last){
    			count ++;
    			Last=s[i];
    		}
    	else{
    		if (count > topc)
    			topc=count;
    		count=1;
    	}

    }
    if (count > topc)
    			topc=count;
	return topc;   
}

//-----------19

int maiorPrefixo (char s1[], char s2[]) {
	int i;
	for (i=0;s1[i]!='\0' && s2[i]!='\0' && s1[i]==s2[i];i++);
	return i;
}

//-----------20

int maiorSufixo (char s1[], char s2[]) { 
    int i, j;
    for(i=strlen(s1)-1, j=strlen(s2)-1; i>=0 && j>=0 && s1[i]==s2[j]; i--,j--);
    return (strlen(s1)-i-1);
}

//-----------21

int sufPref (char s1[], char s2[]) {
    int i , j;
    
    for (i=0; s1[i]!='\0'; i++) {
        for(j=0; s2[j] == s1[i] && s1[i]!='\0'; j++,i++);
        if (s1[i] == '\0') return j;
    }

    return 0;
}

//-----------22

int contaPal (char s[]) {
    int i,count=0;
    char last='\0';
    for (i=0;s[i]!='\0';i++){
    	if (s[i]!=' ' && (last ==' ' || last =='\0')){
    		count ++;}
    	last =s[i];
    }
    return count;
}

//-----------23

int contaVogais (char s[]) {
    int i, c=0;
    for (i=0; s[i]!='\0'; i++) {
        if (s[i]=='A' || s[i]=='a' || s[i]=='E' || s[i]=='e' || s[i]=='I' || s[i]=='i' || s[i]=='O' || s[i]=='o' || s[i]=='U' || s[i]=='u') c++;
    }
    return c;
}

//-----------24

int contida (char a[], char b[]) {
    int i1,i2,count=0,result=1;
    for (i1=0; a[i1]!='\0';i1++){
        for (i2=0;b[i2]!='\0';i2++){
            if (a[i1]==b[i2]){
                count++;
                break;}
        }
    }
    if (count == i1)
        result =1;
    else
        result =0;
    return result;
}

//ou

int contida (char a[], char b[]) {
    int i, j;
    for (i=0; a[i]!='\0';i++) {
        for(j=0;b[j]!='\0' && a[i]!=b[j];j++);
        if(b[j]=='\0') { return 0; break; }
    }
    return 1;
}

//-----------25

int palindorome (char s[]) {
    int i, j=strlen(s);
    for(i=0; i!=j/2 && s[i]==s[j-i-1]; i++);
    return (i==j/2);
}

//-----------26

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

//-----------27

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

//-----------28

void insere (int v[], int N, int x){
    int i;
    for(i=N-1; i>=0 && v[i]>x; i--){
        v[i+1]=v[i];
    }
    v[i+1]=x;
}

//-----------29

void merge (int r [], int a[], int b[], int na, int nb){
	int i=0,ia=0,ib=0;
	while (ia!=na && ib!=nb)
	{
		if (a[ia]<=b[ib])
		{
			r[i]=a[ia];
			ia++;
		}
		else
		{
			r[i]=b[ib];
			ib++;
		}
		i++;
	}
	if (ia==na)
	{
		while (ib!=nb)
		{
			r[i]=b[ib];
			i++;
			ib++;
		}
	}
	else
	{
		while (ia!=na)
		{
			r[i]=a[ia];
			i++;
			ia++;
		}
	}
  }

//-----------30

int crescente (int a[], int i, int j){
    for(i=0;i<j;i++)
        if(a[i]>a[i+1]) return 0;
        return 1;
}

//-----------31

int retiraNeg (int v[], int N){
    int i, c=0;
    for(i=0; i<N; i++){
        if(v[i]>=0) {v[c]=v[i]; c++;}
    }
    return c;
}

//-----------32

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


//-----------33

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

//-----------34

int maxCresc (int v[], int N) {
    int i,big=1,r = 1,last=v[0];
    for (i = 1; i <= N; ++i)
    {
        if (v[i]>=last)
        {
            r++;
            last =v[i];
        }
        else if (r>big)
        {
            big=r;
            r=1;
            last=v[i];
        }
        else
        {
            r=1;
            last = v[i];
        }
    }
    return big;
}

//-----------35

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

//-----------36

int elimRepOrd (int v[], int N) {
    int r=N,l=0,e=0;
    while (l <= N-1)
    {
        if (v[l]==v[l+1] && r !=1)
        {
            r--;
            l++;
        }
        else
        {
            v[e]=v[l];
            l++;
            e++;
        }
    }
    return r;
}

//-----------37

int comunsOrd (int a[], int na, int b[], int nb){
    int i, j, c=0;
    for(i=0, j=0 ;i<na && j<nb; i++, j++){
        if(a[i]==b[j]) c++;
        else if(a[i]>b[j]) i--;
        else j--;
    }
    return c;
}

//-----------38

int comuns (int a[], int na, int b[], int nb){
    int r=0,i1,i2,leave;
    for (i1=0;i1<na;i1++)
    {
        leave=0;
        for(i2=0;i2<nb && leave == 0;i2++)
        {
            if (a[i1]==b[i2])
            {
                r++;
                leave =1;
            }
        }
    }
    return r;
}

//-----------39

int minInd (int v[], int n) {
   int r=0,min=v[0],i;
   for (i=1;i<n;i++)
   {
        if (v[i]<min)
        {
            min = v[i];
            r=i;
        }
   }
   return r;
}

//-----------40

void somasAc (int v[], int Ac [], int N) { 
    int i, soma=0;
    for(i=0;i<N;i++) {
        soma+=v[i];
        Ac[i]=soma;
    }
}

//-----------41

int triSup (int N, float m [N][N]) {
    int l,c,zeros=1,sup=1;
    for (l = 1; l < N && sup==1 ; ++l)
    {
        for (c = 0; c < N && c < zeros && sup==1; ++c)
        {
            if (m[l][c]!=0)
                sup =0;
        }
        zeros++;
    }
    return sup;
}

//-----------42

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

//-----------43

void addTo (int N, int M, int a [N][M], int b[N][M]) {
    int i, j;
    for(i=0; i<N;i++) {
        for(j=0;j<M;j++) 
            a[i][j]+=b[i][j];
    }
}

//-----------44

void sumDiag(int N, int m [N][N]){
    int l,c,soma=0;
    for (l = 0; l < N; ++l){
        for (c = 0; c < N; ++c){
            soma=soma + m[l][c];
        }
        m[l][l]=soma -m[l][l];
        soma=0;
    }
    return;
}

//-----------45

int questao45() {
    char c='A';
    while (c<='Z' || c<='z') {
        printf ("%c - %d \n", c,c);
        if (c=='Z') c='a';
        else c++;
    }
    return 1;
}

//-----------46

int unionSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]==1 || v2[i]==1) r[i]=1;
        else r[i]=0;
        i++;
    }
    return 1;
}

//-----------47

int intersectSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]==1 && v2[i]==1) r[i]=1;
        else r[i]=0;
        i++;
    }
    return 1;
}

//-----------48

int intersectMSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]!=0 && v2[i]!=0) r[i]=(v1[i]<v2[i] ? v1[i]: v2[i]);
        else r[i]=0;
        i++;
    }
    return 1;
}

//-----------49

int unionMSet (int N, int v1[N], int v2[N], int r[N]){
    int i=0;
    while(i<N){
        if(v1[i]!=0 || v2[i]!=0) r[i]=(v1[i]>v2[i] ? v1[i]: v2[i]);
        else r[i]=0;
        i++;
    }
    return 1;
}

//-----------50

int cardinalMSet (int N, int v[N]){
    int i=0, s=0;
    while(i<N){
        s+=v[i];
        i++;
    }
    return s;
}

