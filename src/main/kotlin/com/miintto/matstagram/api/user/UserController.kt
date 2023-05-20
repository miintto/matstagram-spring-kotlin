package com.miintto.matstagram.api.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/{user-pk}")
    fun getUser(@PathVariable("user-pk") userPk: Long): ResponseEntity<Any> {
        val user = userService.getUserById(userPk)
        return ResponseEntity.ok().body(user)
    }
}