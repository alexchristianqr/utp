package com.app.androidutp

import android.app.AlertDialog
import android.content.Context

open class Utilidad {
	companion object {
		fun mostrarAlerta(context: Context, titulo: String, mensaje: String) {
			AlertDialog.Builder(context)
				.setTitle(titulo)
				.setMessage(mensaje)
				.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
				.show()
		}
	}
}