package com.david.cursoiniciacion.springboot.springboot_backend.repositories

import com.david.cursoiniciacion.springboot.springboot_backend.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UsersRepository extends JpaRepository<User, Long> {
    Optional <User>findByEmail(String email)

}