%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Base de Conhecimento com informacao genealogica.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado soma: Valor,Valor,Resultado -> {V,F}

soma( A,B,R ) :- R is A+B.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado soma: Valor,Valor,Valor,Resultado -> {V,F}

soma( A,B,C,R ) :- R is A+B+C.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado somaL: Lista,Resultado -> {V,F}

somaL( [],0 ).
somaL( [X],X ).
somaL( [X|L],R ) :- somaL( L,R1 ), R is X+R1.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado op: Operacao,Valor,Valor,Resultado -> {V,F}

op( add,A,B,R ) :- R is A+B.
op( sub,A,B,R ) :- R is A-B.
op( mul,A,B,R ) :- R is A*B.
op( div,A,B,R ) :- Y \= 0, R is A/B.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado opL: Operação,Lista,Resultado -> {V,F}

opL( add,[],0 ).
opL( mul,[],0).
opL( add,[X|L], R) :- somaL( [X|L],R ).
opL( mul,[X|L], R) :- opL( mul, L, R1), R is X*R1.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado maior: Valor,Valor,Resultado -> {V,F}

maior(A,B,B):- B>=A.
maior(A,B,A):- A>B.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado maior: Valor,Valor,Valor,Resultado -> {V,F}

maior(A,B,C,R):- maior(B,C,R1), maior(A,R1,R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado maiorL: Lista,Resultado -> {V,F}

maiorL([X],X).
maiorL([A,B|L],R) :- maior(A,B,R1), maiorL([R1|L],R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado menor: Valor,Valor,Resultado -> {V,F}

menor(A,B,B):- B=<A.
menor(A,B,A):- A<B.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado menor: Valor,Valor,Resultado -> {V,F}

menor(A,B,C,R):- menor(B,C,R1), menor(A,R1,R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado menorL: Lista,Resultado -> {V,F}

menorL([X],X).
menorL([A,B|L],R):- menor(A,B,R1), menorL([R1|L],R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado media: Valor,Valor,Resultado -> {V,F}

comprimento([],0 ).
comprimento([X|L],R ) :- comprimento(L,N) , R is 1+N . 

media(L,R):- somaL(L,RA), comprimento(L,RB), R is RA/RB.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado crescente: Lista,ListaResultado -> {V,F}

crescente([],[]).
crescente([X|L],[X|R]) :- menorL(L,Y), X<Y, crescente(L,R).
crescente([X|L],[Y|R]) :- menorL(L,Y), X>Y, crescente(L,R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado decrescente: Lista,ListaResultado -> {V,F}

decrescente([],[]).
decrescente([X|L],[X|R]) :- decrescente(L,R), maiorL(L,Y), X>=Y.
decrescente([X|L],[Y|R]) :- decrescente(L,R), maiorL(L,Y), X<Y.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado vazios: Lista,Resultado -> {V,F}

vazios([],0).
vazios([[]|L],R) :- vazios(L,N), R is 1+N.
vazios([X|L],N) :- X\=[], vazios(L,N). 

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado nao: Questao -> {V,F}

nao(Questao):- Questao, !, fail.
nao(Questao).






