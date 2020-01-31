package com.yts.ytscleanarchitecture.presentation.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yts.domain.entity.Document
import com.yts.domain.usecase.search.SearchUseCase
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.extension.addAll
import com.yts.ytscleanarchitecture.extension.clear
import com.yts.ytscleanarchitecture.presentation.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


class SearchViewModel(private val searchUseCase: SearchUseCase) : BaseViewModel() {
    private var searchDisposable: Disposable? = null

    private var _query = MutableLiveData<String>()
    private var _sort = MutableLiveData<String>()
    private var _size = MutableLiveData<Int>()
    private var _page = MutableLiveData<Int>()

    private var _listDocument = MutableLiveData<List<Document>>()

    val query: LiveData<String> get() = _query
    val sort: LiveData<String> get() = _sort
    val size: LiveData<Int> get() = _size
    val page: LiveData<Int> get() = _page

    val listDocument: LiveData<List<Document>> get() = _listDocument

    fun setQuery(query: String) {
        _query.value = query
    }

    fun setPage(page: Int) {
        _page.value = page
    }

    /**
     * 검색
     */
    fun search(query: String) {
        setQuery(query)
        searchDisposable?.dispose()
        if (query.isNotEmpty()) {
            searchDisposable =
                Observable.timer(1, TimeUnit.SECONDS)
                    .subscribe({
                        _listDocument.clear()
                        getImages()
                    }, {
                        it.printStackTrace()
                    })
        }
    }

    /**
     * 이미지목록 가져오기
     */
    fun getImages() {
        if (query.value != null) {
            _isLoading.postValue(true)
            addDisposable(
                searchUseCase.getImages(
                    query.value!!,
                    sort.value,
                    page.value,
                    size.value
                ).subscribe({

                    if (it.documents?.size == 0) {
                        _toastMessageId.postValue(R.string.error_query_size_null_message)
                    }
                    if (it.meta?.total_count != _listDocument.value?.size) {
                        _listDocument.addAll(it.documents!!)
                    } else {

                    }

                    _isLoading.postValue(false)
                }, {
                    it.printStackTrace()
                    _toastMessageId.postValue(R.string.error_message)
                    _isLoading.postValue(false)
                })
            )
        } else {
            _toastMessageId.postValue(R.string.error_query_text_null_message)
        }
    }


}

