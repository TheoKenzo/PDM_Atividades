package com.example.muitastelasads

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tela01 : AppCompatActivity() {

    lateinit var BT1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela01)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BT1 = findViewById(R.id.BT_Tela01)

        var bd_mochilaParaT2 = Bundle()
        bd_mochilaParaT2.putFloat("12345", 478.23F)
        bd_mochilaParaT2.putFloat("54321", 100.187F)

        var queroIrParaTela02 = Intent(this, Tela02::class.java)
        queroIrParaTela02.putExtras(bd_mochilaParaT2)

        BT1.setOnClickListener{
            startActivity(queroIrParaTela02)
        }
    }
}