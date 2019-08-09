%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% TRABALHO PRÁTICO: EXERCÍCIO 01    2017/18

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

:- op( 900,xfy,'::' ).
:- dynamic utente/4.
:- dynamic prestador/4.
:- dynamic cuidado/5.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado que permite a evolucao do conhecimento: P -> {V,F}    1

evolucao( Termo ) :-
    findall( Invariante,+Termo::Invariante,Lista ),
    insercao( Termo ),
    teste( Lista ).

insercao( Termo ) :-
    assert( Termo ).
insercao( Termo ) :-
    retract( Termo ),!,fail.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado teste: [H|T] -> {V,F}      1,2

teste( [] ).
teste( [R|LR] ) :-
    R,
    teste( LR ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado que permite a remocao do conhecimento: T -> {V,F}    2

remover(Termo) :-
    findall( Invariante, -Termo::Invariante, Lista),
    teste(Lista),
    retract(Termo).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado comprimento: L,R -> {V,F}       

comprimento([],0).
comprimento([H|T],R) :- comprimento(T,N),
                        R is N+1.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Estrutural:  nao permitir a insercao de numero de utente repetido

+utente( X,Y,Z,W ) :: (findall( X,(utente( X,B,C,D )),S ),
                  comprimento( S,N ), 
                  N == 1
                  ).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Estrutural:  nao permitir a inserçao de número de prestador repetido

+prestador( X,Y,Z,W ) :: (findall(X, (prestador( X,B,C,D )),S ),
                  comprimento( S,N ), 
                  N == 1
                  ).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Estrutural:  nao permitir a insercao de cuidado repetido

+cuidado( X,Y,Z,W,W1 ) :: (findall( (X,Y,Z,W,W1),(cuidado( X,Y,Z,W,W1 )),S ),
                  comprimento( S,N ), 
                  N == 1
                  ).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Estrutural:  nao permitir a insercao de cuidado com utente inexistente

+cuidado( X,Y,Z,W,W1 ) :: (findall( A,(utente( A,B,C,D )),S ),
                    contem( Y,S)
                    ).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Estrutural:  nao permitir a insercao de cuidado com prestador inexistente

+cuidado( X,Y,Z,W,W1 ) :: (findall( A,(prestador( A,B,C,D )),S ),
                    contem( Z,S)
                    ).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Estrutural:  nao permitir a insercao de custo negativo

+cuidado(X,Y,Z,W,W1) :: (findall(W1,cuidado(A,B,C,D,W1),S),
                    naoNegativo(S)
                    ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado naoNegativo: L -> {V,F}  

naoNegativo([]).
naoNegativo([H|T]) :- H>=0,
                      naoNegativo(T).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado contem: H,[H|T] -> {V, F}

contem(H, [H|T]).
contem(X, [H|T]) :-
    contem(X, T).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarUtenteN: N,S -> {V,F}      3

listarUtenteN(N,S):-
    findall(X,utente(X,N,Z,W),S).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarUtenteI: I,S -> {V,F}      3

listarUtenteI(I,S):-
    findall(X,utente(X,Y,I,W),S).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarUtenteM: M,S -> {V,F}      3

listarUtenteM(M,S):-
    findall(X,utente(X,Y,Z,M),S).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarUtenteNI: N,I,S -> {V,F}      3

listarUtenteNI(N,I,S):-
    findall(X,utente(X,N,I,W),S).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarUtenteNM: N,M,S -> {V,F}      3

listarUtenteNM(N,M,S):-
    findall(X,utente(X,N,Z,M),S).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarUtenteIM: I,M,S -> {V,F}      3

listarUtenteIM(I,M,S):-
    findall(X,utente(X,Y,I,M),S).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarUtenteNIM: N,I,M,S -> {V,F}      3

listarUtenteNIM(N,I,M,S):-
    findall(X,utente(X,N,I,M),S).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarUtenteID: ID,S -> {V,F}      3

listarUtenteID(ID,S):-
    findall((ID,N,Idade,M),utente(ID,N,Idade,M),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado listarInstituicoes: S -> {V,F} 4 

listarInstituicoes( S ) :-
    findall( W,prestador(X,Y,Z,W),S ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado concatenar: L1,L2,L3 -> {V,F}  

concatenar( [],L,L ).
concatenar( [H|T],L2,[H|L] ) :- concatenar(T,L2,L).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado prestadorInstituicao: I,S -> {V,F} 5

prestadorInstituicao( I,S ) :-
    findall( X ,prestador(X,Y,Z,I),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado cuidadosInstituicao: Instituicao,S -> {V,F} 5

cuidadosInstituicao(I,S) :-
	prestadorInstituicao(I,X),
	icuidados(X,S).

icuidados([],[]).
icuidados([H|T],S) :-
	findall((X,Y,H,W,Z),cuidado(X,Y,H,W,Z),NL),
	concatenar(N,NL,S),
	icuidados(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado cuidadosData: Data,S -> {V,F} 5

cuidadosData(Data,S) :-
	findall((Data,B,C,D,E),cuidado(Data,B,C,D,E),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado removerElemento: L1, Y, L2 -> {V,F}

removerElemento( [],_,[] ).

removerElemento( [X|L],X,NL ) :-
    removerElemento( L,X,NL ).

removerElemento( [X|L],Y,[X|NL] ) :-
    X \== Y, removerElemento( L,Y,NL ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado removerRepetidos: L1, L2 -> {V,F} 

removerRepetidos( [],[] ).
removerRepetidos( [X|L],[X|NL] ) :-
    removerElemento( L,X,TL ), removerRepetidos( TL,NL ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado utentesDeUmPrestador: I,S -> {V,F} 6

utentesDeUmPrestador(I,S) :-
	findall(X,cuidado(A,X,I,D,E),NL).
	removerRepetidos(NL,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado prestadorEspecialidade: I,S -> {V,F} 6

prestadorEspecialidade(I,S) :-
	findall(X,prestador(X,B,I,D),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado utentesPrestador: ListaPrestadores,S -> {V,F} 

utentesPrestador([],[]).
utentesPrestador([H|T],S) :-
	findall(X,cuidado(A,X,H,D,E),NL),
	concatenar(N,NL,S),
	utentesPrestador(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado utentesEspecialidade: I,S -> {V,F} 6

utentesEspecialidade(I,S) :-
	prestadorEspecialidade(I,X),
	utentesPrestador(X,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado prestadorInstituicao: Instituicao,S -> {V,F} 6
prestadorInstituicao(I,S) :-
	findall(X,prestador(X,Y,Z,I),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado utentesInstituicao: I,S -> {V,F} 6

utentesInstituicao(I,S) :-
	prestadorInstituicao(I,X),
	utentesPrestador(X,S).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado cuidadoUtente: IdUtente,S -> {V,F} 7

cuidadoUtente(I,S) :-
	findall((A,I,C,D,E),cuidado(A,I,C,D,E),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado cuidadoPrestador: IdPrestador,S -> {V,F} 7

cuidadoPrestador(I,S) :-
	findall((A,B,I,D,E),cuidado(A,B,I,D,E),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado prestadorUtente: IdUt,S -> {V,F} 8

prestadorUtente(I,S) :-
	findall(X,cuidado(A,I,X,D,E),NL),
	removerRepetidos(NL,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado instituicoesUtente: IdUt,S -> {V,F} 8

instituicoesUtente(I,S) :-
	prestadorUtente(I,X),
	iu(X,S).

iu([],[]).
iu([H|T],S) :-
	findall(X,prestador(H,B,C,X),NL),
	concatenar(N,NL,S),
	iu(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado somatorio: L,R -> {V,F}  

somatorio( [],0 ).
somatorio( [H|T],R ) :- somatorio(T,N),
                        R is H+N.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado custoUtente: IdUt,S -> {V,F}        9

custoUtente(I,S) :-
	findall(E,cuidado(A,I,C,D,E),NL),
	somatorio(NL,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado prestadorEspecialidade: Especialidade,S -> {V,F}        9

prestadorEspecialidade(I,S) :-
	findall(X,prestador(X,B,I,D),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado custoEspecialidade: Especialidade,S -> {V,F}        9

custoEspecialidade(I,S) :-
	prestadorEspecialidade(I,X),
	cEaux(X,Y),
	somatorio(Y,S).

cEaux([],[]).
cEaux([H|T],S) :-
	findall(X,cuidado(A,B,H,D,X),NL),
	concatenar(N,NL,S),
	cEaux(T,N).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado custoPrestador: Prestador,S -> {V,F}        9

custoPrestador(I,S) :-
	findall(X,cuidado(A,B,I,D,X),NL),
	somatorio(NL,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado custoData: Data,S -> {V,F}        9

custoData(I,S) :-
	findall(X,cuidado(I,B,C,D,X),NL),
	somatorio(NL,S).
