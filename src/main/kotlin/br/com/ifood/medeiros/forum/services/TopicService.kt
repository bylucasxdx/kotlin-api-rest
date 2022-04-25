package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.dtos.TopicForm
import br.com.ifood.medeiros.forum.dtos.TopicView
import br.com.ifood.medeiros.forum.model.Topic
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicService(
    private var topics: List<Topic> = ArrayList(),
    private val courseService: CourseService,
    private val userService: UserService,
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
        return topics.stream().map { topic -> TopicView(
            id = topic.id,
            title = topic.message,
            message = topic.message,
            status = topic.status,
            createdAt = topic.createdAt
        ) }.collect(Collectors.toList())
    }

    fun getById(id: Long): TopicView {
        return list().stream().filter { topic ->
            topic.id == id
        }.findFirst().get()
    }

    fun store(topicForm: TopicForm): Topic {
        val topic = Topic(
            id = topics.size.toLong() + 1,
            title = topicForm.title,
            message = topicForm.message,
            course = courseService.getById(topicForm.idCourse),
            author = userService.getById(topicForm.idAuthor),
        )

        topics = topics.plus(topic)

        return topic
    }

}