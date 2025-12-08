package com.app.apkproductos.productos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.apkproductos.R


import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.app.apkproductos.common.services.HttpService
import kotlinx.coroutines.launch



import android.net.Uri


import androidx.activity.result.contract.ActivityResultContracts

import java.io.InputStream


import java.io.File


import java.util.*


import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.OutputStream


class RegistrarProductoActivity : AppCompatActivity() {
	private lateinit var btnBack: ImageButton

	private lateinit var btnGuardar: Button

	private lateinit var txtProductoNombre: EditText
	private lateinit var txtProductoDescripcion: EditText
	private lateinit var txtProductoStock: EditText
	private lateinit var txtProductoPrecio: EditText
	private lateinit var spProductoCategoria: Spinner
	private lateinit var imgProductoFoto: ImageView

	private var selectedImageUri: Uri? = null
	private val api = HttpService.retrofit.create(ProductoService::class.java)

	// Launcher para galería
	private val galleryLauncher =
		registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
			uri?.let {
				imgProductoFoto.setImageURI(it)
				selectedImageUri = it
			}
		}

	// ⚠️ 1. DEFINE EL ARREGLO DE CATEGORÍAS AQUÍ ⚠️
	private val categorias = arrayOf(
		"Seleccione...", // <-- Opción por defecto
		"componentes",
		"perifericos",
		"Monitores",
		"PC completa",
		"equipo de trabajo",
		"redes",
		"licencia",
		"accesorios"
	)


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_registrar_producto)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registrar_producto)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		
		referenciar()

		configurarSpinner()
		acciones()
	}

	private fun referenciar() {
		btnBack = findViewById(R.id.btnBack3)
		btnGuardar = findViewById(R.id.btnGuardar)
		imgProductoFoto = findViewById(R.id.imgProductoFoto)



		txtProductoNombre = findViewById(R.id.txtProductoNombre)
		txtProductoDescripcion = findViewById(R.id.txtProductoDescripcion)
		txtProductoStock = findViewById(R.id.txtProductoStock)
		txtProductoPrecio = findViewById(R.id.txtProductoPrecio)
		spProductoCategoria = findViewById(R.id.spProductoCategoria)
	}


	private fun configurarSpinner() {
		// Configuramos el ArrayAdapter como antes
		val adapter = ArrayAdapter(
			this,
			android.R.layout.simple_spinner_item,
			categorias
		)

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		spProductoCategoria.adapter = adapter
	}

	private fun acciones() {
		btnBack.setOnClickListener { finish() }
		btnGuardar.setOnClickListener { registrarProducto() }
		imgProductoFoto.setOnClickListener { abrirGaleria() }
	}

	private fun abrirGaleria() {
		galleryLauncher.launch("image/*")
	}



	private fun registrarProducto() {
		val nombre = txtProductoNombre.text.toString()
		val descripcion = txtProductoDescripcion.text.toString()
		val stock = txtProductoStock.text.toString().toIntOrNull() ?: 0
		val precio = txtProductoPrecio.text.toString().toFloatOrNull() ?: 0f
		val categoria = spProductoCategoria.selectedItem.toString()

		if (nombre.isBlank() || descripcion.isBlank() || categoria == "Seleccione...") {
			Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
			return
		}

		lifecycleScope.launch {
			try {
				val multipartImage: MultipartBody.Part? = selectedImageUri?.let { uri ->
					val file = createTempFileFromUri(uri)
					val reqFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
					MultipartBody.Part.createFormData("imagen", file.name, reqFile)
				}

				val requestNombre = nombre.toRequestBody("text/plain".toMediaTypeOrNull())
				val requestDescripcion = descripcion.toRequestBody("text/plain".toMediaTypeOrNull())
				val requestPrecio = precio.toString().toRequestBody("text/plain".toMediaTypeOrNull())
				val requestStock = stock.toString().toRequestBody("text/plain".toMediaTypeOrNull())
				val requestCategoria = categoria.toRequestBody("text/plain".toMediaTypeOrNull())

				val response = api.registrarProductoMultipart(
					nombre = requestNombre,
					descripcion = requestDescripcion,
					precio = requestPrecio,
					stock = requestStock,
					categoria = requestCategoria,
					imagen = multipartImage
				)

				if (response.isSuccessful) {
					val body = response.body()!!
					Toast.makeText(
						this@RegistrarProductoActivity,
						"Registrado! ID = ${body.data.producto_id}",
						Toast.LENGTH_LONG
					).show()
					finish()
				} else {
					Toast.makeText(
						this@RegistrarProductoActivity,
						"Error API: ${response.code()}",
						Toast.LENGTH_LONG
					).show()
				}

			} catch (e: Exception) {
				Toast.makeText(
					this@RegistrarProductoActivity,
					"Error: ${e.message}",
					Toast.LENGTH_LONG
				).show()
			}
		}
	}

	// Función para crear un archivo temporal desde Uri
	private fun createTempFileFromUri(uri: Uri): File {
		val inputStream: InputStream? = contentResolver.openInputStream(uri)
		val file = File(cacheDir, "temp_${System.currentTimeMillis()}.jpg")
		val outputStream: OutputStream = file.outputStream()
		inputStream?.copyTo(outputStream)
		inputStream?.close()
		outputStream.close()
		return file
	}

}