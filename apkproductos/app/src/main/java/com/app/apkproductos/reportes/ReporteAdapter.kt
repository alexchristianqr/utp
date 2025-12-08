package com.app.apkproductos.reportes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.apkproductos.R


class ReporteAdapter(
    private val movimientos: List<MovimientoReporte>
) : RecyclerView.Adapter<ReporteAdapter.MovimientoViewHolder>() {

    inner class MovimientoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTipoMovimiento: TextView = itemView.findViewById(R.id.tvTipoMovimiento)
        val tvIdProducto: TextView = itemView.findViewById(R.id.tvIdProducto)
        val nombreProd: TextView = itemView.findViewById(R.id.NombreProd)
        val tvCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reporte, parent, false)
        return MovimientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovimientoViewHolder, position: Int) {
        val movimiento = movimientos[position]

        holder.tvTipoMovimiento.text = movimiento.tipo
        holder.tvIdProducto.text = movimiento.producto_id.toString()
        holder.nombreProd.text = movimiento.nombreProd
        holder.tvCantidad.text = movimiento.cantidad.toString()
        holder.tvFecha.text = movimiento.fecha
        holder.tvDescripcion.text = movimiento.descripcion
    }

    override fun getItemCount(): Int = movimientos.size
}