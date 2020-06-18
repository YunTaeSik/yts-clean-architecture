package com.yts.ytscleanarchitecture.presentation.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.SparseArray
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yts.ytscleanarchitecture.BR
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.SearchBinding
import com.yts.ytscleanarchitecture.extension.*
import com.yts.ytscleanarchitecture.presentation.base.BackDoubleClickFinishActivity
import com.yts.ytscleanarchitecture.utils.EndlessRecyclerOnScrollListener
import com.yts.ytscleanarchitecture.utils.LinearLayoutManagerWrapper
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BackDoubleClickFinishActivity<SearchBinding>(), View.OnClickListener {
    private val searchAdapter: SearchAdapter by inject()
    private val model: SearchViewModel by viewModel()

    override fun onLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun setupViewModel(): SparseArray<ViewModel> {
        val setupViewModel = SparseArray<ViewModel>()
        setupViewModel.put(BR.model, model)
        return setupViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun initView() {
        btn_text_delete.setOnClickListener(this)
        settingSearchList()
    }

    private fun settingSearchList() {
        list_search.layoutManager = LinearLayoutManagerWrapper(this, RecyclerView.VERTICAL, false)
        list_search.adapter = searchAdapter

        /**
         * 터치시 키보드 가림
         */
        list_search.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

        /**
         * Load More 함수
         */
        list_search.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(list_search.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                log("onLoadMore page = $page")
                binding.model?.setPage(page)
                binding.model?.getImages()
            }

        })
    }

    override fun observer() {
        model.isLoading.observe(this, Observer {
            loading.showLoading(it)
        })
        model.toastMessageId.observe(this, Observer {
            makeToast(it)
        })

        edit_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                btn_text_delete.visible(s != null && s.isNotEmpty())

                binding.model?.search(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        model.listDocument.observe(this, Observer {
            searchAdapter.submitList(it)
            searchAdapter.notifyDataSetChanged()
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_text_delete -> {
                edit_search.setText("")
            }
        }
    }
}