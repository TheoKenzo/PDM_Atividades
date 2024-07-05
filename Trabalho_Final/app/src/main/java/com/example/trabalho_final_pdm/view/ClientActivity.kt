package com.example.trabalho_final_pdm.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabalho_final_pdm.R
import com.example.trabalho_final_pdm.controller.ClienteController
import com.example.trabalho_final_pdm.model.Cliente
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ClientActivity : AppCompatActivity() {

    lateinit var BT_Voltar: Button
    lateinit var BT_ListaBuscar: Button
    lateinit var ET_CPF: EditText
    lateinit var ET_Nome: EditText
    lateinit var ET_Telefone: EditText
    lateinit var ET_Endereco: EditText
    lateinit var ET_Instagram: EditText
    lateinit var BT_Deletar: Button
    lateinit var BT_Alterar: Button
    lateinit var BT_Inserir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_client)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.BT_Reset_Tela05)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT_Voltar = findViewById(R.id.BT_Voltar_Tela02)
        BT_ListaBuscar = findViewById(R.id.BT_Listar_Tela02)
        ET_CPF = findViewById(R.id.ET_CPF_Tela02)
        ET_Nome = findViewById(R.id.ET_Nome_Tela02)
        ET_Telefone = findViewById(R.id.ET_Telefone_Tela02)
        ET_Endereco = findViewById(R.id.ET_Endereco_Tela02)
        ET_Instagram = findViewById(R.id.ET_Instagram_Tela02)
        BT_Deletar = findViewById(R.id.BT_Deletar_Tela02)
        BT_Alterar = findViewById(R.id.BT_Alterar_Tela02)
        BT_Inserir = findViewById(R.id.BT_Inserir_Tela02)

        val clienteController = ClienteController()

        var cliente: Cliente

        BT_Voltar.setOnClickListener{
            this.finish()
        }

        BT_ListaBuscar.setOnClickListener{
            val intent = Intent(this, ClientListActivity::class.java)
            startActivity(intent)
        }

        BT_Inserir.setOnClickListener{
            cliente = Cliente(ET_CPF.text.toString(), ET_Nome.text.toString(), ET_Telefone.text.toString(), ET_Endereco.text.toString(), ET_Instagram.text.toString())

            Toast.makeText(this, clienteController.addCliente(cliente), Toast.LENGTH_SHORT).show()

            ET_CPF.text.clear()
            ET_Nome.text.clear()
            ET_Telefone.text.clear()
            ET_Endereco.text.clear()
            ET_Instagram.text.clear()
        }

        BT_Alterar.setOnClickListener{
            cliente = Cliente(ET_CPF.text.toString(), ET_Nome.text.toString(), ET_Telefone.text.toString(), ET_Endereco.text.toString(), ET_Instagram.text.toString())

            Toast.makeText(this, clienteController.attCliente(cliente), Toast.LENGTH_SHORT).show()

            ET_CPF.text.clear()
            ET_Nome.text.clear()
            ET_Telefone.text.clear()
            ET_Endereco.text.clear()
            ET_Instagram.text.clear()
        }

        BT_Deletar.setOnClickListener{
            cliente = Cliente(ET_CPF.text.toString(), ET_Nome.text.toString(), ET_Telefone.text.toString(), ET_Endereco.text.toString(), ET_Instagram.text.toString())

            Toast.makeText(this, clienteController.delCliente(cliente), Toast.LENGTH_SHORT).show()

            ET_CPF.text.clear()
            ET_Nome.text.clear()
            ET_Telefone.text.clear()
            ET_Endereco.text.clear()
            ET_Instagram.text.clear()
        }
    }
}