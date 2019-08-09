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
% Extensao do predicado filho: Filho,Pai -> {V,F}

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
% Extensao do predicado bisavo: Bisavo,Bisneto -> {V,F}

bisavo( B,Bn ) :- neto( Bn,X ), pai( B,X ).

bisavo( B,Bn ) :- descendente( Bn, B, 3 ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado trisavo: Trisavo,Trisneto -> {V,F}

trisavo( T,Tn ) :- descendente( Tn,T,4 ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado tetravo: Bisavo,Bisneto -> {V,F}

tretavo( Tr,Trn ) :- descendente( Trn,Tr,5 ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado descendente: Descendente,Ascendente -> {V,F}

descendente( D,A ) :- filho( D,A ).

descendente( D,A ) :- neto( D,A ).

descendente( D,A ) :- filho( D,Z ), descendente( Z,A ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado grau: Descendente,Ascendente,Grau -> {V,F}

descendente( D,A,1 ) :- filho( D,A ).

descendente( D,A,G ) :- 
	filho( D,X ), 
	descendente( X,A,N ),
	G is N+1.



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado masculino: Pessoa -> {V,F}

masculino( joao ).
masculino( jose ).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado feminino: Pessoa -> {V,F}

feminino( maria ).
feminino( joana ).



