package com.example.primeiroapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroapp.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater);
        setContentView(binding.root);


        binding.cadastrarProduto.setOnClickListener{
            startActivity(Intent(this, CadastrarProdutoActivity::class.java));
        }

        binding.ListarProdutos.setOnClickListener{
            startActivity(Intent(this, ProdutosActivity::class.java));
        }
    }
}