/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.controller;

import com.example.misaldo.model.Category;
import com.example.misaldo.model.CategoryType;
import com.example.misaldo.model.budget;
import com.example.misaldo.model.Salary;
import com.example.misaldo.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alejandro
 */
@Repository
public class PeriodController {

    Configuration conf = new Configuration();

    @Autowired
    private DataSource dataSource;

    private static String fields = " p.id, p.id_usuario, u.username, p.nombre, p.fecha_inicio, p.fecha_fin, p.estado, p.id_salario, s.cantidad, p.ahorro, p.ahorrado, p.a_gastar, p.restante, p.restante_anterior ";
    private static String leftJoinType = "\n INNER JOIN usuario u ON u.id = p.id_usuario \n"
            + " LEFT JOIN salario s ON s.id = p.id_salario\n";
    private static String whereClausule = "\n WHERE id_usuario = ?\n";

    public List<budget> getPeriods(int idUsuario) {
        List<budget> l = new ArrayList();
        String sql = "SELECT " + fields + " FROM presupuesto p" + leftJoinType
                + whereClausule;

        //System.out.println("Consulta de Periodos ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    budget p = new budget();

                    p.setId(rs.getInt("p.id"));
                    p.setIdUsuario(new User());
                    p.getIdUsuario().setId(rs.getInt("p.id_usuario"));
                    p.getIdUsuario().setUsername(rs.getString("u.username"));
                    p.setNombre(rs.getString("p.nombre"));
                    p.setFechaInicio(rs.getDate("p.fecha_inicio"));
                    p.setFechaFin(rs.getDate("p.fecha_fin"));
                    p.setEstado(rs.getString("p.estado"));
                    p.setIdSalario(new Salary());
                    p.getIdSalario().setId(rs.getInt("p.id_salario"));
                    p.getIdSalario().setCantidad(rs.getFloat("s.cantidad"));
                    p.setPorcentajeAhorro(rs.getFloat("p.ahorro"));
                    p.setAhorrado(rs.getFloat("p.ahorrado"));
                    p.setaGastar(rs.getFloat("p.a_gastar"));
                    p.setRestante(rs.getFloat("p.restante"));
                    p.setRestanteAnterior(rs.getFloat("p.restante_anterior"));

                    l.add(p);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails serching periods", e);
        }

        return l;
    }
    
    public budget getPeriodById(int idUser, int id) {
        budget p = new budget();
        String sql = "SELECT " + fields + " FROM presupuesto p" + leftJoinType
                + whereClausule + "AND id = ?";

        //System.out.println("Consulta de presupuesto por id ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ps.setInt(2, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    p.setId(rs.getInt("p.id"));
                    p.setIdUsuario(new User());
                    p.getIdUsuario().setId(rs.getInt("p.id_usuario"));
                    p.getIdUsuario().setUsername(rs.getString("u.username"));
                    p.setNombre(rs.getString("p.nombre"));
                    p.setFechaInicio(rs.getDate("p.fecha_inicio"));
                    p.setFechaFin(rs.getDate("p.fecha_fin"));
                    p.setEstado(rs.getString("p.estado"));
                    p.setIdSalario(new Salary());
                    p.getIdSalario().setId(rs.getInt("p.id_salario"));
                    p.getIdSalario().setCantidad(rs.getFloat("s.cantidad"));
                    p.setPorcentajeAhorro(rs.getFloat("p.ahorro"));
                    p.setAhorrado(rs.getFloat("p.ahorrado"));
                    p.setaGastar(rs.getFloat("p.a_gastar"));
                    p.setRestante(rs.getFloat("p.restante"));
                    p.setRestanteAnterior(rs.getFloat("p.restante_anterior"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails serching budgets", e);
        }

        return p;
    }

    public boolean setPeriod(budget p) {
        boolean b = false;

        String sql = "INSERT INTO presupuesto (id, id_usuario, nombre, fecha_inicio, fecha_fin, estado, id_salario, ahorro, ahorrado, a_gastar, restante, restante_anterior)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n"
                + "ON DUPLICATE KEY UPDATE\n"
                + "    id_usuario = VALUES(id_usuario),\n"
                + "    nombre = VALUES(nombre),\n"
                + "    fecha_inicio = VALUES(fecha_inicio),\n"
                + "    fecha_fin = VALUES(fecha_fin),\n"
                + "    estado = VALUES(estado),\n"
                + "    id_salario = VALUES(id_salario),\n"
                + "    ahorro = VALUES(ahorro),\n"
                + "    ahorrado = VALUES(ahorrado),\n"
                + "    a_gastar = VALUES(a_gastar),\n"
                + "    restante = VALUES(restante),\n"
                + "    restante_anterior = VALUES(restante_anterior)";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (p.getId() == 0) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, p.getId());
            }

            ps.setInt(2, p.getIdUsuario().getId());
            ps.setString(3, p.getNombre());
            ps.setString(4, String.valueOf(p.getFechaInicio()));
            ps.setString(5, p.getFechaFin() != null ? String.valueOf(p.getFechaFin()) : "");
            ps.setString(6, p.getEstado());
            ps.setNull(7, Types.INTEGER);
            ps.setFloat(8, p.getPorcentajeAhorro());
            ps.setFloat(9, p.getAhorrado());
            ps.setFloat(10, p.getaGastar());
            ps.setFloat(11, p.getRestante());
            ps.setFloat(12, p.getRestanteAnterior());

            b = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error guardando presupuesto", e);
        }
        return b;
    }

    public boolean deletePeriod(int id) {

        boolean b = false;

        String sql = "DELETE FROM presupuesto WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            b = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error borrando el presupuesto", e);
        }
        return b;
    }
}
