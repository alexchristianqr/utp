% // Ejercicio #11.


% Definición del predicado dinámico proyecto/3
:- dynamic proyecto/3.

% a. Ejemplo de base de datos inicial
% proyecto(NombreProyecto, Cliente, Estado).
proyecto(sistema_gestion, acme_corp, activo).
proyecto(app_movil, tech_solutions, finalizado).
proyecto(erp_integrado, global_soft, activo).

% b. Agregar un proyecto
agregar_proyecto(NombreProyecto, Cliente, Estado) :-
    assertz(proyecto(NombreProyecto, Cliente, Estado)).

% Eliminar un proyecto por su nombre
eliminar_proyecto(NombreProyecto) :-
    retract(proyecto(NombreProyecto, _, _)).

% Consultar proyectos activos
proyectos_activos(ListaProyectos) :-
    findall(NombreProyecto, proyecto(NombreProyecto, _, activo), ListaProyectos).

% ?- agregar_proyecto(web_ecommerce, tienda_online, activo).
% ?- eliminar_proyecto(app_movil).
% ?- proyectos_activos(ListaProyectos).


% // Ejercicio #12.


% Definición del predicado dinámico alquiler/4
:- dynamic alquiler/4.

% a. Ejemplo de base de datos inicial
% alquiler(ID, Cliente, Articulo, Fecha).
alquiler(1, juan, bicicleta, '2024-11-01').
alquiler(2, maria, kayak, '2024-11-10').
alquiler(3, pedro, patineta, '2024-11-15').

% b. Agregar un alquiler
agregar_alquiler(ID, Cliente, Articulo, Fecha) :-
    \+ alquiler(ID, _, _, _), % Verifica que no exista el ID
    assertz(alquiler(ID, Cliente, Articulo, Fecha)).

% Eliminar un alquiler por su ID
eliminar_alquiler(ID) :-
    retract(alquiler(ID, _, _, _)).

% Buscar alquileres por nombre del cliente
buscar_alquileres_por_cliente(Cliente, ListaAlquileres) :-
    findall((ID, Articulo, Fecha), alquiler(ID, Cliente, Articulo, Fecha), ListaAlquileres).

% ?- agregar_alquiler(4, ana, monopatin, '2024-12-01').
% ?- eliminar_alquiler(2).
% ?- buscar_alquileres_por_cliente(juan, ListaAlquileres).


% // Ejercicio #13.


% Definición del predicado dinámico nota/3
:- dynamic nota/3.

% a. Base de datos inicial de ejemplo
% nota(Estudiante, Materia, Calificacion).
nota(ana, matematicas, 85).
nota(ana, historia, 90).
nota(juan, matematicas, 78).
nota(juan, fisica, 88).
nota(maria, historia, 92).

% b. Agregar una nueva nota
agregar_nota(Estudiante, Materia, Calificacion) :-
    assertz(nota(Estudiante, Materia, Calificacion)).

% Eliminar una nota
eliminar_nota(Estudiante, Materia) :-
    retract(nota(Estudiante, Materia, _)).

% Calcular el promedio de calificaciones de un estudiante
promedio_estudiante(Estudiante, Promedio) :-
    findall(Calificacion, nota(Estudiante, _, Calificacion), Calificaciones),
    sumlist(Calificaciones, Suma),
    length(Calificaciones, Total),
    Total > 0,  % Asegurarse de que hay al menos una calificación
    Promedio is Suma / Total.


% ?- agregar_nota(pedro, quimica, 80).
% ?- agregar_nota(luis, ingles, 95).
% ?- eliminar_nota(juan, matematicas).
% ?- eliminar_nota(maria, historia).
% ?- promedio_estudiante(ana, Promedio).
% ?- promedio_estudiante(juan, Promedio).
% ?- promedio_estudiante(luis, Promedio).
% ?- promedio_estudiante(pedro, Promedio).


% // Ejercicio #14.


% Definición del predicado dinámico pedido/3
:- dynamic pedido/3.

% Base de datos inicial de ejemplo
% pedido(ID, Cliente, Estado).
pedido(1, ana, pendiente).
pedido(2, juan, enviado).
pedido(3, maria, entregado).
pedido(4, ana, enviado).

% Actualizar el estado de un pedido
actualizar_estado(ID, NuevoEstado) :-
    retract(pedido(ID, Cliente, _)),
    assertz(pedido(ID, Cliente, NuevoEstado)).

