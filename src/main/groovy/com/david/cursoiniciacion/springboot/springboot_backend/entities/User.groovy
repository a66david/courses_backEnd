package com.david.cursoiniciacion.springboot.springboot_backend.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
@Table(name = "users")
class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false, length = 100)
    String name

    @Column(nullable = false, unique = true, length = 150)
    String email

    @Column(nullable = false)
    String password
    
    @Column(name = "Date_Created", updatable = false)
    LocalDateTime dateCreated = LocalDateTime.now()

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Inscription> inscriptions

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles = new HashSet<>()
}