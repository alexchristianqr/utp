/Regla para calcular elcuadrado de un numero/
cuadrado(X,Rpta):-Rpta is X*X.

/Regla para calcular el prom numero/
promedio(X,Y,Rpta):-Rpta is (X+Y)/2.


/Regla para conocer el max de 2 num/
max(X,Y,X):- X>=Y.
max(X,Y,Y):-Y>=X.



/Hecho/
tiene(maria,libro).
tiene(juan,libreta).
tiene(juan,libro).
le_gusta(juan,maria).
le_gusta(maria,juan).
le_gusta(laura,luis).

/Desarrollo de ejercicio/
operaciones(X, +, Y, Rpta):-Rpta is X + Y.
operaciones(X, -, Y, Rpta):-Rpta is X - Y.
operaciones(X, *, Y, Rpta):-Rpta is X * Y.
operaciones(X, /, Y, Rpta):-Y =\=0, Rpta is X / Y.

/*Desarrollar las reglas necesarias para verificar si un elemento
 * pertenece auna lista*/
miembro(X, [X|_]).
miembro(X,[_|Y]):-miembro(X,Y).

hombre(jose).
hombre(tomas).
hombres(pedro).
hombres([]).
hombres([X|Xs]):-hombre(X), hombres(Xs).

noPertenece(X,[]).
noPertenece(X,[Y|Ys]):-X\=Y, noPertenece(X,Ys).
