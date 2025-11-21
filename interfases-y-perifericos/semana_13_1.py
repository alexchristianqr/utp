import numpy as np                      # Manejo de datos numéricos y matrices
from skimage import io                  # Para leer imágenes desde archivo
import matplotlib.pyplot as plt         # Para mostrar imágenes
from scipy.ndimage import convolve      # Para aplicar filtros de convolución


def generar_bayer(img_rgb):
    h, w, _ = img_rgb.shape             # Obtiene alto, ancho y canales de la imagen
    bayer = np.zeros((h, w))            # Crea imagen vacía (1 solo canal RAW)

    # Construcción del patrón Bayer RGGB:
    # R G R G ...
    # G B G B ...
    # R G R G ...
    # G B G B ...
    bayer[0::2, 0::2] = img_rgb[0::2, 0::2, 0]  # Valores ROJO en filas pares, columnas pares
    bayer[0::2, 1::2] = img_rgb[0::2, 1::2, 1]  # Valores VERDE en filas pares, columnas impares
    bayer[1::2, 0::2] = img_rgb[1::2, 0::2, 1]  # Valores VERDE en filas impares, columnas pares
    bayer[1::2, 1::2] = img_rgb[1::2, 1::2, 2]  # Valores AZUL en filas impares, columnas impares

    return bayer                           # Devuelve el mosaico RAW


def demosaic_bilineal(bayer):
    h, w = bayer.shape                    # Dimensiones de la imagen RAW
    rgb = np.zeros((h, w, 3))             # Imagen reconstruida vacía en RGB

    # Máscaras para indicar qué píxel pertenece a qué canal en el mosaico:
    Rmask = np.zeros_like(bayer); Rmask[0::2, 0::2] = 1       # Posiciones del rojo
    Gmask = np.zeros_like(bayer); 
    Gmask[0::2, 1::2] = 1                                     # Verde superior
    Gmask[1::2, 0::2] = 1                                     # Verde inferior
    Bmask = np.zeros_like(bayer); Bmask[1::2, 1::2] = 1       # Posiciones del azul

    # Colocar los valores capturados reales en su canal correspondiente
    rgb[:,:,0] = bayer * Rmask              # Canal rojo
    rgb[:,:,1] = bayer * Gmask              # Canal verde
    rgb[:,:,2] = bayer * Bmask              # Canal azul

    # Kernel de interpolación bilineal (promedio ponderado)
    kernel = np.array([
        [0.25, 0.5, 0.25],
        [0.5 , 1.0, 0.5 ],
        [0.25, 0.5, 0.25]
    ])

    # Aplicar interpolación a cada canal para rellenar valores faltantes
    for c in range(3):
        rgb[:,:,c] = convolve(rgb[:,:,c], kernel)

    return np.clip(rgb, 0, 1)              # Asegura valores entre 0 y 1


def main():
    ruta = "C:/Ruta /ovoo.jpeg"              # Ruta del archivo a procesar

    # Cargar la imagen original y normalizarla a rango 0–1
    img = io.imread(ruta) / 255.0
    plt.figure(); plt.imshow(img); plt.title("1. Imagen original"); plt.axis("off")

    # Generar el mosaico RAW en patrón Bayer RGGB
    bayer = generar_bayer(img)
    plt.figure(); plt.imshow(bayer, cmap='gray'); plt.title("2. Mosaico Bayer (RAW)"); plt.axis("off")

    # Aplicar demosaicing para reconstruir la imagen RGB
    img_demosaic = demosaic_bilineal(bayer)
    plt.figure(); plt.imshow(img_demosaic); plt.title("3. Imagen demosaiced (RGB)"); plt.axis("off")

    # Mostrar comparación lado a lado
    plt.figure(figsize=(12,6))
    plt.subplot(1,2,1); plt.imshow(img); plt.title("Original"); plt.axis("off")
    plt.subplot(1,2,2); plt.imshow(img_demosaic); plt.title("Reconstruida"); plt.axis("off")
    plt.show()


if __name__ == "__main__":
    main()    # Ejecuta el programa principal
