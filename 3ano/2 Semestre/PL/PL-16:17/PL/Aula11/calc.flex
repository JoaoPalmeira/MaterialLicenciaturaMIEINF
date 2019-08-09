%option noyywrap yylineno
%%
[0-9]+(\.[0-9]+)?   { yylval.d = atof(yytext);
                      return NUM; }
pi                  { yylval.d = M_PI;
                      return NUM; }
[\^+\-*/()=\n\.]    { return yytext[0]; }
exp                 { yylval.f = exp; return F1; }
log                 { yylval.f = log; return F1; }
sqrt                { yylval.f = sqrt; return F1; }
s[ie]n              { yylval.f = sin; return F1; }
cos                 { yylval.f = cos; return F1; }
tan                 { yylval.f = tan; return F1; }
[a-z]               { yylval.c = yytext[0];
                      return VAR; }
[ \t]               { }
.                   { yyerror("Caracter desconhecido"); }
%%
