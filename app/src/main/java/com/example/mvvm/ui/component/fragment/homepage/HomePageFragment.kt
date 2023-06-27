package com.example.mvvm.ui.component.fragment.homepage

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.databinding.FragmentSampleBinding
import com.example.mvvm.ui.base.YsBaseFragment
import com.example.mvvm.ui.factory.BaseModelFactory
import com.example.mvvm.helper.observe
import com.example.mvvm.helper.setOnBackPressed
import com.example.mvvm.helper.showToast
import com.example.mvvm.ui.component.dialog.DialogSample
import com.example.mvvm.ui.component.dialog.ShowMessageDialog
import kotlinx.coroutines.DelicateCoroutinesApi
@DelicateCoroutinesApi
class HomePageFragment: YsBaseFragment<HomepageViewModel, FragmentSampleBinding>() {
    override fun initViewModel() {
        viewModel = ViewModelProviders.of(requireActivity(), BaseModelFactory(requireActivity()))[HomepageViewModel::class.java]
    }

    override fun initViewBinding() {
        binding = FragmentSampleBinding.inflate(layoutInflater)
    }

    override fun argument(bundle: Bundle?) {}

    override fun observeViewModel() {
        observe(viewModel.number) {
            binding?.run {
                tvNumber.text = "${it}"
            }
        }
    }

    override fun init() {
        viewModel.getLocalNumber()
    }

    override fun setListener() {
        requireActivity().setOnBackPressed(this) {
            ShowMessageDialog<ViewModel> {
                it.setTitle("此路不通")
                it.setListener(object: ShowMessageDialog.Listener {
                    override fun onOk() {
                        it.dismiss()
                    }
                })
            }.show(requireActivity().supportFragmentManager, "message")
        }

        binding?.run {
            btnAdd.setOnClickListener { viewModel.addNumber("", 1) }

            btnDecrease.setOnClickListener { viewModel.addNumber("", -1) }

            btnDialog.setOnClickListener {
                DialogSample(tvNumber.text.toString()).also {
                    it.setListener(object: DialogSample.Listener {
                        override fun onLeft() {
                            viewModel.addNumber("", -1)
                        }

                        override fun onRight() {
                            viewModel.addNumber("", 1)
                        }
                    })
                }.show(requireActivity().supportFragmentManager, "hi")
            }

            btnToast.setOnClickListener { context?.showToast(tvNumber.text.toString()) }
        }
    }
}