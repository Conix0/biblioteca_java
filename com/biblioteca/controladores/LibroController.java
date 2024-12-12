package com.biblioteca.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.Modelos.LibroDTO;
import com.biblioteca.conexion.enlace;

public class LibroController {

public List<LibroDTO> buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM libros WHERE titulo LIKE ?";
        List<LibroDTO> listaLibros = new ArrayList<>();

        try (Connection conn = enlace.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + titulo + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                listaLibros.add(new LibroDTO(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar libros: " + e.getMessage());
        }
        return listaLibros;
        }


    public void agregarLibro(LibroDTO libro) {
        String sql = "INSERT INTO libros (titulo, autor, anio) VALUES (?, ?, ?)";
        try (Connection conn = enlace.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setInt(3, libro.getAnio());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar el libro: " + e.getMessage());
        }
    }

    public void eliminarLibro(int id) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection conn = enlace.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el libro: " + e.getMessage());
        }
    }
}
