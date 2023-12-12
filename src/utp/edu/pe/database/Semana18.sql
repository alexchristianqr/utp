-- 01
select e.EmployeeID, e.FirstName, e.Salary from Employees e
where e.Salary > (select avg(em.Salary) from Employees em) and e.Country = 'USA'

-- 02
select e.EmployeeID, e.FirstName, e.Salary from Employees e
where e.Salary < (select avg(em.Salary) from Employees em) and e.Country = 'UK'

-- 03
select
    concat(e.FirstName, ' ', e.LastName) as 'Nombre completo',
    e.Title,
    min(e.Salary) as 'Salario minimo',
    max(e.Salary) as 'Salario maximo',
    avg(e.Salary) as 'Promedio'
from Employees e
join EmployeeTerritories et on et.EmployeeID = e.EmployeeID
right join Territories t on t.TerritoryID = et.TerritoryID
right join Region r on r.RegionID = t.RegionID
where r.RegionDescription = 'Northern'
group by e.FirstName, e.LastName,e.Title,e.Salary
    go

Select Title, MIN(Salary) as SalarioMinimo, MAX(Salary) as SalarioMáximo, AVG(Salary) as SalarioPromedio
from Employees e
inner join EmployeeTerritories et ON e.EmployeeID=et.EmployeeID
inner join Territories t ON et.TerritoryID=t.TerritoryID
inner join Region r ON t.RegionID=r.RegionID
Where RegionDescription='Northern'
Group by Title

-- 04
select
    c.CompanyName,
    c.Country,
    count(o.OrderID) as 'Cantidad de ordenes'
from Orders o
join Customers c on c.CustomerID = o.CustomerID
where year(o.ShippedDate) = '1996'
group by c.CompanyName,c.Country
order by c.Country
    go

-- 05
select
    concat(e.FirstName, ', ', e.LastName) as 'Trabajador',
    e.Salary,
    count(c.CompanyName) as 'Cantidad clientes atendidos'
from Orders o
join Employees e on e.EmployeeID = o.EmployeeID
join Customers c on c.CustomerID = o.CustomerID
where year(o.ShippedDate) = '1997'
group by e.FirstName,e.LastName,e.Salary
order by e.FirstName asc
go
