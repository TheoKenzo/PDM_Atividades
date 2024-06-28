package com.example.a4ex_pdm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sqrt
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    //Funções

    fun calcularDistancia(x1: Double, y1: Double, x2: Double, y2: Double): Double {
        val dx = x2 - x1
        val dy = y2 - y1
        return sqrt(dx.pow(2) + dy.pow(2))
    }

    //Variáveis Exercício 4

    lateinit var ET_X1: EditText
    lateinit var ET_Y1: EditText
    lateinit var ET_X2: EditText
    lateinit var ET_Y2: EditText

    lateinit var BT_Calcular: Button

    lateinit var TV_Resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ET_X1 = findViewById(R.id.ET_X1)
        ET_Y1 = findViewById(R.id.ET_Y1)
        ET_X2 = findViewById(R.id.ET_X2)
        ET_Y2 = findViewById(R.id.ET_Y2)

        BT_Calcular = findViewById(R.id.BT_Calcular)

        TV_Resultado = findViewById(R.id.TV_Resultado)

        BT_Calcular.setOnClickListener{
            val distancia = calcularDistancia(ET_X1.text.toString().toDouble(), ET_Y1.text.toString().toDouble(), ET_X2.text.toString().toDouble(), ET_Y2.text.toString().toDouble())

            TV_Resultado.setText("A distância entre os pontos (${ET_X1.text}, ${ET_Y1.text}) e (${ET_X2.text}, ${ET_Y2.text}) é: $distancia")
        }
    }
}