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
% Extensao do predicado pertence: Valor,Lista -> {V,F}

pertence(X,[X|L]).
pertence(X,[Y|L]) :- X\=Y , pertence(X,L).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado nao: Valor -> {V,F}

nao(Q):- Q,!,falso.
nao(Q).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado comprimento: Lista,Resultado -> {V,F}

comprimento([],0 ).
comprimento([X|L],R ) :- comprimento(L,N) , R is 1+N . 

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado diferentes: Lista,Resultado -> {V,F}

diferentes([],0 ).
diferentes([X|L],N ) :- pertence( X,L ) , diferentes( L,N ).
diferentes([X|L],R ) :- nao( pertence( X,L ) ) , diferentes( L,R ), R is 1+N.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado apaga1: Valor,Lista,Resultado -> {V,F}

apaga1(X,[X|L],L).
apaga1(X,[Y|L1],[Y|L2]):-apaga1(X,L1,L2). 


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado apagaT: Valor,Lista,Resultado -> {V,F}

apagaT(X,[],[]).
apagaT(X,[X|L1],L2):-apagaT(X,L1,L2).
apagaT(X,[Y|L1],[Y|L2]):- X\==Y, apagaT(X,L1,L2).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado adicionar: Valor,Lista,Resultado -> {V,F}

adicionar(X,[Y|L],[X,Y|L]):- nao(pertence(X,[Y|L])).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado concatenar: Lista1,Lista2,ListaRes -> {V,F}

concatenar([],L,L).
concatenar(L,[],L).
concatenar([X1|L1],L2,[X1|L]) :- concatenar(L1,L2,L).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado inverter: Lista,ListaRes -> {V,F}

inverter([],[]).
inverter([A|B],R):-inverter(B,C),concatenar(C,[A],R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado subLista: L,S -> {V,F}

subLista(L,S):- concatenar(L1,L3,L), concatenar(S,L2,L3).


