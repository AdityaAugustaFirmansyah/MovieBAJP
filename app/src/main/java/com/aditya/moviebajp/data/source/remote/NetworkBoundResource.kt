package com.aditya.moviebajp.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.aditya.moviebajp.utils.AppExecutors
import com.aditya.moviebajp.vo.Resource

abstract class NetworkBoundResource<ResultType,RequestType>(private val executors:AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource){ it ->
            result.removeSource(dbSource)
            if (shouldFetch(it)){
                fetchFromNetwork(dbSource)
            }else{
                result.addSource(dbSource){
                    result.value = Resource.success(it)
                }
            }
        }
    }

    private fun onFetchFailed(){}
    protected abstract fun loadFromDb():LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType?):Boolean
    protected abstract fun createCall():LiveData<ApiResponse<RequestType>>
    protected abstract fun saveCallResult(data: RequestType)
    private fun fetchFromNetwork(dbSource:LiveData<ResultType>){
        val apiResponse = createCall()
        result.addSource(apiResponse){ it ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(it.status){
                StatusResponse.SUCCESS->{
                    executors.diskIO().execute {
                        it.body?.let { it1 -> saveCallResult(it1) }
                        executors.mainThread().execute{
                            result.addSource(loadFromDb()){
                                result.value = Resource.success(it)
                            }
                        }
                    }
                }
                StatusResponse.EMPTY->executors.mainThread().execute {
                    result.addSource(loadFromDb()) {
                        result.value = Resource.success(it)
                    }
                }

                StatusResponse.ERROR->{
                    onFetchFailed()
                    result.addSource(dbSource){newData->
                        result.value = Resource.error(it.message,newData)
                    }
                }
            }
        }
    }

    fun asLiveData():LiveData<Resource<ResultType>> =result
}