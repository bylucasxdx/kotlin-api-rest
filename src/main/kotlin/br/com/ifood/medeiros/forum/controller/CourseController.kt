package br.com.ifood.medeiros.forum.controller

import br.com.ifood.medeiros.forum.model.Course
import br.com.ifood.medeiros.forum.repositories.CourseRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
class CourseController(private val repository: CourseRepository) {

    @GetMapping
    fun list(): List<Course> {
        return repository.findAll()
    }

    @PostMapping
    fun store(@RequestBody item: Course) {
        repository.save(item)
    }
}