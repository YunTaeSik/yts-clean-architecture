package com.yts.ytscleanarchitecture.presentation.ui.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yts.ytscleanarchitecture.presentation.base.BaseViewModel

class ImageDetailViewModel : BaseViewModel() {
    private var _imageDetail = MutableLiveData<String>()

    val imageDetail: LiveData<String> get() = _imageDetail

    fun setImageDetail(image : String?){
        _imageDetail.postValue(image)
    }
}