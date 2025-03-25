
package vista;

import controlador.AlquilerController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Alquiler;


public class frmListaAlquileres extends javax.swing.JFrame {

   private AlquilerController alquilerController;
    private DefaultTableModel modeloTabla;
    
    public frmListaAlquileres(List<Alquiler> listaAlquileres) {
    initComponents(); // ☑️ ¡Importante! Inicializar componentes
        alquilerController = new AlquilerController();
        modeloTabla = (DefaultTableModel) tablaAlquileres.getModel();
        cargarAlquileres(); // 🔹 Llenar automáticamente la tabla
        // Llenar la tabla con los datos recibidos
        for (Alquiler alquiler : listaAlquileres) {
            modeloTabla.addRow(new Object[]{
                alquiler.getId(),
                alquiler.getNombreCliente(),
                alquiler.getIdVehiculo(),
                alquiler.getFechaInicio(),
                alquiler.getFechaFin(),
                alquiler.getCosto()
            });
        }
    }
    // 🔹 Método para cargar todos los alquileres en la tabla
    private void cargarAlquileres() {
        modeloTabla.setRowCount(0); // Limpiar tabla

        List<Alquiler> listaAlquileres = alquilerController.obtenerAlquileres();
        for (Alquiler alquiler : listaAlquileres) {
            modeloTabla.addRow(new Object[]{
                alquiler.getId(),
                alquiler.getNombreCliente(),
                alquiler.getIdVehiculo(),
                alquiler.getFechaInicio(),
                alquiler.getFechaFin(),
                alquiler.getCosto()
            });
        }
    }


    // 🔹 Método para buscar alquileres por ID de cliente
    private void buscarAlquileresPorCliente() {
        String idTexto = txtBuscarAlquiler.getText().trim();

        if (idTexto.isEmpty()) {
            cargarAlquileres(); // Si no se ingresa ID, mostrar todos
            return;
        }

        try {
            int idCliente = Integer.parseInt(idTexto);
            modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar los resultados

            List<Alquiler> listaFiltrada = alquilerController.obtenerAlquileresPorCliente(idCliente);

            for (Alquiler alquiler : listaFiltrada) {
                modeloTabla.addRow(new Object[]{
                    alquiler.getId(),
                    alquiler.getNombreCliente(),
                    alquiler.getIdVehiculo(),
                    alquiler.getFechaInicio(),
                    alquiler.getFechaFin(),
                    alquiler.getCosto()
                });
            }

            if (listaFiltrada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El cliente con ID " + idCliente + " no tiene alquileres.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠️ Ingrese un ID válido (número entero).");
        }
    }

   // 🔹 Método para ver detalles de un alquiler seleccionado
    private void verDetalles() {
        int filaSeleccionada = tablaAlquileres.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un alquiler para ver detalles.");
            return;
        }

        // 🔹 Obtener los datos de la fila seleccionada
        String cliente = modeloTabla.getValueAt(filaSeleccionada, 1).toString();
        String vehiculo = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
        String fechaInicio = modeloTabla.getValueAt(filaSeleccionada, 3).toString();
        String fechaFin = modeloTabla.getValueAt(filaSeleccionada, 4).toString();
        double costo = Double.parseDouble(modeloTabla.getValueAt(filaSeleccionada, 5).toString());

        // 🔹 Abrir `frmDetallesAlquiler` y pasar los datos
        frmDetallesAlquiler detallesAlquiler = new frmDetallesAlquiler(cliente, vehiculo, fechaInicio, fechaFin, costo);
        detallesAlquiler.setVisible(true);
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlquileres = new javax.swing.JTable();
        btnVerDetalles = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarAlquiler = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscarAlquiler = new javax.swing.JButton();
        Cerrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        tablaAlquileres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Vehículo", "Fecha Inicio", "Fecha Fin", "Costo"
            }
        ));
        jScrollPane1.setViewportView(tablaAlquileres);

        btnVerDetalles.setText("VER DETALLES");
        btnVerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetallesActionPerformed(evt);
            }
        });

        jLabel3.setText("Tabla de alquileres:");

        jLabel2.setText("Buscar Alquiler:");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Auto.png"))); // NOI18N
        jLabel1.setText("LISTADO DE ALQUILERES");

        btnBuscarAlquiler.setText("BUSCAR");
        btnBuscarAlquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlquilerActionPerformed(evt);
            }
        });

        Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/X.png"))); // NOI18N
        Cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cerrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(txtBuscarAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarAlquiler)
                        .addGap(161, 161, 161))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(180, 180, 180)
                        .addComponent(Cerrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerDetalles)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(Cerrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarAlquiler)))
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerDetalles)
                .addGap(55, 55, 55))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(11, 15, 460, 470);

        setSize(new java.awt.Dimension(486, 512));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    private void Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cerrar1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_Cerrar1ActionPerformed

    private void btnBuscarAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlquilerActionPerformed
        buscarAlquileresPorCliente();
    }//GEN-LAST:event_btnBuscarAlquilerActionPerformed

    private void btnVerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetallesActionPerformed
        verDetalles();
       // frmDetallesAlquiler detallesVentana = new frmDetallesAlquiler(); // Crea la nueva ventana
   // detallesVentana.setVisible(true);  // La muestra en pantalla
    }//GEN-LAST:event_btnVerDetallesActionPerformed

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
            java.util.logging.Logger.getLogger(frmListaAlquileres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmListaAlquileres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmListaAlquileres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmListaAlquileres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //new frmListaAlquileres().setVisible(true);
            }
       });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar1;
    private javax.swing.JButton btnBuscarAlquiler;
    private javax.swing.JButton btnVerDetalles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAlquileres;
    private javax.swing.JTextField txtBuscarAlquiler;
    // End of variables declaration//GEN-END:variables
}
