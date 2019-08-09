%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Programacao em logica estendida
%
% Representacao de conhecimento imperfeito

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

-filho( F,P ) :-
    nao( filho( F,P ) ),
    nao( excecao( filho( F,P ) ) ).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+filho( F,P ) :: (solucoes( (F,P),(filho( F,P )),S ),
                  comprimento( S,N ), N == 1
                  ).

% Invariante Referencial: nao admitir mais do que 2 progenitores
%                         para um mesmo individuo

+filho( F,P ) :: (solucoes( (Ps),(filho( F,Ps )),S ),
                  comprimento( S,N ), N =< 2
                  ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Explicitacao das situacoes de excecao

% A Belem é filha de uma pessoa de que se desconhece a identidade

filho( belem,xpto023 ).
excecao( filho( F,P ) ) :-
    filho( F,xpto023 ).

% A Maria é filha do Faria ou do Garcia

excecao( filho( maria,faria ) ).
excecao( filho( maria,garcia ) ).

% O Julio tem um filho que ninguem pode conhecer

filho( xpto732,julio ).
excecao( filho( F,P ) ) :-
    filho( xpto732,P ).
nulo( xpto732 ).
+filho( F,P ) :: (solucoes( (Fs,P),(filho(Fs,julio),nao(nulo(Fs))),S ),
                  comprimento( S,N ), N == 0 
                  ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

evolucao( Termo ) :-
    solucoes( Invariante,+Termo::Invariante,Lista ),
    insercao( Termo ),
    teste( Lista ).

insercao( Termo ) :-
    assert( Termo ).
insercao( Termo ) :-
    retract( Termo ),!,fail.

teste( [] ).
teste( [R|LR] ) :-
    R,
    teste( LR ).

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
% Extensao do meta-predicado nao: Questao -> {V,F}

nao( Questao ) :-
    Questao, !, fail.
nao( Questao ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

solucoes( X,Y,Z ) :-
    findall( X,Y,Z ).

comprimento( S,N ) :-
    length( S,N ).



%Ficha 7

-jogo(I,A,C) :- nao(jogo(I,A,C)), nao(excecao(jogo(I,A,C))).


%iii

excecao(jogo(3,cc,500)).
excecao(jogo(3,cc,2500)).


%iv 

jogo(4,dd,xpto4000).
excecao(jogo(I,A,C)) :- jogo(I,A,xpto4000).


%v 

jogo(5,ee,xpto5000).
excecao(jogo(I,A,C)) :- jogo(I,A,xpto5000).
nulo(xpto5000).

+jogo(I,A,C) :: (solucoes((I,A,Cs),jogo(4,ee,Cs),nao(nulo(Cs)),S), comprimento(S,N), N==0).


%vi

jogo(6,ff,250).

excecao(jogo(6,ff,X)) :- X>=5000.


%vii

-jogo(7,gg,2500).
jogo(7,gg,xpto7000).
excecao(jogo(I,A,C)) :- jogo(I,A,xpto7000).


%vii

jogo(8,hh,1000).


%ix

excecao(jogo(9,ii,3000)).


%Ficha 8 (versao pior)

%i)

filho(ana, abel).
filho(ana, alice).

%nascer : P, D -> {V,F}
nascer(ana, 01-01-2010).

%ii)

filho(anibal, antonio).
filho(anibal, alberta).

nascer(anibal, 02-01-2010).

%iii)

filho(berta, bras).
filho(berto, bras).
filho(berta, belem).
filho(berto, belem).

nascer(berta, 02-02-2010).
nascer(berto, 02-02-2010).

%iv)

nascer(catia, 03-03-2010).

%v)

filho(crispim, catia).
excecao(filho(crispim, celso)).
excecao(filho(crsipim, caio)).

%vi)

filho(danilo, daniel).
filho(danilo, xpto806)
excecao(filho(F,P)) :- filho(F,xpto806).

nascer(danilo, 04-04-2010).

%vii)

filho(eurico, elias).
filho(eurico, elsa).

-nascer(P,D) :- nao(nascer(P,D)), nao(excecao(nascer(P,D))).

excecao(nascer(eurico, 05-05-2010)).
excecao(nascer(eurico, 15-05-2010)).
excecao(nascer(eurico, 25-05-2010)).

