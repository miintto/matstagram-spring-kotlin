package com.miintto.matstagram.api.place

import com.miintto.matstagram.api.place.service.PlaceService
import com.miintto.matstagram.api.place.service.TagService
import com.miintto.matstagram.common.response.ApiResponse
import com.miintto.matstagram.common.response.code.Http2xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController {

    @Autowired
    private lateinit var placeService: PlaceService

    @Autowired
    private lateinit var tagService: TagService

    @GetMapping("/place/{place-id}")
    fun getPlaceDetail(@PathVariable("place-id") placeId: Long): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, placeService.getPlace(placeId))
    }

    @GetMapping("/place")
    fun getUserPlaceList(auth: Authentication): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, placeService.getPlaceListByUserId(auth.name.toLong()))
    }

    @GetMapping("/tag")
    fun getUserTagList(auth: Authentication): ApiResponse {
        return ApiResponse(Http2xx.SUCCESS, tagService.getTagListByUserId(auth.name.toLong()))
    }
}
