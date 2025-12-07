package com.app.apkproductos.productos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.apkproductos.R
import com.app.apkproductos.movimientos.RegistrarMovimientoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductosActivity : AppCompatActivity() {
    private var listaProductos: List<Producto> = emptyList()
    private var adaptador: ProductoAdaptador = ProductoAdaptador()
    private lateinit var rvEstList: RecyclerView
    private lateinit var btnNuevo: FloatingActionButton
    private lateinit var btnBack: ImageButton
//    private lateinit var btnEditar: ImageButton
//    private lateinit var btnEliminar:  ImageButton

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
//        cargarProductos()
        regresar()
    }
    
    private fun referenciar() {
        rvEstList = findViewById(R.id.rvProductoList)
        btnBack = findViewById(R.id.btnBack)
//        btnEditar = findViewById(R.id.btnEditar)
//        btnEliminar = findViewById(R.id.btnEliminar)
    }
    
//    private fun cargarProductos() {
//        btnEditar.setOnClickListener {
//            startActivity(Intent(this, ProductosActivity::class.java))
//        }
//
//        btnEliminar.setOnClickListener {
//            startActivity(Intent(this, RegistrarMovimientoActivity::class.java))
//        }
//    }

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