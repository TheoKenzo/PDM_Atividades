package com.example.trabalho_final_pdm.controller

import com.example.trabalho_final_pdm.model.Produto
import com.example.trabalho_final_pdm.model.dao.ProdutoDAO

class ProdutoController {
    private val DAO = ProdutoDAO()

    fun getAllProdutos(onResult: (ArrayList<Produto>) -> Unit){
        try{
            DAO.getAllProdutos { produtos ->
                onResult(produtos)
            }
        }catch (e: Exception){
            throw(e)
        }
    }

    fun getProdutoById(Id_Produto: String, onResult: (ArrayList<Produto>) -> Unit){
        try {
            DAO.getProdutoById(Id_Produto) { produtos ->
                onResult(produtos)
            }
        }catch (e:Exception){
            throw(e)
        }
    }

    fun getLastProdutoId(onResult: (String) -> Unit) {
        try {
            DAO.getLastProdutoId(){ idProduto ->
                onResult(idProduto)
            }
        }catch (e:Exception){
            throw(e)
        }
    }

    fun addProduto(produto: Produto): String{
        try {
            return DAO.addProduto(produto)
        }catch (e:Exception){
            throw(e)
        }
    }

    fun attProduto(produto: Produto): String{
        try {
            return DAO.attProduto(produto)
        }catch (e:Exception){
            throw(e)
        }
    }

    fun delProduto(produto: Produto): String{
        try {
            return DAO.delProduto(produto)
        }catch (e:Exception){
            throw(e)
        }
    }
}