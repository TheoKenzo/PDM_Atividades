package com.example.a3ex_pdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    // Variáveis EX 3

    lateinit var ET1: EditText
    lateinit var ET2: EditText
    lateinit var ET3: EditText
    lateinit var BT: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Código Principal

        ET1 = findViewById(R.id.ET1)
        ET2 = findViewById(R.id.ET2)
        ET3 = findViewById(R.id.ET3)
        BT = findViewById(R.id.BT)

        BT.setOnClickListener{
            val valA: Float = ET1.text.toString().toFloat()
            val valB: Float = ET2.text.toString().toFloat()
            val valC: Float = ET3.text.toString().toFloat()

            val delta: Float = (valB.pow(2)) - (4 * valA * valC)

            if(delta >= 0){
                val res1: Float = (-valB + sqrt(delta)) / (2 * valA)
                val res2: Float = (-valB - sqrt(delta)) / (2 * valA)

                val Coord_x = -valB / (2 * valA)
                val Coord_y = -delta / (4 * valA)

                Toast.makeText(this, "x1: $res1 x2: $res2\nconcavidade para ${if(valA > 0) "cima" else "baixo"}\ncoordenadas\nx: $Coord_x y: $Coord_y", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this, "Delta: $delta, como é negativo, resulta em números imaginários :(", Toast.LENGTH_SHORT).show()
            }
        }
    }
}