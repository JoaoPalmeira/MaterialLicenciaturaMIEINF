%{
    #include <stdio.h>
    #include <string.h>

    int lineno=1;

    typedef struct horario{
        int nDias;
        char *dias[7];
    }horario;
%}
%union {horario valHor;char * valStr;}

%token <valStr>dia
%token <valStr>hora
%token texto
%type <valHor>Horario
%type <valStr>Hi
%type <valStr>Hf

%%
Horario : '<' dia ',' Slots '>' { $$.nDias=0;
                                  $$.dias[$$.nDias++]=strdup($2);}
        | Horario '<' dia ',' Slots '>' {
                            int i;
                            for( i=0 ; i<$1.nDias && strcmp($1.dias[i],$3)!=0; i++ );
                            if(i<$1.nDias)
                                printf("linha %d : Já existem eventos à/ao %s\n",lineno,$3);  
                            else
                                $1.dias[$1.nDias++]=strdup($3);
                            $$=$1;

                            }
        ;
Slots   : Slot
        | Slots Slot
        ;
Slot    : '<' Evento ',' Hi ',' Hf '>' { int hIni , minIni, hFim , minFim;
                                        hIni=atoi( strtok($4,":") );
                                        minIni=atoi( strtok(NULL , " ") );

                                        hFim=atoi( strtok($6,":") );
                                        minFim=atoi( strtok(NULL , " ") );

                                        if( hIni > hFim ||
                                           (hIni == hFim && minIni >= minFim )
                                          )
                                            printf("linha %d : A hora de início não pode ser posterior à hora de fim\n",lineno);
                                        }
        ;
Evento  : texto
        ;
Hi      : hora      { $$=strdup($1);}
        ;
Hf      : hora      { $$=strdup($1);}
        ;
%%
#include "lex.yy.c"

int yyerror(char *s){
    printf("erro: %s\n",s);
}

int main(){
    yyparse();
    return 0;
}
