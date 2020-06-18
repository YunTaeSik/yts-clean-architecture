package com.yts.data.repository

import com.yts.data.source.remote.SearchService
import com.yts.domain.repository.SearchRepository
import com.yts.domain.response.SearchResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.create

class SearchRepositoryImp(private val retrofit: Retrofit) : SearchRepository {
    private val authorization = "KakaoAK ebc0afd8be627ae7946c041011b88705"

    override fun getImages(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Observable<SearchResponse> {
        return retrofit.create<SearchService>().getImages(
            authorization,
            query,
            sort,
            page,
            size
        )
    }


}