package com.robelseyoum3.perseusprayer.ui

import android.util.Log
import android.widget.Toast
import com.robelseyoum3.perseusprayer.utils.DataState
import com.robelseyoum3.perseusprayer.utils.displayToast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class BaseActivity : DaggerAppCompatActivity(), DataStateChangeListener {

    val TAG: String = "AppDebug"

    override fun onDataStateChange(dataState: DataState<*>?) {

        dataState?.let {
            GlobalScope.launch(Dispatchers.Main) {

                //for loading
                displayProgressBar(it.loading.isLoading)

                //check error case
                it.error?.let {errorEvent ->
                    handleStateError(errorEvent)
                }

                //for success data
                it.data?.let {
                    it.response?.let { responseEvent ->
                        handleStateResponse(responseEvent)
                    }
                }
            }
        }
    }

    private fun handleStateError(errorEvent: Event<StateError>) {
        errorEvent.getContentIfNotHandled()?.let {
            when(it.response.responseType){
                is ResponseType.Toast -> {
                    it.response.message?.let { message ->
                        displayToast(message)
                    }
                }

                is ResponseType.None -> {
                    Log.e(TAG, "handleStateError: ${it.response.message}")
                }
            }
        }
    }

    private fun handleStateResponse(responseEvent: Event<Response>) {
        responseEvent.getContentIfNotHandled()?.let {
            when(it.responseType){
                is ResponseType.Toast -> {
                    it.message?.let { message ->
                        displayToast(message)
                    }
                }

                is ResponseType.None -> {
                    Log.e(TAG, "handleStateError: ${it.message}")
                }
            }
        }
    }

        abstract fun displayProgressBar(boolean: Boolean)

}