CREATE DATABASE `db_productos`;

USE `db_productos`;

CREATE TABLE `productos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(200) DEFAULT NULL,
  `imagen` VARCHAR(255) DEFAULT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `stock` INT NOT NULL,
  `categoria` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE movimientos(
    id INT NOT NULL AUTO_INCREMENT,
    producto_id INT NOT NULL,
    tipo ENUM('ENTRADA','SALIDA') NOT NULL, -- Entrada suma, salida resta
    cantidad INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    descripcion VARCHAR(200),
    PRIMARY KEY (id)
);

-- Datos de ejemplo para la tabla productos



INSERT  INTO `productos`(`id`,`nombre`,`descripcion`,`precio`,`stock`,`categoria`) VALUES ('Laptop Lenovo','Laptop Core i5 8GB RAM',NULL,2500.00,10,'Tecnología');
INSERT  INTO `productos`(`id`,`nombre`,`descripcion`,`precio`,`stock`,`categoria`) VALUES ('Mouse inalámbrico','Mouse óptico 1600 DPI',NULL,45.00,50,'Accesorios');
INSERT  INTO `productos`(`id`,`nombre`,`descripcion`,`precio`,`stock`,`categoria`) VALUES ('Teclado mecánico','Teclado RGB switches blue',NULL,150.00,20,'Accesorios');
INSERT  INTO `productos`(`id`,`nombre`,`descripcion`,`precio`,`stock`,`categoria`) VALUES ('Monitor Samsung 24\"','Monitor LED Full HD',NULL,650.00,15,'Tecnología');
INSERT  INTO `productos`(`id`,`nombre`,`descripcion`,`precio`,`stock`,`categoria`) VALUES ('Silla gamer','Silla ergonómica reclinable',NULL,780.00,8,'Muebles');
INSERT  INTO `productos`(`id`,`nombre`,`descripcion`,`precio`,`stock`,`categoria`) VALUES ('Producto alex quispe','Prueba',NULL,100.00,1000,'Cosa');
INSERT  INTO `productos`(`id`,`nombre`,`descripcion`,`precio`,`stock`,`categoria`) VALUES ('Prueba #2 PC3','ttt Silla ergonómica reclinable',NULL,780.00,8,'Muebles');


INSERT INTO movimientos (producto_id, tipo, cantidad, descripcion)
VALUES (1, 'ENTRADA', 1, 'Reposición de proveedor');
INSERT INTO movimientos (producto_id, tipo, cantidad, descripcion)
VALUES (1, 'SALIDA', 1, 'Venta realizada');

