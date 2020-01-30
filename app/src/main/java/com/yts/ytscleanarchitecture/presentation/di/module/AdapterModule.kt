package com.yts.ytscleanarchitecture.presentation.di.module

import com.yts.ytscleanarchitecture.presentation.ui.search.SearchAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdapterModule {
    @Provides
    @Singleton
    fun provideSearchAdapter(): SearchAdapter {
        return SearchAdapter()
    }
}