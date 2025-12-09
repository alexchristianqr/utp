package com.app.apkproductos.productos

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.app.apkproductos.R
import com.app.apkproductos.common.constants.GlobalApp
import com.app.apkproductos.common.services.HttpService
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ModificarProductoActivity : AppCompatActivity() {

    private lateinit var imgProductoFoto: ImageView
    private lateinit var fabCamera: FloatingActionButton
    private lateinit var txtProductoNombre: EditText
    private lateinit var txtProductoDescripcion: EditText
    private lateinit var tvStockValue: TextView
    private lateinit var txtProductoPrecio: EditText


    private lateinit var spProductoCategoria: Spinner
    private lateinit var btnActualizar: Button

    private var imagenUri: Uri? = null
    private var producto: Productomodificar? = null

    private val PICK_IMAGE = 200

    // ======= ARREGLO DE CATEGORÍAS =======
    private val categorias = arrayOf(
        "Seleccione...",
        "componentes",
        "perifericos",
        "Monitores",
        "PC completa",
        "equipo de trabajo",
        "redes",
        "licencia",
        "accesorios"
    )


    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_producto)

        imgProductoFoto = findViewById(R.id.imgProductoFoto)
        fabCamera = findViewById(R.id.fabCamera)
        txtProductoNombre = findViewById(R.id.txtProductoNombre)
        txtProductoDescripcion = findViewById(R.id.txtProductoDescripcion)
        tvStockValue = findViewById(R.id.tvStockValue)
        txtProductoPrecio = findViewById(R.id.txtProductoPrecio)
        //txtProductoCantidad = findViewById(R.id.txtProductoCantidad)
        spProductoCategoria = findViewById(R.id.spProductoCategoria)
        btnActualizar = findViewById(R.id.btnActualizar)

        // ======= RECIBIR PRODUCTO =======
        producto = intent.getSerializableExtra("PRODUCTO_EDITAR") as? Productomodificar

        producto?.let { p ->
            // Mostrar imagen
            if (!p.imagen.isNullOrEmpty()) {
                Glide.with(this)
                    .load(GlobalApp.PRODUCTO_BASE_URL + p.imagen)
                    .centerCrop()
                    .into(imgProductoFoto)
            }

            txtProductoNombre.setText(p.nombre)
            txtProductoDescripcion.setText(p.descripcion)
            tvStockValue.text = p.stock.toString()
            txtProductoPrecio.setText(p.precio.toString())
            //txtProductoCantidad.setText(p.cantidad.toString())

            // Si usas lista de categorías:
            // spProductoCategoria.setSelection( índice según p.categoria )
        }

        // ======= BOTÓN PARA CAMBIAR IMAGEN =======
        fabCamera.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        // ======= BOTÓN ACTUALIZAR =======
        btnActualizar.setOnClickListener {
            actualizarProducto()
        }
    }

    // =============================== //
    //      RESULTADO DE IMAGEN       //
    // =============================== //
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imagenUri = data?.data
            imgProductoFoto.setImageURI(imagenUri)
        }
    }

    // =============================== //
    //     ACTUALIZAR PRODUCTO API    //
    // =============================== //
    private fun actualizarProducto() {

        val p = producto ?: return

        val nombre = txtProductoNombre.text.toString()
        val descripcion = txtProductoDescripcion.text.toString()
        val precio = txtProductoPrecio.text.toString()
        //val cantidad = txtProductoCantidad.text.toString()
        val categoria = "General"

        val requestMap = HashMap<String, RequestBody>().apply {
            put("nombre", nombre.toRequestBody())
            put("descripcion", descripcion.toRequestBody())
            put("precio", precio.toRequestBody())
            //put("cantidad", cantidad.toRequestBody())
            put("categoria", categoria.toRequestBody())
        }

        var imagenPart: MultipartBody.Part? = null
        imagenUri?.let {
            val file = File(getRealPathFromURI(it))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            imagenPart = MultipartBody.Part.createFormData("imagen", file.name, requestFile)
        }

        val api = HttpService.retrofit.create(ProductoService::class.java)

        lifecycleScope.launch {
            try {
                val response = api.actualizarProducto(p.id, requestMap, imagenPart)
                if (response.isSuccessful) {
                    val productoActualizado = response.body()?.data
                    if (productoActualizado != null) {
                        val data = Intent().apply {
                            putExtra("PRODUCTO_ACTUALIZADO", productoActualizado as java.io.Serializable)
                        }
                        setResult(Activity.RESULT_OK, data)
                        finish()
                    } else {
                        Toast.makeText(this@ModificarProductoActivity, "Error al actualizar", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@ModificarProductoActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ModificarProductoActivity, "Excepción: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    // =============================== //
    //      HELPER FUNCTIONS          //
    // =============================== //
    private fun String.toRequestBody(): RequestBody =
        RequestBody.create("text/plain".toMediaTypeOrNull(), this)

    private fun getRealPathFromURI(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val idx = cursor?.getColumnIndex("_data") ?: -1
        val path = cursor?.getString(idx)
        cursor?.close()
        return path ?: ""
    }*/


     */
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_producto)

        imgProductoFoto = findViewById(R.id.imgProductoFoto)
        fabCamera = findViewById(R.id.fabCamera)
        txtProductoNombre = findViewById(R.id.txtProductoNombre)
        txtProductoDescripcion = findViewById(R.id.txtProductoDescripcion)
        tvStockValue = findViewById(R.id.tvStockValue)
        txtProductoPrecio = findViewById(R.id.txtProductoPrecio)
        spProductoCategoria = findViewById(R.id.spProductoCategoria)
        btnActualizar = findViewById(R.id.btnActualizar)

        // ======= CONFIGURAR SPINNER =======
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categorias
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProductoCategoria.adapter = adapter

        // ======= RECIBIR PRODUCTO =======
        producto = intent.getSerializableExtra("PRODUCTO_EDITAR") as? Productomodificar
        producto?.let { p ->
            // Mostrar imagen
            if (!p.imagen.isNullOrEmpty()) {
                Glide.with(this)
                    .load(GlobalApp.PRODUCTO_BASE_URL + p.imagen)
                    .centerCrop()
                    .into(imgProductoFoto)
            }

            txtProductoNombre.setText(p.nombre)
            txtProductoDescripcion.setText(p.descripcion)
            tvStockValue.text = p.stock.toString()
            txtProductoPrecio.setText(p.precio.toString())

            // Seleccionar categoría en el Spinner
            val index = categorias.indexOf(p.categoria)
            spProductoCategoria.setSelection(if (index >= 0) index else 0)
        }

        // ======= BOTÓN PARA CAMBIAR IMAGEN =======
        fabCamera.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        // ======= BOTÓN ACTUALIZAR =======
        btnActualizar.setOnClickListener {
            actualizarProducto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imagenUri = data?.data
            imgProductoFoto.setImageURI(imagenUri)
        }
    }

    /*
    private fun actualizarProducto() {
        val p = producto ?: return

        val nombre = txtProductoNombre.text.toString()
        val descripcion = txtProductoDescripcion.text.toString()
        val stock = tvStockValue.text.toString()
        val precio = txtProductoPrecio.text.toString()
        val categoria = spProductoCategoria.selectedItem.toString()

        if (nombre.isBlank() || descripcion.isBlank() || categoria == "Seleccione...") {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Log de datos a actualizar
        android.util.Log.d(
            "ModificarProducto",
            "Actualizando -> Nombre: $nombre, Descripción: $descripcion, stock: $stock,Precio: $precio, Categoría: $categoria, Imagen: ${imagenUri != null}"
        )

        val requestMap = HashMap<String, RequestBody>().apply {
            put("nombre", nombre.toRequestBody())
            put("descripcion", descripcion.toRequestBody())
            put("precio", precio.toRequestBody())
            put("stock", stock.toRequestBody()) // <-- Aquí
            put("categoria", categoria.toRequestBody())
        }

        var imagenPart: MultipartBody.Part? = null
        imagenUri?.let {
            val file = File(getRealPathFromURI(it))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            imagenPart = MultipartBody.Part.createFormData("imagen", file.name, requestFile)
        }

        val api = HttpService.retrofit.create(ProductoService::class.java)

        lifecycleScope.launch {
            try {
                val response = api.actualizarProducto(p.id, requestMap, imagenPart)
                if (response.isSuccessful) {
                    val productoActualizado = response.body()?.data
                    if (productoActualizado != null) {
                        val data = Intent().apply {
                            putExtra("PRODUCTO_ACTUALIZADO", productoActualizado as java.io.Serializable)
                        }
                        setResult(Activity.RESULT_OK, data)
                        finish()
                    } else {
                        Toast.makeText(this@ModificarProductoActivity, "Error al actualizar", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@ModificarProductoActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ModificarProductoActivity, "Excepción: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
*/

     */


    private fun actualizarProducto() {
        val p = producto ?: return

        val nombre = txtProductoNombre.text.toString()
        val descripcion = txtProductoDescripcion.text.toString()
        val stock = tvStockValue.text.toString()
        val precio = txtProductoPrecio.text.toString()
        val categoria = spProductoCategoria.selectedItem.toString()

        if (nombre.isBlank() || descripcion.isBlank() || categoria == "Seleccione...") {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Log de datos a actualizar
        android.util.Log.d(
            "ModificarProducto",
            "Actualizando -> Nombre: $nombre, Descripción: $descripcion, Stock: $stock, Precio: $precio, Categoría: $categoria, Imagen: ${imagenUri != null}"
        )

        // Construir requestMap con stock incluido
        val requestMap = HashMap<String, RequestBody>().apply {
            put("nombre", nombre.toRequestBody())
            put("descripcion", descripcion.toRequestBody())
            put("precio", precio.toRequestBody())
            put("stock", stock.toRequestBody()) // <-- Stock agregado
            put("categoria", categoria.toRequestBody())
        }

        // Imagen opcional
        var imagenPart: MultipartBody.Part? = null
        imagenUri?.let {
            val file = File(getRealPathFromURI(it))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            imagenPart = MultipartBody.Part.createFormData("imagen", file.name, requestFile)
        }

        val api = HttpService.retrofit.create(ProductoService::class.java)

        lifecycleScope.launch {
            try {
                val response = api.actualizarProducto(p.id, requestMap, imagenPart)
                if (response.isSuccessful) {
                    val productoActualizado = response.body()?.data
                    if (productoActualizado != null) {
                        val data = Intent().apply {
                            putExtra("PRODUCTO_ACTUALIZADO", productoActualizado as java.io.Serializable)
                        }
                        setResult(Activity.RESULT_OK, data)
                        finish()
                    } else {
                        Toast.makeText(this@ModificarProductoActivity, "Error al actualizar", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@ModificarProductoActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ModificarProductoActivity, "Excepción: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }



    private fun String.toRequestBody(): RequestBody =
        RequestBody.create("text/plain".toMediaTypeOrNull(), this)

    private fun getRealPathFromURI(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val idx = cursor?.getColumnIndex("_data") ?: -1
        val path = cursor?.getString(idx)
        cursor?.close()
        return path ?: ""
    }



}
