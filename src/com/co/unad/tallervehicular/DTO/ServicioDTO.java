/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Charlie
 */
public class ServicioDTO {
    private String id;
    private String motivo_ingreso;
    private Date fecha_ingreso;
    private Date fecha_entrega;
    private BigDecimal costo;
    private Integer horas_trabajo;
    private String placa;
    private String cedula;
    private String motivo_costo_servicio;
    
    public ServicioDTO() {
        
    }
    
    public ServicioDTO(String id, String motivo_ingreso, Date fecha_ingreso, Date fecha_entrega, BigDecimal costo, Integer horas_trabajo, String placa, String cedula) {
        this.id = id;
        this.motivo_ingreso = motivo_ingreso;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_entrega = fecha_entrega;
        this.costo = costo;
        this.horas_trabajo = horas_trabajo;
        this.placa = placa;
        this.cedula = cedula;
    }
    
    public String getMotivo_ingreso() {
        return motivo_ingreso;
    }

    public void setMotivo_ingreso(String motivo_ingreso) {
        this.motivo_ingreso = motivo_ingreso;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Integer getHoras_trabajo() {
        return horas_trabajo;
    }

    public void setHoras_trabajo(Integer horas_trabajo) {
        this.horas_trabajo = horas_trabajo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotivo_costo_servicio() {
        return motivo_costo_servicio;
    }

    public void setMotivo_costo_servicio(String motivo_costo_servicio) {
        this.motivo_costo_servicio = motivo_costo_servicio;
    }
    
    
}
