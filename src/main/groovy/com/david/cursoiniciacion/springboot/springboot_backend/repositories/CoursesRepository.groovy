package com.david.cursoiniciacion.springboot.springboot_backend.repositories

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CoursesRepository extends JpaRepository<Course, Long> {
    

}
