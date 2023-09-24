package com.miintto.matstagram.manager

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.anyDouble
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.redisson.api.RScoredSortedSet
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class RecentPlaceManagerTests {

    @MockBean
    private lateinit var redissonClient: RedissonClient

    @Autowired
    private lateinit var recentPlaceManager: RecentPlaceManager

    @Test
    @DisplayName("Redis ZSET에 객체 저장")
    fun testAddPlaceId() {
        val userId = 1L
        val placeId = 10L

        @Suppress("UNCHECKED_CAST")
        val sortedSet = mock(RScoredSortedSet::class.java) as RScoredSortedSet<String>
        given(redissonClient.getScoredSortedSet<String>(anyString(), any())).willReturn(sortedSet)

        recentPlaceManager.add(userId, placeId)
    }

    @Test
    @DisplayName("Redis ZSET에 객체 저장시 에러 발생")
    fun testAddPlaceIdWithError() {
        val userId = 1L
        val placeId = 10L

        @Suppress("UNCHECKED_CAST")
        val sortedSet = mock(RScoredSortedSet::class.java) as RScoredSortedSet<String>
        given(redissonClient.getScoredSortedSet<String>(anyString(), any())).willReturn(sortedSet)
        given(sortedSet.add(anyDouble(), anyString())).willThrow()

        recentPlaceManager.add(userId, placeId)
    }

    @Test
    @DisplayName("Redis ZSET에서 객체 가져오기")
    fun testGetMemberList() {
        val userId = 1L

        @Suppress("UNCHECKED_CAST")
        val sortedSet = mock(RScoredSortedSet::class.java) as RScoredSortedSet<String>
        val zrangeList = listOf("2", "4", "3", "1")
        given(redissonClient.getScoredSortedSet<String>(anyString(), any())).willReturn(sortedSet)
        given(sortedSet.valueRangeReversed(0, 10)).willReturn(zrangeList)

        recentPlaceManager.getList(userId)
    }
}
