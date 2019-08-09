%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais
 
:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).
 
:- dynamic soma/2.

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
% Extensao do predicado op: X,Y,OP,R -> {V,F}
 
op(X,Y,+,R) :- R is X+Y .
op(X,Y,-,R) :- R is X-Y .
op(X, Y, * , R) :- R is X * Y.
op(X, Y, / , R) :- R is X/Y.

%v
% Extensao do predicado op_conj: L,OP,R -> {V,F}
op_conj([], + , 0).
op_conj([], - , 0).
op_conj([], * , 1).
op_conj([], / , 1) .
op_conj([X | Rest], OP, R) :-
    op_conj(Rest, OP, R2),
    op(R2, X, OP, R).

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

%xi
ordenarCresc([X],[X]).
ordenarCresc([X|Y],T):- 
	ordenarCresc(Y,R),insereOrdenado(X,R,T).

insereOrdenado(X,[],[X]).
insereOrdenado(X,[Y|Z],[X|[Y|Z]]) :- X<Y.
insereOrdenado(X,[Y|Z],[Y|R2]) :- X>=Y,
	insereOrdenado(X,Z,R2).
