package com.david.cursoiniciacion.springboot.springboot_backend.repositories

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Role
import org.springframework.data.jpa.repository.JpaRepository



interface RolesRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name)
}
