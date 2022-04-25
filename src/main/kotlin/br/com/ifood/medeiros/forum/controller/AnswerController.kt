package br.com.ifood.medeiros.forum.controller

import br.com.ifood.medeiros.forum.model.Answer
import br.com.ifood.medeiros.forum.services.AnswerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics/{id}/answers")
class AnswerController(private val service: AnswerService) {

    @GetMapping
    fun list(@PathVariable id: Long): List<Answer> {
        return service.list(id)
    }
}