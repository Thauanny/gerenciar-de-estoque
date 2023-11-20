package com.example.primeiroapp.view

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.primeiroapp.R
import com.example.primeiroapp.data.DatabaseHelper
import com.example.primeiroapp.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cadastrarProduto.setOnClickListener {
            startActivity(Intent(this, CadastrarProdutoActivity::class.java))
        }

        binding.ListarProdutos.setOnClickListener {
            startActivity(Intent(this, ProdutosActivity::class.java))
        }

        binding.atualizarProdutos.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
        }

        binding.deletarProduto.setOnClickListener {
            startActivity(Intent(this, DeletarActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_logout, menu)

        if (menu != null) {
            // Obter o item do menu
            val logoutItem: MenuItem = menu.findItem(R.id.action_logout)

            // Aplicar filtro de cor branco ao ícone
            val whiteIcon = ContextCompat.getDrawable(this, R.drawable.ic_logout)
            whiteIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
            logoutItem.icon = whiteIcon
        }


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                finish();
                val i = Intent(this, LoginActivity::class.java);
                startActivity(i);
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}