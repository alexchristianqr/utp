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
  FechaCreado date,
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
  FechaCreado date,
  primary key (ClienteID),
  foreign key (PersonaID) references Persona(PersonaID)
)
go

-- # Crear tabla Perfil
create table Perfil
(
  PerfilID int not null,
  Nombre varchar(100) not null,
  Permisos varchar(250) not null,
  FechaCreado date,
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
  FechaCreado date,
  primary key (EmpleadoID),
  foreign key (PersonaID) references Persona(PersonaID),
  foreign key (PerfilID) references Perfil(PerfilID)
)
go

-- # Crear tabla Habitación
create table Habitacion
(
  HabitacionID int not null,
  TipoHabitacionID int not null,
  Descripcion varchar(250) not null,
  Precio money not null,
  FechaCreado date,
  primary key (HabitacionID)
)
go

-- # Crear tabla Reserva
create table Reserva
(
  ReservaID int not null,
  ClienteID int not null,
  HabitacionID int not null,
  EmpleadoID int not null,
  MontoTotal decimal(2),
  CantidadPersonas int not null,
  FechaReserva date,
  FechaEntrada date,
  FechaSalida date,
  FechaCreado date,
  primary key (ReservaID),
  foreign key (HabitacionID) references Habitacion(HabitacionID),
  foreign key (EmpleadoID) references Empleado(EmpleadoID),
  foreign key (ClienteID) references Cliente(ClienteID)
)
go

insert into Persona (PersonaId, Nombre, Apellido, Edad, FechaCreado) values (1, 'Luis', 'Torres', '25', '10/19/2023 17:34:00')
go

create or alter function dbo.FnClienteTieneDescuento
(
  @ClienteID
) returns boolean
as
begin
  -- select * from 
end
go

insert into Persona (PersonaId, Nombre, Apellido, Edad, FechaCreado) values (1, 'Luis', 'Torres', '25', '10/19/2023 17:34:00')
go

create or alter function SpPagarReserva
(
  @ReservaID int
)
as
begin
  -- select * from 
end
go

