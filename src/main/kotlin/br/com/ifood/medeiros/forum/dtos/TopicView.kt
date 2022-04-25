package br.com.ifood.medeiros.forum.dtos

import br.com.ifood.medeiros.forum.model.StatusTopic
import java.time.LocalDateTime

data class TopicView (

    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val createdAt: LocalDateTime,

)
