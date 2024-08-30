% SWI-Prolog version web: 
% https://wasm.swi-prolog.org/wasm/shell

% EJERCICIO 01
% Hechos productos
% producto(id_producto, nombre, categoria, precio, cantidad_en_stock).
producto(1, televisor, electrodomesticos, 1.5, 0).
producto(2, refrigerador, electrodomesticos, 2.6, 300).
producto(3, microondas, electrodomesticos, 3.5, 150).
producto(4, computadora, computo,5.5, 150).
producto(5, telefono, computo, 9.2, 150).

venta_producto(NombreProducto, StockProducto) :-
    producto(_,NombreProducto,_,_,Stock),
    Stock >= StockProducto.
% ?- venta_producto(televisor,100).

en_stock(IdProducto) :-
    producto(IdProducto,_,_,_,Stock),
    Stock > 0.
% ?- en_stock(1).

precio_producto(NombreProducto, PrecioProducto) :-
    producto(_,NombreProducto,_,PrecioProducto,_).
% ?- precio_producto(televisor, PrecioProducto).

productos_categoria(Categoria, Productos) :-
    findall(Nombre, (producto(_,Nombre,Categoria,_,_)), Productos).
% ?- productos_categoria(electrodomesticos, Productos).

% EJERCICIO 02
% Hechos
% curso(nombre_curso, profesor, dia, hora, aula).

curso(matematica, julio, martes, '5pm', a101).
curso(fisica, oscar, miercoles, '6pm', a101).
curso(programacion, mirella, martes, '7pm', a301).
curso(quimica, luis, lunes, '8pm', a401).
curso(ciencias, javier, martes, '5pm', a501).
curso(matematica, javier, jueves, '8am', a601).
curso(programacion, javier, martes, '10am', a701).

horario_curso(NombreCurso, Dia, Hora, Aula) :-
    curso(NombreCurso,_,Dia,Hora,Aula).
% ?- horario_curso(matematica, Dia, Hora, Aula).

cursos_profesor(Docente, Cursos) :-
    findall(Nombre, (curso(Nombre,Docente,_,_,_)), Cursos).
% ?- cursos_profesor(javier, Cursos).

conflicto_horario(NombreCursoUno, NombreCursoDos) :-
    curso(NombreCursoUno,_,_,HoraUno,_),
    curso(NombreCursoDos,_,_,HoraDos,_),
    HoraUno = HoraDos.
% ?- conflicto_horario(matematica, ciencias).    

aula_disponible(Dia, Hora, Aulas) :- 
    findall(Aula, (curso(_,_,Dia,Hora,Aula)), Aulas).
% ?- aula_disponible(martes, '5pm', Aulas).
