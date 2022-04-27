package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.dtos.TopicForm
import br.com.ifood.medeiros.forum.dtos.TopicView
import br.com.ifood.medeiros.forum.dtos.UpdateTopicForm
import br.com.ifood.medeiros.forum.exceptions.NotFoundException
import br.com.ifood.medeiros.forum.mappers.TopicFormMapper
import br.com.ifood.medeiros.forum.mappers.TopicViewMapper
import br.com.ifood.medeiros.forum.model.Topic
import br.com.ifood.medeiros.forum.repositories.TopicRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
) {

    fun list(): List<TopicView> {
        return repository.findAll().stream().map {
            topic -> topicViewMapper.map(topic)
        }.collect(Collectors.toList())
    }

    fun getById(id: Long): TopicView {
        val topic = repository
            .findById(id)
            .orElseThrow { NotFoundException("Topic not found") }

        return topicViewMapper.map(topic)
    }

    fun store(topicForm: TopicForm): TopicView {
        val topic = topicFormMapper.map(topicForm)
        repository.save(topic);

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