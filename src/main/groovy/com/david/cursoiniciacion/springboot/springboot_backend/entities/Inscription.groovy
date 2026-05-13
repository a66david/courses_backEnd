package com.david.cursoiniciacion.springboot.springboot_backend.entities

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@Entity
@Table(name = "inscripcions")
class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    // Relación: Muchos registros de inscripción pueden pertenecer a un solo Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties(["hibernateLazyInitializer", "handler"])
    User user

    // Relación: Muchos registros de inscripción pueden pertenecer a un solo Curso
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnoreProperties(["hibernateLazyInitializer", "handler"])
    Course course

    @Column(name = "dateInscripcion", updatable = false)
    LocalDateTime dateInscripcion = LocalDateTime.now()
}