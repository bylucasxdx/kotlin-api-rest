package br.com.ifood.medeiros.forum.repositories

import br.com.ifood.medeiros.forum.dtos.TopicByCategoryDTO
import br.com.ifood.medeiros.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository : JpaRepository<Topic, Long> {

    fun findByCourseName(courseName: String, paginate: Pageable): Page<Topic>

    @Query("SELECT new br.com.ifood.medeiros.forum.dtos.TopicByCategoryDTO(course.category, count(t)) FROM Topic as t JOIN t.course course GROUP BY course.category")
    fun report(): List<TopicByCategoryDTO>

}
