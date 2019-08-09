%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

% Representação de conhecimento imperfeito


% Incerto:

% Sabemos que o utente foi o 8º a ser registado, chamado André e com 21 anos de idade, mas devido a um incêndio, foi perdida parte da informação, não sabendo a morada do utente

utente(8,andre,21,xpto023).
excecao(utente(Id,N,I,M)):-
	utente(Id,N,I,xpto023).

% Nesse mesmo incêndio foi recolhida a informação relativamente ao 15º utente a ser registado, sendo essa informação a seguinte: É um utente de Bragança, chamado Joel, que no dia 21 de Janeiro de 2014 pagou um certo valor por uma consulta de neurologia (Id=4) na cidade de Guimarães.
Todos os outros dados são desconhecidos.

utente(15,joel,xpto123,braganca).
excecao(utente(Id,N,I,M)):-
	utente(Id,N,I,xpto123).
cuidados(4,neurologia,xpto312,guimaraes).
excecao(cuidados(Id,D,I,C)):-
	cuidados(Id,D,xpto312,guimaraes).
atoMedico(21-01-2014,15,4,xpto456).
excecao(atoMedico(D,IdU,IdS,C)):-
	atoMedico(D,IdU,IdS,xpto456).


% Relativamente à paciente número 9 a ser registada, sabemos apenas que se chama Sofia e que foi a uma consulta de cardiologia( Id=1 )

utente(9,sofia,xpto001,xpto002).
excecao(utente(IdU,N,I,M)):-
	utente(IdU,N,xpto001,xpto002).
cuidados(1,cardiologia,xpto003,xpto004).
excecao(cuidados(IdS,D,I,C)):-
	cuidados(IdS,D,xpto003,xpto004).


 

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% 
% Impreciso:


% Sabemos que o 2º utente a ser registado se chama João, tem 21 anos e reside em Braga, mas devido a uma falha no sistema não conseguimos apurar se ele foi a uma consulta de cardiologia(Id=1) ou de pediatria(Id=3), sabendo apenas que foi na cidade de Braga

utente(2,joao,21,braga).
excecao(cuidados(1,cardiologia,hospitalBraga,braga)).
excecao(cuidados(3,pediatria,hospitalTrofa,braga)).


% Devido a um aumento nos preços de cuidados médicos, a variar entre 5 a 10 euros, o utente João ( Nº 2 ), com 21 anos de idade e residente em Braga, não sabe quanto é que será o preço final de uma consulta de oftalmologia(Id=2), a ser realizada no dia 14 de Outubro de 2009, na cidade de Bragança, no Hospital de Bragança, sabendo apenas que inicialmente tinha um custo associado 18 euros.

cuidados(2,oftalmologia,hospitalBragança,bragança).
utente(2,joao,21,braga).
excecao(atoMedico(14-10-2009,2,2,Custo)):-
	Custo>=23,Custo<=28.

% Não sabemos qual dos 4 utentes que deu entrada no Hospital de Bragança, em Bragança, foi à consulta de oftalmologia(Id=4). Sabemos apenas as informações relativas a esses 4 utentes: O utente número 8 chama-se André, tem 21 anos de idade e veio da Selva, o utente número 2 chama-se João, tem 21 anos de idade e veio de Braga, o utente número 15 chama-se Joel, tem 21 anos de idade e veio de Bragança e por último o utente número 9 chama-se Sofia, tem 20 anos de idade e veio do Porto.

cuidados(2,oftalmologia,hospitalBragança,bragança).
excecao(utente(8,andre,21,selva)).
excecao(utente(2,joao,21,braga)).
excecao(utente(15,joel,21,bragança)).
excecao(utente(9,sofia,20,porto)).



