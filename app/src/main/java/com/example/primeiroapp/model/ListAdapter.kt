package com.example.primeiroapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.primeiroapp.R

class ListAdapter(private val produtos: List<Produto>) : RecyclerView.Adapter<ListAdapter.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(produtos[position])
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    // Substitua com os tipos de dados reais do seu item
    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeProduto: TextView = itemView.findViewById(R.id.nomeProduto)
        private val codigoProduto: TextView = itemView.findViewById(R.id.codigoProduto)
        private val quantidadeProduto: TextView = itemView.findViewById(R.id.quantidadeProduto)

        fun bind(produto: Produto) {
            nomeProduto.text = produto.nome
            codigoProduto.text = "Nº: ${produto.codigo}"
            quantidadeProduto.text = produto.quantidade.toString()
        }
    }
}
