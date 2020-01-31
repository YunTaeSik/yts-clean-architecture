package com.yts.data.repository

import com.yts.data.source.remote.SearchService
import com.yts.domain.repository.SearchRepository
import com.yts.domain.response.SearchResponse
import io.reactivex.Observable

object SearchRepositoryImp : SearchRepository {
    private const val authorization = "KakaoAK f1328266d7ef1949f7cd02c8ba212a72"
    private var searchService = SearchService.Creator.create()


    override fun getImages(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Observable<SearchResponse> {
        return searchService.getImages(
            authorization,
            query,
            sort,
            page,
            size
        )
    }


}