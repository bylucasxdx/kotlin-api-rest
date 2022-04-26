package br.com.ifood.medeiros.forum.dtos

import javax.validation.constraints.NotEmpty

data class AnswerForm (

    @field:NotEmpty
    val message: String,

    val idAuthor: Long,
)