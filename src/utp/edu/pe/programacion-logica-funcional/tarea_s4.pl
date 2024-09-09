% Hechos aviones(codigo, aerolinea, capacidad)
aviones(1, latam, 200).
aviones(2, avianca, 180).
aviones(3, aeromexico, 220).
aviones(4, copa, 150).
aviones(5, gol, 170).
aviones(6, azul, 140).
aviones(7, sky, 190).
aviones(8, aerolineas_argentinas, 210).
aviones(9, viva_air, 160).
aviones(10, interjet, 230).
aviones(11, avianca, 90).
aviones(12, sky, 120).
aviones(13, viva_air, 100).
aviones(14, latam, 350).
aviones(15, interjet, 300).

% Regla: Aviones grandes, son aquellos que tengan una capacidad >= 200 personas
aviones_grandes(Resultado) :- 
    findall(Codigo, (aviones(Codigo,_,Capacidad), Capacidad >= 200), Resultado).
% ?- aviones_grandes(Resultado).

% Regla: Aviones cuya capacidad está entre Min y Max
aviones_entre_capacidades(Min, Max, Resultado) :- 
    findall(Codigo, (aviones(Codigo, _, Capacidad), Capacidad >= Min, Capacidad =< Max), Resultado).
% ?- aviones_entre_capacidades(50,150,Resultado).

% Regla: Avión con mayor capacidad
avion_mayor_capacidad(Codigo, Aerolinea, Capacidad) :- 
    findall(Capacidad, aviones(_, _, Capacidad), ListaCapacidades),
    max_list(ListaCapacidades, MaxCapacidad),
    aviones(Codigo, Aerolinea, MaxCapacidad),
    Capacidad = MaxCapacidad.
% ?- avion_mayor_capacidad(Codigo, Aerolinea, Capacidad).

% Hechos pilotos(codigo,dni,nombre,edad,anios_experienicia)
pilotos(1, 12345678,juan_perez, 34, 5).
pilotos(2, 23456789, maria_garcia, 29, 3).
pilotos(3, 34567890, juan_lopez, 41, 15).
pilotos(4, 45678901, ana_martinez, 36, 8).
pilotos(5, 56789012, roberto_sanchez, 32, 9).
pilotos(6, 67890123, laura_reyes, 27, 4).
pilotos(7, 78901234, carlos_jimenez, 38, 9).
pilotos(8, 89012345, sofia_ortiz, 45, 18).
pilotos(9, 90123456, pedro_fernandez, 40, 15).
pilotos(10, 12345679, claudia_alvarez, 33, 6).

% Regla: Pilotos con años de experiencia >= 10
pilotos_experimentados(Resultado) :- 
     findall(Codigo, (pilotos(Codigo,_,_,_,AniosExp), AniosExp >= 10), Resultado).
% ?- pilotos_experimentados(Resultado).

