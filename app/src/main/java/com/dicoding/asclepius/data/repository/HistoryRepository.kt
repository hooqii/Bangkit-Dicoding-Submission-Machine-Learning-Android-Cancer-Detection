package com.dicoding.asclepius.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.data.local.entity.History
import com.dicoding.asclepius.data.local.room.HistoryDao
import com.dicoding.asclepius.data.local.room.HistoryDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository(application: Application) {

    private val _historyDao: HistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = HistoryDatabase.getDatabase(application)
        _historyDao = db.historyDao()
    }

    fun getAllHistory(): LiveData<List<History>> = _historyDao.getAllHistory()

    fun insert(history: History) {
        executorService.execute { _historyDao.insert(history) }
    }

    fun delete(history: History) {
        executorService.execute { _historyDao.delete(history) }
    }

}