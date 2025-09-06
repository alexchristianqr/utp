package com.app.androidutp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class OperacionesActivity : AppCompatActivity() {
    private lateinit var txtNumero1: TextInputEditText;
    private lateinit var txtNumero2: TextInputEditText;
    private lateinit var lblResultado: TextView;
    private lateinit var btnCalcular: Button;
    private lateinit var selectOpciones: Spinner;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.semana_04_02)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Asignar referencias de los componentes de la interfaz
        txtNumero1 = findViewById(R.id.txtNumero1)
        txtNumero2 = findViewById(R.id.txtNumero2)
        lblResultado = findViewById(R.id.lblResultado)
        btnCalcular = findViewById(R.id.btnCalcular)

        selectOpciones = findViewById(R.id.selectOpciones)

        val opciones = listOf("Suma", "Resta", "Multiplicacion", "Division")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        selectOpciones.adapter = adapter

        btnCalcular.setOnClickListener {
          calcular()
        }
    }

    fun calcular() {
        val numero1 = txtNumero1.text.toString().toInt()
        val numero2 = txtNumero2.text.toString().toInt()
        val resultado = numero1 + numero2
        println("El resultado es: $resultado")

        val opcion = selectOpciones.selectedItem.toString()
        when (opcion) {
            "Suma" -> lblResultado.text = "El resultado es: ${numero1 + numero2}"
            "Resta" -> lblResultado.text = "El resultado es: ${numero1 - numero2}"
            "Multiplicacion" -> lblResultado.text = "El resultado es: ${numero1 * numero2}"
            "Division" -> {
                if (numero2 != 0) {
                    lblResultado.text = "El resultado es: ${numero1 / numero2}"
                } else {
                    lblResultado.text = "Error: División por cero"
                }
            }
            else -> lblResultado.text = "Operación no válida"
        }

//        lblResultado.text = "El resultado es: $resultado"
    }
}