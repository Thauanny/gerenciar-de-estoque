package com.example.primeiroapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem

import com.example.primeiroapp.R;
import com.example.primeiroapp.databinding.ActivityMenuBinding
import com.example.primeiroapp.databinding.ActivityProdutosBinding
import com.example.primeiroapp.model.ListAdapter
import com.example.primeiroapp.model.Produto

public class ProdutosActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityProdutosBinding
    private lateinit var produtos: ArrayList<Produto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater);
        setContentView(binding.root);


        val nomes = arrayOf("Andrielly", "Batista", "dos Santos");
        val codigos = arrayOf(12312312, 534532, 903849234);
        val descricaoes = arrayOf("desc 1", "desc 2", "desc 3");
        val quantidades = arrayOf(5, 2, 6);

        produtos = ArrayList()

        for (i in nomes.indices){
            val produto = Produto(nomes[i], codigos[i], descricaoes[i], quantidades[i])
            produtos.add(produto)
        }

        binding.produtos.isClickable = true;
        binding.produtos.adapter = ListAdapter(this, produtos)
        binding.produtos.setOnItemClickListener{ parent, view, position, id ->
            val nomeProduto = nomes[position]
            val codigoProduto = codigos[position]
            val descricaoProduto = descricaoes[position]
            val quantidadeProduto = quantidades[position]

           // TODO: DIALOG
        }

    }

}