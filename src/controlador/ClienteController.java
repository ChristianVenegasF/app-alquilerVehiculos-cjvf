package controlador;

import conexion.Database;
import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    // 🔹 Método para registrar un cliente en MySQL
    public boolean registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, documento, telefono, direccion) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());

            return stmt.executeUpdate() > 0; // Devuelve true si se insertó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Método para obtener todos los clientes desde MySQL
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("documento"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // 🔹 Método para buscar clientes por nombre o documento
    public List<Cliente> buscarClientes(String filtro) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nombre LIKE ? OR documento LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + filtro + "%");
            stmt.setString(2, "%" + filtro + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("documento"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // 🔹 Método para actualizar un cliente en MySQL
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre=?, documento=?, telefono=?, direccion=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());
            stmt.setInt(5, cliente.getId());

            return stmt.executeUpdate() > 0; // Devuelve true si se actualizó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Método para eliminar un cliente de la base de datos
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0; // Devuelve true si se eliminó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
