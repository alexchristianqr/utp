-- Ejemplo de consulta con INNER JOIN y GROUP BY
select 
sum(Freight) as 'Suma total', 
OrderDate, 
Freight, 
o.CustomerID, 
o.EmployeeID, 
e.LastName
from Orders o
inner join Customers c on c.CustomerID = o.CustomerID
inner join Employees e on e.EmployeeID = o.EmployeeID
where year(OrderDate) = 1997 and month(OrderDate) between  1 and 12
group by OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName -- Aplicando agrupamiento
order by month(OrderDate) desc
go

-- Ejemplo de consulta con INNER JOIN y OVER PARTITION
select
sum(Freight) over(partition by  o.CustomerID, o.EmployeeID) as 'Suma total',
o.CustomerID,
c.CompanyName, 
o.EmployeeID, 
e.LastName
from Orders o
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID
where year(OrderDate) = 1997 and (month(OrderDate) between  1 and 12) and o.CustomerID = 'SAVEA' and o.EmployeeID = 1
go

-- * --

-- Ejemplo crear o actualizar FUNCTION
create or alter function dbo.FnListarGastosAcumulados
(
	@periodo int,
	@conteo int
) returns table
as
return 
(
	select 
	sum(Freight) as 'Suma total', 
	count(o.CustomerID) as 'Conteo total'
	OrderDate, 
	Freight, 
	o.CustomerID, 
	o.EmployeeID, 
	e.LastName
	from Orders o
	join Customers c on c.CustomerID = o.CustomerID
	join Employees e on e.EmployeeID = o.EmployeeID
	where year(OrderDate) = @periodo
	group by OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName
	having count(o.CustomerID) >= @conteo
)
go

-- Ejecutar FUNCTION
select * from dbo.FnListarGastosAcumulados(1996, 1)
go

-- * --
	
-- Ejemplo crear o actualizar PROCEDURE
create or alter procedure SpListarGastosAcumulados
(
	@periodo int,
	@conteo int
)
as
begin 
	select 
	sum(Freight) as 'Suma total', 
	count(o.CustomerID) as 'Conteo total',
	OrderDate, 
	Freight, 
	o.CustomerID, 
	o.EmployeeID, 
	e.LastName
	from Orders o
	join Customers c on c.CustomerID = o.CustomerID
	join Employees e on e.EmployeeID = o.EmployeeID
	where year(OrderDate) = @periodo
	group by OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName
	having count(o.CustomerID) >= @conteo
end
go

-- Ejecutar PROCEDURE
exec SpListarGastosAcumulados 1996, 1
go
















-- Pregunta 01

-- max(select count(*) from Products pro on od.ProductID) as 'Menos vendido'


select top 5
od.ProductID, 
p.ProductName, 
od.Quantity,
od.OrderID,
count(p.UnitPrice * od.Quantity * (1 - Discount))
-- (select sum(UnitPrice * Quantity * (1 - Discount)) from OrderDetails od where od.OrderID = o.OrderID) as 'Valor Orden',
-- min(od.Quantity) as 'Menos vendido'
from OrderDetails od
inner join Products p on p.ProductID = od.ProductID
inner join Suppliers s on s.SupplierID = p.SupplierID
where s.Country in ('Japan', 'Finland')
group by od.ProductID, p.ProductName, od.Quantity, od.OrderID
go

-- # Vista con SELECT de SELECT
--create view vTotalOrdenes as
--select 
--o.OrderID,
--o.OrderDate,
--(select sum(UnitPrice * Quantity * (1 - Discount)) from OrderDetails od where od.OrderID = o.OrderID) as 'Valor Orden',
--(select count(*) from OrderDetails od where od.OrderID = o.OrderID) as 'Cantidad de Productos',
--c.CompanyName,e.LastName, o.ShipCountry, o.ShipCity
--from Orders o
--join Customers c on c.CustomerID = o.CustomerID
--join Employees e on e.EmployeeID = o.EmployeeID



-- Pregunta 02

