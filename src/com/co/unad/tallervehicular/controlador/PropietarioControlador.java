/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.controlador;

import com.co.unad.tallervehicular.DTO.PropietarioDTO;
import com.co.unad.tallervehicular.conexionbd.Conexionbd;
import com.co.unad.tallervehicular.util.ObjectsUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Charlie
 */
public class PropietarioControlador extends Conexionbd{
    
    public boolean cedulaExiste(String cedula) {
        String sql = "SELECT COUNT(*) FROM propietario WHERE cedula = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Devuelve true si hay coincidencias
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void guardarPropietario(PropietarioDTO prop) {
        String sql = "INSERT INTO propietario (cedula, nombres, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prop.getCedula());
            stmt.setString(2, prop.getNombres());
            stmt.setString(3, prop.getDireccion());
            stmt.setString(4, prop.getTelefono());
            stmt.setString(5, prop.getCorreo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Propietario guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actualizarPropietario(PropietarioDTO prop) {
        String sql = "UPDATE propietario set nombres = ?, direccion = ?, "
                + " telefono = ?, correo = ?, nrotarjetapropiedad = ? WHERE cedula = ? ";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prop.getNombres());
            stmt.setString(2, prop.getDireccion());
            stmt.setString(3, prop.getTelefono());
            stmt.setString(4, prop.getCorreo());
            stmt.setString(5, prop.getNrotarjetapropiedad());
            stmt.setString(6, prop.getCedula());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Propietario actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<PropietarioDTO> consultarPropietarios() {
        return consultarPropietarios(null);
    }
    public List<PropietarioDTO> consultarPropietarios(String cedula) {
        List<PropietarioDTO> propietarios = new ArrayList<>();
        String sql = "SELECT * FROM propietario";
        if(!ObjectsUtil.vacio(cedula)){
            sql += " WHERE cedula = ? ";
        }
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            if(!ObjectsUtil.vacio(cedula)){
                stmt.setString(1, cedula);
            }
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String cedulaResult = rs.getString("cedula");
                String nombres = rs.getString("nombres");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                PropietarioDTO prop = new PropietarioDTO(cedulaResult, nombres, direccion, telefono, correo);
                prop.setNrotarjetapropiedad(rs.getString("nrotarjetapropiedad"));
                propietarios.add(prop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propietarios;
    }
    public void eliminarPropietario(PropietarioDTO propietario) {
         String sql = "DELETE FROM propietario WHERE cedula = ? ";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, propietario.getCedula());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Propietario eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
