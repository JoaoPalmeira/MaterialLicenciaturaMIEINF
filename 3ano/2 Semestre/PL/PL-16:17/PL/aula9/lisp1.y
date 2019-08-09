%{
// a) Imprimir a profundidade da sexp
#include <stdio.h>

void yyerror(char* );
int yylex();

int dp = 0;
%}
%token NUM ID
%union { char* s; int i; }
%type <s> ID
%type <i> NUM
%%
sexp: '(' { dp++; } lista ')' { dp--; }
    | NUM { printf("%d:%d\n", $1 ,dp); }
    | ID
    ;

lista:
     | sexp lista
     ;
%%
#include "lex.yy.c"

void yyerror (char *s) {
    fprintf(stderr, "%d: %s\n\t%s\n", yylineno, yytext, s);
}

int main(int argc, char* argv[]) {
    yyparse();

    return 0;
}
