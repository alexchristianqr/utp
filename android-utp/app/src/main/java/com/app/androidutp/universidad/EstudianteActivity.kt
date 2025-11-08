package com.app.androidutp.universidad

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.app.androidutp.R
import com.app.androidutp.common.constants.GlobalApp
import com.app.androidutp.common.services.HttpService
import com.app.androidutp.posts.PostApi
import com.app.androidutp.universidad.entidad.Estudiante
import com.app.androidutp.universidad.entidad.EstudianteResponse
import com.app.androidutp.universidad.service.EstudianteService
import kotlinx.coroutines.launch

class EstudianteActivity : AppCompatActivity() {
    private var listaEstudiantes: List<Estudiante> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.estudiante_listado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.estudiante_listado)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cargarEstudiantes()
    }

    private fun cargarEstudiantes() {
        HttpService.setBaseUrl(GlobalApp.ESTUDIANTE_BASE_URL)
        val service = HttpService.create<EstudianteService>()

        lifecycleScope.launch {
            val response = service.cargarEstudiantes()

            if (response.isSuccessful) {
                val estudianteResponse: EstudianteResponse = response.body() as EstudianteResponse
                Log.d("===", "Response: $estudianteResponse")

                estudianteResponse.let {
                    listaEstudiantes = it.data
//                     Log.d("===", listaEstudiantes.toString())

                    for (estudiante in listaEstudiantes) {
                        Log.d(
                            "===",
                            "Item: ${estudiante.car_id}: ${estudiante.alu_nombres} ${estudiante.alu_apellidos}"
                        )
                    }
                }
            } else {
                Log.e("===", "Error en la respuesta: ${response.code()}")
            }
        }
    }
}