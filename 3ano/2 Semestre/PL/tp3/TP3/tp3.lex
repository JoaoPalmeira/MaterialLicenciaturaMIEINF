%option noyywrap

%%
Emigrante           			return EMIGRANTE;
Nome                			return NOME;
Dados\ pessoais     			return DADOSPESSOAIS;
Dados\ de\ emigracao			return DADOSDEEMIGRACAO;
Fez								return FEZ;
Participou						return PARTICIPOU;
Obra							return OBRA;
Evento							return EVENTO;
Detalhes						return DETALHES;
[a-zA-Z0-9]+[a-zA-Z0-9 /\.,]*   {yylval.s= strdup(yytext); return VAR;} /* Tem que começar por letra ou número */
[{};:\-\>\<=]       			return yytext[0];
[\n\t ]							/* Ignorar */
%%
