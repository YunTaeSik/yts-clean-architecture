package com.yts.ytscleanarchitecture.presentation.base

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.transition.*
import com.yts.ytscleanarchitecture.R

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    protected var binding: B? = null

    /**
     * 레이아웃 설정
     */
    protected abstract fun onLayoutId(): Int

    /**
     * 뷰모델 셋팅
     */
    protected abstract fun setupViewModel(): SparseArray<ViewModel>

    /**
     * 옵저버
     */
    protected abstract fun observer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = transition
        enterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.fade_in_trans)
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedElementReturnTransition = transition
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.fade_out_trans)
    }

    private val transition: Transition
        get() {
            val set = TransitionSet()
            set.ordering = TransitionSet.ORDERING_TOGETHER
            set.addTransition(ChangeBounds())
            set.addTransition(ChangeImageTransform())
            set.addTransition(ChangeTransform())
            return set
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<B>(inflater, onLayoutId(), container, false)
        for (i in 0 until setupViewModel().size()) {
            binding!!.setVariable(setupViewModel().keyAt(i), setupViewModel().valueAt(i))
        }
        binding!!.lifecycleOwner = this
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observer()
    }
}