package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.dtos.TopicForm
import br.com.ifood.medeiros.forum.dtos.TopicView
import br.com.ifood.medeiros.forum.mappers.TopicFormMapper
import br.com.ifood.medeiros.forum.mappers.TopicViewMapper
import br.com.ifood.medeiros.forum.model.Topic
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicService(
    private var topics: List<Topic> = ArrayList(),
    private val courseService: CourseService,
    private val userService: UserService,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
) {

    init {
        val course = courseService.getById(1)
        val user = userService.getById(1)

        val topic1 = Topic(
            id = 1,
            title = "Dúvidas Kotlin",
            message = "Variáveis Kotlin",
            course = course,
            author = user,
        )

        topics = Arrays.asList(topic1)
    }

    fun list(): List<TopicView> {
        return topics.stream().map {
            topic -> topicViewMapper.map(topic)
        }.collect(Collectors.toList())
    }

    fun getById(id: Long): TopicView {
        return list().stream().filter { topic ->
            topic.id == id
        }.findFirst().get()
    }

    fun store(topicForm: TopicForm) {
        val topic = topicFormMapper.map(topicForm)
        topic.id = topics.size.toLong() + 1

        topics = topics.plus(topic)
    }

}