package com.yts.ytscleanarchitecture.extension

import android.view.View
import android.widget.ProgressBar

fun View.visible(value: Boolean) {
    if (value) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


fun ProgressBar.showLoading(value: Boolean) {
    var visible = if (value) {
        View.VISIBLE
    } else{
        View.GONE
    }
    this.visibility = visible
}
