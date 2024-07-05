package com.example.trabalho_final_pdm.model

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

data class Pedido(var id_pedido: String, var data: Timestamp, var fk_cpf: String){
    private val formatoData = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
    val dataFormatada: String = try {
        formatoData.format(data.toDate())
    } catch (e: Exception) {
        "Data inválida"
    }

    override fun toString(): String {
        return ("ID Pedido: $id_pedido | Data: $dataFormatada | CPF: $fk_cpf")
    }
}