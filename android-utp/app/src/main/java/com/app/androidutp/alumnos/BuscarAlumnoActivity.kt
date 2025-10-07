package com.app.androidutp.alumnos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.androidutp.R
import com.app.androidutp.Utilidad
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.toString

class BuscarAlumnoActivity : AppCompatActivity() {

    private lateinit var txtCodigo: EditText
    private lateinit var fieldNombre: TextView
    private lateinit var fieldEdad: TextView
    private lateinit var btnBuscar: Button
    private lateinit var btnAgregar: FloatingActionButton
    private lateinit var alumnoService: AlumnoService

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.semana_08) // Utilizar el layout de la semana 08

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        referenciar()

        btnBuscar.setOnClickListener {
            buscar()
        }

        btnAgregar.setOnClickListener {
            mostrarFormularioAgregar()
        }
    }

    fun referenciar() {
        txtCodigo = findViewById(R.id.txtCodigo)
        fieldNombre = findViewById(R.id.fieldNombre)
        fieldEdad = findViewById(R.id.fieldEdad)
        btnBuscar = findViewById(R.id.btnBuscar)
        btnAgregar = findViewById(R.id.btnAgregar)
    }

    fun buscar() {
        if (!validarFormulario()) {
            return
        }

        alumnoService = AlumnoService(this)

        val alumno: Alumno? = alumnoService.buscarAlumno(txtCodigo.toString())
        print(alumno)

        fieldNombre.text = alumno?.nombres.toString()
        fieldEdad.text = alumno?.edad.toString()
    }

    fun validarFormulario(): Boolean {
        val codigo = txtCodigo.text.toString().trim()

        if (codigo.isEmpty()) {
            Utilidad.mostrarAlerta(this, "Error", "El código no puede estar vacío.")
            return false
        }
        if (codigo.length < 4) {
            Utilidad.mostrarAlerta(this, "Error", "El código debe tener al menos 4 caracteres.")
            return false
        }
        if (!codigo.matches(Regex("^[a-zA-Z0-9]+$"))) {
            Utilidad.mostrarAlerta(this, "Error", "El código solo puede contener letras y números.")
            return false
        }

        return true
    }

    fun mostrarFormularioAgregar() {
        val intent = Intent(this, AgregarAlumnoActivity::class.java)
        startActivity(intent)
    }
}