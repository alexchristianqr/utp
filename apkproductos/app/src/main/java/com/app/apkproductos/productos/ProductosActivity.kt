package com.app.apkproductos.productos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import com.app.apkproductos.movimientos.RegistrarMovimientoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class ProductosActivity : AppCompatActivity() {


    private lateinit var btnEditar: Button
    private lateinit var btnEliminar:  Button

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

    }


    private fun referenciar() {
        btnEditar = findViewById(R.id.btnMenuProducto)
        btnEliminar = findViewById(R.id.btnMenuMovimiento)

    }


    private fun cargarProductos() {
        btnEditar.setOnClickListener {
            startActivity(Intent(this, ProductosActivity::class.java))
        }

        btnEliminar.setOnClickListener {
            startActivity(Intent(this, RegistrarMovimientoActivity::class.java))
        }

    }

}