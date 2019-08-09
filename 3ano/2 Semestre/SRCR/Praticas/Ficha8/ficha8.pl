%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Programacao em logica estendida

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%---
projenitores(abel,alice,ana).
nascimento(ana,01,01,2010).

projenitores(antonio,alberta,anibal).
nascimento(abibal,02,01,2010).

projenitores(bras,belem,berta).
projenitores(bras,belem,berto).
nascimento(berto,02,02,2010).
nascimento(berta,02,02,2010).
nascimento(catia,03,03,2010).

projenitores(xpto111,catia,crispim). %desconheco se o pai é celso ou caio->impreciso
projenitores(daniel,xpto222,danilo). %desconheco a mae-> incerto
nascimento(danilo,04,04,2010).
projenitores(elias,elsa,eurico).
nascimento(eurico,xpto111,04,2010). %desconheco se o dia foi 5,15,25 ->impreciso
projenitores(fausto,xpto222,xpto111). %desconheco a mae->incerto desconheco se o filha e Fabia ou Octavia
projenitores(guido,guida,golias).
nascimento(golias,xpto222,xpto222,xpto222). %desconheco a data(nunca vou saber)-> incerto
nascimento(helder,xpto222,xpto222,xpto222). %desconheco a data->incerto. Sei que não é no dia 8 agosto 2010
nascimento(ivo,xpto111,07,2010). %desconheco se o dia é 1-15. -> impreciso

%predicado excecao: predicado -> {V,F,D}
excecao(projenitores(P,M,F)):-
      projenitores(P,xpto222,F). %incerto

excecao(projenitores(celso,catia,crispim)).
excecao(projenitores(caio,catia,crispim)).

excecao(nascimento(eurico,5,04,2010)).
excecao(nascimento(eurico,15,04,2010)).
excecao(nascimento(eurico,25,04,2010)).

excecao(projenitores(fausto,M,fabia)):-
        projenitores(fausto,xpto222,xpto111).
excecao(projenitores(fausto,M,octavia)):-
        projenitores(fausto,xpto222,xpto111).

excecao(nascimento(P,D,M,A)):-
        nascimento(P,xpto222,xpto222,xpto222).

excecao(nascimento(ivo,D,07,2010)):-
        D>=1,
        D=<15.

%nascimento: pessoa,dia,mes,ano ->{V,F,D}

-nascimento(P,D,M,A):-
  nao(nascimento(P,D,M,A)),
  nao(excecao(nascimento(P,D,M,A))).
-nascimento(helder,8,08,2010).

%projenitores: projenitorP,projenitorM,filho -> {V,F}

-projenitores(P,M,F):-
  nao(projenitores(P,M,F)),
  nao(excecao(projenitores(P,M,F))).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demo: Lista_Questao,Resposta -> {V,F}

demo([H|T],verdadeiro ) :-
    H,
    demo(T,verdadeiro).
demo( [H|T],falso ) :-
    -H.
demo([H|T],falso):-
    demo(T,falso).
demo([H|T],desconhecido ) :-
    nao( H ),
    nao( -H).
demo([H|T],desconhecido):-
    demo(T,desconhecido).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado nao: Questao -> {V,F}

nao( Questao ) :-
    Questao, !, fail.
nao( Questao ).
