package com.app.apkproductos.reportes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.apkproductos.R


import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.apkproductos.common.services.HttpService
import kotlinx.coroutines.launch


class ReporteActivity : AppCompatActivity() {

	private lateinit var txtCodigoM: EditText
	private lateinit var btnBuscar: Button
	private lateinit var rvMovimientos: RecyclerView

	// 🔹 Aquí declaramos apiService correctamente
	private val apiService by lazy {
		HttpService.create<ApiService>()
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()

		setContentView(R.layout.activity_reporte)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		// 🔹 Referencias a elementos del layout
		txtCodigoM = findViewById(R.id.txtCodigoM)
		btnBuscar = findViewById(R.id.btnBuscar)
		rvMovimientos = findViewById(R.id.rvMovimientos)

		// 🔹 Setup del RecyclerView
		rvMovimientos.layoutManager = LinearLayoutManager(this)

		btnBuscar.setOnClickListener {
			val codigo = txtCodigoM.text.toString()
			if (codigo.isNotEmpty()) {
				lifecycleScope.launch {
					try {
						// Llamada a la API
						val response = apiService.getMovimientos(codigo.toInt())

						if (response.isSuccessful) {
							val responseBody = response.body() // <- Aquí reemplazamos data por responseBody
							if (responseBody != null) {
								val movimientos = responseBody.entradas + responseBody.salidas
								val nombreProducto = responseBody.producto.nombre

								rvMovimientos.adapter = ReporteAdapter(
									movimientos.map { movimiento ->
										MovimientoReporte(
											id = movimiento.id,
											producto_id = movimiento.productoId, // según tu modelo
											nombreProd = nombreProducto,
											tipo = movimiento.tipo,
											cantidad = movimiento.cantidad,
											fecha = movimiento.fecha,
											descripcion = movimiento.descripcion
										)
									}
								)
							} else {
								Toast.makeText(this@ReporteActivity, "No hay datos disponibles", Toast.LENGTH_SHORT).show()
							}
						} else {
							Toast.makeText(this@ReporteActivity, "Error en la respuesta: ${response.code()}", Toast.LENGTH_SHORT).show()
						}
					} catch (e: Exception) {
						Toast.makeText(this@ReporteActivity, "Error de red: ${e.message}", Toast.LENGTH_LONG).show()
					}
				}
			} else {
				Toast.makeText(this, "Ingrese un código", Toast.LENGTH_SHORT).show()
			}
		}





	}

	// 🔹 Función temporal para probar
	private fun consultarMovimientos(productoId: Int) {
		Toast.makeText(this, "Consultar movimientos para ID: $productoId", Toast.LENGTH_SHORT).show()
	}





}//fin