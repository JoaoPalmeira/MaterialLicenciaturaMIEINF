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

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado filho: Filho,Pai -> {V,F,D}

filho( joao,jose ).
filho( jose,manuel ).
filho( carlos,jose ).

-filho( F,P ) :-
    nao( filho( F,P ) ),
    nao( excecao( filho( F,P ) ) ).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+filho( F,P ) :: (solucoes( (F,P),(filho( F,P )),S ),
                  comprimento( S,N ), N == 1
                  ).

% Invariante Referencial: nao admitir mais do que 2 progenitores
%                         para um mesmo individuo

+filho( F,P ) :: (solucoes( (Ps),(filho( F,Ps )),S ),
                  comprimento( S,N ), N =< 2
                  ).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado pai: Filho, Pai  -> {V,F,D}

-pai( F,P ) :-
    nao( pai( F,P ) ),
    nao( excecao( pai( F,P ) ) ).


% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+pai( F,P ) :: (solucoes( (F,P),(pai( F,P )),S ),
                  comprimento( S,N ), N == 1
                  ).

% Invariante Referencial: nao admitir mais do que 1 pai
%                         para um mesmo individuo

+pai( F,P ) :: (solucoes( (Ps),(pai( F,Ps )),S ),
                  comprimento( S,N ), N =< 1
                  ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado mae: Filho, Pai  -> {V,F,D}

-mae( F,P ) :-
    nao( mae( F,P ) ),
    nao( excecao( mae( F,P ) ) ).


% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+mae( F,P ) :: (solucoes( (F,P),(mae( F,P )),S ),
                  comprimento( S,N ), N == 1
                  ).

% Invariante Referencial: nao admitir mais do que 1 mae
%                         para um mesmo individuo

+mae( F,P ) :: (solucoes( (Ps),(mae( F,Ps )),S ),
                  comprimento( S,N ), N =< 1
                  ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado data: Pessoa, Data  -> {V,F,D}

-data( F,D ) :-
    nao( data( F,D ) ),
    nao( excecao( data( F,D ) ) ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Explicitacao das situacoes de excecao

% A Belem e filha de uma pessoa de que se desconhece a identidade

filho( belem,xpto023 ).
excecao( filho( F,P ) ) :-
    filho( F,xpto023 ).

% A Maria é filha do Faria ou do Garcia

excecao( filho( maria,faria ) ).
excecao( filho( maria,garcia ) ).

% O Julio tem um filho que ninguem pode conhecer

filho( xpto732,julio ).
excecao( filho( F,P ) ) :-
    filho( xpto732,P ).
nulo( xpto732 ).
+filho( F,P ) :: (solucoes( (Fs,P),(filho(Fs,julio),nao(nulo(Fs))),S ),
                  comprimento( S,N ), N == 0 
                  ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

evolucao( Termo ) :-
    solucoes( Invariante,+Termo::Invariante,Lista ),
    insercao( Termo ),
    teste( Lista ).

insercao( Termo ) :-
    assert( Termo ).
insercao( Termo ) :-
    retract( Termo ),!,fail.

teste( [] ).
teste( [R|LR] ) :-
    R,
    teste( LR ).

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

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

solucoes( X,Y,Z ) :-
    findall( X,Y,Z ).

comprimento( S,N ) :-
    length( S,N ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -

%ex1
pai(ana,abel).
mae(ana,alice).
data(ana,01/01/2010).

%ex2
pai(anibal,antonio).
mae(anibal,alberta).
data(anibal,02/01/2010).

%ex3

pai(berto,bras).
mae(berto,belem).
data(berto,02/02/2010).

pai(berta,bras).
mae(berta,belem).
data(berta,02/02/2010).

%ex4
data(catia,03/03/2010).

%ex5
mae(crispim,catia).

excecao(pai(crispim, celso)).
excecao(pai(crispim, caio)).


%ex6

pai(danilo,daniel).
data(danilo,04/04/2010).
mae(danilo,xpto023).
excecao(mae(F,P)):-
        mae(F,xpto023).

%ex7

mae(eurico,elsa).
pai(eurico,elias).
excecao(data(eurico, 05/05/2010)).
excecao(data(eurico, 15/05/2010)).
excecao(data(eurico, 25/05/2010)).

%ex8

excecao(pai(fabia,fausto)).
excecao(pai(octavia,fausto)).

mae(fabia,xpto023).
excecao(mae(F,P)):-
      mae(F,xpto023).

mae(octavia,xpto023).
excecao(mae(F,P)):-
      mae(F,xpto023).

%ex9

pai(golias,guido).
mae(golias,guida).

data(golias,xpto023).
excecao(data(F,D)):-
      data(F,xpto023).
nulo(xpto023).
+data( F,D ) :: (solucoes( (F,Ds),(data(golias,Ds),nao(nulo(Ds))),S ),
                  comprimento( S,N ), N == 0 
                  ).


%ex10
-(data(helder,08/08/2010)).

data(helder,xpto023).
excecao(data(F,D)):-
        data(F,xpto023).



%alterar o invariante da data de uma string para um D,M,A para fazer o exercicio 11.