% Consultar pedidos por cliente
pedidos_por_cliente(Cliente, Pedidos) :-
    findall(ID, pedido(ID, Cliente, _), Pedidos).

% ?- actualizar_estado(1, enviado).
% ?- actualizar_estado(3, pendiente).
% ?- pedidos_por_cliente(ana, Pedidos).
% ?- pedidos_por_cliente(juan, Pedidos).
% ?- pedidos_por_cliente(maria, Pedidos).


% // Ejercicio #15.


% Definición del predicado dinámico inventario/4
:- dynamic inventario/4.

% Base de datos inicial de ejemplo
% inventario(ID, Nombre, Categoria, Cantidad).
inventario(1, manzana, frutas, 50).
inventario(2, naranja, frutas, 30).
inventario(3, arroz, granos, 100).
inventario(4, lentejas, granos, 75).
inventario(5, detergente, limpieza, 20).

% Calcular el stock total por categoría
stock_total_por_categoria(Categoria, TotalStock) :-
    findall(Cantidad, inventario(_, _, Categoria, Cantidad), Cantidades),
    sumlist(Cantidades, TotalStock).

% ?- stock_total_por_categoria(frutas, TotalStock).
% ?- stock_total_por_categoria(granos, TotalStock).
% ?- stock_total_por_categoria(limpieza, TotalStock).


% // Ejercicio #16.


% Definición del predicado dinámico ruta/4
:- dynamic ruta/4.

% Base de datos inicial de ejemplo
% ruta(Origen, Destino, Distancia, CostoPorKilometro).
ruta(lima, cusco, 1100, 0.5).
ruta(cusco, puno, 400, 0.4).
ruta(puno, arequipa, 600, 0.6).
ruta(lima, arequipa, 1000, 0.5).

% Agregar una nueva ruta
agregar_ruta(Origen, Destino, Distancia, CostoPorKilometro) :-
    assertz(ruta(Origen, Destino, Distancia, CostoPorKilometro)).

% Eliminar una ruta
eliminar_ruta(Origen, Destino) :-
    retract(ruta(Origen, Destino, _, _)).

% Modificar una ruta
modificar_ruta(Origen, Destino, NuevaDistancia, NuevoCostoPorKilometro) :-
    retract(ruta(Origen, Destino, _, _)),
    assertz(ruta(Origen, Destino, NuevaDistancia, NuevoCostoPorKilometro)).

% Calcular el costo total de un viaje directo entre dos ciudades
costo_viaje_directo(Origen, Destino, CostoTotal) :-
    ruta(Origen, Destino, Distancia, CostoPorKilometro),
    CostoTotal is Distancia * CostoPorKilometro.

% Identificar todas las rutas disponibles desde un origen
rutas_desde_origen(Origen, Rutas) :-
    findall((Destino, Distancia, CostoPorKilometro), ruta(Origen, Destino, Distancia, CostoPorKilometro), Rutas).

% Manejar rutas indirectas
costo_viaje_indirecto(Origen, Destino, CostoTotal) :-
    ruta(Origen, Intermedio, Distancia1, Costo1),
    ruta(Intermedio, Destino, Distancia2, Costo2),
    CostoTotal is (Distancia1 * Costo1) + (Distancia2 * Costo2).

% ?- agregar_ruta(trujillo, chiclayo, 200, 0.3).
% ?- agregar_ruta(chiclayo, piura, 300, 0.4).
% ?- eliminar_ruta(lima, cusco).
% ?- eliminar_ruta(cusco, puno).
% ?- modificar_ruta(puno, arequipa, 650, 0.55).
% ?- modificar_ruta(lima, arequipa, 1050, 0.6).
% ?- costo_viaje_directo(lima, arequipa, CostoTotal).
% ?- costo_viaje_directo(cusco, puno, CostoTotal).
% ?- rutas_desde_origen(lima, Rutas).
% ?- rutas_desde_origen(cusco, Rutas).
% ?- costo_viaje_indirecto(lima, puno, CostoTotal).
% ?- costo_viaje_indirecto(lima, arequipa, CostoTotal).


% // Ejercicio #17.


% Definición de los predicados dinámicos
:- dynamic persona/3.
:- dynamic padre/2.
:- dynamic madre/2.

