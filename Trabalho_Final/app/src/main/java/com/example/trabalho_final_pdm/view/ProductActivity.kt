package com.example.trabalho_final_pdm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabalho_final_pdm.R

class ProductActivity : AppCompatActivity() {

    lateinit var BT_Voltar: Button
    lateinit var BT_ListaBusca: Button
    lateinit var ET_IDProduto: EditText
    lateinit var ET_TipoDeGrao: EditText
    lateinit var ET_PontoDaTorra: EditText
    lateinit var ET_Valor: EditText
    lateinit var Switch_Blend: Switch
    lateinit var BT_Deletar: Button
    lateinit var BT_Alterar: Button
    lateinit var BT_Inserir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.BT_Reset_Tela05)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT_Voltar = findViewById(R.id.BT_Voltar_Tela04)
        BT_ListaBusca = findViewById(R.id.BT_Listar_Tela04)
        ET_TipoDeGrao = findViewById(R.id.ET_Tipo_De_Grao_Tela04)
        ET_PontoDaTorra = findViewById(R.id.ET_Ponto_Da_Torra_Tela04)
        ET_Valor = findViewById(R.id.ETN_Valor_Tela04)
        Switch_Blend = findViewById(R.id.SWITCH_Blend_Tela04)
        BT_Deletar = findViewById(R.id.BT_Deletar_Tela04)
        BT_Alterar = findViewById(R.id.BT_Alterar_Tela04)
        BT_Inserir = findViewById(R.id.BT_Inserir_Tela04)

        BT_Voltar.setOnClickListener{
            this.finish()
        }

        BT_ListaBusca.setOnClickListener{
            Intent(this, ProductListActivity::class.java).let{
                startActivity(it)
            }
        }
    }
}