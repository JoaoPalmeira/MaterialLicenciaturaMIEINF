delimiter $$
CREATE PROCEDURE UtilizadoresDeUmDadoSexo (IN sex CHAR(1))
BEGIN
	SELECT Email, nome, DataNascimento
	FROM cliente
	WHERE sexo = sex;
END $$
CALL UtilizadoresDeUmDadoSexo ("M");

delimiter $$
CREATE PROCEDURE UtilizadoresDeUmDadoSexoComRestriçãoDeData (IN sex CHAR(1), dat1 Date)
BEGIN
	SELECT Email, nome, DataNascimento
	FROM cliente
	WHERE sexo = sex AND DATE(datanascimento) <> dat1;
END $$
CALL UtilizadoresDeUmDadoSexoComRestriçãoDeData ("F", '1994-07-02');

delimiter $$
CREATE PROCEDURE DataPartidaCustoComDescontoAtravésDeBilhetesDados (IN a1 Int, a2 Int, a3 Int, a4 Int, a5 Int)
BEGIN
    SELECT datapartida, custodesconto, nrbilhete
    FROM bilhete
    WHERE nrbilhete IN (a1,a2,a3,a4,a5) 
    ORDER BY nrbilhete DESC;
END $$
CALL DataPartidaCustoComDescontoAtravésDeBilhetesDados (9,2,19,10,8);

delimiter $$
CREATE PROCEDURE SaberDadosDeClienteDeCertaCidade (IN cid CHAR(20))
BEGIN
	SELECT nome, codigopostal, rua, localidade
	FROM endereco As B INNER JOIN cliente AS C
    WHERE B.Cliente_Email=C.Email AND localidade = cid;
END $$
CALL SaberDadosDeClienteDeCertaCidade ("Braga");

-- número de bilhetes vendidos num intervalo de tempo
delimiter $$
CREATE PROCEDURE NºdeBilhetesVendidosNumIntervaloDeTempo (IN dat1 DATE, dat2 DATE)
BEGIN
	SELECT COUNT(nrbilhete)
	FROM Bilhete AS B INNER JOIN Reserva AS R
		ON B.Reserva_idR=R.idR
		WHERE R.Data between dat1 and dat2
        ORDER BY nrbilhete;
END $$
CALL NºdeBilhetesVendidosNumIntervaloDeTempo ('2016-11-11', '2016-11-12');

-- número de cada bilhete vendido num intervalo de tempo 
delimiter $$
CREATE PROCEDURE NºdosBilhetesVendidosNumIntervaloDeTempo (IN dat1 DATE, dat2 DATE)
BEGIN
	SELECT nrbilhete
	FROM Bilhete AS B INNER JOIN Reserva AS R
		ON B.Reserva_idR=R.idR
		WHERE R.Data between dat1 and dat2
        ORDER BY nrbilhete;
END $$
CALL NºdosBilhetesVendidosNumIntervaloDeTempo ('2016-11-11', '2016-11-12');

-- montante a pagar pelas reservas de um dado cliente
delimiter $$
CREATE PROCEDURE MontanteAPagarPelaReservaDeUmDadoNomeDeCliente (IN cli CHAR(20))
BEGIN
	SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS CustoDeUmCliente, Nome, Cliente_Email
	FROM Reserva AS R INNER JOIN Bilhete AS B
    ON R.idr=B.reserva_idr
    INNER JOIN Cliente AS C
    ON R.cliente_email = C.email
    WHERE nome = cli;
END $$
CALL MontanteAPagarPelaReservaDeUmDadoNomeDeCliente ("Gustavo");

-- montante a pagar por uma dada reserva
delimiter $$
CREATE PROCEDURE MontanteAPagarPelaReservaDeUmaDadaidR (IN idr1 INT)
BEGIN
	SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS CustoDeUmaReserva, Nome, Cliente_Email
	FROM Reserva AS R INNER JOIN Bilhete AS B
    ON R.idr=B.reserva_idr
    INNER JOIN Cliente AS C
    ON R.cliente_email = C.email
    WHERE idr = idr1;
END $$
CALL MontanteAPagarPelaReservaDeUmaDadaidR (11);

-- lucro total de uma viagem
delimiter $$
CREATE PROCEDURE LucroTotalDeDadaViagem (IN idv2 INT)
BEGIN
	SELECT (SUM(CASE WHEN R.Data<B.DataPartida THEN B.CustoDesconto ELSE B.CustoNormal END)) AS LucroDeUmaViagem, idV, comboio_idc
    FROM Reserva AS R INNER JOIN Bilhete AS B
        ON R.idr=B.reserva_idr
        INNER JOIN Viagem AS V
	ON B.Viagem_idV=V.idV
        WHERE idV = idv2;
END $$
CALL LucroTotalDeDadaViagem (11);

