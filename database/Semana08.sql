CREATE
OR
ALTER FUNCTION dbo.fValorOrdenCompra
    (
    @nroOrden int
    )
    RETURNS money
    AS
BEGIN
	DECLARE
@monto money

SELECT @monto = SUM(UnitPrice * Quantity * (1 - Discount))
FROM OrderDetails
WHERE OrderId = @nroOrden return @monto
END

SELECT dbo.fValorOrdenCompra(10248)

CREATE
OR
ALTER FUNCTION dbo.fValorOrdenCompraXCliente
    (
    @empresa varchar (30)
    )
    RETURNS money
    AS
BEGIN
	DECLARE
@monto money

SELECT @monto = SUM(UnitPrice * Quantity * (1 - Discount))
FROM OrderDetails od
         JOIN Orders o ON o.OrderID = od.OrderID
         JOIN Customers c ON c.CustomerID = o.CustomerID
WHERE CompanyName = @empresa return @monto
END

select dbo.fValorOrdenCompraXCliente('Chop-suey Chinese')

CREATE
OR
ALTER FUNCTION dbo.fEmpleadoDelMes
    (
    @mes int,
    @periodo int
    )
    RETURNS varchar (120)
    AS
BEGIN
	DECLARE
@mesC varchar(15)
	DECLARE
@periodoC varchar(4)

	DECLARE
@nombre varchar(50)
	DECLARE
@monto money
	DECLARE
@frase varchar(120)

SELECT @MesC = NombreMes
FROM Meses
WHERE MesId = @mes SET @periodoC = CAST(@periodo as varchar(4))

SELECT TOP 1 @nombre = LastName + ', ' + FirstName, @monto = SUM(UnitPrice * Quantity * (1 - Discount))
FROM OrderDetails od
         JOIN Orders o ON o.OrderID = od.OrderID
         JOIN Employees e ON e.EmployeeID = o.EmployeeID
WHERE YEAR (OrderDate) = @periodo AND MONTH (OrderDate) = @mes
GROUP BY LastName, FirstName
ORDER BY SUM (UnitPrice * Quantity * (1 - Discount)) DESC

SET @frase = 'En el anio de ' + @periodoC + ' y en el mes de ' + @mesC + ' el empleado TOP es '
SET @frase = @frase + @nombre + ' con el monto total de ' + CAST (@monto as varchar (10))
    return @frase
END

SELECT dbo.fEmpleadoDelMes(12, 1997)

CREATE
OR
ALTER FUNCTION dbo.fEmpleadoDelMesTabla
    (
    @mes int,
    @periodo int
    )
    RETURNS TABLE
    AS
    RETURN
    (
    SELECT LastName + ', ' + FirstName AS Empleado, SUM (UnitPrice * Quantity * (1 - Discount)) AS Monto
    FROM OrderDetails od
    JOIN Orders o ON o.OrderID = od.OrderID
    JOIN Employees e ON e.EmployeeID = o.EmployeeID
    WHERE YEAR (OrderDate) = @periodo AND MONTH (OrderDate) = @mes
    GROUP BY LastName, FirstName
    )

SELECT *
FROM dbo.fEmpleadoDelMesTabla(12, 1997)
ORDER BY 2 DESC

-- Ejemplo de F1
create or alter function dbo.fValorOrdenCompra
(
	@nroorden int
)
returns int
as
begin
	declare @orden int

	select @orden = od.OrderID from OrderDetails od 
	join Orders o on o.OrderID = od.OrderID
	join Customers c on c.CustomerID = o.CustomerID
	where OD.OrderID = @nroorden

	return @orden
end

select dbo.fValorOrdenCompra(10248)

	
-- Ejemplo de F2
create or alter function dbo.fValorOrdenCompraPorCliente
(
	@empresa varchar(30)
)
returns money
as
begin
	declare @monto money
	
	select @monto = sum(UnitPrice * Quantity * (1 - Discount)) from OrderDetails od 
	join Orders o on o.OrderID = od.OrderID
	join Customers c on c.CustomerID = o.CustomerID
	where CompanyName = @empresa

	return @monto
end

select dbo.fValorOrdenCompraPorCliente('Chop-suey Chinese')

-- Ejemplo de F3
create or alter function dbo.fEmpleadoDelMes
(
	@mes int,
	@periodo int
)
returns varchar(100)
as
begin
	declare @nombre varchar(100)
	declare @mesCadena varchar(100)
	declare @periodoCadena varchar(100)
	declare @frase varchar(100)

	set @mesCadena = cast(@mes as varchar(2))
	
	select top 1 @nombre = 'En el a�o de: '+ @periodoCadena + ' y  en el mes de '+ @mesCadena + ' el empleado es' + LastName + ' '+cast(sum(UnitPrice * Quantity * (1 - Discount)) as varchar(15)) 
	from OrderDetails od 
	join Orders o on o.OrderID = od.OrderID
	join Employees e on e.EmployeeID = o.EmployeeID
	where year(OrderDate)

	return @frase
end

select dbo.fEmpleadoDelMes('Chop-suey Chinese')
