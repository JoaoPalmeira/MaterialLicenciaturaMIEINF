%{
#define _GNU_SOURCE
#include<stdio.h>
#include<string.h>
#include<fcntl.h>
int yylex();
void yyerror(char* c);
void writeHTML(char *s, char* h);
char * emigranteAtual;
char * htmlToWrite;
char * htmlToWriteDadosPessoais;
char * htmlToWriteDadosEmigracao;
char * htmlToWriteFez;
char * htmlToWriteParticipou;
char * relacoesEncontradasFez;
char * relacoesEncontradasParticipou;
%}

%union {char* s;}
%token EMIGRANTE NOME VAR DADOSPESSOAIS FEZ PARTICIPOU DADOSDEEMIGRACAO OBRA DETALHES EVENTO
%type <s> emigrante VAR elementos fez participou relacoesFez relacoesParticipou dadosEmigracao nodos grafo dadosPessoais obra detalhes evento
%%

grafo: nodos  {printf("digraph{\nrankdir=LR\n%s\n}",$1);}

nodos : EMIGRANTE '{' emigrante '}' nodos {
                                              asprintf(&$$,"%s%s", $3,$5);
                                          }
      | OBRA '{' obra '}' nodos           {asprintf(&$$,"%s%s",$3,$5);}
      | EVENTO '{' evento '}' nodos       {asprintf(&$$,"%s%s",$3,$5);}
      |                                   {$$="";}          
;

emigrante: NOME ':' VAR ';' dadosPessoais dadosEmigracao fez participou {
            asprintf(&htmlToWrite,
            "<!DOCTYPE html>\n"
              "<html lang=\"pt\">\n"
              "\t<head>\n"
                "\t\t<meta charset=\"utf8\">\n"
                "\t\t<link rel=\"stylesheet\" href=\"strips.css\">\n"
                "\t\t<link rel=\"stylesheet\" href=\"//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css\">\n"
                "\t\t<link rel=\"stylesheet\" href=\"title.css\">\n"
                "\t\t<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.7.1.min.js\"></script>\n"
              "\t</head>\n"
              "\t<body><section class=\"strips\">\n"
                "%s"
                "%s"
                "%s"
                "%s"
                "<i class=\"fa fa-close strip__close strip__close--show\" style=\"transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1) 1s;\">\n"
                  "\t\tFechar\n"
                "</i>\n"
              "\t</section>\n"
              "\t<script type=\"text/javascript\" src=\"https://codepen.io/ettrics/pen/ZYqKGb.js\"></script>\n"
              "\t</body>\n"
            "</html>",htmlToWriteDadosPessoais, htmlToWriteDadosEmigracao, htmlToWriteFez, htmlToWriteParticipou); 
            emigranteAtual=$3;

            asprintf(&$$,"\"%s\"[href=\"%s.html\" style=filled fillcolor=\"aliceblue\"]\n",emigranteAtual,emigranteAtual);

            char *pt;

          if(relacoesEncontradasFez != NULL){
              for(pt = strtok(relacoesEncontradasFez,";"); pt != NULL;  pt = strtok (NULL, ";")){
                asprintf(&$$,"%s\"%s\"->\"%s\" [label = \"Fez\"]\n",$$,emigranteAtual,pt);
              }
              free(relacoesEncontradasFez);
              relacoesEncontradasFez = NULL;
          }
          if(relacoesEncontradasParticipou != NULL){
              for(pt = strtok(relacoesEncontradasParticipou,";"); pt != NULL;  pt = strtok (NULL, ";")){
                  asprintf(&$$,"%s\"%s\"->\"%s\" [label = \"Participou\"]\n",$$,emigranteAtual,pt);
                }
              free(relacoesEncontradasParticipou);
              relacoesEncontradasParticipou = NULL;
          }
            writeHTML(emigranteAtual,htmlToWrite);

          }
;
dadosPessoais: DADOSPESSOAIS '-' '>' elementos '<' '-'{
            asprintf(&htmlToWriteDadosPessoais,
              "<article class=\"strips__strip\">\n"
                "<div class=\"strip__content\">\n"
                  "<H1 class=\"strip__title\">Dados Pessoais</H1>\n"
                  "<div class=\"strip__inner-text\" style=\"transition: all 0.5s cubic-bezier(0.23, 1, 0.32, 1) 0.3s;\">\n"
                    "\t\t<ul style=\"list-style-type:circle\">\n"
                      "%s"
                    "</ul>\n"
                  "\t\t</div>\n"
                "</div>\n"
              "</article>\n"
              ,$4);
          }
;

dadosEmigracao: DADOSDEEMIGRACAO '-' '>' elementos '<' '-'{
            asprintf(&htmlToWriteDadosEmigracao,
              "<article class=\"strips__strip\">\n"
                "<div class=\"strip__content\">\n"
                  "<H1 class=\"strip__title\">Dados de Emigração</H1>\n"
                  "<div class=\"strip__inner-text\" style=\"transition: all 0.5s cubic-bezier(0.23, 1, 0.32, 1) 0.3s;\">\n"
                    "\t\t<ul style=\"list-style-type:circle\">\n"
                      "%s"
                    "</ul>\n"
                  "\t\t</div>\n"
                "</div>\n"
              "</article>\n"
              ,$4);
          }

