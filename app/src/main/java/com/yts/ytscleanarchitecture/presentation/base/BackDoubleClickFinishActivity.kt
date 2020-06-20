package com.yts.ytscleanarchitecture.presentation.base

import androidx.databinding.ViewDataBinding
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.extension.hideKeyboard
import com.yts.ytscleanarchitecture.extension.makeToast

abstract class BackDoubleClickFinishActivity<B : ViewDataBinding> : BaseActivity<B>() {
    private val TIME_INTERVAL = 2000
    private var mBackPressed: Long = 0


    //두번클릭시 종료
    override fun onBackPressed() {
        hideKeyboard()
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            this.makeToast(R.string.msg_quit)
        }
        mBackPressed = System.currentTimeMillis()
    }
}