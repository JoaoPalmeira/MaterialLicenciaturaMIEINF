%{
// Converter a notação LISP para uma notação mais convencional
#define _GNU_SOURCE
#include <stdio.h>

void yyerror(char* );
int yylex();
void trim (char *s);
%}
%token NUM ID
%union { char* s; int i; }
%type <s> ID
%type <i> NUM
%type <s> sexp
%type <s> lista
%%
lisp: sexp { printf("%s\n", $1); }

sexp: '(' ID lista ')' { trim($3); asprintf(&$$, "%s(%s)", $2, $3); }
    | NUM              { asprintf(&$$, "%d", $1); }
    | ID               { asprintf(&$$, "%s", $1); }
    ;

//lista de argumentos
lista:            { asprintf(&$$, ""); }
     | sexp lista { asprintf(&$$, "%s, %s", $1, $2); }
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
