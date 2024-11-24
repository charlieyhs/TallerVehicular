/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.DTO;

/**
 *
 * @author Charlie
 */
public class UsuarioDTO {
    private String usuario;
    private String password;
    private String saltpass;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSaltpass() {
        return saltpass;
    }

    public void setSaltpass(String saltpass) {
        this.saltpass = saltpass;
    }
    
    
}
