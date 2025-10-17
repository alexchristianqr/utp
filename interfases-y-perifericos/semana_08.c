#define LED1 2      // LED1 en pin 8
#define LED2 13       // LED2 en pin 9
#define BOTON1  4    // Botón1 en pin 2 (INT0)
#define BOTON2  12    // Botón2 en pin 3 (INT1)

// VARIABLES VOLÁTILES PORQUE SE MODIFICAN EN INTERRUPCIONES
volatile bool estadoLED1 = false;
volatile bool estadoLED2 = false;
volatile unsigned long ultimaInterrupcion1 = 0;
volatile unsigned long ultimaInterrupcion2 = 0;
volatile int contadorBoton2 = 0;

// VARIABLES PARA CONTROL DE TIEMPO
unsigned long tiempoEncendidoLED1 = 0;  // PARA MEDIR EL TIEMPO DE LED1 ENCENDIDO
unsigned long tiempoParpadeo = 0;       // PARA EL PARPADEO AUTOMÁTICO
unsigned long ultimaImpresion = 0;      // PARA MENSAJES POR SERIAL

void setup() {
  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);
  pinMode(BOTON1, INPUT_PULLUP);  // SE USA PULLUP INTERNO
  pinMode(BOTON2, INPUT_PULLUP);

  Serial.begin(9600);

  // CONFIGURAR INTERRUPCIONES
  attachInterrupt(digitalPinToInterrupt(BOTON1), cambiarLED1, FALLING);
  attachInterrupt(digitalPinToInterrupt(BOTON2), cambiarLED2, FALLING);
}

void loop() {
  // VERIFICAR SI LED1 LLEVA MÁS DE 5 SEGUNDOS ENCENDIDO
  if (estadoLED1) {
    if (millis() - tiempoEncendidoLED1 > 5000) {
      // HACER PARPADEAR LED1 CADA 500 MILISEGUNDOS
      if (millis() - tiempoParpadeo > 500) {
        digitalWrite(LED1, !digitalRead(LED1));
        tiempoParpadeo = millis();
      }
    }
  }

  // MOSTRAR INFORMACIÓN POR SERIAL CADA 2 SEGUNDOS
  if (millis() - ultimaImpresion > 2000) {
    Serial.print("ESTADO LED1: ");
    Serial.print(estadoLED1 ? "ENCENDIDO" : "APAGADO");
    Serial.print(" | ESTADO LED2: ");
    Serial.print(estadoLED2 ? "ENCENDIDO" : "APAGADO");
    Serial.print(" | CONTADOR BOTON2: ");
    Serial.println(contadorBoton2);
    ultimaImpresion = millis();
  }
}

// INTERRUPCIÓN PARA BOTÓN 1 (LED1)
void cambiarLED1() {
  unsigned long tiempoActual = millis();
  if (tiempoActual - ultimaInterrupcion1 > 200) { // ANTI-REBOTE
    estadoLED1 = !estadoLED1;
    digitalWrite(LED1, estadoLED1);
    if (estadoLED1) {
      tiempoEncendidoLED1 = millis(); // SE REGISTRA EL TIEMPO DE ENCENDIDO
    }
    ultimaInterrupcion1 = tiempoActual;
  }
}

// INTERRUPCIÓN PARA BOTÓN 2 (LED2 CONTROLADO POR CONTADOR PAR/IMPAR)
void cambiarLED2() {
  unsigned long tiempoActual = millis();
  if (tiempoActual - ultimaInterrupcion2 > 200) { // ANTI-REBOTE
    contadorBoton2++;
    if (contadorBoton2 % 2 == 0) {
      estadoLED2 = true;   // SI ES PAR, LED2 ENCENDIDO
    } else {
      estadoLED2 = false;  // SI ES IMPAR, LED2 APAGADO
    }
    digitalWrite(LED2, estadoLED2);
    ultimaInterrupcion2 = tiempoActual;
  }
}
