package com.example.quizz.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizz.R
import com.example.quizz.entidades.Categoria
import kotlinx.android.synthetic.main.layout_categoria.view.*

class CategoriaAdapter(private var categorias: List<Categoria>): RecyclerView.Adapter<CategoriaAdapter.ResultViewHolder>() {
    override fun getItemViewType(position: Int) = R.layout.layout_categoria

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaAdapter.ResultViewHolder =
        ResultViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun getItemCount(): Int = categorias.size


    override fun onBindViewHolder(holder: CategoriaAdapter.ResultViewHolder, position: Int) =
        holder.preencherView(categorias[position])

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(categoria:Categoria) {

            itemView.btCategoria.text=categoria.name

        }

    }
}