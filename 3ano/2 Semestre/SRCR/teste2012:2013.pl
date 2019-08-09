%Grupo 1
%1
%a

partida(Origem, Destino, H, M).
chegada(Origem, Destino, H, M).

%b
chegada(Porto, Guimaraes, 16, 0).
partida(Guimaraes, Porto, 16, 15).
partida(Guimaraes, Porto, 21, 45).
excecao(chegada(Guimaraes, Trofa, atrasado, atrasado)).
excecao(chegada(Trofa, Guimaraes, 17, 0)).
excecao(chegada(Trofa, Guimaraes, 17, 30))
excecao(partida(Guimaraes, Trofa, 16, 45)).
excecao(partida(Guimaraes, Fafe, 16, 45)).
excecao(partida(Guimaraes, Braga, 16, 45)).
excecao(chegada(Braga, cancelado, 19, 0)).
partida(xpto1, Braga, 21, 0).
chegada(xpto1, Braga, 21, 0).

excecao(Origem, Destino, H, M):- partida(xpto1, Destino, H, M).
+partida(Origem, Destino, H, M) :: findall((Origem, Destino, H, M),(partida(Origem, Destino, H, M), nao(nulo(xpto1))), S),
								   length(S,N), N==0.
excecao(Origem, Destino, H, M):- chegada(xpto1, Destino, H, M).
+chegada(Origem, Destino, H, M) :: findall((Origem, Destino, H, M),(chegada(Origem, Destino, H, M), nao(nulo(xpto1))), S),
								   length(S,N), N==0.
nulo(xpto1).

%c
-partida(O,D,H,M)  ::  (findall((D,HH,MM), chegada(O,D,HH,MM), S)),
						comprimento(S,N), N>0).

%d
+chegada(O,D,H,M) :: ( O\=D ).
+partida(O,D,H,M) :: ( O\=D ).

remove(X,[],[]).
remove(X,[X|L],L).
remove(X,[Y|L],LR) :- X\=Y,
					  remove(X,L,LR).


%Grupo2
%1 - F, um termo pode ser unificado com outro termo se os símbolos e a aridade das funções sob as quais eles ocorrem são idênticos e se os parâmetros podem ser unificados simultaneamente.

%2 - F, comparação.

%3 - V

%4 - V

%


