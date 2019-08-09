%{
#include <stdio.h>
#include <math.h>

void yyerror(char*);
int yylex();
double vals[26];
double last = 0;
%}
%token NUM F1 VAR
%union { double d;
         double (*f) (double);
         char c; }
%type<d> NUM exp
%type<f> F1
%type<c> VAR
%left '+' '-'
%left '*' '/'
%right '^'
%%
calc    : calc exp '\n'          { printf("%f\n", $2);
                                   last = $2; }
        | calc VAR '=' exp '\n'  { vals[$2 - 'a'] = $4; }
        | calc error '\n'        { }
        |
        ;
exp     : exp '+' exp      { $$ = $1 + $3; }
        | exp '-' exp      { $$ = $1 - $3; }
        | exp '*' exp      { $$ = $1 * $3; }
        | exp '/' exp      { $$ = $1 / $3; }
        | exp '^' exp      { $$ = pow($1, $3); }
        | F1 '(' exp ')'   { $$ = (*$1) ($3); }
        | '(' exp ')'      { $$ = $2; }
        | '-' exp          { $$ = - $2; }
        | NUM              { $$ = $1; }
        | VAR              { $$ = vals[$1 - 'a']; }
        | '.'              { $$ = last; }
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
