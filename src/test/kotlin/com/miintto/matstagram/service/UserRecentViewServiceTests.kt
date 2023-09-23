package com.miintto.matstagram.service

import com.miintto.matstagram.domain.projection.PlaceSummary
import com.miintto.matstagram.domain.repository.PlaceRepository
import com.miintto.matstagram.manager.RecentPlaceManager
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class UserRecentViewServiceTests {

    data class PlaceSummaryImp(
        override val id: Long,
        override val placeName: String,
        override val description: String,
        override val lng: Float?,
        override val lat: Float?,
        override val imageUrl: String?
    ) : PlaceSummary

    @MockBean
    private lateinit var placeRepository: PlaceRepository

    @MockBean
    private lateinit var recentPlaceManager: RecentPlaceManager

    @Autowired
    private lateinit var userRecentViewService: UserRecentViewService

    @Test
    @DisplayName("최근 조회 목록")
    fun testRecentPlaceList() {
        val userId = 1L
        val placeIdList = listOf(1L, 3L, 2L)
        val placeList = listOf(
            PlaceSummaryImp(id = 1, placeName = "장소1", description = "", lng = null, lat = null, imageUrl = null),
            PlaceSummaryImp(id = 2, placeName = "장소2", description = "", lng = null, lat = null, imageUrl = null),
            PlaceSummaryImp(id = 3, placeName = "장소3", description = "", lng = null, lat = null, imageUrl = null)
        )

        given(recentPlaceManager.getList(userId)).willReturn(placeIdList)
        given(placeRepository.findByIdIn(placeIdList)).willReturn(placeList)

        val result = userRecentViewService.getUserRecentView(userId)

        verify(recentPlaceManager).getList(userId)
        verify(placeRepository).findByIdIn(placeIdList)
        assert(result.size == 3)
    }

    @Test
    @DisplayName("최근 조회 목록 없는 경우")
    fun testEmptyRecentPlaceList() {
        val userId = 1L
        given(recentPlaceManager.getList(userId)).willReturn(listOf())

        val result = userRecentViewService.getUserRecentView(userId)

        verify(recentPlaceManager).getList(userId)
        assert(result.isEmpty())
    }
}
