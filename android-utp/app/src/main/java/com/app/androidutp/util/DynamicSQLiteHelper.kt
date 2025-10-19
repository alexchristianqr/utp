package com.app.androidutp.util

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DynamicSQLiteHelper(
	context: Context,
	dbName: String = "app_database.db",
	version: Int = 1
) : SQLiteOpenHelper(context, dbName, null, version) {

	private val tablesToCreate = mutableListOf<String>()

	/** ✅ Permite registrar tablas dinámicamente antes de onCreate */
	fun registerTable(createTableSql: String) {
		tablesToCreate.add(createTableSql)
	}

	override fun onCreate(db: SQLiteDatabase) {
		tablesToCreate.forEach { sql ->
			db.execSQL(sql)
		}
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		// 🔹 comportamiento opcional: eliminar tablas registradas
		tablesToCreate.forEach { sql ->
			val tableName = extractTableName(sql)
			db.execSQL("DROP TABLE IF EXISTS $tableName")
		}
		onCreate(db)
	}

	private fun extractTableName(createSql: String): String {
		// muy simple: extrae nombre tras "CREATE TABLE IF NOT EXISTS"
		return createSql.split(" ").getOrNull(5) ?: ""
	}

	/** ✅ Ejecutar consultas arbitrarias (SELECT, PRAGMA, etc.) */
	fun rawQuery(query: String, args: Array<String>? = null): Cursor? {
		return readableDatabase.rawQuery(query, args)
	}

	/** ✅ Ejecutar sentencias SQL directas (CREATE, ALTER, INSERT, etc.) */
	fun execSQL(sql: String, args: Array<Any>? = null) {
		writableDatabase.use { db ->
			if (args != null) db.execSQL(sql, args) else db.execSQL(sql)
		}
	}

	/** ✅ Inserción genérica */
	fun insert(table: String, values: Map<String, Any?>): Long {
		val db = writableDatabase
		val contentValues = ContentValues()
		values.forEach { (key, value) ->
			when (value) {
				null -> contentValues.putNull(key)
				is String -> contentValues.put(key, value)
				is Int -> contentValues.put(key, value)
				is Long -> contentValues.put(key, value)
				is Float -> contentValues.put(key, value)
				is Double -> contentValues.put(key, value)
				is Boolean -> contentValues.put(key, if (value) 1 else 0)
				else -> contentValues.put(key, value.toString())
			}
		}
		return db.insert(table, null, contentValues)
	}

	/** ✅ Actualización genérica */
	fun update(
		table: String,
		values: Map<String, Any?>,
		whereClause: String,
		whereArgs: Array<String>
	): Int {
		val db = writableDatabase
		val contentValues = ContentValues()
		values.forEach { (key, value) ->
			when (value) {
				null -> contentValues.putNull(key)
				is String -> contentValues.put(key, value)
				is Int -> contentValues.put(key, value)
				is Long -> contentValues.put(key, value)
				is Float -> contentValues.put(key, value)
				is Double -> contentValues.put(key, value)
				is Boolean -> contentValues.put(key, if (value) 1 else 0)
				else -> contentValues.put(key, value.toString())
			}
		}
		return db.update(table, contentValues, whereClause, whereArgs)
	}

	/** ✅ Eliminación genérica */
	fun delete(table: String, whereClause: String?, whereArgs: Array<String>?): Int {
		return writableDatabase.delete(table, whereClause, whereArgs)
	}
}