fez: FEZ '-' '>' relacoesFez '<' '-'{
            asprintf(&htmlToWriteFez,
              "<article class=\"strips__strip\">\n"
                "<div class=\"strip__content\">\n"
                  "<H1 class=\"strip__title\">Obras</H1>\n"
                  "<div class=\"strip__inner-text\" style=\"transition: all 0.5s cubic-bezier(0.23, 1, 0.32, 1) 0.3s;\">\n"
                    "\t\t<ul style=\"list-style-type:circle\">\n"
                      "%s"
                    "</ul>\n"
                  "\t\t</div>\n"
                "</div>\n"
              "</article>\n"
              ,$4);
          }
;

participou: PARTICIPOU '-' '>' relacoesParticipou '<' '-' {
            asprintf(&htmlToWriteParticipou,
              "<article class=\"strips__strip\">\n"
                "<div class=\"strip__content\">\n"
                  "<H1 class=\"strip__title\">Eventos</H1>\n"
                  "<div class=\"strip__inner-text\" style=\"transition: all 0.5s cubic-bezier(0.23, 1, 0.32, 1) 0.3s;\">\n"
                    "\t\t<ul style=\"list-style-type:circle\">\n"
                      "%s"
                    "</ul>\n"
                  "\t\t</div>\n"
                "</div>\n"
              "</article>\n",$4);
          }
;



relacoesFez: VAR ';' relacoesFez {
                        asprintf(&$$,"<a href=\"%s.html\"><li>%s</li></a>\n%s",$1,$1,$3);
                        if(relacoesEncontradasFez != NULL){
                          asprintf(&relacoesEncontradasFez,"%s;%s",$1,relacoesEncontradasFez);
                        }
                        else{
                          asprintf(&relacoesEncontradasFez ,"%s;",$1);
                        }
                     }
      |              {$$="";}
;

relacoesParticipou: VAR ';' relacoesParticipou{
                      asprintf(&$$,"<a href=\"%s.html\"><li>%s</li></a>\n%s",$1,$1,$3);
                      if(relacoesEncontradasParticipou != NULL){
                        asprintf(&relacoesEncontradasParticipou,"%s;%s",$1,relacoesEncontradasParticipou);
                      }
                      else{
                        asprintf(&relacoesEncontradasParticipou,"%s;",$1);
                      }
                     }
      |              {$$="";}
;

obra: NOME ':' VAR ';' detalhes {
                          asprintf(&$$,"\"%s\"[href=\"%s.html\" style=filled fillcolor=\"lawngreen\"]\n",$3,$3);
                          asprintf(&$5,
                          "<!DOCTYPE html>\n"
                            "<html lang=\"pt\">\n"
                              "\t<head>\n"
                                "\t\t<meta charset=\"utf8\">\n"
                                "\t\t<link rel=\"stylesheet\" href=\"title.css\">\n"
                                "%s",$5);
                          writeHTML($3,$5); 
                        }

evento: NOME ':' VAR ';' detalhes {
                          asprintf(&$$,"\"%s\"[href=\"%s.html\" style=filled fillcolor=\"gold\"]\n",$3,$3);
                          asprintf(&$5,
                          "<!DOCTYPE html>\n"
                            "<html lang=\"pt\">\n"
                              "\t<head>\n"
                                "\t\t<meta charset=\"utf8\">\n"
                                "\t\t<link rel=\"stylesheet\" href=\"title.css\">\n"
                                "%s",$5);
                          writeHTML($3,$5); 
                        }                       

detalhes: DETALHES '-' '>' elementos '<' '-' {$$=$4;}

elementos: VAR '=' VAR ';' elementos {
                                        if(!strcmp($1,"Esposa/Marido ") || !strcmp($1,"Esposa/Marido"))
                                          asprintf(&$$,"<li>%s: <a href=\"%s.html\">%s</a></li>\n%s",$1,$3,$3,$5);
                                        else
                                          asprintf(&$$,"<li>%s: %s</li>\n%s",$1,$3,$5);
                                      }
          |                          {$$="";}
;


%%
#include "lex.yy.c"
char * emigranteAtual;
char * htmlToWrite = NULL;
char * htmlToWriteDadosPessoais = NULL;
char * htmlToWriteDadosEmigracao = NULL;
char * htmlToWriteFez = NULL;
char * htmlToWriteParticipou = NULL;
char * relacoesEncontradasFez = NULL;
char * relacoesEncontradasParticipou = NULL;
int main(){
  yyparse();
  if(htmlToWrite != NULL)
    free(htmlToWrite);
  return 0;
}
void yyerror(char* c){
  fprintf(stderr,"%s, %s, %d \n",c,yytext,yylineno);
}
void writeHTML(char* nomeFicheiro, char* htmlString){
  char * fileName;
  fileName= malloc(sizeof(char)*(strlen(nomeFicheiro)+10));
  sprintf(fileName,"%s.html",nomeFicheiro);
  FILE *fd=fopen(fileName,"w");

  if(fd) {
    fprintf(fd,"<h1 class = \"page-title\">%s</h1>\n",nomeFicheiro);
    fprintf(fd,"%s",htmlString);
    fclose(fd);
  }
  else printf("Erro ao escrever ficheiro %s\n",fileName);
  free(fileName);
}
