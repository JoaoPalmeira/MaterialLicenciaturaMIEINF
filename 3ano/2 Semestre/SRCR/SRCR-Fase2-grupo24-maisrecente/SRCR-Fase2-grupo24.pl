%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% TRABALHO PRÁTICO: EXERCÍCIO 02    2017/18

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: definicoes iniciais

:- op( 900,xfy,'::' ).
:- dynamic utente/4.
:- dynamic prestador/4.
:- dynamic cuidado/5.
:- dynamic excecao/1.
:- dynamic nulo/1.
:- dynamic '-'/1.


%---------------------  BASE DE CONHECIMENTO  ------------------------
%---------------------------------------------------------------------


%------------- UTENTE ------------------------------------------------
% UTENTE:  #IdUt, Nome, Idade, Morada


%----------------------- Conhecimento Perfeito -----------------------

%----Positivo

utente(1, joao, 22, ruaJoao).
utente(3, rafael, 21, ruaRafael).
utente(4, luis, 20, ruaLuis).

%----Negativo

-utente(2, angelo, 22, ruaAngelo).
-utente(6, joel, 23, ruaJoel).
-utente(5, frederico, 30, ruaFred).


%------------------ Conhecimento Imperfeito Incerto ------------------

utente(7,desconhecido01,20,ruaNova).
excecao(utente(X,Y,W,Z)) :-
    utente(X,desconhecido01,W,Z).


%------------------ Conhecimento Imperfeito Impreciso ------------------

excecao(utente(8,pedro,22,ruaP)).
excecao(utente(8,paulo,22,ruaP)).


%------------------ Conhecimento Imperfeito Interdito ------------------

utente(9,proibido01,21,ruaVerde).
excecao(utente(X,Y,W,Z)) :-
    utente(X,proibido01,W,Z).
nulo(proibido01).
+utente(X,Y,W,Z) :: (findall((X,Nome,W,Z),(utente(9,Nome,21,ruaVerde),nao(nulo(Nome))),S),
            comprimento(S,N),N==0).

utente(10,roberto,proibido04,ruaAzul).
excecao(utente(X,Y,W,Z)) :-
    utente(X,Y,proibido04,Z).
nulo(proibido04).
+utente(X,Y,W,Z) :: (findall((X,Y,Idade,Z),(utente(10,roberto,Idade,ruaAzul),nao(nulo(Idade))),S),
            comprimento(S,N),N==0).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado utente: X,Y,Z,W -> {V,F,D}

-utente( X,Y,Z,W ) :-
    nao( utente( X,Y,Z,W ) ),
    nao( excecao( utente( X,Y,Z,W ) ) ).


%------------- PRESTADOR ---------------------------------------------
% PRESTADOR:  #IdPrest, Nome, Especialidade, Instituição


%----------------------- Conhecimento Perfeito -----------------------

%----Positivo

prestador(1, bruno, ortopedia, hospitalBraga).
prestador(5, tiago, oftamologia, hospitalViana).
prestador(4, fernando, cardiologia, hospitalPorto).

%----Negativo

-prestador(2, antonio, cardiologia, hospitalChaves).
-prestador(32, jose, otorrinolaringolista, hospitalLisboa).
-prestador(23, manuel, psiquiatria, hospitalPorto).


%------------------ Conhecimento Imperfeito Incerto ------------------

prestador(7,desconhecido02,oftamologia,hospitalPorto).
excecao(prestador(X,Y,W,Z)) :-
    prestador(X,desconhecido02,W,Z).


%------------------ Conhecimento Imperfeito Impreciso ----------------

excecao(prestador(9,joao,22,hospitalBraga)).
excecao(prestador(9,jose,22,hospitalBraga)).


%------------------ Conhecimento Imperfeito Interdito -----------------

prestador(9,proibido02,cardiologia,hospitalViana).
excecao(prestador(X,Y,Z,W)) :-
    prestador(X,proibido02,Z,W).
nulo(proibido02).
+prestador(X,Y,W,Z) :: (findall((X,Nome,W,Z),(prestador(9,Nome,cardiologia,hospitalViana),nao(nulo(Nome))),S),
            comprimento(S,N),N==0).

prestador(10,rui,proibido05,hospitalBraga).
excecao(prestador(X,Y,Z,W)) :-
    prestador(X,Y,proibido05,W).
nulo(proibido05).
+prestador(X,Y,W,Z) :: (findall((X,Y,Especialidade,Z),(prestador(10,rui,Especialidade,ruaVerde),nao(nulo(Especialidade))),S),
            comprimento(S,N),N==0).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado prestador: X,Y,Z,W -> {V,F,D}

