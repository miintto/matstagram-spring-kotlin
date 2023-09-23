package com.miintto.matstagram.api.place.manager

import mu.KotlinLogging
import org.redisson.api.RScoredSortedSet
import org.redisson.api.RedissonClient
import org.redisson.client.codec.StringCodec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset

private val logger = KotlinLogging.logger {}

@Component
class RecentProductManager {

    @Autowired
    private lateinit var redissonClient: RedissonClient

    private fun getKey(userId: Long) = "recent-view-$userId"

    private fun getSortedSet(userId: Long): RScoredSortedSet<Long> {
        return redissonClient.getScoredSortedSet(getKey(userId), StringCodec.INSTANCE)
    }

    fun add(userId: Long, placeId: Long) {
        try {
            val sortedSet = getSortedSet(userId)
            val now = LocalDateTime.now()
            sortedSet.add(now.toEpochSecond(ZoneOffset.UTC).toDouble(), placeId)
        } catch (e: Exception) {
            logger.error("Redis SortedSet 에러 발생 - user=$userId place=$placeId", e)
        }
    }

    fun getList(userId: Long): Collection<Long> {
        return getSortedSet(userId).valueRangeReversed(0, 10)
    }
}
