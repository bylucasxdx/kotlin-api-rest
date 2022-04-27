package br.com.ifood.medeiros.forum.repositories

import br.com.ifood.medeiros.forum.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Long> {
}