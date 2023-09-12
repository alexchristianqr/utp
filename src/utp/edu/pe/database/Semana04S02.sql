-- # Utilidades: JOIN
SELECT top 5 OrderId,
       OrderDate,
       Freight,
       o.EmployeeId,
       LastName,
       CompanyName,
       ShipCountry,
       e.Country
FROM Orders as o
         JOIN Employees as e ON e.EmployeeID = o.EmployeeID
         JOIN Customers as c ON o.CustomerID = c.CustomerID

-- # Utilidades: IN, IS NOT NULL
SELECT top 5 o.OrderId, o.ShipCountry
FROM Orders as o
WHERE o.ShipCountry in ('Brazil', 'Germany', 'Venezuela')
SELECT top 5 OrderId,
       OrderDate,
       Freight,
       o.EmployeeId,
       LastName,
       CompanyName,
       ShipCountry,
       e.Country
FROM Orders as o
         JOIN Employees as e ON e.EmployeeID = o.EmployeeID
         JOIN Customers as c ON o.CustomerID = c.CustomerID
where ShipCountry in ('Brazil', 'Germany', 'Venezuela')
  and ShipRegion is not null

-- # Utilidades: NOT IN, IS NOT NULL
SELECT top 5 o.OrderId, o.ShipCountry
FROM Orders as o
WHERE o.ShipCountry not in ('Brazil', 'Germany', 'Venezuela')
SELECT top 5 OrderId,
       OrderDate,
       Freight,
       o.EmployeeId,
       LastName,
       CompanyName,
       ShipCountry,
       e.Country
FROM Orders as o
         JOIN Employees as e ON e.EmployeeID = o.EmployeeID
         JOIN Customers as c ON o.CustomerID = c.CustomerID
where ShipCountry not in ('Brazil', 'Germany', 'Venezuela')
  and ShipRegion is not null


-- # Utilidades: LIKE
SELECT top 5 OrderId,
       OrderDate,
       Freight,
       o.EmployeeId,
       LastName,
       CompanyName,
       ShipCountry,
       e.Country
FROM Orders as o
         JOIN Employees as e ON e.EmployeeID = o.EmployeeID
         JOIN Customers as c ON o.CustomerID = c.CustomerID
where ShipCountry not in ('Brazil', 'Germany', 'Venezuela')
  and ShipRegion is not null
  and Freight between 100 and 200
  and CompanyName like '__e%'