% Base de datos inicial de ejemplo
% persona(Nombre, Genero, FechaDeNacimiento).
persona(juan, hombre, '1980-05-10').
persona(maria, mujer, '1982-07-15').
persona(carlos, hombre, '2005-03-21').
persona(laura, mujer, '2007-09-12').

% padre(Padre, Hijo).
padre(juan, carlos).
padre(juan, laura).

% madre(Madre, Hijo).
madre(maria, carlos).
madre(maria, laura).

% a. Agregar personas
agregar_persona(Nombre, Genero, FechaDeNacimiento) :-
    assertz(persona(Nombre, Genero, FechaDeNacimiento)).

% Registrar relaciones parentales
registrar_padre(Padre, Hijo) :-
    assertz(padre(Padre, Hijo)).

registrar_madre(Madre, Hijo) :-
    assertz(madre(Madre, Hijo)).

% b. Determinar si dos personas son hermanos
hermanos(Persona1, Persona2) :-
    padre(Padre, Persona1), padre(Padre, Persona2),
    madre(Madre, Persona1), madre(Madre, Persona2),
    Persona1 \= Persona2.

% Construir un árbol genealógico dinámicamente
arbol_genealogico(Persona, Arbol) :-
    findall(Padre, padre(Padre, Persona), Padres),
    findall(Madre, madre(Madre, Persona), Madres),
    findall(Hermano, hermanos(Persona, Hermano), Hermanos),
    Arbol = [padres(Padres), madres(Madres), hermanos(Hermanos)].


% ?- agregar_persona(pedro, hombre, '2010-11-05').
% ?- agregar_persona(sara, mujer, '2012-08-17').
% ?- registrar_padre(juan, pedro).
% ?- registrar_madre(maria, sara).
% ?- hermanos(carlos, laura).
% ?- hermanos(pedro, sara).
% ?- arbol_genealogico(carlos, Arbol).
% ?- arbol_genealogico(sara, Arbol).


% // Ejercicio #18.

% Definición del predicado dinámico animal/3
:- dynamic animal/3.

% Base de datos inicial de ejemplo
% animal(Nombre, Especie, Estado).
animal(leon1, leon, vivo).
animal(cebra1, cebra, vivo).
animal(cebra2, cebra, vivo).
animal(antilope1, antilope, vivo).
animal(tigre1, tigre, vivo).

% a. Depredación entre especies
depredar(Depredador, Presa) :-
    animal(Depredador, EspecieDepredador, vivo),
    animal(Presa, EspeciePresa, vivo),
    es_depredador(EspecieDepredador, EspeciePresa),
    retract(animal(Presa, EspeciePresa, vivo)),
    assertz(animal(Presa, EspeciePresa, muerto)).

% Relación de depredadores y presas
es_depredador(leon, cebra).
es_depredador(tigre, antilope).

% b. Nacimiento de nuevos animales
nacer(Nombre, Especie) :-
    assertz(animal(Nombre, Especie, vivo)).

% c. Extinción de una especie
extinguir_especie(Especie) :-
    retractall(animal(_, Especie, _)).

% d. Verificar si el ecosistema está en equilibrio
ecosistema_en_equilibrio :-
    findall(Especie, animal(_, Especie, vivo), EspeciesVivas),
    sort(EspeciesVivas, EspeciesUnicas),
    forall(member(Especie, EspeciesUnicas),
           animal(_, Especie, vivo)).

% ?- depredar(leon1, cebra1).
% ?- depredar(tigre1, antilope1).
% ?- nacer(cebra3, cebra).
% ?- nacer(leon2, leon).
% ?- extinguir_especie(cebra).
% ?- extinguir_especie(tigre).
% ?- ecosistema_en_equilibrio.


% // Ejercicio #19.

% Definición de los predicados dinámicos
:- dynamic usuario/2.
:- dynamic pelicula/2.
:- dynamic visualizacion/2.

% a. Base de datos inicial de ejemplo
% usuario(Nombre, ListaDeGenerosFavoritos).
usuario(juan, [accion, comedia, aventura]).
usuario(maria, [drama, comedia, thriller]).
usuario(carlos, [ciencia_ficcion, aventura, accion]).

% pelicula(Titulo, Genero).
pelicula(titanic, drama).
pelicula(avengers, accion).
pelicula(jumanji, comedia).
pelicula(star_wars, ciencia_ficcion).
pelicula(terminator, accion).

