package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.dtos.TopicForm
import br.com.ifood.medeiros.forum.dtos.TopicView
import br.com.ifood.medeiros.forum.dtos.UpdateTopicForm
import br.com.ifood.medeiros.forum.exceptions.NotFoundException
import br.com.ifood.medeiros.forum.mappers.TopicFormMapper
import br.com.ifood.medeiros.forum.mappers.TopicViewMapper
import br.com.ifood.medeiros.forum.repositories.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
) {

    fun list(courseName: String?, paginate: Pageable): Page<TopicView> {
        val topics = if (courseName == null) {
            repository.findAll(paginate)
        } else {
            repository.findByCourseName(courseName, paginate)
        }

        return topics.map {
                topic ->
            topicViewMapper.map(topic)
        }
    }

    fun getById(id: Long): TopicView {
        val topic = repository
            .findById(id)
            .orElseThrow { NotFoundException("Topic not found") }

        return topicViewMapper.map(topic)
    }

    fun store(topicForm: TopicForm): TopicView {
        val topic = topicFormMapper.map(topicForm)
        repository.save(topic)

        return topicViewMapper.map(topic)
    }

    fun update(topicForm: UpdateTopicForm): TopicView {
        val topic = repository.findById(topicForm.id)
            .orElseThrow { NotFoundException("Topic not found") }

        topic.title = topicForm.title
        topic.message = topicForm.message

        return topicViewMapper.map(topic)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}
