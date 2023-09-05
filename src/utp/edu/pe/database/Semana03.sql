-- # Crear base de datos
create database ColeccionLibros
go

-- # Usar base de datos
use ColeccionLibros
go

-- # Actualizar base de datos
-- alter database Coleccionlibros MODIFY NAME =  ColeccionLibros

-- # Crear tabla Autor
create table Autor
(
Codigo int not null,
Nombre varchar(250) not null,
paisNacimiento varchar(50)
primary key (Codigo)
)
go

-- # Eliminar columna de la tabla
-- alter table Autor drop column paisNacimiento

-- # Actualizar columna de la tabla
-- alter table Autor alter column paisNacimiento varchar(50) 

-- # Agregar columna a la tabla
-- alter table Autor add PaisNacimiento varchar(50) 

-- # Insertar registro a la tabla Autor
insert into Autor (Codigo, Nombre, PaisNacimiento) values (1,'Cesar Vallejo','Peru')
insert into Autor (Codigo, Nombre, PaisNacimiento) values (2,'Ricardo Palma','Peru')
go

-- # Mostrar tabla Autor
select * from Autor
go

-- # Actualizar registro de la tabla Autor por ID
update Autor set PaisNacimiento = 'Peru' where Autor.Codigo = 1
go

-- # Eliminar tabla Autor por ID
delete Autor where Autor.Codigo = 2;

-- # Crear tabla Libro
create table Libro
(
Codigo int not null,
Titulo varchar(250),
CodigoAutor int not null,
FechaCompra datetime,
Paginas int,
AnioPublicacion int,
Descripcion varchar(500),
primary key (Codigo),
foreign key (codigoAutor) references Autor(Codigo)
)
go

-- # Eliminar tabla
-- drop table Libro

-- # Insertar registros a la tabla Libro
insert into Libro (Codigo, Titulo, CodigoAutor, FechaCompra, Paginas, AnioPublicacion, Descripcion) values (1,'Cien años de soledad',2,GETDATE(),100,1875,'Libro antiguo')
go

-- # Mostrar registros de la tabla Libro
select * from Libro
select a.Nombre as 'Nombre Autor',l.Titulo, l.Paginas from Libro as l 
join Autor as a on a.Codigo = l.CodigoAutor
go

-- # Utilidades
select GETDATE()
go
