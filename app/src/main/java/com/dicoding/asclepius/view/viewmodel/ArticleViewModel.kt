package com.dicoding.asclepius.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.Result
import com.dicoding.asclepius.data.repository.ArticleRepository
import com.dicoding.asclepius.data.remote.response.ArticlesItem

class ArticleViewModel(articleRepository: ArticleRepository) : ViewModel() {
    val listUser: LiveData<Result<List<ArticlesItem>>> = articleRepository.getListUser()
}
