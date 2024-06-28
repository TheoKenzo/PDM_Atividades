package com.example.trabalho_final_pdm.view

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabalho_final_pdm.R

class ClientListActivity : AppCompatActivity() {

    lateinit var BT_Voltar: Button
    lateinit var ET_IDCliente: Button
    lateinit var BT_Buscar: Button
    lateinit var LV: ListView
    lateinit var BT_Reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_client_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.BT_Reset_Tela05)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT_Voltar = findViewById(R.id.BT_Voltar_Tela03)
        ET_IDCliente = findViewById(R.id.ET_Busca_Tela03)
        BT_Buscar = findViewById(R.id.BT_Buscar_Tela03)
        LV = findViewById(R.id.LV_Tela03)
        BT_Reset = findViewById(R.id.BT_Reset_Tela03)

        BT_Voltar.setOnClickListener{
            this.finish()
        }
    }
}