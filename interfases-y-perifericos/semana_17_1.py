import serial, matplotlib.pyplot as plt, matplotlib.animation as animation
from collections import deque
import csv, time, math, random

# === Configuración de puerto ===
# ser = serial.Serial('COM4', 115200, timeout=1)   # Para ESP32 real
ser = None  # Simulación

xs = deque(maxlen=300)
ys = deque(maxlen=300)
states = deque(maxlen=300)

fig, ax = plt.subplots()
line, = ax.plot([], [])
current_state = None
start_time = time.time()



ultimo_outlier = 0   # memoria del último outlier generado
AMPLITUD = 1.0       # amplitud de la señal base
RUIDO = 0.15         # ruido normal
K = 300              # parámetro que define el periodo

def dato_simulado():
    global ultimo_outlier

    t = int((time.time() - start_time) * 1000)

    # Señal base senoidal entre -1 y +1
    base = math.sin(t / K)

    # Ruido leve
    v = base + random.uniform(-RUIDO, RUIDO)

    # Detectar si estamos en picos alto o bajo
    pico_alto = base > 0.85 * AMPLITUD
    pico_bajo = base < -0.85 * AMPLITUD

    # Calcular periodo aproximado
    periodo = 2 * math.pi * K        # periodo según seno(t/K)
    dt = t - ultimo_outlier          # ms desde último outlier

    # Condición: outlier permitido solo entre 2× y 3× el periodo
    ventana = (dt > 2 * periodo) and (dt < 3 * periodo)

    # Si estamos dentro de una ventana válida y en un pico → posible outlier
    if ventana and (pico_alto or pico_bajo):

        # Probabilidad moderada de generar un outlier
        if random.random() < 0.4:  

            if pico_alto:
                # outlier entre amplitud y 2× amplitud
                v = random.uniform(AMPLITUD, 2 * AMPLITUD)
            else:
                v = random.uniform(-2 * AMPLITUD, -AMPLITUD)

            ultimo_outlier = t

        # Outlier suave adicional
        elif random.random() < 0.15:
            if pico_alto:
                v = random.uniform(0.8 * AMPLITUD, 1.2 * AMPLITUD)
            else:
                v = random.uniform(-1.2 * AMPLITUD, -0.8 * AMPLITUD)
            ultimo_outlier = t

    # Limitar al rango visual del gráfico
    v = max(-2.2, min(2.2, v))

    # Asignación de estados
    if v < -0.3:
        st = "NORMAL"
    elif v < 0.5:
        st = "ALERTA"
    else:
        st = "ALARM"
        
    return f"{t},{v:.3f},{st}"







# --- Función: generar dato simulado ---
def dato_simulado_bk():
    t = int((time.time() - start_time) * 250) # tiempo
    v = math.sin(t/500) + 250 + random.uniform(-0.1, 0.1) # calculo matematico
    st = "NORMAL" if v < -0.2 else "ALERTA" if v < 0.5 else "ALARMA" # cualquier estado
    return f"{t},{v:.2f},{st}"

# --- Función: leer dato de ESP32 ---
def dato_esp32():
    if ser and ser.in_waiting:
        return ser.readline().decode().strip()
    return None

# --- Función: procesar trama ---
def procesar(d):
    global current_state
    if d.count(",") != 2: return
    t, v, st = d.split(",")
    xs.append(int(t))
    ys.append(float(v))
    states.append(st)
    if st != current_state:
        print(f"CAMBIO DE ESTADO: {st} en t={t}")
        current_state = st

# --- Función de actualización ---
def update(i):
    d = dato_esp32() or dato_simulado()
    procesar(d)
    line.set_data(range(len(ys)), ys)
    # ax.set_ylim(-1.5, 1.5)
    ax.set_ylim(-2.2, 2.2)
    ax.set_xlim(0, len(ys) + 10)
    ax.set_title(f"Estado actual: {current_state}")
    return line,

# --- Guardado de CSV ---
def guardar_csv():
    with open("datos_esp32.csv", "w", newline="") as f:
        w = csv.writer(f)
        w.writerow(["timestamp", "valor", "estado"])
        for t, v, st in zip(xs, ys, states):
            w.writerow([t, v, st])

ani = animation.FuncAnimation(fig, update, interval=30, cache_frame_data=False)
plt.show()
