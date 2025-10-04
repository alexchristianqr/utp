package com.app.androidutp

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper

object Utilidad {

	fun mostrarAlerta(context: Context?, titulo: String, mensaje: String) {
		// Validar que el contexto no sea nulo y que sea una Activity visible
		if (context == null) return

		// Ejecutar siempre en el hilo principal (UI)
		if (Looper.myLooper() == Looper.getMainLooper()) {
			mostrarDialog(context, titulo, mensaje)
		} else {
			Handler(Looper.getMainLooper()).post {
				mostrarDialog(context, titulo, mensaje)
			}
		}
	}

	private fun mostrarDialog(context: Context, titulo: String, mensaje: String) {
		try {
			AlertDialog.Builder(context)
				.setTitle(titulo)
				.setMessage(mensaje)
				.setPositiveButton("OK", null)
				.show()
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}