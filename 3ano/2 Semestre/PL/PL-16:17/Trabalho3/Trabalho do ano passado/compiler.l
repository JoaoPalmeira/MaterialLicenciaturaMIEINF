%{
#include "y.tab.h"

#define TYPE_INTEGER 0
%}

%option noyywrap
%option yylineno

%%
"else"          { return ELSE; }
"empty"         { return EMPTY; }
"function"      { return FUNCTION; }
"if"            { return IF; }
"instructions"  { return INSTRUCTIONS; }
"integer"       { return INTEGER; }
"program"       { return PROGRAM; }
"read"          { return READ; }
"return"        { return RETURN; }
"variable"      { return VARIABLE; }
"while"         { return WHILE; }
"write"         { return WRITE; }
\&\&            { return AND_OP; }
\|\|            { return OR_OP; }
\<\=            { return LE_OP; }
\>\=            { return GE_OP; }
\=\=            { return EQ_OP; }
\!\=            { return NE_OP; }
\+\+            { return CONCAT; }
\;              { return SEMICOLON; }
\{              { return O_CURLY; }
\}              { return C_CURLY; }
\,              { return COMMA; }
\:              { return COLON; }
\=              { return EQUALS; }
\(              { return O_BRACKETS; }
\)              { return C_BRACKETS; }
\[              { return O_SQUARE; }
\]              { return C_SQUARE; }
\!              { return NOT_OP; }
\+              { return ADD_OP; }
\-              { return SUB_OP; }
\*              { return MUL_OP; }
\/              { return DIV_OP; }
\%              { return MOD_OP; }
\<              { return LT_OP; }
\>              { return GT_OP; }
[A-Za-z]+       { yylval.value.identifier = strdup(yytext); return ID; }
[0-9]+          { yylval.value.number = atoi(yytext); yylval.value.type = TYPE_INTEGER; return NUMERO; }
\"(\\.|[^"])*\" { yylval.value.string = strdup(yytext); return STRING; }
.|\n            { ; }
