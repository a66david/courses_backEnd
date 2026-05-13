package com.david.cursoiniciacion.springboot.springboot_backend.entities

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false, unique = true, length = 50)
    String name
}