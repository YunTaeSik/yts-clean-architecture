package com.yts.ytscleanarchitecture.presentation.di.module

import com.yts.data.repository.SearchRepositoryImp
import com.yts.domain.repository.SearchRepository
import com.yts.domain.usecase.search.SearchUseCase
import com.yts.domain.usecase.search.SearchUseCaseImp
import com.yts.ytscleanarchitecture.presentation.ui.search.SearchAdapter
import com.yts.ytscleanarchitecture.presentation.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<SearchRepository> { SearchRepositoryImp }
    single<SearchUseCase> { SearchUseCaseImp(get()) }

    single<SearchAdapter> { SearchAdapter() }
}

var adapterModule = module {
    single<SearchAdapter> { SearchAdapter() }
}


var viewModelModule = module {
    viewModel {
        SearchViewModel(androidApplication(), get())
    }
}

var moduleList = listOf(
    repositoryModule, adapterModule, viewModelModule
)