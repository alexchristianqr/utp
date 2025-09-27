package com.app.androidutp.semana_07

import android.content.ContentValues
import android.content.Context

class AlumnoService(context: Context) {
	private var sqLiteHelper: SQLiteHelper = SQLiteHelper(context)

	fun registrarAlumno(alumno: Alumno): String {

		var respuesta = ""
		val db = sqLiteHelper.writableDatabase

		sqLiteHelper.onOpen(db);

		try {
			val valores = ContentValues()
			valores.put("codigo", alumno.codigo)
			valores.put("nombres", alumno.nombres)
			valores.put("apellidos", alumno.apellidos)
			valores.put("edad", alumno.edad)

			val r = db.insert("alumnos", null, valores)
			if (r == -1L) {
				respuesta = "Error al registrar alumno"
			} else {
				respuesta = "Alumno registrado exitosamente"
			}
		} catch (e: Exception) {
			respuesta = e.message.toString()
		} finally {
			db.close()
		}

		return respuesta
	}
}