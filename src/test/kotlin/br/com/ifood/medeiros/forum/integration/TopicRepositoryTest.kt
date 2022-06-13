package br.com.ifood.medeiros.forum.integration

import br.com.ifood.medeiros.forum.dtos.TopicByCategoryDTO
import br.com.ifood.medeiros.forum.model.TopicTest
import br.com.ifood.medeiros.forum.repositories.TopicRepository
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {

    @Autowired
    private lateinit var topicRepository: TopicRepository

    private val topic = TopicTest.build()

    companion object {
        @Container
        private val mySQLContainer = MySQLContainer<Nothing>("mysql:8.0.28").apply {
            withDatabaseName("test-database-name")
            withUsername("test-username")
            withPassword("test-password")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mySQLContainer::getUsername)
            registry.add("spring.datasource.password", mySQLContainer::getPassword)
        }
    }

    @Test
    fun `Should return an report`() {
        topicRepository.save(topic)

        val report = topicRepository.report()
        assertThat(report).isNotNull
        assertThat(report.first()).isExactlyInstanceOf(TopicByCategoryDTO::class.java)
    }
}
