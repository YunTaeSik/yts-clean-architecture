package com.yts.ytscleanarchitecture.presentation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.yts.domain.entity.Document
import com.yts.domain.usecase.search.SearchUseCase
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.extension.addAll
import com.yts.ytscleanarchitecture.extension.clear
import com.yts.ytscleanarchitecture.presentation.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashSet


class SearchViewModel(private val searchUseCase: SearchUseCase) : BaseViewModel() {
    private var searchDisposable: Disposable? = null

    private var _viewType = MutableLiveData<SearchViewType>()

    private var _query = MutableLiveData<String>()
    private var _sort = MutableLiveData<String>()
    private var _size = MutableLiveData<Int>()
    private var _page = MutableLiveData<Int>()

    private var _documentList = MutableLiveData<List<Document>>()
    private var _documentFilterList = MutableLiveData<List<Document>>()

    private var _filterHashSet = MutableLiveData<HashSet<String>>()

    val query: LiveData<String> get() = _query
    val sort: LiveData<String> get() = _sort
    val size: LiveData<Int> get() = _size
    val page: LiveData<Int> get() = _page
    val viewType: LiveData<SearchViewType> get() = _viewType

    val documentList: LiveData<List<Document>> get() = _documentList
    val documentFilterList: LiveData<List<Document>> get() = _documentFilterList

    val filterHashSet: LiveData<HashSet<String>> get() = _filterHashSet

    fun setViewType(viewType: SearchViewType) {
        _viewType.postValue(viewType)
    }

    fun setQuery(query: String) {
        _query.postValue(query)

        if (query.isNotEmpty()) {
            setViewType(SearchViewType.RESULT)
        } else {
            setViewType(SearchViewType.NONE)
        }
    }

    fun setPage(page: Int) {
        _page.postValue(page)
    }

    fun setFilterHashSet(documents: List<Document>?) {
        if (documents != null) {
            var filterHashSet: HashSet<String> = HashSet()
            filterHashSet.add("ALL")
            for (document in documents) {
                if (document.collection != null) {
                    filterHashSet.add(document.collection!!.toUpperCase(Locale.getDefault()))
                }
            }
            _filterHashSet.postValue(filterHashSet)
        }
    }

    /**
     * 검색
     */
    fun search(query: String) {
        _isLoading.postValue(false)
        setQuery(query)

        searchDisposable?.dispose()
        if (query.isNotEmpty()) {
            _isLoading.postValue(true)
            searchDisposable =
                Observable.timer(500, TimeUnit.MILLISECONDS)
                    .subscribe({
                        _isLoading.postValue(false)
                        _documentList.clear()
                        setPage(1)
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
                )
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe({

                        if (it.documents?.size == 0) {
                            _toastMessageId.postValue(R.string.error_query_size_null_message)
                        } else if (it.meta?.total_count != _documentList.value?.size) {
                            _documentList.addAll(it.documents!!)
                            setFilterHashSet(it.documents)
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

