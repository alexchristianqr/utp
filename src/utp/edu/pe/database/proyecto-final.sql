-- # Crear base de datos
create database DbHotel

-- # Usar base de datos
use DbHotel

-- # Crear tabla Persona
create table Persona
(
  PersonaID int not null identity(1,1),
  Nombre varchar(100) not null,
  Apellido varchar(100) not null,
  TipoDocumento int not null,
  NroDocumento varchar(5) not null,
  Edad varchar(5),
  FechaCreado datetime not null,
  primary key (PersonaId)
)

-- # Crear tabla Cliente
create table Cliente
(
  ClienteID int not null identity(1,1),
  PersonaID int not null,
  Empresa varchar(250) not null,
  FechaCreado datetime not null,
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
  FechaCreado datetime not null,
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
    primary key (ProductoID)
)

-- # Crear tabla ReservaConsumo
create table ReservaConsumo
(
    ReservaID int not null identity(1,1),
    ProductoID int not null,
    Cantidad int not null,
    Precio decimal(2) not null,
    FechaCreado datetime not null,
    foreign key (ReservaID) references Reserva(ReservaID),
    foreign key (ProductoID) references Producto(ProductoID),
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
  FechaReserva datetime,
  FechaEntrada datetime,
  FechaSalida datetime,
  FechaCreado datetime not null,
  primary key (ReservaID),
  foreign key (ClienteID) references Cliente(ClienteID),
  foreign key (HabitacionID) references Habitacion(HabitacionID),
  foreign key (EmpleadoID) references Empleado(EmpleadoID)
)

-- # Crear tabla Comprobante de Pago
create table ComprobantePago
(
    ComprobantePagoID int not null identity(1,1),
    ReservaID int not null,
    EmpleadoID int not null,
    TipoComprobante int not null, -- 1: Factura, 2: Boleta
    FechaCreado datetime not null,
    FechaPagado datetime,
    Estado varchar(50) not null, -- Activo, Pendiente pago, Pagado, Cancelado
    primary key (ComprobantePagoID),
    foreign key (ReservaID) references Reserva(ReservaID),
    foreign key (EmpleadoID) references Empleado(EmpleadoID)
)

-- Perfil
insert into Perfil (PerfilID, Nombre, Permisos, FechaCreado) values (1, 'Administrador', '{}', '2023-11-10 18:45:29')

-- Persona Empleado
insert into Persona (PersonaId, Nombre, Apellido, TipoDocumento, NroDocumento, Edad, FechaCreado) values (1, 'Luis', 'Torres', 1, '12345678', '30', '2023-11-10 18:45:29')
insert into Empleado (EmpleadoID, PersonaID, PerfilID, Sueldo, FechaCreado) values (1, 1, 1, 1250.69, '2023-11-10 18:45:29')

-- Persona Cliente
insert into Persona (PersonaId, Nombre, Apellido, TipoDocumento, NroDocumento, Edad, FechaCreado) values (2, 'Javier', 'Retamoso', 2, '20345678901', '30', '2023-11-10 18:45:29')
insert into Cliente (ClienteID, PersonaID, Empresa, FechaCreado) values (1, 2, 'Securitec Peru SAC', '2023-11-10 18:45:29')

-- Tipo Habitacion
insert into TipoHabitacion (TipoHabitacionID, Descripcion, FechaCreado) values (1, 'Clasico', '2023-11-10 18:45:29')

-- Habitacion
insert into Habitacion (HabitacionID, TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, FechaCreado) values (1, 1, 'Habitación con agua caliente + TV', '1', '101', 49.50, '2023-11-10 18:45:29')

-- Reserva
insert into Reserva (ReservaID, ClienteID, HabitacionID, MontoTotal, CantidadPersonas, FechaReserva, FechaEntrada, FechaSalida, FechaCreado)
  values (1, 1, 1, 89.90, '2023-11-10 18:45:29', '2023-11-10 18:45:29', '2023-11-10 18:45:29', '2023-11-10 18:45:29')
  
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