% Hechos Vuelo(cod_vuelo, tipo_vuelo, avion, piloto, pasajeros, salida, llegada, fecha, hora)
vuelo(1, nacional, 1, 1, [juan_perez, ruth_torres, alex_quispe, mario_vargas, jose_reyes, sebastian_mogollon], lima, cuzco, '10-05-24', '15:00').
vuelo(2, internacional, 2, 2, [marta_lopez, fernando_garcia, lucia_fernandez, laura_rodriguez, oscar_lopez], madrid, paris, '11-05-24', '09:30').
vuelo(3, nacional, 3, 3, [carmen_diaz, eduardo_martinez, laura_castro, juan_gomez, maria_suarez], bogota, medellin, '12-05-24', '12:45').
vuelo(4, nacional, 4, 4, [pedro_sanabria, maria_ramos, carlos_perez, luisa_sanchez, victor_morales], quito, guayaquil, '13-05-24', '16:00').
vuelo(5, internacional, 5, 5, [ana_gonzalez, joseph_davis, carlos_torres, sandra_velez, mario_flores], new_york, london, '14-05-24', '22:15').
vuelo(6, nacional, 6, 6, [daniela_morales, felipe_herrera, sofia_fernandez, alberto_ramos, juan_vega], santiago, valparaiso, '15-05-24', '08:30').
vuelo(7, internacional, 7, 7, [olga_ruiz, marcos_garcia, beatriz_torres, ricardo_diaz, esteban_martinez], sao_paulo, buenos_aires, '16-05-24', '19:00').
vuelo(8, nacional, 8, 8, [mario_flores, carla_moreno, luis_gomez, andrea_rodriguez, juan_castillo], montevideo, asuncion, '17-05-24', '14:20').
vuelo(9, internacional, 9, 9, [luisa_castano,juan_perez , alicia_vega, pablo_mora, clara_torres], rio_de_janeiro, miami, '18-05-24', '23:10').
vuelo(10, nacional, 10, 10, [carlos_fernandez, sofia_rodriguez, diana_vargas, jose_rivera, manuel_diaz], medellin, bogota, '19-05-24', '11:00').
vuelo(11, nacional, 11, 1, [jesus_morales, estela_gutierrez, patricia_gonzalez, pablo_garcia, jorge_lopez], san_pedro_sula, tegucigalpa, '20-05-24', '06:50').
vuelo(12, internacional, 12, 2, [beatriz_mendez, juan_valencia, maria_arias, jose_garcia, andrea_flores], los_angeles, vancouver, '21-05-24', '17:40').
vuelo(13, nacional, 13, 3, [paola_escobar, ricardo_ponce, victor_suarez, andres_fernandez, ana_martinez], caracas, maracaibo, '22-05-24', '21:30').
vuelo(14, internacional, 14, 4, [jose_moreno, carla_silva, marcos_torres, laura_diaz, juan_fernandez], madrid, rome, '23-05-24', '13:15').
vuelo(15, nacional, 15, 5, [jose_antonio, valeria_beltran, carlos_flores, daniela_gomez, patricia_rojas], lima, arequipa, '24-05-24', '18:00').
vuelo(16, nacional, 16, 6, [maria_sanchez, eduardo_diaz, diana_moreno, felipe_martinez, mariana_lopez], santiago, concepcion, '25-05-24', '10:05').
vuelo(17, internacional, 17, 7, [diego_hernandez, laura_rivera, pablo_morales, andrea_garcia, sofia_torres], new_york, sao_paulo, '26-05-24', '12:25').
vuelo(18, nacional, 18, 8, [martha_perez, antonio_rios, carlos_vega, paula_diaz, jorge_morales], lima, piura, '27-05-24', '14:10').
vuelo(19, internacional, 19, 9, [carmen_bermudez, manuel_zapata, andrea_moreno, jose_perez, sandra_suarez], berlin, zurich, '28-05-24', '16:00').
vuelo(20, nacional, 20, 10, [andres_uribe, daniela_aro, juan_suarez, ana_martinez, carlos_torres], medellin, cartagena, '29-05-24', '11:30').
vuelo(21, internacional, 1, 1, [sandra_jimenez, antonio_uribe, pedro_gomez, luisa_rojas, carlos_perez], sydney, auckland, '30-05-24', '19:45').
vuelo(22, nacional, 2, 2, [ivan_ponce, juliana_carreno, maria_torres, juan_lopez, patricia_moreno], quito, cuenca, '31-05-24', '08:00').
vuelo(23, internacional, 3, 3, [veronica_moreno, alberto_perez, sofia_diaz, jose_ramos, maria_gonzalez], tokyo, beijing, '01-06-24', '23:00').
vuelo(24, nacional, 4, 4, [camila_mora, gustavo_gallegos, juan_velez, paola_ramos, victor_torres], buenos_aires, cordoba, '02-06-24', '17:30').
vuelo(25, internacional, 5, 5, [karen_lopez, andres_garcia, mario_rodriguez, juan_felipe, luisa_perez], miami, buenos_aires, '03-06-24', '10:10').
vuelo(26, nacional, 6, 6, [gabriel_rivera, lucia_diaz, sofia_garcia, carlos_fernandez, andrea_perez], bogota, barranquilla, '04-06-24', '15:55').
vuelo(27, internacional, 7, 7, [julio_paredes, estefania_silva, juan_lopez, maria_torres, jose_garcia], new_york, madrid, '05-06-24', '14:00').
vuelo(28, nacional, 8, 8, [alicia_jimenez, samuel_mosquera, luisa_flores, mario_perez, jorge_martinez], quito, guayaquil, '06-06-24', '12:20').
vuelo(29, internacional, 9, 9, [mario_castro, isabella_torres, pablo_gomez, carla_moreno, jose_ramirez], london, tokyo, '07-06-24', '21:10').
vuelo(30, nacional, 10, 10, [lorena_vega, miguel_cardenas, juan_diaz, sofia_morales, eduardo_lopez], arequipa, cusco, '08-06-24', '09:30').
vuelo(31, internacional, 11, 1, [sandra_jimenez, antonio_uribe, pedro_gomez, luisa_rojas, carlos_perez], sydney, auckland, '09-06-24', '19:45').
vuelo(32, nacional, 12, 2, [ivan_ponce, juliana_carreno, maria_torres, juan_lopez, patricia_moreno], quito, cuenca, '10-06-24', '08:00').
vuelo(33, internacional, 13, 3, [veronica_moreno, alberto_perez, sofia_diaz, jose_ramos, maria_gonzalez], tokyo, beijing, '11-06-24', '23:00').
vuelo(34, nacional, 14, 4, [camila_mora, gustavo_gallegos, juan_velez, paola_ramos, victor_torres], buenos_aires, cordoba, '12-06-24', '17:30').
vuelo(35, internacional, 15, 5, [karen_lopez, andres_garcia, mario_rodriguez, juan_felipe, luisa_perez], miami, buenos_aires, '13-06-24', '10:10').
vuelo(36, nacional, 16, 6, [gabriel_rivera, lucia_diaz, sofia_garcia, carlos_fernandez, andrea_perez], bogota, barranquilla, '14-06-24', '15:55').
vuelo(37, internacional, 17, 7, [julio_paredes, estefania_silva, juan_lopez, maria_torres, jose_garcia], new_york, madrid, '15-06-24', '14:00').
vuelo(38, nacional, 18, 8, [alicia_jimenez, samuel_mosquera, luisa_flores, mario_perez, jorge_martinez], quito, guayaquil, '16-06-24', '12:20').
vuelo(39, internacional, 19, 9, [mario_castro, isabella_torres, pablo_gomez, carla_moreno, jose_ramirez], london, tokyo, '17-06-24', '21:10').
vuelo(40, nacional, 20, 10, [lorena_vega, miguel_cardenas, juan_diaz, juan_perez ,eduardo_lopez], arequipa, cusco, '18-06-24', '09:30').
vuelo(41, internacional, 1, 1, [alexa_morales, hugo_perez, daniela_mendez, carlos_vega, laura_moreno], sydney, auckland, '19-06-24', '19:45').
vuelo(42, nacional, 2, 2, [gabriel_rios, mariana_torres, juan_perez, paola_sanchez, sofia_garcia], quito, cuenca, '20-06-24', '08:00').
vuelo(43, internacional, 3, 3, [veronica_ramirez, alberto_fernandez, sofia_velez, jose_garcia, maria_sanchez], tokyo, beijing, '21-06-24', '23:00').
vuelo(44, nacional, 4, 4, [camila_rivera, gustavo_mora, juan_velez, paola_castano, victor_morales], buenos_aires, cordoba, '22-06-24', '17:30').
vuelo(45, internacional, 5, 5, [karen_paredes, andres_moreno, mario_rojas, juan_fernandez, luisa_torres], miami, buenos_aires, '23-06-24', '10:10').
vuelo(46, nacional, 6, 6, [gabriel_lopez, lucia_perez, sofia_mora, juan_perez, andrea_vega], bogota, barranquilla, '24-06-24', '15:55').
vuelo(47, internacional, 7, 7, [julio_silva, estefania_torres, juan_diaz, maria_gonzalez, jose_ramirez], new_york, madrid, '25-06-24', '14:00').
vuelo(48, nacional, 8, 8, [alicia_garcia, samuel_perez, luisa_morales, mario_vega, jorge_rodriguez], quito, guayaquil, '26-06-24', '12:20').
vuelo(49, internacional, 9, 9, [mario_torres, isabella_flores, pablo_sanchez, carla_velez, jose_moreno], london, tokyo, '27-06-24', '21:10').
vuelo(50, nacional, 10, 10, [lorena_sanchez, miguel_vega, juan_castano, sofia_rih, eduardo_suarez], arequipa, cusco, '28-06-24', '09:30').

