USE comboioslusitanos;

SELECT *
	FROM bilhete;
    
SELECT *
	FROM cliente;
    
SELECT *
	FROM comboio;

SELECT *
	FROM endereco;

SELECT *
	FROM lugar;
    
SELECT *
	FROM reserva;

SELECT *
	FROM viagem;

SELECT Email, nome, DataNascimento
	FROM cliente
	WHERE sexo = 'F';

SELECT Email, nome, DataNascimento
	FROM cliente
	WHERE sexo = 'F' AND DATE(datanascimento) <> '1965-07-02';

SELECT datapartida, custodesconto, nrbilhete
	FROM bilhete
	WHERE nrbilhete IN (9,2,19,10,8) 
    ORDER BY nrbilhete DESC;

SELECT codigopostal, rua
	FROM endereco 
    WHERE localidade = 'Braga';
    
SELECT count(*)
	FROM bilhete;

SELECT count(*)
	FROM cliente;

SELECT MIN(custodesconto) AS BilheteMaisBarato
	FROM bilhete;

SELECT custodesconto, nrbilhete     
	FROM bilhete
	ORDER BY custodesconto 
    LIMIT 4;

SELECT *
	FROM Cliente AS C INNER JOIN Endereco AS E
		ON C.email=E.cliente_email;

-- número de bilhetes vendidos num intervalo de tempo
SELECT COUNT(nrbilhete)
	FROM Bilhete AS B INNER JOIN Reserva AS R
		ON B.Reserva_idR=R.idR
		WHERE R.Data between '2016-11-11' and '2016-11-13'
        ORDER BY nrbilhete;

-- número de cada bilhete vendido num intervalo de tempo 
SELECT nrbilhete
	FROM Bilhete AS B INNER JOIN Reserva AS R
		ON B.Reserva_idR=R.idR
		WHERE R.Data between '2016-11-11' and '2016-11-13'
        ORDER BY nrbilhete;
        
-- lucro total custo normal de todas as viagens
SELECT SUM(custoNormal)
	FROM Bilhete AS B INNER JOIN Viagem AS V 
		ON B.viagem_idV = V.idV;

-- nova reserva
INSERT INTO Reserva
	 (idR, Data, Cliente_Email)
    VALUES (99, '2016-11-23', 'josecidplatina@gmail.com');

-- novo bilhete
INSERT INTO Bilhete
   (NrBilhete, DataChegada, DataPartida, CustoNormal, CustoDesconto, NumeroB, CarruagemB, ClasseB, Viagem_idV, Reserva_idR)
	VALUES (100, '2016-12-25','2016-12-23', 9, 7, 3, 2, 'económica', 11, 99);

DELETE FROM Cliente
	WHERE email = 'ola@gmail.com';

-- lucro custo normal de certa viagem
SELECT SUM(custoNormal)
	FROM Bilhete
		WHERE viagem_idV = 202;

-- criação de uma tabela auxiliar com bilhetes da classe executiva
CREATE VIEW Normal AS
SELECT nrbilhete, DataChegada, DataPartida, CustoNormal AS Custo, numeroB, carruagemB, classeB, Viagem_idV, Reserva_idR
	FROM Bilhete
    WHERE ClasseB='executiva';

-- criação de uma tabela auxiliar com bilhetes da classe económica
CREATE VIEW Barato AS
SELECT nrbilhete, DataChegada, DataPartida, CustoNormal AS Custo, numeroB, carruagemB, classeB, Viagem_idV, Reserva_idR
	FROM Bilhete
    WHERE ClasseB='económica';
    
INSERT INTO Reserva
	(idR, Data, Cliente_Email)
    VALUES(30,'2016-11-25', 'sofifreitas@hotmail.com');
    
INSERT INTO Bilhete
   (NrBilhete, DataChegada, DataPartida, CustoNormal, CustoDesconto, NumeroB, CarruagemB, ClasseB, Viagem_idV, Reserva_idR)
	VALUES(60, '2016-11-25', '2016-11-25', 15, 10.5, 8, 2, 'económica', 11, 30);

