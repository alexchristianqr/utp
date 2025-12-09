package com.app.apkproductos.productos

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
import java.io.FileOutputStream
import java.io.InputStream

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

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imagenUri = it
            imgProductoFoto.setImageURI(it)
        }
    }

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

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProductoCategoria.adapter = adapter

        // Cargar producto
        producto = intent.getSerializableExtra("PRODUCTO_EDITAR") as? Productomodificar
        producto?.let { p ->
            Glide.with(this)
                .load(GlobalApp.PRODUCTO_BASE_URL + (p.imagen ?: ""))
                .centerCrop()
                .into(imgProductoFoto)

            txtProductoNombre.setText(p.nombre)
            txtProductoDescripcion.setText(p.descripcion)
            tvStockValue.setText(p.stock.toString())
            txtProductoPrecio.setText(p.precio.toString())

            val index = categorias.indexOf(p.categoria)
            spProductoCategoria.setSelection(if (index >= 0) index else 0)
        }

        fabCamera.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        btnActualizar.setOnClickListener {
            actualizarProducto()
        }
    }

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

        val requestMap = HashMap<String, RequestBody>().apply {
            put("nombre", nombre.toRequestBody())
            put("descripcion", descripcion.toRequestBody())
            put("precio", precio.toRequestBody())
            put("stock", stock.toRequestBody())
            put("categoria", categoria.toRequestBody())
        }

        // Construir imagen solo si se seleccionó una nueva
        var imagenPart: MultipartBody.Part? = null
        imagenUri?.let {
            val file = uriToFile(it)
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
                        AlertDialog.Builder(this@ModificarProductoActivity)
                            .setTitle("Éxito")
                            .setMessage("Producto actualizado exitosamente")
                            .setPositiveButton("OK") { dialog, _ ->


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

    private fun uriToFile(uri: Uri): File {
        val fileName = getFileName(uri)
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val file = File(cacheDir, fileName)
        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        return file
    }

    private fun getFileName(uri: Uri): String {
        var name = "temp_image"
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val idx = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (idx >= 0) name = it.getString(idx)
            }
        }
        return name
    }
}
