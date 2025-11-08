package com.app.androidutp.common.util

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

	private var loadingDialog: AlertDialog? = null

	fun mostrarLoading(context: Context, mensaje: String) {
		cerrarLoading()
		val dialog = AlertDialog.Builder(context)
			.setTitle("Cargando")
			.setMessage(mensaje)
			.setCancelable(false)
			.create()
		dialog.show()
		loadingDialog = dialog
	}

	fun cerrarLoading() {
		loadingDialog?.let {
			if (it.isShowing) {
				it.dismiss()
			}
			loadingDialog = null
		}
	}
}