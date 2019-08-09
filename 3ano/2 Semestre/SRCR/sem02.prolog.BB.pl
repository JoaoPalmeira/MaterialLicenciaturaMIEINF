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
% Extensao do predicado soma (2 numeros): Valor, Valor, Soma -> {V,F}
soma(X,Y,S) :- S is X+Y.

%ii
% Extensao do predicado soma (3 numeros): Valor, Valor, Valor, Soma -> {V,F}
soma2(X,Y,W,S) :- S is X+Y+W.

%iii
% Extensao do predicado somaL: Lista , Valor -> {V,F}
somaL([],0).
somaL([X | Rest],R) :-
    somaL(Rest, R2), R is X + R2 .

%iv
% Extensao do predicado operacao: Op, X, Y, Resultado ->{V,F}
	operacao(adicao,X,Y,Adicao) :- Adicao is X+Y.
	operacao(subtracao,X,Y,Subtracao) :- Subtracao is X-Y.
	operacao(multiplicacao,X,Y,Multiplicacao) :- Multiplicacao is X*Y.
	operacao(divisao,X,Y,Divisao) :- Divisao is X/Y.

%v
% Extensao do predicado operaçao: Lista, Resultado, Operação -> {V,F}

%vi
% Extensao do predicado maior: Valor, Valor, Maior -> {V,F}
maior(X,Y,X) :- X>Y.
maior(X,Y,Y) :- X<Y.

%vii
% Extensao do predicado maiorL: Lista, Maior -> {V,F}
maiorL([X],X) .
maiorL([X | Y], R) : -
    maiorL(Y, R2), maior(X, R2, R) .

%ix
% Extensao do predicado menor: Valor, Valor, Menor -> {V,F}
menor(X,Y,X) :- X<Y.
menor(X,Y,Y) :- X>Y.

%x
% Extensao do predicado menorL: Lista, Menor -> {V,F}
menorL([X],X).
menorL([X | Y], R) :- 
    menorL(Y, R2), menor(X, R2, R).



%Ficha 3
%iv
pertence X,L ->{V,F}
pertence(X,[X|L]).
pertence(X,[Y|L]) :- X\=Y, pertence(X,L).

%v
comprimento:L,N -> {V,F}
comprimento ([],0).
comprimento([X|L],N) :- N is N+1, comprimento(L,N).

%vi
diferentes X,L -> {V,F}
diferentes ([],0).
diferentes([X|L],N) :- pertence(X,L), diferentes(L,N).
diferentes([X|L], ) :- pertence(X,L), diferentes(L,N). 

%vii
apagar1: X,L,L -> {V,F}
apagar1 (X,[X|L],[L]).
apagar1 (X,[Y|L],[L]) :- X\=Y, apagar1(X,L).

%viii
concatenar L1, L2, L -> {V,F}
concatenar ([],L2,L2).
concatenar([X|L],L2,[X|L]) :- concatenar(L1,L2,L).

%ix
sublista S,L -> {V,F}
sublista (S,L) :- concatenar(X,S,Y), concatenar(Y,Z,L).



