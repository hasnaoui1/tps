package com.example.tp4_hasnaoui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp4_hasnaoui.databinding.ActivityGridBinding
import com.example.tp4_hasnaoui.databinding.ActivityWorldBinding

class WorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val continent = resources.getStringArray(R.array.Continent)
        val PaysAm = resources.getStringArray(R.array.PaysAm)
        val paysOc = resources.getStringArray(R.array.paysOc)
        val paysAfr = resources.getStringArray(R.array.paysAfr)
        val paysEur = resources.getStringArray(R.array.paysEur)
        val paysAsie = resources.getStringArray(R.array.paysAsie)
        enableEdgeToEdge()
        val binding = ActivityWorldBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_world)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adpt = ArrayAdapter(this,android.R.layout.simple_spinner_item,continent)
       adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerContinent.adapter = adpt
        binding.spinnerContinent.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var tab  ;
               when(continent[p2]) {
                   "Afrique" -> tab = paysAfr
                   "Europe" -> tab = paysEur
                   "Asie" -> tab = paysAsie
                   "Océanie" -> tab = paysOc
                   "Amérique" -> tab = PaysAm
               }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }










    }
}