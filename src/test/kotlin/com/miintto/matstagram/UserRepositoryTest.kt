package com.miintto.matstagram

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
class UserRepositoryTest {

    companion object {

        @JvmStatic
        private val postgresqlContainer = PostgreSQLContainer<Nothing>("postgres:latest").apply {
            withDatabaseName("test-database")
        }

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            postgresqlContainer.start()
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            postgresqlContainer.stop()
        }
    }
}