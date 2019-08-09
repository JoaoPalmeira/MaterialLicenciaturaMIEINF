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

%----------------------- Base de Conhecimento ------------------------
%-------- Utente -----------------------------------------------------
% Utente: #idUt, Nome, Idade, Morada 

utente(3, joao, 21, braga).
utente(5, angelo, 22, braga).
utente(7, bruno, 22, viana).
utente(9, pedro, 21, barcelos).
utente(20, luis, 34, lisboa).
utente(21, josefina, 25, faro).

%-------- Prestador --------------------------------------------------
% Prestador: #Prest, Nome, Especialidade, Instituicao

prestador(5, tiago, ortopedia, hospitalViana).
prestador(2, fernando, oftalmologia, hospitalPorto).
prestador(32, jose, podologia, hospitalBraga).
prestador(14, francisco, cardiologia, hospitalBraga).
prestador(23, manuel, psiquiatria, hospitalPorto).
prestador(28, jorge, otorrinolaringologia, hospitalLisboa).

%-------- Cuidados ---------------------------------------------------
% Cuidado: Data, #idUt, #idPrest, Descricao, Custo

cuidado(01-03-2015, 5, 14, enfartedomiocardio, 60).
cuidado(15-12-2014, 7, 28, timpanofurado, 45).
cuidado(09-08-2016, 5, 14, colocacaopacemaker, 52).
cuidado(17-05-2013, 3, 23, criseexistencial, 56).
cuidado(23-01-2018, 21, 32, remocaodoutero, 65).
cuidado(16-02-2014, 9, 2, testedevisao, 15).
cuidado(03-06-2011, 20, 5, entorce, 41).


%---------------------- Predicados Solicitados -----------------------
%---------------------------------------------------------------------

%---------------------------------------------------------------------
%----- Registar utentes, prestadores e cuidados de saúde -------------
%---------------------------------------------------------------------


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Faz a insercao de conhecimento
% Extensão do predicado que permite a evolucao do conhecimento: Termo -> {V,F}

evolucao( Termo ) :- 
    findall( Invariante,+Termo::Invariante,Lista ),
    insercao( Termo ),
    teste( Lista ).

insercao( Termo ) :-
    assert( Termo ).
insercao( Termo ) :-
    retract( Termo ),!,fail.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Testa todos os elementos da lista
% Extensão do predicado teste: [R|LR] -> {V,F}

teste( [] ).
teste( [R|LR] ) :-
    R,
    teste( LR ).


%---------------------------------------------------------------------
%----- Remover utentes, prestadores e cuidados de saúde -------------
%---------------------------------------------------------------------

% Extensão do predicado que permite a remocao do conhecimento: Termo -> {V,F}    

inevolucao(Termo) :-
    findall( Invariante, -Termo::Invariante, Lista),
    remocao(Termo),
    teste(Lista).
    

remocao(Termo):- 
	retract(Termo).
remocao(Termo):-
	assert(Termo),!,fail.

%---------------------------------------------------------------------
%----- Identificar utentes por critérios de seleção ------------------
%---------------------------------------------------------------------

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica utentes pelo nome 
% Extensao do predicado listarUtenteN: N,S -> {V,F}      