-- numero de reservas feitas por um dado utilizador (para a transação em que utilizador verifica quantas reservas efetuou)
delimiter $$
CREATE PROCEDURE NumeroReservasFeitasPorDadoNome (IN nout CHAR (20))
BEGIN
	SELECT nome, COUNT(idr)
	FROM Reserva AS R INNER JOIN Cliente AS C
    ON R.cliente_email=C.email
    WHERE nome = nout;
END $$
CALL NumeroReservasFeitasPorDadoNome ("Sofia");

-- numero de bilhetes comprados por um dado utilizador 
delimiter $$
CREATE PROCEDURE NumeroBilhetesCompradosPorDadoNome (IN utBilh CHAR (20))
BEGIN
	SELECT nome, COUNT(nrbilhete)
	FROM Bilhete AS B INNER JOIN Reserva AS R
    ON B.reserva_idr=R.idr
       INNER JOIN Cliente AS C
	   ON R.cliente_email=C.email
       WHERE nome = utBilh;
END $$
CALL NumeroBilhetesCompradosPorDadoNome ("José");

-- comboios e bilhetes associados a uma dada viagem 
delimiter $$
CREATE PROCEDURE ComboiosEBilhestesAssociadosAUmDadoIdv (IN idvv INT)
BEGIN
	SELECT idv, nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    WHERE idv=idvv;
END $$
CALL ComboiosEBilhestesAssociadosAUmDadoIdv (11);

delimiter $$
CREATE PROCEDURE ComboiosEBilhestesAssociadosAUmaDadaOrigemOuDestino (IN cbav1 CHAR(20), cbav2 CHAR(20))
BEGIN
	SELECT idv, nrbilhete, comboio_idc, origem, destino
    FROM Bilhete AS B INNER JOIN Viagem AS V
    ON B.viagem_idv=V.idv
    WHERE origem = cbav1 OR destino = cbav2
    ORDER BY origem;
END $$
CALL ComboiosEBilhestesAssociadosAUmaDadaOrigemOuDestino ("Braga", "Madrid");

-- comboio de uma determinada viagem (para a transaçao em que utilizador verifica qual o comboio de cada viagem)
delimiter $$
CREATE PROCEDURE ComboiosDeDeterminadaViagemIdv (IN cdv INT)
BEGIN
	SELECT idv, comboio_idc, origem, destino
    FROM Viagem
    WHERE idv = cdv;
END $$
CALL ComboiosDeDeterminadaViagemIdv (11);
    
delimiter $$
CREATE PROCEDURE ComboiosDeDeterminadaViagem (IN cdo CHAR(20), cdd CHAR(20))
BEGIN
	SELECT idv, comboio_idc, origem, destino
    FROM Viagem
    WHERE origem = cdo AND destino = cdd;
END $$
Call ComboiosDeDeterminadaViagem ("Braga", "Porto")

-- numero de lugares ocupados num dado comboio numa viagem
delimiter $$
CREATE PROCEDURE NºdeLugaresOcupadosNumaDadaViagem (IN ido INT)
BEGIN
SELECT idc, idv, count(numerob) AS NumeroDeOcupados, DataPartida, HorarioPartida, DataChegada, HorarioChegada, Origem, Destino, Lotacao
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       WHERE idc = ido;
END $$
CALL NºdeLugaresOcupadosNumaDadaViagem (5);

-- numero de lugares livres num dado comboio numa viagem (para a transação do numero de lugares livres de um comboio para uma viagem)
delimiter $$
CREATE PROCEDURE NºdeLugaresLivresNumaDadaViagem (IN id INT)
BEGIN
    SELECT idc, idv, ((lotacao)-count(numerob)) AS LugLiv, DataPartida, HorarioPartida, DataChegada, HorarioChegada, Origem, Destino, Lotacao
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
    INNER JOIN Bilhete AS B
    ON V.idv = B.Viagem_idV
    WHERE C.idc=id;
END $$
CALL NºdeLugaresLivresNumaDadaViagem (8);

-- Saber os destinos de uma certa origem
delimiter $$
CREATE PROCEDURE DestinosDeUmaDadaViagem (IN part CHAR(20))
BEGIN
 SELECT Viagem.Origem, Viagem.Destino
    FROM Viagem
    WHERE Viagem.Origem = part;
END $$
CALL DestinosDeUmaDadaViagem ("Barcelona");

-- Saber de onde partem os comboios para um certo destino
delimiter $$
CREATE PROCEDURE OrigensDeUmDadoDestino (IN dest CHAR(20))
BEGIN
 SELECT Viagem.Origem, Viagem.Destino
    FROM Viagem
    WHERE Viagem.Destino = dest;
END $$
CALL OrigensDeUmDadoDestino ("Braga");
