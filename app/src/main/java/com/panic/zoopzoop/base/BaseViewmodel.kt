package com.panic.zoopzoop.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

open class BaseViewmodel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun <T> requestApi(single: Single<T>, onSuccess: Consumer<T>, onError: Consumer<in Throwable>) {
        addDisposable(addSchedulers(single).subscribe(onSuccess, onError))
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun <T> addSchedulers(single: Single<T>): Single<T> {
        return single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


    //Progress Bar
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int>
        get() = _progress


    fun showProgress(transparent : Boolean){
        _progress.value = if (transparent) 2 else 1
    }

    fun hideProgress(){
        _progress.value = 0
    }
}