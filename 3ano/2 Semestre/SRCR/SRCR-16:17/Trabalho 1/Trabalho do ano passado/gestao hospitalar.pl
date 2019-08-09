:- op( 900,xfy,'::' ).
:- dynamic servico/1.
:- dynamic utente/1.
:- dynamic instituicao/1.
:- dynamic servico_instituicao/2.
:- dynamic profissional/3.
:- dynamic dados_utente/4.

:- set_prolog_flag( unknown,fail ).
:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).

% pertence: Lista,K -> {V,F}
pertence(X,[X|Y]).
pertence(K,[X|Y]) :- X\=K,pertence(K,Y).

% concatenar: L1,L2,L -> {V,F}
concatenar([],L2,L2).
concatenar([X|L1],L2,[X|L]):-concatenar(L1,L2,L).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

% comprimento: X,Lista -> {V,F}
comprimento(0,[]).
comprimento(X,[Y|T]):-comprimento(R,T),X is R+1.

solucoes(X,P,S):- findall(X,P,S).

insere(P):- assert(P).
insere(P):- retract(P), write('ERRO!! Nao foi possivel fazer insercao.').

testa([]).
testa([R|LR]) :- R, testa(LR).

inserir( P ) :- solucoes(Inv,+P::Inv,S), insere(P), testa(S).

% Base de conhecimento sobre utentes
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%NOTA: 
%     - Não podem haver repetições.

utente(joao).
utente(pedro).
utente(luis).

% Base de conhecimento sobre serviços 
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%NOTA: 
%     - Não podem haver repetições.

servico(oncologia).
servico(cardiologia).
servico(consultas).

% Base de conhecimento sobre instituições
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%NOTA: 
%     - Não podem haver repetições.

instituicao(lisboa).
instituicao(porto).
instituicao(braga).

% Base de conhecimento sobre serviços de uma determinada instituição
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%NOTA: 
%     - Não podem haver repetições.

% serviço 1
servico_instituicao(oncologia, porto).
servico_instituicao(oncologia, lisboa).

servico_instituicao(cardiologia, lisboa).
servico_instituicao(cardiologia, porto).

servico_instituicao(consultas, braga).
servico_instituicao(consultas, lisboa).

% Base de conhecimento sobre profissionais de um determinado serviço de uma determinada instituição
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%NOTA: 
%     - Não podem haver serviços em instituições que as não têm
%     - Não podem haver repetições.

% profissional 1
profissional(drjoao, oncologia, lisboa).
profissional(drjoao, oncologia, porto).

%profissional 2
profissional(drpaulo, cardiologia, lisboa).
profissional(drpaulo, cardiologia, porto).

%profissional 3
profissional(drajoana, consultas, braga).
profissional(drajoana, consultas, lisboa).


% Base de conhecimento sobre utentes de uma determinada instituição com um determinado profissional num determinado servico
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%NOTA: 
%     - Um profissional não pode estar numa instituição a que não pertence
%     - Um serviço não pode estar numa instituição que a não possui
%     - Não podem haver repetições.

% utente 1
dados_utente(joao, drjoao, oncologia, lisboa).
dados_utente(joao, drjoana, consultas, lisboa).

%utente 2
dados_utente(pedro, drjoao, oncologia, porto).
dados_utente(pedro, drpaulo, cardiologia, porto).
dados_utente(pedro, drajoana, consultas, braga).

%utente 3
dados_utente(luis, drajoana, consultas, braga).

%invariante de repetições
+utente( P ) :: (solucoes( (P),(utente(P)),S ),
                  comprimento( N,S ), 
                          N < 2
                  ).

+servico( P ) :: (solucoes( (P),(servico(P)),S ),
                  comprimento( N,S ), 
                          N < 2
                  ).

+instituicao( P ) :: (solucoes( (P),(instituicao(P)),S ),
                  comprimento( N,S ), 
                          N < 2
                  ).

+servico_instituicao( F,P ) :: (solucoes( (F,P),(servico_instituicao( F,P )),S ),
                  comprimento( N,S ), 
                          N < 2
                  ).

+profissional( F,P,G ) :: (solucoes( (F,P,G),(profissional( F,P,G )),S ),
                  comprimento( N,S ), 
                          N < 2
                  ).

+dados_utente( F,P,X,Y ) :: (solucoes( (F,P),(dados_utente( F,P,X,Y )),S ),
                  comprimento( N,S ), 
                          N < 2
                  ).

%invariante verifica se existe instituicao
+servico_instituicao(S,I) :: (solucoes( X ,(instituicao( X )), F ),
                  pertence(I,F) 
                  ).

%invariante verifica se existe servico
+servico_instituicao(S,I) :: (solucoes( X ,(servico( X )), F ),
                  pertence(S,F) 
                  ).

%invariante verifica se existe servico_instituicao
+profissional(P,S,I) :: (solucoes( (X,Y) ,(  servico_instituicao( X,Y )), F ),
                  pertence((S,I),F) 
                  ).

