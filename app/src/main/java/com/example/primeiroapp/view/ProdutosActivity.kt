package com.example.primeiroapp.view;

import ConstantesBancoDeDados
import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem
import android.widget.Toast

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
    private lateinit var codigos: ArrayList<String>;
    private lateinit var descricaoes: ArrayList<String>;
    private lateinit var quantidades: ArrayList<String>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater);
        setContentView(binding.root);


        nomes = ArrayList<String>();
        codigos = ArrayList<String>();
        descricaoes = ArrayList<String>();
        quantidades = ArrayList<String>();
        produtos = ArrayList();

        storeDataInArrays();




        binding.produtos.isClickable = true;
        binding.produtos.adapter = ListAdapter(this, produtos)
        binding.produtos.setOnItemClickListener { parent, view, position, id ->
            val nomeProduto = nomes[position]
            val codigoProduto = codigos[position]
            val descricaoProduto = descricaoes[position]
            val quantidadeProduto = quantidades[position]

            // TODO: DIALOG
        }

    }

    @SuppressLint("Range")
    private fun storeDataInArrays(){
        val cursor  = db.readAllData();
        if(cursor.count == 0){
            Toast.makeText(this, "Sem Registros.", Toast.LENGTH_SHORT).show();
            finish();

        }else{
            while (cursor.moveToNext()) {
                val produto = Produto(
                    nome = cursor.getString(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_NOME)),
                    codigo = cursor.getInt(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_CODIGO)),
                    descricao = cursor.getString(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_DESCRICAO)),
                    quantidade = cursor.getInt(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_QUANTIDADE))
                )
                produtos.add(produto)
            }
        }
    }

}

