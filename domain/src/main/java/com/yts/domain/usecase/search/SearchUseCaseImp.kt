package com.yts.domain.usecase.search

import com.yts.domain.repository.SearchRepository
import com.yts.domain.response.SearchResponse
import io.reactivex.Observable

class SearchUseCaseImp(private val searchRepository: SearchRepository) : SearchUseCase {
    override fun getImages(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Observable<SearchResponse> {
        return searchRepository.getImages(query, sort, page, size)
    }

}