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
        //viewModel.repository.name = SharedPreferences(application).getName()
    }

    override fun setListener() {}
}