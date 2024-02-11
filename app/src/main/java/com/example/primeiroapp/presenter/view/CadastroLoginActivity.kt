package com.example.primeiroapp.presenter.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.primeiroapp.databinding.ActivityCadastroLoginBinding

class CadastroLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCadastro.setOnClickListener {
            saveLogin();
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish();
                val i = Intent(this, LoginActivity::class.java);
                startActivity(i);
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun saveLogin(){
        val nome = binding.editNome.text.toString();
        val senha =  binding.editSenha.text.toString();

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("LoginName", nome);
            putString("LoginSenha", senha);
        }.apply();

        Toast.makeText(applicationContext, "Cadastro feito com sucesso! :)", Toast.LENGTH_SHORT).show();

        finish();

        val i = Intent(this, LoginActivity::class.java);
        startActivity(i);

    }
}