/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.DTO;

/**
 *
 * @author Charlie
 */
public class VehiculoDTO {
    private String placa;
    private String tipo;
    private String estado;
    
    public VehiculoDTO() {
        
    }
    
    public VehiculoDTO(String placa, String tipo, String estado) {
        this.placa = placa;
        this.tipo = tipo;
        this.estado = estado;
    }
   
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
   
}
