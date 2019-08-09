-- Criação do utilizador 'bossBD'
CREATE USER 'bossBD'@'localhost';
SET PASSWORD FOR 'bossBD'@'localhost' = 'euamome1234';

SELECT * 
	FROM mysql.user
	WHERE User = 'bossBD';

-- permicissão de acesso a todos os objetos de todas as bases de dados
GRANT ALL ON comboioslusitanos.* TO 'bossBD'@'localhost';

SHOW GRANTS FOR 'bossBD'@'localhost';

REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'bossBD'@'localhost';

    
