package com.david.cursoiniciacion.springboot.springboot_backend.services.implementations

import com.david.cursoiniciacion.springboot.springboot_backend.entities.Role
import com.david.cursoiniciacion.springboot.springboot_backend.repositories.RolesRepository
import com.david.cursoiniciacion.springboot.springboot_backend.services.RoleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RolesServiceImpl implements RoleService {

    private final RolesRepository rolesRepository

    RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository
    }

    @Override
    @Transactional
    Role createRole(Role role){
        Optional<Role> existingRole = rolesRepository.findByName(role.getName())
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("El rol con nombre ${role.getName()} ya existe")
        }
        return rolesRepository.save(role)
    }

    @Override
    @Transactional(readOnly = true)
    Optional<Role> findByName(String name) {
        return rolesRepository.findByName(name)
    }

    @Override
    @Transactional(readOnly = true)
    List<Role> getAllRoles() {
        return rolesRepository.findAll()
    }


}