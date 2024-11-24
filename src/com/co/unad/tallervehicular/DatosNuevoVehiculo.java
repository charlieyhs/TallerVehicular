/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.co.unad.tallervehicular;

import com.co.unad.tallervehicular.DTO.VehiculoDTO;
import com.co.unad.tallervehicular.controlador.VehiculoControlador;
import com.co.unad.tallervehicular.exceptions.UnadException;
import com.co.unad.tallervehicular.util.ObjectsUtil;
import javax.swing.JOptionPane;

/**
 *
 * @author Charlie
 */
public class DatosNuevoVehiculo extends javax.swing.JDialog {
    VehiculoDTO vehiculo = null;
    VehiculoControlador vehSvc = new VehiculoControlador();
    /**
     * Creates new form DatosNuevoVehiculo
     */
    public DatosNuevoVehiculo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        agregarItemsTipoVehiculo();
        agregarItemsEstadosVehiculo();
        jButton1.setVisible(false);
    }
    public DatosNuevoVehiculo(java.awt.Frame parent, boolean modal, VehiculoDTO vehiculo) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        agregarItemsTipoVehiculo();
        agregarItemsEstadosVehiculo();
        this.vehiculo = vehiculo;
        inputPlaca.setText(vehiculo.getPlaca());
        inputPlaca.setEnabled(false);
        tipoVehiculo.setSelectedItem(vehiculo.getTipo());
        estadoVehiculo.setSelectedItem(vehiculo.getEstado());
        jButton2.setText("Actualizar");
        jButton1.setText("Eliminar");
        jButton1.setVisible(true);
    }
    private void agregarItemsTipoVehiculo(){
        tipoVehiculo.removeAllItems();
        String[] nuevasOpciones = {"Automóvil", "Camioneta"};

        for (String opcion : nuevasOpciones) {
            tipoVehiculo.addItem(opcion);
        }
    }
    private void agregarItemsEstadosVehiculo(){
        estadoVehiculo.removeAllItems();
        String[] nuevasOpciones = {"Bueno", "Malo","Regular"};

        for (String opcion : nuevasOpciones) {
            estadoVehiculo.addItem(opcion);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        inputPlaca = new javax.swing.JTextField();
        tipoVehiculo = new javax.swing.JComboBox<>();
        estadoVehiculo = new javax.swing.JComboBox<>();

        
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Placa:");

        jLabel2.setText("Tipo:");

        jLabel3.setText("Estado:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nuevo vehiculo");
        jLabel4.setToolTipText("");

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tipoVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        estadoVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputPlaca)
                    .addComponent(tipoVehiculo, 0, 200, Short.MAX_VALUE)
                    .addComponent(estadoVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 90, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addGap(123, 123, 123))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(estadoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            if(ObjectsUtil.vacio(inputPlaca.getText())){
                throw new UnadException("Debe digitar una placa.");
            }
            if(vehiculo == null && vehSvc.placaExiste(inputPlaca.getText())){
                throw new UnadException("La placa que intenta agregar ya existe.");
            }
            if(ObjectsUtil.vacio((String)tipoVehiculo.getSelectedItem())){
                throw new UnadException("Debe seleccionar una opción en tipo de vehiculo.");
            }
            if(ObjectsUtil.vacio((String)estadoVehiculo.getSelectedItem())){
                throw new UnadException("Debe seleccionar una opción para el estado del vehiculo.");
            }
            
            VehiculoDTO veh = new VehiculoDTO();
            veh.setPlaca(inputPlaca.getText());
            veh.setTipo((String)tipoVehiculo.getSelectedItem());
            veh.setEstado((String)estadoVehiculo.getSelectedItem());
            if(vehiculo != null){
                vehSvc.actualizarVehiculo(veh);
            }else{
                vehSvc.guardarVehiculo(veh);   
            }
        }catch(Exception e){
            if(e instanceof UnadException){
                JOptionPane.showMessageDialog(null,e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            e.printStackTrace();
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(vehiculo == null || ObjectsUtil.vacio(vehiculo.getPlaca())){
            JOptionPane.showMessageDialog(null,"No es posible eliminar este vehiculo.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        vehSvc.eliminarVehiculo(vehiculo);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatosNuevoVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatosNuevoVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatosNuevoVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatosNuevoVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DatosNuevoVehiculo dialog = new DatosNuevoVehiculo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> estadoVehiculo;
    private javax.swing.JTextField inputPlaca;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> tipoVehiculo;
    // End of variables declaration//GEN-END:variables
}