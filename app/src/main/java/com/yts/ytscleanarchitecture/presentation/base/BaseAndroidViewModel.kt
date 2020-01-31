package com.yts.ytscleanarchitecture.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val compositeDisposable = CompositeDisposable()

    protected var _isLoading = MutableLiveData<Boolean>()
    protected var _toastMessageId = MutableLiveData<Int>()

    val isLoading: LiveData<Boolean> get() = _isLoading
    val _toastMessage: LiveData<Int> get() = _toastMessageId

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