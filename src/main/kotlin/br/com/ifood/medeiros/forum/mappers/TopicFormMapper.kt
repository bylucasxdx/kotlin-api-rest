package br.com.ifood.medeiros.forum.mappers

import br.com.ifood.medeiros.forum.dtos.TopicForm
import br.com.ifood.medeiros.forum.model.Topic
import br.com.ifood.medeiros.forum.services.CourseService
import br.com.ifood.medeiros.forum.services.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService,
) : Mapper<TopicForm, Topic> {

    override fun map(topicForm: TopicForm): Topic {
        return Topic(
            title = topicForm.title,
            message = topicForm.message,
            course = courseService.getById(topicForm.idCourse),
            author = userService.getById(topicForm.idAuthor),
        )
    }
}
