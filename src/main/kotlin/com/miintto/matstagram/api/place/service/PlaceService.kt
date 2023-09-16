package com.miintto.matstagram.api.place.service

import com.miintto.matstagram.api.place.domain.Place
import com.miintto.matstagram.api.place.domain.repository.PlaceRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.response.code.Http4xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PlaceService {

    @Autowired
    private lateinit var placeRepository: PlaceRepository

    fun getPlace(placeId: Long): Place {
        return placeRepository.findByIdOrNull(placeId) ?: throw ApiException(Http4xx.PLACE_NOT_FOUND)
    }

    fun getPlaceListByUserId(userId: Long): List<Place> {
        return placeRepository.findByUserIdOrderById(userId)
    }
}
