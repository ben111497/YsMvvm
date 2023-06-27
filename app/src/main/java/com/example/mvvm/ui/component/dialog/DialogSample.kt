package com.example.mvvm.ui.component.dialog

import android.view.*
import androidx.lifecycle.ViewModel
import com.example.mvvm.databinding.DialogSampleBinding
import com.example.mvvm.ui.base.YsBaseDialogFragment

class DialogSample(private val hint: String): YsBaseDialogFragment<ViewModel, DialogSampleBinding>(0.75) {
    private lateinit var listener: Listener

    interface Listener {
        fun onLeft()
        fun onRight()
    }

    override fun initViewBinding() {
        binding = DialogSampleBinding.inflate(LayoutInflater.from(requireContext()))
    }

    override fun initViewModel() {}

    override fun observeViewModel() {}

    override fun setListener() {
        binding?.run {
            tvLeft.setOnClickListener {
                listener.onLeft()
                dismiss()
            }
            tvRight.setOnClickListener {
                listener.onRight()
                dismiss()
            }
        }
    }

    override fun init() {
        binding?.tvSample?.text = hint
    }

    fun setListener(l: Listener) { listener = l }
}
