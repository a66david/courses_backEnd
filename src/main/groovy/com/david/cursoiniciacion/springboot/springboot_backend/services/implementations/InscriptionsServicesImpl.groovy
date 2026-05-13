package com.david.cursoiniciacion.springboot.springboot_backend.service.implementations

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Inscription
import com.david.cursoiniciacion.springboot.springboot_backend.repositories.InscripcionsRepository
import com.david.cursoiniciacion.springboot.springboot_backend.repositories.UsersRepository
import com.david.cursoiniciacion.springboot.springboot_backend.repositories.CoursesRepository
import com.david.cursoiniciacion.springboot.springboot_backend.services.InscriptionsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service

class InscriptionsServicesImpl implements InscriptionsService {

    private final InscripcionsRepository inscripcionsRepository
    private final UsersRepository usersRepository
    private final CoursesRepository coursesRepository

    InscriptionsServicesImpl(InscripcionsRepository inscripcionsRepository, UsersRepository usersRepository, CoursesRepository coursesRepository) {
        this.inscripcionsRepository = inscripcionsRepository
        this.usersRepository = usersRepository
        this.coursesRepository = coursesRepository
    }

    @Override
    @Transactional
    Inscription register (Long userId, Long courseId){

        def user = usersRepository.findById(userId)
        .orElseThrow(() -> new NoSuchElementException("Usuario con ID ${userId} no encontrado"))

        def course = coursesRepository.findById(courseId)
            .orElseThrow(() -> new NoSuchElementException("Curso con ID ${courseId} no encontrado"))

        def newInscription = new Inscription(user: user, course: course)    

        return inscripcionsRepository.save(newInscription)
    }


   @Override
   @Transactional(readOnly = true)
   List<Inscription> listByUser(Long userId) {
       return inscripcionsRepository.findByUserId(userId)
   } 


   @Override
   @Transactional
    void cancelInscription(Long id) {
         if (!inscripcionsRepository.existsById(id)) {
              throw new NoSuchElementException("Inscripción con ID ${id} no encontrada")
         }
         inscripcionsRepository.deleteById(id)
    }

    @Override
    Inscription save(Inscription inscription) {
        return inscripcionsRepository.save(inscription)
    }

    
}