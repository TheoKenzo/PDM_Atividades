package com.example.trabalho_final_pdm.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabalho_final_pdm.R
import com.example.trabalho_final_pdm.controller.ClienteController
import com.example.trabalho_final_pdm.controller.Item_PedidoController
import com.example.trabalho_final_pdm.controller.PedidoController
import com.example.trabalho_final_pdm.controller.ProdutoController
import com.example.trabalho_final_pdm.model.Cliente
import com.example.trabalho_final_pdm.model.Item_Pedido
import com.example.trabalho_final_pdm.model.Pedido
import com.example.trabalho_final_pdm.model.Produto
import com.google.firebase.Timestamp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PedidoActivity : AppCompatActivity() {

    lateinit var BT_Voltar: Button
    lateinit var ET_CPFConsultaPedidos:EditText
    lateinit var BT_ListaClientes1: Button
    lateinit var BT_ListaPedidos: Button
    lateinit var ET_Data: EditText
    lateinit var ET_Time: EditText
    lateinit var ET_CPFCliente: EditText
    lateinit var BT_ListaClientes2: Button
    lateinit var ET_IDProduto: EditText
    lateinit var ET_Quantidade: EditText
    lateinit var BT_CriarPedido: Button

    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT_Voltar = findViewById(R.id.BT_Voltar_Tela06)
        ET_CPFConsultaPedidos = findViewById(R.id.ET_BuscaCliente_Tela06)
        BT_ListaClientes1 = findViewById(R.id.BT_ListaClientes_Tela06)
        BT_ListaPedidos = findViewById(R.id.BT_ListaPedido_Tela06)
        ET_Data = findViewById(R.id.ET_Data_Tela06)
        ET_Time = findViewById(R.id.ET_Tempo_Tela06)
        ET_CPFCliente = findViewById(R.id.ET_CPF_Tela06)
        BT_ListaClientes2 = findViewById(R.id.BT_ListaClientes2_Tela06)
        ET_IDProduto = findViewById(R.id.ET_IDProduto_Tela06)
        ET_Quantidade = findViewById(R.id.ET_Quantidade_Tela06)
        BT_CriarPedido = findViewById(R.id.BT_CriarPedido_Tela06)

        val clienteController = ClienteController()
        val pedidoController = PedidoController()
        val itemPedidocontroller = Item_PedidoController()

        var lastIDPedido = "1"
        var lastIDItemPedido = "1"

        pedidoController.getLastPedidoId{ id ->
            lastIDPedido = id
        }

        itemPedidocontroller.getLastItemPedidoId { id ->
            lastIDItemPedido = id
        }

        BT_Voltar.setOnClickListener{
            this.finish()
        }

        BT_ListaClientes1.setOnClickListener{
            val intent = Intent(this, ClientListActivity::class.java)
            startActivity(intent)
        }

        BT_ListaPedidos.setOnClickListener{
            clienteController.getClienteByCpf(ET_CPFConsultaPedidos.text.toString()){ cliente ->
                if(cliente.isNotEmpty()){
                    val intent = Intent(this, PedidoListActivity::class.java)
                    val clientCPF = ET_CPFConsultaPedidos.text.toString()
                    intent.putExtra("cpf", clientCPF)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Cliente não encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ET_Data.setOnClickListener {
            showDatePicker()
        }

        ET_Time.setOnClickListener {
            showTimePicker()
        }

        BT_ListaClientes2.setOnClickListener{
            val intent = Intent(this, ClientListActivity::class.java)
            startActivity(intent)
        }

        BT_CriarPedido.setOnClickListener{
            pedidoController.getLastPedidoId{ id ->
                lastIDPedido = id
            }

            itemPedidocontroller.getLastItemPedidoId { id ->
                lastIDItemPedido = id
            }

            if(ET_Data.text.isNotEmpty() && ET_Time.text.isNotEmpty() && ET_CPFCliente.text.isNotEmpty()){
                val pedido = Pedido(lastIDPedido, Timestamp(Instant.ofEpochMilli(combineDateTime())), ET_CPFCliente.text.toString())
                Toast.makeText(this, pedidoController.addPedido(pedido), Toast.LENGTH_SHORT).show()
            }

            if(ET_IDProduto.text.isNotEmpty() && ET_Quantidade.text.isNotEmpty()){
                val itemPedido = Item_Pedido(lastIDItemPedido, lastIDPedido, ET_IDProduto.text.toString(), ET_Quantidade.text.toString().toDouble())
                Toast.makeText(this, itemPedidocontroller.addItemPedido(itemPedido), Toast.LENGTH_SHORT).show()
            }

            ET_Data.text.clear()
            ET_Time.text.clear()
            ET_CPFCliente.text.clear()
            ET_IDProduto.text.clear()
            ET_Quantidade.text.clear()
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
        ET_Time.setText(timeFormat.format(calendar.time))
    }

    private fun combineDateTime(): Long {
        return calendar.timeInMillis
    }
}