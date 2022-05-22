package com.panic.zoopzoop.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

/**
 * Base Activity
 *
 * @author panicmg@gmail.com
 * @param T DataBinding
 * @param R ViewModel
 * @since 22.03.26
 */
abstract class BaseActivity<T : ViewDataBinding, R : BaseViewmodel> : AppCompatActivity() {

    lateinit var viewDataBinding: T
    abstract val layoutResourceId: Int
    abstract val baseViewModel: R

    /**
     * Init
     *
     * set init data here
     * (ex) intent)
     */
    abstract fun init()

    /**
     * Set Observer
     *
     * set viewmodel observer here
     */
    abstract fun setObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

        init()
        setObserver()
        observeProgress()
    }

    /**
     * Progress Control
     *
     * type :
     * 0 -> hide progress
     * 1 -> show progress with clear background
     * 2 -> show progress with transparent background
     */
    private fun observeProgress(){
        baseViewModel.progress.observe(this, Observer { type ->
            when(type){
                //show progress with clear background
                1 -> {

                }
                //show progress with transparent background
                2 -> {

                }
                //hide progress
                else -> {

                }
            }
        })
    }
}