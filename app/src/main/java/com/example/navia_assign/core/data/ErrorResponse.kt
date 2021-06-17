package com.example.navia_assign.core.data

class ErrorResponse(
    var status: String? = null,
    var error: String = "",
    var error_description: String = ""
) : Exception(error)