package com.app.androidutp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.pow

class OperacionesActivity : AppCompatActivity() {
	private lateinit var txtNumero1: TextInputEditText
	private lateinit var txtNumero2: TextInputEditText
	private lateinit var lblResultado: TextView
	private lateinit var btnCalcular: Button
	private lateinit var selectOpciones: Spinner
	private lateinit var cbxCuadrado: CheckBox
	private lateinit var cbxCubo: CheckBox

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.semana_04_tarea)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		// Referenciar componentes de la interfaz
		referenciar()

		// Configurar Spinner con opciones
		selectOpciones.adapter = cargarOpciones()

		// Configurar evento del botón
		btnCalcular.setOnClickListener {
			calcular()
		}
	}

	fun referenciar() {
		txtNumero1 = findViewById(R.id.txtNumero1)
		txtNumero2 = findViewById(R.id.txtNumero2)
		lblResultado = findViewById(R.id.lblResultado)
		btnCalcular = findViewById(R.id.btnCalcular)
		cbxCuadrado = findViewById(R.id.cbxCuadrado)
		cbxCubo = findViewById(R.id.cbxCubo)
		selectOpciones = findViewById(R.id.selectOpciones)
	}

	fun cargarOpciones(): ArrayAdapter<String?> {
		val opciones = listOf("Suma", "Resta", "Multiplicacion", "Division")
		val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
		return adapter
	}

	fun calcular() {
		val numero1 = txtNumero1.text.toString().toIntOrNull()
		val numero2 = txtNumero2.text.toString().toIntOrNull()

		if (numero1 == null || numero2 == null) {
			mostrarAlerta("Error", "Por favor ingrese números válidos.")
			return
		}

		var resultado: Int
		val opcion = selectOpciones.selectedItem.toString()
		when (opcion) {
			"Suma" -> {

				sumar(numero1, numero2).also {
					resultado = recalcularAlExponente(it)
					lblResultado.text = "El resultado es: $resultado"
				}
			}

			"Resta" -> {
				restar(numero1, numero2).also {
					resultado = recalcularAlExponente(it)
					lblResultado.text = "El resultado es: $resultado"
				}
			}

			"Multiplicacion" -> {
				multiplicar(numero1, numero2).also {
					resultado = recalcularAlExponente(it)
					lblResultado.text = "El resultado es: $resultado"
				}
			}

			"Division" -> {
				dividir(numero1, numero2).also {
					resultado = recalcularAlExponente(it)
					lblResultado.text = "El resultado es: $resultado"
				}
			}

			else -> mostrarAlerta("Error", "Operación no válida.")
		}
	}

	fun recalcularAlExponente(valor: Int): Int {
		val base: Int = valor
		var exponente:Int
		var resultado: Int

		if (cbxCuadrado.isChecked) {
			exponente = 2
			resultado = base.toDouble().pow(exponente).toInt()
		} else if (cbxCubo.isChecked) {
			exponente = 3
			resultado = base.toDouble().pow(exponente).toInt()
		}else{
			resultado = base
		}
		return resultado
	}

	fun sumar(a: Int, b: Int): Int {
		if (a < 0 || b < 0) {
			mostrarAlerta("Error", "Los números deben ser positivos.")
		}
		return a + b
	}

	fun restar(a: Int, b: Int): Int {
		return a - b
	}

	fun multiplicar(a: Int, b: Int): Int {
		return a * b
	}

	fun dividir(a: Int, b: Int): Int {
		if (b == 0) {
			mostrarAlerta("Error", "No se puede dividir por cero.")
			return 0
		}
		return a / b
	}

	fun Context.mostrarAlerta(titulo: String, mensaje: String) {
		AlertDialog.Builder(this)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
			.show()
	}
}