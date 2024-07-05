package com.example.trabalho_final_pdm.model

data class Cliente (var cpf: String, var nome: String, var telefone: String, var endereco: String, var instagram: String){
    override fun toString(): String {
        return ("CPF: $cpf | Nome: $nome | Telefone: $telefone | Endereco: $endereco | Instagram: $instagram")
    }
}