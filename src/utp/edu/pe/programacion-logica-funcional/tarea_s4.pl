% Hechos las carreras y sus características
lista_carreras(arquitecto, [numeros, letras, creativo]).
lista_carreras(medico, [numeros, fisico, letras, creativo, ciencia, fisica, quimica]).
lista_carreras(contador, [numeros,logico,a,finanzas]).
lista_carreras(programador, [numeros, letras, logico]).
% lista_carreras(Resultado).

% Regla para encontrar las carreras que contienen una lista de características específicas
test_vocacional(ListaCaracteristicas, Carreras) :-
    findall(Carrera,(
            lista_carreras(Carrera, ListaRequisitos),
            contiene_caracteristicas(ListaCaracteristicas, ListaRequisitos)
            ),Carreras).

% Sub-regla para validar si una lista contiene todas las características
contiene_caracteristicas([], _).
contiene_caracteristicas([Caracteristica | Resto], Lista) :-
    member(Caracteristica, Lista),
    contiene_caracteristicas(Resto, Lista).

% Ejemplo 01 Correcto
% ?- test_vocacional([logico], Resultado).
% Resultado = [programador].

% Ejemplo 02 Error
% ?- test_vocacional([logico,numeros], Resultado).
% Resultado = [programador].

% Ejemplo 03 Correcto
% ?- test_vocacional([numeros,letras], Resultado).
% Resultado = [arquitecto, medico, programador].
