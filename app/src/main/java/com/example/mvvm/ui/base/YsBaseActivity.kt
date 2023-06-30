package com.example.mvvm.ui.base

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewbinding.ViewBinding


abstract class YsBaseActivity<ViewModel: androidx.lifecycle.ViewModel, binding: ViewBinding>: AppCompatActivity() {
    lateinit var viewModel: ViewModel
    lateinit var binding: binding
    var mRequestPermission: RequestPermission? = null

    interface RequestPermission {
        fun onCallBack(isSuccess: Boolean, requestCode: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initViewModel()
        observeViewModel()
        setListener()
        init()
    }

    abstract fun initViewBinding()
    abstract fun initViewModel()
    abstract fun observeViewModel()
    abstract fun init()
    abstract fun setListener()

    fun checkAndRequestPermission(permission: String, TAG: Int): Boolean {
        return if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, Array<String>(1) { permission }, TAG)
            false
        } else true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mRequestPermission?.onCallBack(true, requestCode)
        } else {
            mRequestPermission?.onCallBack(false, requestCode)
        }
    }
}