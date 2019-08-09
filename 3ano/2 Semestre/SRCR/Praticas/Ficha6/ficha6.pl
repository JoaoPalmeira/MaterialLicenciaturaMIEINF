%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Programacao em logica estendida

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: definicoes iniciais

:- dynamic vem_automovel/0.
:- dynamic vem_comboio/0.
:- dynamic '-'/1.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -


% i. --------------------------------- - - - - - - - - - -  -  -  -  -   -

voo(X) :- ave(X), nao(excepcao(voo(X))).
voo(X) :- excepcao(-voo(X)).

% ii. --------------------------------- - - - - - - - - - -  -  -  -  -   -

-voo(X) :- mamifero(X), nao(excepcao(-voo(X))).
-voo(X) :- excepcao(voo(X)).

% iii. --------------------------------- - - - - - - - - - -  -  -  -  -   -

-voo(twetty).

% iv. --------------------------------- - - - - - - - - - -  -  -  -  -   -

ave(pitigui).

% v. --------------------------------- - - - - - - - - - -  -  -  -  -   -

ave(X) :- canario(X).

% vi. --------------------------------- - - - - - - - - - -  -  -  -  -   -

ave(X) :- periquito(X).

% vii. --------------------------------- - - - - - - - - - -  -  -  -  -   -

canario(piupiu).

% viii. --------------------------------- - - - - - - - - - -  -  -  -  -   -

mamifero(silvestre).

% iv. --------------------------------- - - - - - - - - - -  -  -  -  -   -

mamifero(X) :- cao(X).

% x. --------------------------------- - - - - - - - - - -  -  -  -  -   -

mamifero(X) :- gato(X).

% xi. --------------------------------- - - - - - - - - - -  -  -  -  -   -

cao(bobby).

% xii. --------------------------------- - - - - - - - - - -  -  -  -  -   -

ave(X) :- avestruz(X).

% xiii. --------------------------------- - - - - - - - - - -  -  -  -  -   -

ave(X) :- pinguim(X).

% xiv. --------------------------------- - - - - - - - - - -  -  -  -  -   -

avestruz(trux).

% xv. --------------------------------- - - - - - - - - - -  -  -  -  -   -

pinguim(pingu).

% xvi. --------------------------------- - - - - - - - - - -  -  -  -  -   -

mamifero(X) :- morcego(X).

% xvi. --------------------------------- - - - - - - - - - -  -  -  -  -   -

morcego(batemene).

% final --------------------------------- - - - - - - - - - -  -  -  -  -   -

excepcao(voo(X)) :- avestruz(X).
excepcao(voo(X)) :- pinguim(X).
excepcao(-voo(X)) :- morcego(X).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demo: Questao,Resposta -> {V,F}

demo( Questao,verdadeiro ) :-
    Questao.
demo( Questao,falso ) :-
    -Questao.
demo( Questao,desconhecido ) :-
    nao( Questao ),
    nao( -Questao ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado nao: Questao -> {V,F}

nao( Questao ) :-
    Questao, !, fail.
nao( Questao ).



