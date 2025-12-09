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
import androidx.appcompat.app.AlertDialog

class ModificarProductoActivity : AppCompatActivity() {


    private lateinit var imgProductoFoto: ImageView
    private lateinit var fabCamera: FloatingActionButton
    private lateinit var txtProductoNombre: EditText
    private lateinit var txtProductoDescripcion: EditText
    private lateinit var tvStockValue: EditText
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
            //tvStockValue.text = p.stock.toString()
            tvStockValue.setText(p.stock.toString())
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
    }*/

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

        // Log de datos antes de enviar
        android.util.Log.d(
            "ModificarProducto",
            "=== ACTUALIZAR PRODUCTO ===\n" +
                    "ID: ${p.id}\nNombre: $nombre\nDescripción: $descripcion\nStock: $stock\nPrecio: $precio\nCategoría: $categoria\nImagen seleccionada: ${imagenUri != null}"
        )

        // Construir requestMap con stock incluido
        val requestMap = HashMap<String, RequestBody>().apply {
            put("nombre", nombre.toRequestBody())
            put("descripcion", descripcion.toRequestBody())
            put("precio", precio.toRequestBody())
            put("stock", stock.toRequestBody())
            put("categoria", categoria.toRequestBody())
        }

        // Imagen opcional
        var imagenPart: MultipartBody.Part? = null
        imagenUri?.let {
            val file = File(getRealPathFromURI(it))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            imagenPart = MultipartBody.Part.createFormData("imagen", file.name, requestFile)
            android.util.Log.d("ModificarProducto", "Imagen enviada: ${file.absolutePath}")
        }

        val api = HttpService.retrofit.create(ProductoService::class.java)

        /*
        lifecycleScope.launch {
            try {
                val response = api.actualizarProducto(p.id, requestMap, imagenPart)

                android.util.Log.d("ModificarProducto", "Código HTTP: ${response.code()}")
                android.util.Log.d("ModificarProducto", "Respuesta raw: ${response.raw()}")

                if (response.isSuccessful) {
                    val productoActualizado = response.body()?.data
                    android.util.Log.d("ModificarProducto", "Respuesta body: $productoActualizado")
                    if (productoActualizado != null) {
                        val data = Intent().apply {
                            putExtra("PRODUCTO_ACTUALIZADO", productoActualizado as java.io.Serializable)
                        }
                        setResult(Activity.RESULT_OK, data)
                        finish()
                    } else {
                        Toast.makeText(this@ModificarProductoActivity, "Error: body vacío", Toast.LENGTH_LONG).show()
                        android.util.Log.e("ModificarProducto", "Body de respuesta es null")
                    }
                } else {
                    // Leer body de error si existe
                    val errorBody = response.errorBody()?.string()
                    android.util.Log.e("ModificarProducto", "Error HTTP: ${response.code()}, body: $errorBody")
                    Toast.makeText(this@ModificarProductoActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                // Log completo del stacktrace
                e.printStackTrace()
                Toast.makeText(this@ModificarProductoActivity, "Excepción: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }*/


        lifecycleScope.launch {
            try {
                val response = api.actualizarProducto(p.id, requestMap, imagenPart)

                android.util.Log.d("ModificarProducto", "Código HTTP: ${response.code()}")
                android.util.Log.d("ModificarProducto", "Respuesta raw: ${response.raw()}")

                if (response.isSuccessful) {
                    val productoActualizado = response.body()?.data
                    android.util.Log.d("ModificarProducto", "Respuesta body: $productoActualizado")

                    if (productoActualizado != null) {
                        // Mostrar diálogo de éxito
                        AlertDialog.Builder(this@ModificarProductoActivity)
                            .setTitle("Éxito")
                            .setMessage("Producto actualizado exitosamente")
                            .setPositiveButton("OK") { dialog, _ ->
                                // Cuando presione OK, devolver resultado y cerrar
                                val data = Intent().apply {
                                    putExtra("PRODUCTO_ACTUALIZADO", productoActualizado as java.io.Serializable)
                                }
                                setResult(Activity.RESULT_OK, data)
                                dialog.dismiss()
                                finish()
                            }
                            .setCancelable(false)
                            .show()
                    } else {
                        Toast.makeText(this@ModificarProductoActivity, "Error: body vacío", Toast.LENGTH_LONG).show()
                        android.util.Log.e("ModificarProducto", "Body de respuesta es null")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    android.util.Log.e("ModificarProducto", "Error HTTP: ${response.code()}, body: $errorBody")
                    Toast.makeText(this@ModificarProductoActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
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
