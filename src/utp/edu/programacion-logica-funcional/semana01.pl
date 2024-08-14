% Definimos algunos hechos simples
perro(bruno).
perro(jon).
gato(tom).
gato(felix).
pajaro(lia).
pajaro(urraco).
pajaro(juan).
% Consulta simple
animal(X) :- gato(X).
animal(X) :- perro(X).
animal(X) :- pajaro(X).
