package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.model.User
import br.com.ifood.medeiros.forum.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val repository: UserRepository) {

    fun getById(id: Long): User {
        return repository.getById(id)
    }

}