package com.example.tp4_hasnaoui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp4_hasnaoui.databinding.ActivityCitiesBinding
import com.example.tp4_hasnaoui.databinding.ActivityGridBinding

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val cities = arrayOf("tunis", "sfax","sousse","mahdia","gabes")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,cities)
        binding.gridList.adapter=adapter
        binding.gridList.setOnItemClickListener { adapterView, view, i, l ->
            val city = cities[i]
            binding.textViewGrid.text = cities[i]
            val uri = Uri.parse("https://www.google.com/search?q=$city")

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent);


        }
    }
}