package com.miintto.matstagram.service

import com.miintto.matstagram.domain.projection.PlaceSummary
import com.miintto.matstagram.domain.repository.PlaceRepository
import com.miintto.matstagram.manager.RecentProductManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserRecentViewService {

    @Autowired
    private lateinit var placeRepository: PlaceRepository

    @Autowired
    private lateinit var recentProductManager: RecentProductManager

    fun getUserRecentView(userId: Long): List<PlaceSummary> {
        val placeIdList = recentProductManager.getList(userId).map { str -> str.toLong() }
        if (placeIdList.isEmpty()) {
            return listOf()
        }
        return placeRepository.findByIdIn(placeIdList).sortedBy { place -> placeIdList.indexOf(place.id) }
    }
}
