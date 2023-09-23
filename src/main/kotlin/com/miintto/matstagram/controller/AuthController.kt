package com.miintto.matstagram.controller

import com.miintto.matstagram.dto.LoginInfo
import com.miintto.matstagram.dto.RegisterInfo
import com.miintto.matstagram.service.LoginService
import com.miintto.matstagram.service.RegisterService
import com.miintto.matstagram.common.response.ApiResponse
import com.miintto.matstagram.common.response.code.Http2xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var registerService: RegisterService

    @Autowired
    private lateinit var loginService: LoginService

    @PostMapping("/signup")
    @ResponseBody
    fun registerUser(
        @RequestBody
        @Valid
        registerInfo: RegisterInfo
    ): ApiResponse {
        return ApiResponse(Http2xx.CREATED, registerService.run(registerInfo))
    }

    @PostMapping("/login")
    fun login(
        @RequestBody
        @Valid
        loginInfo: LoginInfo
    ): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, loginService.run(loginInfo))
    }
}
