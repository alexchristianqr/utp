% Hechos aviones(codigo, aerolinea, capacidad)
aviones(1,latam,200).
aviones(2, avianca, 180).
aviones(3, aeromexico, 220).
aviones(4, copa, 150).
aviones(5, gol, 170).
aviones(6, azul, 140).
aviones(7, sky, 190).
aviones(8, aerolineas_argentinas, 210).
aviones(9, viva_air, 160).
aviones(10, interjet, 230).

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

% Hechos vuelo(cod_vuelo, tipo_vuelo, avion, piloto, pasajeros, salida, llegada, fecha, hora)
vuelo(1, nacional, 1, 1, [juan_perez, ruth_torres, alex_quispe, mario_vargas, jose_reyes, sebastian_mogollon], lima, cuzco, '10-05-24', '15:00').
vuelo(2, internacional, 2, 2, [marta_lopez, fernando_garcia, lucia_fernandez], madrid, paris, '11-05-24', '09:30').
vuelo(3, nacional, 3, 3, [carmen_diaz, eduardo_martinez, laura_castro], bogota, medellin, '12-05-24', '12:45').
vuelo(4, nacional, 4, 4, [pedro_sanabria, maria_ramos], quito, guayaquil, '13-05-24', '16:00').
vuelo(5, internacional, 5, 5, [ana_gonzalez, joseph_davis], new_york, london, '14-05-24', '22:15').
vuelo(6, nacional, 6, 6, [daniela_morales, felipe_herrera], santiago, valparaiso, '15-05-24', '08:30').
vuelo(7, internacional, 7, 7, [olga_ruiz, marcos_garcia], sao_paulo, buenos_aires, '16-05-24', '19:00').
vuelo(8, nacional, 8, 8, [mario_flores, carla_moreno], montevideo, asuncion, '17-05-24', '14:20').
vuelo(9, internacional, 9, 9, [luisa_castano, fernando_jimenez], rio_de_janeiro, miami, '18-05-24', '23:10').
vuelo(10, nacional, 10, 10, [carlos_fernandez, sofia_rodriguez], medellin, bogota, '19-05-24', '11:00').
vuelo(11, nacional, 11, 11, [jesus_morales, estela_gutierrez], san_pedro_sula, tegucigalpa, '20-05-24', '06:50').
vuelo(12, internacional, 12, 12, [beatriz_mendez, juan_valencia], los_angeles, vancouver, '21-05-24', '17:40').
vuelo(13, nacional, 13, 13, [paola_escobar, ricardo_ponce], caracas, maracaibo, '22-05-24', '21:30').
vuelo(14, internacional, 14, 14, [jose_moreno, carla_silva], madrid, rome, '23-05-24', '13:15').
vuelo(15, nacional, 15, 15, [jose_antonio, valeria_beltran], lima, arequipa, '24-05-24', '18:00').
vuelo(16, nacional, 16, 16, [maria_sanchez, eduardo_diaz], santiago, concepcion, '25-05-24', '10:05').
vuelo(17, internacional, 17, 17, [diego_hernandez, laura_rivera], new_york, san_paulo, '26-05-24', '12:25').
vuelo(18, nacional, 18, 18, [martha_perez, antonio_rios], lima, piura, '27-05-24', '14:10').
vuelo(19, internacional, 19, 19, [carmen_bermudez, manuel_zapata], berlin, zurich, '28-05-24', '16:00').
vuelo(20, nacional, 20, 20, [andres_uribe, daniela_aro], medellin, cartagena, '29-05-24', '11:30').
vuelo(21, internacional, 21, 21, [sandra_jimenez, antonio_uribe], sydney, auckland, '30-05-24', '19:45').
vuelo(22, nacional, 22, 22, [ivan_ponce, juliana_carreno], quito, cuenca, '31-05-24', '08:00').
vuelo(23, internacional, 23, 23, [veronica_moreno, alberto_perez], tokyo, beijing, '01-06-24', '23:00').
vuelo(24, nacional, 24, 24, [camila_mora, gustavo_gallegos], buenos_aires, cordoba, '02-06-24', '17:30').
vuelo(25, internacional, 25, 25, [karen_lopez, andres_garcia], miami, buenos_aires, '03-06-24', '10:10').
vuelo(26, nacional, 26, 26, [gabriel_rivera, lucia_diaz], bogota, barranquilla, '04-06-24', '15:55').
vuelo(27, internacional, 27, 27, [julio_paredes, estefania_silva], new_york, madrid, '05-06-24', '14:00').
vuelo(28, nacional, 28, 28, [alicia_jimenez, samuel_mosquera], quito, guayaquil, '06-06-24', '12:20').
vuelo(29, internacional, 29, 29, [mario_castro, isabella_torres], london, tokyo, '07-06-24', '21:10').
vuelo(30, nacional, 30, 30, [lorena_vega, miguel_cardenas], arequipa, cusco, '08-06-24', '09:30').
