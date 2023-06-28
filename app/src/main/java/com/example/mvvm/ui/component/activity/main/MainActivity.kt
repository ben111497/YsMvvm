package com.example.mvvm.ui.component.activity.main

import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.data.local.database.DataBase
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.ui.base.YsBaseActivity

class MainActivity: YsBaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, SavedStateViewModelFactory(application, this))[MainViewModel::class.java]
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