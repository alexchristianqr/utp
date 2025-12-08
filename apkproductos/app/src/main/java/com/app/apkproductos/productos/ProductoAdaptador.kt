package com.app.apkproductos.productos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.apkproductos.R

class ProductoAdaptador : RecyclerView.Adapter<ProductoAdaptador.MiViewHolder>() {

    private var listaProductos: List<Producto> = emptyList()
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    fun setListaProductos(lista: List<Producto>) {
        this.listaProductos = lista
    }

    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var txtProductoNombre: TextView
        private lateinit var txtProductoDescripcion: TextView
        private lateinit var txtProductoPrecio: TextView
        private lateinit var txtProductoStock: TextView
        private lateinit var rvProductoList: RecyclerView
        private lateinit var btnEditar: ImageButton
        private lateinit var btnEliminar:  ImageButton

        init {
            referenciar(view)
        }

        private fun referenciar(view: View) {
            txtProductoNombre = view.findViewById(R.id.txtProductoNombre)
            txtProductoDescripcion = view.findViewById(R.id.txtProductoDescripcion)
            txtProductoPrecio = view.findViewById(R.id.txtProductoPrecio)
            txtProductoStock = view.findViewById(R.id.txtProductoStock)
            rvProductoList = view.findViewById(R.id.rvProductoList)
            btnEditar = view.findViewById(R.id.btnEditar)
            btnEliminar = view.findViewById(R.id.btnEliminar)
        }

        fun rellenarDatos(producto: Producto) {
            txtProductoNombre.text = producto.nombre.uppercase()
            txtProductoDescripcion.text = producto.descripcion
            txtProductoPrecio.text = producto.precio.toString()
            txtProductoStock.text = producto.stock.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.producto_fila, parent, false)
    )

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val item = listaProductos[position]
        holder.rellenarDatos(item)
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }

}