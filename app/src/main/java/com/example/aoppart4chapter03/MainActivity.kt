package com.example.aoppart4chapter03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.aoppart4chapter03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SearchRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initViews()

    }

    private fun initAdapter() {
        adapter = SearchRecyclerviewAdapter{
            Toast.makeText(this, "아이템 클릭", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() = with(binding) {
        emptyResultTxt.isVisible = false
        recycleView.adapter = adapter
    }
}