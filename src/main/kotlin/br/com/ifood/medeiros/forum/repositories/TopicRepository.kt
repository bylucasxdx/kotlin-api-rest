package br.com.ifood.medeiros.forum.repositories

import br.com.ifood.medeiros.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {

    fun findByCourseName(courseName: String, paginate: Pageable): Page<Topic>
}
