/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.controller;

import com.example.misaldo.model.Category;
import com.example.misaldo.model.CategoryType;
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
public class CategoryController {

    Configuration conf = new Configuration();

    @Autowired
    private DataSource dataSource;

    private static String fields = " c.id, c.id_usuario, c.nombre, c.tipo, tc.descripcion ";
    private static String leftJoinType = "\n LEFT JOIN tipo_categoria tc ON tc.id_tipo = c.tipo \n";
    private static String whereClausule = "\n WHERE id_usuario = ? AND id_usuario = 2 \n";

    public List<Category> getCategories(int idUsuario) {

        List<Category> l = new ArrayList();
        String sql = "SELECT " + fields + " FROM categoria c" + leftJoinType
                + whereClausule;

        //System.out.println("Consulta de Categorias ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Category c = new Category();
                    c.setId(rs.getInt("id"));
                    c.setIdUsuario(rs.getInt("id_usuario"));
                    c.setNombre(rs.getString("nombre"));
                    c.setTipo(new CategoryType());
                    c.getTipo().setIdTipo(rs.getInt("tipo"));
                    c.getTipo().setDescripcion(rs.getString("descripcion"));

                    l.add(c);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails searching categories", e);
        }

        return l;
    }

    public Category getCategoryById(int id) {
        Category c = new Category();
        String sql = "SELECT " + fields + " FROM categoria c" + leftJoinType
                + " WHERE c.id = ?";

        //System.out.println("Consulta de Categorias ----> "  + sql);
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    c.setId(rs.getInt("id"));
                    c.setIdUsuario(rs.getInt("id_usuario"));
                    c.setNombre(rs.getString("nombre"));
                    c.setTipo(new CategoryType());
                    c.getTipo().setIdTipo(rs.getInt("tipo"));
                    c.getTipo().setDescripcion(rs.getString("descripcion"));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERROR: something fails searching cards", e);
        }

        return c;
    }

    public boolean setCategory(Category c) {
        boolean b = false;

        String sql = "INSERT INTO categoria (id, id_usuario, nombre, tipo)\n"
                + "VALUES (?, ?, ?, ?)\n"
                + "ON DUPLICATE KEY UPDATE\n"
                + "    id_usuario = VALUES(id_usuario),\n"
                + "    nombre = VALUES(nombre),\n"
                + "    tipo = VALUES(tipo)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (c.getId() == 0) {
                ps.setNull(1, Types.INTEGER); // ID = NULL
            } else {
                ps.setInt(1, c.getId());
            }
            ps.setInt(2, c.getIdUsuario());
            ps.setString(3, c.getNombre());
            ps.setInt(4, c.getTipo().getIdTipo());

            b = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando card", e);
        }
        return b;
    }

    public boolean deleteCategory(int idCategory) {

        boolean b = false;

        String sql = "DELETE FROM categoria WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCategory);

            b = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error borrando la categoria", e);
        }
        return b;
    }
}
