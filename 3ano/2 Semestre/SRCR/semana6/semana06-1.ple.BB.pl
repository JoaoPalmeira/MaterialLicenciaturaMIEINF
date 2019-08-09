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
% Extensao do predicado excecao: A -> {V,F}



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado mamifero: A -> {V,F}
mamifero(silvestre).
mamifero(A):-
    cao(A).
mamifero(A):-
    gato(A).
mamifero(A):-
    morcego(A).
-mamifero(A):-
    ave(A).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado ave: A -> {V,F}

ave(pitigui).
ave(A):-
    canario(A).
ave(A):-
    periquito(A).
ave(A):-
    avestruz(A).
ave(A):-
    pinguim(A).
-ave(A):-
    mamifero(A).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado cao: A -> {V,F}
cao(boby).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado canario: A -> {V,F}
canario(piupiu).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado avestruz: A -> {V,F}
avestruz(trux).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado pinguim: A -> {V,F}
pinguim(pingú).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado morcego: A -> {V,F}
morcego(bateméne).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado voar: A -> {V,F,D}

voar(A):-
    ave(A),nao( excecao( voar(A))).
-voar(tweety).
-voar(pinguim).
-voar(avestruz).
-voar(A):-
    mamifero(A),nao( excecao(-voar(A))).
voar(morcego).
-voar(A):- 
    excecao(voar(A)).
voar(A):- 
    excecao(-voar(A)).

excecao(voar(A)) :- 
                avestruz(A).
excecao(voar(A)):-
                pinguim(A).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Atravessar a estrada?

atravessar( estrada ) :-
    nao( vem_automovel ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Atravessar a linha de caminho-de-ferro?

atravessar( ferrovia ) :-
    -vem_comboio.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado par: N -> {V,F,D}

par( 0 ).
par( X ) :-
    N is X-2,
    N >= 0,
    par( N ).
-par( X ) :-
    nao( par( X ) ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado impar: N -> {V,F,D}

impar( 1 ).
impar( X ) :-
    N is X-2,
    N >= 1,
    impar( N ).
-impar( 0 ).
-impar( X ) :-
    N is X-2,
    N >=0,
    -impar( N ).

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
























