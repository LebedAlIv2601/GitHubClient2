package com.example.githubclient2.utils

sealed class Resource<out T>(val data: T?, val message: String?) {

    class Success<T>(data: T?):
        Resource<T>(data = data, message = null)

    class Error<T>(data: T?, message: String?):
        Resource<T>(data = data, message = message)

    class Loading<T>(data: T?):
        Resource<T>(data = data, message = null)
//        fun <T> success(data: T?): Resource<T> = Resource(status = Status.SUCCESS,
//            data = data,
//            message = null)
//
//        fun <T> error(data: T?, message: String?): Resource<T> = Resource(status = Status.ERROR,
//            data = data,
//            message = message)
//
//        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING,
//            data = data,
//            message = null)

}