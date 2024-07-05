package com.example.trabalho_final_pdm.model.dao

import android.util.Log
import com.example.trabalho_final_pdm.model.Cliente
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.firestore

class ClienteDAO {
    val db = Firebase.firestore

    fun getAllClientes(onResult: (ArrayList<Cliente>) -> Unit) {
        val clientes = ArrayList<Cliente>()

        db.collection("cliente")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val aux = Cliente(
                        document.getString("cpf").toString(),
                        document.getString("nome").toString(),
                        document.getString("telefone").toString(),
                        document.getString("endereco").toString(),
                        document.getString("instagram").toString()
                    )
                    clientes.add(aux)
                }
                onResult(clientes)
            }
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao buscar os clientes", e)
            }
    }

    fun getClienteByCpf(cpf: String, onResult: (ArrayList<Cliente>) -> Unit) {
        val clientes = ArrayList<Cliente>()

        db.collection("cliente")
            .whereEqualTo("cpf", cpf)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val aux = Cliente(
                        document.getString("cpf").toString(),
                        document.getString("nome").toString(),
                        document.getString("telefone").toString(),
                        document.getString("endereco").toString(),
                        document.getString("instagram").toString()
                    )
                    clientes.add(aux)
                }
                onResult(clientes)
            }
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao buscar o cliente", e)
            }
    }

    fun addCliente(cliente: Cliente): String{
        db.collection("cliente")
            .document(cliente.cpf)
            .set(cliente)
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao adicionar o novo cliente!", e)
            }
        return "Novo cliente adicionado com sucesso!"
    }

    fun attCliente(cliente: Cliente): String{
        db.collection("cliente").document(cliente.cpf).update(
            "nome", cliente.nome,
            "telefone", cliente.telefone,
            "endereco", cliente.endereco,
            "instagram", cliente.instagram
        )
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao atualizar o cliente!", e)
            }
        return "Cliente atualizado com sucesso!"
    }

    fun delCliente(cliente: Cliente): String{
        db.collection("cliente").document(cliente.cpf)
            .delete()
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao remover o cliente!", e)
            }
        return "Cliente removido com sucesso!"
    }
}