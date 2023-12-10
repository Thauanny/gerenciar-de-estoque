package com.example.primeiroapp.view

import RecyclerItemClickListener
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.primeiroapp.R
import com.example.primeiroapp.data.DatabaseHelper
import com.example.primeiroapp.databinding.ActivityProdutosBinding
import com.example.primeiroapp.model.ConstantesBancoDeDados
import com.example.primeiroapp.model.ListAdapter
import com.example.primeiroapp.model.Produto

class ProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProdutosBinding
    private lateinit var produtos: ArrayList<Produto>
    private var db = DatabaseHelper(this)
    private lateinit var nomes: ArrayList<String>
    private lateinit var codigos: ArrayList<Int>
    private lateinit var descricaoes: ArrayList<String>
    private lateinit var quantidades: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nomes = ArrayList()
        codigos = ArrayList()
        descricaoes = ArrayList()
        quantidades = ArrayList()
        produtos = ArrayList()

        storeDataInArrays()

        val layoutManager = LinearLayoutManager(this)
        binding.produtos.layoutManager = layoutManager

        val adapter = ListAdapter(produtos)
        binding.produtos.adapter = adapter

        binding.produtos.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                binding.produtos,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val produto = produtos[position]
                        exibirDialogoInformacoes(
                            this@ProdutosActivity,
                            produto.nome,
                            produto.quantidade.toString(),
                            produto.descricao,
                            produto.codigo.toString()
                        )

                    }

                    override fun onLongItemClick(view: View, position: Int) {
                        TODO("Not yet implemented")
                    }
                })
        )

    }

    fun exibirDialogoInformacoes(
        context: Context,
        nome: String,
        quantidade: String,
        descricao: String,
        numero: String
    ) {
        val inflater = LayoutInflater.from(context)
        val cardView = inflater.inflate(R.layout.dialog_produto, null)

        val textNome = cardView.findViewById<TextView>(R.id.textNome)
        val textQuantidade = cardView.findViewById<TextView>(R.id.textQuantidade)
        val textDescricao = cardView.findViewById<TextView>(R.id.textDescricao)
        val textNumero = cardView.findViewById<TextView>(R.id.textNumero)

        textNome.text = "Nome: $nome"
        textQuantidade.text = "Quantidade: $quantidade"
        textDescricao.text = "Descrição: $descricao"
        textNumero.text = "Número: $numero"

        // Criar e exibir AlertDialog
        AlertDialog.Builder(context)
            .setView(cardView)
            .setPositiveButton("OK", null)
            .show()
    }

    @SuppressLint("Range")
    private fun storeDataInArrays() {
        val cursor = db.read()
        if (cursor.count == 0) {
            Toast.makeText(this, "Sem Registros.", Toast.LENGTH_SHORT).show()
            finish()

        } else {
            while (cursor.moveToNext()) {
                val nome =
                    cursor.getString(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_NOME))
                val desc =
                    cursor.getString(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_DESCRICAO))
                val cod = cursor.getInt(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_CODIGO))
                val quant =
                    cursor.getInt(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_QUANTIDADE))

                nomes.add(nome)
                descricaoes.add(desc)
                codigos.add(cod)
                quantidades.add(quant)

                val produto = Produto(
                    nome = nome,
                    codigo = cod,
                    descricao = desc,
                    quantidade = quant,
                )
                produtos.add(produto)
            }
        }
        cursor.close()
    }
}
