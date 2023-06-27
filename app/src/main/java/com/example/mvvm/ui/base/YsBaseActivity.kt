package com.example.mvvm.ui.base

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class YsBaseActivity<ViewModel: androidx.lifecycle.ViewModel, binding: ViewBinding>: AppCompatActivity() {
    lateinit var viewModel: ViewModel
    lateinit var binding: binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initViewModel()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        observeViewModel()
        setListener()
        init()
        return super.onCreateView(name, context, attrs)
    }

    abstract fun initViewBinding()
    abstract fun initViewModel()
    abstract fun observeViewModel()
    abstract fun init()
    abstract fun setListener()
}