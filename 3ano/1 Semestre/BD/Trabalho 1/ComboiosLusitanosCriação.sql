-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ComboiosLusitanos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ComboiosLusitanos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ComboiosLusitanos` DEFAULT CHARACTER SET utf8 ;
USE `ComboiosLusitanos` ;

-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`Cliente` (
  `Email` VARCHAR(45) NOT NULL,
  `NIF` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Sexo` VARCHAR(45) NOT NULL,
  `DataNascimento` DATE NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`Contactos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`Contactos` (
  `Telefone` INT NOT NULL,
  `Telemovel` INT NOT NULL,
  `Cliente_Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Cliente_Email`),
  CONSTRAINT `fk_Contactos_Cliente1`
    FOREIGN KEY (`Cliente_Email`)
    REFERENCES `ComboiosLusitanos`.`Cliente` (`Email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`Reserva` (
  `idR` INT NOT NULL,
  `Data` DATE NOT NULL,
  `Cliente_Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idR`),
  INDEX `fk_Reserva_Cliente1_idx` (`Cliente_Email` ASC),
  CONSTRAINT `fk_Reserva_Cliente1`
    FOREIGN KEY (`Cliente_Email`)
    REFERENCES `ComboiosLusitanos`.`Cliente` (`Email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`Endereco` (
  `CodigoPostal` INT NOT NULL,
  `Localidade` VARCHAR(45) NOT NULL,
  `Rua` VARCHAR(45) NOT NULL,
  `Cliente_Email` VARCHAR(45) NOT NULL,
  CONSTRAINT `fk_Endereco_Cliente1`
    FOREIGN KEY (`Cliente_Email`)
    REFERENCES `ComboiosLusitanos`.`Cliente` (`Email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`Comboio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`Comboio` (
  `idC` INT NOT NULL,
  `Lotacao` INT NOT NULL,
  PRIMARY KEY (`idC`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`Viagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`Viagem` (
  `idV` INT NOT NULL,
  `Origem` VARCHAR(45) NOT NULL,
  `Destino` VARCHAR(45) NOT NULL,
  `HorarioPartida` TIME NOT NULL,
  `HorarioChegada` TIME NOT NULL,
  `Comboio_idC` INT NOT NULL,
  PRIMARY KEY (`idV`),
  INDEX `fk_Viagem_Comboio1_idx` (`Comboio_idC` ASC),
  CONSTRAINT `fk_Viagem_Comboio1`
    FOREIGN KEY (`Comboio_idC`)
    REFERENCES `ComboiosLusitanos`.`Comboio` (`idC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`Bilhete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`Bilhete` (
  `NrBilhete` INT NOT NULL,
  `DataChegada` DATE NOT NULL,
  `DataPartida` DATE NOT NULL,
  `CustoNormal` DECIMAL(5,2) NOT NULL,
  `CustoDesconto` DECIMAL(5,2) NOT NULL,
  `Viagem_idV` INT NOT NULL,
  `Reserva_idR` INT NOT NULL,
  `Reserva_Cliente_Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`NrBilhete`),
  INDEX `fk_Bilhete_Viagem1_idx` (`Viagem_idV` ASC),
  INDEX `fk_Bilhete_Reserva1_idx` (`Reserva_idR` ASC, `Reserva_Cliente_Email` ASC),
  CONSTRAINT `fk_Bilhete_Viagem1`
    FOREIGN KEY (`Viagem_idV`)
    REFERENCES `ComboiosLusitanos`.`Viagem` (`idV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bilhete_Reserva1`
    FOREIGN KEY (`Reserva_idR` , `Reserva_Cliente_Email`)
    REFERENCES `ComboiosLusitanos`.`Reserva` (`idR` , `Cliente_Email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`LugarBilhete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`LugarBilhete` (
  `Numero` INT NOT NULL,
  `Carruagem` INT NOT NULL,
  `Classe` VARCHAR(45) NOT NULL,
  `Bilhete_NrBilhete` INT NOT NULL,
  `Bilhete_Viagem_idV` INT NOT NULL,
  `Bilhete_Reserva_idR` INT NOT NULL,
  `Bilhete_Reserva_Cliente_Email` VARCHAR(45) NOT NULL,
  CONSTRAINT `fk_LugarBilhete_Bilhete1`
    FOREIGN KEY (`Bilhete_NrBilhete` , `Bilhete_Viagem_idV` , `Bilhete_Reserva_idR` , `Bilhete_Reserva_Cliente_Email`)
    REFERENCES `ComboiosLusitanos`.`Bilhete` (`NrBilhete` , `Viagem_idV` , `Reserva_idR` , `Reserva_Cliente_Email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ComboiosLusitanos`.`Lugar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ComboiosLusitanos`.`Lugar` (
  `Carruagem` INT NOT NULL,
  `Classe` VARCHAR(45) NOT NULL,
  `Numero` INT NOT NULL,
  `Comboio_idC` INT NOT NULL,
  CONSTRAINT `fk_Lugar_Comboio1`
    FOREIGN KEY (`Comboio_idC`)
    REFERENCES `ComboiosLusitanos`.`Comboio` (`idC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
