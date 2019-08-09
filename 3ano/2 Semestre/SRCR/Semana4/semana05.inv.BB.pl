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

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+filho( F,P ) :: (solucoes((F,P),(filho(F,P)),S),
                  comprimento(S,N), 
				  N==1).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+pai(P,F) :: (solucoes((P,F),(pai(P,F)),S),
                  comprimento(S,N), 
				  N==1).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+neto(N,A) :: (solucoes((N,A),(neto(N,A)),S),
                  comprimento(S,N), 
				  N==1).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+avo(A,N) :: (solucoes((A,N),(avo(A,N)),S),
                  comprimento(S,N), 
				  N==1).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+descendente(D,A) :: (solucoes((D,A),(descendente(D,A)),S),
                  comprimento(S,N), 
				  N==1).

% Invariante Referencial: nao admitir mais do que 2 progenitores
%                         para um mesmo individuo

+filho(F,P) :: (solucoes(Ps),(filho(F,Ps)),S),
				   comprimento(S,N),
				   N<=2 ).

% Invariante Referencial: nao admitir mais do que 2 progenitores
%                         para um mesmo individuo

+pai(P,F) :: (solucoes(Ps),(filho(F,Ps)),S),
				   comprimento(S,N),
				   N<=2 ).

% Invariante Referencial: nao admitir mais do que 4 avôs
%                         para um mesmo individuo

+neto(N,A) :: (solucoes(As),(neto(N,As)),S),
				   comprimento(S,N),
				   N<=4).

% Invariante Referencial: nao admitir mais do que 4 avôs
%                         para um mesmo individuo

+avo(A,N) :: (solucoes(As),(neto(N,As)),S),
				   comprimento(S,N),
				   N<=4).

% Invariante Referencial: nao admitir mais do que 4 avôs
%                         para um mesmo individuo

+descendente(D,A) :: (solucoes(D,A),(grau(D,A,G)),S),
				   G>=0).

% Invariante Referencial: nao é possivel remover filhos para os 
%							quais existe registo de idade

-filho(F,P) :: ( solucoes(Ps),(idade(F,I)),S),
				   comprimento(S,N),
				   N==0 ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado que permite a evolucao do conhecimento

evolucao( Termo ) :- 
		solucoes(Invariante,+Termo::Invariante,Lista),
		insercao(Termo),
		teste(Lista).


