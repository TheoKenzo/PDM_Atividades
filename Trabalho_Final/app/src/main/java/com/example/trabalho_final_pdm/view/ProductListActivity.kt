package com.example.trabalho_final_pdm.view

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabalho_final_pdm.R
import com.example.trabalho_final_pdm.controller.ProdutoController
import com.example.trabalho_final_pdm.model.Produto

class ProductListActivity : AppCompatActivity() {

    lateinit var BT_Voltar: Button
    lateinit var ET_IDProduto: EditText
    lateinit var BT_Buscar: Button
    lateinit var LV: ListView
    lateinit var BT_Reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.BT_Reset_Tela05)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT_Voltar = findViewById(R.id.BT_Voltar_Tela05)
        ET_IDProduto = findViewById(R.id.ET_Busca_Tela05)
        BT_Buscar = findViewById(R.id.BT_Buscar_Tela05)
        LV = findViewById(R.id.LV_Tela05)
        BT_Reset = findViewById(R.id.BT_Reset_Tela05)

        val produtoController = ProdutoController()

        var listaProdutos: ArrayList<Produto>? = null

        produtoController.getAllProdutos { produtos ->
            if (produtos.isNotEmpty()) {
                listaProdutos = produtos
                LV.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produtos)
            } else {
                listaProdutos = null
                Toast.makeText(this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        BT_Voltar.setOnClickListener{
            this.finish()
        }

        BT_Buscar.setOnClickListener{
            if(ET_IDProduto.text.isNotEmpty()){
                produtoController.getProdutoById(ET_IDProduto.text.toString()) { produtos ->
                    if (produtos.isNotEmpty()) {
                        listaProdutos = produtos
                        LV.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produtos)
                    } else {
                        listaProdutos = null
                        Toast.makeText(this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        LV.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
            val selectedItem = listaProdutos?.get(position)

            if (selectedItem != null) {
                ET_IDProduto.setText(selectedItem.id_produto)
            }
        }

        BT_Reset.setOnClickListener{
            produtoController.getAllProdutos { produtos ->
                if (produtos.isNotEmpty()) {
                    listaProdutos = produtos
                    LV.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produtos)
                } else {
                    listaProdutos = null
                    Toast.makeText(this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}