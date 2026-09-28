package com.example.calculadora_imc

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora_imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCalculate.setOnClickListener (this)
    }



    override fun onClick(view: View) {
        if (view.id == binding.buttonCalculate.id)
        {
            val peso = binding.editWeight.text.toString().toDoubleOrNull() ?: 0.0
            val altura =  binding.editHeight.text.toString().toDoubleOrNull() ?: 0.0

            if (peso == 0.0 || altura == 0.0){
                Toast.makeText(applicationContext, R.string.error_msg, Toast.LENGTH_LONG).show()
            }else{
                val imc = peso /(altura * altura)
                binding.valueImc.text = imc.toString()

                val result = when(imc) {
                    in 0.0 .. 18.4 -> "Magreza"
                    in 18.5 ..24.9 -> "Peso normal"
                    in 25.0 .. 29.9 -> "Sobrepeso"
                    in 30.0.. 34.9 -> "Obesidade Grau 1"
                    in 35.0.. 39.9 -> "Obesidade Grau 2"
                    else -> "Obesidade Grau 3"
                }
                binding.textResult.text = "Você está com " + result
                Toast.makeText(applicationContext, R.string.success_msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

}