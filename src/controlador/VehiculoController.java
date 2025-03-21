package controlador;

import conexion.Database;
import modelo.Vehiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoController {
    
    // 🔹 Método para obtener solo los vehículos disponibles
    public List<Vehiculo> obtenerVehiculosDisponibles() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE disponible = true"; // Filtrar solo los disponibles

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("✅ Consulta ejecutada: " + sql);

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("placa"),
                    rs.getBoolean("disponible")
                );
                vehiculos.add(vehiculo);
                System.out.println("📌 Vehículo disponible encontrado: " + vehiculo.getModelo());
            }

        } catch (SQLException e) {
            System.out.println("❌ Error en obtenerVehiculosDisponibles: " + e.getMessage());
        }

        System.out.println("🔹 Total vehículos disponibles: " + vehiculos.size());
        return vehiculos;
    }

    // 🔹 Método para registrar un vehículo en MySQL
    public boolean registrarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (marca, modelo, placa, disponible) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getMarca());
            stmt.setString(2, vehiculo.getModelo());
            stmt.setString(3, vehiculo.getPlaca());
            stmt.setBoolean(4, vehiculo.isDisponible());

            return stmt.executeUpdate() > 0; // Devuelve true si la inserción fue exitosa

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Método para obtener todos los vehículos de la base de datos
    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("placa"),
                    rs.getBoolean("disponible")
                );
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }

    // 🔹 Método para buscar vehículos por marca, modelo o placa
    public List<Vehiculo> buscarVehiculos(String filtro) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE marca LIKE ? OR modelo LIKE ? OR placa LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + filtro + "%");
            stmt.setString(2, "%" + filtro + "%");
            stmt.setString(3, "%" + filtro + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("placa"),
                    rs.getBoolean("disponible")
                );
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }

    // 🔹 Método para actualizar un vehículo en MySQL
    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET marca=?, modelo=?, placa=?, disponible=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getMarca());
            stmt.setString(2, vehiculo.getModelo());
            stmt.setString(3, vehiculo.getPlaca());
            stmt.setBoolean(4, vehiculo.isDisponible());
            stmt.setInt(5, vehiculo.getId());

            return stmt.executeUpdate() > 0; // Devuelve true si la actualización fue exitosa

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Método para eliminar un vehículo de la base de datos
    public boolean eliminarVehiculo(int id) {
        String sql = "DELETE FROM vehiculos WHERE id=?";

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
