/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.api;

import com.example.misaldo.controller.SalaryController;
import com.example.misaldo.controller.UserController;
import com.example.misaldo.model.Salary;
import com.example.misaldo.model.User;
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
@RequestMapping("/salaries")
public class SalaryAPI {

    private final SalaryController salaryController;

    public SalaryAPI(SalaryController salaryController) {
        this.salaryController = salaryController;
    }

    // Obtener todos los salarios de un usuario
    @GetMapping("/user/{idUsuario}")
    public String getSalaries(@PathVariable int idUsuario) {

        List<Salary> salaries = salaryController.getSalaries(idUsuario);

        JsonUtil ju = new JsonUtil();

        return ju.toPrettyJson(salaries);
    }

    // Obtener los salarios asociados a un presupuesto
    @GetMapping("/user/{idUsuario}/budget/{idPresupuesto}")
    public String getSalariesByBudget(
            @PathVariable int idUsuario,
            @PathVariable int idPresupuesto) {

        List<Salary> salaries = salaryController.getSalariesByBudget(idUsuario, idPresupuesto);

        JsonUtil ju = new JsonUtil();

        return ju.toPrettyJson(salaries);
    }

    // Obtener un salario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getSalaryById(
            @PathVariable int id) {

        Salary salary = salaryController.getSalarieByid(id);

        if (salary == null) {
            return ResponseEntity.notFound().build();
        }

        JsonUtil ju = new JsonUtil();

        return ResponseEntity.ok(ju.toPrettyJson(salary));
    }

    // Guardar o actualizar un salario
    @PostMapping("/save")
    public ResponseEntity<String> setSalary(
            @RequestBody Salary salary) {

        boolean saved = salaryController.setSalary(salary);

        if (saved) {
            return ResponseEntity.ok("Salario guardado correctamente");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No se ha podido guardar el salario");
    }

    // Eliminar un salario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSalary(
            @PathVariable int id) {

        boolean deleted = salaryController.deleteSalary(id);

        if (deleted) {
            return ResponseEntity.ok("Salario eliminado correctamente");
        }

        return ResponseEntity.notFound().build();
    }
}
