-- # Crear base de datos
create database db_hotel
go

-- # Usar base de datos
use db_hotel
go

-- # Crear tabla Persona
create table Persona
(
  PersonaId int not null,
  Nombre varchar(100) not null,
  Apellido varchar(100) not null,
  Edad varchar(5),
  primary key (PersonaId)
)
go

-- # Crear tabla Cliente
create table Cliente
(
  ClienteID int not null,
  PersonaID int not null,
  Empresa varchar(5) not null,
  TipoDocumento varchar(100) not null,
  NroDocumento varchar(5) not null,
  primary key (ClienteID),
  foreign key (PersonaID) references Persona(PersonaID)
)
go

  -- # Crear tabla Empleado
create table Perfil
(
  PerfilID int not null,
  Nombre varchar(100) not null,
  Permisos varchar(250) not null,
  primary key (PerfilID)
)
go
  
-- # Crear tabla Empleado
create table Empleado
(
  EmpleadoID int not null,
  PersonaID int not null,
  PerfilID int not null,
  Sueldo decimal not null,
  primary key (EmpleadoID),
  foreign key (PersonaID) references Persona(PersonaID),
  foreign key (PerfilID) references Perfil(PerfilID)
)
go




