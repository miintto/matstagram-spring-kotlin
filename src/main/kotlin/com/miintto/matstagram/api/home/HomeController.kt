package com.miintto.matstagram.api.home

import com.miintto.matstagram.common.response.APIResponse
import com.miintto.matstagram.common.response.code.Http2xx
import org.springframework.boot.SpringBootVersion
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun index(): APIResponse {
        return APIResponse(
            Http2xx.SUCCESS,
            mapOf(
                "java" to System.getProperty("java.version"),
                "kotlin" to KotlinVersion.CURRENT.toString(),
                "springBoot" to SpringBootVersion.getVersion(),
            ),
        )
    }
}
