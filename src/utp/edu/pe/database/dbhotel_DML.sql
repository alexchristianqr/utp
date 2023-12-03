-- (DML) DATA MANIPULATION LANGUAGE

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