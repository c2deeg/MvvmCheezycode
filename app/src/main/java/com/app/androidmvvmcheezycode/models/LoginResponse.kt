package com.app.androidmvvmcheezycode.models

data class LoginResponse(
    val `data`: Data,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: Int
)