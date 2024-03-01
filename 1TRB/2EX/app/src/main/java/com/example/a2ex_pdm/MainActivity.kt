package com.example.a2ex_pdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Variáveis EX 2

    lateinit var ET: EditText
    lateinit var BT: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Código Principal

        ET = findViewById(R.id.ET1)
        BT = findViewById(R.id.BT)

        BT.setOnClickListener{
            Toast.makeText(this, "O número é ${if(ET.text.toString().toInt() % 2 == 0) "Par" else "Ímpar"}", Toast.LENGTH_SHORT).show()
        }
    }
}