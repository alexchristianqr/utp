; === Definición de Constantes (Etiquetas) ===
; Estas líneas no son código ejecutable. Le dan un nombre fácil de recordar
; a números (direcciones de puertos) para que el código sea más legible.

EOI  EQU 20H      ; Define 'EOI' como el valor 20H. Es el puerto para la señal "End Of Interrupt" (Fin de Interrupción).
IMR  EQU 21H      ; Define 'IMR' como 21H. Es el puerto del "Interrupt Mask Register" (Registro de Máscara de Interrupción).
INT0 EQU 24H      ; Define 'INT0' como 24H. Probablemente un puerto de control para configurar la interrupción 0 en este sistema específico.

; === Rutina de Servicio de Interrupción (ISR) ===
; Esta es la función que se ejecutará CADA VEZ que ocurra la interrupción de hardware configurada.
ORG 3000H         ; Directiva "ORGanize": Le dice al ensamblador que coloque el siguiente código en la dirección de memoria 3000H.

CONTAR:
  INC DL          ; INCrementa en 1 el valor del registro DL. Este es nuestro contador.
  MOV AL, EOI     ; MOVe el valor de EOI (20H) al registro AL. Se necesita para la instrucción OUT.
  OUT EOI, AL     ; Envía (OUTput) el valor de AL al puerto EOI (20H). Esto le avisa al controlador de interrupciones que hemos terminado.
  IRET            ; Instrucción "Interrupt RETurn". Regresa el control al programa principal, justo donde fue interrumpido.

; === Programa Principal y Configuración ===
ORG 2000H         ; Le dice al ensamblador que coloque el siguiente código en la dirección de memoria 2000H.

; --- Configuración del Vector de Interrupción ---
  MOV AX, CONTAR  ; MOVe la dirección de la etiqueta 'CONTAR' (que es 3000H) al registro AX.
  MOV BX, 40H     ; MOVe el valor 40H (64 en decimal) al registro BX. Esta es la dirección de memoria para el vector de la interrupción 10H.
  MOV [BX], AX    ; MOVe el contenido de AX (la dirección de CONTAR) a la dirección de memoria apuntada por BX ([40H]).
                  ; Ahora, la interrupción 10H apuntará a nuestra rutina 'CONTAR'.

; --- Configuración del Controlador de Interrupciones (PIC) ---
  CLI             ; CLear Interrupts: Deshabilita TODAS las interrupciones temporalmente. Es una buena práctica mientras lo reconfiguramos.
  MOV AL, 11111110B ; MOVe el valor binario 11111110 al registro AL. El '0' en la posición menos significativa (IRQ0) la desenmascara (habilita).
  OUT IMR, AL     ; Envía (OUTput) el valor de AL al puerto IMR (21H). Con esto, solo la interrupción de hardware 0 (IRQ0) está permitida.
  MOV AL, 10      ; MOVe el número 10 al registro AL.
  OUT INT0, AL    ; Envía el valor de AL al puerto INT0 (24H). Esto probablemente configura alguna característica específica de la interrupción 0.
  STI             ; SeT Interrupts: Vuelve a habilitar las interrupciones en el CPU. El sistema ya está listo para recibirlas.

; --- Inicialización y Bucle de Espera ---
  MOV DL, 0       ; MOVe un 0 al registro DL, inicializando nuestro contador.

LOOP:
  JMP LOOP        ; JuMP to LOOP: Salta a la etiqueta 'LOOP'. Esto crea un bucle infinito que no hace nada, solo espera.

INT 0             ; Llama a la interrupción de software número 0. Esto es probablemente para fines de prueba.
END               ; Directiva que indica el fin del código fuente para el ensamblador.


--


EOI EQU 20H
IMR EQU 21H
INT0 EQU 24H

ORG 3000H
CONTAR:
  INC DL
  MOV AL, EOI
  OUT EOI, AL
  IRET

ORG 2000H
  MOV AX, CONTAR
  MOV BX, 40
  MOV [BX], AX

  CLI
  MOV AL, 11111110B
  OUT IMR, AL
  MOV AL, 10
  OUT INT0, AL
  STI

  ; MOV DL, 0
  ; MOV AL, DL 

LOOP: JMP LOOP

MOV DL, AL

INT 0

END
