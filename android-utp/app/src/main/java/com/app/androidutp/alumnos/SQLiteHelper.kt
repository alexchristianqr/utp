package com.app.androidutp.alumnos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(
	context,
	"universidad.db",
	null,
	1
) {
//	override fun onOpen
	override fun onCreate(db: SQLiteDatabase?) {
		val sql = "CREATE TABLE IF NOT EXISTS alumnos " +
			"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"codigo TEXT, " +
			"nombres TEXT, " +
			"apellidos TEXT, " +
			"edad INTEGER)"
		db?.execSQL(sql)
	}

	override fun onUpgrade(
		db: SQLiteDatabase?,
		p1: Int,
		p2: Int
	) {
		db?.execSQL("DROP TABLE IF EXISTS alumnos")

	}
}