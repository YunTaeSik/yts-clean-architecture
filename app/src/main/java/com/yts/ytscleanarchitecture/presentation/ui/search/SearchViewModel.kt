package com.yts.ytscleanarchitecture.presentation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yts.domain.entity.Document
import com.yts.domain.response.SearchResponse
import com.yts.domain.usecase.search.SearchUseCase
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.extension.addAll
import com.yts.ytscleanarchitecture.extension.clear
import com.yts.ytscleanarchitecture.presentation.base.BaseViewModel
import com.yts.ytscleanarchitecture.utils.Const
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


class SearchViewModel(private val searchUseCase: SearchUseCase) : BaseViewModel() {
    private var searchDisposable: Disposable? = null


    private var _query = MutableLiveData<String>()
    private var _page = MutableLiveData<Int>()

    private var _filter = MutableLiveData<String>()
    private var _viewType = MutableLiveData<SearchViewType>()

    private var _documentList = MutableLiveData<List<Document>>()
    private var _documentFilterList = MutableLiveData<List<Document>>()

    private var _filterHashSet = MutableLiveData<HashSet<String>>()

    val query: LiveData<String> get() = _query
    val page: LiveData<Int> get() = _page

    val filter: LiveData<String> get() = _filter
    val viewType: LiveData<SearchViewType> get() = _viewType

    val documentList: LiveData<List<Document>> get() = _documentList
    val documentFilterList: LiveData<List<Document>> get() = _documentFilterList

    val filterHashSet: LiveData<HashSet<String>> get() = _filterHashSet

    private var isEnd = false

    fun setQuery(query: String) {
        _query.value = query

        if (query.isNotEmpty()) {
            setViewType(SearchViewType.RESULT)
        } else {
            setViewType(SearchViewType.NONE)
        }
    }

    fun setPage(page: Int) {
        _page.postValue(page)
    }

    fun setViewType(viewType: SearchViewType) {
        _viewType.postValue(viewType)
    }

    fun setFilter(filter: String) {
        _isLoading.postValue(true)
        _filter.postValue(filter)
        addDisposable(
            Observable.just(filter).observeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(false)
                    setDocumentFilterList(it)
                }, {
                    _toastMessageId.postValue(R.string.error_message)
                    _isLoading.postValue(false)
                })
        )
    }

    fun setDocumentList(searchResponse: SearchResponse) {
        _documentList.addAll(searchResponse.documents!!)

    }


    fun setDocumentFilterList(filter: String?) {
        setDocumentFilterList(filter, documentList.value)
    }

    fun setDocumentFilterList(documentList: List<Document>?) {
        setDocumentFilterList(filter.value, documentList)
    }

    fun setDocumentFilterList(filter: String?, documentList: List<Document>?) {
        if (filter == null || filter == Const.FILTER_ALL) {
            _documentFilterList.postValue(documentList)
        } else {
            if (documentList != null) {
                var filterList: ArrayList<Document> = ArrayList<Document>()
                for (document in documentList) {
                    if (filter == document.collection?.toUpperCase()) {
                        filterList.add(document)
                    }
                }
                _documentFilterList.postValue(filterList)
            }
        }
    }

    fun changeQueryText(query: String) {
        clearFilter()
        clearFilterHashSet()
        clearDocumentList()
        setPage(1)
        search(query)
    }

    private fun clearFilter() {
        _filter.postValue(null)
    }

    private fun clearFilterHashSet() {
        var filterHashSet: HashSet<String> = HashSet()
        filterHashSet.add(Const.FILTER_ALL)
        _filterHashSet.postValue(filterHashSet)
    }

    /**
     * 이미지 리스트 초기화
     */
    private fun clearDocumentList() {
        _documentList.clear()
        _documentFilterList.clear()
    }

    /**
     * 검색
     */
    fun search(query: String) {
        _isLoading.postValue(false)
        setQuery(query)

        searchDisposable?.dispose()
        _isLoading.postValue(true)
        searchDisposable =
            Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribe({
                    _isLoading.postValue(false)
                    getImages(1)
                }, {
                    it.printStackTrace()
                })
    }

    /**
     * Load More
     */
    fun loadMore(findLastCompletelyVisibleItemPosition: Int) {
        if (findLastCompletelyVisibleItemPosition == documentFilterList.value!!.size - 1) {
            if (!_isLoading.value!! && !isEnd) {
                getImages(page.value!! + 1)
            }
        }

    }

    /**
     * 이미지목록 가져오기
     */
    private fun getImages(page: Int) {
        if (query.value != null && query.value!!.isNotEmpty()) {
            _isLoading.postValue(true)
            addDisposable(
                searchUseCase.getImages(
                    query.value!!,
                    null,
                    page,
                    78
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({

                        if (it.documents?.size == 0) {
                            _toastMessageId.postValue(R.string.error_query_size_null_message)
                        } else if (it.meta?.total_count != _documentList.value?.size) {
                            Log.e("is_end", it.meta?.is_end.toString())
                            Log.e("total_count", it.meta?.total_count.toString())
                            Log.e("size", _documentList.value?.size.toString())
                            isEnd = it.meta?.is_end!!
                            setDocumentList(it)
                        }
                        setPage(page)
                        _isLoading.postValue(false)
                    }, {
                        it.printStackTrace()
                        _toastMessageId.postValue(R.string.error_message)
                        _isLoading.postValue(false)
                    })
            )
        }
    }

    /**
     * 필터 리스트 셋
     */
    fun setFilterHashSet(documents: List<Document>?) {
        if (documents != null) {
            var filterHashSet: HashSet<String> = HashSet()
            filterHashSet.add(Const.FILTER_ALL)
            for (document in documents) {
                if (document.collection != null) {
                    filterHashSet.add(document.collection!!.toUpperCase(Locale.getDefault()))
                }
            }
            _filterHashSet.postValue(filterHashSet)
        }
    }
}

