package com.miintto.matstagram.api.home

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun index(): ResponseEntity<Any> {
        return ResponseEntity.ok().body("ok")
    }
}