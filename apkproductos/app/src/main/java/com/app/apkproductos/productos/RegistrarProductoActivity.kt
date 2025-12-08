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

class RegistrarProductoActivity : AppCompatActivity() {
	private lateinit var btnBack: ImageButton
	
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
	}
	
	private fun acciones() {
		btnBack.setOnClickListener {
			finish() // Finaliza la actividad actual y regresa a la anterior
		}
	}
}