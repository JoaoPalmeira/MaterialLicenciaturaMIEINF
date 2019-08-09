

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado cuidados: idServ, Descricao, Instituicao, Cidade -> {V,F}

cuidados(1, cardiologia, hospital_de_Braga, braga).
cuidados(2, oftalmologia, hospital_de_Bragança, bragança).
cuidados(3, pediatria, hospital_da_Trofa, braga).
cuidados(4, neurologia, hospital_de_Guimarães, guimarães).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado atoMedico: Data, idUt, idServ, Custo -> {V,F}

atoMedico(01-01-2001, 9, 1, 30).
atoMedico(14-10-2009, 2, 2, 18).
atoMedico(21-01-2014, 15, 4, 23).
atoMedico(30-07-2010, 8, 3, 50).
atoMedico(30-08-2015, 8, 1, 40).
atoMedico(20-11-2016, 9, 2, 20).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado medico: idMed, Nome, IDade, IDserv, Morada -> {V,F}

medico (1,joao_coutinho,46,1,rua_de_Lisboa).
medico (2,ana_margarida,32,3,rua_da_Lapa).
medico (3,filipa_sofia,26,2,rua_das_Andorinhas).
-(medico (4,maria_moutinho,37,4,rua_da_Bouça).)
%maria_moutinho teve a licensa retirada (conhecimento negativo forte)





% Representar conhecimento positivo e negativo;
% Representar casos de conhecimento imperfeito, pela utilização de valores nulos de todos os tipos estudados;
% Manipular invariantes que designem restrições à inserção e à remoção de conhecimento do sistema;
% Lidar com a problemática da evolução do conhecimento, criando os procedimentos adequados;
% Desenvolver um sistema de inferência capaz de implementar os mecanismos de raciocínio inerentes a estes sistemas.