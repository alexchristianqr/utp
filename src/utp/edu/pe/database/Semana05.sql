-- # Utilidades: HAVING
select avg(e.Salary) from Employees as e
having avg(e.Salary) > 100
go

-- # Utilidades: YEAR
select 
year(o.OrderDate) as 'Periodo' ,
o.CustomerID, 
CompanyName,
o.EmployeeID,
LastName+','+FirstName as 'Empleado',
Freight
from Orders o 
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID
order by year(o.OrderDate)
go

-- # Utilidades: YEAR, SUM, GROUP BY, ORDER BY
select 
year(o.OrderDate) as 'Periodo' ,
concat('S/.',sum(Freight)) as 'Gasto total',
concat('S/.',avg(Freight)) as 'Gasto promedio'
from Orders o 
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID
group by year(o.OrderDate)
order by year(o.OrderDate) desc
go

-- # Utilidades: YEAR, SUM, GROUP BY, ORDER BY, HAVING
select 
year(o.OrderDate) as 'Periodo' ,
concat('S/.',sum(Freight)) as 'Gasto total',
concat('S/.',avg(Freight)) as 'Gasto promedio'
from Orders o 
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID
group by year(o.OrderDate)
having avg(Freight) < 70 -- Aplicar HAVING depsues de obtener los registros, después de agrupar
order by year(o.OrderDate) desc
go

-- # Utilidades: YEAR, SUM, GROUP BY, ORDER BY, HAVING
select 
year(o.OrderDate) as 'Periodo' ,
concat('S/.',sum(Freight)) as 'Gasto total', -- SUM
count(o.CustomerID) as 'Cantidad de ordenes', -- COUNT
concat('S/.',avg(Freight)) as 'Gasto promedio', -- AVG
concat('S/.',min(Freight)) as 'Valor minimo',-- MIN
concat('S/.',max(Freight)) as 'Valor maximo' -- MAX
from Orders o 
join Customers c on c.CustomerID = o.CustomerID -- Unir con la tabla clientes
join Employees e on e.EmployeeID = o.EmployeeID -- Unir con la tabla empleados
where o.CustomerID = 'ALFKI' -- Filtrando los datos
group by year(o.OrderDate)
having sum(Freight) between 500 and 600 -- Filtrando los calculos
order by year(o.OrderDate) desc
go

select 
o.OrderID, 
od.UnitPrice, 
od.Quantity, 
od.Discount, 
sum( od.UnitPrice * od.Quantity) as 'Suma'
from OrderDetails od 
join Orders o on o.OrderID = od.OrderID
where o.OrderID = 10248
group by o.OrderID, od.UnitPrice, od.Quantity, od.Discount
order by 5 desc -- Ordenar por posición

select 
top 5
o.OrderID, sum( od.UnitPrice * od.Quantity) as 'Suma'
from OrderDetails od 
join Orders o on o.OrderID = od.OrderID
group by o.OrderID
order by 1 desc -- Ordenar por posición

select 
top 5
od.UnitPrice * od.Quantity * (1-od.Discount) as 'Calculo'
from OrderDetails od
