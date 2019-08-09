%{
#include <stdio.h>

void yyerror(char* );
int yylex();
%}
%token NOME NUM
%union { char* s; int i; struct {double len, sum; } p; }
%type <s> NOME
%type <i> NUM
%type <p> notas
%%
turma: cla
     | cla turma
     ;
cla: NOME notas '.' { printf("%s -> %.2f!\n", $1, $2.sum / $2.len); }
   ;
notas: NUM { $$.sum = $1; $$.len = 1; }
     | NUM ',' notas { $$.sum = $1 + $3.sum; $$.len = 1 + $3.len; }
     ;
%%
#include "lex.yy.c"

void yyerror (char *s) {
    fprintf(stderr, "%d: %s\n", yylineno, s);
}

int main(int argc, char* argv[]) {
    yyparse();

    return 0;
}
