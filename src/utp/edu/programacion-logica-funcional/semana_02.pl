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
