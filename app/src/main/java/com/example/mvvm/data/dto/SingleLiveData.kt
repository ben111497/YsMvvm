package com.example.mvvm.data.dto

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveData<T>: MutableLiveData<T>() {
    private var mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) Log.e("SingleLiveEvent", "Multiple observers registered but only one will be notified of changes.")
        // Observe the internal MutableLiveData
        super.observe(owner) { t -> if (mPending.compareAndSet(true, false)) { observer.onChanged(t) } }
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }
    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() { value = null }
}