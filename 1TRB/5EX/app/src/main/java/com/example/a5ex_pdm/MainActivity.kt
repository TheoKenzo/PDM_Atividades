package com.example.a5ex_pdm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //Função
    fun ehPrimo(numero: Int): Boolean {
        if (numero <= 1) {
            return false
        }

        for (i in 2 until numero) {
            if (numero % i == 0) {
                return false
            }
        }
        return true
    }

    //Variáveis
    lateinit var ET_Numero: EditText

    lateinit var BT_Verificar: Button

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

        ET_Numero = findViewById(R.id.ET_Numero)

        BT_Verificar = findViewById(R.id.BT_Verificar)

        TV_Resultado = findViewById(R.id.TV_Resultado)

        BT_Verificar.setOnClickListener{
            if (ehPrimo(ET_Numero.text.toString().toInt())) {
                TV_Resultado.setText("${ET_Numero.text} é primo.")
            } else {
                TV_Resultado.setText("${ET_Numero.text} não é primo.")
            }
        }
    }
}