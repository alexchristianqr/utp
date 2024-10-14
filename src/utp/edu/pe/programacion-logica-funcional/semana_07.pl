/*
    HECHOS
    Se lee pilar es progenitor de belen
*/
es_progenitor(pilar, belen).
es_progenitor(tomas, belen).
es_progenitor(tomas, lucia).
es_progenitor(pilar,lucia).
es_progenitor(belen, ana).
es_progenitor(belen, pedro).
es_progenitor(belen, juan).
es_progenitor(belen, carlos).
es_progenitor(belen, martha).
es_progenitor(belen, alicia).
es_progenitor(pedro, jose).
es_progenitor(pedro, maria).

/*  Se lee belen descansa
    Se lee pedro es niño o infante*/
descanso(belen).
infante(pedro).

/*Reglas:
    1. Belen cuida a pedro si belen esta descansando y ademas pedro es un niño infante
    2. X es madre de Y, si X es mujer y además X es progenitor de Y
*/
cuida(belen, pedro):- descanso(belen), infante(pedro).
madre(X,Y):- mujer(X), es_progenitor(X,Y).

mujer(pilar).
mujer(belen).
mujer(lucia).
mujer(ana).
mujer(maria).
hombre(tomas).
hombre(pedro).
hombre(jose).

son_hermanos(X, Y) :- es_progenitor(Z, X), es_progenitor(Z, Y), X \= Y.
es_antepasado(X,Y):-es_progenitor(X,Y).
es_antepasado(X,Y):-es_progenitor(X,Z),es_antepasado(Z,Y).

amigos(pedro,antonio).
amigos(pedro,juana).
amigos(fernando, pedro).

son_amigos(X,Y):-amigos(X,Y).
son_amigos(Y,X):-amigos(Y,X).