% Regla: vuelos por tipo
vuelos_por_tipo(Tipo, Resultado) :-
    findall(ID, vuelo(ID, Tipo, _, _, _, _, _, _, _), Resultado).
% ?- vuelos_por_tipo(internacional, Resultado), write(Resultado).

% Regla: obtener los vuelos en los que ha estado un pasajero
vuelos_por_pasajero(NombrePasajero, Resultado) :-
    findall(ID, (vuelo(ID, _, _, _, Pasajeros, _, _, _, _), member(NombrePasajero, Pasajeros)), Resultado).
% ?- vuelos_por_pasajero(jose_reyes, Resultado).

% Regla: obtener el mejor viajero (más frecuente)
contar_pasajero(Pasajero, Cantidad) :-
    findall(Pasajero, (vuelo(_, _, _, _, Pasajeros, _, _, _, _), member(Pasajero, Pasajeros)), Lista),
    length(Lista, Cantidad).

mejor_viajero(Pasajero, Cantidad) :-
    findall(Cantidad-Pasajero, (vuelo(_, _, _, _, Pasajeros, _, _, _, _), member(Pasajero, Pasajeros), contar_pasajero(Pasajero, Cantidad)), Lista),
    sort(0, @>=, Lista, [MaxCantidad-Pasajero|_]),
    Cantidad = MaxCantidad,
    format('El viajero más frecuente es: ~w, con ~d apariciones.~n', [Pasajero, MaxCantidad]).
% mejor_viajero(Pasajero, Cantidad).



%Regla para saber los vuelos asignados a una fecha en particular
vuelos_por_fecha(Fecha, Resultado) :-
    findall(ID, vuelo(ID, _, _, _, _, _, _, Fecha, _), Resultado).
% ?- vuelos_por_fecha('10-05-24', Resultado).

%Regla para saber el nombre de un piloto brindando el codigo de vuelo
nombre_piloto_por_vuelo(CodVuelo, NombrePiloto) :-
    vuelo(CodVuelo, _, _, CodPiloto, _, _, _, _, _),
    pilotos(CodPiloto, _, NombrePiloto, _, _).
%?- nombre_piloto_por_vuelo(8, NombrePiloto).

% Regla para saber fecha y hora de un vuelo brindando las ciudades
info_vuelo(CiudadSalida, CiudadLlegada, CodVuelo, Fecha, Hora) :-
    vuelo(CodVuelo, _, _, _, _, CiudadSalida, CiudadLlegada, Fecha, Hora).
%?- info_vuelo(lima,cuzco,CodVuelo,Fecha,Hora).




