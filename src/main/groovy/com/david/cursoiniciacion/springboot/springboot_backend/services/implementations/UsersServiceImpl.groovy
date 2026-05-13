package com.david.cursoiniciacion.springboot.springboot_backend.services.implementations

import com.david.cursoiniciacion.springboot.springboot_backend.entities.User
import com.david.cursoiniciacion.springboot.springboot_backend.repositories.UsersRepository
import com.david.cursoiniciacion.springboot.springboot_backend.services.UsersService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository
    private final PasswordEncoder passwordEncoder

    UsersServiceImpl(UsersRepository usersRepository , PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository
        this.passwordEncoder = passwordEncoder
    }

    @Override
    @Transactional
    User createUser(User user){
        Optional<User> existingUser = usersRepository.findByEmail(user.getEmail())
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado")
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()))
        return usersRepository.save(user)
    }

    @Override
    @Transactional(readOnly = true)
    Optional<User> searchByEmail(String email) {
        return usersRepository.findByEmail(email)
    }

    @Override
    @Transactional(readOnly = true)
    Optional<User> searchById(Long id) {
        return usersRepository.findById(id)
    }

    @Override
    @Transactional(readOnly = true)
    List<User> getAllUsers() {
        return usersRepository.findAll()
    }

    @Override
    @Transactional
    void deleteUser(Long id) {
        if (!usersRepository.existsById(id)) {
            throw new NoSuchElementException("Usuario con ID ${id} no encontrado")
        }
        usersRepository.deleteById(id)
    }

    @Override
    User login(String email, String password){
        Optional<User> userOpt = usersRepository.findByEmail(email)
        if (userOpt.isPresent()) {
            User user = userOpt.get()
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user
            }
        }
        throw new IllegalArgumentException("Credenciales inválidas")
    }

    @Override
    User updateNameUser(Long id, String newNameUser){
        User user = usersRepository.findById(id)
                .orElseThrow({new RuntimeException("Usuario no encontrado")})
        user.setName(newNameUser)
        return usersRepository.save(user)
    }

    @Override
    User updateUserPassword(Long id, String newPassword){
        User user = usersRepository.findById(id)
                .orElseThrow({new RuntimeException("Usuario no encontrado")})
        user.setPassword(passwordEncoder.encode(newPassword))
        return usersRepository.save(user)
    }

    
}