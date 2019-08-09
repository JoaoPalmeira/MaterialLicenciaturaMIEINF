#% Extensao do predicado registarU: U, LU, L -> {V,F}
#registarU(U,[],[U]).
#registarU(U,[X|Y],L) :- registarU(U,Y,[X|L]).
#
#%--------------------------------- - - - - - - - - - -  -  -  -  -   -
#% Extensao do predicado registarCP: CP, LCP, L -> {V,F}
#registarCP(CP,[],[CP]).
#registarCP(CP,[X|Y],L) :- registarU(CP,Y,[X|L]).

#%--------------------------------- - - - - - - - - - -  -  -  -  -   -
/*% Extensao do predicado registarAM: AM, LAM, L -> {V,F}
registarAM(AM,[],[AM]).
registarAM(AM,[X|Y],L) :- registarAM(AM,Y,[X|L]).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%PERGUNTAR AO DOCENTE
% Extensao do predicado idUtente: IDU, LU, L -> {V,F}
idUtente(X,) :- 

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado idInstitut: LCP, LINST -> {V,F}
idInstitut([],[]).
idInstitut([(IS,D,INST,C)|Y],L) :- idInstitut(Y,[INST|L]). 

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado idCuidadosI: INST, LCP, L -> {V,F}
idCuidadosI(I,[],[]).
idCuidadosI(I,[(IS,D,I,C)|Y],L) :- idCuidadosI(I,Y,[(IS,D,I,C)|L]).
idCuidadosI(I,[(IS,D,INST,C)|Y],L) :- I\=INST, idCuidadosI(I,Y,L]).

#Identificar os cuidados prestados por cidade
% Extensao do predicado idCuidadosC: CI, LCP, L -> {V,F}
idCuidadosC(C,[],[]).
idCuidadosC(C,[(IS,D,I,CI)|Y],L) :- idCuidadosC(C,Y,[(IS,D,I,CI)|L]).
idCuidadosC(C,[(IS,D,I,CI)|Y],L) :- C\=CI, idCuidadosC(C,Y,L]).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

#% Extensao do predicado idUtentesI: I,LCP,LIDSERV -> {V,F}
#idUtentesI(I,[(ISE,D,I,C)],[ISE]).
#idUtentesI(I,[(ISE,D,I,C)|Y],L) :- idUtentesI(I,Y,[ISE|L]).
#idUtentesI(I,[(ISE,D,IS,C)|Y],L) :- I\=IS, idUtentesI(I,Y,L). 

#% Extensao do predicado idUtentesAM: IS,LAM,LIDUT -> {V,F}
#idUtentesAM(IS,[],[]).
#idUtentesAM(IS,[(D,IDUT,IS,C)|Y],L) :- idUtentesAM(IS,Y,[IDUT|L]).
#idUtentesAM(IS,[(D,IDUT,I,C)|Y],L) :- I\=IS, idUtentesAM(I,Y,L).




# Identificar os utentes de um serviço

% Extensao do predicado idUtentesS: IS,LAM,LIDUT -> {V,F}
idUtentesS(IS,[],[]).
idUtentesS(IS,[(D,IDUT,IS,C)|Y],L) :- idUtentesS(IS,Y,[IDUT|L]).
idUtentesS(IS,[(D,IDUT,I,C)|Y],L) :- IS\=I, idUtentesS(IS,Y,L).

% Extensao do predicado idUtentesS: IS,LAM,LIDUT -> {V,F}





%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado atoMedI: I -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado atoMedS: S -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado determinar: U -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado custoU: U -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado custoS: S -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado custoI: I -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado custoD: D -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado remU: U -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado remC: C -> {V,F}


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado remAtoMed: A -> {V,F}



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado solucoes : F, Q, S -> {V,F}
solucoes( F,Q,S ) :- Q, assert(tmp(F)), fail.
solucoes( F,Q,S ) :- construir( S,[] ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado construir : S1, S2 -> {V,F}
construir( S1,S2 ) :- retract(tmp(F)), construir( S1, [X|S2] ).
construir( S1,S2 ).

