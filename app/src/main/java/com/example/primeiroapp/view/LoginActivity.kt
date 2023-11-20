package com.example.primeiroapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primeiroapp.R
import com.example.primeiroapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var savedNome = "";
    var savedSenha = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadLogin();

        var btn = findViewById<Button>(R.id.button_login);
        var esqueceuSenhaButton = findViewById<TextView>(R.id.esqueceuSenha);
        setClickListener(btn);

        esqueceuSenhaButton.setOnClickListener {
            finish();
            val i = Intent(this, CadastroLoginActivity::class.java);
            startActivity(i);
        }

    }

    private fun setClickListener(btn: Button) {
        btn.setOnClickListener(View.OnClickListener {
            val nome = binding.editNome.text.toString();


            if(savedNome.isNotEmpty() && savedSenha.isNotEmpty() ){

                if ( binding.editSenha.text.toString() != savedSenha || nome != savedNome) {
                    Toast.makeText(applicationContext, "Para alterar a senha vá para -> Esqueci minha senha", Toast.LENGTH_SHORT)
                        .show();
                } else {
                    Toast.makeText(applicationContext, "Login Feito com sucesso!", Toast.LENGTH_SHORT)
                        .show();
                    finish();
                    val i = Intent(this, MenuActivity::class.java);
                    startActivity(i);
                }

            }else{
                if (nome.isEmpty() ||  binding.editSenha.text.toString().isEmpty() || binding.editSenha.text.toString() != "admin" || nome != "admin") {
                    Toast.makeText(applicationContext, "Credenciais invalidas :( Esqueceu a sua senha?", Toast.LENGTH_SHORT)
                        .show();
                } else {
                    Toast.makeText(applicationContext, "Login Feito com sucesso!", Toast.LENGTH_SHORT)
                    finish();
                    val i = Intent(this, MenuActivity::class.java);
                    startActivity(i);

                }
            }

        })
    }


    private fun loadLogin(){

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        savedNome = sharedPreferences.getString("LoginName", "") ?: "";
        savedSenha = sharedPreferences.getString("LoginSenha", "") ?: "";

    }

}