-- A SUPER DIFICIL. SABEMOS OS CUSTOS REAIS DE CADA CENA AGORA. PROBLEMA DOS DESCONTOS RESOLVIDOS
SELECT (CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END) AS CustoReal, CustoNormal, idR, Cliente_Email, Data, DataPartida
	FROM Reserva AS R INNER JOIN Bilhete AS B
    WHERE R.idR=B.Reserva_idR;
    
-- montante de cada reserva
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS CustoPorReserva, idR, Cliente_Email
	FROM Reserva AS R INNER JOIN Bilhete AS B
    ON R.idr=B.reserva_idr
    INNER JOIN Cliente AS C
    ON R.cliente_email = C.email
    GROUP BY idr;
    
-- montante a pagar por cada cliente pelas suas reservas
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS CustoPorCliente, Nome, Cliente_Email
	FROM Reserva AS R INNER JOIN Bilhete AS B
    ON R.idr=B.reserva_idr
    INNER JOIN Cliente AS C
    ON R.cliente_email = C.email
    GROUP BY email;
    
-- montante a pagar pelas reservas de um dado cliente
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS CustoDeUmCliente, Nome, Cliente_Email
	FROM Reserva AS R INNER JOIN Bilhete AS B
    ON R.idr=B.reserva_idr
    INNER JOIN Cliente AS C
    ON R.cliente_email = C.email
    WHERE nome = 'Sofia';
    
-- montante a pagar por uma dada reserva
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS CustoDeUmaReserva, Nome, Cliente_Email
	FROM Reserva AS R INNER JOIN Bilhete AS B
    ON R.idr=B.reserva_idr
    INNER JOIN Cliente AS C
    ON R.cliente_email = C.email
    WHERE idr = 4;

-- lucro total de uma viagem
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS LucroDeUmaViagem, idV, comboio_idc
    FROM Reserva AS R INNER JOIN Bilhete AS B
        ON R.idr=B.reserva_idr
        INNER JOIN Viagem AS V
		ON B.Viagem_idV=V.idV
        WHERE idV = 11;
        
-- lucro total de cada viagem
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS LucroDeCadaViagem, idV, comboio_idc
    FROM Reserva AS R INNER JOIN Bilhete AS B
        ON R.idr=B.reserva_idr
        INNER JOIN Viagem AS V
		ON B.Viagem_idV=V.idV
        GROUP BY idv;
        
-- lucro total de todas as viagens
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS LucroTotal
    FROM Reserva AS R INNER JOIN Bilhete AS B
        ON R.idr=B.reserva_idr
        INNER JOIN Viagem AS V
		ON B.Viagem_idV=V.idV;

-- numero total de utilizadores que compram bilhete
SELECT COUNT(*)
	FROM (SELECT DISTINCT email 
		FROM Cliente AS C INNER JOIN Reserva AS R
			ON C.email=R.Cliente_Email) AS E; 

-- inserção de outra reserva feita por um utilizador que já efetuou alguma
INSERT INTO Reserva
	(idR, Data, Cliente_Email)
    VALUES(26,'2016-10-5', 'sofifreitas@hotmail.com');
    
-- id reservas feitas por um utilizador
SELECT idR, Cliente_Email
	FROM Reserva AS R INNER JOIN Cliente AS C
		WHERE R.Cliente_Email=C.Email
			ORDER BY email;
		
-- número dos bilhetes comprados por um utilizador
SELECT NrBilhete, Cliente_Email
	FROM Bilhete AS B INNER JOIN Reserva AS R
		WHERE B.Reserva_idR=R.idR;

-- numero de reservas feitas por um dado utilizador (para a transação em que utilizador verifica quantas reservas efetuou)
SELECT nome, COUNT(idr)
	FROM Reserva AS R INNER JOIN Cliente AS C
    ON R.cliente_email=C.email
    WHERE nome = 'Sofia';
    
