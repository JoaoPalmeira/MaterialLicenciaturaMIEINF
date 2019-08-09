%{
// Imprimir a profundidade maxima
#include <stdio.h>

void yyerror(char* );
int yylex();
%}
%token NUM ID
%union { char* s; int i; }
%type <s> ID
%type <i> NUM
%type <i> sexp
%type <i> lista
%%
lisp: sexp { printf("%d\n", $1); }

sexp: '(' lista ')' { $$ = $2 + 1; }
    | NUM           { $$ = 0; }
    | ID            { $$ = 0; }
    ;

lista:              { $$ = 0; }
     | sexp lista   { $$ = ($1 > $2)? $1 : $2; }
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
