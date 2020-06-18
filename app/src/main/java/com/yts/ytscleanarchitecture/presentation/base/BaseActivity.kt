package  com.yts.ytscleanarchitecture.presentation.base

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.ViewAnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.keyIterator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.ViewModel
import com.yts.ytscleanarchitecture.extension.hideKeyboard
import kotlin.math.hypot


abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    private var mLastClickTime: Long = 0

    abstract fun onLayoutId(): Int
    abstract fun setupViewModel(): SparseArray<ViewModel>
    abstract fun observer()

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, onLayoutId())

        if (::binding.isInitialized) {
            for (variableId in setupViewModel().keyIterator()) {
                binding.setVariable(variableId, setupViewModel()[variableId])
            }
            binding.lifecycleOwner = this
            observer()
        }

    }

    fun clickTimeCheck(): Boolean {
        if (System.currentTimeMillis() - mLastClickTime < 700) {
            return true
        }
        mLastClickTime = System.currentTimeMillis()
        return false
    }

    fun startCircularRevealAnimation(textView: TextView) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                var width = textView.textSize * textView.text.length
                var height = textView.textSize

                var startRadius = 0.0f
                var endRadius = hypot(width.toDouble(), height.toDouble())

                Log.e("width", width.toString())
                Log.e("height", height.toString())
                Log.e("startRadius", startRadius.toString())
                Log.e("endRadius", endRadius.toString())

                var animator = ViewAnimationUtils.createCircularReveal(
                    textView,
                    textView.left,
                    textView.right,
                    startRadius,
                    endRadius.toFloat()
                )
                animator.interpolator = FastOutSlowInInterpolator()
                animator.duration = 250
                animator.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        this.hideKeyboard()
        super.onBackPressed()
    }
}
