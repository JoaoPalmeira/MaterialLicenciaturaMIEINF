%{
#include <stdio.h>
#include <math.h>

void yyerror(char*);
int yylex();
double vals[26];
%}
%token NUM F1 VAR
%union { double d;
         double (*f) (double);
         char c; }
%type<d> NUM exp p es fat
%type<f> F1
%type<c> VAR
%%
calc    : calc exp '\n'          { printf("%f\n", $2); }
        | calc VAR '=' exp '\n'  { vals[$2 - 'a'] = $4; }
        | calc error '\n'        { }
        |
        ;
exp     : exp '+' p        { $$ = $1 + $3; }
        | exp '-' p        { $$ = $1 - $3; }
        | p                { $$ = $1; }
        ;
p       : p '*' fat        { $$ = $1 * $3; }
        | p '/' fat        { $$ = $1 / $3; }
        | fat              { $$ = $1; }
        ;
fat     : es '^' fat       { $$ = pow($1, $3); }
        | es               { $$ = $1; }
        ;
es      : '(' exp ')'      { $$ = $2; }
        | NUM              { $$ = $1; }
        | '-' es           { $$ = - $2; }
        | F1 '(' exp ')'   { $$ = (*$1) ($3); }
        | VAR              { $$ = vals[$1 - 'a']; }
        ;
%%
#include "lex.yy.c"


void yyerror(char *s) {
  fprintf(stderr, "linha %d: [%s] - %s\n", yylineno, yytext, s);
}


int main(int argc, char *args[]) {
  yyparse();
  return 0;
}
