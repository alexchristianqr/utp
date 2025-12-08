package com.app.apkproductos.reportes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.apkproductos.R
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.apkproductos.common.services.HttpService
import com.app.apkproductos.movimientos.MovimientoService
import kotlinx.coroutines.launch




class ReporteActivity : AppCompatActivity() {

	private lateinit var txtCodigoM: EditText
	private lateinit var btnBuscar: Button
	private lateinit var rvMovimientos: RecyclerView

	// 🔹 Aquí declaramos apiService correctamente
	private val apiService by lazy { HttpService.create<ApiService>() }


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
		rvMovimientos.adapter = ReporteAdapter(emptyList()) // Adapter inicial vacío

		configurarBotonBuscar()


	}


	private fun configurarBotonBuscar() {
		btnBuscar.setOnClickListener {
			val codigo = txtCodigoM.text.toString().toIntOrNull()
			if (codigo == null) {
				Toast.makeText(this, "Ingrese un código válido", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			lifecycleScope.launch {
				try {
					val response = HttpService.create<ApiService>().getMovimientos(codigo)

					if (response.isSuccessful) {
						val apiResponse = response.body()

						Log.d("ReporteActivity", "API Response completa: $apiResponse")

						val movimientos = apiResponse?.data?.movimientos ?: emptyList()

						if (movimientos.isEmpty()) {
							Toast.makeText(this@ReporteActivity, "No se encontraron movimientos", Toast.LENGTH_SHORT).show()
							rvMovimientos.adapter = ReporteAdapter(emptyList())
							return@launch
						}

						//val productoNombre = apiResponse.data?.producto?.nombre ?: "Desconocido"

						val productoNombre = apiResponse?.data?.producto?.nombre ?: "Desconocido"


						val listaReporte = movimientos.map { m ->
							MovimientoReporte(
								id = m.id,
								producto_id = m.producto_id,
								nombreProd = productoNombre, // ✔ ahora es String
								tipo = m.tipo,
								cantidad = m.cantidad,
								fecha = m.fecha,
								descripcion = m.descripcion ?: ""
							)
						}


						rvMovimientos.adapter = ReporteAdapter(listaReporte)

						// 🔹 Debug: imprimir lista
						listaReporte.forEach { Log.d("ReporteActivity", it.toString()) }

					} else {
						val errorBody = response.errorBody()?.string()
						Toast.makeText(this@ReporteActivity, "Error: $errorBody", Toast.LENGTH_SHORT).show()
					}
				} catch (e: Exception) {
					Toast.makeText(this@ReporteActivity, "Error de red: ${e.message}", Toast.LENGTH_LONG).show()
					Log.e("ReporteActivity", "Exception", e)
				}
			}
		}
	}


	// 🔹 Función temporal para probar
	private fun consultarMovimientos(productoId: Int) {
		Toast.makeText(this, "Consultar movimientos para ID: $productoId", Toast.LENGTH_SHORT).show()
	}





}//fin