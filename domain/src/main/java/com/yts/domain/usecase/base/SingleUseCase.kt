package com.yts.domain.usecase.base

import io.reactivex.Single

abstract class SingleUseCase<T> : UseCase<T>() {

    internal abstract fun buildUseCaseSingle(): Single<T>

    override fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit
    ) {
        disposeLast()
        lastDisposable = buildUseCaseSingle()
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)

        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}