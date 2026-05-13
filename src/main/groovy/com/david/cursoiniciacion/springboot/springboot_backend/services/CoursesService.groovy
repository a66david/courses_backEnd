package com.david.cursoiniciacion.springboot.springboot_backend.services

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Course

interface CoursesService {
    List<Course> findAll()
    Course createCourse(Course course)
    Optional<Course> findById(Long id)
    List<Course> getAllCourses()
    Course save(Course course)
    void deleteCourse(Long id)
}