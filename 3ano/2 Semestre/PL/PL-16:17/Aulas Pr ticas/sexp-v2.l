%{

%}
%option noyywrap
%%
[()]        {return yytext[0];}
[a-zA-Z]+   {yylval.valStr=strdup(yytext); return pal;}
[0-9]+      {yylval.valStr=strdup(yytext);return num;}
.|\n {;}
%%