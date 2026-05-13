package com.david.cursoiniciacion.springboot.springboot_backend.entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "courses")
class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false, length = 200)
    String title

    @Column(columnDefinition = "TEXT")
    String description

    @Column(nullable = false)
    BigDecimal price

    @Column(name = "fecha_creacion", updatable = false)
    LocalDateTime fechaCreacion = LocalDateTime.now()
    
    // Más adelante agregaremos aquí la relación con el Profesor
}