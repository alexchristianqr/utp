-- REQ01: Obtener la cantidad de reservas por tipo de habitación
SELECT
    th.Descripcion AS TipoHabitacion,
    COUNT(r.ReservaID) AS CantidadReservas
FROM
    Reserva r
        INNER JOIN Habitacion h ON r.HabitacionID = h.HabitacionID
        INNER JOIN TipoHabitacion th ON h.TipoHabitacionID = th.TipoHabitacionID
GROUP BY
    th.Descripcion
GO

-- REQ02: Listar las reservas activas con detalles de habitación y cliente
SELECT
    r.ReservaID,
    r.Estado,
    h.Descripcion AS Habitacion,
    p.Nombre AS ClienteNombre,
    p.Apellido AS ClienteApellido
FROM
    Reserva r
        INNER JOIN Habitacion h ON r.HabitacionID = h.HabitacionID
        INNER JOIN Cliente c ON r.ClienteID = c.ClienteID
        INNER JOIN Persona p ON c.PersonaID = p.PersonaID
WHERE
        r.Estado = 'activo'
GO

-- REQ03: Obtener el total de ingresos por empleado en un rango de fechas
SELECT
    e.EmpleadoID,
    pe.Nombre,
    p.Nombre AS EmpleadoNombre,
    p.Apellido AS EmpleadoApellido,
    SUM(r.MontoTotal) AS TotalIngresos
FROM
    Empleado e
        INNER JOIN Persona p ON e.PersonaID = p.PersonaID
        INNER JOIN Reserva r ON e.EmpleadoID = r.EmpleadoID
        INNER JOIN ComprobantePago cp ON r.ReservaID = cp.ReservaID
        INNER JOIN Perfil pe ON pe.PerfilID = e.PerfilID
WHERE
    cp.FechaPagado BETWEEN '2023-01-01' AND '2023-12-31'
GROUP BY
    e.EmpleadoID, p.Nombre, p.Apellido, pe.Nombre
GO

-- REQ04: Función Avanzada: Calcular el promedio de gasto por reserva
CREATE FUNCTION CalcularPromedioGastoReserva(@ReservaID int)
    RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @TotalReserva DECIMAL(10,2)
    DECLARE @TotalConsumo DECIMAL(10,2)

    -- Obtener el monto total de la reserva
    SELECT @TotalReserva = MontoTotal
    FROM Reserva
    WHERE ReservaID = @ReservaID

    -- Obtener el total de consumo asociado a la reserva
    SELECT @TotalConsumo = SUM(Cantidad * Precio)
    FROM ReservaConsumo
    WHERE ReservaID = @ReservaID

    -- Calcular el promedio de gasto
    RETURN CASE
        WHEN @TotalConsumo > 0 THEN (@TotalReserva + @TotalConsumo) / 2
		ELSE @TotalReserva
    END
END
GO

-- Ejecutar funcion
select dbo.CalcularPromedioGastoReserva(1)

-- REQ05: Procedimiento Almacenado Avanzado: Actualizar el Estado de Reserva y Generar Comprobante de Pago
CREATE PROCEDURE ActualizarEstadoReservaYGenerarComprobantePago
    @ReservaID int,
    @TipoComprobante varchar,
    @Estado varchar
AS
BEGIN
    DECLARE @NuevoEstado varchar(50)
    DECLARE @FechaActual datetime
    DECLARE @MontoTotal decimal(10,2)
    DECLARE @EmpleadoID int

    -- Obtener información necesaria de la reserva
    SELECT
        @NuevoEstado = CASE
            WHEN Estado = 'reservado' THEN 'en_proceso'
            WHEN Estado = 'en_proceso' THEN 'finalizado'
            ELSE Estado
        END,
        @FechaActual = GETDATE(),
        @MontoTotal = MontoTotal,
        @EmpleadoID = EmpleadoID
    FROM Reserva
    WHERE ReservaID = @ReservaID

    -- Actualizar el estado de la reserva
    UPDATE Reserva
    SET Estado = @NuevoEstado, FechaActualizado = @FechaActual
    WHERE ReservaID = @ReservaID

    -- Generar nuevo comprobante de pago
    INSERT INTO ComprobantePago (ReservaID, EmpleadoID, TipoComprobante, Estado, FechaCreado, FechaActualizado)
    VALUES (@ReservaID, @EmpleadoID, @TipoComprobante, @Estado, @FechaActual, @FechaActual)

    -- Imprimir mensaje de éxito
    PRINT 'Estado de reserva actualizado y nuevo comprobante de pago generado exitosamente.'
END
GO

-- Ejecutar procedimineto almacenado
exec ActualizarEstadoReservaYGenerarComprobantePago 1, '1', 'pendiente_pago'

-- REQ06: Vista asociada a un Requerimiento de Negocio: Información Detallada de Reservas con Consumos
CREATE VIEW VistaReservasDetalladas AS
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

-- Ejecutar vista
SELECT ReservaID, count(ProductoID)
FROM VistaReservasDetalladas
GROUP BY ReservaID
GO

