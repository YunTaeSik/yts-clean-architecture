package com.yts.ytscleanarchitecture.utils

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridLayoutManagerWrapper(var context: Context?, val count: Int) :
    GridLayoutManager(context, count) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("TAG", "meet a IOOBE in RecyclerView")
        }
    }

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}