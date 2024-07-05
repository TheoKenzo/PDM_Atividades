package com.example.trabalho_final_pdm.model

data class Produto(var id_produto: String, var tipo_do_grao: String, var ponto_da_torra: String, var valor: Double, var blend: Boolean){
    override fun toString(): String {
        return ("ID Produto: $id_produto | Tipo do Grão: $tipo_do_grao | Ponto da Torra: $ponto_da_torra | Valor: R$$valor | Blend: $blend")
    }
}