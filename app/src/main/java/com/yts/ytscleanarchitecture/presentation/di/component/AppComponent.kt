package com.yts.ytscleanarchitecture.presentation.di.component

import com.yts.ytscleanarchitecture.presentation.di.module.ActivityModule
import com.yts.ytscleanarchitecture.presentation.di.module.AdapterModule
import com.example.stunitastest.presentation.di.module.RepositoryModule
import com.yts.ytscleanarchitecture.BaseApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        AdapterModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<BaseApplication>

}

