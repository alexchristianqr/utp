package com.app.androidutp.universidad

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private var adaptador: Adaptador = Adaptador()

    //    private lateinit var txtEstCodigo: TextView
//    private lateinit var txtEstNombreApellido: TextView
//    private lateinit var txtEstEdad: TextView
//    private lateinit var txtEstCarrera: TextView
//    private lateinit var imgEstGenero: ImageView
    private lateinit var rvEstList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.estudiante_listado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.estudiante_listado)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        referenciar()
        cargarEstudiantes()
    }

    private fun referenciar() {
//        txtEstCodigo = findViewById(R.id.txtEstCodigo)
//        txtEstNombreApellido = findViewById(R.id.txtEstNombreApellido)
//        txtEstEdad = findViewById(R.id.txtEstEdad)
//        txtEstCarrera = findViewById(R.id.txtEstCarrera)
//        imgEstGenero = findViewById(R.id.imgEstGenero)
        rvEstList = findViewById(R.id.rvEstList)

        // Setear el layout manager para el RecyclerView
        rvEstList.layoutManager = LinearLayoutManager(this)
        adaptador.setContext(this)
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
                    mostrarEstudiantes()
//                    adaptador.setListaEstudiantes(listaEstudiantes)
//                    rvEstList.adapter = adaptador


//                    val carreras = listOf("Ingeniería de Sistemas", "Ingeniería Industrial", "Administración de Empresas", "Contabilidad", "Derecho")

                    for (estudiante in listaEstudiantes) {

//                        txtEstCodigo.text = estudiante.alu_codigo
//                        txtEstEdad.text = estudiante.alu_edad.toString()
//                        txtEstCarrera.text = carreras[estudiante.car_id]
//                        txtEstNombreApellido.text = "${estudiante.alu_nombres.uppercase()} ${estudiante.alu_apellidos.uppercase()}"

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

    private fun mostrarEstudiantes(){
        adaptador.setListaEstudiantes(listaEstudiantes)
        rvEstList.adapter = adaptador
    }
}