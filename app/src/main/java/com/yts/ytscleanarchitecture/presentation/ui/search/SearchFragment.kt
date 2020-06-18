package com.yts.ytscleanarchitecture.presentation.ui.search

import android.os.Bundle
import android.util.SparseArray
import androidx.lifecycle.ViewModel
import androidx.transition.TransitionInflater
import com.yts.ytscleanarchitecture.BR
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.FragmentSearchBinding
import com.yts.ytscleanarchitecture.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val model: SearchViewModel by sharedViewModel()

    companion object {
        fun newInstance(): SearchFragment {
            val args = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun setupViewModel(): SparseArray<ViewModel> {
        val setupViewModel = SparseArray<ViewModel>()
        setupViewModel.put(BR.model, model)
        return setupViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.slide_in_trans)
    }

    override fun onDestroy() {
        super.onDestroy()
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.slide_out_trans)
    }

    override fun observer() {
    }
}