%{

%}

%option noyywrap

%%
\"[^"]+\"                   {return texto;}
[0-9]{1,2}:[0-9]{1,2}       {yylval.valStr=strdup(yytext);
                             return hora;}
seg|ter|qua|qui|sex|sab|dom {yylval.valStr=strdup(yytext);
                             return dia;}
[<,>]                       {return yytext[0];}
\n                          {lineno++;}
.                           {;}
%%
