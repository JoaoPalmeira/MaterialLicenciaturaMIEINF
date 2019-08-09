%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SISTEMAS DE REPRESENTA��O DE CONHECIMENTO E RACIOC�NIO - MiEI
% Exerc�cio Pr�tico N� 1
% Grupo 3



% Declara��es iniciais

:- dynamic utente/4.
:- dynamic cuidados/4.
:- dynamic atoMedico/4.
:- op( 900,xfy,'::' ).

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado inserir : F -> {V,F}
inserir( F ) :- assert( F ).
inserir( F ) :- retract( F ), !,fail.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado testar : L -> {V,F}
testar( [] ).
testar( [I|Li] ) :- I,testar( Li ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado retirar : F -> {V,F}
retirar( F ) :- retract( F ).
retirar( F ) :- assert( F ), !,fail.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado comprimento : L,N -> {V,F}
comprimento( [],0 ).
comprimento( [X|L],N ) :- comprimento(L,N2), N is 1+N2.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado encontrar : F, Q, S -> {V,F}
encontrar( F,Q,S ) :- findall( F,Q,S ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado concatenar : L1, L2, L -> {V,F}
concatenar([],L,L).
concatenar([X|T],L1,[X|R]) :- concatenar(T,L1,R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado soma: L, R -> {V,F}
soma([],0).
soma([H|T],S):-soma(T,G),S is H+G.



% Invariantes Estruturais: n�o permitir a inser��o de conhecimento repetido

% Utentes n�o repetidos
+utente( IU,N,ID,M ) :: (encontrar( (IU,N,ID,M),(utente(IU,N,ID,M)),S ),comprimento( S,C ), C == 1).

% Cuidados prestados n�o repetidos
+cuidados( IS,D,INST,C ) :: (encontrar( ( IS,D,INST,C ),(cuidados( IS,D,INST,C )),S ), comprimento( S,N ), N == 1).

% Atos m�dicos n�o repetidos
+atoMedico( D,IU,IS,C ) :: (encontrar( (D,IU,IS,C),(atoMedico( D,IU,IS,C )),S ), comprimento( S,N ), N == 1).


% Invariantes Referenciais

% Impedir a remo��o de utentes se houver conhecimento sobre eles nos atos m�dicos
-utente( IU,NO,ID,M ) :: (encontrar( (IU),(atoMedico(D,IU,IS,C)), S ), comprimento( S,N ), N == 0).

% Impedir a remo��o de cuidados prestados se houver conhecimento sobre eles nos atos m�dicos
-cuidados( IS,DE,INST,CI ) :: (encontrar( (IS),(atoMedico(D,IU,IS,C)), S ), comprimento( S,N ), N == 0).

% Impedir a adi��o de utentes com um IdUt j� existente                     
+utente( IU,NO,ID,M ) :: (encontrar( (NOs,IDs,Ms) ,(utente(IU,NOs,IDs,Ms)), S ), comprimento( S,C ), C == 1).

% Impedir a adi��o de cuidados com um IdServ j� existente                     
+cuidados( IS,DES,INST,CI ) :: (encontrar( (DESs,INSTs,CIs),(cuidados(IS,DESs,INSTs,CIs)),S ), comprimento( S,C ), C == 1).

% Impedir a adi��o de atos m�dicos com uma combina��o de IdUt e IdServ j� existente                  
+atoMedico( D,IU,IS,C ) :: (encontrar( (Ds,Cs),(atoMedico(Ds,IU,IS,Cs)),S ), comprimento( S,N ), N == 1).

% Impedir a adi��o de atos m�dicos com um utente n�o existente (verifica��o do IdUt)                 
+atoMedico( D,IU,IS,C ) :: (encontrar( (IU),(utente(IU,NO,ID,M)),S ), comprimento( S,N ), N > 0). 

% Impedir a adi��o de atos m�dicos com um cuidado prestado n�o existente (verifica��o do IdServ)              
+atoMedico( D,IU,IS,C ) :: (encontrar( (IS),(cuidados( IS,DES,INST,CI )),S ), comprimento( S,N ), N > 0).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado utente: IdUt, Nome, Idade, Morada -> {V,F}
utente(8, andre, 21, selva).
utente(2, joao, 21, braga).
utente(15, joel, 21, bragan�a).
utente(9, sofia, 20, porto).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado cuidados: IdServ, Descri��o, Institui��o, Cidade -> {V,F}
cuidados(1, cardiologia, hospitalBraga, braga).
cuidados(2, oftalmologia, hospitalBragan�a, bragan�a).
cuidados(3, pediatria, hospitalTrofa, braga).
cuidados(4, neurologia, hospitalGuimar�es, guimar�es).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado atoMedico: Data, IdUt, IdServ, Custo -> {V,F}
atoMedico(01-01-2001, 9, 1, 30).
atoMedico(14-10-2009, 2, 2, 18).
atoMedico(21-01-2014, 15, 4, 23).
atoMedico(30-07-2010, 8, 3, 50).
atoMedico(30-08-2015, 8, 1, 40).
atoMedico(20-11-2016, 9, 2, 20).



% 1-Registar utentes, cuidados prestados e atos m�dicos
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado evolucao : F -> {V,F}
evolucao( F ) :- encontrar( I, +F::I, Li ), inserir( F ), testar( Li ).



% 2-Identificar os utentes por crit�rios de sele��o
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado identificarU: IU, N, I, M, LU -> {V,F}
identificarU(IU, N, I, M,LU) :- encontrar((IU, N, I, M),utente(IU, N, I, M),LU).



% 3-Identificar as institui��es prestadoras de cuidados de sa�de
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado identificarI: LI -> {V,F}
identificarI(LI) :- encontrar((I),cuidados(IS, D, I, C),LI).



% 4-Identificar os cuidados prestados por institui��o/cidade
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado identificarIC: INST,C,LI -> {V,F}
identificarIC(INST,C,LI) :- encontrar((IS,D,INST,C),cuidados(IS, D, INST, C),LI).



% 5-Identificar os utentes de uma institui��o/servi�o
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado identificarUIS: INST,S,L -> {V,F}
identificarUIS(INST,S,L) :- encontrar((S),cuidados(S, D, INST, C),L2), procuraIdut(L2,L1), procuraUtent(L1,L).

% Extens�o do predicao procuraIdut: LIDS, LIDU -> {V,F}
procuraIdut([],[]).
procuraIdut([IDS|T],R) :- encontrar((IDU), atoMedico(D,IDU,IDS,C), L), procuraIdut(T,R2), concatenar(L,R2,R).

% Extens�o do predicado procuraUtent: LIDU, LU -> {V,F}
procuraUtent([],[]).
procuraUtent([IDU|T],R) :- encontrar((IDU, N, I, M), utente(IDU, N, I, M),L), procuraUtent(T,R2), concatenar(L,R2,R).



% 6-Identificar os atos m�dicos realizados, por utente/institui��o/servi�o
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado identificarAMI: INST,L2 -> {V,F}
identificarAMI(INST, R) :- encontrar((IDS),cuidados(IDS,D,INST,C),X), identificarAMIAux(X,R).

% Extens�o do predicado identificarAMIAux: L1,L2 -> {V,F}
identificarAMIAux([],[]).
identificarAMIAux([IDS|T],R) :- encontrar((D,IDU,IDS,C),atoMedico(D,IDU,IDS,C),L),identificarAMIAux(T,R2),concatenar(L,R2,R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado identificarAMS: IDS,L2 -> {V,F}
identificarAMS(IDS,R) :- encontrar((D,U,IDS,CUS),atoMedico(D,U,IDS,CUS),R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado identificarAMU: IDU,L2 -> {V,F}
identificarAMU(IDU,R) :- encontrar((D,IDU,IDS,CUS),atoMedico(D,IDU,IDS,CUS),R).



% 7-Determinar todas as institui��es/servi�os a que um utente j� recorreu
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado determinarInst: IDU, LINST -> {V,F}
determinarInst(IDU, R) :- encontrar((IDS), atoMedico(D,IDU,IDS,C), LIDS), procuraInst(LIDS,R).

% Extens�o do predicado procuraInst: LIDS, L -> {V,F}
procuraInst([],[]).
procuraInst([IDS|T],R) :- encontrar((INST), cuidados(IDS,DES,INST,CID),L), procuraInst(T,R2), concatenar(L,R2,R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado determinarServ: IDU, LIDS -> {V,F}
determinarServ(IDU,R) :- encontrar((IDS),atoMedico(D,IDU,IDS,C),R).



% 8-Calcular o custo total dos atos m�dicos por utente/servi�o/institui��o/data
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado calcularCINST: INST, CUS -> {V,F}
calcularCINST(INST, R) :- encontrar((IDS),cuidados(IDS,D,INST,C),N), procuraCS(N,R2), soma(R2,R).

% Extens�o do predicado procuraCS: LIDS, L -> {V,F}
procuraCS([],[]).
procuraCS([IDS|T],R) :- encontrar((C), atoMedico(D,IDU,IDS,C),L), procuraCS(T,R2), concatenar(L,R2,R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado calcularCD: D,CUS -> {V,F}
calcularCD(D,R) :- encontrar(CUS,atoMedico(D,U,IDS,CUS),N),soma(N,R). 

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado calcularCIDU: IDU,CUS -> {V,F}
calcularCIDU(IDU,R) :- encontrar(CUS,atoMedico(D,IDU,IDS,CUS),N),soma(N,R).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado calcularCIDS: IDS,CUS -> {V,F}
calcularCIDS(IDS,R) :- encontrar(CUS,atoMedico(D,U,IDS,CUS),N),soma(N,R).



% 9-Remover utentes, cuidados e atos m�dicos
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado involucao : F -> {V,F}
involucao( F ) :- encontrar( I, -F::I, Li ), retirar( F ), testar( Li ).



% 10-Identificar os servi�os que n�o se podem encontrar numa institui��o
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado apagarElemento: X, LI, LF -> {V,F}
apagarElemento(X, [], []).
apagarElemento(X, [X|T], Y) :- apagarElemento(X, T, Y).
apagarElemento(X, [H|T], [H|Y]) :- apagarElemento(X, T, Y).

% Extens�o do predicado apagarElems: L1, L2, LF -> {V,F}
apagarElems([],[X],[X]).
apagarElems([H|T],L,Z) :- apagarElemento(H,L,Y), apagarElems(T,Y,Z).
apagarElems([],Z,Z).

% Extens�o do predicado naoEstaoInst: INST, LIDS -> {V,F}
naoEstaoInst(INST,LIDS) :- encontrar((IDS),cuidados(IDS,DES,INST,CID),L1), encontrar((I),cuidados(I,D,IN,C),L2), apagarElems(L1,L2,LIDS).



% 11-Determinar o n�mero de atos m�dicos de um utente
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extens�o do predicado numAtos: IDUT, N -> {V,F}
numAtos(IU,N) :- encontrar((IU),atoMedico(D,IU,IS,C),L), comprimento(L,N).
