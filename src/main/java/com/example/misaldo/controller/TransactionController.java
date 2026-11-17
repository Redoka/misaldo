/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.controller;

import com.example.misaldo.model.Category;
import com.example.misaldo.model.CategoryType;
import com.example.misaldo.model.budget;
import com.example.misaldo.model.Transaction;
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
public class TransactionController {

    Configuration conf = new Configuration();

    @Autowired
    private DataSource dataSource;

    private static String fields = " t.id, t.id_periodo, t.id_categoria, tc.descripcion, t.descripcion, t.cantidad, t.id_tipo, t.fecha, p.nombre ";
    private static String leftJoinType = "\n LEFT JOIN tipo_categoria tc ON tc.id_tipo = t.id_tipo\n"
            + "INNER JOIN periodo p ON p.id = t.id_periodo \n";
    private static String whereClausule = "\n WHERE t.id_usuario = ? \n";

    public List<Transaction> getTransactions(int idUsuario) {

        List<Transaction> l = new ArrayList();
        String sql = "SELECT " + fields + " FROM transaccion t" + leftJoinType
                + whereClausule;

        //System.out.println("Consulta de Categorias ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Transaction t = new Transaction();

                    t.setId(rs.getInt("t.id"));
                    t.setIdPeriodo(new budget());
                    t.getIdPeriodo().setId(rs.getInt("t.id_periodo"));
                    t.setIdCategoria(new Category());
                    t.getIdCategoria().setId(rs.getInt("t.id_categoria"));
                    t.getIdCategoria().setNombre(rs.getString("tc.nombre"));
                    t.setDescripcion(rs.getString("t.descripcion"));
                    t.setCantidad(rs.getFloat("t.cantidad"));
                    t.setIdTipo(rs.getInt("t.id_tipo"));
                    t.setFecha(rs.getDate(rs.getString("t.fecha")));

                    l.add(t);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails searching transactions", e);
        }

        return l;
    }

    public Transaction getTransaction(int id) {

        Transaction t = new Transaction();
        String sql = "SELECT " + fields + " FROM transaccion t" + leftJoinType
                + " WHERE id_usuario = ?";

        //System.out.println("Consulta de Categorias ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    t.setId(rs.getInt("t.id"));
                    t.setIdPeriodo(new budget());
                    t.getIdPeriodo().setId(rs.getInt("t.id_periodo"));
                    t.setIdCategoria(new Category());
                    t.getIdCategoria().setId(rs.getInt("t.id_categoria"));
                    t.getIdCategoria().setNombre(rs.getString("tc.nombre"));
                    t.setDescripcion(rs.getString("t.descripcion"));
                    t.setCantidad(rs.getFloat("t.cantidad"));
                    t.setIdTipo(rs.getInt("t.id_tipo"));
                    t.setFecha(rs.getDate(rs.getString("t.fecha")));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails searching transactions", e);
        }

        return t;
    }

    public boolean setTransaction(Transaction t) {
        boolean b = false;

        String sql = "INSERT INTO transaccion (id, id_periodo, id_categoria, descripcion, cantidad, id_tipo, fecha)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)\n"
                + "ON DUPLICATE KEY UPDATE\n"
                + "    id_periodo = VALUES(id_periodo),\n"
                + "    id_categoria = VALUES(id_categoria),\n"
                + "    descripcion = VALUES(descripcion),\n"
                + "    cantidad = VALUES(cantidad),\n"
                + "    id_tipo = VALUES(id_tipo),\n"
                + "    fecha = VALUES(fecha)";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (t.getId() == 0) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, t.getId());
            }

            ps.setInt(2, t.getIdPeriodo().getId());
            ps.setInt(3, t.getIdCategoria().getId());
            ps.setString(4, t.getDescripcion());
            ps.setFloat(5, t.getCantidad());
            ps.setInt(6, t.getIdTipo());
            ps.setString(7, String.valueOf(t.getFecha()));

            b = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error guardando transacción", e);
        }

        return b;
    }

    public boolean deleteTransaction(int id) {
        boolean b = false;

        String sql = "DELETE FROM transaccion WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            b = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error borrando la categoria", e);
        }
        return b;
    }
}
