package com.david.cursoiniciacion.springboot.springboot_backend.repositories

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Inscription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface InscripcionsRepository extends JpaRepository<Inscription, Long> {

    List<Inscription> findByUserId(Long userId)   
}