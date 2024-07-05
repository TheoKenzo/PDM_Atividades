package com.example.trabalho_final_pdm.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabalho_final_pdm.R
import com.example.trabalho_final_pdm.controller.ClienteController
import com.example.trabalho_final_pdm.controller.Item_PedidoController
import com.example.trabalho_final_pdm.controller.PedidoController
import com.example.trabalho_final_pdm.model.Item_Pedido
import com.example.trabalho_final_pdm.model.Pedido
import com.example.trabalho_final_pdm.model.Produto
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PedidoListActivity : AppCompatActivity() {

    lateinit var BT_Voltar: Button
    lateinit var TV_Cliente: TextView
    lateinit var ET_IDPedido: EditText
    lateinit var ET_IDItemPedido: EditText
    lateinit var ET_Data: EditText
    lateinit var ET_IDProduto: EditText
    lateinit var ET_Horario: EditText
    lateinit var ET_Quantidade: EditText
    lateinit var ET_CPF: EditText
    lateinit var BT_DeletarPedido: Button
    lateinit var BT_DeletarItemPedido: Button
    lateinit var BT_AlterarPedido: Button
    lateinit var BT_AlterarItemPedido: Button
    lateinit var LV_Pedidos: ListView
    lateinit var LV_ItemPedido: ListView
    lateinit var BT_ResetListas: Button

    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT_Voltar = findViewById(R.id.BT_Voltar_Tela07)
        TV_Cliente = findViewById(R.id.TV_Cliente_Tela07)
        ET_IDPedido = findViewById(R.id.ET_IDPedido_Tela07)
        ET_IDItemPedido = findViewById(R.id.ET_IDItemPedido_Tela07)
        ET_Data = findViewById(R.id.ET_Data_Tela07)
        ET_IDProduto = findViewById(R.id.ET_IDProduto_Tela07)
        ET_Horario = findViewById(R.id.ET_Horario_Tela07)
        ET_Quantidade = findViewById(R.id.ET_Quantidade_Tela07)
        ET_CPF = findViewById(R.id.ET_CPF_Tela07)
        BT_DeletarPedido = findViewById(R.id.BT_DeletarPedido_Tela07)
        BT_DeletarItemPedido = findViewById(R.id.BT_DeletarItemPedido_Tela07)
        BT_AlterarPedido = findViewById(R.id.BT_AlterarPedido_Tela07)
        BT_AlterarItemPedido = findViewById(R.id.BT_AlterarItemPedido_Tela07)
        LV_Pedidos = findViewById(R.id.LV_Pedidos_Tela07)
        LV_ItemPedido = findViewById(R.id.LV_ItemPedidos_Tela07)
        BT_ResetListas = findViewById(R.id.BT_ResetListas_Tela07)

        val pedidoController = PedidoController()
        val itemPedidocontroller = Item_PedidoController()

        val clientCPF = intent.extras?.getString("cpf")!!

        var listaPedidos: ArrayList<Pedido>? = null

        pedidoController.getAllPedidosbyCliente(clientCPF) { pedidos ->
            if (pedidos.isNotEmpty()) {
                listaPedidos = pedidos
                LV_Pedidos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pedidos)
            } else {
                listaPedidos = null
                Toast.makeText(this, "Nenhum pedido encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        var listaItemPedido: ArrayList<Item_Pedido> = arrayListOf()

        fun populateListView() {
            listaItemPedido = arrayListOf()
            val totalPedidos = listaPedidos?.size ?: 0
            var pedidosProcessados = 0

            fun checkAndPopulateAdapter() {
                if (pedidosProcessados == totalPedidos) {
                    LV_ItemPedido.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaItemPedido)
                }
            }

            if (totalPedidos > 0) {
                listaPedidos?.forEach { pedido ->
                    itemPedidocontroller.getAllItemPedidobyPedido(pedido.id_pedido) { itensPedidos ->
                        itensPedidos.forEach { itemPedido ->
                            listaItemPedido.add(itemPedido)
                        }
                        pedidosProcessados++
                        checkAndPopulateAdapter()
                    }
                }
            } else {
                LV_ItemPedido.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaItemPedido)
            }
        }

        populateListView()

        var date: Date

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        var pedido: Pedido
        var itemPedido: Item_Pedido

        BT_Voltar.setOnClickListener{
            this.finish()
        }

        TV_Cliente.setText(clientCPF)

        ET_Data.setOnClickListener{
            showDatePicker()
        }

        ET_Horario.setOnClickListener{
            showTimePicker()
        }

        BT_DeletarPedido.setOnClickListener{
            pedido = Pedido(ET_IDPedido.text.toString(), Timestamp(Instant.ofEpochMilli(combineDateTime())), clientCPF)

            pedidoController.delPedido(pedido)

            pedidoController.getAllPedidosbyCliente(clientCPF) { pedidos ->
                if (pedidos.isNotEmpty()) {
                    listaPedidos = pedidos
                    LV_Pedidos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pedidos)
                } else {
                    listaPedidos = null
                    Toast.makeText(this, "Nenhum pedido encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        BT_AlterarPedido.setOnClickListener{
            pedido = Pedido(ET_IDPedido.text.toString(), Timestamp(Instant.ofEpochMilli(combineDateTime())), ET_CPF.text.toString())

            pedidoController.attPedido(pedido)

            pedidoController.getAllPedidosbyCliente(clientCPF) { pedidos ->
                if (pedidos.isNotEmpty()) {
                    listaPedidos = pedidos
                    LV_Pedidos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pedidos)
                } else {
                    listaPedidos = null
                    Toast.makeText(this, "Nenhum pedido encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        BT_DeletarItemPedido.setOnClickListener{
            itemPedido = Item_Pedido(ET_IDItemPedido.text.toString(), ET_IDPedido.text.toString(), ET_IDProduto.text.toString(), ET_Quantidade.text.toString().toDouble())

            itemPedidocontroller.delItemPedido(itemPedido)

            populateListView()
        }

        BT_AlterarItemPedido.setOnClickListener{
            itemPedido = Item_Pedido(ET_IDItemPedido.text.toString(), ET_IDPedido.text.toString(), ET_IDProduto.text.toString(), ET_Quantidade.text.toString().toDouble())

            itemPedidocontroller.attItemPedido(itemPedido)

            populateListView()
        }

        LV_Pedidos.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
            val selectedItem = listaPedidos?.get(position)

            if (selectedItem != null) {
                date = Date(selectedItem.data.toString().toLong())
                ET_IDPedido.setText(selectedItem.id_pedido)
                ET_Data.setText(dateFormat.format(date))
                ET_Horario.setText(timeFormat.format(date))
                ET_CPF.setText(selectedItem.fk_cpf)
            }
        }

        LV_ItemPedido.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
            val selectedItem = listaItemPedido.get(position)

            ET_IDItemPedido.setText(selectedItem.id_item_pedido)
            ET_IDPedido.setText(selectedItem.fk_id_pedido)
            ET_IDProduto.setText(selectedItem.fk_id_produto)
            ET_Quantidade.setText(selectedItem.quantidade.toString())
        }

        BT_ResetListas.setOnClickListener{
            pedidoController.getAllPedidosbyCliente(clientCPF) { pedidos ->
                if (pedidos.isNotEmpty()) {
                    listaPedidos = pedidos
                    LV_Pedidos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pedidos)
                } else {
                    listaPedidos = null
                    Toast.makeText(this, "Nenhum pedido encontrado", Toast.LENGTH_SHORT).show()
                }
            }

            populateListView()
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateText()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        val timePicker = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                updateTimeText()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }

    private fun updateDateText() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        ET_Data.setText(dateFormat.format(calendar.time))
    }

    private fun updateTimeText() {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        ET_Horario.setText(timeFormat.format(calendar.time))
    }

    private fun combineDateTime(): Long {
        return calendar.timeInMillis
    }
}