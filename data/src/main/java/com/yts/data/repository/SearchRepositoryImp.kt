package com.yts.data.repository

import com.yts.data.source.remote.SearchService
import com.yts.domain.repository.SearchRepository
import com.yts.domain.response.SearchResponse
import io.reactivex.Observable

object SearchRepositoryImp : SearchRepository {
    private var searchService = SearchService.Creator.create()


    override fun getImages(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Observable<SearchResponse> {
        return searchService.getImages(
            "KakaoAK ebc0afd8be627ae7946c041011b88705",
            query,
            sort,
            page,
            size
        )
    }


}