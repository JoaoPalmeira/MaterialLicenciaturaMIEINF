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


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Atravessar a linha de caminho-de-ferro?

atravessar( ferrovia) :-
    -vem_comboio.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Estensao do predicado par: N -> {V,F,D}

par(0).
par(X) :-
    N is X+2,
    N>=0,
    par(N).
-par(X):-
    nao(par(N)).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Estensao do predicado impar: N -> {V,F,D}

impar(1).
impar(X) :-
    N is X-2,
    N>=1,
    impar(N).
-impar(0).
-impar(X):-
    N is X-2,
    N>=0,
    -impar(N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Estensao do predicado arcoiris: Cor -> {V,F,D}

arcoiris(violeta).
arcoiris(vermelho).
%etc...

-arcoiris(branco).
-arcoiris(preto).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Estensao do predicado nodo: N -> {V,F,D}
nodo(a).
nodo(b).
nodo(c).
nodo(d).
nodo(e).
nodo(f).
nodo(g).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Estensao do predicado arco: origem, destino -> {V,F,D}

arco(b,a).
arco(b,c).
arco(c,a).
arco(c,d).
arco(f,g).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Estensao do predicado terminal: T -> {V,F,D}

-terminal(N):-
    nodo(N),
    arco(N,D).
terminal(N):-
    nodo(N),
    nao(-terminal(N)).