% b. Registrar usuarios
registrar_usuario(Nombre, GenerosFavoritos) :-
    assertz(usuario(Nombre, GenerosFavoritos)).

% Registrar películas
registrar_pelicula(Titulo, Genero) :-
    assertz(pelicula(Titulo, Genero)).

% Registrar visualización de una película
registrar_visualizacion(Nombre, Titulo) :-
    assertz(visualizacion(Nombre, Titulo)).

% Recomendar películas a un usuario según sus géneros favoritos, excluyendo las ya vistas
recomendar_peliculas(Nombre, PeliculasRecomendadas) :-
    usuario(Nombre, GenerosFavoritos),
    findall(Titulo, (pelicula(Titulo, Genero), member(Genero, GenerosFavoritos), 
                     \+ visualizacion(Nombre, Titulo)), PeliculasRecomendadas).

% ?- registrar_usuario(ana, [romance, comedia, accion]).
% ?- registrar_pelicula(inception, ciencia_ficcion).
% ?- registrar_pelicula(frozen, animacion).
% ?- registrar_visualizacion(juan, avengers).
% ?- registrar_visualizacion(maria, titanic).
% ?- recomendar_peliculas(juan, PeliculasRecomendadas).
% ?- recomendar_peliculas(maria, PeliculasRecomendadas).


% // Ejercicio #20.

% Definición del predicado dinámico activo/4
:- dynamic activo/4.
:- dynamic operacion/4.

% a. Base de datos inicial de ejemplo
% activo(Nombre, Tipo, Precio, Volumen)
activo(apple, accion, 150, 10000).
activo(bono_europeo, bono, 120, 5000).
activo(bitcoin, criptomoneda, 30000, 20000).

% b. Reglas para comprar y vender activos

% Comprar un activo (actualiza el volumen y el precio según oferta y demanda)
comprar(Activo, Cantidad, PrecioCompra) :-
    activo(Activo, Tipo, Precio, Volumen),
    PrecioCompra =< Precio,
    NuevoVolumen is Volumen - Cantidad,
    NuevoPrecio is Precio * (1 - 0.01 * Cantidad),  % Simulación de precio ajustado por oferta
    retract(activo(Activo, Tipo, Precio, Volumen)),
    assertz(activo(Activo, Tipo, NuevoPrecio, NuevoVolumen)),
    assertz(operacion(Activo, compra, PrecioCompra, Cantidad)).

% Vender un activo (actualiza el volumen y el precio según oferta y demanda)
vender(Activo, Cantidad, PrecioVenta) :-
    activo(Activo, Tipo, Precio, Volumen),
    PrecioVenta >= Precio,
    NuevoVolumen is Volumen + Cantidad,
    NuevoPrecio is Precio * (1 + 0.01 * Cantidad),  % Simulación de precio ajustado por demanda
    retract(activo(Activo, Tipo, Precio, Volumen)),
    assertz(activo(Activo, Tipo, NuevoPrecio, NuevoVolumen)),
    assertz(operacion(Activo, venta, PrecioVenta, Cantidad)).

% c. Calcular la ganancia o pérdida de un inversor
ganancia_o_perdida(Activo, GananciaPerdida) :-
    findall(Cantidad, operacion(Activo, compra, _, Cantidad), Compras),
    findall(Cantidad, operacion(Activo, venta, _, Cantidad), Ventas),
    sumlist(Compras, TotalCompras),
    sumlist(Ventas, TotalVentas),
    activo(Activo, _, PrecioActual, _),
    GananciaPerdida is (TotalVentas - TotalCompras) * PrecioActual.

% d. Consultar los activos más negociados (con mayor volumen negociado)
activos_mas_negociados(ListaActivos) :-
    findall([Activo, Volumen], activo(Activo, _, _, Volumen), Activos),
    sort(2, @>=, Activos, ActivosOrdenados),
    findall(Activo, member([Activo, _], ActivosOrdenados), ListaActivos).

% ?- comprar(apple, 100, 145).
% ?- vender(apple, 50, 155).
% ?- comprar(bitcoin, 10, 29000).
% ?- vender(bono_europeo, 100, 125).
% ?- ganancia_o_perdida(apple, GananciaPerdida).
% ?- ganancia_o_perdida(bitcoin, GananciaPerdida).
% ?- activos_mas_negociados(ListaActivos).

