-- ****************************** --
-- NORMALIZACION DE BASE DE DATOS --
-- ****************************** --
-- Sitio web: https://www.marcossarmiento.com/2017/06/28/normalizacion-de-base-de-datos/
-- 1FN: Eliminar datos repetitivos en varias columnas
-- 2FN: Eliminar datos redundantes en la tabla principal y ponerlas en otra tabla por PK1 y PK2.
-- 3FN: Eliminar columnas que no dependen de la PK en la tabla principal en y ponerlas a otra tabla
  
-- # Crear base de datos
create database DBHotel

-- # Usar base de datos
use DBHotel

-- # Crear tabla Persona
create table Persona
(
  PersonaID int not null identity(1,1),
  Nombre varchar(100) not null,
  Apellido varchar(100) not null,
  TipoDocumento int not null,
  NroDocumento varchar(5) not null,
  Sexo varchar(10) not null, 
  Edad varchar(5),
  FechaCreado datetime not null,
  FechaActualizado datetime,
  primary key (PersonaId)
)

-- # Crear tabla Cliente
create table Cliente
(
  ClienteID int not null identity(1,1),
  PersonaID int not null,
  Empresa varchar(250),
  FechaCreado datetime not null,
  FechaActualizado datetime,
  primary key (ClienteID),
  foreign key (PersonaID) references Persona(PersonaID)
)

-- # Crear tabla Perfil
create table Perfil
(
  PerfilID int not null identity(1,1),
  Nombre varchar(100) not null,
  Permisos varchar(250) not null,
  FechaCreado datetime not null,
  FechaActualizado datetime,
  primary key (PerfilID)
)
  
-- # Crear tabla Empleado
create table Empleado
(
  EmpleadoID int not null identity(1,1),
  PersonaID int not null,
  PerfilID int not null,
  Sueldo decimal not null,
  FechaCreado datetime not null,
  FechaActualizado datetime,
  primary key (EmpleadoID),
  foreign key (PersonaID) references Persona(PersonaID),
  foreign key (PerfilID) references Perfil(PerfilID)
)

-- # Crear tabla Tipo Habitación
create table TipoHabitacion
(
    TipoHabitacionID int not null identity(1,1),
    Descripcion varchar(250) not null,
    FechaCreado datetime not null,
    FechaActualizado datetime,
    primary key (TipoHabitacionID)
)

-- # Crear tabla Habitación
create table Habitacion
(
  HabitacionID int not null identity(1,1),
  TipoHabitacionID int not null,
  Descripcion varchar(250) not null,
  Nivel varchar(25) not null,
  NumeroPiso varchar(25) not null,
  Precio decimal not null,
  CantidadCamas int not null,
  FechaCreado datetime not null,
  FechaActualizado datetime,
  primary key (HabitacionID),
  foreign key (TipoHabitacionID) references TipoHabitacion(TipoHabitacionID)
)

-- # Crear tabla Producto
create table Producto
(
    ProductoID int not null identity(1,1),
    Descripcion varchar(250) not null,
    Precio decimal not null,
    CantidadStock int not null,
    FechaCreado datetime not null,
    FechaActualizado datetime,
    primary key (ProductoID)
)

-- # Crear tabla Reserva
create table Reserva
(
  ReservaID int not null identity(1,1),
  ClienteID int not null,
  HabitacionID int not null,
  EmpleadoID int not null,
  MontoTotal decimal(2) not null,
  CantidadPersonas int not null,
  Estado varchar(50) not null, -- activo, reservado, en_proceso, finalizado, cancelado
  FechaReserva datetime,
  FechaEntrada datetime,
  FechaSalida datetime,
  FechaCreado datetime not null,
  FechaActualizado datetime,
  primary key (ReservaID),
  foreign key (ClienteID) references Cliente(ClienteID),
  foreign key (HabitacionID) references Habitacion(HabitacionID),
  foreign key (EmpleadoID) references Empleado(EmpleadoID)
)

-- # Crear tabla ReservaConsumo
create table ReservaConsumo
(
    ReservaID int not null identity(1,1),
    ProductoID int not null,
    Cantidad int not null,
    Precio decimal(2) not null,
    FechaCreado datetime not null,
    FechaActualizado datetime,
    foreign key (ReservaID) references Reserva(ReservaID),
    foreign key (ProductoID) references Producto(ProductoID),
)

-- # Crear tabla Comprobante de Pago
create table ComprobantePago
(
    ComprobantePagoID int not null identity(1,1),
    ReservaID int not null,
    EmpleadoID int not null,
    TipoComprobante varchar(5) not null, -- 1: Factura, 2: Boleta
    Estado varchar(50) not null, -- activo, pendiente_pago, pagado, cancelado
    FechaPagado datetime,
    FechaCreado datetime not null,
    FechaActualizado datetime,
    primary key (ComprobantePagoID),
    foreign key (ReservaID) references Reserva(ReservaID),
    foreign key (EmpleadoID) references Empleado(EmpleadoID)
)

-- Perfil
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Administrador', '{}', '2023-11-10 18:45:29')
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Recepcionista', '{}', '2023-11-09 10:23:51')
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Portero', '{}', '2023-11-08 08:51:49')
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Conserje', '{}', '2023-11-07 11:01:36')
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Personal de limpieza', '{}', '2023-11-06 16:06:02')
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Personal de mantenimiento', '{}', '2023-11-05 12:01:20')

-- Persona Empleado
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Luis', 'Torres', 1, '12345678', 'M', '30', '2023-11-10 18:45:29')
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (1, 1, 1250.69, '2023-11-10 18:45:29')

-- Persona Cliente
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Javier', 'Retamoso', 1, '74567890', 'M', '30', '2023-11-10 18:45:29')
insert into Cliente (PersonaID, Empresa, FechaCreado) values (2, null, '2023-11-10 18:45:29')

-- Tipo Habitacion
insert into TipoHabitacion (Descripcion, FechaCreado) values ('Clasico', '2023-11-10 18:45:29')

-- Habitacion
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 1, 'Habitación con agua caliente + TV', '1', '101', 49.50, 1, '2023-11-10 18:45:29')

-- Reserva
insert into Reserva (ClienteID, HabitacionID, MontoTotal, FechaReserva, FechaEntrada, FechaSalida, FechaCreado)
  values (1, 1, 89.90, '2023-11-10 18:45:29', '2023-11-10 18:45:29', '2023-11-10 18:45:29', '2023-11-10 18:45:29')

-- Producto
insert into Producto (ProductoID, Descripcion, Precio, CantidadStock, FechaCreado) values (1, 'Botella de agua cielo', 5, 100, '2023-11-10 18:45:29')

-- Reserva Consumo
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (1, 1, 3, 5, '2023-11-10 18:45:29')

-- Comprobante Pago
insert into ComprobantePago (ComprobantePagoID, ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (1, 1, 1, '1', '2023-11-10 18:45:29', '2023-11-10 18:45:29', 'Pagado')
  
create or alter function dbo.FnClienteTieneDescuento
(
  @ClienteID
) returns boolean
as
begin
  -- select * from 
end

create or alter function SpPagarReserva
(
  @ReservaID int
)
as
begin
  -- select * from 
end

