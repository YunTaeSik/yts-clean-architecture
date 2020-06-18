package com.yts.ytscleanarchitecture.presentation.ui.intro

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.ViewModel
import com.yts.ytscleanarchitecture.BR
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.IntroBinding
import com.yts.ytscleanarchitecture.extension.startCircularRevealAnimation
import com.yts.ytscleanarchitecture.presentation.base.BaseActivity
import com.yts.ytscleanarchitecture.presentation.ui.search.SearchActivity
import com.yts.ytscleanarchitecture.utils.Consts
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_intro.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class IntroActivity : BaseActivity<IntroBinding>() {

    private val model: IntroViewModel by viewModel()

    override fun onLayoutId(): Int {
        return R.layout.activity_intro
    }

    override fun setupViewModel(): SparseArray<ViewModel> {
        val setupViewModel = SparseArray<ViewModel>()
        setupViewModel.put(BR.model, model)
        return setupViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.addDisposable(Single.timer(250, TimeUnit.MILLISECONDS).subscribe { _ ->
            text_title.startCircularRevealAnimation()
        })

        model.addDisposable(Single.timer(1500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ ->
                var pair: Pair<View, String> = Pair.create(text_title, Consts.TRANS_VIEW_NAME_TITLE)
                val optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        pair
                    )

                startActivity(
                    Intent(this, SearchActivity::class.java)
                )
                finish()
            })
    }

    override fun observer() {
    }
}
