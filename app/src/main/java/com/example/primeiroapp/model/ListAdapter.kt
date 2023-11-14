package com.example.primeiroapp.model

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.primeiroapp.R

class ListAdapter(private val context: Activity, private val arrayList: ArrayList<Produto>) :
    ArrayAdapter<Produto>(context, R.layout.list_item, arrayList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item, null);

        val nomeProduto: TextView = view.findViewById(R.id.nomeProduto)
        val codigoProduto: TextView = view.findViewById(R.id.codigoProduto)


        nomeProduto.text = arrayList[position].nome
        codigoProduto.text = arrayList[position].codigo.toString()
        return view
    }
}