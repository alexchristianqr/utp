package com.app.androidutp.pc2.autos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.app.androidutp.util.DynamicSQLiteHelper

class AutoService(context: Context) {
    private var dbHelper: DynamicSQLiteHelper = DynamicSQLiteHelper(context, "db_autos.db")

    init {
        registerTables()
        dbHelper.writableDatabase
    }

    private fun registerTables() {
        dbHelper.registerTable(
            """
            CREATE TABLE IF NOT EXISTS autos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                placa TEXT,
                marca TEXT,
                color TEXT,
                nro_asientos TEXT,
                precio TEXT,
                anio_fab TEXT
            )
            """.trimIndent()
        )
    }

    fun registrarAuto(auto: Auto): String {

        var respuesta = ""
        val db = dbHelper.writableDatabase

        dbHelper.onOpen(db);

        try {
            val valores = ContentValues()
            valores.put("id", "")
            valores.put("placa", auto.placa)
            valores.put("marca", auto.marca)
            valores.put("color", auto.color)
            valores.put("nro_asientos", auto.nroAsientos)
            valores.put("precio", auto.precio)
            valores.put("anio_fab", auto.anioFabricacion)

            val r = db.insert("autos", null, valores)
            if (r == -1L) {
                respuesta = "Error al registrar vehiculo"
            } else {
                respuesta = "Vehiculo registrado exitosamente"
            }
        } catch (e: Exception) {
            respuesta = e.message.toString()
        } finally {
            db.close()
        }

        return respuesta
    }

    fun buscarAuto(placa: String): Auto? {
        var auto: Auto? = null
        val db = dbHelper.readableDatabase
        var cursor: Cursor? = null

        try {
            val sql = "SELECT id, placa, marca, color, nro_asientos, precio, anio_fab FROM autos WHERE placa = ?"
            val params = arrayOf(placa);
            cursor = db.rawQuery(sql, params)

            if (cursor.moveToFirst()) {
                val id = cursor.getString(cursor.getColumnIndexOrThrow("id"))
                val placa = cursor.getString(cursor.getColumnIndexOrThrow("placa"))
                val marca = cursor.getString(cursor.getColumnIndexOrThrow("marca"))
                val color = cursor.getString(cursor.getColumnIndexOrThrow("color"))
                val nroAsientos = cursor.getString(cursor.getColumnIndexOrThrow("nro_asientos"))
                val precio = cursor.getString(cursor.getColumnIndexOrThrow("precio"))
                val aniofab = cursor.getString(cursor.getColumnIndexOrThrow("anio_fab"))
                auto = Auto(id, placa, marca, color, nroAsientos, precio, aniofab)
            }
        } catch (e: Exception) {
            Log.d("==>", e.message.toString())
            auto = null
        } finally {
            cursor?.close()
            db.close()
        }

        return auto
    }
}