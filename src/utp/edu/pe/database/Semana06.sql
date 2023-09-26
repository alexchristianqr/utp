-- # Consulta con INNER JOIN
select
o.OrderID,
o.OrderDate,
c.CompanyName,
e.LastName+', '+e.FirstName as 'Nombre Completo',
o.Freight
from Orders o
inner join Employees e on e.EmployeeID = o.EmployeeID
inner join Customers c on c.CustomerID = o.CustomerID
go

-- # Subconsulta SELECT de SELECT
select
o.OrderID,
o.OrderDate,
 (select c.CompanyName from Customers c where c.CustomerID = o.CustomerID) as 'Empresa',
e.LastName+', '+e.FirstName as 'Nombre Completo',
o.Freight
from Orders o
join Employees e on e.EmployeeID = o.EmployeeID
go

-- # SELECT de SELECT
-- Seleccionar valor promedio
select avg(e.Salary) from Employees e

-- Integrar campo valor del promedio
select e.EmployeeID, e.LastName, e.Salary 
from Employees e
where Salary < (select avg(e.Salary) from Employees e)
go

-- Actualizar salario
UPDATE Employees set Salary = Salary * 1.30
where EmployeeID = 9
go

-- # Vista con SELECT de SELECT
create view vTotalOrdenes as
select 
o.OrderID,
o.OrderDate,
(select sum(UnitPrice * Quantity * (1 - Discount)) from OrderDetails od where od.OrderID = o.OrderID) as 'Valor Orden',
(select count(*) from OrderDetails od where od.OrderID = o.OrderID) as 'Cantidad de Productos',
c.CompanyName,e.LastName, o.ShipCountry, o.ShipCity
from Orders o
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID

-- # INNER JOIN
select
LastName,
o.EmployeeID,
OrderID
from Orders o
join Employees e on e.EmployeeID = o.EmployeeID -- Todos los registros que NO incluyen valores nulos

-- # RIGHT JOIN
select
LastName,
o.EmployeeID,
OrderID
from Orders o right join Employees e on e.EmployeeID = o.EmployeeID -- Todos los registros que SI incluyen valores nulos
--            --> Hacia la derecha

-- # LEFT JOIN
select
LastName,
o.EmployeeID,
OrderID
from Orders o left join Employees e on e.EmployeeID = o.EmployeeID -- Todos los registros que SI incluyen valores nulos
--			  <-- Hacia la izquierda

-- # FULL JOIN
select
LastName,
o.EmployeeID,
OrderID
from Orders o full join Employees e on e.EmployeeID = o.EmployeeID -- Todos los registros que SI incluyen valores nulos







