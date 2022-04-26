package br.com.ifood.medeiros.forum.controller

import br.com.ifood.medeiros.forum.dtos.AnswerForm
import br.com.ifood.medeiros.forum.model.Answer
import br.com.ifood.medeiros.forum.services.AnswerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/topics/{id}/answers")
class AnswerController(private val service: AnswerService) {

    @GetMapping
    fun list(@PathVariable id: Long): List<Answer> {
        return service.list(id)
    }

    @PostMapping
    fun store(@PathVariable id: Long, @RequestBody @Valid answerForm: AnswerForm) {
        service.store(id, answerForm)
    }
}