package com.example.apistructure.data.api

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Error1 {

    const val NO_INTER_CONNECTION = "no internet connection"
    const val CONNECTION_TIMEOUT = "connection timeout"
    const val FAILED_TO_REACHED_NETWORK = "Check Your Connection"
    const val UNAUTHORIZED_INVALID_CREDENTIALS = "Unauthorized: Invalid credentials"
    const val FORBIDDEN_ACCESS_DENIED = "Forbidden: Access denied"
    const val NOT_FOUND_REQUESTED_RESOURCE_NOT_FOUND = "Not Found: Requested resource not found"
    const val FAILED_TO_CONNECTE_TO_SERVER = "Failed to connect to server"
    //    const val WRONG_USERNAME_OR_PASSWORD= "Wrong username or password!"
    const val PLEASE_FILL_IN_ALL_FIELDS= "Please, fill in all the fields"

    fun handleException(throwable: Throwable):String {
        return when (throwable) {
            is ConnectException -> {
                NO_INTER_CONNECTION

            }

            is SocketTimeoutException -> {
                CONNECTION_TIMEOUT
            }

            is UnknownHostException -> {
                FAILED_TO_REACHED_NETWORK
            }

            is HttpException -> {
                when (throwable.code()) {
                    401 -> {
                        // HTTP 401 Unauthorized: Invalid credentials
                        UNAUTHORIZED_INVALID_CREDENTIALS
                    }
                    403 -> {
                        // HTTP 403 Forbidden: Access denied
                        FORBIDDEN_ACCESS_DENIED
                    }
                    404 -> {
                        // HTTP 404 Not Found: Requested resource not found
                        NOT_FOUND_REQUESTED_RESOURCE_NOT_FOUND
                    }
                    // Add more cases for other HTTP error codes if needed
                    else -> {
                        // Handle other HTTP error codes with a generic message
                        FAILED_TO_CONNECTE_TO_SERVER
                    }
                }
            }

            else -> {
                ""
            }
        }
    }

}