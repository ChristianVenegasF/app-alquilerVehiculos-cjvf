
package vista;

import controlador.AlquilerController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Alquiler;


public class frmReporteIngresos extends javax.swing.JFrame {
    private AlquilerController alquilerController;
    private DefaultTableModel modeloTabla;
    
    public frmReporteIngresos() {
        alquilerController = new AlquilerController();
        initComponents();
        cargarReporteIngresos();
    }

    private void cargarReporteIngresos() {
    DefaultTableModel modelo = (DefaultTableModel) tablaIngresos.getModel();
    modelo.setRowCount(0); // 🔹 Limpiar la tabla antes de agregar datos

    List<Alquiler> listaAlquileres = alquilerController.obtenerAlquileres();

    for (Alquiler alquiler : listaAlquileres) {
        Object[] fila = {
            alquiler.getIdCliente(),   // 🔹 Cliente
            alquiler.getIdVehiculo(),  // 🔹 Vehículo
            alquiler.getFechaInicio(), // 🔹 Fecha de inicio
            String.format("%.2f", alquiler.getCostoTotal()) // 🔹 Ingreso
        };
        modelo.addRow(fila);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcFechaDesde = new com.toedter.calendar.JDateChooser();
        dcFechaHasta = new com.toedter.calendar.JDateChooser();
        btnGenerarReporte = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaIngresos = new javax.swing.JTable();
        Cerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Auto.png"))); // NOI18N
        jLabel1.setText("REPORTE DE INGRESOS ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(110, 20, 180, 40);

        jLabel2.setText("Fecha Desde:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 70, 90, 30);

        jLabel3.setText("Fecha Hasta:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 110, 90, 30);
        jPanel1.add(dcFechaDesde);
        dcFechaDesde.setBounds(110, 70, 170, 22);
        jPanel1.add(dcFechaHasta);
        dcFechaHasta.setBounds(110, 110, 160, 22);

        btnGenerarReporte.setText("GENERAR REPORTE");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerarReporte);
        btnGenerarReporte.setBounds(20, 160, 150, 30);

        jLabel4.setText("Tabla de ingresos:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 210, 110, 30);

        tablaIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Vehiculo", "Fecha Inicio", "Ingreso"
            }
        ));
        jScrollPane1.setViewportView(tablaIngresos);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 250, 452, 220);

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/X.png"))); // NOI18N
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        jPanel1.add(Cerrar);
        Cerrar.setBounds(450, 0, 30, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(6, 6, 480, 510);

        setSize(new java.awt.Dimension(505, 546));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
      this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
      java.util.Date fechaInicio = dcFechaDesde.getDate();
    java.util.Date fechaFin = dcFechaHasta.getDate();

    // Verificar que ambas fechas estén seleccionadas
    if (fechaInicio == null || fechaFin == null) {
        JOptionPane.showMessageDialog(this, "Seleccione un rango de fechas válido.");
        return;
    }

    // Validar que la fecha de inicio no sea mayor a la fecha fin
    if (fechaInicio.after(fechaFin)) {
        JOptionPane.showMessageDialog(this, "La fecha de inicio debe ser anterior a la fecha de fin.");
        return;
    }

    // Convertir las fechas a formato SQL
    java.sql.Date sqlFechaInicio = new java.sql.Date(fechaInicio.getTime());
    java.sql.Date sqlFechaFin = new java.sql.Date(fechaFin.getTime());

    // Obtener los alquileres filtrados por fechas
    List<Alquiler> listaAlquileres = alquilerController.obtenerAlquileresPorFecha(sqlFechaInicio, sqlFechaFin);

    // Limpiar la tabla antes de agregar nuevos datos
    DefaultTableModel modelo = (DefaultTableModel) tablaIngresos.getModel();
    modelo.setRowCount(0);

    double totalIngresos = 0.0;

    for (Alquiler alquiler : listaAlquileres) {
        Object[] fila = {
            alquiler.getIdCliente(),
            alquiler.getIdVehiculo(),
            alquiler.getFechaInicio(),
            String.format("%.2f", alquiler.getCostoTotal())
        };
        modelo.addRow(fila);
        totalIngresos += alquiler.getCostoTotal();
    }

    JOptionPane.showMessageDialog(this, "Reporte generado con éxito. Total ingresos: $" + totalIngresos);

    }//GEN-LAST:event_btnGenerarReporteActionPerformed

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
            java.util.logging.Logger.getLogger(frmReporteIngresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReporteIngresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReporteIngresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReporteIngresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReporteIngresos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JButton btnGenerarReporte;
    private com.toedter.calendar.JDateChooser dcFechaDesde;
    private com.toedter.calendar.JDateChooser dcFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaIngresos;
    // End of variables declaration//GEN-END:variables
}
