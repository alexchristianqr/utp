% Definimos algunos hechos simples
%
% Ejercicio 01
%
perro(bruno).
perro(jon).
gato(tom).
gato(felix).
pajaro(lia).
pajaro(urraco).
pajaro(juan).
% Regla consulta simple
animal(X) :- gato(X).
animal(X) :- perro(X).
animal(X) :- pajaro(X).
%
% Ejercicio 02
%
hombre(juan).
hombre(mateo).
hombre(mario).
hombre(luis).
hombre(miguel).
mujer(tatiana).
mujer(rosa).
mujer(milagros).
mujer(pilar).
mujer(lucia).
% Regla consulta simple
persona(X) :- hombre(X).
persona(X) :- mujer(X).

