package com.example.trabalho_final_pdm.model

data class Item_Pedido(var id_item_pedido: String, var fk_id_pedido: String, var fk_id_produto: String, var quantidade: Double){
    override fun toString(): String {
        return ("ID Item Pedido: $id_item_pedido | FK Id Pedido: $fk_id_pedido | FK Id Produto: $fk_id_produto | Quantidade: $quantidade")
    }
}