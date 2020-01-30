package com.yts.domain.repository

import com.yts.domain.response.SearchResponse
import io.reactivex.Observable


public interface SearchRepository {

    /**
     * query = 	검색을 원하는 질의어
     * sort = 	결과 문서 정렬 방식	accuracy (정확도순) or recency (최신순)
     * page = 	결과 페이지 번호	(기본 1)	1-50 사이 Integer
     * size = 한 페이지에 보여질 문서의 개수	(기본 80)	1-80 사이 Integer
     */
    fun getImages(query: String, sort: String?, page: Int?, size: Int?): Observable<SearchResponse>
}