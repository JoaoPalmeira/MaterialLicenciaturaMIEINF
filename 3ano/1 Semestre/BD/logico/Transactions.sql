USE comboioslusitanos;

-- transação que permite inserir um novo cliente
delimiter $$
CREATE PROCEDURE Inserir_Cliente(IN EmailC VARCHAR(45), IN NIFC INT, IN NomeC VARCHAR(45), IN SexoC ENUM('M','F'), IN DataN DATE, IN Pass VARCHAR(45), IN Telf INT, IN Telm INT)
BEGIN
START TRANSACTION;
INSERT INTO Cliente
(Email, NIF, Nome, Sexo, DataNascimento, Password, Telefone, Telemovel)
VALUES (EmailC, NIFC, NomeC, SexoC, DataN, Pass, Telf, Telm);

SELECT * FROM Cliente WHERE email = EmailC;

ROLLBACK;

END $$
CALL Inserir_Cliente('ola@gmail.com', 999999999, 'Joaquim', 'M', '1984-11-15', 'lol', 253234999, 967948888);

-- transação que permite alterar a password e o nome de um cliente
delimiter $$
CREATE PROCEDURE Alterar_Dados(IN EmailC VARCHAR(45), IN NomeC VARCHAR(45), IN Pass VARCHAR(45))
BEGIN
START TRANSACTION;
	UPDATE Cliente
	SET nome = NomeC
		WHERE email=emailC;
	UPDATE Cliente
    SET password = Pass
		WHERE email=emailC;
SELECT * FROM Cliente WHERE email =emailC;

ROLLBACK;

END $$
CALL Alterar_Dados('maria@gmail.com', 'Maria', 'olaola');

-- transação que permite inserir um novo endereços de um cliente
delimiter $$
CREATE PROCEDURE Inserir_Endereco(IN EmailC VARCHAR(45), IN CodP INT, IN Loc VARCHAR(45), IN R VARCHAR(45))
BEGIN
START TRANSACTION;
	UPDATE Endereco
	SET CodigoPostal = CodP
		WHERE Cliente_Email=EmailC;
	UPDATE Endereco
    SET Localidade = Loc
		WHERE Cliente_Email=EmailC;
        
SELECT * FROM Endereco WHERE Cliente_Email=emailC;

ROLLBACK;

END $$
CALL Inserir_Endereco('maria@gmail.com', '100000000', 'Vila Nova', 'Rua Nova');


-- transação que permite a um cliente reservar uma viagem para uma determinada data e num determinado lugar do comboio
delimiter $$
CREATE PROCEDURE Reserva_viagem(IN idReserva INT, IN numeroBilhete INT, IN custoN DECIMAL(5,2), IN custoD DECIMAL(5,2), IN EmailC VARCHAR(45), IN DataC DATE,
 IN DataP DATE, IN idViagem INT, IN nLugar INT)
BEGIN 
START TRANSACTION; 

INSERT INTO Reserva
(idR, Data, Cliente_Email)
VALUES 
(idReserva, CURDATE(), EmailC);

SELECT * 
   FROM Reserva
   WHERE cliente_email = EmailC;

INSERT INTO Bilhete
(NrBilhete, DataChegada, DataPartida, CustoNormal, CustoDesconto, NumeroB, CarruagemB, ClasseB, Viagem_idV, Reserva_idR)
VALUES
(numeroBilhete, DataC, DataP, custoN, custoD, nLugar, CASE WHEN nLugar between 1 AND 6 THEN 1 ELSE 2 END,
CASE WHEN nLugar between 1 AND 6 then 'executiva' else 'económica' end, idViagem, idReserva);

SELECT *
    FROM Bilhete 
    WHERE Reserva_idR = idReserva;

ROLLBACK;

END $$
CALL Reserva_viagem(28, 80, 25, 22.5, 'pedrodias@hotmail.com', CURDATE(), CURDATE(), 91, 1);

-- inserir novo comboio e associá-lo a uma viagem existente
delimiter $$
CREATE PROCEDURE Insere_comboio(IN idComboio INT, IN LotacaoC INT, IN idViagem INT)
BEGIN 
START TRANSACTION; 

SELECT * FROM Viagem WHERE idv = idViagem;

INSERT INTO Comboio
(idC, Lotacao)
VALUES 
(idComboio, LotacaoC);

INSERT INTO Lugar
(Carruagem, Classe, Numero, Comboio_idC)
VALUES
(1, 'executiva', 1, idComboio),
(1, 'executiva', 2, idComboio),
(1, 'executiva', 3, idComboio),
(1, 'executiva', 4, idComboio),
(1, 'executiva', 5, idComboio),
(1, 'executiva', 6, idComboio),
(2, 'económica', 7, idComboio),
(2, 'económica', 8, idComboio),
(2, 'económica', 9, idComboio),
(2, 'económica', 10, idComboio),
(2, 'económica', 11, idComboio),
(2, 'económica', 12, idComboio),
(2, 'económica', 13, idComboio),
(2, 'económica', 14, idComboio),
(2, 'económica', 15, idComboio);

UPDATE Viagem
SET Comboio_idC = idComboio
WHERE idV = idViagem;

SELECT * FROM Viagem WHERE comboio_idc = idComboio;

ROLLBACK;

END $$
CALL Insere_comboio(11, 15, 21);

-- transação que permite inserir uma viagem para um comboio que já existe
delimiter $$
CREATE PROCEDURE Inserir_Viagem(IN idViagem INT, IN OrigemV VARCHAR(45), IN DestinoV VARCHAR(45), IN HPartida TIME, IN HChegada TIME, Comboio INT)
BEGIN
START TRANSACTION;
INSERT INTO Viagem
(idV, Origem, Destino, HorarioPartida, HorarioChegada, Comboio_idC)
     VALUES	 
(idViagem, OrigemV, DestinoV, HPartida, HChegada, Comboio);
        
SELECT * FROM Viagem WHERE idV=idViagem;

ROLLBACK;

END $$
CALL Inserir_Viagem(221, 'Coimbra', 'Viana do Castelo', '130000', '143000', 9);
