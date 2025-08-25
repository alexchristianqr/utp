-- (DDL) DATA DEFINITION LANGUAGE

-- PASOS a SEGUIR
-- 1.- Crear y usar la base de datos
-- 2.- Crear todas las tablas
-- 3.- Insertar datos
-- 4.- Crear las funciones
-- 5.- Crear los procedimientos
-- 6.- Crear las vistas

-- 1.- Crear y usar la base de datos
create database DBHotel
use DBHotel

-- 2.- Crear todas las tablas
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

-- 3.- Insertar datos
-- (DML) DATA MANIPULATION LANGUAGE
-- Perfil
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Administrador', '{}', GETDATE())
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Recepcionista', '{}', GETDATE())
-- Persona Empleado
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Luis', 'Torres', '1', '12345678', 'M', '30',  GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 1250.69, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Edgar', 'Guerrero', '1', '71289127', 'M', '29', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 2, 1500.89, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Nilton', 'Bohorquez', '1', '82918291', 'M', '31', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 2, 900.75, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Sheyla', 'Gonzales', '1', '62373627', 'F', '22', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 950.15, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Juan', 'Castillo', '1', '35473848', 'M', '25', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 780.99,  GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Saori', 'Durand', '1', '27182781', 'F', '26', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 2, 2300.27, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Doris', 'Gomez', '1', '48294238', 'F', '21', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 1020.38, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Laura', 'Jimenez', '1', '38293892', 'F', '20', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 2, 955.49, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Donato', 'Hernandez', '1', '37284728', 'M', '24', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 1780.45, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('German', 'Mamani', '1', '57385738', 'M', '33', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 855.10, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Sharon', 'Gonzales', '1', '38293829', 'F', '34', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 2, 1890.26, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Maria', 'Asnabar', '1', '23278911', 'F', '36', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 750.66, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Gilda', 'Guillen', '1', '47384728', 'F', '45', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 1305.92, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Alex', 'Espinoza', '1', '12891281', 'M', '41', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 2, 2120.74, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Domingo', 'Sierra', '1', '73281312', 'M', '39', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 2, 2890.12, GETDATE())
-- Persona Cliente
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Carlos', 'Gómez', 1, '12345678', 'M', '25', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Ana', 'Martínez', 1, '98765432', 'F', '28', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Roberto', 'Fernández', 1, '55555555', 'M', '32', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Luisa', 'García', 1, '11223344', 'F', '22', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Pedro', 'Díaz', 1, '55667788', 'M', '35', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('María', 'López', 1, '99001122', 'F', '28', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Martín', 'Hernández', 1, '44332211', 'M', '31', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Laura', 'Ramírez', 1, '66778899', 'F', '26', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Andrés', 'Torres', 1, '11223344', 'M', '30', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Isabel', 'Gómez', 1, '55667788', 'F', '32', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Santiago', 'Martínez', 1, '99001122', 'M', '27', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Ana', 'López', 1, '44332211', 'F', '29', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Alejandro', 'Hernández', 1, '66778899', 'M', '34', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Sebastian', 'Mogollon', 1, '74644204', 'M', '27', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Andrea', 'Gonsalez', 1, '74224188', 'F', '24', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())
-- Tipo Habitacion
insert into TipoHabitacion (Descripcion, FechaCreado) values ('Clasico', GETDATE())
insert into TipoHabitacion (Descripcion, FechaCreado) values ('Matrimonial', GETDATE())
-- Habitacion
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '1', '101', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '1', '102', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '1', '103', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '1', '104', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '1', '105', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '2', '201', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '2', '202', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '2', '203', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '2', '204', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '2', '205', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '3', '301', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '3', '302', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '3', '303', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '3', '304', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '3', '305', 49.50, 1, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '4', '401', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '4', '402', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '4', '403', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '4', '404', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '4', '405', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '5', '501', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '5', '502', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '5', '503', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '5', '504', 80.00, 2, GETDATE())
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (2, 'Habitación con agua caliente + TV + Servicio', '5', '505', 80.00, 2, GETDATE())
-- Reserva
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (1, 1, 1, 89.90, 2, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (4, 15, 4, 44.90, 1, 'inactivo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (3, 8, 5, 179.90, 4, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (9, 10, 1, 134.90, 3, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (7, 4, 2, 89.90, 2, 'inactivo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (5, 11, 3, 45.90, 1, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (11, 9, 1, 224.90, 3, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (13, 12, 4, 89.90, 3, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (12, 5, 3, 89.90, 2, 'inactivo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (15, 17, 5, 44.90, 1, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (6, 2, 2, 179.90, 4, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (10, 19, 1, 89.90, 2, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (14, 7, 3, 224.90, 3, 'inactivo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (3, 13, 4, 89.90, 2, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (8, 14, 2, 224.90, 3, 'activo',  GETDATE(),  GETDATE(),  GETDATE(),  GETDATE())
-- Producto
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Botella de agua Cielo', 5.00, 100, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Botella de agua San Luis', 6.00, 100, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Botella de agua San Mateo', 7.00, 100, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Botella de agua San Carlos', 3.00, 100, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Galleta Nick', 3.50, 50, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Cerveza Pilsen', 20.00, 200, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Cerveza Cuzqueña', 35.00, 300, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Cerveza Pilsen', 11.00, 200, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Dulce Halls negro', 1.50, 100, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Preservativo Durex', 15.00, 500, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Preservativo Piel', 10.00, 500, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Preservativo Gents', 12.00, 500, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Gaseosa Inka Cola', 5.10, 100, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Gaseosa Coca Cola', 5.50, 100, GETDATE())
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Energizante Moster', 15.50, 100, GETDATE())
-- Reserva Consumo
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (1, 1, 3, 15.50, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (2, 2, 2, 5.50, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (3, 3, 2, 5.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (4, 4, 1, 12.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (5, 12, 1, 10.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (6, 6, 2, 15.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (7, 7, 1, 1.50, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (8, 8, 3, 11.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (9, 9, 1, 35.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (10, 10, 1, 20.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (11, 11, 1, 3.50, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (12, 12, 1, 3.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (13, 13, 1, 7.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (14, 14, 1, 6.00, GETDATE())
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (15, 15, 1, 5.10, GETDATE())
-- Comprobante Pago
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (1, 1, '1', GETDATE(), GETDATE(), 'pagado')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (2, 2, '2', GETDATE(), GETDATE(), 'activo')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (3, 1, '1', GETDATE(), GETDATE(), 'pendiente_pago')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (4, 2,'1', GETDATE(), GETDATE(), 'cancelado')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (5, 1, '2', GETDATE(), GETDATE(), 'pagado')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (6, 2, '1', GETDATE(), GETDATE(), 'pagado')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (7, 2, '2', GETDATE(), GETDATE(), 'cancelado')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (8, 1, '1', GETDATE(), GETDATE(), 'pendiente_pago')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (9, 1, '2', GETDATE(), GETDATE(), 'pendiente_pago')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (10, 2, '2', GETDATE(), GETDATE(), 'activo')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (11, 1, '2', GETDATE(), GETDATE(), 'cancelado')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (12, 2, '1', GETDATE(), GETDATE(), 'activo')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (13, 1, '1', GETDATE(), GETDATE(), 'activo')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (14, 2, '2', GETDATE(), GETDATE(), 'pagado')
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (3, 2, '1', GETDATE(), GETDATE(), 'Pagado')

-- 4.- Crear las funciones
-- REQ04: Función Avanzada - Calcular el promedio de gasto por reserva
CREATE OR ALTER FUNCTION CalcularPromedioGastoReserva(@ReservaID int)
    RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @TotalReserva DECIMAL(10,2)
    DECLARE @TotalConsumo DECIMAL(10,2)

    -- Obtener el monto total de la reserva
    SELECT @TotalReserva = MontoTotal
    FROM Reserva
    WHERE ReservaID = @ReservaID

    -- Calcular el promedio de gasto
    RETURN CASE
        WHEN @TotalConsumo > 0 THEN (@TotalReserva + @TotalConsumo) / 2
		ELSE @TotalReserva
    END
END
GO
-- 5.- Crear los procedimientos
-- REQ05: Procedimiento Almacenado Avanzado - Actualizar el Estado de Reserva y Generar Comprobante de Pago
CREATE OR ALTER PROCEDURE FinalizarReservaYGenerarComprobantePago
    @ReservaID int
AS
BEGIN
    DECLARE @NuevoEstadoReserva varchar(50)
    DECLARE @FechaActual datetime
    DECLARE @MontoTotal decimal(10,2)
    DECLARE @EmpleadoID int

    -- Verificar si la reserva existe y no está finalizada ni cancelada
    IF NOT EXISTS (SELECT 1 FROM Reserva WHERE ReservaID = @ReservaID)
    BEGIN
        PRINT 'Error: La reserva no existe.'
        RETURN
    END

    IF (SELECT Estado FROM Reserva WHERE ReservaID = @ReservaID) IN ('finalizado', 'cancelado')
    BEGIN
        PRINT 'Error: La reserva ya está finalizada o cancelada.'
        RETURN
    END

    -- Obtener información necesaria de la reserva
    SELECT
        @NuevoEstadoReserva = 'finalizado',
        @FechaActual = GETDATE(),
        @MontoTotal = MontoTotal,
        @EmpleadoID = EmpleadoID
    FROM Reserva
    WHERE ReservaID = @ReservaID

    -- Actualizar el estado de la reserva
    UPDATE Reserva
    SET Estado = @NuevoEstadoReserva, FechaActualizado = @FechaActual
    WHERE ReservaID = @ReservaID

      -- Generar nuevo comprobante de pago con estado "pagado"
    INSERT INTO ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, Estado, FechaPagado, FechaCreado, FechaActualizado)
    VALUES (@ReservaID, @EmpleadoID, '1', 'pagado', @FechaActual, @FechaActual, @FechaActual)

    -- Imprimir mensaje de éxito
    PRINT 'La reserva ha sido finalizada y se ha generado un comprobante de pago pagado exitosamente.'
END
GO
-- 6.- Crear las vistas
-- REQ06: Mostrar Información Detallada de Reservas con sus Consumos respectivo
CREATE OR ALTER VIEW VistaReservasDetalladas AS
SELECT
    r.ReservaID,
    r.Estado,
    r.FechaReserva,
    r.FechaEntrada,
    r.FechaSalida,
    c.PersonaID AS ClienteID,
    c.Nombre AS ClienteNombre,
    c.Apellido AS ClienteApellido,
    h.HabitacionID,
    th.Descripcion AS TipoHabitacion,
    h.Descripcion AS HabitacionDescripcion,
    h.Nivel AS HabitacionNivel,
    h.NumeroPiso AS HabitacionNumeroPiso,
    h.Precio AS PrecioHabitacion,
    rc.ProductoID,
    p.Descripcion AS ProductoDescripcion,
    rc.Cantidad AS CantidadConsumida,
    rc.Precio AS PrecioConsumo
FROM
    Reserva r
        INNER JOIN Cliente cl ON r.ClienteID = cl.ClienteID
        INNER JOIN Persona c ON cl.PersonaID = c.PersonaID
        INNER JOIN Habitacion h ON r.HabitacionID = h.HabitacionID
        INNER JOIN TipoHabitacion th ON h.TipoHabitacionID = th.TipoHabitacionID
        LEFT JOIN ReservaConsumo rc ON r.ReservaID = rc.ReservaID
        LEFT JOIN Producto p ON rc.ProductoID = p.ProductoID
GO