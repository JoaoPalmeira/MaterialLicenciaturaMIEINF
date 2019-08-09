%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Base de Conhecimento com informacao genealogica.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais
% Isto são questões, porque são condiçoes, sem conclusao

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado filho: Filho,Pai -> {V,F}

filho( joao,jose ).
filho( jose,manuel ).
filho( carlos,jose ).
filho(manuel,danilo).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado pai: Pai,Filho -> {V,F}

pai( P,F ) :- filho( F,P ).
pai(paulo,filipe).
pai(paulo,maria).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado avo: Avo,Neto -> {V,F}
avo(antonio,nadia).
avo(A,N) :- pai(A,X),pai(X,N).
avo(A,N) :- grau(N,A,2).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado neto: Neto,Avo -> {V,F}
neto(N,A) :- avo(A,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado sexo: Pessoa,Genero -> {V,F}
sexo(jose,masculino).
sexo(maria,feminino).
sexo(joane,feminino).

% Extensao do predicado masculino: P->{V,F}
% masculino(joao).
% Esta extensao tem a mesma capacidade de representacao de conhecimento
% que a do sexo.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado bisavo: Bisavo,Bisneto -> {V,F}
biavo(A,N) :- avo(A,X),pai(X,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado trisavo: Trisavo,Trisneto -> {V,F}
triavo(A,N) :- bisavo(A,X),pai(X,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado Tetraneto,Tetravo -> {V,F}
tetraneto(N,A) :- bisavo(A,X),bisavo(X,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado descendente: Descendente,Ascendente -> {V,F}
descendente(D,A) :- filho(D,A).
descendente(D,A) :- neto(D,A).
descendente(D,A) :-
    filho(D,X),
    descendente(X,A).
descendente(D,A) :-
    neto(D,X),
    descendente(X,A).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado descendente: Descendente,Ascendente,Grau -> {V,F}
% ProLog não é como Haskell, que é fortemente tipado.
grau(D,A,1) :- filho(D,A).
grau(D,A,2) :- neto(D,A).
grau(D,A,N+1) :-
    filho(D,X),
    grau(X,A,N).
grau(D,A,N+1) :-
    neto(D,X),
    grau(X,A,N).