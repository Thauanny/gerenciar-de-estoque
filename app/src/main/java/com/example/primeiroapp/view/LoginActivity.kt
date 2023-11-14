package com.example.primeiroapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primeiroapp.R
import com.example.primeiroapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var btn = findViewById<Button>(R.id.button_login);
        setClickListener(btn);

    }

    private fun setClickListener(btn: Button) {
        btn.setOnClickListener(View.OnClickListener {
            val nome = binding.editNome.text.toString();
            if (nome.isEmpty() ||  binding.editSenha.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "credenciais invalidas", Toast.LENGTH_SHORT)
                    .show();
            } else {
                Toast.makeText(applicationContext, "Login feito", Toast.LENGTH_SHORT).show();
                val i = Intent(this, MenuActivity::class.java);
                i.putExtra("nome", nome);
                startActivity(i);

            }
        })
    }

}