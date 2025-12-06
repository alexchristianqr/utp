/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.30 : Database - mydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mydb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mydb`;

/*Table structure for table `alumno` */

DROP TABLE IF EXISTS `alumno`;

CREATE TABLE `alumno` (
  `alu_id` int NOT NULL AUTO_INCREMENT,
  `alu_codigo` varchar(45) NOT NULL,
  `alu_nombres` varchar(45) NOT NULL,
  `alu_apellidos` varchar(45) NOT NULL,
  `alu_edad` int NOT NULL,
  `alu_genero` varchar(1) NOT NULL,
  `car_id` int NOT NULL,
  PRIMARY KEY (`alu_id`),
  KEY `fk_alumno_carrera_idx` (`car_id`),
  CONSTRAINT `fk_alumno_carrera` FOREIGN KEY (`car_id`) REFERENCES `carrera` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `alumno` */

insert  into `alumno`(`alu_id`,`alu_codigo`,`alu_nombres`,`alu_apellidos`,`alu_edad`,`alu_genero`,`car_id`) values (1,'U001','Juan','Perez',20,'M',1);
insert  into `alumno`(`alu_id`,`alu_codigo`,`alu_nombres`,`alu_apellidos`,`alu_edad`,`alu_genero`,`car_id`) values (2,'U002','Carmen','Abt',18,'F',2);
insert  into `alumno`(`alu_id`,`alu_codigo`,`alu_nombres`,`alu_apellidos`,`alu_edad`,`alu_genero`,`car_id`) values (3,'U003','Carlos','Valdez',23,'M',3);

/*Table structure for table `carrera` */

DROP TABLE IF EXISTS `carrera`;

CREATE TABLE `carrera` (
  `car_id` int NOT NULL AUTO_INCREMENT,
  `car_nombre` varchar(45) NOT NULL,
  `car_sede` varchar(45) NOT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `carrera` */

insert  into `carrera`(`car_id`,`car_nombre`,`car_sede`) values (1,'Ingeniería de Sistemas','Centro');
insert  into `carrera`(`car_id`,`car_nombre`,`car_sede`) values (2,'Contabilidad','Centro');
insert  into `carrera`(`car_id`,`car_nombre`,`car_sede`) values (3,'Administración','Centro');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
