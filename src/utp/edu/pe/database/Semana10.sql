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
where year(OrderDate) = 1997 and month(OrderDate) between  1 and 12 and o.CustomerID = 'SAVEA' and o.EmployeeID = 1
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

