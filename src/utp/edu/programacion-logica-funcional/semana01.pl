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
% Hechos de relaciones familiares
es_padre(juan, maria).
es_padre(carlos, pedro).
es_padre(miguel, laura).
es_madre(ana, luis).
es_madre(ana, tatiana).
es_madre(ana, carlos).
% Regla
papitos(X,Y) :-  es_padre(X,Y).
papitos(X,Y) :-  es_madre(X,Y).
% Hechos de Peliculas
es_pelicula(harry_potter, accion).
es_pelicula(rambo, accion).
es_pelicula(cenicienta, animado).
es_cancion(una_cancion_de_amor, romantica).
es_cancion(muelle_de_sanblas, rock).
es_cancion(gata_fiera, perreo).
% Regla
entretenimiento(X,Y) :- es_pelicula(X,Y).
entretenimiento(X,Y) :- es_cancion(X,Y).
