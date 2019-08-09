USE comboioslusitanos;

-- bilhete com custo mínimo
CREATE VIEW BilheteMínimo AS 
SELECT MIN(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END) AS BilheteMaisBarato
	FROM Bilhete AS B INNER JOIN Reserva AS R;
  
-- bilhete com custo máximo
CREATE VIEW BilheteMáximo AS
SELECT MAX(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END) AS BilheteMaisBarato
	FROM Bilhete AS B INNER JOIN Reserva AS R;
  
-- 4 bihetes com menor custo
CREATE VIEW 4BilhetesMínimos AS 
SELECT DISTINCT nrbilhete, (CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END) AS custo  
	FROM Bilhete AS B INNER JOIN Reserva AS R
	ORDER BY custo
    LIMIT 4;

-- junção da tabela Cliente com os seus Endereços
CREATE VIEW ClienteEOSeuEndereço AS
SELECT *
	FROM Cliente AS C INNER JOIN Endereco AS E
		ON C.email=E.cliente_email;
        
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

-- custo real bilhete        
CREATE VIEW CustoBilhete AS 
SELECT (CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END) AS CustoReal, CustoNormal, idR, Cliente_Email, Data, DataPartida
	FROM Reserva AS R INNER JOIN Bilhete AS B
    WHERE R.idR=B.Reserva_idR;

-- montante de cada reserva
CREATE VIEW MontanteReserva AS
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS CustoPorReserva, idR, Cliente_Email
	FROM Reserva AS R INNER JOIN Bilhete AS B
    ON R.idr=B.reserva_idr
    INNER JOIN Cliente AS C
    ON R.cliente_email = C.email
    GROUP BY idr;

-- montante a pagar por cada cliente pelas suas reservas
CREATE VIEW CustoReservasCliente AS
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS CustoPorCliente, Nome, Cliente_Email
	FROM Reserva AS R INNER JOIN Bilhete AS B
    ON R.idr=B.reserva_idr
    INNER JOIN Cliente AS C
    ON R.cliente_email = C.email
    GROUP BY email;
    
-- lucro total de cada viagem
CREATE VIEW LucroViagem AS
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS LucroDeCadaViagem, idV, comboio_idc
    FROM Reserva AS R INNER JOIN Bilhete AS B
        ON R.idr=B.reserva_idr
        INNER JOIN Viagem AS V
		ON B.Viagem_idV=V.idV
        GROUP BY idv;

-- lucro total de todas as viagens
CREATE VIEW LucroViagens AS 
SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS LucroTotal
    FROM Reserva AS R INNER JOIN Bilhete AS B
        ON R.idr=B.reserva_idr
        INNER JOIN Viagem AS V
		ON B.Viagem_idV=V.idV;
	
-- numero total de utilizadores que compram bilhete
CREATE VIEW NúmeroUtilizadoresCompramBilhete AS
SELECT COUNT(*)
	FROM (SELECT DISTINCT email 
		FROM Cliente AS C INNER JOIN Reserva AS R
			ON C.email=R.Cliente_Email) AS E;
            
-- id reservas feitas por um utilizador
CREATE VIEW ReservasDeUtilizador AS
SELECT idR, Cliente_Email
	FROM Reserva AS R INNER JOIN Cliente AS C
		WHERE R.Cliente_Email=C.Email
			ORDER BY email;
            
-- numero de reservas feitas por cada utilizador
CREATE VIEW NúmeroReservasUtilizador AS
SELECT nome, COUNT(idr)
	FROM Reserva AS R INNER JOIN Cliente AS C
    ON R.cliente_email=C.email
    GROUP BY nome;
    
-- quantidade de bilhetes comprados por cada utilizador
CREATE VIEW QuantidadeBilhetesUtilizador AS
SELECT nome, COUNT(nrbilhete)
	FROM Bilhete AS B INNER JOIN Reserva AS R
    ON B.reserva_idr=R.idr
       INNER JOIN Cliente AS C
	   ON R.cliente_email=C.email
       GROUP BY nome;

-- bilhetes comprados por cada utilizador e origem e destino de cada um dos bilhetes
CREATE VIEW BilhetesCompradosUtilizador AS
SELECT nome, nrbilhete, origem, destino
	FROM Viagem AS V INNER JOIN Bilhete AS B 
    ON V.idv=B.viagem_idv
      INNER JOIN Reserva AS R
      ON B.reserva_idr=R.idr
		 INNER JOIN Cliente AS C
         ON R.cliente_email=C.email;

-- comboio destinado a cada bilhete e origem e destino da viagem correspondente a esse bilhete
CREATE VIEW ComboioBilhete AS
SELECT nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    ORDER BY nrbilhete;

-- viagens e comboios respetivos
CREATE VIEW ViagemComboio AS
SELECT idv, comboio_idc, origem, destino FROM Viagem;

-- todas viagens com origens e destinos correspondentes
CREATE VIEW Viagens AS
SELECT idv, nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    ORDER BY idv;

-- viagens e horários respetivos 
CREATE VIEW ViagensHorários AS
SELECT idv, horariopartida, horariochegada, origem, destino
    FROM Viagem;
    
-- horário correspondente a cada bilhete
CREATE VIEW BilheteHorário AS
SELECT nrbilhete, horariopartida, horariochegada, origem, destino, datapartida, datachegada, idv
	FROM Viagem AS V INNER JOIN Bilhete AS B
    ON V.idv = B.viagem_idv
    ORDER BY nrbilhete;
    
-- lugares ocupados em cada comboio numa viagem
CREATE VIEW LugaresOcupadosViagem AS
SELECT idc, numerob, carruagemb, classeb, nrbilhete, idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       ORDER BY idc;
       
-- numero de lugares ocupados em cada comboio numa viagem
CREATE VIEW NúmeroLugaresOcupadosViagem AS
SELECT idc, count(numerob), idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       GROUP BY idc;
       
-- numero de lugares livres num comboio de uma dada viagem
CREATE VIEW NúmeroLugaresLivresViagem AS 
SELECT idc, idv, (Lotacao-COUNT(numerob)) AS LugLiv, DataPartida, HorarioPartida, DataChegada, HorarioChegada, Origem, Destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       GROUP BY NrBilhete
       Order By idC;
       
-- lugares livres em cada comboio
CREATE VIEW LugaresLivresComboio AS
SELECT idc, nrbilhete, numerob, origem, destino, datapartida, datachegada, idv, numero, carruagem
    FROM Lugar AS L INNER JOIN Comboio C
    ON L.comboio_idc = C.idc
    INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
	   WHERE numerob <> numero;