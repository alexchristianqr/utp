package com.app.apkproductos.productos

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ProductosActivity : AppCompatActivity() {
    private var listaProductos: MutableList<Producto> = mutableListOf()

    private lateinit var adaptador: ProductoAdaptador
    private lateinit var rvProductoList: RecyclerView
    private lateinit var btnNuevo: FloatingActionButton
    private lateinit var btnBack: ImageButton
    
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
        
        /*
        adaptador = ProductoAdaptador { productoEditar -> // <- Aquí recibes el 'productomod'
            val intent = Intent(this, ModificarProductoActivity::class.java)
            intent.putExtra("PRODUCTO_EDITAR", productoEditar)
            editProductoLauncher.launch(intent) // <- Aquí se lanza correctamente esperando resultado
        }*/
        
        /*
        adaptador = ProductoAdaptador { productoEditar ->
            val intent = Intent(this, ModificarProductoActivity::class.java)
            intent.putExtra("PRODUCTO_EDITAR", productoEditar)
            editProductoLauncher.launch(intent)
        }*/
        adaptador = ProductoAdaptador(
            onEditarProducto = { productoEditar ->
                val intent = Intent(this, ModificarProductoActivity::class.java)
                intent.putExtra("PRODUCTO_EDITAR", productoEditar)
                editProductoLauncher.launch(intent)
            },
            onEliminar = { producto ->
                eliminarProducto(producto) // llamamos a tu función que hace la eliminación
            }
        )
        
        
        
        adaptador.setContext(this)
        rvProductoList.adapter = adaptador // se agrego esto
    }
    
    private fun eliminarProducto(producto: Producto) {
        mostrarDialogEliminar(producto) // Llama a la función que crea el modal oscuro
    }

    private fun mostrarDialogEliminar(producto: Producto) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_confirmar_eliminar)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvTitulo = dialog.findViewById<TextView>(R.id.tvTitulo)
        val tvMensaje = dialog.findViewById<TextView>(R.id.tvMensaje)
        val btnCancelar = dialog.findViewById<Button>(R.id.btnCancelar)
        val btnEliminar = dialog.findViewById<Button>(R.id.btnEliminar)

        tvMensaje.text = "¿Seguro que quieres eliminar '${producto.nombre}'?"

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        btnEliminar.setOnClickListener {
            dialog.dismiss()
            borrarProductoServidor(producto) // Llama a tu función que elimina del servidor
        }

        dialog.show()
    }

    private fun borrarProductoServidor(producto: Producto) {
        lifecycleScope.launch {
            try {
                val response = HttpService.create<ProductoService>().eliminarProducto(producto.id)
                if (response.isSuccessful && response.body()?.ok == true) {
                    val index = listaProductos.indexOf(producto)
                    listaProductos.remove(producto)
                    adaptador.setListaProductos(listaProductos)
                    Snackbar.make(
                        findViewById(R.id.listar_producto),
                        "Producto '${producto.nombre}' eliminado",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Log.i("EliminarProducto", "Producto eliminado: ${producto.id} - ${producto.nombre}")
                } else {
                    Log.e("EliminarProducto", "Error al eliminar: ${response.body()?.message}")
                    Toast.makeText(this@ProductosActivity, "Error al eliminar producto", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("EliminarProducto", "Excepción al eliminar producto: ${e.message}")
                Toast.makeText(this@ProductosActivity, "Error al eliminar producto", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private val nuevoProductoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val productoNuevo = result.data?.getSerializableExtra("producto") as? Producto
            productoNuevo?.let {
                listaProductos.add(0, it) // Agregar al inicio
                adaptador.setListaProductos(listaProductos) // Notificar adaptador
                rvProductoList.scrollToPosition(0) // Mover scroll al inicio
            }
        }
    }
    
    private val editProductoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val productoActualizado = result.data?.getSerializableExtra("PRODUCTO_ACTUALIZADO") as? Producto

            productoActualizado?.let { prod ->
                val index = listaProductos.indexOfFirst { it.id == prod.id }

                if (index >= 0) {
                    listaProductos[index] = prod
                    adaptador.setListaProductos(listaProductos)
                } else {
                    cargarProductos()
                }
            }
        }
    }

    private fun cargarProductos() {
        HttpService.setBaseUrl(GlobalApp.PRODUCTO_BASE_URL)
        val service = HttpService.create<ProductoService>()

        lifecycleScope.launch {
            try {
                val response = service.cargarProductos()
                if (response.isSuccessful) {

                    val productoResponse = response.body()
                    listaProductos.clear()
                    listaProductos.addAll(productoResponse!!.data)
                    adaptador.setListaProductos(listaProductos)

                } else {
                    Log.e("ProductosActivity", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ProductosActivity", "Error: ${e.message}")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        cargarProductos()   // 🔥 Recarga los datos cada vez que vuelves a la Activity
    }

    private fun acciones() {
        btnBack.setOnClickListener { finish() }

        btnNuevo.setOnClickListener {
            val intent = Intent(this, RegistrarProductoActivity::class.java)
            nuevoProductoLauncher.launch(intent)
        }
    }
}