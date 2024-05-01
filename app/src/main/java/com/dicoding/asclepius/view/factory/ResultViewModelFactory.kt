package com.dicoding.asclepius.view.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.view.viewmodel.ResultViewModel

class ResultViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ResultViewModelFactory? = null
        fun getInstance(application: Application): ResultViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ResultViewModelFactory(application)
            }.also { instance = it }
    }
}