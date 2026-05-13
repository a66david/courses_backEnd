package com.david.cursoiniciacion.springboot.springboot_backend.services.implementations

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Course
import com.david.cursoiniciacion.springboot.springboot_backend.repositories.CoursesRepository
import com.david.cursoiniciacion.springboot.springboot_backend.services.CoursesService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired

@Service
class CoursesServiceImpl implements CoursesService {

    @Autowired
    CoursesRepository coursesRepository

    CoursesServiceImpl(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository
    }

    @Override
    List<Course> findAll() {
        return coursesRepository.findAll()
    }

    @Override
    @Transactional
    Course createCourse(Course course) {
        return coursesRepository.save(course)
    }

    @Override
    @Transactional(readOnly = true)
    Optional<Course> findById(Long id) {
        return coursesRepository.findById(id)
    }

    @Override
    @Transactional(readOnly = true)
    List<Course> getAllCourses() {
        return coursesRepository.findAll()
    }

    @Override
    @Transactional
    void deleteCourse(Long id) {
        if (!coursesRepository.existsById(id)) {
            throw new NoSuchElementException("Curso con ID ${id} no encontrado")
        }
        coursesRepository.deleteById(id)
    }

    @Override
    Course save(Course course) {
        return coursesRepository.save(course)
    }

}