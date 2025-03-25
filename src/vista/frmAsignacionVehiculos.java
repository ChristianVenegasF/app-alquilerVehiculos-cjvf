/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.AlquilerController;
import controlador.ClienteController;
import controlador.VehiculoController;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Alquiler;
import modelo.Cliente;
import modelo.Vehiculo;

/**
 *
 * @author USUARIO
 */
public class frmAsignacionVehiculos extends javax.swing.JFrame {

    private ClienteController clienteController;
    private VehiculoController vehiculoController;
    private AlquilerController alquilerController;
    public frmAsignacionVehiculos() {
        clienteController = new ClienteController();
        vehiculoController = new VehiculoController();
        alquilerController = new AlquilerController();
        initComponents();
        cargarClientes();
        cargarVehiculos();
        
        // 🔹 Agregar acción al botón "Asignar Vehículo"
        btnAsignar.addActionListener(evt -> asignarVehiculo());

        // 🔹 Agregar acción al botón "Cancelar"
        btnCancelar.addActionListener(evt -> dispose());
    }
    private void cargarClientes() {
        List<Cliente> clientes = clienteController.obtenerClientes();
        for (Cliente cliente : clientes) {
            cbClientes.addItem(cliente.getId() + " - " + cliente.getNombre());
        }
    }

    private void cargarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoController.obtenerVehiculos();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.isDisponible()) {
                cbVehiculos.addItem(vehiculo.getId() + " - " + vehiculo.getMarca() + " " + vehiculo.getModelo());
            }
        }
    }

   private void asignarVehiculo() {
    btnAsignar.setEnabled(false); // 🔹 Evita múltiples clics

    try {
        if (cbClientes.getSelectedItem() == null || cbVehiculos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente y un vehículo.");
            return;
        }

        int idCliente = Integer.parseInt(cbClientes.getSelectedItem().toString().split(" - ")[0]);
        int idVehiculo = Integer.parseInt(cbVehiculos.getSelectedItem().toString().split(" - ")[0]);

        Date fechaInicio = dcFechaInicio.getDate();
        Date fechaFin = dcFechaFin.getDate();

        if (fechaInicio == null || fechaFin == null) {
            JOptionPane.showMessageDialog(this, "Seleccione fechas válidas.");
            return;
        }
        if (fechaFin.before(fechaInicio)) {
            JOptionPane.showMessageDialog(this, "La fecha de fin debe ser posterior a la fecha de inicio.");
            return;
        }

        // ✅ Convertir `java.util.Date` a `java.sql.Date`
        java.sql.Date sqlFechaInicio = new java.sql.Date(fechaInicio.getTime());
        java.sql.Date sqlFechaFin = new java.sql.Date(fechaFin.getTime());

        // ✅ Verificar si el vehículo ya está alquilado en esas fechas
        if (alquilerController.existeAlquiler(idVehiculo, sqlFechaInicio, sqlFechaFin)) {
            JOptionPane.showMessageDialog(this, "El vehículo ya está alquilado en este período.");
            return;
        }

        // ✅ Calcular días de alquiler
        long diffMilisegundos = fechaFin.getTime() - fechaInicio.getTime();
        long diasAlquiler = (diffMilisegundos / (1000 * 60 * 60 * 24)) + 1;

        // ✅ Obtener la tarifa diaria desde el campo de texto
        double tarifaDiaria;
        try {
            tarifaDiaria = Double.parseDouble(txtMonto.getText());
            if (tarifaDiaria <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese una tarifa diaria válida mayor a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para la tarifa diaria.");
            return;
        }

        // ✅ Calcular el monto total
        double montoTotal = diasAlquiler * tarifaDiaria;

        // 🔹 Asignar el monto a `txtMonto` SOLO UNA VEZ antes del registro
        txtMonto.setText(String.format("%.2f", montoTotal));

        // ✅ Registrar alquiler en la base de datos (SOLO UNA VEZ)
        Alquiler nuevoAlquiler = new Alquiler(0, idCliente, idVehiculo, sqlFechaInicio, sqlFechaFin, montoTotal);
        boolean registrado = alquilerController.registrarAlquiler(nuevoAlquiler);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Vehículo asignado correctamente.");
            dispose(); // 🔹 Cierra la ventana después de asignar
        } else {
            JOptionPane.showMessageDialog(this, "Error al asignar el vehículo.");
        }

    } finally {
        btnAsignar.setEnabled(true); // 🔹 Habilitar el botón nuevamente
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAsignar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
        cbVehiculos = new javax.swing.JComboBox<>();
        cbClientes = new javax.swing.JComboBox<>();
        Cerrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Auto.png"))); // NOI18N
        jLabel1.setText("REGISTRO DE ALQUILERES");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 10, 330, 60);

        jLabel2.setText("Cliente:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 70, 60, 30);

        jLabel3.setText("Fecha Inicio:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 180, 80, 30);
        jPanel1.add(dcFechaInicio);
        dcFechaInicio.setBounds(90, 180, 150, 22);

        jLabel5.setText("Vehículo:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 110, 60, 30);

        jLabel6.setText("Fecha Fin:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 210, 70, 30);

        btnAsignar.setText("ASIGNAR VEHÍCULO");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAsignar);
        btnAsignar.setBounds(20, 250, 160, 30);

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(210, 250, 100, 30);
        jPanel1.add(dcFechaFin);
        dcFechaFin.setBounds(80, 210, 170, 22);

        jPanel1.add(cbVehiculos);
        cbVehiculos.setBounds(80, 110, 140, 22);

        jPanel1.add(cbClientes);
        cbClientes.setBounds(80, 80, 140, 22);

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/X.png"))); // NOI18N
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        jPanel1.add(Cerrar);
        Cerrar.setBounds(370, 0, 30, 20);

        jLabel4.setText("Monto:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 150, 60, 20);
        jPanel1.add(txtMonto);
        txtMonto.setBounds(80, 150, 64, 22);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(428, 325));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        asignarVehiculo(); // 🔹 Llama al método que realiza la asignación
    }//GEN-LAST:event_btnAsignarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    
    dcFechaInicio.setDate(null);
    dcFechaFin.setDate(null);
    cbClientes.setSelectedIndex(-1);
    cbVehiculos.setSelectedIndex(-1);
    this.dispose(); // 🔹 Cierra la ventana después de limpiar los campos
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(frmAsignacionVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAsignacionVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAsignacionVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAsignacionVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAsignacionVehiculos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbClientes;
    private javax.swing.JComboBox<String> cbVehiculos;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
