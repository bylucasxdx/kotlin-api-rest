package br.com.ifood.medeiros.forum.model

import br.com.ifood.medeiros.forum.dtos.TopicView
import java.time.LocalDateTime

object TopicViewTest {
    fun build() = TopicView(
        id = 1,
        title = "Course Name",
        message = "Description",
        status = StatusTopic.NOT_ANSWERED,
        createdAt = LocalDateTime.now(),
    )
}
