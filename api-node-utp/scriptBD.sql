-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`carrera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`carrera` (
  `car_id` INT NOT NULL AUTO_INCREMENT,
  `car_nombre` VARCHAR(45) NOT NULL,
  `car_sede` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`car_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`alumno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`alumno` (
  `alu_id` INT NOT NULL AUTO_INCREMENT,
  `alu_codigo` VARCHAR(45) NOT NULL,
  `alu_nombres` VARCHAR(45) NOT NULL,
  `alu_apellidos` VARCHAR(45) NOT NULL,
  `alu_edad` INT NOT NULL,
  `alu_genero` VARCHAR(1) NOT NULL,
  `car_id` INT NOT NULL,
  PRIMARY KEY (`alu_id`),
  INDEX `fk_alumno_carrera_idx` (`car_id` ASC) ,
  CONSTRAINT `fk_alumno_carrera`
    FOREIGN KEY (`car_id`)
    REFERENCES `mydb`.`carrera` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
