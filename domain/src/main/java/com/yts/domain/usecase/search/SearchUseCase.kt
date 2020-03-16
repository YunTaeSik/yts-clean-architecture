package com.yts.domain.usecase.search

import com.yts.domain.repository.SearchRepository
import com.yts.domain.response.SearchResponse
import com.yts.domain.usecase.base.UseCase
import io.reactivex.Observable
import javax.naming.directory.SearchControls

class SearchUseCase(private val searchRepository: SearchRepository) : UseCase<SearchResponse>() {
    fun getImages(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Observable<SearchResponse> {
        return searchRepository.getImages(query, sort, page, size)
    }

    override fun buildUseCase(): Observable<SearchResponse> {
        return searchRepository.getImages("", null, null, null)
    }

}