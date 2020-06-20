package com.yts.ytscleanarchitecture.presentation.ui.image

import android.os.Bundle
import android.util.SparseArray
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModel
import com.yts.ytscleanarchitecture.BR
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.ActivityImageDetailBinding
import com.yts.ytscleanarchitecture.presentation.base.BaseActivity
import com.yts.ytscleanarchitecture.utils.Const
import kotlinx.android.synthetic.main.activity_image_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDetailActivity : BaseActivity<ActivityImageDetailBinding>() {
    private val model: ImageDetailViewModel by viewModel()

    override fun onLayoutId(): Int {
        return R.layout.activity_image_detail
    }

    override fun setupViewModel(): SparseArray<ViewModel> {
        val setupViewModel = SparseArray<ViewModel>()
        setupViewModel.put(BR.model, model)
        return setupViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewCompat.setTransitionName(
            image_detail,
            Const.TRANS_VIEW_IMAGE
        )


        model.setImageDetail(intent.getStringExtra(Const.INTENT_IMAGE_URL))

    }

    override fun observer() {
    }
}