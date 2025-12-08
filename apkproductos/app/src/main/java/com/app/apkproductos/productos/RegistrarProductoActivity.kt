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

import android.app.Activity

import android.net.Uri

import android.util.Base64
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream

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

		val imagenBase64 = selectedImageUri?.let { uri ->
			contentResolver.openInputStream(uri)?.use { inputStream ->
				val bytes = inputStream.readBytes()
				Base64.encodeToString(bytes, Base64.DEFAULT)
			}
		}

		val request = RegistrarProductoRequest(
			nombre = nombre,
			descripcion = descripcion,
			precio = precio,
			stock = stock,
			categoria = categoria,
			imagen = imagenBase64
		)

		lifecycleScope.launch {
			try {
				val response = api.registrarProducto(request)

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


}