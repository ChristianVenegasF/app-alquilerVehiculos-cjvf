/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.VehiculoController;
import javax.swing.JOptionPane;
import modelo.Vehiculo;

/**
 *
 * @author USUARIO
 */
public class frmRegistroVehiculos extends javax.swing.JFrame {

    private VehiculoController vehiculoController;
    public frmRegistroVehiculos() {
        vehiculoController = new VehiculoController();
        initComponents();
    }
    
    
    
    private void registrarVehiculo() {
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String placa = txtPlaca.getText().trim();
        boolean disponible = chkDisponible.isSelected();

        if (marca.isEmpty() || modelo.isEmpty() || placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        Vehiculo nuevoVehiculo = new Vehiculo(0, marca, modelo, placa, disponible);
        if (vehiculoController.registrarVehiculo(nuevoVehiculo)) {
            JOptionPane.showMessageDialog(this, "Vehículo registrado correctamente.");
            dispose(); // 🔹 Cierra la ventana después del registro
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el vehículo.");
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        chkDisponible = new javax.swing.JCheckBox();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtPlaca = new javax.swing.JTextField();
        Cerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Auto.png"))); // NOI18N
        jLabel1.setText("REGISTRO DE VEHÍCULOS ");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(80, 0, 220, 50);

        jLabel2.setText("Marca:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(20, 70, 60, 40);

        jLabel3.setText("Modelo:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 130, 60, 30);

        jLabel4.setText("Placa:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 180, 60, 30);

        jLabel5.setText("Disponible:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 220, 80, 40);

        btnRegistrar.setText("REGISTRAR VEHÍCULO");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrar);
        btnRegistrar.setBounds(20, 280, 150, 30);

        btnCancelar.setText("CANCELAR");
        jPanel2.add(btnCancelar);
        btnCancelar.setBounds(180, 280, 110, 30);
        jPanel2.add(chkDisponible);
        chkDisponible.setBounds(100, 230, 100, 30);
        jPanel2.add(txtMarca);
        txtMarca.setBounds(90, 80, 120, 30);
        jPanel2.add(txtModelo);
        txtModelo.setBounds(90, 130, 120, 30);
        jPanel2.add(txtPlaca);
        txtPlaca.setBounds(90, 180, 120, 30);

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/X.png"))); // NOI18N
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        jPanel2.add(Cerrar);
        Cerrar.setBounds(350, 0, 30, 20);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 20, 380, 330);

        setSize(new java.awt.Dimension(433, 379));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
      this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
       registrarVehiculo(); // 🔹 Llamamos al método que guarda el vehículo
    }//GEN-LAST:event_btnRegistrarActionPerformed

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
            java.util.logging.Logger.getLogger(frmRegistroVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegistroVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegistroVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegistroVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegistroVehiculos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JCheckBox chkDisponible;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