SELECT nome, COUNT(idr)
	FROM Reserva AS R INNER JOIN Cliente AS C
    ON R.cliente_email=C.email
    WHERE nome = 'José';
    
-- numero de reservas feitas por cada utilizador
SELECT nome, COUNT(idr)
	FROM Reserva AS R INNER JOIN Cliente AS C
    ON R.cliente_email=C.email
    GROUP BY nome;

-- numero de bilhetes comprados por um dado utilizador 
SELECT nome, COUNT(nrbilhete)
	FROM Bilhete AS B INNER JOIN Reserva AS R
    ON B.reserva_idr=R.idr
       INNER JOIN Cliente AS C
	   ON R.cliente_email=C.email
       WHERE nome = 'Sofia';
       
-- quantidade de bilhetes comprados por cada utilizador
SELECT nome, COUNT(nrbilhete)
	FROM Bilhete AS B INNER JOIN Reserva AS R
    ON B.reserva_idr=R.idr
       INNER JOIN Cliente AS C
	   ON R.cliente_email=C.email
       GROUP BY nome;
       
-- bilhetes comprados por cada utilizador (porque uma reserva pode ter vários bilhetes)
SELECT nome, nrbilhete
	FROM Bilhete AS B INNER JOIN Reserva AS R
    ON B.reserva_idr=R.idr
       INNER JOIN Cliente AS C
	   ON R.cliente_email=C.email;
       
-- bilhetes comprados por cada utilizador e origem e destino de cada um dos bilhetes
SELECT nome, nrbilhete, origem, destino
	FROM Viagem AS V INNER JOIN Bilhete AS B 
    ON V.idv=B.viagem_idv
      INNER JOIN Reserva AS R
      ON B.reserva_idr=R.idr
		 INNER JOIN Cliente AS C
         ON R.cliente_email=C.email;

-- comboio destinado a cada bilhete e origem e destino da viagem correspondente a esse bilhete
SELECT nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    ORDER BY nrbilhete;

-- comboios e bilhetes associados a uma dada viagem 
SELECT idv, nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    WHERE idv=11;
    
SELECT idv, nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    WHERE origem = 'Braga' OR destino = 'Madrid';

-- viagens com origem em Braga ou destino Madrid
SELECT idv, nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    WHERE origem = 'Braga' OR destino = 'Madrid'
    ORDER BY origem;

-- viagens e comboios respetivo (para a transaçao em que utilizador verifica qual o comboio de cada viagem)
SELECT idv, comboio_idc, origem, destino FROM Viagem;

-- todas viagens as com origens e destinos correspondentes
SELECT idv, nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    ORDER BY idv;
    
-- comboio de uma determinada viagem (para a transaçao em que utilizador verifica qual o comboio de cada viagem)
SELECT idv, comboio_idc, origem, destino
    FROM Viagem
    WHERE idv = 11;
    
SELECT idv, comboio_idc, origem, destino
    FROM Viagem
    WHERE origem = 'Porto' AND destino = 'Braga';
    
-- viagens e horários respetivos (para a transação em que se identifica o horário da viagem)
SELECT idv, horariopartida, horariochegada, origem, destino
    FROM Viagem;
    
-- horário correspondente a cada bilhete
SELECT nrbilhete, horariopartida, horariochegada, origem, destino, datapartida, datachegada, idv
	FROM Viagem AS V INNER JOIN Bilhete AS B
    ON V.idv = B.viagem_idv
    ORDER BY nrbilhete;
    
-- lugares ocupados em cada comboio numa viagem
SELECT idc, numerob, carruagemb, classeb, nrbilhete, idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       ORDER BY idc;
       
-- numero de lugares ocupados em cada comboio numa viagem
SELECT idc, count(numerob), idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       GROUP BY idc;
       
