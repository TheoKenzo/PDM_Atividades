package com.example.trabalho_final_pdm.model.dao

import com.example.trabalho_final_pdm.model.Item_Pedido
import com.example.trabalho_final_pdm.model.Pedido
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class Item_PedidoDAO {
    val db = Firebase.firestore

    fun getAllItemPedidobyPedido(idPedido: String, onResult: (ArrayList<Item_Pedido>) -> Unit) {
        val itensPedidos = ArrayList<Item_Pedido>()

        db.collection("item_pedido")
            .whereEqualTo("fk_id_pedido", idPedido)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val aux = Item_Pedido(
                        document.getString("id_item_pedido").toString(),
                        document.getString("fk_id_pedido").toString(),
                        document.getString("fk_id_produto").toString(),
                        document.getDouble("quantidade").toString().toDouble()
                    )
                    itensPedidos.add(aux)
                }
                onResult(itensPedidos)
            }
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao buscar os itens pedidos", e)
            }
    }

    fun getLastItemPedidoId(onResult: (String) -> Unit) {
        db.collection("item_pedido")
            .orderBy("id_item_pedido", Query.Direction.DESCENDING)
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

    fun addItemPedido(itemPedido: Item_Pedido): String{
        db.collection("item_pedido")
            .document(itemPedido.id_item_pedido)
            .set(itemPedido)
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao adicionar o novo pedido!", e)
            }
        return "Novo pedido adicionado com sucesso!"
    }

    fun attItemPedido(itemPedido: Item_Pedido): String{
        db.collection("item_pedido").document(itemPedido.id_item_pedido).update(
            "fk_id_pedido", itemPedido.fk_id_pedido,
            "fk_id_produto", itemPedido.fk_id_produto,
            "quantidade", itemPedido.quantidade
        )
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao atualizar o item pedido!", e)
            }
        return "Item pedido atualizado com sucesso!"
    }

    fun delItemPedido(itemPedido: Item_Pedido): String{
        db.collection("item_pedido").document(itemPedido.id_item_pedido)
            .delete()
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao remover o item pedido!", e)
            }
        return "Item pedido removido com sucesso!"
    }
}