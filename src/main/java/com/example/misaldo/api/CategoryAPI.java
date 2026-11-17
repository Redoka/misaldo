/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.api;

import com.example.misaldo.controller.CategoryController;
import com.example.misaldo.model.Category;
import com.example.utiles.JsonUtil;
import java.util.List;
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
@RequestMapping("/categories")
public class CategoryAPI {

    private final CategoryController categoryController;

    public CategoryAPI(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    // Obtener todas las categorías de un usuario
    @GetMapping("/user/{idUsuario}")
    public String getCategories(@PathVariable int idUsuario) {

        List<Category> categories = categoryController.getCategories(idUsuario);

        JsonUtil ju = new JsonUtil();

        return ju.toPrettyJson(categories);
    }

    // Obtener una categoría por su ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getCategoryById(
            @PathVariable int id) {

        Category category = categoryController.getCategoryById(id);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        JsonUtil ju = new JsonUtil();

        return ResponseEntity.ok(ju.toPrettyJson(category));
    }

    // Guardar o actualizar una categoría
    @PostMapping("/save")
    public ResponseEntity<String> setCategory(
            @RequestBody Category category) {

        boolean saved = categoryController.setCategory(category);

        if (saved) {
            return ResponseEntity.ok("Categoría guardada correctamente");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No se ha podido guardar la categoría");
    }

    // Eliminar una categoría
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable int id) {

        boolean deleted = categoryController.deleteCategory(id);

        if (deleted) {
            return ResponseEntity.ok("Categoría eliminada correctamente");
        }

        return ResponseEntity.notFound().build();
    }
}
