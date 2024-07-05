package com.example.trabalho_final_pdm.controller

import com.example.trabalho_final_pdm.model.Pedido
import com.example.trabalho_final_pdm.model.Produto
import com.example.trabalho_final_pdm.model.dao.PedidoDAO
import com.example.trabalho_final_pdm.model.dao.ProdutoDAO

class PedidoController {
    private val DAO = PedidoDAO()

    fun getAllPedidosbyCliente(cpf: String, onResult: (ArrayList<Pedido>) -> Unit){
        try{
            DAO.getAllPedidosbyCliente(cpf) { pedidos ->
                onResult(pedidos)
            }
        }catch (e: Exception){
            throw(e)
        }
    }

    fun getPedidoById(idPedido: String,cpf: String, onResult: (ArrayList<Pedido>) -> Unit){
        try{
            DAO.getPedidoById(idPedido, cpf) { pedidos ->
                onResult(pedidos)
            }
        }catch (e: Exception){
            throw(e)
        }
    }

    fun getLastPedidoId(onResult: (String) -> Unit){
        try{
            DAO.getLastPedidoId { idPedido ->
                onResult(idPedido)
            }
        }catch (e: Exception){
            throw(e)
        }
    }

    fun addPedido(pedido: Pedido): String{
        try {
            return DAO.addPedido(pedido)
        }catch (e:Exception){
            throw(e)
        }
    }

    fun attPedido(pedido: Pedido): String{
        try {
            return DAO.attPedido(pedido)
        }catch (e:Exception){
            throw(e)
        }
    }

    fun delPedido(pedido: Pedido): String{
        try {
            return DAO.delPedido(pedido)
        }catch (e:Exception){
            throw(e)
        }
    }
}