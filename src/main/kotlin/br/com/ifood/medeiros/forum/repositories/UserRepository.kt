package br.com.ifood.medeiros.forum.repositories

import br.com.ifood.medeiros.forum.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}