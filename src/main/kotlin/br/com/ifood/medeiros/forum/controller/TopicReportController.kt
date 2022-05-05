package br.com.ifood.medeiros.forum.controller

import br.com.ifood.medeiros.forum.dtos.TopicByCategoryDTO
import br.com.ifood.medeiros.forum.repositories.TopicRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics/report")
class TopicReportController(val repository: TopicRepository) {

    @GetMapping
    fun report(): List<TopicByCategoryDTO> {
        return repository.report()
    }
}
