package com.app.androidutp.universidad

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.app.androidutp.R
import com.app.androidutp.common.constants.GlobalApp
import com.app.androidutp.common.services.HttpService
import com.app.androidutp.common.util.Utilidad
import com.app.androidutp.universidad.entidad.Carrera
import com.app.androidutp.universidad.entidad.CarreraResponse
import com.app.androidutp.universidad.entidad.Estudiante
import com.app.androidutp.universidad.entidad.EstudianteResponse
import com.app.androidutp.universidad.service.EstudianteService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class EstudianteNuevoActivity : AppCompatActivity() {

    private var listaCarreras: List<Carrera> = emptyList()
    private lateinit var spinGenero: Spinner
    private lateinit var spinCarrera: Spinner
    private lateinit var txtEstudianteCodigo: EditText
    private lateinit var txtEstudianteNombre: EditText
    private lateinit var txtEstudianteApellido: EditText
    private lateinit var txtEstudianteEdad: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.estudiante_registrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.estudiante_registrar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        referenciar()
        mostrarGeneros()
        cargarCarreras()

//        btnGuardar.setOnClickListener {
//            registrarEstudiante()
//        }
    }

    private fun referenciar() {
        spinGenero = findViewById(R.id.spinGenero)
        spinCarrera = findViewById(R.id.spinCarrera)
        txtEstudianteCodigo = findViewById(R.id.txtEstudianteCodigo)
        txtEstudianteNombre = findViewById(R.id.txtEstudianteNombre)
        txtEstudianteApellido = findViewById(R.id.txtEstudianteApellido)
        txtEstudianteEdad = findViewById(R.id.txtEstudianteEdad)
        btnGuardar = findViewById(R.id.btnGuardar)
    }

    private fun cargarCarreras() {
        HttpService.setBaseUrl(GlobalApp.ESTUDIANTE_BASE_URL)
        val service = HttpService.create<EstudianteService>()

        lifecycleScope.launch {
            val response = service.cargarCarreras()

            if (response.isSuccessful) {
                val carreraResponse: CarreraResponse = response.body() as CarreraResponse
                Log.d("===", "Response: $carreraResponse")

                carreraResponse.let {
                    listaCarreras = it.data
                    mostrarCarreras()
                }
            } else {
                Log.e("===", "Error en la respuesta: ${response.code()}")
            }
        }
    }

    private fun mostrarGeneros() {
        val listaGeneros = listOf("Masculino", "Femenino")

        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, listaGeneros
        )
        spinGenero.adapter = adaptador
    }

    private fun mostrarCarreras() {
        val listaNombreCarreras = listaCarreras.map { it.car_nombre }

        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, listaNombreCarreras
        )
        spinCarrera.adapter = adaptador
    }

    private fun registrarEstudiante() {
        val codigo = txtEstudianteCodigo.toString()
        val nombres = txtEstudianteNombre.toString()
        val apellidos = txtEstudianteApellido.toString()
        val edadText = txtEstudianteEdad.toString()
        val genero = spinGenero.selectedItem.toString()
        val carreraPosition = spinCarrera.selectedItemPosition
        val car_id = if (carreraPosition >= 0) listaCarreras[carreraPosition].car_id else null

        val edad = edadText.toIntOrNull() ?: 0

        val nuevoEstudiante = Estudiante(
            alu_codigo = codigo,
            alu_nombres = nombres,
            alu_apellidos = apellidos,
            alu_edad = edad,
            alu_genero = genero,
            car_id = car_id
        )

        Log.d("===", "Nuevo Estudiante: $nuevoEstudiante")

        HttpService.setBaseUrl(GlobalApp.ESTUDIANTE_BASE_URL)
        val service = HttpService.create<EstudianteService>()

        lifecycleScope.launch {
            val response = service.registrarEstudiante()

            if (response.isSuccessful) {
                val estudianteResponse: EstudianteResponse = response.body() as EstudianteResponse
                Log.d("===", "Response: $estudianteResponse")

                estudianteResponse.let {
                    // Manejar la respuesta del registro si es necesario
                    Utilidad.mostrarAlerta(
                        this@EstudianteNuevoActivity,
                        "Registro Exitoso",
                        "El estudiante ha sido registrado correctamente."
                    )
                }
            } else {
                Log.e("===", "Error en la respuesta: ${response.code()}")
            }
        }
    }
}