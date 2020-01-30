package com.yts.ytscleanarchitecture.utils

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

class LinearLayoutManagerWrapper(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {


    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}
