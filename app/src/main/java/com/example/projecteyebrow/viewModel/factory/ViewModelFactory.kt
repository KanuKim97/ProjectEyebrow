package com.example.projecteyebrow.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projecteyebrow.di.AppRepository

class ViewModelFactory(private val appRepo: AppRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AppRepository::class.java).newInstance(appRepo)
    }
}