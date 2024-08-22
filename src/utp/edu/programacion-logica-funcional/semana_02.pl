% Apuntes
% /+ :
%  findall : Lista de objetos

% Hechos 01
padre(john, mary).
padre(john, tom).
madre(jane, mary).
madre(jane, tom).

% Regla: Un niño tiene una madre y un padre
tiene_padre(Nino, Padre) :- padre(Padre, Nino).
tiene_madre(Nino, Madre) :- madre(Madre, Nino).

% Regla: Un niño tiene dos padres
tiene_padres(Nino, Padre, Madre) :-
    tiene_padre(Nino, Padre),
    tiene_madre(Nino, Madre).

% Regla: Un niño tiene un padre y una madre
tiene_padres_completos(Nino) :-
    tiene_padre(Nino, _),
    tiene_madre(Nino, _).

% ?- padre(john, mary).
% true.
% ?- madre(jane, tom).
% true.
% ?- tiene_padre(mary, john).
% true.
% ?- tiene_padres(mary, john, jane).
% true.
% ?- tiene_padres_completos(tom).
% true.
% ?- tiene_padres_completos(mary).
% true.
% ?- tiene_padres_completos(tom).
% true.
% ?- tiene_padres_completos(anne).
% false.


% Hechos 02
producto(televisor, 500).
producto(refrigerador, 300).
producto(microondas, 150).
producto(computadora, 150).
producto(telefono, 150).
producto(lapicero, 150).

descuento(televisor, 0.10).% // 10% de descuento
descuento(refrigerador, 0.15).% // 15% de descuento
descuento(microondas, 0.05).% // 5% de descuento
descuento(microondas, 0.05).
descuento(telefono, 0.05).
descuento(lapicero, 0.05).

% Regla 1: Precio con descuento
precio_con_descuento(Producto, PrecioFinal) :-
    producto(Producto, Precio),
    descuento(Producto, Descuento),
    PrecioFinal is Precio * (1 - Descuento).

% Regla 2: Es un producto caro si su precio es mayor a 250
producto_caro(Producto) :-
    producto(Producto, Precio),
    Precio > 250.

% ?- precio_con_descuento(televisor, PrecioFinal).
% PrecioFinal = 450.0.
% ?- producto_caro(refrigerador).
% true.


% Hechos 03
amigo(john, mary).
amigo(mary, alice).
amigo(alice, bob).
amigo(bob, charlie).

% Regla 1: Un amigo de un amigo es también considerado amigo indirecto
amigo_indirecto(Persona1, Persona2) :-
amigo(Persona1, Persona3),
amigo(Persona3, Persona2),
Persona1 \= Persona2.

% Regla 2: Una persona tiene una cadena de amigos si hay una conexión de al menos 3 amigos
cadena_de_amigos(Persona, Cadena) :-
amigo(Persona, Amigo1),
amigo(Amigo1, Amigo2),
amigo(Amigo2, Amigo3),
Cadena = [Persona, Amigo1, Amigo2, Amigo3].

% ?- amigo_indirecto(john, alice).
% true.
% ?- amigo_indirecto(mary, charlie).
% false.
% ?- cadena_de_amigos(john, Cadena).
% Cadena = [john, mary, alice, bob].
% ?- cadena_de_amigos(alice, Cadena).
% false.


% Hechos 04
calificacion(john, 85).
calificacion(mary, 92).
calificacion(alice, 78).
calificacion(bob, 65).
calificacion(charlie, 88).

% Regla 1: Categoría de calificación
categoria(Calificacion, 'Excelente') :- Calificacion >= 90.
categoria(Calificacion, 'Bueno') :- Calificacion >= 80, Calificacion < 90.
categoria(Calificacion, 'Suficiente') :- Calificacion >= 70, Calificacion < 80.
categoria(Calificacion, 'Insuficiente') :- Calificacion < 70.

% Regla 2: Estudiantes en una categoría específica
estudiantes_en_categoria(Categoria, Estudiantes) :-
    findall(Nombre, (calificacion(Nombre, Calificacion), categoria(Calificacion, Categoria)), Estudiantes).

% ?- categoria(85, Categoria).
% Categoria = 'Bueno'.
% ?- categoria(65, Categoria).
% Categoria = 'Insuficiente'.
% ?- estudiantes_en_categoria('Bueno', Estudiantes).
% Estudiantes = [john, alice, charlie].
% ?- estudiantes_en_categoria('Insuficiente', Estudiantes).
% Estudiantes = [bob].


% Hechos 05
habitacion(101, simple).
habitacion(102, doble).
habitacion(103, suite).
habitacion(104, suite).

% Hechos de reservas
reservado(101, '2024-08-20').
reservado(102, '2024-08-21').

% Regla 1: Verificar si una habitación está disponible en una fecha
disponible(Habitacion, Fecha) :-
    habitacion(Habitacion, _),
    \+ reservado(Habitacion, Fecha).

% Regla 2: Obtener habitaciones disponibles de un tipo específico
habitaciones_disponibles(Tipo, HabitacionesDisponibles) :-
    findall(Habitacion, (habitacion(Habitacion, Tipo), disponible(Habitacion, _)), HabitacionesDisponibles).

% ?- disponible(101, '2024-08-20').
% false.
% ?- disponible(101, '2024-08-22').
% true.
% ?- disponible(102, '2024-08-21').
% false.


% Hechos Tarea 01
autor_de(alex, libro_1).
autor_de(juan, libro_2).
autor_de(maria, libro_3).
autor_de(alex, libro_4).

libros_de_autor(Autor, Libro) :- autor_de(Autor, Libro).

% libros_de_autor(alex,libro_1).
% libros_de_autor(alex,libro_4).

calificacion(miguel, 14).
calificacion(luis, 18).
calificacion(lorena, 11).

estudiantes_con_calificacion_mayor_a(Nota) :-
    findall(Calificacion,_, Nota),
    Calificacion >= Nota.
