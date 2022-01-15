package com.aditya.moviebajp.vo

data class Resource<T>(
    val status: Status,
    val data:T?,
    val message:String?
){
    companion object{
        fun <T> success(data:T?):Resource<T> = Resource(Status.SUCCESS,data,null)
        fun <T> error(msg:String?,data:T?):Resource<T> = Resource(Status.FAILURE,data,msg)
        fun <T> loading(data:T?):Resource<T> = Resource(Status.LOADING,data,null)
    }
}
