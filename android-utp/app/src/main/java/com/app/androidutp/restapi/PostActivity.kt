package com.app.androidutp.restapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.app.androidutp.R
import com.app.androidutp.util.HttpService
import kotlinx.coroutines.launch

class PostActivity : AppCompatActivity() {
	private lateinit var tvIndex: TextView
	private lateinit var tvUserId: TextView
	private lateinit var tvId: TextView
	private lateinit var tvTitle: TextView
	private lateinit var txtBody: EditText
	private lateinit var btnAnterior: Button
	private lateinit var btnSiguiente: Button
	private lateinit var posts: List<Post>
	private var positionTemp: Int = 0
	// private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()


		// binding = ActivityMainBinding.inflate(layoutInflater)
		// setContentView(binding.root)

		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		referenciar()

		listarPosts()

		btnAnterior.setOnClickListener {
			anterior()
		}

		btnSiguiente.setOnClickListener {
			siguiente()
		}
	}

	private fun referenciar() {
		tvIndex = findViewById(R.id.tvIndex)
		tvUserId = findViewById(R.id.tvUserId)
		tvId = findViewById(R.id.tvId)
		tvTitle = findViewById(R.id.tvTitle)
		txtBody = findViewById(R.id.txtBody)
		btnAnterior = findViewById(R.id.btnAnterior)
		btnSiguiente = findViewById(R.id.btnSiguiente)
	}

	private fun listarPosts() {
		val service = HttpService.create<PostApi>()
		lifecycleScope.launch {
			posts = service.getPosts()
			setDataPost(positionTemp)
		}
	}

	private fun anterior() {
		if (positionTemp > 0) {
			positionTemp--
		}

		setDataPost(positionTemp)
	}

	private fun siguiente() {
		val lastIndex = posts.size - 1 // Índice del último elemento
		if (positionTemp < lastIndex) {
			positionTemp++
		}

		setDataPost(positionTemp)
	}

	private fun setDataPost(position: Int) {
		val post = posts[position]
		val lastIndex = posts.size - 1 // Índice del último elemento

		btnAnterior.isEnabled = position != 0
		btnSiguiente.isEnabled = position != lastIndex

		tvIndex.text = "${position + 1} / ${lastIndex + 1}"
		tvUserId.text = post.userId.toString()
		tvId.text = post.id.toString()
		tvTitle.text = post.title
		txtBody.setText(post.body)
	}
}