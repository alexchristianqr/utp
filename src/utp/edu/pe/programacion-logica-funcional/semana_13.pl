% -- EJERCICIO 01

:- dynamic persona/1.

agregar_persona(Nombre) :-
    assertz(persona(Nombre)).
% ?- agregar_persona('Alex').

eliminar_persona(Nombre) :-
    retract(persona(Nombre)).
% ?- eliminar_persona('Alex').

mostrar_personas :-
    findall(Nombre, persona(Nombre), ListaPersonas),
    writeln(ListaPersonas).
% ?- mostrar_personas().

% -- EJERCICIO 02

:- dynamic producto/2.

agregar_producto(Nombre, Cantidad):-
    assertz(producto(Nombre, Cantidad)).
% ?- agregar_producto('Manzana',100).

eliminar_producto(Nombre):-
    retract(producto(Nombre, _)).
% ?- eliminar_producto('Manzana').

mostrar_productos :-
    producto(Nombre, Cantidad),
    format("Producto: ~w, Cantidad: ~w~n", [Nombre, Cantidad]),
    fail.
% ?- mostrar_productos().
