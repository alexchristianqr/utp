# -*- coding: utf-8 -*-
"""
Script de ejemplo para comunicación simultánea PC ↔ ESP32 usando Python.
Lee datos del ESP32 y envía datos al mismo tiempo mediante hilos (threads).
"""

import serial, threading, time   # Librerías necesarias:
# serial → comunicación por puerto COM
# threading → permite ejecutar funciones en paralelo
# time → usado para pausas con sleep()


# ---- CONFIGURACIÓN DEL PUERTO SERIAL ----
ser = serial.Serial('COM4', 9600, timeout=1)
# 'COM4' → puerto donde está conectado el ESP32
# 9600 → velocidad de comunicación (baud rate). Debe coincidir con el ESP32.
# timeout=1 → si no llega nada en 1 segundo, continúa sin bloquearse

ser.reset_input_buffer()   # Limpia el buffer de entrada (borra datos antiguos)
ser.reset_output_buffer()  # Limpia el buffer de salida


# ---- FUNCIÓN PARA RECIBIR DATOS DESDE LA ESP32 ----
def recibir():
    while True:   # Bucle infinito para leer constantemente
        try:
            data = ser.readline().decode(errors='ignore').strip()
            # readline() → lee una línea enviada por el ESP32
            # decode() → convierte de bytes a texto
            # errors='ignore' → ignora caracteres corruptos
            # strip() → quita saltos de línea y espacios

            if data:     # Si la línea recibida no está vacía
                print("ESP32:", data)   # Imprime lo recibido
        except Exception as e:
            # Si ocurre un error (cable desconectado, etc.)
            print("Error de lectura:", e)


# ---- FUNCIÓN PARA ENVIAR DATOS HACIA LA ESP32 ----
def enviar():
    while True:   # Envío repetido
        ser.write(b"Hola simultaneo desde PC\n")
        # write() → envía bytes al ESP32
        # b"texto" → formato de bytes
        time.sleep(1)   # Pausa de 1 segundo entre envíos


# ---- CREACIÓN DE HILOS (THREADS) ----
t1 = threading.Thread(target=recibir)   # Hilo para recibir
t2 = threading.Thread(target=enviar)    # Hilo para enviar

t1.start()   # Inicia el hilo de recepción
t2.start()   # Inicia el hilo de envío