%viii)

excecao(filho(fabia, fausto)).
excecao(filho(otavia, fausto)).

filho(fabia, xpto808).
filho(otavia, xpto808).
excecao(filho(F,P)) :- filho(F,xpto808).

%ix)

filho(golias, guido).
filho(golias, guida).

nascer(golias, xpto809).
excecao(nascer(P,Data)) :- nascer(P,xpto809).
nulo(xpto809).

+nascer(P,D) :: (solucoes((Data), (nascer(golias, Data)), nao(nulo(Data)), S),
                 comprimento(S,N), N==0).

%x)

-nascer(helder, 08-08-2010).

nascer(helder, xpto810).
excecao(nascer(P,D)) :- nascer(P,xpto810).

%xi)



%Ficha 8 

%pai: P,F -> {V,F}

%mae: P,F -> {V,F}

-pai( P,F ) :-
    nao( pai( P,F ) ),
    nao( excecao( pai( P,F ) ) ).

-mae( M,F ) :-
    nao( mae( M,F ) ),
    nao( excecao( mae( M,F ) ) ).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+pai( P,F ) :: (solucoes( (P,F),(pai( P,F )),S ),
                  comprimento( S,N ), N == 1
                  ).

% Invariante Referencial: nao admitir mais do que 1 pai
%                         para um mesmo individuo

+pai( P,F ) :: (solucoes( (Ps),(pai( Ps,F )),S ),
                  comprimento( S,N ), N =< 1
                  ).

% Invariante Estrutural:  nao permitir a insercao de conhecimento
%                         repetido

+mae( M,F ) :: (solucoes( (M,F),(pai( M,F )),S ),
                  comprimento( S,N ), N == 1
                  ).

% Invariante Referencial: nao admitir mais do que 1 mãe
%                         para um mesmo individuo

+mae( M,F ) :: (solucoes( (Ms),(pai( Ms,F )),S ),
                  comprimento( S,N ), N =< 1
                  ).

%i)

pai(abel, ana).
mae(alice, ana).

%nascer : C, D, M, A -> {V,F}
nascer(ana, 01, 01, 2010).

%ii)

pai(antonio, anibal).
mae(alberta, anibal).

nascer(anibal, 02, 01, 2010).

%iii)

pai(bras, berta).
pai(bras, berto).
mae(belem, berta).
mae(belem, berto).

nascer(berta, 02, 02, 2010).
nascer(berto, 02, 02, 2010).

%iv)

nascer(catia, 03, 03, 2010).

%v)

mae(catia, crispim).
excecao(pai(celso, crispim)).
excecao(pai(caio, crispim)).

%vi)

pai(daniel, danilo).
mae(xpto806, danilo).
excecao(mae(M,F)) :- mae(xpto806,F).

nascer(danilo, 04, 04, 2010).

%vii)

pai(elias, eurico).
mae(elsa, eurico).

-nascer(C,D,M,A) :- nao(nascer(C,D,M,A)), nao(excecao(nascer(C,D,M,A))).

excecao(nascer(eurico, 05, 05, 2010)).
excecao(nascer(eurico, 15, 05, 2010)).
excecao(nascer(eurico, 25, 05, 2010)).

%viii)

excecao(pai(fausto,fabia)).
excecao(pai(fausto,otavia)).

mae(xpto808,fabia).
mae(xpto808,otavia).
excecao(mae(M,F)) :- mae(xpto808,F).

%ix)

pai(guido, golias).
mae(guida, golias).

nascer(golias, xpto809, xpto890, xpto899).
excecao(nascer(C,D,M,A)) :- nascer(C,xpto809,xpto890,xpto899).
nulo(xpto809).

+nascer(C,D,M,A) :: (solucoes((Dia,Mes,Ano), (nascer(golias, Dia,Mes,Ano)), nao(nulo(Dia,Mes,Ano)), S),
                     comprimento(S,N), N==0).

%x)

-nascer(helder, 08, 08, 2010).

nascer(helder, xpto810, xpto818, xpto819).
excecao(nascer(C,D,M,A)) :- nascer(C,xpto810,xpto818,xpto819).

%xi)

excecao(nascer(ivo,D,06,2010)) :- D>=1, D<=15.
















