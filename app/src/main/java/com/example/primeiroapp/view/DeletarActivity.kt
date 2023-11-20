package com.example.primeiroapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primeiroapp.data.DatabaseHelper
import com.example.primeiroapp.databinding.ActivityDeletarBinding

class DeletarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeletarBinding
    private var db = DatabaseHelper(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletarBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.buttonDeletar.setOnClickListener {
            delete();
        }

        binding.limparCodigoDeletar.setOnClickListener {
            binding.inputCodigoDeletar.setText("");
        }
    }

    private fun delete(){
        val codigo = binding.inputCodigoDeletar.text.toString();
        if(codigo.isNotEmpty()){
            db.remove(codigo);
            finish();
        }else{
            Toast.makeText(this, "Insira o codigo do produto", Toast.LENGTH_SHORT).show();
        }


    }
}