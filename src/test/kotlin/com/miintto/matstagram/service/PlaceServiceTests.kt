package com.miintto.matstagram.service

import com.miintto.matstagram.domain.Place
import com.miintto.matstagram.domain.repository.PlaceRepository
import com.miintto.matstagram.manager.RecentProductManager
import com.miintto.matstagram.common.exception.ApiException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.util.Optional

@ActiveProfiles("test")
@SpringBootTest
class PlaceServiceTests {

    @MockBean
    private lateinit var placeRepository: PlaceRepository

    @MockBean
    private lateinit var recentProductManager: RecentProductManager

    @Autowired
    private lateinit var placeService: PlaceService

    @Test
    @DisplayName("장소 상세 조회")
    fun testSearchPlace() {
        val userId = 1L
        val placeId = 1L
        val place = Place(id = placeId, userId = userId, placeName = "장소1", placeType = "restaurant")
        given(placeRepository.findById(placeId)).willReturn(Optional.of(place))

        placeService.searchPlace(userId, placeId)

        verify(placeRepository).findById(placeId)
    }

    @Test
    @DisplayName("장소 상세 조회 - 존재하지 않는 id")
    fun testSearchPlaceNotFount() {
        val placeId = 2L
        given(placeRepository.findById(placeId)).willReturn(Optional.empty())

        assertThrows<ApiException> {
            placeService.searchPlace(1, placeId)
        }
    }

    @Test
    @DisplayName("장소 리스트 조회")
    fun testSearchPlaceList() {
        val placeId = 3L
        val userId = 1L
        val place = Place(id = placeId, userId = userId, placeName = "장소1", placeType = "restaurant")
        given(placeRepository.findByUserIdOrderById(userId)).willReturn(listOf(place))

        placeService.getPlaceListByUserId(userId)

        verify(placeRepository).findByUserIdOrderById(userId)
    }
}
