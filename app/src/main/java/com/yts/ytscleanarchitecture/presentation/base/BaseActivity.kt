package  com.yts.ytscleanarchitecture.presentation.base

import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.keyIterator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.yts.ytscleanarchitecture.extension.hideKeyboard


abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    private var mLastClickTime: Long = 0

    abstract fun onLayoutId(): Int
    abstract fun setupViewModel(): SparseArray<ViewModel>
    abstract fun observer()

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {

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

    override fun onBackPressed() {
        this.hideKeyboard()
        super.onBackPressed()
    }
}
