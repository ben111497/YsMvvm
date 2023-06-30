package com.example.mvvm.ui.component.activity.sample

import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.data.local.database.DataBase
import com.example.mvvm.databinding.ActivitySampleBinding
import com.example.mvvm.ui.base.YsBaseActivity

class SampleActivity: YsBaseActivity<SampleViewModel, ActivitySampleBinding>() {
    override fun initViewBinding() {
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, SavedStateViewModelFactory(application, this))[SampleViewModel::class.java]
    }

    override fun observeViewModel() {}

    override fun init() {
        DataBase.instance(this)

        /** Permission Sample
        checkAndRequestPermission(Manifest.permission.ACCESS_COARSE_LOCATION, 0)
            mRequestPermission = object: RequestPermission {
            override fun onCallBack(isSuccess: Boolean, requestCode: Int) {
            Log.e("RequestPermission", "$isSuccess")
            Log.e("Tag", "$requestCode")
            }
        }
         */
    }

    override fun setListener() {}
}