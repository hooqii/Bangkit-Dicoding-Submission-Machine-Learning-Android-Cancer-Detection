package com.dicoding.asclepius.view.activity

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.data.local.entity.History
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.factory.ResultViewModelFactory
import com.dicoding.asclepius.view.viewmodel.ResultViewModel

class ResultActivity : AppCompatActivity() {

    private var binding: ActivityResultBinding? = null
    private val history = History()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val factory = ResultViewModelFactory.getInstance(application)
        val viewModel: ResultViewModel =
            ViewModelProvider(this, factory)[ResultViewModel::class.java]

        binding?.saveButton?.setOnClickListener {
            viewModel.insert(history)
            Toast.makeText(this, "Save successfully", Toast.LENGTH_SHORT).show()
        }

        val result = intent.getStringExtra(EXTRA_RESULT)
        val prediction = intent.getStringExtra(EXTRA_PREDICT)
        val score = intent.getStringExtra(EXTRA_SCORE)
        val data = intent.getStringExtra(EXTRA_IMAGE_URI)
        val imageUri = Uri.parse(data)

        history.image = data
        history.prediction = prediction
        history.score = score

        runOnUiThread {
            imageUri?.let {
                Log.d("Image URI", "showImage: $it")
                binding?.resultImage?.setImageURI(it)
                binding?.resultText?.text = result
            }
        }
        Log.d(ContentValues.TAG, "Image URI: $result")
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_PREDICT = "extra_predict"
        const val EXTRA_SCORE = "extra_score"
    }
}