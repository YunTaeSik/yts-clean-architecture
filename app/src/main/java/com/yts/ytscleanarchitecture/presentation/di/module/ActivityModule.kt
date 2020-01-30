package com.yts.ytscleanarchitecture.presentation.di.module

import com.yts.ytscleanarchitecture.presentation.ui.intro.IntroActivity
import com.yts.ytscleanarchitecture.presentation.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {
    @ContributesAndroidInjector
    fun introActivityInjector(): IntroActivity

    @ContributesAndroidInjector
    fun searchActivityInjector(): SearchActivity

}