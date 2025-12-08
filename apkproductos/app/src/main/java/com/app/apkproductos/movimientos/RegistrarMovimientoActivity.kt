package com.app.apkproductos.movimientos


//librerias

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.app.apkproductos.R
import com.app.apkproductos.common.constants.GlobalApp
import com.app.apkproductos.common.services.HttpService
import com.app.apkproductos.productos.ProductoService
import kotlinx.coroutines.launch

class RegistrarMovimientoActivity : AppCompatActivity() {
	private lateinit var txtCodigo: EditText
	private lateinit var btnBuscar: Button

	private lateinit var txtNombre: TextView
	private lateinit var txtStockActual: TextView

	private lateinit var spTipoMovimiento: Spinner
	private lateinit var txtStockMovimiento: EditText
	private lateinit var btnRegistrarMovimiento: Button
	private lateinit var btnBack: ImageButton

	private var productoSeleccionado: ProductoMovimiento? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_registrar_movimiento)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		referenciar()
		cargarSpn()
		configurarEventos()
		regresar()

	}

	private fun referenciar() {
		txtCodigo = findViewById(R.id.txtCodigo)
		btnBuscar = findViewById(R.id.btnBuscar)

		txtNombre = findViewById(R.id.txtNombre)
		txtStockActual = findViewById(R.id.txtStockActual)

		spTipoMovimiento = findViewById(R.id.spTipoMovimiento)
		txtStockMovimiento = findViewById(R.id.txtStockMovimiento)
		btnRegistrarMovimiento = findViewById(R.id.btnRegistrarMovimiento)
		btnBack = findViewById(R.id.btnBack2)
	}

	private fun cargarSpn() {
		val items = listOf("Seleccione", "ENTRADA", "SALIDA")
		val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
		spTipoMovimiento.adapter = adapter
		spTipoMovimiento.setSelection(0) // "Seleccione" por defecto
	}

	private fun configurarEventos() {

		/// === BUSCAR PRODUCTO ==== ///
		btnBuscar.setOnClickListener {
			val codigoInt = txtCodigo.text.toString().toIntOrNull()
			if (codigoInt == null) {
				Toast.makeText(this, "Ingrese un código válido", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			buscarProductoPorId(codigoInt)
		}

		/// === REGISTRAR MOVIMIENTO ==== ///
		btnRegistrarMovimiento.setOnClickListener {

			if (productoSeleccionado == null) {
				Toast.makeText(this, "Busque un producto primero", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			val cantidad = txtStockMovimiento.text.toString().toIntOrNull()
			if (cantidad == null || cantidad <= 0) {
				Toast.makeText(this, "Ingrese cantidad válida", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			val tipo = spTipoMovimiento.selectedItem.toString()
			if (tipo == "Seleccione") {
				Toast.makeText(this, "Seleccione tipo de movimiento", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			registrarMovimiento(tipo, cantidad)
		}
	}

	private fun buscarProductoPorId(productoId: Int) {

		HttpService.setBaseUrl(GlobalApp.PRODUCTO_BASE_URL)
		val service = HttpService.create<ProductoService>()

		lifecycleScope.launch {

			val response = service.obtenerProductoPorId(productoId)

			if (response.isSuccessful) {

				val producto = response.body()?.data

				if (producto != null) {

					// 🔥 ARREGLO PRINCIPAL: cargar ID REAL
					productoSeleccionado = ProductoMovimiento(
						id = producto.id,     // <--- ANTES tomaba 0
						nombre = producto.nombre,
						stock = producto.stock
					)

					txtNombre.text = productoSeleccionado!!.nombre
					txtStockActual.text = productoSeleccionado!!.stock.toString()

				} else {
					Toast.makeText(this@RegistrarMovimientoActivity, "Producto no encontrado", Toast.LENGTH_SHORT).show()
				}

			} else {
				Toast.makeText(this@RegistrarMovimientoActivity, "Error al buscar", Toast.LENGTH_SHORT).show()
			}
		}
	}

	private fun registrarMovimiento(tipo: String, cantidad: Int) {
		val producto = productoSeleccionado ?: run {
			Toast.makeText(this, "Seleccione un producto primero", Toast.LENGTH_SHORT).show()
			return
		}

		// Validar ID real
		if (producto.id <= 0) {
			Toast.makeText(this, "ID de producto inválido", Toast.LENGTH_SHORT).show()
			Log.d("RegistrarMovimiento", "Producto seleccionado con ID inválido: $producto")
			return
		}

		val body = MovimientoRequest(
			producto_id = producto.id,
			tipo = tipo,
			cantidad = cantidad,
			descripcion = "Movimiento registrado desde app"
		)

		Log.d("RegistrarMovimiento", "Body enviado: $body")

		val service = HttpService.create<MovimientoService>()

		lifecycleScope.launch {
			try {
				val response = service.registrarMovimiento(body)

				if (response.isSuccessful) {
					val nuevoStock =
						(response.body()?.data as? Map<*, *>)?.get("nuevoStock") as? Int
							?: (producto.stock + if (tipo == "ENTRADA") cantidad else -cantidad)

					producto.stock = nuevoStock
					txtStockActual.text = nuevoStock.toString()

					// Mostrar diálogo de registro exitoso
					androidx.appcompat.app.AlertDialog.Builder(this@RegistrarMovimientoActivity)
						.setTitle("Registro exitoso")
						.setMessage("El movimiento se registró correctamente.")
						.setPositiveButton("Aceptar") { dialog, _ ->
							dialog.dismiss()
						}
						.show()

					// Limpiar campos y spinner
					txtCodigo.text.clear()
					txtNombre.text = ""
					txtStockActual.text = ""
					txtStockMovimiento.text.clear()
					spTipoMovimiento.setSelection(0)

					// Resetear producto seleccionado
					productoSeleccionado = null

				} else {
					val errorBody = response.errorBody()?.string()
					Log.d("RegistrarMovimiento", "Error ${response.code()}: $errorBody")
					Toast.makeText(this@RegistrarMovimientoActivity, "Error al registrar: $errorBody", Toast.LENGTH_SHORT).show()
				}

			} catch (e: Exception) {
				Toast.makeText(this@RegistrarMovimientoActivity, "Error de red: ${e.message}", Toast.LENGTH_LONG).show()
				Log.e("RegistrarMovimiento", "Exception: ", e)
			}
		}
	}
	
	private fun regresar(){
		btnBack.setOnClickListener {
			finish() // Finaliza la actividad actual y regresa a la anterior
		}
	}
}