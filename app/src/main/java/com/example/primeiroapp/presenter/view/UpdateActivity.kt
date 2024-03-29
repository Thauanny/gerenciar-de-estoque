package com.example.primeiroapp.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.primeiroapp.R
import com.example.primeiroapp.data.database.DatabaseHelper
import com.example.primeiroapp.databinding.ActivityUpdateBinding
import com.google.android.material.checkbox.MaterialCheckBox

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    var notificar = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater);
        setContentView(binding.root);

        var btn = findViewById<Button>(R.id.updateProduto);
        setClickListener(btn);

        binding.limparUpdate.setOnClickListener {
            binding.codigoProduto.setText("");
            binding.descricaoProduto.setText("");
            binding.estoqueProduto.setText("");
            binding.nomeProduto.setText("");
        }

    }


    private fun setClickListener(btn: Button) {

        btn.setOnClickListener (View.OnClickListener {
            val nome_produto = binding.nomeProduto.text.toString();
            val descricao_produto = binding.descricaoProduto.text.toString();
            val cod_produto = binding.codigoProduto.text.toString();
            val quantidade_produto = binding.estoqueProduto.text.toString();
            val materialCheckBox = findViewById<MaterialCheckBox>(R.id.atualizar_notificar_produto)
            materialCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    notificar = 1;
                } else {
                    notificar = 0;
                }
            }


            try {
                val db =
                    DatabaseHelper(this);
                db.update(
                    nome_produto,
                    descricao_produto,
                    Integer.valueOf(quantidade_produto),
                    Integer.valueOf(cod_produto),
                    notificar
                );
                finish();

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Insira todas as informações", Toast.LENGTH_SHORT).show();

            }
        })
    }
}