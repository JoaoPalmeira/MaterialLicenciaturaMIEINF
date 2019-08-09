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
% Extensao do predicado jogo: Id, árbitro, Ajudas de Custo ->{ V,F,D }

-jogo(Id,Arb,AjC) :- nao(jogo(Id,Arb,AjC)), nao(excecao(jogo(Id,Arb,AjC))).


% i. ---------------------------------------------

jogo(1,aa,500).


% ii. ---------------------------------------------
% -- incerto( NAO consigo limitar as duvidas);

jogo(2,bb,xpto2).

excecao( jogo(Id,Arb,Ajc) ) :- jogo(Id,Arb,xpto2).


% iii. ---------------------------------------------
% -- impreciso ( SIM, consigo limitar as duvidas);

excecao(jogo(3,cc,500)).
excecao(jogo(3,cc,2500)).

% iv. ---------------------------------------------
% -- impreciso

excecao(jogo(4,dd,Valor)) :- Valor > 250.
excecao(jogo(4,dd,Valor)) :- Valor < 750.



% v. ---------------------------------------------
% -- interdito

jogo(5,ee,xpto5).

excecao(jogo(Id,Arb,Ajc)) :- jogo(Id,Arb,xpto5).

nulointerdito(xpto5).

+jogo(Id,Arb,Ajc) :: solucoes((Id,Arb,Ajcs), jogo(5,ee,Ajcs), nao(nulointerdito(Ajcs),S)), comprimento(S,N), N==0. 

% vi. ---------------------------------------------
% -- CP

jogo(6,ff,250).

% -- impreciso
excecao(6,ff,Valor) :- Valor > 5000.



% vii. ---------------------------------------------
% -- CP

-jogo(7,gg,2500).

% -- incerto

jogo(7,gg,xpto7).
excecao(jogo(Id,Arb,Ajc)) :- jogo(Id,Arb,xpto7).


% viii. ---------------------------------------------
% -- impreciso -> DUVIDA

excecao(jogo(8,hh,Valor)) :- Valor > 950.
excecao(jogo(8,hh,Valor)) :- Valor < 1000.

% ix. ---------------------------------------------
% -- impreciso -> DUVIDA

excecao(jogo(9,dd,Valor)) :- Valor > 2950.
excecao(jogo(9,dd,Valor)) :- Valor < 3000.


% x. ---------------------------------------------

+jogo(ID,_,_) :: solucoes( (ID), jogo(ID,_,_), S), comprimento(S,N), N==1.

% xi. ---------------------------------------------

+jogo(_,Arb,_) :: solucoes((ID), jogo(ID,Arb,_),S), comprimento(S,N), N=<3.

% xii. ---------------------------------------------

+jogo(IDA,Arb,_) :: solucoes((ID), jogo(ID,Arb,_),S), consecutivos(IDA,S,N), N=<2.


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
% Extensao do predicado comprimento: Lista, Resultado -> {V,F}

comprimento([],0).
comprimento([X|L],R) :- comprimento(L,N) , R is 1+N.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado consecutivos: ID,Lista, Resultado -> {V,F}

consecutivos(_,[],0).
consecutivos(ID,[X|L],R) :- X>ID, X-ID==1, consecutivos(ID,L,N), R is N+1.
consecutivos(ID,[X|L],R) :- ID>X, ID-X==1, consecutivos(ID,L,N), R is N+1.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado solucoes: Termo,Questao, olucao -> {V,F}

solucoes(T,Q,S) :- findall(T,Q,S).



