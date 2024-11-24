/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.controlador;

import com.co.unad.tallervehicular.DTO.UsuarioDTO;
import com.co.unad.tallervehicular.conexionbd.Conexionbd;
import com.co.unad.tallervehicular.util.SeguridadUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Charlie
 */
public class UsuarioControlador extends Conexionbd{
    
    
    public boolean usuarioExiste(String usuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Retorna true si hay coincidencias
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean insertarUsuario(UsuarioDTO usuario) {
        if (usuarioExiste(usuario.getUsuario())) {
            JOptionPane.showMessageDialog(null, 
                    "El usuario ya existe. Por favor, elige otro nombre de usuario.");
            return false;
        }
        String sql = "INSERT INTO usuarios (usuario, password, saltpass) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            String salt = SeguridadUtil.generateSalt();
            String hashedPassword = SeguridadUtil.hashPassword(usuario.getPassword(), salt); // Encriptar la contraseña
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, salt);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario insertado correctamente.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean validarUsuario(String usuario, String password) {
        String sql = "SELECT password, saltpass FROM usuarios WHERE usuario = ?";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String saltBd = rs.getString("saltpass");
                    String hashedPassword = SeguridadUtil.hashPassword(password, saltBd);
                    
                    if(hashedPassword.equals(rs.getString("password"))){
                       return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
        return false;
    }
}
