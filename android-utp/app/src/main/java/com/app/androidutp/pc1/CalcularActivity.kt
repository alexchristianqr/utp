package com.app.androidutp.pc1

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.androidutp.R
import com.google.android.material.textfield.TextInputEditText

class CalcularActivity : AppCompatActivity() {
    private lateinit var txtSueldoBruto: TextInputEditText
    private lateinit var txtAguinaldo: TextInputEditText
    private lateinit var lblResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var cbxIGV: CheckBox
    private lateinit var cbxSeguro: CheckBox
    private lateinit var cbxEPS: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pc1_tarea_02)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referenciar componentes de la interfaz
        referenciar()

        // Configurar evento del botón
        btnCalcular.setOnClickListener {
            calcular()
        }
    }

    fun referenciar() {
        txtSueldoBruto = findViewById(R.id.txtSueldoBruto)
        txtAguinaldo = findViewById(R.id.txtAguinaldo)
        lblResultado = findViewById(R.id.lblResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        cbxIGV = findViewById(R.id.cbxIGV)
        cbxSeguro = findViewById(R.id.cbxSeguro)
        cbxEPS = findViewById(R.id.cbxEPS)
    }

    fun calcular() {
        val numero1 = txtSueldoBruto.text.toString().toIntOrNull()
        val numero2 = txtSueldoBruto.text.toString().toIntOrNull()

        if (numero1 == null || numero2 == null) {
            mostrarAlerta("Error", "Por favor ingrese números válidos.")
            return
        }

        var resultado: String
        var total: Double = 0.0
        val sueldoBruto = txtSueldoBruto.text.toString().toInt() // 3000
        val aguinaldoPorcentaje = txtAguinaldo.text.toString().toInt()
        val totalAguinaldo = (sueldoBruto * aguinaldoPorcentaje) / 100 // 300
        val subTotal = sueldoBruto + totalAguinaldo

        val IGV = 18
        val SEGURO = 5

        var totalIGV: Double = 0.0
        var totalSeguro: Double = 0.0
        var totalEPS: Int = 0

        if (cbxIGV.isChecked) {
            totalIGV = ((sueldoBruto * IGV) / 100).toDouble()
        }

        if (cbxSeguro.isChecked) {
            totalSeguro = ((sueldoBruto * SEGURO) / 100).toDouble()
        }

        if (cbxEPS.isChecked) {
            totalEPS = 150
        }

        total = subTotal - totalIGV - totalSeguro - totalEPS

        resultado = "El sueldo bruto es: $sueldoBruto\n" +
                "El aguinaldo es: $totalAguinaldo\n" +
                "El IGV es: $totalIGV\n" +
                "El seguro es: $totalSeguro\n" +
                "El EPS es: $totalEPS\n" +
                "-------------------------\n" +
                "El total a pagar es: $total"

        lblResultado.text = resultado
    }

    fun Context.mostrarAlerta(titulo: String, mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}