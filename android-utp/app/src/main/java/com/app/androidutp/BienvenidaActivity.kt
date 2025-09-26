package com.app.androidutp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BienvenidaActivity : AppCompatActivity() {
	private lateinit var tvBienvenida: TextView
	private lateinit var btnTerminar: Button
	private lateinit var preferencias: Preferencias

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.semana_06_intent)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		// Referenciar componentes de la interfaz
		referenciar()

		// Cargar y mostrar preferencias
		cargarPreferencias()

		// Configurar evento del botón
		btnTerminar.setOnClickListener {
			terminar()
		}
	}

	fun referenciar() {
		tvBienvenida = findViewById(R.id.tvBienvenida)
		btnTerminar = findViewById(R.id.btnTerminar)
		preferencias = Preferencias(this)
	}

	fun cargarPreferencias() {
		val nombre = preferencias.getNombre()
		val esUsuarioVip = preferencias.esUsuarioVip()

		val mensajeBienvenida = if (esUsuarioVip) {
			"¡Bienvenido usuario VIP, $nombre!"
		} else {
			"¡Bienvenido usuario, $nombre!"
		}

		tvBienvenida.text = mensajeBienvenida
	}

	fun terminar() {
		preferencias.eliminar()
		val intent = Intent(this, PreferenciasActivity::class.java)
		startActivity(intent)
	}
}