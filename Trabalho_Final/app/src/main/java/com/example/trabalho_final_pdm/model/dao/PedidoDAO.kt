package com.example.trabalho_final_pdm.model.dao

import android.util.Log
import com.example.trabalho_final_pdm.model.Pedido
import com.example.trabalho_final_pdm.model.Produto
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class PedidoDAO {
    val db = Firebase.firestore

    fun getAllPedidosbyCliente(cpf: String, onResult: (ArrayList<Pedido>) -> Unit) {
        val pedidos = ArrayList<Pedido>()

        db.collection("pedido")
            .whereEqualTo("fk_cpf", cpf)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val aux = Pedido(
                        document.getString("id_pedido").toString(),
                        document.getTimestamp("data") ?: Timestamp.now(),
                        document.getString("fk_cpf").toString()
                    )
                    pedidos.add(aux)
                }
                onResult(pedidos)
            }
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao buscar os pedidos", e)
            }
    }

    fun getPedidoById(idPedido: String,cpf: String, onResult: (ArrayList<Pedido>) -> Unit) {
        val pedidos = ArrayList<Pedido>()

        db.collection("pedido")
            .whereEqualTo("id_pedido", idPedido)
            .whereEqualTo("fk_cpf", cpf)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val aux = Pedido(
                        document.getString("id_pedido").toString(),
                        document.getTimestamp("data") ?: Timestamp.now(),
                        document.getString("fk_cpf").toString()
                    )
                    pedidos.add(aux)
                }
                onResult(pedidos)
            }
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao buscar o produto", e)
            }
    }

    fun getLastPedidoId(onResult: (String) -> Unit) {
        db.collection("pedido")
            .orderBy("id_pedido", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    onResult("1.0")
                } else {
                    val latestDocument = documents.documents[0]
                    val documentId = latestDocument.id
                    onResult((documentId.toDouble() + 1).toString())
                }
            }
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao buscar o ID do documento", e)
            }
    }

    fun addPedido(pedido: Pedido): String{
        db.collection("pedido")
            .document(pedido.id_pedido)
            .set(pedido)
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao adicionar o novo pedido!", e)
            }
        return "Novo pedido adicionado com sucesso!"
    }

    fun attPedido(pedido: Pedido): String{
        db.collection("pedido").document(pedido.id_pedido).update(
            "fk_cpf", pedido.fk_cpf,
            "data", pedido.data
        )
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao atualizar o pedido!", e)
            }
        return "Pedido atualizado com sucesso!"
    }

    fun delPedido(pedido: Pedido): String{
        db.collection("pedido").document(pedido.id_pedido)
            .delete()
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao remover o pedido!", e)
            }
        return "Pedido removido com sucesso!"
    }
}