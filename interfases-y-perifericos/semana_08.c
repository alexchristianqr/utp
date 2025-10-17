// === Definición de Constantes (Etiquetas) ===
// Le damos nombres descriptivos a los pines del microcontrolador para que el código sea fácil de entender.
#define LED1    2       // El LED número 1 está conectado al pin digital 2.
#define LED2    13      // El LED número 2 está conectado al pin digital 13.
#define BOTON1  4       // El Botón 1 está en el pin 4. Este pin puede generar la interrupción INT0.
#define BOTON2  12      // El Botón 2 está en el pin 12. Este pin puede generar la interrupción INT1.

// === Variables Globales ===
// Estas variables deben ser accesibles desde cualquier parte del código (loop, interrupciones, etc.).

// La palabra "volatile" es MUY IMPORTANTE. Le dice al compilador que esta variable puede cambiar
// de forma inesperada (por una interrupción). Esto evita que el compilador "optimice" el código
// de forma incorrecta y cause errores.
volatile bool estadoLED1 = false;             // Guarda si el LED1 debe estar encendido (true) o apagado (false).
volatile bool estadoLED2 = false;             // Guarda el estado del LED2.
volatile unsigned long ultimaInterrupcion1 = 0; // Para el anti-rebote del botón 1.
volatile unsigned long ultimaInterrupcion2 = 0; // Para el anti-rebote del botón 2.
volatile int contadorBoton2 = 0;              // Contador de pulsaciones del botón 2.

// Variables para controlar el tiempo sin usar "delay()", lo que permite que el programa siga funcionando sin pausas.
unsigned long tiempoEncendidoLED1 = 0;  // Almacena cuándo se encendió el LED1.
unsigned long tiempoParpadeo = 0;       // Ayuda a controlar el parpadeo del LED1.
unsigned long ultimaImpresion = 0;      // Controla la frecuencia de los mensajes por el puerto serial.

// === Función de Configuración ===
// Esta función se ejecuta UNA SOLA VEZ cuando el microcontrolador se enciende o se resetea.
void setup() {
  // Configurar los pines de los LEDs como SALIDAS (OUTPUT).
  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);
  
  // Configurar los pines de los botones como ENTRADAS (INPUT).
  // INPUT_PULLUP activa una resistencia interna que mantiene el pin en estado ALTO (HIGH)
  // por defecto. El botón debe conectarse a tierra (GND) para que al presionarlo el pin pase a BAJO (LOW).
  pinMode(BOTON1, INPUT_PULLUP);
  pinMode(BOTON2, INPUT_PULLUP);

  // Inicia la comunicación serial a 9600 baudios para enviar mensajes a la computadora.
  Serial.begin(9600);

  // === Configuración de las Interrupciones ===
  // Aquí le decimos al microcontrolador: "Cuando ocurra un evento en este pin, detén todo y ejecuta esta función".
  // digitalPinToInterrupt(BOTON1): Convierte el número de pin a su interrupción correspondiente.
  // cambiarLED1: Es la función que se ejecutará.
  // FALLING: Es el "gatillo". La interrupción se activa cuando el voltaje del pin cambia de ALTO a BAJO (cuando se presiona el botón).
  attachInterrupt(digitalPinToInterrupt(BOTON1), cambiarLED1, FALLING);
  attachInterrupt(digitalPinToInterrupt(BOTON2), cambiarLED2, FALLING);
}

// === Bucle Principal ===
// Esta función se ejecuta en un ciclo infinito después de que 'setup()' termina.
void loop() {
  // --- Lógica de parpadeo para el LED1 ---
  // Primero, verifica si el LED1 está supuesto a estar encendido.
  if (estadoLED1) {
    // Luego, verifica si han pasado más de 5 segundos (5000 milisegundos) desde que se encendió.
    if (millis() - tiempoEncendidoLED1 > 5000) {
      // Si han pasado 5s, ahora lo hacemos parpadear cada medio segundo (500 ms).
      if (millis() - tiempoParpadeo > 500) {
        digitalWrite(LED1, !digitalRead(LED1)); // Invierte el estado actual del pin del LED (si está ON, lo pone OFF y viceversa).
        tiempoParpadeo = millis();              // Actualiza el tiempo del último parpadeo.
      }
    }
  }

  // --- Lógica para enviar información por el puerto serial ---
  // Verifica si han pasado más de 2 segundos (2000 ms) desde la última vez que se imprimió algo.
  if (millis() - ultimaImpresion > 2000) {
    Serial.print("ESTADO LED1: ");
    Serial.print(estadoLED1 ? "ENCENDIDO" : "APAGADO"); // Operador ternario: una forma corta de un if/else.
    Serial.print(" | ESTADO LED2: ");
    Serial.print(estadoLED2 ? "ENCENDIDO" : "APAGADO");
    Serial.print(" | CONTADOR BOTON2: ");
    Serial.println(contadorBoton2);
    ultimaImpresion = millis(); // Actualiza el tiempo de la última impresión.
  }
}

// === Rutinas de Servicio de Interrupción (ISR) ===
// Estas son las funciones especiales que se ejecutan cuando se activa una interrupción.
// Deben ser lo más cortas y rápidas posible.

// --- ISR para el Botón 1 ---
void cambiarLED1() {
  unsigned long tiempoActual = millis(); // Obtiene el tiempo actual.
  // Mecanismo "Anti-Rebote" (Debounce):
  // Ignora la interrupción si ha pasado muy poco tiempo (200 ms) desde la última.
  // Esto evita que los falsos contactos de un botón mecánico se cuenten como múltiples pulsaciones.
  if (tiempoActual - ultimaInterrupcion1 > 200) {
    estadoLED1 = !estadoLED1;           // Invierte el estado lógico del LED1 (true -> false, false -> true).
    digitalWrite(LED1, estadoLED1);     // Aplica el nuevo estado al pin del LED.
    if (estadoLED1) { // Si el LED se acaba de encender...
      tiempoEncendidoLED1 = millis();   // ...registra este momento para el contador de 5 segundos.
    }
    ultimaInterrupcion1 = tiempoActual; // Actualiza el tiempo de la última pulsación válida.
  }
}

// --- ISR para el Botón 2 ---
void cambiarLED2() {
  unsigned long tiempoActual = millis();
  // Mismo mecanismo Anti-Rebote.
  if (tiempoActual - ultimaInterrupcion2 > 200) {
    contadorBoton2++; // Incrementa el contador de pulsaciones para este botón.
    // La lógica de este LED depende de si el contador es par o impar.
    if (contadorBoton2 % 2 == 0) { // Si el número de pulsaciones es par...
      estadoLED2 = true;           // ...se enciende el LED.
    } else {                       // Si es impar...
      estadoLED2 = false;          // ...se apaga.
    }
    digitalWrite(LED2, estadoLED2);     // Aplica el estado correspondiente al LED2.
    ultimaInterrupcion2 = tiempoActual; // Actualiza el tiempo.
  }
}