%invariante verifica se existe profissional
+dados_utente(U,P,S,I) :: (solucoes( (X,Y,Z) ,(  profissional( X,Y,Z )), F ),
                  pertence((P,S,I),F) 
                  ).

%invariante verifica se existe utente
+dados_utente(U,P,S,I) :: (solucoes( X ,(utente( X )), F ),
                  pertence(U,F) 
                  ).

%Querie1
% Extensao do predicado servInst: instituicao,X -> {V,F}
% Identificar os serviços existentes numa instituição
servInst(I,L) :-
  solucoes(X,servico_instituicao(X,I),L).

%Querie2
% Extensao do predicado utentInst: instituicao,X-> {V,F}
% Identificar os utentes de uma instituição
utentInst(I,L) :-
  solucoes(X,dados_utente(X,A,B,I),L).

%Querie3
% Extensao do predicado utentServ: servico,X -> {V,F}
% Identificar os utentes de um determinado serviço
utentServ(S,L) :-
  solucoess(X,dados_utente(X,A,S,B),L).

%Querie4
% Identificar os utentes de um determinado serviço numa instituição
% Extensao do predicado utentServInst: servico, instituicao, X -> {V,F}
utentServInst(S,I,L) :-
  solucoes(X,dados_utente(X,A,S,I),L).

% Extensao do predicado nao: Q->{v,f}
nao(Q):- (Q, !, fail).
nao(Q).

% Extensao do predicado rep: LI, LF -> {V,F}
rep([],[]).
rep([H|T],[H|C]) :- nao(pertence(H,T)), rep(T,C).
rep([H|T],L) :- pertence(H,T), rep(T,L).

%Querie 5
%instituicoes aonde e prestado um servico ou conjunto de servicos
% Extensao do predicado inst: Instituicoes,Servicos -> {V,F}
inst(I,[S]) :- solucoes(X,servico_instituicao(S,X),I).
inst(I,[S|T]) :- solucoes(X,servico_instituicao(S,X),K), inst(H,T), concatenar(K,H,O), rep(O,I).


% Extensao do predicado delMember: X, LI, LF -> {V,F}
delMember(X, [], []).
delMember(X, [X|Xs], Y) :- delMember(X, Xs, Y).
delMember(X, [H|Xs], [H|Y]) :- delMember(X, Xs, Y).

% Extensao do predicado delTodos: L1, L2, LF -> {V,F}
delTodos([],[X],[X]).
delTodos([H|T],S,P) :- delMember(H,S,K), delTodos(T,K,P).
delTodos([],P,P).

%Querie 6
%identifica servicos que nao se podem encontrar numa instituicao
% Extensao do predicado nao_encontra: Servicos, Instituicao -> {V,F}
nao_encontra(S,I) :- solucoes(X,servico_instituicao(X,I),O), solucoes(Q,servico(Q),W), delTodos(O,W,S).

%Querie7
% Extensao do predicado prestaServ: profissional,X -> {V,F}
% Determinar as instituições onde um profissional presta serviço
prestaServ(P,L) :-
    solucoes(X,profissional(P,S,X),L).

%Querie8
% Extensao do predicado determ: utente, X, Y -> {V,F}
% Determinar todas as instituições (ou serviços, ou profissionais) a que um utente já recorreu
% determ(U,Tag,L)      //Tag : Inst, Serv , Prof
determ(U,inst,O) :-
    solucoes(X,dados_utente(U,A,B,X),L), rep(L,O).
determ(U,serv,O) :-
    solucoes(X,dados_utente(U,A,X,B),L), rep(L,O).
determ(U,prof,O) :-
    solucoes(X,dados_utente_(U,X,A,B),L), rep(L,O).

%Querie11
% Extensao do predicado profutente: profissional, X -> {V,F}
% Os utentes que um dado profissional prestou o seu servico
profutente(P,O) :-
    solucoes(X,dados_utente(X,P,A,B),L), rep(L,O).

%Querie10
% Extensao do predicado remove: Tag, X  ||  Tag, X, I -> {V,F}
%//Tag : utente, servico , profissional, instituicao
remove(utente,X) :- retract(utente(X)).
remove(servico,X) :- retractall(profissional(P,X,I)), retractall(servico_instituicao(X,B)), retract(servico(X)).
remove(servico,X,I) :- retractall(profissional(P,X,I)), retractall(servico_instituicao(X,I)), retract(servico(X)).
remove(profissional,X) :- retractall(profissional(X,S,I)).
remove(profissional,X,I) :- retractall(profissional(X,S,I)).
remove(instituicao,X) :- retractall(profissional(P,S,X)), retractall(servico_instituicao(S,X)), retract(instituicao(X)).

%Querie12
% Extensao do predicado dadoservico: servico,X -> {V,F}
% Os profissionais que prestam um dado servico
dadoservico(S,O) :-
    solucoes(P,profissional(P,S,X),L), rep(L,O).
