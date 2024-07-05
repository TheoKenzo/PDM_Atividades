package com.example.trabalho_final_pdm.controller

import android.util.Log
import com.example.trabalho_final_pdm.model.Cliente
import com.example.trabalho_final_pdm.model.dao.ClienteDAO
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ClienteController {
    private val DAO = ClienteDAO()

    fun getAllClientes(onResult: (ArrayList<Cliente>) -> Unit){
        try{
            DAO.getAllClientes { clientes ->
                onResult(clientes)
            }
        }catch (e: Exception){
            throw(e)
        }
    }

    fun getClienteByCpf(cpf: String, onResult: (ArrayList<Cliente>) -> Unit){
        try {
            DAO.getClienteByCpf(cpf) { clientes ->
                onResult(clientes)
            }
        }catch (e:Exception){
            throw(e)
        }
    }

    fun addCliente(cliente: Cliente): String{
        try {
            return DAO.addCliente(cliente)
        }catch (e:Exception){
            throw(e)
        }
    }

    fun attCliente(cliente: Cliente): String{
        try {
            return DAO.attCliente(cliente)
        }catch (e:Exception){
            throw(e)
        }
    }

    fun delCliente(cliente: Cliente): String{
        try {
            return DAO.delCliente(cliente)
        }catch (e:Exception){
            throw(e)
        }
    }
}