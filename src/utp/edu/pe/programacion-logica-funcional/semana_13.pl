% rotar(L, 0, L).
% rotar([C|Q], N, P).


rotar([], []).
rotar([C|Q], R) :- 
    rotar(Q, CI), % Q=[1,2,3,4,5] C=[1] ; Q=[2,3,4,5] C=[2] ; Q=[3,4,5] C=[3] ; Q=[4,5] C=[4] ; Q=[] C=[5]
    append(CI, [C], R). % CI=[5]...CI=[1]
    
% rotar([1,2,3,4,5], P).

% --


:- dynamic persona/1.

agregar_persona(Nombre) :-
    assertz(persona(Nombre)).

eliminar_persona(Nombre) :-
    retract(persona(Nombre)).

mostrar_personas :-
    findall(Nombre, persona(Nombre), ListaPersonas),
    writeln(ListaPersonas).
