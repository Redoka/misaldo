/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.controller;

import com.example.misaldo.model.budget;
import com.example.misaldo.model.Salary;
import com.example.misaldo.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alejandro
 */
@Repository
public class SalaryController {

    Configuration conf = new Configuration();

    @Autowired
    private DataSource dataSource;

    private static String fields = "id, descripcion, cantidad, id_usuario, fecha_cobrado ";
    private static String whereClausule = "\n WHERE id_usuario = ?\n";

    public List<Salary> getSalaries(int idUsuario) {
        List<Salary> l = new ArrayList();
        String sql = "SELECT " + fields + " FROM salario"
                + whereClausule;

        //System.out.println("Consulta de Salarios ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Salary s = new Salary();

                    s.setId(rs.getInt("id"));
                    s.setIdUsuario(new User());
                    s.getIdUsuario().setId(rs.getInt("id_usuario"));
                    s.setCantidad(rs.getFloat("cantidad"));
                    s.setFechaCobrado(rs.getString("fecha_cobrado"));

                    l.add(s);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails serching salary", e);
        }

        return l;
    }

    public List<Salary> getSalariesByBudget(int idUsuario, int idPresupuesto) {
        List<Salary> l = new ArrayList();
        String sql = "SELECT s.id, s.descripcion, s.cantidad, "
                + "s.id_usuario, s.fecha_cobrado "
                + "FROM presupuesto p "
                + "INNER JOIN salario s ON s.id = p.id_salario "
                + "WHERE s.id_usuario = ? AND p.id = ?";

        //System.out.println("Consulta de salarios por presupuesto ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(1, idPresupuesto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Salary s = new Salary();

                    s.setId(rs.getInt("id"));
                    s.setIdUsuario(new User());
                    s.getIdUsuario().setId(rs.getInt("id_usuario"));
                    s.setCantidad(rs.getFloat("nombre"));
                    s.setFechaCobrado(rs.getString("fecha_cobrado"));

                    l.add(s);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails serching salary", e);
        }

        return l;
    }

    public Salary getSalarieByid(int id) {
        Salary s = new Salary();
        String sql = "SELECT " + fields + " FROM salario \n"
                + "WHERE id = ?";

        //System.out.println("Consulta de salario ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    s.setId(rs.getInt("id"));
                    s.setIdUsuario(new User());
                    s.getIdUsuario().setId(rs.getInt("id_usuario"));
                    s.setCantidad(rs.getFloat("nombre"));
                    s.setFechaCobrado(rs.getString("fecha_cobrado"));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails serching salary", e);
        }

        return s;
    }

    public boolean setSalary(Salary s) {
        boolean b = false;

        String sql = "INSERT INTO salario (id, descripcion, cantidad, id_usuario, fecha_cobrado)\n"
                + "VALUES (?, ?, ?, ?, ?)\n"
                + "ON DUPLICATE KEY UPDATE\n"
                + "    descripcion = VALUES(descripcion),\n"
                + "    cantidad = VALUES(cantidad),\n"
                + "    id_usuario = VALUES(id_usuario),\n"
                + "    fecha_cobrado = VALUES(fecha_cobrado)";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (s.getId() == 0) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, s.getId());
            }

            ps.setString(2, s.getDescripcion());
            ps.setFloat(3, s.getCantidad());
            ps.setInt(4, s.getIdUsuario().getId());
            ps.setString(5, s.getFechaCobrado());

            b = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error guardando salario", e);
        }

        return b;
    }

    public boolean deleteSalary(int id) {

        boolean b = false;

        String sql = "DELETE FROM salario WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            b = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error borrando el salario", e);
        }
        return b;
    }

}
