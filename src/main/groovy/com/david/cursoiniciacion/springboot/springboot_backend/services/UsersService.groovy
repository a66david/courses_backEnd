package com.david.cursoiniciacion.springboot.springboot_backend.services

import com.david.cursoiniciacion.springboot.springboot_backend.entities.User

interface UsersService {

    User createUser(User user)
    Optional<User> searchByEmail(String email)
    Optional<User> searchById(Long id)
    List<User> getAllUsers()
    void deleteUser(Long id)
    User login(String email, String password)
    User updateNameUser(Long id, String newNameUser)
    User updateUserPassword(Long id, String newPassword)


}