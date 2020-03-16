package com.yts.domain.usecase.base

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase<T> {

    protected var lastDisposable: Disposable? = null
    protected val compositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCase(): Observable<T>

    open fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ) {
        disposeLast()
        lastDisposable = buildUseCase()
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)

        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }


    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}