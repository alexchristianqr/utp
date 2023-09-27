
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
