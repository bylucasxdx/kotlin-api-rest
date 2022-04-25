package br.com.ifood.medeiros.forum.controller

import br.com.ifood.medeiros.forum.model.Course
import br.com.ifood.medeiros.forum.model.Topic
import br.com.ifood.medeiros.forum.model.User
import br.com.ifood.medeiros.forum.services.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(): List<Topic> {
        return service.list();
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): Topic {
        return service.getById(id)
    }

}