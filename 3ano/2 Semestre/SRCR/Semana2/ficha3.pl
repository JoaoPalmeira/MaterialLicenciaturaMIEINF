%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais
 
:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

 
nao(X) :-
    X, !, fail.
nao(X).

%i
pertence(X, [X | _]).
pertence(X, [Y | L]) :- X \= Y, pertence(X,L).

%ii
comprimento([],0).
comprimento([X | Y], R) :- comprimento(Y, R2),
                            R is 1+R2 .

%iii
diferentes([X|L],N) :- pertence(X,L), N is 0, diferentes(L,N)t.
diferentes([X|L],N) :- nao(pertence(X,L)), N is N+1, diferentes(L,N). 

%iv
apagar(X, [X | T],T).
apagar(X, [Y | T], [Y | R]) :- apagar(X, T, R).

%v
apagaT(X,[],[]).
apagaT(X,[X|L1],L2):-apagaT(X,L1,L2).
apagaT(X,[Y|L1],[Y|L2]):- X\==Y, apagaT(X,L1,L2).

%vi
adicionar(X,[],[X|[]]).
adicionar(X,[X|L],[X|L]).
adicionar(X, [Y|L],[Y|R]) :-
    adicionar(X,L,R).

%vii
concat([],L,L).
concat([H|T],L1,[H|R]) :- concat(T,L1,R).

concatenar([],[],[]).
concatenar(X,X,[]).
concatenar(X,[],X).
concatenar([Lista|Rest],Y,[Lista|X]) :- concatenar(Rest,Y,X).

%viii
inverter([],[]).
inverter([H|T],R) :- inverter(T,Z),
    						concat(Z,[H],R).

%ix
subLista(L,S):- concatenar(L1,L3,L), concatenar(S,L2,L3).





