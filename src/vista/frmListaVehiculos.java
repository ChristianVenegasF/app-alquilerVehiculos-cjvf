
package vista;

import controlador.VehiculoController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Vehiculo;


public class frmListaVehiculos extends javax.swing.JFrame {

   private VehiculoController vehiculoController;
    private DefaultTableModel modeloTabla;

    public frmListaVehiculos() {
        initComponents();
        vehiculoController = new VehiculoController();
        modeloTabla = (DefaultTableModel) tablaVehiculos.getModel(); 
        cargarVehiculos();
    }
    // 🔹 Método para cargar la lista de vehículos en la tabla
    private void cargarVehiculos() {
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar datos

        List<Vehiculo> listaVehiculos = vehiculoController.obtenerVehiculos();
        for (Vehiculo v : listaVehiculos) {
            modeloTabla.addRow(new Object[]{
                v.getId(),
                v.getPlaca(),
                v.getMarca(),
                v.getModelo(),
                v.isDisponible() ? "Disponible" : "No Disponible"
            });
        }
    }

    // 🔹 Método para buscar vehículos
    private void buscarVehiculos() {
        String criterio = txtBuscarVehiculo.getText().trim();
        if (criterio.isEmpty()) {
            cargarVehiculos(); // Si no hay filtro, se recarga la lista completa
            return;
        }

        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar los resultados

        List<Vehiculo> listaVehiculos = vehiculoController.buscarVehiculos(criterio);
        for (Vehiculo v : listaVehiculos) {
            modeloTabla.addRow(new Object[]{
                v.getId(),
                v.getPlaca(),
                v.getMarca(),
                v.getModelo(),
                v.isDisponible() ? "Disponible" : "No Disponible"
            });
        }

        if (listaVehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron vehículos con ese criterio.");
        }
    }

    // 🔹 Método para editar vehículo
    private void editarVehiculo() {
        int fila = tablaVehiculos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para editar.");
            return;
        }

        // Obtener los datos del vehículo seleccionado
        int id = (int) modeloTabla.getValueAt(fila, 0);
        String placa = modeloTabla.getValueAt(fila, 1).toString();
        String marca = modeloTabla.getValueAt(fila, 2).toString();
        String modelo = modeloTabla.getValueAt(fila, 3).toString();
        

        // Abrir el formulario de edición y pasar los datos
        frmEditarVehiculo editarVehiculoForm = new frmEditarVehiculo(this, id, placa, marca, modelo);
        editarVehiculoForm.setVisible(true);
    }

    // 🔹 Método para eliminar vehículo
    private void eliminarVehiculo() {
        int fila = tablaVehiculos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para eliminar.");
            return;
        }

        int idVehiculo = (int) modeloTabla.getValueAt(fila, 0);

        int confirmacion = JOptionPane.showConfirmDialog(
            this, "¿Está seguro de eliminar este vehículo?", "Confirmación", JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (vehiculoController.eliminarVehiculo(idVehiculo)) {
                JOptionPane.showMessageDialog(this, "Vehículo eliminado correctamente.");
                cargarVehiculos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el vehículo.");
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarVehiculo = new javax.swing.JTextField();
        btnBuscarVehiculo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVehiculos = new javax.swing.JTable();
        btnEditarVehiculo = new javax.swing.JButton();
        btnEliminarVehiculo = new javax.swing.JButton();
        Cerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Auto.png"))); // NOI18N
        jLabel1.setText("LISTADO DE VEHÍCULOS");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(90, 40, 164, 30);

        jLabel2.setText("Buscar Vehículo:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(27, 83, 86, 16);
        jPanel1.add(txtBuscarVehiculo);
        txtBuscarVehiculo.setBounds(126, 80, 128, 30);

        btnBuscarVehiculo.setText("BUSCAR");
        btnBuscarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVehiculoActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarVehiculo);
        btnBuscarVehiculo.setBounds(260, 80, 74, 23);

        jLabel3.setText("Tabla de vehículos:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(27, 121, 101, 16);

        tablaVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Placa", "Marca", "Modelo", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tablaVehiculos);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 155, 375, 165);

        btnEditarVehiculo.setText("EDITAR VEHÍCULO");
        btnEditarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarVehiculoActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditarVehiculo);
        btnEditarVehiculo.setBounds(14, 348, 128, 23);

        btnEliminarVehiculo.setText("ELIMINAR VEHÍCULO");
        jPanel1.add(btnEliminarVehiculo);
        btnEliminarVehiculo.setBounds(185, 348, 141, 23);

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/X.png"))); // NOI18N
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        jPanel1.add(Cerrar);
        Cerrar.setBounds(370, 0, 30, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 410);

        setSize(new java.awt.Dimension(416, 446));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
       this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    private void btnEditarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarVehiculoActionPerformed
       int fila = tablaVehiculos.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un vehículo para editar.");
        return;
    }

    // 🔹 Obtener los datos del vehículo seleccionado
    int id = (int) modeloTabla.getValueAt(fila, 0);
    String placa = modeloTabla.getValueAt(fila, 1).toString();
    String marca = modeloTabla.getValueAt(fila, 2).toString();
    String modelo = modeloTabla.getValueAt(fila, 3).toString();
 

    // 🔹 Abrir el formulario de edición y pasarle los datos
    frmEditarVehiculo editarVehiculoForm = new frmEditarVehiculo(this, id, placa, marca, modelo);
    editarVehiculoForm.setVisible(true);
    }//GEN-LAST:event_btnEditarVehiculoActionPerformed

    private void btnBuscarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVehiculoActionPerformed
       buscarVehiculos(); // Llamar al método que filtra los vehículos
    }//GEN-LAST:event_btnBuscarVehiculoActionPerformed

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
            java.util.logging.Logger.getLogger(frmListaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmListaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmListaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmListaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmListaVehiculos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JButton btnBuscarVehiculo;
    private javax.swing.JButton btnEditarVehiculo;
    private javax.swing.JButton btnEliminarVehiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVehiculos;
    private javax.swing.JTextField txtBuscarVehiculo;
    // End of variables declaration//GEN-END:variables
}
