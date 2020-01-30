package com.yts.domain.usecase.search

import com.example.stunitastest.domain.usecase.BaseUseCase
import com.yts.domain.response.SearchResponse
import io.reactivex.Observable

interface SearchUseCase : BaseUseCase {

    fun getImages(query: String, sort: String?, page: Int?, size: Int?): Observable<SearchResponse>
}