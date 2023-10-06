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
	
	select top 1 @nombre = 'En el año de: '+ @periodoCadena + ' y  en el mes de '+ @mesCadena + ' el empleado es' + LastName + ' '+cast(sum(UnitPrice * Quantity * (1 - Discount)) as varchar(15)) 
	from OrderDetails od 
	join Orders o on o.OrderID = od.OrderID
	join Employees e on e.EmployeeID = o.EmployeeID
	where year(OrderDate)

	return @frase
end

select dbo.fEmpleadoDelMes('Chop-suey Chinese')