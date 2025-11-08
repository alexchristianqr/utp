package com.app.androidutp.pc2.autos

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.androidutp.R
import com.app.androidutp.common.util.Utilidad
import com.google.android.material.textfield.TextInputEditText

class RegistrarAutoActivity : AppCompatActivity() {
    private lateinit var txtPlaca: TextInputEditText
    private lateinit var txtMarca: TextInputEditText
    private lateinit var txtColor: TextInputEditText
    private lateinit var txtNroAsientos: TextInputEditText
    private lateinit var txtPrecio: TextInputEditText
    private lateinit var txtAnioFab: TextInputEditText
    private lateinit var btnRegistrar: Button
    private lateinit var autoService: AutoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.pc2_registrar_auto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pc2_registrar_auto)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        referenciar()

        btnRegistrar.setOnClickListener {
            registrar()
        }
    }

    fun referenciar() {
        txtPlaca = findViewById(R.id.txtPlaca)
        txtMarca = findViewById(R.id.txtMarca)
        txtColor = findViewById(R.id.txtColor)
        txtNroAsientos = findViewById(R.id.txtNroAsientos)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtAnioFab = findViewById(R.id.txtAnioFab)
        btnRegistrar = findViewById(R.id.btnRegistrar)
    }

    fun registrar() {
        val auto = Auto(
            placa= txtPlaca.text.toString(),
            marca =txtMarca.text.toString(),
            color = txtColor.text.toString(),
            nroAsientos =  txtNroAsientos.text.toString(),
            precio =txtPrecio.text.toString(),
            anioFabricacion =  txtAnioFab.text.toString(),
        )
        autoService = AutoService(this)


        val autoExistente: Auto? = autoService.buscarAuto(auto.placa)
        if (autoExistente != null) {
            Utilidad.mostrarAlerta(this, "Error", "Esta placa ya está registrado.")
            return
        }

        val resultado = autoService.registrarAuto(auto)
        Utilidad.mostrarAlerta(this, "Info", resultado)
    }
}