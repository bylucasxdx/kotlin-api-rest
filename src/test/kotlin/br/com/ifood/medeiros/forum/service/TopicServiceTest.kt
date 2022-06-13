package br.com.ifood.medeiros.forum.service

import br.com.ifood.medeiros.forum.exceptions.NotFoundException
import br.com.ifood.medeiros.forum.mappers.TopicFormMapper
import br.com.ifood.medeiros.forum.mappers.TopicViewMapper
import br.com.ifood.medeiros.forum.model.Topic
import br.com.ifood.medeiros.forum.model.TopicTest
import br.com.ifood.medeiros.forum.model.TopicViewTest
import br.com.ifood.medeiros.forum.repositories.TopicRepository
import br.com.ifood.medeiros.forum.services.TopicService
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

@SpringBootTest
class TopicServiceTest {

    val topics = PageImpl(
        listOf(TopicTest.build())
    )

    val pagination: Pageable = mockk()

    val topicRepository: TopicRepository = mockk {
        every { findByCourseName(any(), any()) } returns topics
        every { findAll(pagination) } returns topics
    }

    val topicViewMapper: TopicViewMapper = mockk {
        every { map(any()) } returns TopicViewTest.build()
    }

    val topicFormMapper: TopicFormMapper = mockk()

    val topicService = TopicService(
        topicRepository,
        topicViewMapper,
        topicFormMapper,
    )

    @Test
    fun `Should list topics based on course name`() {
        topicService.list("Kotlin", pagination)

        verify(exactly = 1) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 0) { topicRepository.findAll(pagination) }
    }

    @Test
    fun `Should return an equal topic when search for course name`() {
        val topic = TopicTest.build()
        val slot = slot<Topic>()
        every { topicViewMapper.map(capture(slot)) } returns TopicViewTest.build()

        topicService.list("Kotlin", pagination)

        assertThat(slot.captured.title).isEqualTo(topic.title)
        assertThat(slot.captured.message).isEqualTo(topic.message)
        assertThat(slot.captured.status).isEqualTo(topic.status)
    }

    @Test
    fun `Should list all topics when course name is null`() {
        topicService.list(null, pagination)

        verify(exactly = 1) { topicRepository.findAll(pagination) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 0) { topicRepository.findByCourseName(any(), any()) }
    }

    @Test
    fun `Should throw a not found exception when topic does not exists`() {
        every { topicRepository.findById(any()) } returns Optional.empty()

        val exception = assertThrows<NotFoundException> {
            topicService.getById(1)
        }

        assertThat(exception.message).isEqualTo("Topic not found")
    }
}
