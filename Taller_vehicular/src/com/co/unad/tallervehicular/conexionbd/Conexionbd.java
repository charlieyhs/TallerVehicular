package com.co.unad.tallervehicular.conexionbd;


import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class Conexionbd {
    private static final String URL = "jdbc:mysql://localhost:3306/tallervehicular";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
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
    public boolean insertarUsuario(String usuario, String password) {
        if (usuarioExiste(usuario)) {
            JOptionPane.showMessageDialog(null, 
                    "El usuario ya existe. Por favor, elige otro nombre de usuario.");
            return false;
        }
        String sql = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            String hashedPassword = hashPassword(password); // Encriptar la contraseña
            stmt.setString(1, usuario);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario insertado correctamente.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean validarUsuario(String usuario, String password) {
        String sql = "SELECT password FROM usuarios WHERE usuario = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = hashPassword(password);
                    
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
    public boolean placaExiste(String placa) {
        String sql = "SELECT COUNT(*) FROM vehiculos WHERE placa = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int prube = rs.getInt(1);
                    return rs.getInt(1) > 0; // Devuelve true si existe una coincidencia
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void guardarVehiculo(String placa, String tipo, String estado) {
        String sql = "INSERT INTO vehiculos (placa, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.setString(2, tipo);
            stmt.setString(3, estado);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vehículo guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean cedulaExiste(String cedula) {
        String sql = "SELECT COUNT(*) FROM propietario WHERE cedula = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
     public void guardarPropietario(String cedula, String nombres, String direccion,
             String telefono, String correo) {
        String sql = "INSERT INTO propietario (cedula, nombres, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            stmt.setString(2, nombres);
            stmt.setString(3, direccion);
            stmt.setString(4, telefono);
            stmt.setString(5, correo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Propietario guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void guardarServicio(String motivo_ingreso,
            Date fecha_ingreso, Date fecha_entrega, double costo,
            int horas_trabajo, String placa, String cedula) {
        String sql = "INSERT INTO servicios (motivo_ingreso, fecha_ingreso, fecha_entrega, costo, horas_trabajo, placa, cedula) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, motivo_ingreso);
            stmt.setDate(2, new java.sql.Date(fecha_ingreso.getTime()));
            stmt.setDate(3, new java.sql.Date(fecha_entrega.getTime()));
            stmt.setDouble(4, costo);
            stmt.setInt(5, horas_trabajo);
            stmt.setString(6, placa);
            stmt.setString(7, cedula);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Propietario> consultarPropietarios() {
        List<Propietario> propietarios = new ArrayList<>();
        String sql = "SELECT cedula, nombres, direccion, telefono, correo FROM propietario";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String cedula = rs.getString("cedula");
                String nombres = rs.getString("nombres");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                propietarios.add(new Propietario(cedula, nombres, direccion, telefono, correo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propietarios;
    }
    public List<Vehiculo> consultarVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT placa, tipo, estado FROM vehiculos";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String placa = rs.getString("placa");
                String tipo = rs.getString("tipo");
                String estado = rs.getString("estado");
                vehiculos.add(new Vehiculo(placa, tipo, estado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
    public List<Servicio> consultarServicios() {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT motivo_ingreso, fecha_ingreso, fecha_entrega, costo, horas_trabajo, placa, cedula FROM servicios";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String motivoIngreso = rs.getString("motivo_ingreso");
                String fechaIngreso = rs.getString("fecha_ingreso");
                String fechaEntrega = rs.getString("fecha_entrega");
                double costo = rs.getDouble("costo");
                int horasTrabajo = rs.getInt("horas_trabajo");
                String placa = rs.getString("placa");
                String cedula = rs.getString("cedula");
                servicios.add(new Servicio(motivoIngreso, fechaIngreso, fechaEntrega, costo, horasTrabajo, placa, cedula));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }
    public class Servicio{
        private String motivoIngreso;
        private String fechaIngreso;
        private String fechaEntrega;
        private double costo;
        private int horasTrabajo;
        private String placa;
        private String cedula;

        public Servicio(String motivoIngreso, String fechaIngreso, String fechaEntrega, double costo, int horasTrabajo, String placa, String cedula) {
            this.motivoIngreso = motivoIngreso;
            this.fechaIngreso = fechaIngreso;
            this.fechaEntrega = fechaEntrega;
            this.costo = costo;
            this.horasTrabajo = horasTrabajo;
            this.placa = placa;
            this.cedula = cedula;
        }

        public String getMotivoIngreso() {
            return motivoIngreso;
        }

        public void setMotivoIngreso(String motivoIngreso) {
            this.motivoIngreso = motivoIngreso;
        }

        public String getFechaIngreso() {
            return fechaIngreso;
        }

        public void setFechaIngreso(String fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

        public String getFechaEntrega() {
            return fechaEntrega;
        }

        public void setFechaEntrega(String fechaEntrega) {
            this.fechaEntrega = fechaEntrega;
        }

        public double getCosto() {
            return costo;
        }

        public void setCosto(double costo) {
            this.costo = costo;
        }

        public int getHorasTrabajo() {
            return horasTrabajo;
        }

        public void setHorasTrabajo(int horasTrabajo) {
            this.horasTrabajo = horasTrabajo;
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
        
        
    }
    public class Vehiculo{
        private String placa;
        private String tipo;
        private String estado;

        public Vehiculo(String placa, String tipo, String estado) {
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
    public class Propietario{
        private String cedula;
        private String nombres;
        private String direccion;
        private String telefono;
        private String correo;
        
        public Propietario(){
             
        }

        public Propietario(String cedula, String nombres, String direccion, String telefono, String correo) {
            this.cedula = cedula;
            this.nombres = nombres;
            this.direccion = direccion;
            this.telefono = telefono;
            this.correo = correo;
        }

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }
        
     }
}
