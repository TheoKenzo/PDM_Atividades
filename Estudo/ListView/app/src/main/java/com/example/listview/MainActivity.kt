package com.example.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var et_nome: EditText
    lateinit var bt_inserir: Button
    lateinit var lv_nomes: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        et_nome = findViewById(R.id.ET1)
        bt_inserir = findViewById(R.id.BT)
        lv_nomes = findViewById(R.id.ListView1)

        var listaNomes = ArrayList<String>()

        listaNomes.add("Pedro")
        listaNomes.add("Cugler")
        listaNomes.add("Zé das Couves")

        var adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNomes)

        lv_nomes.adapter = adaptador

        bt_inserir.setOnClickListener{
            var novoNome: String = et_nome.text.toString()
            listaNomes.add(novoNome)
            lv_nomes.adapter = adaptador
            et_nome.text.clear()
        }

        lv_nomes.setOnItemClickListener { adapterView, view, i, l ->
            var elemento = adapterView.getItemIdAtPosition(i)
            Toast.makeText(this, "Escolhido: " + elemento, Toast.LENGTH_LONG).show()
        }
    }
}