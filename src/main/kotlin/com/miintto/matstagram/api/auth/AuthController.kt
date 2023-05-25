package com.miintto.matstagram.api.auth

import com.miintto.matstagram.api.auth.dto.RegisterInfo
import com.miintto.matstagram.common.response.APIResponse
import com.miintto.matstagram.common.response.code.Http2xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var userService: AuthService

    @PostMapping("/signup")
    fun registerUser(@RequestBody @Valid registerInfo: RegisterInfo): APIResponse {
        return APIResponse(Http2xx.CREATED, userService.register(registerInfo))
    }
}
