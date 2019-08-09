%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Base de Conhecimento com informacao genealogica.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado filho: Filho,Pai -> {V,F}

:- dynamic filho/2.
:- dynamic pai/2.
:- dynamic avo/2.
:- dynamic genero/2.
:- dynamic comprimento/2.

filho( joao,jose ).
filho( jose,manuel ).
filho( carlos,jose ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado pai: Pai,Filho -> {V,F}

pai( P,F ) :-
    filho( F,P ).

%iv)
pai(paulo, filipe).
%v)
pai(paulo, maria).





%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado avo: Avo,Neto -> {V,F}
%vi)
avo(antonio, nadia).

%Extensao do predicado genero: Pessoa,Genero -> {V,F}
%vii)
genero(joao, masculino).
%vii)
genero(jose, masculino).
%ix
genero(maria, feminino).
%x
genero(joana, feminino).

%xii)

avo(A,N) :- filho(N,X),pai(A,X).

%xiii)

% Extensao do predicado neto: Neto,Avo -> {V,F}

neto(N,A) :- avo(A,N).




%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado bisavo: Bisavo,Bisneto -> {V,F}

%xvii)

bisavo(X,Y) :- filho(N,X),avo(N,Y).




%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado descendente: Descendente,Ascendente -> {V,F}


%xiv)

descendente(X,Y) :- filho(X,Y); pai(Y,X); avo(Y,X); neto(X,Y); bisavo(X,Y).




%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado descendente: Descendente,Ascendente,Grau -> {V,F}

%xv)

descendente(X,Y) :- filho(X,Y).

%Ficha 2
%i
% Extensao do predicado soma: Valor, Valor, Soma -> {V,F}
soma(X,Y,S) :- S is X+Y.

%viii
% Extensao do predicado menor: Valor, Valor, Menor -> {V,F}
menor(X,Y,X) :- X<Y.
menor(X,Y,Y) :- X>=Y.

%v
% Extensao do predicado operaçao: Lista, Resultado, Operação -> {V,F}


%vii
% Extensao do predicado maiorL: Lista, Maior -> {V,F}




%Ficha 3
%iv 
%comprimento:L,N -> {V,F}
comprimento ([],0).
comprimento([X|L],N) :- N is N+1, comprimento(L,N).

%v
%quantos: L, N -> {V,F}
%quantos ([],0).
%quantos([H,T],N+1):- 

%vi
%apagar: X,L,L -> {V,F}
%apagar (X,[X|L],[L]).
%apagar (X,[Y|L],


