-- drop table Libro

use Coleccionlibros
go

-- Crear tabla Autor
create table Autor
(
Codigo int not null,
Nombre varchar(250) not null,
paisNacimiento varchar(50)
primary key (Codigo)
)
go

-- alter table Autor drop column paisNacimiento  
-- alter table Autor alter column paisNacimiento varchar(50) 
alter table Autor add PaisNacimiento varchar(50) 
go

-- Insertar tabla Autor
insert into Autor (Codigo, Nombre, PaisNacimiento) values (1,'Cesar Vallejo','Peru')
insert into Autor (Codigo, Nombre, PaisNacimiento) values (2,'Ricardo Palma','Peru')
go

-- Mostrar tabla Autor
select * from Autor
go

-- Actualizar tabla Autor por ID
update Autor set PaisNacimiento = 'Peru' where Autor.Codigo = 1
go

-- Eliminar tabla Autor por ID
delete Autor where Autor.Codigo = 2;

-- Crear tabla Libro
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

-- Insertar tabla Libro
insert into Libro (Codigo, Titulo, CodigoAutor, FechaCompra, Paginas, AnioPublicacion, Descripcion) values (1,'Cien años de soledad',2,GETDATE(),100,1875,'Libro antiguo')
go

-- Mostrar tabla Libro
select * from Libro
select l.*,a.Nombre as 'Nombre Autor' from Libro as l 
join Autor as a on a.Codigo = l.CodigoAutor
go

-- Utilidades
-- select GETDATE();

