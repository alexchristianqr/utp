package com.app.androidutp.alumnos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

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
            valores.put("edad", alumno.edad.toString().toInt())

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

    fun buscarAlumno(codigo: String): Alumno? {
        var alumno: Alumno? = null
        val db = sqLiteHelper.readableDatabase
        var cursor: Cursor? = null

        try {
            val sql = "SELECT id, codigo, nombres, apellidos, edad FROM alumnos WHERE codigo = ?"
            val params = arrayOf(codigo);
            cursor = db.rawQuery(sql, params)

            if (cursor.moveToFirst()) {
                val id = cursor.getString(cursor.getColumnIndexOrThrow("id"))
                val nombres = cursor.getString(cursor.getColumnIndexOrThrow("nombres"))
                val apellidos = cursor.getString(cursor.getColumnIndexOrThrow("apellidos"))
                val edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad"))
                alumno = Alumno(id, codigo, nombres, apellidos, edad)
            }
        } catch (e: Exception) {
            Log.d("==>", e.message.toString())
            alumno = null
        } finally {
            cursor?.close()
            db.close()
        }

        return alumno
    }
}