package com.app.androidutp.universidad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.androidutp.R
import com.app.androidutp.universidad.entidad.Estudiante

class Adaptador : RecyclerView.Adapter<Adaptador.MiViewHolder>() {

    private var listaEstudiantes: List<Estudiante> = emptyList()
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    fun setListaEstudiantes(lista: List<Estudiante>) {
        this.listaEstudiantes = lista
    }

    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var txtEstCodigo: TextView
        private lateinit var txtEstNombreApellido: TextView
        private lateinit var txtEstEdad: TextView
        private lateinit var txtEstCarrera: TextView
        private lateinit var imgEstGenero: ImageView
        private lateinit var rvEstList: RecyclerView

        init {
            referenciar(view)
        }

        private fun referenciar(view: View) {
            txtEstCodigo = view.findViewById(R.id.txtProductoDescripcion)
            txtEstNombreApellido = view.findViewById(R.id.txtProductoNombre)
            txtEstEdad = view.findViewById(R.id.txtProductoPrecio)
            txtEstCarrera = view.findViewById(R.id.txtProductoStock)
            imgEstGenero = view.findViewById(R.id.imgEstGenero)
            rvEstList = view.findViewById(R.id.rvEstList)
        }

        fun rellenarDatos(estudiante: Estudiante) {
            val carreras = listOf(
                "Ingeniería de Sistemas",
                "Ingeniería Industrial",
                "Administración de Empresas",
                "Contabilidad",
                "Derecho"
            )

            txtEstNombreApellido.text =
                "${estudiante.alu_nombres.uppercase()} ${estudiante.alu_apellidos.uppercase()}"
            txtEstCodigo.text = estudiante.alu_codigo
            txtEstEdad.text = estudiante.alu_edad.toString()
            txtEstCarrera.text = estudiante.car_nombre
            if (estudiante.alu_genero == "M") {
                imgEstGenero.setImageResource(R.drawable.masculino)
            } else {
                imgEstGenero.setImageResource(R.drawable.femenino)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fila, parent, false)
    )

    override fun onBindViewHolder(holder: Adaptador.MiViewHolder, position: Int) {
        val item = listaEstudiantes[position]
        holder.rellenarDatos(item)
    }

    override fun getItemCount(): Int {
        return listaEstudiantes.size
    }

}