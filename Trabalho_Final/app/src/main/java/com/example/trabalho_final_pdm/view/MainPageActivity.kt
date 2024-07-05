package com.example.trabalho_final_pdm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabalho_final_pdm.R

class MainPageActivity : AppCompatActivity() {

    lateinit var BT_Clientes: Button
    lateinit var BT_Produtos: Button
    lateinit var BT_Pedidos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.BT_Reset_Tela05)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT_Clientes = findViewById(R.id.BT_Clientes_Tela01)
        BT_Produtos = findViewById(R.id.BT_Produtos_Tela01)
        BT_Pedidos = findViewById(R.id.BT_Pedidos_Tela01)

        BT_Clientes.setOnClickListener{
            val intent = Intent(this, ClientActivity::class.java)
            startActivity(intent)
        }

        BT_Produtos.setOnClickListener{
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }

        BT_Pedidos.setOnClickListener{
            val intent = Intent(this, PedidoActivity::class.java)
            startActivity(intent)
        }
    }
}