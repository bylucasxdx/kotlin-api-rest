package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.model.Course
import br.com.ifood.medeiros.forum.repositories.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {

    fun getById(id: Long): Course {
        return repository.getById(id)
    }

}