package com.app.androidutp.alumnos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.androidutp.R
import com.app.androidutp.Utilidad
import com.google.android.material.textfield.TextInputEditText

class AgregarAlumnoActivity : AppCompatActivity() {
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

        setContentView(R.layout.semana_07) // Utilizar el layout de la semana 07

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
        if (!validarFormulario()) {
            return
        }

        alumnoService = AlumnoService(this)
        alumno = Alumno(
            codigo = txtCodigo.text.toString(),
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            edad = txtEdad.text.toString().toInt()
        )

        val rpta = alumnoService.registrarAlumno(alumno)
        print(rpta)

        resetearFormulario()

        tvRespuesta.text = rpta
    }

    fun validarFormulario(): Boolean {
        val codigo = txtCodigo.text.toString().trim()
        val nombres = txtNombres.text.toString().trim()
        val apellidos = txtApellidos.text.toString().trim()
        val edadStr = txtEdad.text.toString().trim()

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
        if (nombres.isEmpty()) {
            Utilidad.mostrarAlerta(this, "Error", "El nombre no puede estar vacío.")
            return false
        }
        if (nombres.length < 2) {
            Utilidad.mostrarAlerta(this, "Error", "El nombre debe tener al menos 2 caracteres.")
            return false
        }
        if (!nombres.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$"))) {
            Utilidad.mostrarAlerta(this, "Error", "El nombre solo puede contener letras.")
            return false
        }
        if (apellidos.isEmpty()) {
            Utilidad.mostrarAlerta(this, "Error", "El apellido no puede estar vacío.")
            return false
        }
        if (apellidos.length < 2) {
            Utilidad.mostrarAlerta(this, "Error", "El apellido debe tener al menos 2 caracteres.")
            return false
        }
        if (!apellidos.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$"))) {
            Utilidad.mostrarAlerta(this, "Error", "El apellido solo puede contener letras.")
            return false
        }
        if (edadStr.isEmpty()) {
            Utilidad.mostrarAlerta(this, "Error", "La edad no puede estar vacía.")
            return false
        }
        val edad = edadStr.toIntOrNull()
        if (edad == null) {
            Utilidad.mostrarAlerta(this, "Error", "La edad debe ser un número válido.")
            return false
        }
        if (edad < 16 || edad > 100) {
            Utilidad.mostrarAlerta(this, "Error", "La edad debe estar entre 16 y 100 años.")
            return false
        }
        return true
    }

    fun resetearFormulario() {
        txtCodigo.setText("")
        txtNombres.setText("")
        txtApellidos.setText("")
        txtEdad.setText("")
        tvRespuesta.text = "-"
    }
}