listarUtenteN(N,S):-
    findall((X,N,Z,W),utente(X,N,Z,W),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica utentes pela Idade
% Extensao do predicado listarUtenteI: I,S -> {V,F}      

listarUtenteI(I,S):-
    findall((X,Y,I,W),utente(X,Y,I,W),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica utentes pela morada
% Extensao do predicado listarUtenteM: M,S -> {V,F}      

listarUtenteM(M,S):-
    findall((X,Y,Z,M),utente(X,Y,Z,M),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica utentes por nome e idade 
% Extensao do predicado listarUtenteNI: N,I,S -> {V,F}      

listarUtenteNI(N,I,S):-
    findall((X,N,I,W),utente(X,N,I,W),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica utentes por nome e morada
% Extensao do predicado listarUtenteNM: N,M,S -> {V,F}      

listarUtenteNM(N,M,S):-
    findall((X,N,Z,M),utente(X,N,Z,M),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica utentes por idade e morada
% Extensao do predicado listarUtenteIM: I,M,S -> {V,F}      

listarUtenteIM(I,M,S):-
    findall((X,Y,I,M),utente(X,Y,I,M),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica utentes por nome, idade e morada
% Extensao do predicado listarUtenteNIM: N,I,M,S -> {V,F}      

listarUtenteNIM(N,I,M,S):-
    findall((X,N,I,M),utente(X,N,I,M),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica utentes por id 
% Extensao do predicado listarUtenteID: ID,S -> {V,F}      

listarUtenteID(ID,S):-
    findall((ID,N,Idade,M),utente(ID,N,Idade,M),S).


%---------------------------------------------------------------------
%--- Identificar as instituições prestadoras de cuidados de saúde ----
%---------------------------------------------------------------------

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Identifica todas as instituições que prestam cuidados
% Extensao do predicado listarInstituicoes: S -> {V,F} 

listarInstituicoes( S ) :-
    findall( W,prestador(X,Y,Z,W),L ),
    removerRepetidos(L,S).


%---------------------------------------------------------------------
%--- Identificar cuidados de saúde prestados por instituição/ cidade /datas
%---------------------------------------------------------------------

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Lista os cuidados oferecidos por instituição
% Extensao do predicado cuidadosPorInstituicao: Instituicao,S -> {V,F} 

cuidadosPorInstituicao(I,S) :-
	findall(X,prestador(A,B,X,I),S).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece Ids dos utentes de uma dada cidade(morada)
% Extensao do predicado utenteMorada: M,S -> {V,F} 

utenteMorada(M,S) :-
	findall(X,utente(X,B,C,M),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece os cuidados prestados por cidade
% Extensao do predicado cuidadosCidade: I,S -> {V,F} 

cuidadosCidade(I,S) :-
	utenteMorada(I,U),
	cCaux(U,S).

cCaux([],[]).
cCaux([H|T],S) :-
	findall((A,H,C,D,E),cuidado(A,H,C,D,E),NL),
	concatenar(N,NL,S),
	cCaux(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Lista os cuidados prestados por Data 
% Extensao do predicado cuidadosData: Data,S -> {V,F} 

cuidadosData(Data,S) :-
  findall((Data,B,C,D,E),cuidado(Data,B,C,D,E),S).


%---------------------------------------------------------------------
%-- Identificar os utentes de um prestador/ especialidade /instituição
%---------------------------------------------------------------------

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece os utentes de um prestador
% Extensao do predicado utentesDeUmPrestador: I,S -> {V,F} 

utentesDeUmPrestador(I,S) :-
  findall(X,cuidado(A,X,I,D,E),NL), 
  removerRepetidos(NL,U),
  uPaux(U,S).

 uPaux([],[]).
 uPaux([H|T],S) :-
 	findall((H,Y,W,Z),utente(H,Y,W,Z),NL),
 	concatenar(N,NL,S),
 	uPaux(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece os Ids dos prestadores de uma dada especialidade
% Extensao do predicado prestadorEspecialidade: Especialidade,S -> {V,F} 

prestadorEspecialidade(I,S) :-
  findall((X),prestador(X,B,I,D),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece a lista de utentes de uma especialidade
% Extensao do predicado utentesEspecialidade: I,S -> {V,F} 

utentesEspecialidade(I,S) :-
  prestadorEspecialidade(I,X),
  utentesPrestador(X,U),
  removerRepetidos(U,Y),
  uEaux(Y,S).

  uEaux([],[]).
  uEaux([H|T],S) :-
    findall((H,Y,Z,W),utente(H,Y,Z,W),NL),
    concatenar(N,NL,S),
    uEaux(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   - 
% Fornece a lista dos Ids dos utentes que receberam cuidados dos prestadores cujos Ids estão contidos no Input
% Extensao do predicado utentesPrestador: ListaPrestadores,S -> {V,F} 

utentesPrestador([],[]).
utentesPrestador([H|T],S) :-
  findall(X,cuidado(A,X,H,D,E),NL),
  concatenar(N,NL,S),
  utentesPrestador(T,N).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece a lista de utentes de uma instituiçao
% Extensao do predicado utentesInstituicao: I,S -> {V,F} 6

utentesInstituicao(I,S) :-
  prestadoresInstituicao(I,X),
  utentesPrestador(X,U),
  removerRepetidos(U,L),
  uIaux(L,S).

uIaux([],[]).
uIaux([H|T],S) :-
	findall((H,Y,W,Z),utente(H,Y,Z,W),NL),
	concatenar(N,NL,S),
	uIaux(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece lista dos Ids dos prestadores da instituição em questão
% Extensao do predicado prestadoresInstituicao: Instituicao,S -> {V,F} 

prestadoresInstituicao(I,S) :-
  findall(X,prestador(X,Y,Z,I),S).


%---------------------------------------------------------------------
%-- Identificar cuidados de saúde realizados por utente/ instituição /prestador
%---------------------------------------------------------------------

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece a lista dos cuidados de que o utente usufruiu
% Extensao do predicado cuidadosUtente: IdUtente,S -> {V,F} 

cuidadosUtente(I,S) :-
   findall((A,I,C,D,E),cuidado(A,I,C,D,E),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece a lista de cuidados realizados por uma instituição
% Extensao do predicado cuidadosInstituicao: Instituição,S -> {V,F}

cuidadosInstituicao(I,S) :-
  prestadoresInstituicao(I,X),
  icuidados(X,S).

icuidados([],[]).
icuidados([H|T],S) :-
  findall((X,Y,H,W,Z),cuidado(X,Y,H,W,Z),NL),
  concatenar(N,NL,S),
  icuidados(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece a lista de cuidados realizados por um prestador
% Extensao do predicado cuidadosPrestador: IdPrestador,S -> {V,F} 

cuidadosPrestador(I,S) :-
  findall((A,B,I,D,E),cuidado(A,B,I,D,E),S).


%---------------------------------------------------------------------
%-- Determinar todas as instituições/prestadores a que um utente já recorreu
%---------------------------------------------------------------------


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece lista de instituições a que um utente recorreu
% Extensao do predicado instituicoesUtente: IdUtente,S -> {V,F} 

instituicoesUtente(I,S) :-
  idPrestadoresUtente(I,X),
  iu(X,S).

iu([],[]).
iu([H|T],S) :-
  findall(X,prestador(H,B,C,X),NL),
  concatenar(N,NL,S),
  iu(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece lista de Ids dos prestadores a que um utente recorreu
% Extensao do predicado idPrestadoresUtente: IdUtente,S -> {V,F} 

idPrestadoresUtente(I,S) :-
	findall(X,cuidado(A,I,X,D,E),U),
	removerRepetidos(U,S).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece lista de prestadores a que um utente recorreu
% Extensao do predicado prestadoresUtente: IdUtente,S -> {V,F} 

prestadoresUtente(I,S) :-
  findall(X,cuidado(A,I,X,D,E),NL),
  removerRepetidos(NL,U),
  pUaux(U,S).

pUaux([],[]).
pUaux([H|T],S) :-
	findall((H,Y,W,Z),prestador(H,Y,W,Z),NL),
	concatenar(N,NL,S),
	pUaux(T,N).


%---------------------------------------------------------------------
%-- Calcular o custo total dos cuidados de saúde por utente/ especialidade/ prestador/datas
%---------------------------------------------------------------------


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Custo total associado a um utente
% Extensao do predicado custoUtente: IdUtente,S -> {V,F}        

custoUtente(I,S) :-
  findall(E,cuidado(A,I,C,D,E),NL),
  somatorio(NL,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece lista dos ids dos prestadores de uma dada especialidade 
% Extensao do predicado prestadoresEspecialidade: Especialidade,S -> {V,F}        

prestadoresEspecialidade(I,S) :-
    findall(X,prestador(X,B,I,D),S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Custo total associado a uma especialidade
% Extensao do predicado custoEspecialidade: Especialidade,S -> {V,F}        

custoEspecialidade(I,S) :-
  prestadoresEspecialidade(I,X),
  cEaux(X,Y),
  somatorio(Y,S).

cEaux([],[]).
cEaux([H|T],S) :-
  findall(X,cuidado(A,B,H,D,X),NL),
  concatenar(N,NL,S),
  cEaux(T,N).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Custo total associado a um prestador
% Extensao do predicado custoPrestador: IdPrestador,S -> {V,F}        

custoPrestador(I,S) :-
  findall(X,cuidado(A,B,I,D,X),NL),
  somatorio(NL,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Custo total associado a uma data
% Extensao do predicado custoData: Data,S -> {V,F}        

custoData(I,S) :-
  findall(X,cuidado(I,B,C,D,X),NL),
  somatorio(NL,S).


%------------------- Novas Funcionalidades ---------------------------
%---------------------------------------------------------------------


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Média de idade de todos os utentes
% Extensao do predicado mediaIdades_utentes: S -> {V,F}        

mediaIdades_utentes(S) :-
  findall(I,utente(_,_,I,_),S1),
  media(S1,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Dá a media de idades correspondentes aos utentes com a morada dada
% Extensao do predicado mediaIdades_morada: Morada,S -> {V,F}  

mediaIdade_morada(M,S) :- 
  findall(Id, utente(_,_,Id,M), S1),
  media(S1,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Dá o numero de prestadores correspondente a uma instituiçao
% Extensao do predicado prestadoresN_Instistuicao: Instituicao,S -> {V,F} 

prestadoresN_Instituicao(I,S) :-
  findall(P,prestador(_,P,_,I),R),
  comprimento(R,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece o número de utentes por instituição
% Extensao do predicado nUtentesInstituicao Instituicao,S -> {V,F} 

nUtentesInstituicao(I,S) :-
  prestadoresInstituicao(I,X),
  utentesPrestador(X,U),
  removerRepetidos(U,L),
  comprimento(L,S).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Fornece o número de cuidados por instituição
% Extensao do predicado nCuidadosInstituicao Instituicao,S -> {V,F} 

nCuidadosInstituicao(I,S) :-
  prestadoresInstituicao(I,X),
  nCuidadosPrestador(X,C),
  comprimento(C,S).

nCuidadosPrestador([],[]).
nCuidadosPrestador([H|T],S) :-
  findall(X,cuidado(X,B,H,D,E),NL),
  concatenar(N,NL,S),
  nCuidadosPrestador(T,N).

%---------------------- Invariantes ----------------------------------
%---------------------------------------------------------------------

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
% Invariante Estrutural:  nao permitir a insercao de custo negativo

+cuidado(X,Y,Z,W,W1) :: (findall(W1,cuidado(A,B,C,D,W1),S),
                 naoNegativo(S)
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
% Invariante Estrutural:  nao permitir a remoção de utente caso exista cuidado associado

-utente(X,Y,W,Z) :: (findall(X, cuidado(A,X,C,D,E),S),
		    comprimento(S,N),
		    N == 0).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariante Estrutural:  nao permitir a remoção de prestador caso exista cuidado associado

-prestador(X,Y,W,Z) :: (findall(X, cuidado(A,B,X,D,E), S),
			comprimento(S,N),
			N == 0).


%---------------------- Predicados Auxiliares ------------------------
%---------------------------------------------------------------------


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Verifica se todos os elementos da lista são não negativos
% Extensao do predicado naoNegativo: L -> {V,F}  

naoNegativo([]).
naoNegativo([H|T]) :- H>=0,
                      naoNegativo(T).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Verifica se contem um elemento numa dado lista
% Extensão do predicado contem: H,[H|T] -> {V, F}

contem(H, [H|T]).
contem(X, [H|T]) :-
    contem(X, T).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% concatena duas listas
% Extensao do predicado concatenar: L1,L2,L3 -> {V,F}  

concatenar( [],L,L ).
concatenar( [H|T],L2,[H|L] ) :- concatenar(T,L2,L).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% remove um elemento de uma lista
% Extensao do predicado removerElemento: L1, Y, L2 -> {V,F}

removerElemento( [],_,[] ).

removerElemento( [X|L],X,NL ) :-      
    removerElemento( L,X,NL ).

removerElemento( [X|L],Y,[X|NL] ) :-
    X \== Y, removerElemento( L,Y,NL ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% remove os elementos repetidos de uma lista
% Extensao do predicado removerRepetidos: L1, L2 -> {V,F} 

removerRepetidos( [],[] ).
removerRepetidos( [X|L],[X|NL] ) :-
    removerElemento( L,X,TL ), removerRepetidos( TL,NL ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Soma os elementos de uma lista
% Extensao do predicado somatorio: L,R -> {V,F}  

somatorio( [],0 ).
somatorio( [H|T],R ) :- somatorio(T,N),
                        R is H+N.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Faz a media de uma lista
% Extensao do predicado media: L,R -> {V,F}

media([H|T],S) :-
  somatorio([H|T],S1),comprimento([H|T],S2), S is S1/S2.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado comprimento: L,R -> {V,F}       

comprimento([],0).
comprimento([H|T],R) :- comprimento(T,N),
                        R is N+1.







