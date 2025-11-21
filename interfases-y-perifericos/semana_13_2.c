#include <BleKeyboard.h>        // Librería para emular un teclado Bluetooth BLE
#include <Wire.h>               // Librería para comunicación I2C
#include <Adafruit_GFX.h>       // Librería gráfica base
#include <Adafruit_SH110X.h>    // Librería para pantallas OLED basadas en SH1106


// Crear objeto teclado BLE con nombre, fabricante y potencia de señal
BleKeyboard teclado("ESP32 Teclado BLE", "Lab", 100);


// Configuración de la pantalla OLED
#define i2c_Address 0x3C
#define SCREEN_WIDTH 128
#define SCREEN_HEIGHT 64
#define OLED_RESET -1
Adafruit_SH1106G display = Adafruit_SH1106G(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, OLED_RESET);


// Velocidades en milisegundos para efectos de escritura
#define VELOCIDAD_LETRA 120
#define VELOCIDAD_PALABRA 300
#define TIEMPO_ESPERA 3000
#define TIEMPO_ENTRE_CICLOS 2000


// Textos que el ESP32 escribirá como teclado BLE
const char* texto1 = "HOLA OLMER OLORTEGUI ORTEGA";
const char* texto2 = "ESTO ES UNA PRUEBA DE FUNCIONAMIENTO COMO TECLADO DESDE ESP32";


// ------------------------------------------------------------
// Función: escribirTextoLento
// Escribe un texto letra por letra a través del teclado BLE
// y lo muestra simultáneamente en la pantalla OLED
// ------------------------------------------------------------
void escribirTextoLento(const char* texto, int velLetra, int velPalabra) {
  display.clearDisplay();        // Limpia la pantalla
  display.setCursor(0, 0);       // Posición inicial del texto
  display.setTextSize(1);        // Tamaño de texto pequeño
  display.setTextColor(SH110X_WHITE);

  while (*texto) {               // Recorre cada carácter del texto
    char c = *texto++;           // Extrae siguiente carácter
    teclado.print(String(c));    // Lo envía como teclado BLE
    display.print(c);            // Lo muestra en la OLED
    display.display();           // Actualiza la pantalla

    if (c == ' ' || c == '\n')   // Si es espacio o salto de línea, usar pausa larga
      delay(velPalabra);
    else                         // Si es letra normal, usar pausa corta
      delay(velLetra);
  }

  display.display();             // Actualizar pantalla final
}


// ------------------------------------------------------------
// Función: borrarTexto
// Envía teclas BACKSPACE repetidas para borrar texto en PC
// También limpia la pantalla OLED
// ------------------------------------------------------------
void borrarTexto(int cantidad, int velocidad) {
  for (int i = 0; i < cantidad; i++) {
    teclado.write(KEY_BACKSPACE);   // Simula tecla borrar
    delay(velocidad);
  }
  display.clearDisplay();           // Limpia pantalla OLED
  display.display();
}


// ------------------------------------------------------------
// SETUP
// Se ejecuta una sola vez al iniciar el ESP32
// ------------------------------------------------------------
void setup() {
  Serial.begin(115200);     // Comunicación serie
  teclado.begin();          // Inicializa el teclado BLE

  // Inicializar pantalla OLED
  if (!display.begin(i2c_Address, true)) {
    Serial.println("No se encontró la pantalla OLED. Verifique conexión.");
    for (;;);               // Detener el código si no detecta pantalla
  }

  display.clearDisplay();
  display.setTextSize(1);
  display.setTextColor(SH110X_WHITE);
  display.setCursor(0, 10);
  display.println("Iniciando Teclado BLE...");
  display.display();

  delay(2000);
  display.clearDisplay();
  display.display();
}


// ------------------------------------------------------------
// LOOP
// Se ejecuta continuamente
// ------------------------------------------------------------
void loop() {

  // Si la computadora/telefono está conectada por BLE:
  if (teclado.isConnected()) {

    display.clearDisplay();
    display.setCursor(0, 0);
    display.println("Escribiendo...");
    display.display();

    // Escribir textos con efecto máquina de escribir
    escribirTextoLento(texto1, VELOCIDAD_LETRA, VELOCIDAD_PALABRA);
    delay(1000);
    escribirTextoLento(texto2, VELOCIDAD_LETRA, VELOCIDAD_PALABRA);

    delay(TIEMPO_ESPERA);   // Pausa luego de escribir

    // Calcular cuántos caracteres se deben borrar
    int totalChars = strlen(texto1) + strlen(texto2) + 10;

    // Borrar texto usando BACKSPACE
    borrarTexto(totalChars, 50);

    display.clearDisplay();
    display.setCursor(0, 20);
    display.println("Texto borrado...");
    display.display();

    delay(TIEMPO_ENTRE_CICLOS);   // Pausa antes de repetir
  } 
  
  // Si NO hay conexión BLE:
  else {
    display.clearDisplay();
    display.setCursor(0, 20);
    display.println("Esperando conexion BLE...");
    display.display();
    delay(2000);
  }
}