-prestador( X,Y,Z,W ) :-
    nao( prestador( X,Y,Z,W ) ),
    nao( excecao( prestador( X,Y,Z,W ) ) ).


%------------- CUIDADO ---------------------------------------------
% CUIDADO:   Data, #IdUt, #IdPrest, Descrição, Custo


%----------------------- Conhecimento Perfeito ---------------------

%----Positivo

cuidado(10-10-2018, 1, 1, entorce, 34).
cuidado(15-12-2014, 3, 5, testedevisao, 45).
cuidado(17-05-2013, 4, 4, enfartedomiocardio, 60).

%----Negativo

-cuidado(23-01-2018, 5, 32, timpanofurado, 58).
-cuidado(10-10-2018, 2, 2, colocacaopacemaker, 150).
-cuidado(03-06-2011, 6, 23, criseexistencial, 80).


%------------------ Conhecimento Imperfeito Incerto ------------------

cuidado(10-5-2018,desconhecido03,1,entorce,100). 
excecao(cuidado(X,Y,Z,W,V)) :-
    cuidado(X,desconhecido03,Z,W,V).


%------------------ Conhecimento Imperfeito Impreciso ------------------

excecao(cuidado(2018,7,7,dorDeCabeca,250)).
excecao(cuidado(2018,8,7,dorDeCabeca,250)).


%------------------ Conhecimento Imperfeito Interdito ------------------


cuidado(2018,proibido03,1,pePartido,500).
excecao(cuidado(X,Y,Z,W,V)) :-
    cuidado(X,proibido03,Z,W,V).
nulo(proibido03).
+cuidado(X,Y,W,Z,V) :: (findall((X,IdUt,W,Z,V),(cuidado(2018,IdUt,1,pePartido,500),nao(nulo(IdUt))),S),
            comprimento(S,N),N==0).

cuidado(2018,3,1,pePartido,proibido06).
excecao(cuidado(X,Y,Z,W,V)) :-
    cuidado(X,Y,Z,W,proibido06).
nulo(proibido06).
+cuidado(X,Y,W,Z,V) :: (findall((X,Y,W,Z,Custo),(cuidado(2018,3,1,pePartido,Custo),nao(nulo(Custo))),S),
            comprimento(S,N),N==0).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado cuidado: X,Y,Z,W,V -> {V,F,D}

-cuidado( X,Y,Z,W,V ) :-
    nao( cuidado( X,Y,Z,W,V ) ),
    nao( excecao( cuidado( X,Y,Z,W,V ) ) ).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado que permite a evolucao do conhecimento imperfeito incerto: Termo -> {V,F}

evolucaoDesconhecidoUtente( utente(X,Y,Z,W) ) :-
    evolucao( utente(X,Y,Z,W) ),
    assert( (excecao( utente( A,B,C,D ) ) :-
                    utente( A,Y,C,D  ))
           ).

evolucaoDesconhecidoPrestador( prestador(X,Y,Z,W) ) :-
    evolucao( prestador(X,Y,Z,W) ),
    assert( (excecao( prestador( A,B,C,D) ) :-
                    prestador( A,Y,C,D))
           ).

