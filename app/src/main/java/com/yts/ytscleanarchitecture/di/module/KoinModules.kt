package com.yts.ytscleanarchitecture.di.module

import com.yts.data.repository.SearchRepositoryImp
import com.yts.domain.repository.SearchRepository
import com.yts.domain.usecase.search.SearchUseCase
import com.yts.ytscleanarchitecture.presentation.ui.filter.FilterAdapter
import com.yts.ytscleanarchitecture.presentation.ui.intro.IntroViewModel
import com.yts.ytscleanarchitecture.presentation.ui.search.SearchAdapter
import com.yts.ytscleanarchitecture.presentation.ui.search.SearchViewModel
import com.yts.ytscleanarchitecture.utils.Const
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val repositoryModule = module {
    single<SearchRepository> { SearchRepositoryImp(get()) }
    single<SearchUseCase> { SearchUseCase(get()) }
}

var adapterModule = module {
    single<SearchAdapter> { SearchAdapter() }
    single<FilterAdapter> { FilterAdapter() }
}

var netModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Const.BASE_URL)
            .build()
    }
}

var viewModelModule = module {
    viewModel {
        IntroViewModel()
    }

    viewModel {
        SearchViewModel(get())
    }
}

var moduleList = listOf(
    repositoryModule, adapterModule, netModule, viewModelModule
)