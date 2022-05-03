package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.dtos.AnswerForm
import br.com.ifood.medeiros.forum.model.Answer
import br.com.ifood.medeiros.forum.model.Topic
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AnswerService(
    private var answers: List<Answer>,
    private val courseService: CourseService,
    private val userService: UserService,
) {

    fun list(id: Long): List<Answer> {
        return answers.stream().filter { answer ->
            answer.topic.id == id
        }.collect(Collectors.toList())
    }

    fun store(id: Long, answerForm: AnswerForm) {
        val course = courseService.getById(1)
        val user = userService.getById(1)

        val topic = Topic(
            id = 1,
            title = "Dúvidas Kotlin",
            message = "Variáveis Kotlin",
            course = course,
            author = user,
        )

        val answer = Answer(
            id = answers.size.toLong() + 1,
            message = answerForm.message,
            topic = topic,
            author = userService.getById(answerForm.idAuthor),
            solved = false
        )

        answers = answers.plus(answer)
    }
}
