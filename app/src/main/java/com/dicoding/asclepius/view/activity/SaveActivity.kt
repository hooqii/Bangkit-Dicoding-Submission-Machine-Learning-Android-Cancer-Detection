package com.dicoding.asclepius.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.adapter.HistoryAdapter
import com.dicoding.asclepius.databinding.ActivitySaveBinding
import com.dicoding.asclepius.view.factory.SaveViewModelFactory
import com.dicoding.asclepius.view.viewmodel.SaveViewModel


class SaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = SaveViewModelFactory.getInstance(application)
        val viewModel: SaveViewModel = ViewModelProvider(this, factory)[SaveViewModel::class.java]
        val adapter = HistoryAdapter()
        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        binding.rvHistory.layoutManager = layoutManager
        binding.rvHistory.addItemDecoration(itemDecoration)

        adapter.onClick = {
            viewModel.delete(it)
            Toast.makeText(this, "Delete successfully", Toast.LENGTH_SHORT).show()
        }

        viewModel.getAllHistory().observe(this) { list ->
            if (list != null) {
                adapter.submitList(list)
                binding.rvHistory.adapter = adapter
            }
            if (list.isEmpty()) {
                Toast.makeText(
                    this,
                    "No History",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}