% Hechos
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
