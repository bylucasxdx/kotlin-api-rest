package br.com.ifood.medeiros.forum.mappers

import br.com.ifood.medeiros.forum.dtos.TopicView
import br.com.ifood.medeiros.forum.model.Topic
import org.springframework.stereotype.Component

@Component
class TopicViewMapper: Mapper<Topic, TopicView> {

    override fun map(topic: Topic): TopicView {
        return TopicView(
            id = topic.id,
            title = topic.message,
            message = topic.message,
            status = topic.status,
            createdAt = topic.createdAt
        )
    }


}