package com.app.androidutp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.androidutp.common.util.Utilidad
import com.google.android.material.textfield.TextInputEditText

class PreferenciasActivity : AppCompatActivity() {
	private lateinit var txtNombre: TextInputEditText
	private lateinit var btnContinuar: Button
	private lateinit var chkEsVip: CheckBox
	private lateinit var preferencias: Preferencias

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.semana_06)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		// Referenciar componentes de la interfaz
		referenciar()


		// Configurar evento del botón
		btnContinuar.setOnClickListener {
			continuar()
		}
	}

	fun referenciar() {
		txtNombre = findViewById(R.id.txtNombre)
		btnContinuar = findViewById(R.id.btnContinuar)
		chkEsVip = findViewById(R.id.chkEsVip)
		preferencias = Preferencias(this)
	}

	fun continuar() {
		val nombre = txtNombre.text.toString().trim()
		val esUsuarioVip = chkEsVip.isChecked

		// Validar que el nombre no esté vacío
		if (nombre.isEmpty()) {
			Utilidad.mostrarAlerta(this, "Error", "Por favor ingrese su nombre.")
			return
		}

		// Guardar preferencias
		preferencias.setNombre(nombre)
		preferencias.setUsuarioVip(esUsuarioVip)

		abrirUIBienvenida()
	}

	fun abrirUIBienvenida(){
		val intent = Intent(this, BienvenidaActivity::class.java)
		startActivity(intent)
	}
}