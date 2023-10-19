-- # Crear base de datos
create database db_hotel
go

  -- # Usar base de datos
use db_hotel
go

-- # Crear tabla Empleado
create table Empleado
(
  EmpleadoId int not null,
  Nombre varchar(100) not null,
  Apellido varchar(100) not null,
  Edad varchar(5),
  primary key (EmpleadoId)
)
go

