package com.david.cursoiniciacion.springboot.springboot_backend.controller

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Inscription
import com.david.cursoiniciacion.springboot.springboot_backend.entities.User
import com.david.cursoiniciacion.springboot.springboot_backend.entities.Course
import com.david.cursoiniciacion.springboot.springboot_backend.services.InscriptionsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = {"http://localhost:5173", "https://courses-front-end.vercel.app"})
@RestController
@RequestMapping("/api/inscriptions")
class InscriptionController {

    private final InscriptionsService inscriptionService

    InscriptionController(InscriptionsService inscriptionService) {
        this.inscriptionService = inscriptionService
    }

    // Ejemplo de URL para Postman: POST http://localhost:8080/api/inscripciones?usuarioId=1&cursoId=1
    /*@PostMapping
    ResponseEntity <Inscription> register (
        @RequestParam ("userId") Long userId, 
        @RequestParam ("courseId") Long courseId) {
        Inscription registerInscription = inscriptionService.register(userId, courseId)
        return new ResponseEntity<>(registerInscription, HttpStatus.CREATED)
    }
   */
    // Para ver los cursos de un usuario específico: GET http://localhost:8080/api/inscripciones/usuario/1
    @GetMapping("/user/{userId}")
    ResponseEntity<List<Inscription>> listByUser (@PathVariable("userId") Long userId) {
        try {
            def misInscripciones = inscriptionService.listByUser(userId)
            return ResponseEntity.ok(misInscripciones)
        } catch (Exception e) {
            // ¡AGREGAR ESTA LÍNEA PARA QUE SPRING GRITE EL ERROR EN LA CONSOLA!
            e.printStackTrace() 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([error: e.getMessage()])
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteInscription(@PathVariable("id") Long id) {
        inscriptionService.deleteInscription(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    ResponseEntity<?> register(@RequestBody Map<String, Object> payload) {
        try {
            
            Map userMap = (Map) payload.get("user")
            Map courseMap = (Map) payload.get("course")
            
            Long userId = Long.valueOf(userMap.get("id").toString())
            Long courseId = Long.valueOf(courseMap.get("id").toString())
            
          
            User usuarioRef = new User()
            usuarioRef.setId(userId)
            
            Course cursoRef = new Course()
            cursoRef.setId(courseId)
            
           
            Inscription newInscripcion = new Inscription()
            newInscripcion.setUser(usuarioRef)
            newInscripcion.setCourse(cursoRef)
            
           
            inscriptionService.save(newInscripcion)
            return ResponseEntity.status(HttpStatus.CREATED).body([mensaje: "Inscripción creada con éxito"])
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([error: e.getMessage()])
        }
    }
}