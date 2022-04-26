package br.com.ifood.medeiros.forum.exceptions

import java.lang.RuntimeException

class NotFoundException(message: String?) : RuntimeException(message) {
}