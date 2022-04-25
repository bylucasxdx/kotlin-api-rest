package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.model.Answer
import br.com.ifood.medeiros.forum.model.Course
import br.com.ifood.medeiros.forum.model.Topic
import br.com.ifood.medeiros.forum.model.User
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class AnswerService(private var answers: List<Answer>) {

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

        val answer = Answer(
            id = 1,
            message = "Primeira resposta",
            author = user,
            topic = topic1,
            solved = false
        )

        answers = Arrays.asList(answer)
    }

    fun list(id: Long): List<Answer> {
        return answers.stream().filter { answer ->
            answer.topic.id == id
        }.collect(Collectors.toList())
    }

}
