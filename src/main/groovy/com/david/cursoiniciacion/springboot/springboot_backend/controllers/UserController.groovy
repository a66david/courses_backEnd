package com.david.cursoiniciacion.springboot.springboot_backend.controllers

import com.david.cursoiniciacion.springboot.springboot_backend.entities.User
import com.david.cursoiniciacion.springboot.springboot_backend.services.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:5173", "https://courses-front-end.vercel.app"])
@RestController
@RequestMapping("/api/users")
class UserController {

    private final UsersService usersService

    UserController(UsersService usersService){
        this.usersService = usersService
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = usersService.createUser(user)
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED)
    }

    @GetMapping
    ResponseEntity<User> getAllUsers(){
        List<User> users = usersService.getAllUsers()
        return new ResponseEntity(users, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return usersService.getUserById(id)
                .map { user -> new ResponseEntity<>(user, HttpStatus.OK) }
                .orElse(new ResponseEntity<>(HttpStatus.notFound().build()))
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        usersService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Map<String, String> credentials){
        
        try{
            User userlogin = usersService.login(credentials.get("email"), credentials.get("password"))
            return ResponseEntity.ok(userlogin)
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body([message: e.getMessage()])
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateNameUser(@PathVariable("id")Long id, @RequestBody Map<String, String> body){
        try{
            User updateNameUser = usersService.updateNameUser(id, body.name)
            return ResponseEntity.ok(updateNamedUser)
            }catch (RuntimeException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([message: e.getMessage()])
            }
            
        }

    @PutMapping("/{id}/password")
    ResponseEntity<User> updateUserPassword(@PathVariable("id")Long id, @RequestBody Map<String, String> body){
        try{
            User updateUser = usersService.updateUserPassword(id, body.password)
            return ResponseEntity.ok([message: "Contraseña actualizada exitosamente"])
            }catch (RuntimeException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([message: e.getMessage()])
            }
            
        }

}