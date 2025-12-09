package com.app.apkproductos.productos

import android.content.Intent
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

//import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts


class ProductosActivity : AppCompatActivity() {

    /*
    private var listaProductos: MutableList<Producto> = mutableListOf()
    //private var listaProductos: List<Producto> = emptyList()
    private var adaptador: ProductoAdaptador = ProductoAdaptador()
    private lateinit var rvProductoList: RecyclerView
    private lateinit var btnNuevo: FloatingActionButton
    private lateinit var btnBack: ImageButton*/


    private var listaProductos: MutableList<Producto> = mutableListOf()
    private var adaptador: ProductoAdaptador = ProductoAdaptador()
    private lateinit var rvProductoList: RecyclerView
    private lateinit var btnNuevo: FloatingActionButton
    private lateinit var btnBack: ImageButton


    /*
    private val nuevoProductoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val productoNuevo = result.data?.getSerializableExtra("producto") as? Producto
            productoNuevo?.let {
                listaProductos.add(0, it) // agregar al inicio
                adaptador.setListaProductos(listaProductos)
                rvProductoList.scrollToPosition(0)
            }
        }
    }
*/


    private val nuevoProductoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val productoNuevo = result.data?.getSerializableExtra("producto") as? Producto
            productoNuevo?.let {
                listaProductos.add(0, it) // Agregar al inicio
                //listaProductos.add(it) // agrega al final
                adaptador.setListaProductos(listaProductos) // Notificar adaptador
                rvProductoList.scrollToPosition(0) // Mover scroll al inicio
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listar_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listar_producto)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        referenciar()
        cargarProductos()
        acciones()
    }
    
    private fun referenciar() {
        rvProductoList = findViewById(R.id.rvProductoList)
        btnBack = findViewById(R.id.btnBack)
        btnNuevo = findViewById(R.id.btnNuevo)
        rvProductoList.layoutManager = LinearLayoutManager(this)
        adaptador.setContext(this)
        rvProductoList.adapter = adaptador // se agrego esto
    }

    /*
    private fun cargarProductos() {
        HttpService.setBaseUrl(GlobalApp.PRODUCTO_BASE_URL)
        val service = HttpService.create<ProductoService>()

        lifecycleScope.launch {
            val response = service.cargarProductos()
            if (response.isSuccessful) {
                val productoResponse: ProductoResponse = response.body() as ProductoResponse
                listaProductos.clear()
                listaProductos.addAll(productoResponse.data)
                adaptador.setListaProductos(listaProductos)
            } else {
                Log.e("===", "Error en la respuesta: ${response.code()}")
            }
        }
    }*/

    private fun cargarProductos() {
        HttpService.setBaseUrl(GlobalApp.PRODUCTO_BASE_URL)
        val service = HttpService.create<ProductoService>()

        lifecycleScope.launch {
            val response = service.cargarProductos()
            if (response.isSuccessful) {
                val productoResponse: ProductoResponse = response.body()!!
                listaProductos.clear()
                listaProductos.addAll(productoResponse.data)
                adaptador.setListaProductos(listaProductos)
            } else {
                Log.e("ProductosActivity", "Error en la respuesta: ${response.code()}")
            }
        }
    }


    private fun mostrarProductos() {
        adaptador.setListaProductos(listaProductos)
        rvProductoList.adapter = adaptador
    }

    private fun acciones() {
        btnBack.setOnClickListener { finish() }

        btnNuevo.setOnClickListener {
            val intent = Intent(this, RegistrarProductoActivity::class.java)
            nuevoProductoLauncher.launch(intent)
        }
    }


}//fin