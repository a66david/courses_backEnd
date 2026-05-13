package com.david.cursoiniciacion.springboot.springboot_backend.controller

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Role
import com.david.cursoiniciacion.springboot.springboot_backend.services.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/roles")
class RolController {

    private final RoleService roleService

    RolController(RoleService roleService) {
        this.roleService = roleService
    }

    @PostMapping
    ResponseEntity<Role> crearRol(@RequestBody Role rol) {
        Role newRole = roleService.crearRol(role)
        return new ResponseEntity<>(newRole, HttpStatus.CREATED)
    }

    @GetMapping
    ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles())
    }
}