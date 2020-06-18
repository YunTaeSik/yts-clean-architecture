package com.yts.ytscleanarchitecture.presentation.base

import android.animation.Animator
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.Math.hypot

open class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    protected var _isLoading = MutableLiveData<Boolean>()
    protected var _toastMessageId = MutableLiveData<Int>()

    val isLoading: LiveData<Boolean> get() = _isLoading
    val toastMessageId: LiveData<Int> get() = _toastMessageId

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    /**
     * 키워드 애니메이션
     */
    open fun startKeywordAnimation(view: TextView, text: String) {
        addDisposable(
            Observable.create(
                ObservableOnSubscribe { emitter: ObservableEmitter<Animator?> ->
                    val width = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP,
                        25f * text.length,
                        view.context.resources.displayMetrics
                    ).toInt()
                    val height = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP,
                        25f,
                        view.context.resources.displayMetrics
                    ).toInt()
                    val x = view.left
                    val y = view.top

                    val startRadius = 0
                    val endRadius = hypot(width.toDouble(), height.toDouble()).toInt()
                    var animator: Animator? = null
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        animator = ViewAnimationUtils.createCircularReveal(
                            view,
                            x,
                            y,
                            startRadius.toFloat(),
                            endRadius.toFloat()
                        )
                        animator.interpolator = FastOutSlowInInterpolator()
                        animator.duration = 500
                        emitter.onNext(animator)
                    }
                    emitter.onComplete()
                }
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { animator: Animator? ->
                        view.visibility = View.VISIBLE
                        animator?.start()
                    }
                ) { throwable: Throwable? ->
                    throwable?.printStackTrace()
                    //  _throwable.postValue(throwable)
                }
        )
    }

    private fun clear() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clear()
        super.onCleared()
    }
}