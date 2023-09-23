package com.miintto.matstagram.controller

import com.miintto.matstagram.service.PlaceService
import com.miintto.matstagram.common.response.ApiResponse
import com.miintto.matstagram.common.response.code.Http2xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/place")
class PlaceController {

    @Autowired
    private lateinit var placeService: PlaceService

    @GetMapping("/{place-id}")
    fun getPlaceDetail(@PathVariable("place-id") placeId: Long, auth: Authentication): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, placeService.searchPlace(auth.name.toLong(), placeId))
    }

    @GetMapping("")
    fun getUserPlaceList(auth: Authentication): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, placeService.getPlaceListByUserId(auth.name.toLong()))
    }
}
