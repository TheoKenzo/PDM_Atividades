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
import com.example.trabalho_final_pdm.controller.ClienteController
import com.example.trabalho_final_pdm.model.Cliente

class ClientListActivity : AppCompatActivity() {

    lateinit var BT_Voltar: Button
    lateinit var ET_IDCliente: EditText
    lateinit var BT_Buscar: Button
    lateinit var LV: ListView
    lateinit var BT_Reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_client_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.BT_Reset_Tela03)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT_Voltar = findViewById(R.id.BT_Voltar_Tela03)
        ET_IDCliente = findViewById(R.id.ET_Busca_Tela03)
        BT_Buscar = findViewById(R.id.BT_Buscar_Tela03)
        LV = findViewById(R.id.LV_Tela03)
        BT_Reset = findViewById(R.id.BT_Reset_Tela03)

        val clienteController = ClienteController()

        var listaClientes: ArrayList<Cliente>? = null

        clienteController.getAllClientes { clientes ->
            if (clientes.isNotEmpty()) {
                listaClientes = clientes
                LV.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, clientes)
            } else {
                listaClientes = null
                Toast.makeText(this, "Nenhum cliente encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        BT_Voltar.setOnClickListener{
            this.finish()
        }

        BT_Buscar.setOnClickListener{
            if(ET_IDCliente.text.isNotEmpty()){
                clienteController.getClienteByCpf(ET_IDCliente.text.toString()) { clientes ->
                    if (clientes.isNotEmpty()) {
                        listaClientes = clientes
                        LV.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, clientes)
                    } else {
                        listaClientes = null
                        Toast.makeText(this, "Nenhum cliente encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        LV.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
            val selectedItem = listaClientes?.get(position)

            if (selectedItem != null) {
                ET_IDCliente.setText(selectedItem.cpf)
            }
        }

        BT_Reset.setOnClickListener{
            clienteController.getAllClientes { clientes ->
                if (clientes.isNotEmpty()) {
                    listaClientes = clientes
                    LV.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, clientes)
                } else {
                    listaClientes = null
                    Toast.makeText(this, "Nenhum cliente encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}