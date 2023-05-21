package com.miintto.matstagram.api.user

import com.miintto.matstagram.common.response.APIResponse
import com.miintto.matstagram.common.response.code.Http2xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/{user-id}")
    fun getUser(@PathVariable("user-id") userId: Long): APIResponse {
        return APIResponse(Http2xx.SUCCESS, userService.getUserById(userId))
    }
}