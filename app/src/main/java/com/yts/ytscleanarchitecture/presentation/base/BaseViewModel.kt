package com.yts.ytscleanarchitecture.presentation.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected val context: Context = application.applicationContext
    private val compositeDisposable = CompositeDisposable()

    protected var _isLoading = MutableLiveData<Boolean>()
    protected var _toastMessage = MutableLiveData<String>()

    val isLoading: LiveData<Boolean> get() = _isLoading
    val toastMessage: LiveData<String> get() = _toastMessage

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clear() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clear()
        super.onCleared()
    }
}