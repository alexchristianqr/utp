package com.app.apkproductos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.apkproductos.movimientos.RegistrarMovimientoActivity
import com.app.apkproductos.productos.ProductosActivity
import com.app.apkproductos.reportes.ReporteActivity

class MenuActivity : AppCompatActivity() {
	private lateinit var btnMenuProducto: Button
	private lateinit var btnMenuMovimiento: Button
	private lateinit var btnMenuReporte: Button
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_menu)
		
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		
		referenciar()
		configurarBotones()
	}
	
	private fun referenciar() {
		btnMenuProducto = findViewById(R.id.btnMenuProducto)
		btnMenuMovimiento = findViewById(R.id.btnMenuMovimiento)
		btnMenuReporte = findViewById(R.id.btnMenuReporte)
	}
	
	private fun configurarBotones() {
		btnMenuProducto.setOnClickListener {
			startActivity(Intent(this, ProductosActivity::class.java))
		}
		
		btnMenuMovimiento.setOnClickListener {
			startActivity(Intent(this, RegistrarMovimientoActivity::class.java))
		}
		
		btnMenuReporte.setOnClickListener {
			startActivity(Intent(this, ReporteActivity::class.java))
		}
	}
	
}