%{
// Calcular o valor de uma expressão matemática
#include <stdio.h>
#include "aux.h"

void yyerror(char* );
int yylex();
void trim (char *s);
%}
%token NUM ID
%union { char* s; int i; LISTAL l; }
%type <s> ID
%type <i> NUM
%type <i> sexp
%type <l> lista
%%
lisp: sexp { printf("= %d\n", $1); }

sexp: '(' ID lista ')' { $$ = calc($2, $3); }
    | NUM              { $$ = $1; }
//    | ID               { $$ = $1; }
    ;

//lista de argumentos
lista:            { $$ = new_listal(); }
     | sexp lista { $$ = cons($1, $2); }
     ;
%%
#include "lex.yy.c"

void trim (char *s) {
    s[strlen(s)-2] = '\0';
}

void yyerror (char *s) {
    fprintf(stderr, "%d: %s\n\t%s\n", yylineno, yytext, s);
}

int main(int argc, char* argv[]) {
    yyparse();

    return 0;
}
