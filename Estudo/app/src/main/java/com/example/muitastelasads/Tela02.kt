package com.example.muitastelasads

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tela02 : AppCompatActivity() {

    lateinit var BT2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela02)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT2 = findViewById(R.id.BT_Tela02)

        var bd_chegouDeT1 = intent.extras
        var primeiroNumero: Float = bd_chegouDeT1!!.getFloat("12345")
        var segundoNumero: Float = bd_chegouDeT1!!.getFloat("54321")

        Toast.makeText(this, "Numeros que vieram: \nNr1: " + primeiroNumero + "\nNr2: " + segundoNumero, Toast.LENGTH_SHORT).show()

        BT2.setOnClickListener{
            this.finish()
        }
    }
}