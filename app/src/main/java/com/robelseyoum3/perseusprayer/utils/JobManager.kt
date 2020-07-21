package com.robelseyoum3.perseusprayer.utils

import android.util.Log
import kotlinx.coroutines.Job

open class JobManager(
    private val className: String
) {
    private val TAG: String = "AppDebug"

    private val jobs: HashMap<String, Job> = HashMap()

    fun addJob(methodName: String, job: Job){
        cancelJob(methodName)
        jobs[methodName] = job
    }

    private fun cancelJob(methodName: String) {
        getJob(methodName)?.cancel()
    }

    private fun getJob(methodName: String): Job? {
        if (jobs.containsKey(methodName)) {
            jobs[methodName]?.let {
                return it
            }
        }
        return null
    }


    fun cancelActiveJobs(){
        for((methodName, job) in jobs){
            if(job.isActive){
                Log.e(TAG, "$className: cancelling job in method: $methodName .")
                job.cancel()
            }
        }
    }

}