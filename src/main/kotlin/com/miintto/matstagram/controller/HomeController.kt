package com.miintto.matstagram.controller

import com.miintto.matstagram.common.response.ApiResponse
import com.miintto.matstagram.common.response.code.Http2xx
import org.springframework.boot.SpringBootVersion
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping("/")
    fun index(): ApiResponse = ApiResponse(
        Http2xx.SUCCESS,
        mapOf(
            "java" to System.getProperty("java.version"),
            "kotlin" to KotlinVersion.CURRENT.toString(),
            "springBoot" to SpringBootVersion.getVersion()
        )
    )
}