evolucaoDesconhecidoCuidado( cuidado(X,Y,Z,W,V) ) :-
    evolucao( cuidado(X,Y,Z,W,V) ),
    assert( (excecao( cuidado( A,B,C,D,E ) ) :-
                    cuidado( A,Y,C,D,E ))
           ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado que permite a evolucao do conhecimento imperfeito impreciso: Termo -> {V,F}

evolucaoImpreciso([]).
evolucaoImpreciso([T| L]) :-
    evolucao(excecao(T)),
    evolucaoImpreciso(L).


%--------------------------  INVARIANTES  ----------------------------
%---------------------------------------------------------------------

%--------------- Conhecimento Perfeito Positivo e Desconhecido ---------------

% -> Não permitir adicionar quando se tem o conhecimento perfeito negativo oposto

+utente(X,Y,Z,W) :: nao( -utente(X,Y,Z,W) ).

+prestador(X,Y,Z,W) :: nao( -prestador(X,Y,Z,W) ).

+cuidado(X,Y,Z,W,V) :: nao( -cuidado(X,Y,Z,W,V) ).

%--------------- Conhecimento Perfeito Negativo ---------------

% -> Não permitir adicionar se houver o conhecimento positivo perfeito oposto.

+(-T) :: nao( T ).

% -> Não permitir adicionar conhecimento negativo repetido

+(-T) :: (findall( T,(-T),S),
                    comprimento(S,N),
                    N < 2).



% -> impossível adicionar excecoes a conhecimento perfeito positivo

+excecao( Termo ) :: nao( Termo ).


% -> Nao permitir adicionar conhecimento perfeito positivo repetido, removendo conhecimento desconhecido, se este existir.

+utente(X,Y,Z,W) :: verificaPerfeitaEvoUtente(X,Y,Z,W).

verificaPerfeitaEvoUtente(X,Y,Z,W) :-
    findall( utente(X,B,Z,W),utente(X,B,Z,W),S),
    removeDesconhecidoUtente(S).

verificaPerfeitaEvoUtente(X,Y,Z,W) :-
    findall( (X,Y,Z,W),utente(X,Y,Z,W),S),
    comprimento( S,N ),
    N == 1.

removeDesconhecidoUtente( [ utente(X,Y,Z,W)] ) :-
    demo(utente(X,e,Z,W),desconhecido),
    removeTermos( [utente(X,Y,Z,W),(excecao(utente(A,B,C,D)):- utente(A,Y,C,D))] ).

removeDesconhecidoUtente( [utente(X,Y,Z,W)|L] ) :-
    demo(utente(X,e,Z,W),desconhecido),
    removeTermos( [utente(X,Y,Z,W),(excecao(utente(A,B,C,D)):- utente(A,Y,C,D))] ).
    
removeDesconhecidoUtente( [X|L] ) :-
    removeDesconhecidoUtente( L ).

%--------------

+prestador(X,Y,Z,W) :: verificaPerfeitaEvoPrestador(X,Y,Z,W).

verificaPerfeitaEvoPrestador(X,Y,Z,W) :-
    findall( prestador(X,B,Z,W),prestador(X,B,Z,W),S),
    removeDesconhecidoPrestador(S).

verificaPerfeitaEvoPrestador(X,Y,Z,W) :-
    findall( (X,Y,Z,W),prestador(X,Y,Z,W),S),
    comprimento( S,N ),
    N == 1.

removeDesconhecidoPrestador( [prestador(X,Y,Z,W)] ) :-
    demo(prestador(X,e,Z,W),desconhecido),
    removeTermos( [prestador(X,Y,Z,W),(excecao(prestador(A,B,C,D)):- prestador(A,Y,C,D))] ).

removeDesconhecidoPrestador( [prestador(X,Y,Z,W)|L] ) :-
    demo(prestador(X,e,Z,W),desconhecido),
    removeTermos( [prestador(X,Y,Z,W),(excecao(prestador(A,B,C,D)):- prestador(A,Y,C,D))] ).

removeDesconhecidoPrestador( [X|L] ) :-
    removeDesconhecidoPrestador( L ).

%---------------

+cuidado(X,Y,Z,W,V) :: verificaPerfeitaEvoCuidado(X,Y,Z,W,V).

verificaPerfeitaEvoCuidado(X,Y,Z,W,V) :-
    findall( cuidado(X,B,Z,W,V),cuidado(X,B,Z,W,V),S),
    removeDesconhecidoCuidado(S).

verificaPerfeitaEvoCuidado(X,Y,Z,W,V) :-
    findall( (X,Y,Z,W,V),cuidado(X,Y,Z,W,V),S),
    comprimento( S,N ),
    N == 1.


removeDesconhecidoCuidado( [cuidado(X,Y,Z,W,V)] ) :-
    demo(cuidado(X,e,Z,W,V),desconhecido),
    removeTermos( [cuidado(X,Y,Z,W,V),(excecao(cuidado(A,B,C,D,E)):- cuidado(A,Y,C,D,E))] ).

removeDesconhecidoCuidado( [cuidado(X,Y,Z,W,V)|L] ) :-
    demo(cuidado(X,e,Z,W,V),desconhecido),
    removeTermos( [cuidado(X,Y,Z,W,V),(excecao(cuidado(A,B,C,D,E)):- cuidado(A,Y,C,D,E))] ).

removeDesconhecidoCuidado( [X|L] ) :-
    removeDesconhecidoCuidado( L ).

%--------------- Conhecimento Imperfeito Impreciso -------------

% -> Apenas deixar adicionar conhecimento positivo se este pertence ao conjunto de conhecimento impreciso e remover o conhecimento impreciso.


%---------------------------------------------------------------

+utente( X,Y,Z,W) :: (findall( B,excecao(utente(X,B,Z,W)),S),
                        contem(Y,S),
                        findall(excecao(utente(X,B,Z,W)),excecao(utente(X,B,Z,W)),S2),
                        removeTermos(S2)
                    ).

%---------------------------------------------------------------

+prestador( X,Y,Z,W) :: (findall( B,excecao(prestador(X,B,Z,W)),S),
                        contem(Y,S),
                        findall(excecao(prestador(X,B,Z,W)),excecao(prestador(X,B,Z,W)),S2),
                        removeTermos(S2)
                    ).

%---------------------------------------------------------------

+cuidado( X,Y,Z,W,V) :: (findall( B,excecao(cuidado(X,B,Z,W,V)),S),
                        contem(Y,S),
                        findall(excecao(cuidado(X,B,Z,W,V)),excecao(cuidado(X,B,Z,W,V)),S2),
                        removeTermos(S2)
                    ).

%--------------- Conhecimento Desconhecido --------------------

+(excecao(T)) :: (findall( excecao(T),excecao(T),S),
                    comprimento(S,N),
                    N < 2).

+(excecao(-T)) :: (findall( excecao(T),excecao(-T),S),
                    comprimento(S,N),
                    N < 2).


%--------------------  PREDICADOS AUXILIARES -------------------------
%---------------------------------------------------------------------

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado que permite a evolucao do conhecimento perfeito: Termo -> {V,F}

evolucao( Termo ) :-
    findall( Invariante,+Termo::Invariante,Lista ),
    insercao( Termo ),
    teste( Lista ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado insercao: Termo -> {V, F}

insercao( Termo ) :-
    assert( Termo ).
insercao( Termo ) :-
    retract( Termo ),!,fail.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado teste: [H|T] -> {V,F}     

teste([]).
teste([H|T]) :- H,
                teste(T).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado comprimento: L,R -> {V,F}  

comprimento([],0).
comprimento([H|T],R) :- comprimento(T,N),
                        R is N+1.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado removeTermos: [X|L] -> {V, F}

removeTermos( [] ).
removeTermos( [X] ) :-
    retract(X).
removeTermos( [X|L] ) :-
    retract(X),
    removeTermos( L ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado contem: H,[H|T] -> {V, F}

contem(X,[]).
contem(H, [H|T]).
contem(X, [H|T]) :-
    contem(X, T).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado nao: Questao -> {V,F}

nao( Questao ) :-
    Questao, !, fail.
nao( Questao ).




%---------------------------------Demonstradores----------------------------------
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demo: Questao,Resposta -> {verdadeiro, falso, desconhecido}

demo( Questao,verdadeiro ) :-
    Questao.
demo( Questao, falso ) :-
    -Questao.
demo( Questao,desconhecido ) :-
    nao( Questao ),
    nao( -Questao ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demoCada: [Questao],[Resposta] -> {verdadeiro, falso, desconhecido}

demoCada([], []).
demoCada([Questao|T], R) :-
    demo(Questao, X),
    demoCada(T, B),
    R = [X|B]. 

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demoConj: [Questao | T], Resposta -> {verdadeiro, falso, desconhecido}

demoConj([], verdadeiro).   			% lista vazia -> verdadeiro

demoConj([Questao|T], verdadeiro) :-    % todos verdadeiros -> verdadeiro
    demo(Questao, verdadeiro),
    demoConj(T, verdadeiro).

demoConj([Questao|T], desconhecido) :-  % se cabeça desconhecido e nao há falsos na tail -> desconhecido
    demo(Questao, desconhecido),
    nao( demoConj(T, falso)).

demoConj([Questao|T], desconhecido) :-  % se cabeça nao é falso e na tail o resultado é desconhecido -> desconhecido 
    nao( demo(Questao, falso)),
    demoConj(T, desconhecido).

demoConj([Questao|T], falso) :-		    % se cabeça for falso -> falso
    demo(Questao, falso).

demoConj([Questao|T], falso) :- 		% se tail tiver falsos -> falso
    demoConj(T, falso).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demoDisj: [Questao | T], Resposta -> {verdadeiro, falso, desconhecido}

demoDisj([], falso).            		% lista vazia -> falso

demoDisj([Questao|T], verdadeiro) :- 	% se a cabeça for verdadeira -> verdadeiro
    demo(Questao, verdadeiro).

demoDisj([Questao|T], verdadeiro) :-	% se a tail tiver resultado verdadeiro -> verdadeiro
    demoDisj(T, verdadeiro).

demoDisj([Questao|T], desconhecido) :-	% se a cabeça for desconhecido e a tail tiver resultado nao verdadeiro -> desconhecido
    demo(Questao, desconhecido),
    nao( demoDisj(T, verdadeiro)).

demoDisj([Questao|T], desconhecido) :-	% se a cabeça nao for verdadeiro e a tail tiver resultado desconhecido -> desconhecido
    nao( demo(Questao, verdadeiro)),
    demoDisj(T, desconhecido).

demoDisj([Questao|T], falso) :- 		% se a cabeça for falso e a tail tiver resultado falso -> falso
    demo(Questao, falso),
    demoDisj(T, falso).


