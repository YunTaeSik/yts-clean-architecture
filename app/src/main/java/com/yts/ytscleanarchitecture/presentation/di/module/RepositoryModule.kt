package com.example.stunitastest.presentation.di.module

import com.yts.data.repository.SearchRepositoryImp
import com.yts.domain.repository.SearchRepository
import com.yts.domain.usecase.search.SearchUseCase
import com.yts.domain.usecase.search.SearchUseCaseImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(): SearchRepository {
        return SearchRepositoryImp
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(searchRepository: SearchRepository): SearchUseCase {
        return SearchUseCaseImp(searchRepository)
    }

    @Provides
    @Singleton
    fun provideModel(): String {
        return "model"
    }

}