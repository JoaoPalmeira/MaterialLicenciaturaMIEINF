%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariantes

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: definicoes iniciais

:- op( 900,xfy,'::' ).
:- dynamic filho/2.
:- dynamic pai/2.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado filho: Filho,Pai -> {V,F,D}

filho( joao,jose ).
filho( jose,manuel ).
filho( carlos,jose ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado pai: Pai,Filho -> {V,F}

pai( paulo,filipe).
pai( paulo,maria).

pai( P,F ) :- filho( F,P ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado avo: Avo,Neto -> {V,F}

avo( antonio,nadia ).

avo( A,N ) :-filho( N,X ), pai( A,X ).

avo( A,N ) :- descendente( N,A,2 ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado neto: Neto,Avo -> {V,F}

neto( N,A ) :- avo( A,N ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado grau: Descendente,Ascendente,Grau -> {V,F}

descendente( D,A,1 ) :- filho( D,A ).

descendente( D,A,G ) :- 
	filho( D,X ), 
	descendente( X,A,N ),
	G is N+1.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+filho( F,P ) :: (solucoes( (F,P),(filho( F,P )),S ),
                  comprimento( S,N ), 
				  N == 1).

+pai(P,F) :: (solucoes( (P,F) , (pai(P,F)),S ), comprimento(S,N),
				N==1).

+neto(Nt,A) :: (solucoes( (Nt,A) , (neto(Nt,A)),S ), comprimento(S,N),
				N==1).

+avo(A,Nt) :: (solucoes( (A,Nt) , (avo(A,Nt)),S ), comprimento(S,N),
				N==1).

+descendente(D,A,G) :: (solucoes( (D,A,G),(descendente(D,A,G)),S), comprimento(S,N), N==1).

+descendente(D,A,G) :: G > 0

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Referencial: nao admitir mais do que 2 progenitores
%                         para um mesmo individuo

+filho( F,P ) :: (solucoes( (Ps),(filho(F,Ps)),S), comprimento(S,N),
					N=<2).

+pai(P,F) :: (solucoes( (Ps),(pai(Ps,F)),S ),comprimento(S,N),N=<2).

+neto(Nt,A) :: (solucoes((As),(neto(Nt,As)),S), comprimento(S,N),
					N=<4).

+avo(A,Nt) :: (solucoes( (As),(avo(As,Nt)),S),comprimento(S,N),N=<4).


listar(P,R) :- (solucoes( (F) , filho(F,P), R )).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado que permite a evolucao do conhecimento

insercao(T) :- assert(T).
insercao(T) :- retract(T),!,fail.

solucoes(T,Q,S) :- findall(T,Q,S).

teste([]).
teste([I|L]) :- I, teste(L).

comprimento([],0).
comprimento([X|L],R) :- comprimento(L,N) , R is 1+N.

evolucao( Termo ) :-
	solucoes( Invariante,+Termo::Invariante,Lista), 
				insercao(Termo), 
					teste(Lista).


