/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.api;

import com.example.misaldo.controller.PeriodController;
import com.example.misaldo.model.budget;
import com.example.utiles.JsonUtil;

import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Alejandro
 */
@RestController
@RequestMapping("/periods")
public class PeriodAPI {

    private final PeriodController periodController;

    public PeriodAPI(PeriodController periodController) {
        this.periodController = periodController;
    }

    // Obtener todos los presupuestos de un usuario
    @GetMapping("/period/{idUsuario}")
    public String getPeriods(@PathVariable int idUsuario) {

        JsonUtil ju = new JsonUtil();

        return ju.toPrettyJson(
                periodController.getPeriods(idUsuario)
        );
    }

    // Obtener un presupuesto concreto
    @GetMapping("/period/{id}/{idUsuario}")
    public String getPeriodById(
            @PathVariable int id,
            @PathVariable int idUsuario) {

        JsonUtil ju = new JsonUtil();

        return ju.toPrettyJson(
                periodController.getPeriodById(idUsuario, id)
        );
    }

    // Crear o actualizar un periodo
    @PostMapping("/save")
    public ResponseEntity<String> setPeriod(@RequestBody budget period) {

        boolean saved = periodController.setPeriod(period);

        if (saved) {
            return ResponseEntity.ok("Presupuesto guardado correctamente");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("No se ha podido guardar el presupuesto");
    }

    // Eliminar un presupuesto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePeriod(@PathVariable int id) {

        boolean deleted = periodController.deletePeriod(id);

        if (deleted) {
            return ResponseEntity.ok("Presupuesto eliminado correctamente");
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No se ha encontrado el presupuesto");
    }
}
