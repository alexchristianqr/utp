-- (DDL) DATA DEFINITION LANGUAGE

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
    TipoDocumento varchar(5) not null,
    NroDocumento varchar(30) not null,
    Sexo varchar(5) not null,
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
    Sueldo decimal(10,2) not null,
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
    Precio decimal(10,2) not null,
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
    Precio decimal(10,2) not null,
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
    MontoTotal decimal(10,2) not null,
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
    ReservaID int not null,
    ProductoID int not null,
    Cantidad int not null,
    Precio decimal(10,2) not null,
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

-- # Resetear tablas
truncate table Perfil

ALTER TABLE Reserva DROP CONSTRAINT FK_PROBLEM_REASON
truncate table Reserva