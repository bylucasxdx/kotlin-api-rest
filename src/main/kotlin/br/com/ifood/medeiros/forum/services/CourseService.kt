package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.model.Course
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseService(var courses: List<Course>) {

    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programação"
        )

        courses = Arrays.asList(course)
    }

    fun getById(id: Long): Course {
        return courses.stream().filter {
            course -> course.id == id
        }.findFirst().get()
    }

}