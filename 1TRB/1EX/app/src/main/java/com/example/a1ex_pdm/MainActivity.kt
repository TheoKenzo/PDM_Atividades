package com.example.a1ex_pdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Variáveis EX1

    lateinit var ET1: EditText
    lateinit var ET2: EditText
    lateinit var BT: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Código principal

        ET1 = findViewById(R.id.ET1)
        ET2 = findViewById(R.id.ET2)
        BT = findViewById(R.id.BT)

        BT.setOnClickListener{
            val val1: Float = ET1.text.toString().toFloat()
            val val2: Float = ET2.text.toString().toFloat()
            val res: Float = val1 + val2

            Toast.makeText(this, "Resultado da soma: $res", Toast.LENGTH_SHORT).show()
        }
    }
}