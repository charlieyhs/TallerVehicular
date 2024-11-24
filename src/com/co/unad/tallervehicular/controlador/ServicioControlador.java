/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.controlador;

import com.co.unad.tallervehicular.DTO.ServicioDTO;
import com.co.unad.tallervehicular.conexionbd.Conexionbd;
import com.co.unad.tallervehicular.util.ObjectsUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Charlie
 */
public class ServicioControlador extends Conexionbd{
    public void guardarServicio(ServicioDTO servicio) {
        String sql = "INSERT INTO servicios (motivo_ingreso, fecha_ingreso, fecha_entrega, costo, horas_trabajo, placa, cedula, motivo_costo_servicio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servicio.getMotivo_ingreso());
            stmt.setDate(2, new java.sql.Date(servicio.getFecha_ingreso().getTime()));
            stmt.setDate(3, new java.sql.Date(servicio.getFecha_entrega().getTime()));
            stmt.setDouble(4, servicio.getCosto().doubleValue());
            stmt.setInt(5, servicio.getHoras_trabajo());
            stmt.setString(6, servicio.getPlaca());
            stmt.setString(7, servicio.getCedula());
            stmt.setString(8, servicio.getMotivo_costo_servicio());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actualizarServicio(ServicioDTO servicio) {
        String sql = "UPDATE servicios SET motivo_ingreso = ?, fecha_ingreso = ?, fecha_entrega = ?, "
                + "costo = ?, horas_trabajo = ?, placa = ?, cedula = ?, motivo_costo_servicio = ? WHERE id = ? ";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servicio.getMotivo_ingreso());
            stmt.setDate(2, new java.sql.Date(servicio.getFecha_ingreso().getTime()));
            stmt.setDate(3, new java.sql.Date(servicio.getFecha_entrega().getTime()));
            stmt.setDouble(4, servicio.getCosto().doubleValue());
            stmt.setInt(5, servicio.getHoras_trabajo());
            stmt.setString(6, servicio.getPlaca());
            stmt.setString(7, servicio.getCedula());
            stmt.setString(8, servicio.getMotivo_costo_servicio());
            stmt.setString(9, servicio.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarServicio(ServicioDTO servicio) {
         String sql = "DELETE FROM servicios WHERE id = ? ";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servicio.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<ServicioDTO> consultarServicios() {
        return consultarServicios(null);
    }
    public List<ServicioDTO> consultarServicios(String idServicio) {
        List<ServicioDTO> servicios = new ArrayList<>();
        String sql = "SELECT * FROM servicios";
        if(!ObjectsUtil.vacio(idServicio)){
            sql += " WHERE id = ?"; 
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            
            if(!ObjectsUtil.vacio(idServicio)){
               stmt.setString(1, idServicio);
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String motivoIngreso = rs.getString("motivo_ingreso");
                Date fechaIngreso = sdf.parse(rs.getString("fecha_ingreso"));
                Date fechaEntrega = sdf.parse(rs.getString("fecha_entrega"));
                double costo = rs.getDouble("costo");
                int horasTrabajo = rs.getInt("horas_trabajo");
                String placa = rs.getString("placa");
                String cedula = rs.getString("cedula");
                String id = rs.getString("id");
                ServicioDTO serv = new ServicioDTO(id, motivoIngreso, fechaIngreso, fechaEntrega,  new BigDecimal(costo), horasTrabajo, placa, cedula);
                serv.setMotivo_costo_servicio(rs.getString("motivo_costo_servicio"));
                servicios.add(serv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return servicios;
    }
}
