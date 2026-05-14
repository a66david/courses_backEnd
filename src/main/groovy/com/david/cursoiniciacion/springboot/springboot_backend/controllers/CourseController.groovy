package com.david.cursoiniciacion.springboot.springboot_backend.controller

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Course
import com.david.cursoiniciacion.springboot.springboot_backend.services.CoursesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:5173", "https://courses-front-end.vercel.app"])
@RestController
@RequestMapping("/api/courses")
class CourseController{

    @Autowired
    CoursesService coursesService

    CourseController(CoursesService coursesService) {
        this.coursesService = coursesService
    }

    
    @GetMapping
    ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(coursesService.getAllCourses())
    }

    @GetMapping("/{id}")
    ResponseEntity<Course> getCourseById(@PathVariable("id") Long id){
        return coursesService.getCourseById(id)
                .map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCourse(@PathVariable("id") Long id){
        try {
            Optional<Course> course = coursesService.findById(id)
            if (course.isPresent()) {
                coursesService.deleteById(id)
                return ResponseEntity.ok([mensaje: "Curso eliminado con éxito"])
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body([mensaje: "El curso no existe"])
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.CONFLICT).body([error: "No se puede eliminar el curso. Probablemente ya tiene alumnos inscritos."])
        }
    }  

    @PostMapping
    ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            course.setId(null) 
            Course courseSave = coursesService.save(course)
            return ResponseEntity.status(HttpStatus.CREATED).body(courseSave)
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([error: e.getMessage()])
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCourse(@PathVariable("id") Long id, @RequestBody Course courseUpdate) {
        try {
            Optional<Course> courseExist = coursesService.findById(id)
            if (!courseExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([mensaje: "Curso no encontrado para actualizar"])
            }

            // Actualizamos los datos del curso que encontramos
            Course courseSaveDB = courseExist.get()
            courseSaveDB.setTitle(courseUpdate.getTitle())
            courseSaveDB.setDescription(courseUpdate.getDescription())
            courseSaveDB.setPrice(courseUpdate.getPrice())
            // Agrega aquí otras propiedades si tu curso tiene más (imagen, etc.)

            // Guardamos los cambios
            Course courseFinal = coursesService.save(courseSaveDB)
            return ResponseEntity.ok(courseFinal)

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([error: e.getMessage()])
        }
    }
}