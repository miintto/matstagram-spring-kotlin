package com.miintto.matstagram.api.user

import com.miintto.matstagram.api.place.manager.RecentProductManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserRecentViewService {

    @Autowired
    private lateinit var recentProductManager: RecentProductManager

    fun getUserRecentView(userId: Long): List<Long> {
        return recentProductManager.getList(userId).toList()
    }
}
