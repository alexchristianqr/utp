-- # Obtener todos los registros de la tabla empleados
select * from Employees;

-- # Obtener 3 campos de la tabla empleados
select e.FirstName, e.LastName, e.Salary from Employees as e;

-- # Utilidades: CONCAT
select concat(e.FirstName, ' ', e.LastName) as 'NombreCompleto', e.Salary as 'Salario' from Employees as e;
select concat(e.FirstName, ' ', e.LastName) as 'NombreCompleto', (e.Salary * 12) as 'Salario' from Employees as e;

-- # Utilidades: DISTINCT
select distinct Country from Employees as e

-- # Utilidades: TOP
select top 3 c.CompanyName from Customers as c

-- # Utilidades: ORDER BY
select * from Customers as c order by c.CustomerID desc

-- # Utilidades: WHERE, OR, AND
select o.OrderID, o.CustomerID, o.EmployeeID, o.Freight, o.OrderDate, o.ShipCountry from Orders as o where o.ShipCountry = 'Brazil'
select o.OrderID, o.Freight, o.OrderDate, o.ShipCountry from Orders as o 
where (o.ShipCountry = 'Brazil' or o.ShipCountry = 'USA') and o.Freight >= 100 and o.Freight <= 150

-- # Utilidades: CAST, CONVERT
select 
concat(e.FirstName, ' ', e.LastName) as 'NombreCompleto', 
'S/.' + cast((e.Salary * 12) as char(6)) as 'SalarioAnual' 
from Employees as e
go
select 
concat(e.FirstName, ' ', e.LastName) as 'NombreCompleto', 
'S/.' + convert(char(6), e.Salary) as 'Salario' 
from Employees as e
go
