%{
#include <stdio.h>
int nivel=0;
%}

%union {char *valStr;}
%token <valStr>pal
%token <valStr>num
%%
Lisp     : SExp                         {printf("\n");}
         | Lisp SExp                    {printf("\n");}
         ; 

SExp     : pal                          { printf("%s[%d] ",$1,nivel);}
         | num                          { printf("%s[%d] ",$1,nivel);}
         | '(' { printf("(");nivel++;} SExpList ')' { printf(")");nivel--;}
         ;

SExpList : SExp
         | SExpList SExp
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