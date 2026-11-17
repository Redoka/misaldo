package com.example.misaldo.api;

import com.example.misaldo.controller.TransactionController;
import com.example.misaldo.model.Transaction;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Alejandro
 */
@RestController
@RequestMapping("/transactions")
public class TransactionAPI {

    private final TransactionController transactionController;

    public TransactionAPI(TransactionController transactionController) {
        this.transactionController = transactionController;
    }

    // Obtener todas las transacciones de un usuario
    @GetMapping("/user/{idUsuario}")
    public String getTransactions(@PathVariable int idUsuario) {

        List<Transaction> transactions
                = transactionController.getTransactions(idUsuario);

        JsonUtil ju = new JsonUtil();

        return ju.toPrettyJson(transactions);
    }

    // Obtener una transacción por su ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getTransaction(@PathVariable int id) {

        Transaction transaction = transactionController.getTransaction(id);

        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }

        JsonUtil ju = new JsonUtil();

        return ResponseEntity.ok(ju.toPrettyJson(transaction));
    }

    // Guardar o actualizar una transacción
    @PostMapping("/save")
    public ResponseEntity<String> setTransaction(
            @RequestBody Transaction transaction) {

        boolean saved = transactionController.setTransaction(transaction);

        if (saved) {
            return ResponseEntity.ok("Transacción guardada correctamente");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No se ha podido guardar la transacción");
    }

    // Eliminar una transacción
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id) {

        boolean deleted = transactionController.deleteTransaction(id);

        if (deleted) {
            return ResponseEntity.ok("Transacción eliminada correctamente");
        }

        return ResponseEntity.notFound().build();
    }
}
