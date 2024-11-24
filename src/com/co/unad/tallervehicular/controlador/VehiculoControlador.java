/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.controlador;

import com.co.unad.tallervehicular.DTO.VehiculoDTO;
import com.co.unad.tallervehicular.conexionbd.Conexionbd;
import com.co.unad.tallervehicular.util.ObjectsUtil;
import java.sql.Connection;
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
public class VehiculoControlador extends Conexionbd{
    
    public boolean placaExiste(String placa) {
        String sql = "SELECT COUNT(*) FROM vehiculos WHERE placa = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void guardarVehiculo(VehiculoDTO veh) {
        String sql = "INSERT INTO vehiculos (placa, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veh.getPlaca());
            stmt.setString(2, veh.getTipo());
            stmt.setString(3, veh.getEstado());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vehículo guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actualizarVehiculo(VehiculoDTO veh) {
        String sql = "UPDATE vehiculos set tipo=?, estado = ? "
                + " WHERE placa = ? ";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veh.getTipo());
            stmt.setString(2, veh.getEstado());
            stmt.setString(3, veh.getPlaca());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vehículo actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarVehiculo(VehiculoDTO vehiculo) {
         String sql = "DELETE FROM vehiculos WHERE placa = ? ";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehiculo.getPlaca());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vehiculo eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<VehiculoDTO> consultarVehiculos() {
        return consultarVehiculos(null);
    }
    public List<VehiculoDTO> consultarVehiculos(String placa) {
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        String sql = "SELECT placa, tipo, estado FROM vehiculos";
        if(!ObjectsUtil.vacio(placa)){
            sql += " WHERE placa = ?";
        }
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            if(!ObjectsUtil.vacio(placa)){
                stmt.setString(1, placa);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String placaRs = rs.getString("placa");
                String tipo = rs.getString("tipo");
                String estado = rs.getString("estado");
                vehiculos.add(new VehiculoDTO(placaRs, tipo, estado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
    public VehiculoDTO getVehiculo(String placa) {
        VehiculoDTO veh = null;
        String sql = "SELECT * FROM vehiculos WHERE placa = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String placabd = rs.getString("placa");
                    String tipo = rs.getString("tipo");
                    String estado = rs.getString("estado");
                    veh = new VehiculoDTO(placabd, tipo, estado);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veh;
    }
}
