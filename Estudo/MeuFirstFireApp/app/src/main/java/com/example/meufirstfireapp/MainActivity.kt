package com.example.meufirstfireapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var referencia: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        referencia = Firebase.database.reference

        // Insert

        val bolo1 = Bolo("123", "Cenoura", "Chocolate", 25.69F)
        referencia.child(bolo1.id).setValue(bolo1)

        val bolo2 = Bolo("456", "Chocolate", "Sem cobertura", 74.91F)
        referencia.child(bolo2.id).setValue(bolo2)

        val bolo3 = Bolo("789", "Iogurte", "Goiaba", 62.51F)
        referencia.child(bolo3.id).setValue(bolo3)

        // Update
        /*
        val bolo4 = Bolo("456", "Milho", "Queijo", 31.50F)
        referencia.child(bolo4.id).setValue(bolo4)
        */

        //Remove
        /*
        referencia.child("789").removeValue()
        */

        val listaBolos = ArrayList<Bolo>()
        referencia.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                if(snapshot.exists()){
                    var gson = Gson()

                    for(i in snapshot.children){
                        val json = gson.toJson(i.value)
                        val cake = gson.fromJson(json, Bolo::class.java)

                        Log.i("Teste", "--------------------------")
                        Log.i("Teste", "Id: ${cake.id}")
                        Log.i("Teste", "Nome: ${cake.nome}")
                        Log.i("Teste", "Cobertura: ${cake.cobertura}")
                        Log.i("Teste", "Preço: R$${cake.preco}")
                        Log.i("Teste", "--------------------------")
                        listaBolos.add(Bolo(cake.id, cake.nome, cake.cobertura, cake.preco))
                    }
                    Log.i("Teste", "Array: ${listaBolos}")
                }
            }

            override fun onCancelled(error: DatabaseError){
                Log.i("Teste", "Erro: $error")
            }
        })
    }
}