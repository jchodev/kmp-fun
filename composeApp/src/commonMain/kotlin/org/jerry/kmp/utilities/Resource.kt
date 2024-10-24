package org.jerry.kmp.utilities

sealed class Resource<T>(val data: T?, val throwableWithMessage: ThrowableWithMessage?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(throwableWithMessage: ThrowableWithMessage) : Resource<T>(null, throwableWithMessage)
}

data class ThrowableWithMessage(
    val throwable: Throwable? = null,
    val message: String? = null,
) {
    fun displayError(): String {
        return when {
            !message.isNullOrBlank() -> message
            throwable !=null &&  !throwable.message.isNullOrBlank() -> throwable.message!!
            else -> "Unknown error"
        }
    }
}