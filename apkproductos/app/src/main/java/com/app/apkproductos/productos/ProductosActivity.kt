package com.app.apkproductos.productos

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.apkproductos.R
import com.app.apkproductos.common.constants.GlobalApp
import com.app.apkproductos.common.services.HttpService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class ProductosActivity : AppCompatActivity() {
    private var listaProductos: List<Producto> = emptyList()
    private var adaptador: ProductoAdaptador = ProductoAdaptador()
    private lateinit var rvEstList: RecyclerView
    private lateinit var btnNuevo: FloatingActionButton
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.producto_listado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.producto_listado)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        referenciar()
        cargarProductos()
        regresar()
//        btnNuevo.setOnClickListener {
//            mostrarNuevo()
//        }
    }

    private fun referenciar() {
        rvEstList = findViewById(R.id.rvProductoList)
        btnBack = findViewById(R.id.btnBack)

        rvEstList.layoutManager = LinearLayoutManager(this)
        adaptador.setContext(this)

//        btnNuevo = findViewById(R.id.btnNuevo)
    }

    private fun cargarProductos() {
        HttpService.setBaseUrl(GlobalApp.PRODUCTO_BASE_URL)
        val service = HttpService.create<ProductoService>()

        lifecycleScope.launch {
            val response = service.cargarProductos()

            if (response.isSuccessful) {
                val productoResponse: ProductoResponse = response.body() as ProductoResponse
                Log.d("===", "Response: $productoResponse")

                productoResponse.let {
                    listaProductos = it.data
                    mostrarProductos()

                    for (producto in listaProductos) {
                        Log.d(
                            "===",
                            "Item: ${producto.id}: ${producto.nombre} ${producto.precio}"
                        )
                    }
                }
            } else {
                Log.e("===", "Error en la respuesta: ${response.code()}")
            }
        }
    }

    private fun mostrarProductos() {
        adaptador.setListaProductos(listaProductos)
        rvEstList.adapter = adaptador
    }
    
    private fun regresar(){
        btnBack.setOnClickListener {
            finish() // Finaliza la actividad actual y regresa a la anterior
        }
    }

//    private fun mostrarNuevo() {
//        val intent = Intent(this, EstudianteNuevoActivity::class.java)
//        startActivity(intent)
//    }
}