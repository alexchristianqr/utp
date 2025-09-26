package com.app.androidutp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Preferencias(context: Context) {
	val PREFERENCIAS = "AndroidUTP_Preferencias"
	val PREFERENCIA_NOMBRE = "nombre"
	val PREFERENCIA_ES_VIP: String? = "false"

	val almacenamiento: SharedPreferences? = context.getSharedPreferences(PREFERENCIAS, 0)

	fun setNombre(nombre: String) {
		almacenamiento?.edit {
			putString(PREFERENCIA_NOMBRE, nombre)
		}
	}

	fun getNombre(): String? {
		return almacenamiento?.getString(PREFERENCIA_NOMBRE, "")
	}

	fun setUsuarioVip(esVip: Boolean) {
		almacenamiento?.edit {
			putBoolean(PREFERENCIA_ES_VIP, esVip)
		}
	}

	fun esUsuarioVip(): Boolean {
		return almacenamiento?.getBoolean(PREFERENCIA_ES_VIP, false) ?: false
	}

	fun eliminar() {
		almacenamiento?.edit {
			clear()
		}
	}
}