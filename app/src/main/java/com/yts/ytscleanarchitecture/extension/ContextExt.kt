package com.yts.ytscleanarchitecture.extension

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yts.ytscleanarchitecture.R

/**
 * 토스트 만들기
 */
fun Context.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_LONG).show()
}

fun Context.makeToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.log(text: String) {
    Log.e(this.javaClass.simpleName, text)
}

fun Context.createDialog(
    title: String,
    message: String,
    positiveText: String,
    positiveClickListener: DialogInterface.OnClickListener
) {
    var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
    dialog.setTitle(title)
    dialog.setMessage(message)
    dialog.setPositiveButton(positiveText, positiveClickListener)
    dialog.setNegativeButton(R.string.cancel, { dialogInterface, _ -> dialogInterface.dismiss() })
    dialog.show()
}