create or alter function dbo.FnNombreApellido
(
	@ProductID int
) returns varchar (250)
as
begin
	declare @nombrecompleto varchar(250)

	select @nombrecompleto = e.FirstName + ' '+ e.LastName 
	from Orders o 
	join OrderDetails od on od.OrderID = o.OrderID
	join Employees e on e.EmployeeID = o.EmployeeID
	join Products p on p.ProductID = od.ProductID
	where od.ProductID = @ProductID
	group by e.FirstName ,e.LastName ,od.Quantity
	having od.Quantity <= max(od.Quantity)

	return @nombrecompleto
end
go

select dbo.FnNombreApellido(1) as 'Nombre completo empleado'


-- Pregunta 03
select 
e.FirstName,
count(o.OrderID)
from 
Orders o
join Employees e on  e.EmployeeID = o.EmployeeID
where year(o.ShippedDate) = '1996' 
-- Pregunta 04

Preparar una consulta que permita obtener la suma y el promedio del valor de las ordenes (precio unitario, cantidad y descuento) por cliente para aquellas ordenes que fueron registradas en el año 1998 utilizando la cláusula OVER. Se deberá mostrar el nombre del cliente evitando los registros repetidos.


Preparar una consulta que permita listar todos los empleados que tengan más de 20 órdenes registradas en el año 1996.


Pregunta 26 pts
Crear una función que reciba el Id del Producto y devuelva el nombre y el apellido del empleado que recibió la orden con la mayor cantidad del producto indicado.

Realizar una consulta que muestre los primeros 5 productos menos vendidos de los proveedores cuyos países son de Japón y Finlandia. Se debe mostrar el ID del Producto, la Descripción y la Cantidad.



    SELECT TOP 5
    p.ProductID,
    p.ProductName as Descripcion,
    od.Quantity
FROM
    Products p
    JOIN
    OrderDetails od ON p.ProductID = od.ProductID
    JOIN
    Orders o ON od.OrderID = o.OrderID
    JOIN
    Suppliers s ON p.SupplierID = s.SupplierID
WHERE
    s.Country IN ('Japan', 'Finland')
GROUP BY
    p.ProductID, p.ProductName, od.Quantity
ORDER BY
    SUM(od.Quantity) ASC;



CREATE FUNCTION ObtenerEmpleadoConMasCantidad(@ProductoID INT)
    RETURNS TABLE
    AS
RETURN
(
    SELECT TOP 1
        e.FirstName,
        e.LastName
    FROM
        Employees e
    JOIN
        Orders o ON e.EmployeeID = o.EmployeeID
    JOIN
        OrderDetails od ON o.OrderID = od.OrderID
    WHERE
        od.ProductID = @ProductoID
    GROUP BY
        e.FirstName, e.LastName
    ORDER BY
        SUM(od.Quantity) DESC
);

SELECT * FROM ObtenerEmpleadoConMasCantidad(2);

SELECT
    e.EmployeeID,
    e.FirstName,
    e.LastName,
    COUNT(o.OrderID) as NumeroDeOrdenes
FROM
    Employees e
        JOIN
    Orders o ON e.EmployeeID = o.EmployeeID
WHERE
    YEAR(o.OrderDate) = 1996
GROUP BY
    e.EmployeeID, e.FirstName, e.LastName
HAVING
    COUNT(o.OrderID) > 20;


SELECT DISTINCT
    c.CustomerID,
    c.CompanyName,
    SUM(od.UnitPrice * od.Quantity * (1 - od.Discount)) OVER (PARTITION BY c.CustomerID) as SumaTotal,
        AVG(od.UnitPrice * od.Quantity * (1 - od.Discount)) OVER (PARTITION BY c.CustomerID) as PromedioTotal
FROM
    Customers c
        JOIN
    Orders o ON c.CustomerID = o.CustomerID
        JOIN
    OrderDetails od ON o.OrderID = od.OrderID
WHERE
    YEAR(o.OrderDate) = 1998;