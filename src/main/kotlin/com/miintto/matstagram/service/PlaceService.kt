package com.miintto.matstagram.service

import com.miintto.matstagram.domain.Place
import com.miintto.matstagram.domain.repository.PlaceRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.manager.RecentProductManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PlaceService {

    @Autowired
    private lateinit var placeRepository: PlaceRepository

    @Autowired
    private lateinit var recentProductManager: RecentProductManager

    fun searchPlace(userId: Long, placeId: Long): Place {
        val place = placeRepository.findByIdOrNull(placeId) ?: throw ApiException(Http4xx.PLACE_NOT_FOUND)
        recentProductManager.add(userId, placeId)
        return place
    }

    fun getPlaceListByUserId(userId: Long): List<Place> {
        return placeRepository.findByUserIdOrderById(userId)
    }
}
