package com.miintto.matstagram.controller

import com.miintto.matstagram.common.response.ApiResponse
import com.miintto.matstagram.common.response.code.Http2xx
import com.miintto.matstagram.service.TagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tag")
class TagController {

    @Autowired
    private lateinit var tagService: TagService

    @GetMapping("")
    fun getUserTagList(auth: Authentication): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, tagService.getTagListByUserId(auth.name.toLong()))
    }
}
