-- DD/MM/YYYY hh:mm.ss
-- Ejemplo JOIN
select 
sum(Freight) as 'Total', OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName
from Orders o
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID
where year(OrderDate) = 1997 and month(OrderDate) between  7 and 9
group by OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName
order by month(OrderDate) desc
go

-- Ejemplo de FUNCTION
create or alter function dbo.ListarGastosAcumulados
(
	@periodo int,
	@conteo int
) returns table
as
return 
(
	select 
	sum(Freight) as 'Total suma', OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName, count(o.CustomerID) as 'Conteo total'
	from Orders o
	join Customers c on c.CustomerID = o.CustomerID
	join Employees e on e.EmployeeID = o.EmployeeID
	where year(OrderDate) = @periodo -- and month(OrderDate) between  7 and 9
	group by OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName
	having count(o.CustomerID) >= @conteo
	-- order by month(OrderDate) desc
)
go

-- Ejecutar FUNCTION
select * from dbo.ListarGastosAcumulados(1996,1)
go

-- Ejemplo de FUNCTION
create or alter function dbo.ListarGastosAcumulados2
(
	@periodo int,
	@conteo int
) returns table
as
return 
(
	select 
	sum(Freight) as 'Total suma', OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName, count(o.CustomerID) as 'Conteo total'
	from Orders o
	join Customers c on c.CustomerID = o.CustomerID
	join Employees e on e.EmployeeID = o.EmployeeID
	where year(OrderDate) = @periodo -- and month(OrderDate) between  7 and 9
	group by OrderDate, Freight, o.CustomerID, o.EmployeeID, e.LastName
	having count(o.CustomerID) >= @conteo
	-- order by month(OrderDate) desc
)
go

select
sum(Freight) over(partition by  o.CustomerID, o.EmployeeID) as 'Gasto total',
o.CustomerID,c.CompanyName, o.EmployeeID, e.LastName
from Orders o
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID
where year(OrderDate) = 1997 and o.CustomerID = 'SAVEA' and o.EmployeeID = 1
go
