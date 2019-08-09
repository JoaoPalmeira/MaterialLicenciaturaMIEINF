%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Programacao em logica estendida

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: definicoes iniciais


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado servico: Serviço, Enfermeira ->{ V,F,D }

-servico(Ser,Enf) :- nao(servico(Ser,Enf)), nao(excecao(servico(Ser,Enf))).

servico(ortopedia,amelia).
servico(obstetricia,ana).
servico(obstetricia,maria).
servico(obstetricia,mariana).
servico(geriatria,sofia).
servico(geriatria,susana).


servico(xpto007,teodora).
excecao( servico(Ser,Enf) ) :- servico(xpto007,Enf).

servico(xptoNP9,zulimira).
excecao(servico(Ser,Enf)) :- servico(xptoNP9,Enf).
nulointerdito(xptoNP9).
+servico(Ser,Enf) :: solucoes((Sers,Enf), servico(Sers,Enf), nao(nulointerdito(Sers),S)), comprimento(S,N), N==0. 



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado ato: Ato, Enfermeira, Utente, Data ->{ V,F,D }

-ato(Ato,Enf,Ut,Dt) :- nao(ato(Ato,Enf,Ut,Dt)), nao(excecao(Ato,Enf,Ut,Dt)).

ato(penso, ana, joana, sabado).
ato(gesso, amelia, jose, domingo).

ato(xpto017,mariana,joaquina,domingo).
excecao( ato(Ato,Enf,Ut,Dt) ) :- ato(xpto017,Enf,Ut,Dt).

ato(domicilio, maria, xpto121,xpto251).
excecao( ato(Ato,Enf,Ut,Dt) ) :- ato(Ato,Enf,xpto121,xpto251).
% excecao( ato(Ato,Enf,Ut,Dt) ) :- ato(Ato,Enf,xpto121,Dt).
% excecao( ato(Ato,Enf,Ut,Dt) ) :- ato(Ato,Enf,Ut,xpto251).

ato(domicilio, susana, (joao,jose), segunda).

ato(sutura, xpto313,josue,segunda).
excecao( ato(Ato,Enf,Ut,Dt) ) :- ato(Ato,xpto313,Ut,Dt).

ato(sutura, [maria,mariana], josefa, [terca,quarta,quinta,sexta]).
ato(penso, ana, jacinta, [segunda,terca,quarta,quinta,sexta]).


+ato(Ser,Enf,Ut,feriado) :: solucoes((Ser,Enf,Ut,feriado), servico(Ser,Enf,Ut,feriado),S)), comprimento(S,N), N==0.

-ato(Ser,Enf,Ut,Dt) :: solucoes((Sers,Enf,Uts,Dts), servico(Sers,Enf,Uts,Dts), S), comprimento(S,N), N==0.










%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demo: Questao,Resposta -> {V,F}

demo( Questao,verdadeiro ) :-
    Questao.
demo( Questao,falso ) :-
    -Questao.
demo( Questao,desconhecido ) :-
    nao( Questao ),
    nao( -Questao ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demo: Lista_Questao,Resposta -> {V,F}

demo([H|T],verdadeiro ) :-
    H,
    demo(T,verdadeiro).
demo( [H|T],falso ) :-
    -H.
demo([H|T],falso):-
    demo(T,falso).
demo([H|T],desconhecido ) :-
    nao( H ),
    nao( -H).
demo([H|T],desconhecido):-
    demo(T,desconhecido).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado nao: Questao -> {V,F}

nao( Questao ) :-
    Questao, !, fail.
nao( Questao ).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado comprimento: Lista, Resultado -> {V,F}

comprimento([],0).
comprimento([X|L],R) :- comprimento(L,N) , R is 1+N.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado solucoes: Termo,Questao, Solucao -> {V,F}

solucoes(T,Q,S) :- findall(T,Q,S).


insercao(T) :- assert(T).
insercao(T) :- retract(T),!,fail.

teste([]).
teste([I|L]) :- I, teste(L).

evolucao( Termo ) :-
	solucoes( Invariante,+Termo::Invariante,Lista), 
				insercao(Termo), 
					teste(Lista).



remocao(T) :- retract(T).
remocao(T) :- assert(T),!,fail.

involucao( Termo ) :-
	Termo,
		solucoes(Invariante, -Termo::Invariante, Lista),
			remocao(Termo),
				teste(Lista).

