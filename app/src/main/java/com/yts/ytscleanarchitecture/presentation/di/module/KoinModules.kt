package com.yts.ytscleanarchitecture.presentation.di.module

import com.yts.data.repository.SearchRepositoryImp
import com.yts.data.source.remote.SearchService
import com.yts.domain.repository.SearchRepository
import com.yts.domain.usecase.search.SearchUseCase
import com.yts.domain.usecase.search.SearchUseCaseImp
import com.yts.ytscleanarchitecture.presentation.ui.intro.IntroViewModel
import com.yts.ytscleanarchitecture.presentation.ui.search.SearchAdapter
import com.yts.ytscleanarchitecture.presentation.ui.search.SearchViewModel
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dapi.kakao.com"

val repositoryModule = module {
    single<SearchRepository> { SearchRepositoryImp }
    single<SearchUseCase> { SearchUseCaseImp(get()) }
}

var adapterModule = module {
    single<SearchAdapter> { SearchAdapter() }
}

var netModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}

var viewModelModule = module {
    viewModel {
        IntroViewModel()
    }

    viewModel {
        SearchViewModel( get())
    }
}

var moduleList = listOf(
    repositoryModule, adapterModule, viewModelModule
)