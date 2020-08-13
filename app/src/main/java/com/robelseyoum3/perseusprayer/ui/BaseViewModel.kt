package com.robelseyoum3.perseusprayer.ui

import androidx.lifecycle.ViewModel
import com.robelseyoum3.perseusprayer.concurrency.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel constructor(
    private val dispatchers: AppDispatchers
) : ViewModel(), CoroutineScope {

    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job


    fun dispose(){
        job.cancelChildren()
    }
}