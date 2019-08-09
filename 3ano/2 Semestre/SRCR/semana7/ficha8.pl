%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Programacao em logica estendida
%
% Representacao de conhecimento imperfeito

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: definicoes iniciais

:- op( 900,xfy,'::' ).
:- dynamic filho/2.
:- dynamic pai/2.
:- dynamic mae/2.
:- dynamic irmao/2.
:- dynamic irma/2.
:- dynamic nascer/4.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demo: Questao,Resposta -> {V,F}

demo( Questao,verdadeiro ) :-
    Questao.
demo( Questao, falso ) :-
    -Questao.
demo( Questao,desconhecido ) :-
    nao( Questao ),
    nao( -Questao ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado nao: Questao -> {V,F}

nao( Questao ) :-
    Questao, !, fail.
nao( Questao ).


solucoes( X,Y,Z ) :-
    findall( X,Y,Z ).


% Resoluçao


%pai: P,F -> {V,F}

%mae: P,F -> {V,F}

-pai(P,F) :-
    nao(pai(P,F)),
    nao(excecao(pai(P,F))).

-mae(M,F) :-
    nao(mae(M,F)),
    nao(excecao(mae(M,F))).

% Invariante Estrutural: nao permitir a insercao de conhecimento
%                         repetido

+pai(P,F) :: (solucoes((P,F),(pai(P,F)),S),
                  comprimento(S,N), N == 1
                  ).

% Invariante Referencial: nao admitir mais do que 1 pai
%                         para um mesmo individuo

+pai(P,F) :: (solucoes((Ps),(pai(Ps,F)),S),
                  comprimento(S,N), N =< 1
                  ).

% Invariante Estrutural: nao permitir a insercao de conhecimento
%                         repetido

+mae(M,F) :: (solucoes((M,F),(pai(M,F)),S),
                  comprimento(S,N), N == 1
                  ).

% Invariante Referencial: nao admitir mais do que 1 mãe
%                         para um mesmo individuo

+mae(M,F) :: (solucoes((Ms),(pai(Ms,F)),S),
                  comprimento(S,N), N =< 1
                  ).

% Pais e Maes
pai(abel, ana).
mae(alice, ana).
pai(antonio, anibal).
mae(alberta, anibal).
pai(bras, berta).
pai(bras, berto).
mae(belem, berta).
mae(belem, berto).
mae(catia, crispim).
pai(daniel, danilo).
mae(xpto606, danilo).
pai(elias, eurico).
mae(elsa, eurico).
mae(xpto660,fabia).
mae(xpto660,otavia).
pai(guido, golias).
mae(guida, golias).

%i)

%nascer : C, D, M, A -> {V,F}
nascer(ana, 01, 01, 2010).

%ii)

nascer(anibal, 02, 01, 2010).

%iii)

nascer(berta, 02, 02, 2010).
nascer(berto, 02, 02, 2010).

%iv)

nascer(catia, 03, 03, 2010).

%v)

excecao(pai(celso, crispim)).
excecao(pai(caio, crispim)).

%vi)

excecao(mae(M,F)) :- mae(xpto606,F).

nascer(danilo, 04, 04, 2010).

%vii)

-nascer(C,D,M,A) :- nao(nascer(C,D,M,A)), nao(excecao(nascer(C,D,M,A))).

excecao(nascer(eurico, 05, 05, 2010)).
excecao(nascer(eurico, 15, 05, 2010)).
excecao(nascer(eurico, 25, 05, 2010)).

%viii)

excecao(pai(fausto,fabia)).
excecao(pai(fausto,otavia)).

excecao(mae(M,F)) :- mae(xpto660,F).

%ix)

nascer(golias, xpto661, xpto662, xpto663).
excecao(nascer(C,D,M,A)) :- nascer(C,xpto661,xpto662,xpto663).
nulo(xpto661).

+nascer(C,D,M,A) :: (solucoes((Dia,Mes,Ano), (nascer(golias, Dia,Mes,Ano)), nao(nulo(Dia,Mes,Ano)), S),
                     comprimento(S,N), N==0).

%x)

-nascer(helder, 08, 08, 2010).

nascer(helder, xpto610, xpto611, xpto612).
excecao(nascer(C,D,M,A)) :- nascer(C,xpto610,xpto611,xpto612).

%xi)

excecao(nascer(ivo,D,06,2010)) :- D>=1, D<=15.