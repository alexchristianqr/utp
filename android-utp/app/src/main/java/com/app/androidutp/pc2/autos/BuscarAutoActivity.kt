package com.app.androidutp.pc2.autos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.androidutp.R
import com.app.androidutp.common.util.Utilidad
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class BuscarAutoActivity : AppCompatActivity() {
    private lateinit var txtBuscar: TextInputEditText
    private lateinit var tvPlaca: TextView
    private lateinit var tvMarca: TextView
    private lateinit var tvColor: TextView
    private lateinit var tvNroAsientos: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var tvAnioFab: TextView
    private lateinit var btnBuscar: Button
    private lateinit var btnAgregar: FloatingActionButton
    private lateinit var autoService: AutoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.pc2_buscar_auto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pc2_buscar_auto)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        referenciar()

        btnBuscar.setOnClickListener {
            buscar()
        }

        btnAgregar.setOnClickListener {
            agregar()
        }
    }

    fun referenciar(){
        txtBuscar = findViewById(R.id.txtBuscar)
        tvPlaca = findViewById(R.id.tvPlaca)
        tvMarca = findViewById(R.id.tvMarca)
        tvColor = findViewById(R.id.tvColor)
        tvNroAsientos = findViewById(R.id.tvNroAsientos)
        tvPrecio = findViewById(R.id.tvPrecio)
        tvAnioFab = findViewById(R.id.tvAnioFab)
        btnBuscar = findViewById(R.id.btnBuscar)
        btnAgregar = findViewById(R.id.btnAgregar)
    }

    fun buscar(){
        val placa = txtBuscar.text.toString()
        autoService = AutoService(this)
        val auto = autoService.buscarAuto(placa)

        if(auto == null){
            Utilidad.mostrarAlerta(this, "Error", "Vehiculo no encontrado")
            return
        }

        tvPlaca.text = auto.placa
        tvMarca.text = auto.marca
        tvColor.text = auto.color
        tvNroAsientos.text = auto.nroAsientos
        tvPrecio.text = auto.precio
        tvAnioFab.text = auto.anioFabricacion
    }

    fun agregar(){
        val intent = Intent(this, RegistrarAutoActivity::class.java)
        startActivity(intent)
    }
}