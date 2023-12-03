-- (DML) DATA MANIPULATION LANGUAGE

-- Perfil
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Administrador', '{}', GETDATE())
insert into Perfil (Nombre, Permisos, FechaCreado) values ('Recepcionista', '{}', GETDATE())

-- Persona Empleado
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Luis', 'Torres', '1', '12345678', 'M', '30', GETDATE())
insert into Empleado (PersonaID, PerfilID, Sueldo, FechaCreado) values (SCOPE_IDENTITY(), 1, 1250.69, GETDATE())

-- Persona Cliente
insert into Persona (Nombre, Apellido, TipoDocumento, NroDocumento, Sexo, Edad, FechaCreado) values ('Javier', 'Retamoso', '1', '74567890', 'M', '30', GETDATE())
insert into Cliente (PersonaID, Empresa, FechaCreado) values (SCOPE_IDENTITY(), null, GETDATE())

-- Tipo Habitacion
insert into TipoHabitacion (Descripcion, FechaCreado) values ('Clasico', GETDATE())

-- Habitacion
insert into Habitacion (TipoHabitacionID, Descripcion, Nivel, NumeroPiso, Precio, CantidadCamas, FechaCreado) values (1, 'Habitación con agua caliente + TV', '1', '101', 49.50, 1, GETDATE())

-- Reserva
insert into Reserva (ClienteID, HabitacionID, EmpleadoID, MontoTotal, CantidadPersonas, Estado, FechaReserva, FechaEntrada, FechaSalida, FechaCreado) values (1, 1, 1, 89.90, 2, 'activo', GETDATE(), GETDATE(), GETDATE(), GETDATE())

-- Producto
insert into Producto (Descripcion, Precio, CantidadStock, FechaCreado) values ('Botella de agua cielo', 5, 100, GETDATE())

-- Reserva Consumo
insert into ReservaConsumo (ReservaID, ProductoID, Cantidad, Precio, FechaCreado) values (1, 1, 3, 5.99, GETDATE())

-- Comprobante Pago
insert into ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, FechaCreado, FechaPagado, Estado) values (1, 1, '1', GETDATE(), GETDATE(), 'Pagado')