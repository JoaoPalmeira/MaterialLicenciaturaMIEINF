-- QUERIES DE QUARTA A NOITE
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
       
-- numero de bilhetes comprados por cada utilizador
SELECT nome, COUNT(nrbilhete)
	FROM Bilhete AS B INNER JOIN Reserva AS R
    ON B.reserva_idr=R.idr
       INNER JOIN Cliente AS C
	   ON R.cliente_email=C.email
       GROUP BY nome;
       
-- bilhetes comprados por cada utilizador
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
    WHERE origem = 'Braga' AND destino = 'Porto';

-- viagens e comboios respetivo (para a transaçao em que utilizador verifica qual o comboio de cada viagem)
SELECT idv, comboio_idc, origem, destino FROM Viagem;

-- comboio de uma determinada viagem (para a transaçao em que utilizador verifica qual o comboio de cada viagem)
SELECT idv, comboio_idc, origem, destino
    FROM Viagem
    WHERE idv = 11;
    
SELECT idv, comboio_idc, origem, destino
    FROM Viagem
    WHERE origem = 'Braga' AND destino = 'Porto';
    
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
       
-- lugares livres em cada comboio (SO ESTA A CRIAR UMA VIEW COM OS OCUPADOS, ESTA INCOMPLETA)
CREATE VIEW ocupados AS
SELECT idc, numerob, carruagemb, classeb, nrbilhete, idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       ORDER BY idc;

-- numero de lugares livres em cada comboio para uma viagem
SELECT idc, lotacao-count(numerob), idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       GROUP BY idc;
       
-- numero de lugares livres num dado comboio numa viagem (para a transação do numero de lugares livres de um comboio para uma viagem)
SELECT idc, lotacao-count(numerob), idv, origem, destino
    FROM Comboio AS C INNER JOIN Viagem AS V
    ON C.idc = V.comboio_idc
       INNER JOIN Bilhete AS B
       ON V.idv = B.viagem_idv
       WHERE idc = 1;
       