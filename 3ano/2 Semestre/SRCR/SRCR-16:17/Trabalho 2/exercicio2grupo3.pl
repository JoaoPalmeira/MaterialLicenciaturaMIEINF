%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SISTEMAS DE REPRESENTAÇÃO DE CONHECIMENTO E RACIOCÍNIO - MiEI
% Exercício Prático Nº 2
% Grupo 3



% Declarações iniciais

:- dynamic utente/4.
:- dynamic cuidados/4.
:- dynamic atoMedico/4.
:- dynamic nao/1.
:- op( 900,xfy,'::' ).

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado inserir : F -> {V,F}
inserir( F ) :- assert( F ).
inserir( F ) :- retract( F ), !,fail.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado testar : L -> {V,F}
testar( [] ).
testar( [I|Li] ) :- I,testar( Li ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado retirar : F -> {V,F}
retirar( F ) :- retract( F ).
retirar( F ) :- assert( F ), !,fail.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado comprimento : L,N -> {V,F}
comprimento( [],0 ).
comprimento( [X|L],N ) :- comprimento(L,N2), N is 1+N2.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado encontrar : F, Q, S -> {V,F}
encontrar( F,Q,S ) :- findall( F,Q,S ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demo: Questao,Resposta -> {V,F}
demo( Questao,verdadeiro ) :-
    Questao.
demo( Questao, falso ) :-
    -Questao.
demo( Questao,desconhecido ) :-
    nao( Questao ),
    nao( -Questao ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demoLista: LQuestao,LResposta -> {V,F}
demoLista( [],[] ).
demoLista( [Questao|L],[Resposta|S] ) :- demo( Questao,Resposta ), demoLista(L,S).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado nao: Questao -> {V,F}
nao( Questao ) :-
    Questao, !, fail.
nao( Questao ).


% Registar utentes, cuidados prestados e atos médicos
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado evolucao : F -> {V,F}
evolucao( F ) :- encontrar( I, +F::I, Li ), inserir( F ), testar( Li ).


% Remover utentes, cuidados e atos médicos
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado involucao : F -> {V,F}
involucao( F ) :- encontrar( I, -F::I, Li ), retirar( F ), testar( Li ).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Invariantes


% Invariantes Estruturais: não permitir a inserção de conhecimento repetido

% Utentes não repetidos
+utente( IU,N,ID,M ) :: (encontrar( (IU,N,ID,M),(utente(IU,N,ID,M)),S ),comprimento( S,C ), C == 1).

% Cuidados prestados não repetidos
+cuidados( IS,D,INST,C ) :: (encontrar( ( IS,D,INST,C ),(cuidados( IS,D,INST,C )),S ), comprimento( S,N ), N == 1).

% Atos médicos não repetidos
+atoMedico( D,IU,IS,C ) :: (encontrar( (D,IU,IS,C),(atoMedico( D,IU,IS,C )),S ), comprimento( S,N ), N == 1).


% Invariantes Referenciais

% Impedir a remoção de utentes se houver conhecimento sobre eles nos atos médicos
-utente( IU,NO,ID,M ) :: (encontrar( (IU),(atoMedico(D,IU,IS,C)), S ), comprimento( S,N ), N == 0).

% Impedir a remoção de cuidados prestados se houver conhecimento sobre eles nos atos médicos
-cuidados( IS,DE,INST,CI ) :: (encontrar( (IS),(atoMedico(D,IU,IS,C)), S ), comprimento( S,N ), N == 0).

% Impedir a adição de utentes com um IdUt já existente                     
+utente( IU,NO,ID,M ) :: (encontrar( (NOs,IDs,Ms) ,(utente(IU,NOs,IDs,Ms)), S ), comprimento( S,C ), C == 1).

% Impedir a adição de cuidados com um IdServ já existente                     
+cuidados( IS,DES,INST,CI ) :: (encontrar( (DESs,INSTs,CIs),(cuidados(IS,DESs,INSTs,CIs)),S ), comprimento( S,C ), C == 1).

% Impedir a adição de atos médicos com uma combinação de IdUt e IdServ já existente                  
+atoMedico( D,IU,IS,C ) :: (encontrar( (Ds,Cs),(atoMedico(Ds,IU,IS,Cs)),S ), comprimento( S,N ), N == 1).

% Impedir a adição de atos médicos com um utente não existente (verificação do IdUt)                 
+atoMedico( D,IU,IS,C ) :: (encontrar( (IU),(utente(IU,NO,ID,M)),S ), comprimento( S,N ), N > 0). 

% Impedir a adição de atos médicos com um cuidado prestado não existente (verificação do IdServ)              
+atoMedico( D,IU,IS,C ) :: (encontrar( (IS),(cuidados( IS,DES,INST,CI )),S ), comprimento( S,N ), N > 0).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Conhecimento positivo

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado utente: IdUt, Nome, Idade, Morada -> {V,F,D}
utente(8, andre, 21, selva).
utente(2, joao, 21, braga).
utente(15, joel, 21, braganca).
utente(9, sofia, 20, praia).
utente(4, ana, 25, porto).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado cuidados: IdServ, Descrição, Instituição, Cidade -> {V,F,D}
cuidados(1, cardiologia, hospitalBraga, braga).
cuidados(2, oftalmologia, hospitalBraganca, braganca).
cuidados(3, pediatria, hospitalTrofa, braga).
cuidados(4, neurologia, hospitalGuimaraes, guimaraes).
cuidados(6, dermatologia, hospitalTrofa, braga).
cuidados(7, dentista, denteCintilante, guimaraes).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensão do predicado atoMedico: Data, IdUt, IdServ, Custo -> {V,F,D}
atoMedico(01-01-2001, 9, 1, 30).
atoMedico(14-10-2009, 2, 2, 18).
atoMedico(21-01-2014, 15, 4, 23).
atoMedico(30-07-2010, 8, 3, 50).
atoMedico(30-08-2015, 8, 1, 40).
atoMedico(20-11-2016, 9, 2, 20).
atoMedico(27-02-2018, 9, 6, 20).
atoMedico(01-07-2017, 9, 7, 130).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Conhecimento negativo

% A senhora Amélia faleceu de velhice e foi removida do Serviço Nacional de Saúde.

-utente(20, amelia, 87, faro).

% O senhor Alberto decidiu emigrar por motivos de força maior, logo não estará registado nos cuidados médicos portugueses.

-utente(34,alberto,46,porto).

% As máquinas de radiologia do hospital de guimarães precisavam de manutenção devido a um problema técnico que fazia com que emitissem grandes níveis de radiação e, por isso, os cuidados médicos
%de radiologia estão suspensos.

-cuidados(5, radiologia, hospitalGuimaraes, guimaraes).

% Devido a um incêndio no Hospital de Guimarães, o serviço de urologia dessa cidade encontra-se encerrado.

-cuidados(6,urologia,hospitalGuimaraes,guimaraes).

% Como os cuidados de radiologia estão indisponíveis, não se poderão realizar os atos médicos correspondentes a esses cuidados.

-atoMedico(09-09-2017, 9, 5, 25).
-atoMedico(21-09-2017, 4, 5, 30).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Conhecimento imperfeito 

-utente(IDU,N,ID,M) :- nao(utente(IDU,N,ID,M)), nao(excecao(utente(IDU,N,ID,M))).

-cuidados(IDS,DES,I,C) :- nao(cuidados(IDS,DES,I,C)), nao(excecao(cuidados(IDS,DES,I,C))).

-atoMedico(D,IDU,IDS,CUS) :- nao(atoMedico(D,IDU,IDS,CUS)), nao(excecao(atoMedico(D,IDU,IDS,CUS))).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%
% Incerto:

% Sabemos que o utente Abel foi o 10º a ser registado e tem 23 anos de idade, mas devido a um incêndio, foi perdida parte da informação, não se sabendo a morada do utente.

utente(10,abel,23,xpto023).
excecao(utente(Id,N,I,M)):-
	utente(Id,N,I,xpto023).

% Nesse mesmo incêndio foi recolhida a informação relativamente ao 20º utente a ser registado, sendo essa informação a seguinte: é um utente de Lisboa, chamado Juliano,
%que no dia 21 de Janeiro de 2014 pagou um certo valor por uma consulta de neurologia em que se sabe que o IdServ é igual a 10 e que foi na cidade de Lisboa.
%Todos os outros dados são desconhecidos.

utente(20,juliano,xpto123,lisboa).
excecao(utente(Id,N,I,M)):-
	utente(Id,N,xpto123,M).

cuidados(10,neurologia,xpto312,lisboa).
excecao(cuidados(Id,D,I,C)):-
	cuidados(Id,D,xpto312,C).

atoMedico(21-01-2014,20,10,xpto456).
excecao(atoMedico(D,IdU,IdS,C)):-
	atoMedico(D,IdU,IdS,xpto456).

% Relativamente à paciente número 30 a ser registada, sabemos apenas que se chama susana e que foi a uma consulta de cardiologia em que apenas se sabe que tem o IdServ igual a 11.

utente(30,susana,xpto001,xpto002).
excecao(utente(IdU,N,I,M)):-
	utente(IdU,N,xpto001,xpto002).

cuidados(11,cardiologia,xpto003,xpto004).
excecao(cuidados(IdS,D,I,C)):-
	cuidados(IdS,D,xpto003,xpto004).

atoMedico(xpto2000,30,11,xpto2001).
excecao(atoMedico(D,IdU,IdS,C)):-
	atoMedico(xpto2000,IdU,IdS,xpto2001).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% 
% Impreciso:

% Sabemos que o 40º utente a ser registado se chama Miguel, tem 21 anos e reside em Braga, mas devido a uma falha no sistema não conseguimos apurar se ele foi a uma consulta de cardiologia (IdServ=1)
%ou de pediatria (IdServ=3) no dia 07-06-2005, sabendo que foi na cidade de Braga e que pagou 40 euros. 

utente(40,miguel,21,braga).
%cuidados(1,cardiologia,hospitalBraga,braga).
%cuidados(3,pediatria,hospitalTrofa,braga).

excecao(atoMedico(07-06-2005,40,1,40)).
excecao(atoMedico(07-06-2005,40,3,40)).


% Devido a um aumento nos preços de cuidados médicos, a variar entre 5 a 10 euros, o utente João (IdUt=2), com 21 anos de idade e residente em Braga, não sabe quanto é 
%que será o preço final de uma consulta de oftalmologia (IdServ=2), a ser realizada no dia 14 de Outubro de 2017, na cidade de Bragança, no Hospital de Bragança, sabendo apenas que inicialmente tinha
%um custo associado de 18 euros.

%cuidados(2,oftalmologia,hospitalBraganca,braganca).
%utente(2,joao,21,braga).

excecao(atoMedico(14-10-2017,2,2,Custo)):- Custo>=23, Custo=<28. 

% Não sabemos qual dos 4 utentes que deu entrada no Hospital de Bragança, em Bragança, foi à consulta de oftalmologia (IdServ=2). Sabemos apenas as informações relativas a esses 4 utentes:
%O utente número 8 chama-se André, tem 21 anos de idade e veio da Selva, o utente número 2 chama-se João, tem 21 anos de idade e veio de Braga, o utente número 15 chama-se Joel,
%tem 21 anos de idade e veio de Bragança e por último o utente número 9 chama-se Sofia, tem 20 anos de idade e veio da Praia.

%cuidados(2,oftalmologia,hospitalBraganca,braganca).
%utente(8,andre,21,selva).
%utente(2,joao,21,braga).
%utente(15,joel,21,braganca).
%utente(9,sofia,20,porto).

excecao(atoMedico(06-04-2017,2,2,50)).
excecao(atoMedico(06-04-2017,8,2,50)).
excecao(atoMedico(06-04-2017,9,2,50)).
excecao(atoMedico(06-04-2017,15,2,50)).

%Uma paciente chamada Ana (IdUt=4) marcou uma consulta de dermatologia em Braga, mas não se lembra do dia para que marcou a consulta, estando indecisa entre os dias 05-04-2017, 05-05-2017 e 15-06-2017.

excecao(atoMedico(05-04-2017,4,6,55)).
excecao(atoMedico(05-05-2017,4,6,55)).
excecao(atoMedico(15-06-2017,4,6,55)).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% 
% Interdito

% Após um roubo no edifício principal do SNS, perdemos informações acerca do valor pago por um certo utente para um ato médico realizado no dia 04-04-2017. 
%Como não havia uma cópia de segurança dos atos médicos mais recentes, é impossível saber o custo deste ato médico.

atoMedico(04-04-2017, 2, 1, xpto700).
excecao(atoMedico(D,IDU,IDS,C)) :- atoMedico(D,IDU,IDS,xpto700).
nulo(xpto700).
+atoMedico(D,IDU,IDS,C) :: (encontrar(Cs,(atoMedico(04-04-2017, 2, 1, Cs),nao(nulo(Cs))),S), comprimento(S,N), N==0).   

% Num certo dia, o sistema informático de resgisto de atos médicos não estava operacional, o que impediu o médico de registar o ato médico de um utente e ele não se lembrou da data
%quando o sistema ficou operacional e, por isso, torna-se impossível saber quando aconteceu o ato médico.

atoMedico(xpto800, 4, 7, 30).
excecao(atoMedico(D,IDU,IDS,C)) :- atoMedico(xpto800,IDU,IDS,C).
nulo(xpto800).
+atoMedico(D,IDU,IDS,C) :: (encontrar(Ds,(atoMedico(Ds, 4, 7, 30),nao(nulo(Ds))),S), comprimento(S,N), N==0).

% O João atingiu a idade adulta, mas frequentava a pediatria porque gostava mais do médico e tinha uma estatura baixa, logo passava despercebido. Certo dia alguém lhe perguntou a idade
%e pediram-lhe a identificação. Após verem a identificação, perceberam que tinha mais de 18 anos e, por isso, proibíram-no de ir à pediatria.

atoMedico(xpto900,2,3,xpto999).
excecao(atoMedico(D,IDU,IDS,C)) :- atoMedico(xpto900,IDU,IDS,xpto999).
nulo(xpto900).
nulo(xpto999).
+atoMedico(D,IDU,IDS,C) :: (encontrar((Ds,Cs),(atoMedico(Ds, 2, 3, Cs),nao(nulo(Ds)),nao(nulo(Cs))),S), comprimento(S,N), N==0).