package com.miintto.matstagram.api.auth.dto

data class RegisterInfo(
    var userName: String,
    var userEmail: String,
    var password: String,
    var passwordAgain: String
)
