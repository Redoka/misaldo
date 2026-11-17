/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.api;

import com.example.misaldo.controller.UserController;
import com.example.misaldo.model.User;
import com.example.utiles.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alejandro
 */
@RestController
@RequestMapping("/users")
public class UserAPI {

    private final UserController userController;

    public UserAPI(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("user/{id}")
    public String getUserById(@PathVariable int id) {
        JsonUtil ju = new JsonUtil();
        return ju.toPrettyJson(userController.getUserById(id));
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user) {

        boolean registered = userController.login(user);

        if (registered) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Usuario registrado correctamente");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("No se ha podido registrar el usuario");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody User user) {

        boolean registered = userController.register(user);

        if (registered) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Usuario registrado correctamente");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("No se ha podido registrar el usuario");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {

        boolean deleted = userController.deleteUser(id);

        if (deleted) {
            return ResponseEntity.ok("Usuario eliminado correctamente");
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No se ha encontrado el usuario");
    }

}
