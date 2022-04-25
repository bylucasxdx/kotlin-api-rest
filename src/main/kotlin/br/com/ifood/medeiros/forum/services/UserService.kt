package br.com.ifood.medeiros.forum.services

import br.com.ifood.medeiros.forum.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(var users: List<User>) {

    init {
        val user = User(
            id = 1,
            name = "Lucas",
            email = "lucas.medeiros@teste.com"
        )

        users = Arrays.asList(user)
    }

    fun getById(id: Long): User {
        return users.stream().filter {
                user -> user.id == id
        }.findFirst().get()
    }

}