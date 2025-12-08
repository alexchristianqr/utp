package com.app.apkproductos.productos


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.apkproductos.R


import android.widget.ImageView
import com.app.apkproductos.common.constants.GlobalApp
import com.bumptech.glide.Glide



class ProductoAdaptador : RecyclerView.Adapter<ProductoAdaptador.MiViewHolder>() {

    private var listaProductos: List<Producto> = emptyList()
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    fun setListaProductos(lista: List<Producto>) {
        this.listaProductos = lista
        notifyDataSetChanged()
    }

    inner class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtProductoNombre: TextView = view.findViewById(R.id.txtProductoNombre)
        private val txtProductoDescripcion: TextView = view.findViewById(R.id.txtProductoDescripcion)
        private val txtProductoPrecio: TextView = view.findViewById(R.id.txtProductoPrecio)
        private val txtProductoStock: TextView = view.findViewById(R.id.txtProductoStock)
        private val imgProducto: ImageView = view.findViewById(R.id.imgProducto)
        private val btnEditar: ImageButton = view.findViewById(R.id.btnEditar)
        private val btnEliminar: ImageButton = view.findViewById(R.id.btnEliminar)

        fun rellenarDatos(producto: Producto) {
            txtProductoNombre.text = producto.nombre.uppercase()
            txtProductoDescripcion.text = producto.descripcion
            txtProductoPrecio.text = producto.precio.toString()
            txtProductoStock.text = producto.stock.toString()

            // Cargar imagen con Glide
            if (!producto.imagen.isNullOrEmpty()) {
                Glide.with(context)
                    .load(GlobalApp.PRODUCTO_BASE_URL + producto.imagen)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(imgProducto)
            } else {
                imgProducto.setImageResource(R.drawable.ic_launcher_foreground)
            }

            // Aquí puedes agregar listeners para editar/eliminar si quieres
            // btnEditar.setOnClickListener { ... }
            // btnEliminar.setOnClickListener { ... }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.producto_fila, parent, false)
        return MiViewHolder(view)
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val item = listaProductos[position]
        holder.rellenarDatos(item)
    }

    override fun getItemCount(): Int = listaProductos.size

}