-- numero de lugares ocupados num dado comboio numa viagem
SELECT idc, count(numerob), idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       WHERE idc = 1;
       
-- numero de lugares livres num comboio de uma dada viagem
SELECT idc, idv, (Lotacao-COUNT(numerob)) AS LugLiv, DataPartida, HorarioPartida, DataChegada, HorarioChegada, Origem, Destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       GROUP BY NrBilhete
       Order By idC;

-- numero de lugares livres num dado comboio numa viagem (para a transação do numero de lugares livres de um comboio para uma viagem)
SELECT idc, lotacao-count(numerob), idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       WHERE idc = 1;
       
-- transação que permite inserir um novo cliente e alterar o seu nome e género
START TRANSACTION;
INSERT INTO Cliente
 (Email, NIF, Nome, Sexo, DataNascimento, Password, Telefone, Telemovel)
    VALUES ('ola@gmail.com', 999999999, 'António', 'M', '1980-01-01', 'olaola', 253444756, 962101407);
UPDATE Cliente
	SET nome = 'Carlos'
	WHERE email='ola@gmail.com';
UPDATE Cliente
	SET password = 'adeus'
	WHERE email='ola@gmail.com';
SELECT * 
	FROM Cliente
	WHERE email = 'ola@gmail.com';
ROLLBACK;

-- transação que permite identificar o horário de cada viagem, ordenado pelo horário de partida
START TRANSACTION;
SELECT horarioPartida, horarioChegada, idV
	FROM viagem
    ORDER BY HorarioPartida;
ROLLBACK;

INSERT INTO Bilhete
   (NrBilhete, DataChegada, DataPartida, CustoNormal, CustoDesconto, NumeroB, CarruagemB, ClasseB, Viagem_idV, Reserva_idR)
	VALUES (101, '2016-12-25','2016-12-23', 9, 7, 3, 1, 'executiva', 11, 99);

-- transação que permite identificar o número total de lugares livres de um comboio para uma viagem
START TRANSACTION;
SELECT nrbilhete, idc, idv, (Lotacao-COUNT(numerob)) AS LugaresLivres, DataPartida, DataChegada
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       GROUP BY idV
       Order By idC;
ROLLBACK;

-- transação que permite a um cliente verificar quantas reservas efetuou
START TRANSACTION;
SELECT email, nome, COUNT(*) AS QuantasReservas
	FROM Reserva AS R INNER JOIN Cliente AS C
    WHERE R.Cliente_Email = C.email
    GROUP BY email
    ORDER BY idR;
ROLLBACK;

-- transação que permite verificar qual o comboio de cada viagem
START TRANSACTION;
SELECT Comboio_idC, origem, destino, idV
	FROM Viagem AS V
    ORDER BY Comboio_idC;
ROLLBACK;

-- transação que permite a um cliente reservar uma viagem para uma determinada data e num determinado lugar do comboio
START TRANSACTION;
SELECT 

-- lugares livres em cada comboio (maybe right maybe wrong?)
SELECT idc, nrbilhete, numerob, origem, destino, datapartida, datachegada, idv, numero, carruagem
    FROM Lugar AS L INNER JOIN Comboio C
    ON L.comboio_idc = C.idc
    INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
	   WHERE numerob <> numero;
 
-- Saber os destinos de uma certa origem
delimiter $$
CREATE PROCEDURE Destinos (IN part CHAR(20))
BEGIN
	SELECT Viagem.Origem, Viagem.Destino
    FROM Viagem
    WHERE Viagem.Origem = part;
END $$

CALL Destinos ("Barcelona")

-- Saber de onde partem os comboios para um certo destino
delimiter $$
CREATE PROCEDURE Origens (IN dest CHAR(20))
BEGIN
	SELECT Viagem.Origem, Viagem.Destino
    FROM Viagem
    WHERE Viagem.Destino = dest;
END $$

CALL Origens ("Braga")
       