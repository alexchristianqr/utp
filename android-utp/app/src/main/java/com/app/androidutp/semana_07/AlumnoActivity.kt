package com.app.androidutp.semana_07

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.androidutp.R
import com.google.android.material.textfield.TextInputEditText

class AlumnoActivity : AppCompatActivity() {
	private lateinit var tvRespuesta: TextView
	private lateinit var txtCodigo: TextInputEditText
	private lateinit var txtNombres: TextInputEditText
	private lateinit var txtApellidos: TextInputEditText
	private lateinit var txtEdad: TextInputEditText
	private lateinit var btnRegistrar: Button
	private lateinit var alumnoService: AlumnoService
	private lateinit var alumno: Alumno

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.semana_07)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		// Referenciar componentes de la interfaz
		referenciar()

		// Configurar evento del botón
		btnRegistrar.setOnClickListener {
			registrar()
		}
	}

	fun referenciar() {
		txtCodigo = findViewById(R.id.txtCodigo)
		txtNombres = findViewById(R.id.txtNombres)
		txtApellidos = findViewById(R.id.txtApellidos)
		txtEdad = findViewById(R.id.txtEdad)
		btnRegistrar = findViewById(R.id.btnRegistrar)
		tvRespuesta = findViewById(R.id.tvRespuesta)
	}


	fun registrar() {
		alumnoService = AlumnoService(this)
		alumno = Alumno(
			codigo = txtCodigo.text.toString(),
			nombres = txtNombres.text.toString(),
			apellidos = txtApellidos.text.toString(),
			edad = txtEdad.text.toString().toInt()
		)

		val rpta = alumnoService.registrarAlumno(alumno)
		print(rpta)

		resetearCampos()
		tvRespuesta.text = rpta
	}

	fun resetearCampos() {
		txtCodigo.setText("")
		txtNombres.setText("")
		txtApellidos.setText("")
		txtEdad.setText("")
		tvRespuesta.setText("-")
	}
}