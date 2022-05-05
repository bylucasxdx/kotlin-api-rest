package br.com.ifood.medeiros.forum.controller

import br.com.ifood.medeiros.forum.dtos.TopicForm
import br.com.ifood.medeiros.forum.dtos.TopicView
import br.com.ifood.medeiros.forum.dtos.UpdateTopicForm
import br.com.ifood.medeiros.forum.services.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    @Cacheable("topic-list")
    fun list(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) paginate: Pageable
    ): Page<TopicView> {
        return service.list(courseName, paginate)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): TopicView {
        return service.getById(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topic-list"], allEntries = true)
    fun store(
        @RequestBody @Valid dto: TopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topic = service.store(dto)
        val uri = uriBuilder.path("/topics/${topic.id}").build().toUri()

        return ResponseEntity.created(uri).body(topic)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topic-list"], allEntries = true)
    fun update(@RequestBody @Valid topicForm: UpdateTopicForm): ResponseEntity<TopicView> {
        val updatedTopic = service.update(topicForm)
        return ResponseEntity.ok(updatedTopic)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["topic-list"], allEntries = true)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}
