package com.david.cursoiniciacion.springboot.springboot_backend.services

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Role

interface RoleService {
    Role createRole(Role role)
    Optional<Role> findByName(String name)
    List<Role> getAllRoles()
}
