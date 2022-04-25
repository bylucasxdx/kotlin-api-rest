package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.model.Course
import br.com.ifood.medeiros.forum.model.Topic
import br.com.ifood.medeiros.forum.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicService(private var topics: List<Topic>) {

    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programação"
        )

        val user = User(
            id = 1,
            name = "Lucas",
            email = "lucas.medeiros@teste.com"
        )

        val topic1 = Topic(
            id = 1,
            title = "Dúvidas Kotlin",
            message = "Variáveis Kotlin",
            course = course,
            author = user,
        )

        val topic2 = Topic(
            id = 2,
            title = "Dúvidas 2 Kotlin",
            message = "Variáveis Kotlin",
            course = course,
            author = user,
        )

        val topic3 = Topic(
            id = 3,
            title = "Dúvidas 3 Kotlin",
            message = "Variáveis 3 Kotlin",
            course = course,
            author = user,
        )

        topics = Arrays.asList(topic1, topic2, topic3)
    }

    fun list(): List<Topic> {
        return topics
    }

    fun getById(id: Long): Topic {
        return topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().get()
    }

}