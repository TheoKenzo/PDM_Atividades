package com.example.trabalho_final_pdm.controller

import com.example.trabalho_final_pdm.model.Item_Pedido
import com.example.trabalho_final_pdm.model.dao.Item_PedidoDAO

class Item_PedidoController {
    private val DAO = Item_PedidoDAO()

    fun getAllItemPedidobyPedido(idPedido: String, onResult: (ArrayList<Item_Pedido>) -> Unit) {
        try{
            DAO.getAllItemPedidobyPedido(idPedido) { pedidos ->
                onResult(pedidos)
            }
        }catch (e: Exception){
            throw(e)
        }
    }

    fun getLastItemPedidoId(onResult: (String) -> Unit) {
        try{
            DAO.getLastItemPedidoId { idItemPedido ->
                onResult(idItemPedido)
            }
        }catch (e: Exception){
            throw(e)
        }
    }

    fun addItemPedido(itemPedido: Item_Pedido): String{
        try {
            return DAO.addItemPedido(itemPedido)
        }catch (e:Exception){
            throw(e)
        }
    }

    fun attItemPedido(itemPedido: Item_Pedido): String{
        try {
            return DAO.attItemPedido(itemPedido)
        }catch (e:Exception){
            throw(e)
        }
    }

    fun delItemPedido(itemPedido: Item_Pedido): String{
        try {
            return DAO.delItemPedido(itemPedido)
        }catch (e:Exception){
            throw(e)
        }
    }
}