package com.example.trabalho_final_pdm.model.dao

import android.util.Log
import com.example.trabalho_final_pdm.model.Produto
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class ProdutoDAO {
    val db = Firebase.firestore

    fun getAllProdutos(onResult: (ArrayList<Produto>) -> Unit) {
        val produtos = ArrayList<Produto>()

        db.collection("produto")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val aux = Produto(
                        document.getString("id_produto").toString(),
                        document.getString("tipo_do_grao").toString(),
                        document.getString("ponto_da_torra").toString(),
                        document.getDouble("valor").toString().toDouble(),
                        document.getBoolean("blend").toString().toBoolean()
                    )
                    produtos.add(aux)
                }
                onResult(produtos)
            }
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao buscar os produtos", e)
            }
    }

    fun getProdutoById(Id_Produto: String, onResult: (ArrayList<Produto>) -> Unit) {
        val produtos = ArrayList<Produto>()

        db.collection("produto")
            .whereEqualTo("id_produto", Id_Produto)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val aux = Produto(
                        document.getString("id_produto").toString(),
                        document.getString("tipo_do_grao").toString(),
                        document.getString("ponto_da_torra").toString(),
                        document.getDouble("valor").toString().toDouble(),
                        document.getBoolean("blend").toString().toBoolean()
                    )
                    produtos.add(aux)
                }
                onResult(produtos)
            }
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao buscar o produto", e)
            }
    }

    fun getLastProdutoId(onResult: (String) -> Unit) {
        db.collection("produto")
            .orderBy("id_produto", Query.Direction.DESCENDING)
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

    fun addProduto(produto: Produto): String{
        db.collection("produto")
            .document(produto.id_produto)
            .set(produto)
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao adicionar o novo produto!", e)
            }
        return "Novo produto adicionado com sucesso!"
    }

    fun attProduto(produto: Produto): String{
        db.collection("produto").document(produto.id_produto).update(
            "tipo_do_grao", produto.tipo_do_grao,
            "ponto_da_torra", produto.ponto_da_torra,
            "valor", produto.valor,
            "blend", produto.blend
        )
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao atualizar o produto!", e)
            }
        return "Produto atualizado com sucesso!"
    }

    fun delProduto(produto: Produto): String{
        db.collection("produto").document(produto.id_produto)
            .delete()
            .addOnFailureListener { e ->
                throw FirebaseException("Erro ao remover o produto!", e)
            }
        return "Produto removido com sucesso!"
    }
}