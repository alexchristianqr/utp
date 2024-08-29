% EJERCICIO 01
% Hechos productos
% producto(id_producto, nombre, categoria, precio, cantidad_en_stock).
producto(1, televisor, electrodomesticos, 1.5, 500).
producto(2, refrigerador, electrodomesticos, 2.6, 300).
producto(3, microondas, electrodomesticos, 3.5, 150).
producto(4, computadora, computo,5.5, 150).
producto(5, telefono, computo, 9.2, 150).

venta_producto(NombreProducto, StockProducto) :-
    producto(_,NombreProducto,_,_,Stock),
    Stock >= StockProducto.

en_stock(IdProducto) :-
    producto(IdProducto,_,_,_,Stock),
    Stock > 0.

precio_producto(NombreProducto, PrecioProducto) :-
    producto(_,NombreProducto,_,PrecioProducto,_).

productos_categoria(Categoria, Productos) :-
    findall(Nombre, (producto(_,Nombre,Categoria,_,_)), Productos).


% EJERCICIO 02
% Hechos
% curso(nombre_curso, profesor, dia, hora, aula).

curso(matematica, julio, martes, '5pm', a101).
curso(fisica, oscar, miercoles, '6pm', a201).
curso(programacion, mirella, martes, '7pm', a301).
curso(quimica, luis, lunes, '8pm', a401).
curso(ciencias, javier, jueves, '9pm', a501).

horario_curso(NombreCurso, Dia, Hora) :-
    curso(NombreCurso,Dia,Hora,_).


