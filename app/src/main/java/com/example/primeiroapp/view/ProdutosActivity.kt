package com.example.primeiroapp.view;

import ConstantesBancoDeDados
import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.example.primeiroapp.R;
import com.example.primeiroapp.data.DatabaseHelper
import com.example.primeiroapp.databinding.ActivityMenuBinding
import com.example.primeiroapp.databinding.ActivityProdutosBinding
import com.example.primeiroapp.model.ListAdapter
import com.example.primeiroapp.model.Produto

public class ProdutosActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProdutosBinding
    private lateinit var produtos: ArrayList<Produto>
    private var db = DatabaseHelper(this);
    private lateinit var nomes: ArrayList<String>;
    private lateinit var codigos: ArrayList<Int>;
    private lateinit var descricaoes: ArrayList<String>;
    private lateinit var quantidades: ArrayList<Int>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater);
        setContentView(binding.root);

        nomes = ArrayList<String>();
        codigos = ArrayList<Int>();
        descricaoes = ArrayList<String>();
        quantidades = ArrayList<Int>();
        produtos = ArrayList();

        storeDataInArrays();

        binding.produtos.isClickable = true;
        binding.produtos.adapter = ListAdapter(this, produtos)
        binding.produtos.setOnItemClickListener { parent, view, position, id ->

            val nomeProduto = nomes[position]
            val codigoProduto = codigos[position]
            val descricaoProduto = descricaoes[position]
            val quantidadeProduto = quantidades[position]

            exibirDialogoInformacoes(this,nomeProduto, quantidadeProduto.toString(), descricaoProduto, codigoProduto.toString());
        }

    }

    fun exibirDialogoInformacoes(
        context: Context,
        nome: String,
        quantidade: String,
        descricao: String,
        numero: String
    ) {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_produto, null)

        val textNome = dialogView.findViewById<TextView>(R.id.textNome)
        val textQuantidade = dialogView.findViewById<TextView>(R.id.textQuantidade)
        val textDescricao = dialogView.findViewById<TextView>(R.id.textDescricao)
        val textNumero = dialogView.findViewById<TextView>(R.id.textNumero)

        textNome.text = "Nome: $nome"
        textQuantidade.text = "Quantidade: $quantidade"
        textDescricao.text = "Descrição: $descricao"
        textNumero.text = "Número: $numero"

        dialogBuilder.setView(dialogView)
            .setPositiveButton("OK", null) // Adicione um botão "OK" ou outro conforme necessário

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    @SuppressLint("Range")
    private fun storeDataInArrays(){
        val cursor  = db.readAllData();
        if(cursor.count == 0){
            Toast.makeText(this, "Sem Registros.", Toast.LENGTH_SHORT).show();
            finish();

        }else{
            while (cursor.moveToNext()) {
                val nome = cursor.getString(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_NOME));
                val desc =cursor.getString(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_DESCRICAO));
                val cod = cursor.getInt(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_CODIGO));
                val quant = cursor.getInt(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_QUANTIDADE));

                nomes.add(nome);
                descricaoes.add(desc);
                codigos.add(cod);
                quantidades.add(quant);

                val produto = Produto(
                    nome = nome,
                    codigo = cod,
                    descricao = desc,
                    quantidade = quant,
                )
                produtos.add(produto)
            }
        }
        cursor.close();
    }

}

