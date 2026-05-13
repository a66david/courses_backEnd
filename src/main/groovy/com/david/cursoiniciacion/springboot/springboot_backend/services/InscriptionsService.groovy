package com.david.cursoiniciacion.springboot.springboot_backend.services


import com.david.cursoiniciacion.springboot.springboot_backend.entities.Inscription

interface InscriptionsService {
    Inscription register(Long userId, Long courseId)
    List<Inscription> listByUser(Long userId)
    void cancelInscription(Long id)
    Inscription save(Inscription inscription)
}