
package vista;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Vehiculo;
import controlador.ClienteController;
import controlador.VehiculoController;

public class frmCalculoTarifas extends javax.swing.JFrame {
    private ClienteController clienteController;
    private VehiculoController vehiculoController;
    
    public frmCalculoTarifas() {
        initComponents();
        // ✅ Inicializar controladores
        clienteController = new ClienteController();
        vehiculoController = new VehiculoController();

        // ✅ Cargar datos en los ComboBox
        cargarClientes();
        cargarVehiculosDisponibles();
    }
    
    private void calcularTarifa() {
    try {
        // ✅ Verificar que se seleccione un cliente y un vehículo
        if (cbClientes.getSelectedItem() == null || cbVehiculos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente y un vehículo.");
            return;
        }

        // ✅ Extraer ID del cliente y del vehículo
        int idCliente = Integer.parseInt(cbClientes.getSelectedItem().toString().split(" - ")[0]);
        int idVehiculo = Integer.parseInt(cbVehiculos.getSelectedItem().toString().split(" - ")[0]);

        // ✅ Verificar que los campos de entrada no estén vacíos
        if (txtTarifaDiaria.getText().trim().isEmpty() || txtDiasUso.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        // ✅ Convertir valores a números
        double tarifaDiaria = Double.parseDouble(txtTarifaDiaria.getText());
        int diasUso = Integer.parseInt(txtDiasUso.getText());

        if (diasUso <= 0) {
            JOptionPane.showMessageDialog(this, "Los días de uso deben ser mayores a 0.");
            return;
        }

        // ✅ Calcular el costo total
        double total = tarifaDiaria * diasUso;
        txtCostoTotal.setText(String.format("%.2f", total));

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.");
    }
}
    private void cargarClientes() {
        cbClientes.removeAllItems(); // 🔹 Limpiar antes de agregar nuevos datos
        List<Cliente> clientes = clienteController.obtenerClientes(); // 🔹 Llamar al controlador

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes registrados.");
        } else {
            for (Cliente cliente : clientes) {
                cbClientes.addItem(cliente.getId() + " - " + cliente.getNombre());
            }
        }
    }

private void cargarVehiculosDisponibles() {
        cbVehiculos.removeAllItems();
        List<Vehiculo> vehiculos = vehiculoController.obtenerVehiculosDisponibles(); // 🔹 Llamar al controlador

        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay vehículos disponibles.");
        } else {
            for (Vehiculo vehiculo : vehiculos) {
                cbVehiculos.addItem(vehiculo.getId() + " - " + vehiculo.getModelo());
            }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbClientes = new javax.swing.JComboBox<>();
        txtDiasUso = new javax.swing.JTextField();
        txtCostoTotal = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbVehiculos = new javax.swing.JComboBox<>();
        Cerrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTarifaDiaria = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Auto.png"))); // NOI18N
        jLabel1.setText("CÁLCULO DE TARIFAS ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(100, 20, 180, 30);

        jLabel2.setText(" Cliente:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 70, 60, 30);

        jLabel3.setText("Vehículo:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 110, 70, 30);

        jLabel4.setText("Días de Uso:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 170, 80, 30);

        jLabel5.setText("Costo Total:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 200, 80, 30);

        jPanel1.add(cbClientes);
        cbClientes.setBounds(100, 80, 130, 22);
        jPanel1.add(txtDiasUso);
        txtDiasUso.setBounds(100, 170, 64, 22);

        txtCostoTotal.setEditable(false);
        jPanel1.add(txtCostoTotal);
        txtCostoTotal.setBounds(100, 210, 90, 22);

        btnCalcular.setText("CALCULAR TARIFA");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        jPanel1.add(btnCalcular);
        btnCalcular.setBounds(30, 260, 140, 30);

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(180, 260, 100, 30);

        jPanel1.add(cbVehiculos);
        cbVehiculos.setBounds(100, 110, 130, 22);

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/X.png"))); // NOI18N
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        jPanel1.add(Cerrar);
        Cerrar.setBounds(340, 0, 30, 20);

        jLabel6.setText("Tarifa:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 150, 70, 20);
        jPanel1.add(txtTarifaDiaria);
        txtTarifaDiaria.setBounds(100, 140, 100, 22);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(5, 8, 370, 340);

        setSize(new java.awt.Dimension(391, 364));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
       this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        calcularTarifa(); // 🔹 Llamamos al método que realiza el cálculo
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCalculoTarifas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbClientes;
    private javax.swing.JComboBox<String> cbVehiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtDiasUso;
    private javax.swing.JTextField txtTarifaDiaria;
    // End of variables declaration//GEN-END:variables
}
