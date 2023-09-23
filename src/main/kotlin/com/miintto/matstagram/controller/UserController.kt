package com.miintto.matstagram.controller

import com.miintto.matstagram.service.UserRecentViewService
import com.miintto.matstagram.service.UserService
import com.miintto.matstagram.common.response.ApiResponse
import com.miintto.matstagram.common.response.code.Http2xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userRecentViewService: UserRecentViewService

    @GetMapping("/profile")
    fun getUserProfile(auth: Authentication): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, userService.getUserById(auth.name.toLong()))
    }

    @GetMapping("/{user-id}")
    fun getUser(@PathVariable("user-id") userId: Long): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, userService.getUserById(userId))
    }

    @GetMapping("recent/view")
    fun getUserRecentView(auth: Authentication): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, userRecentViewService.getUserRecentView(auth.name.toLong()))
    }
}
