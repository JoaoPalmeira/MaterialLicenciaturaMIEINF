%{
#include <stdio.h>
#include <string.h>
#define MAX 1024
int nivel=0;
typedef struct lista{
    char cabeca[MAX];
    char cauda[MAX];
}lista;
%}

%union {char *valStr; lista valList; char valString[MAX];}
%token <valStr>pal
%token <valStr>num
%type <valList>SExpList
%type <valString>SExp
%%
Lisp     : SExp              { printf("%s\n",$1);}       
         | Lisp SExp         { printf("%s\n",$2);}   
         ; 

SExp     : pal               { sprintf($$,"%s",$1);}
         | num               { sprintf($$,"%s",$1);}
         | '(' SExpList ')'  { sprintf($$,"(%s %s)",$2.cauda,$2.cabeca);}
         ;

SExpList : SExp              { strcpy($$.cabeca,$1); strcpy($$.cauda,"");}
         | SExpList SExp     { strcpy($$.cabeca,$1.cabeca);
                               sprintf($$.cauda,"%s %s",$1.cauda,$2); }
         ;
%%

#include "lex.yy.c"

int yyerror(char *s){
    printf("erro: %s\n",s);
}

int main(){
    yyparse();
    return 0;
}