
select * from Categories
go

insert into Categories (CategoryName) values ('Textiles')
go

insert into Categories (CategoryName, Description) values ('Refrigeración', 'Equipo de congelamiento')
go

insert into Categories values ('Transporte', 'Movilidad de carga y personal',null)
go

-- Consultas
select top 3 * from Categories order by CategoryID desc
select top 1 * from Products order by ProductID desc
select top 1 * from Employees order by EmployeeID desc
select top 5 * from OrderDetails
go

insert into Products (ProductName) values ('Refrigeradora MABE')
go

insert into Employees(LastName, FirstName) values ('Ochante', 'Pedro')
go

update Categories set 
CategoryName = 'Area/textil',
Description = 'Un area destinado a la tela'
where CategoryID = 10

-- update OrderDetails set UnitPrice = 19.90
go

-- update OrderDetails set UnitPrice = (select UnitPrice from Products where Products.ProductID = OrderDetails.ProductID)
go

-- update Products set CategoryID = 11
-- where ProductID = 78
go

select * from Products where ProductID = 78
go

-- update Employees set Salary = 6000 where EmployeeID = 12
go

--delete from Categories where CategoryID = 11
--delete from OrderDetails
go

--create table BackupOrderDetails
--(
--OrderID int not null,
--ProductID int not null,
--UnitPrice money  not null,
--Quantity smallint  not null,
--Discount real not null,
--primary key (OrderID,ProductID),
--foreign key (OrderID) references Orders(OrderID),
--foreign key (ProductID) references Products(ProductID)
--)
--go

select * from BackupOrderDetails
go

insert into BackupOrderDetails values (select OrderID, ProductID, UnitPrice, Quantity, Discount from OrderDetails)
go


-- Error
select 
c.CompanyName,
sum(Freight),
year(OrderDate)
from Orders o
join Customers c on c.CustomerID = o.CustomerID
where c.CompanyName like 'WOLS%'
group by OrderDate,c.CompanyName
go

-- OVER Solucion 01
select distinct
c.CompanyName,
sum(Freight) over(partition by o.CustomerID) as 'Gasto cliente',
sum(Freight) over(partition by year(o.OrderDate)) as 'Gasto por año',
year(OrderDate) as 'Año',
e.FirstName,
sum(Freight) over(partition by o.EmployeeID) as 'Gasto por empleado'
from Orders o
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID
where c.CompanyName like 'WOLS%'
go

-- OVER Solucion 02
select distinct
c.CompanyName,
sum(Freight) over(partition by o.CustomerID) as 'Gasto cliente',
sum(Freight) over(partition by year(o.OrderDate)) as 'Gasto por año',
year(OrderDate) as 'Año',
e.FirstName,
sum(Freight) over(partition by o.EmployeeID, o.CustomerID, year(o.OrderDate)) as 'Gasto por empleado'-- Particionado por ID empleado, ID cliente y Año
from Orders o
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID
where c.CompanyName like 'WOLS%'
go

-- OVER y SUB-CONSULTA
select distinct
c.CompanyName,
sum(Freight) over(partition by o.CustomerID) as 'Gasto cliente',
sum(Freight) over(partition by year(o.OrderDate)) as 'Gasto por año',
year(OrderDate) as 'Año',
(select e.FirstName from Employees e where e.EmployeeID = o.EmployeeID) as 'Empleado',
sum(Freight) over(partition by o.EmployeeID, o.CustomerID, year(o.OrderDate)) as 'Gasto por empleado'-- Particionado por ID empleado, ID cliente y Año
from Orders o
join Customers c on c.CustomerID = o.CustomerID
where c.CompanyName like 'WOLS%'
go

-- PIVOT ejemplo 01
select * from 
(select 
Freight,
year(OrderDate) as Periodo,
CompanyName
from Orders o
join Customers c on c.CustomerID = o.CustomerID) as Fuente
pivot 
(sum(Freight) for Periodo in ([1996],[1997],[1998]))as Resultado
go

-- PIVOT Ejemplo 01
select * from 
(select 
Freight,
year(OrderDate) as Periodo,
CompanyName
from Orders o
join Customers c on c.CustomerID = o.CustomerID) as Fuente
pivot 
(sum(Freight) for Periodo in ([1996],[1997],[1998]))as Resultado
go

-- PIVOT Ejemplo 02
select * from 
(select 
Freight,
LastName,
CompanyName
from Orders o
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID ) as Fuente
pivot 
(sum(Freight) for LastName in ([Davolio],[Fuller]))as Resultado
go

-- PIVOT Ejemplo 03
select * from 
(select 
year(OrderDate) as Periodo,
month(OrderDate) as Mes,
UnitPrice * Quantity * (1-Discount) as Venta,
CompanyName
from Orders o
join OrderDetails od on od.OrderID = o.OrderID
join Customers c on c.CustomerID = o.CustomerID
join Employees e on e.EmployeeID = o.EmployeeID ) as Fuente
pivot 
(sum(Venta) for Mes in ([1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12])) as Resultado
go







