package controlador;

import conexion.Database;
import modelo.Alquiler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlquilerController {
    
    
    public boolean existeAlquiler(int idVehiculo, Date fechaInicio, Date fechaFin) {
    String sql = "SELECT COUNT(*) FROM alquileres WHERE id_vehiculo = ? AND " +
                 "(fecha_inicio BETWEEN ? AND ? OR fecha_fin BETWEEN ? AND ?)";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idVehiculo);
        stmt.setDate(2, new java.sql.Date(fechaInicio.getTime()));
        stmt.setDate(3, new java.sql.Date(fechaFin.getTime()));
        stmt.setDate(4, new java.sql.Date(fechaInicio.getTime()));
        stmt.setDate(5, new java.sql.Date(fechaFin.getTime()));

        ResultSet rs = stmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return true; // 🔹 Ya existe un alquiler en esas fechas
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // 🔹 No hay alquiler registrado en esas fechas
}
    public List<Alquiler> obtenerAlquileresPorFecha(Date fechaInicio, Date fechaFin) {
        List<Alquiler> alquileres = new ArrayList<>();
        String sql = "SELECT * FROM alquileres WHERE fecha_inicio >= ? AND fecha_fin <= ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, fechaInicio);
            stmt.setDate(2, fechaFin);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alquiler alquiler = new Alquiler(
                    rs.getInt("id"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_vehiculo"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getDouble("costo")
                );
                alquileres.add(alquiler);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alquileres;
    }

    // 🔹 Método para registrar un alquiler en MySQL
   public boolean registrarAlquiler(Alquiler alquiler) {
    String sql = "INSERT INTO alquileres (id_cliente, id_vehiculo, fecha_inicio, fecha_fin, costo) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        System.out.println("📌 Registrando alquiler en la base de datos...");
        System.out.println("Cliente: " + alquiler.getIdCliente() + " - Vehículo: " + alquiler.getIdVehiculo());
        System.out.println("Fecha Inicio: " + alquiler.getFechaInicio() + " - Fecha Fin: " + alquiler.getFechaFin());
        System.out.println("Costo Total: " + alquiler.getCosto());

        stmt.setInt(1, alquiler.getIdCliente());
        stmt.setInt(2, alquiler.getIdVehiculo());
        stmt.setDate(3, new java.sql.Date(alquiler.getFechaInicio().getTime()));
        stmt.setDate(4, new java.sql.Date(alquiler.getFechaFin().getTime()));
        stmt.setDouble(5, alquiler.getCosto());

        return stmt.executeUpdate() > 0; 

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    // 🔹 Método para obtener todos los alquileres de la base de datos
    public List<Alquiler> obtenerAlquileres() {
        List<Alquiler> alquileres = new ArrayList<>();
        String sql = "SELECT * FROM alquileres";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alquiler alquiler = new Alquiler(
                    rs.getInt("id"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_vehiculo"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getDouble("costo")
                );
                alquileres.add(alquiler);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alquileres;
    }

    // 🔹 Método para buscar alquileres por cliente o vehículo
    public List<Alquiler> buscarAlquileres(String filtro) {
        List<Alquiler> alquileres = new ArrayList<>();
        String sql = "SELECT * FROM alquileres WHERE id_cliente IN (SELECT id FROM clientes WHERE nombre LIKE ?) OR id_vehiculo IN (SELECT id FROM vehiculos WHERE placa LIKE ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + filtro + "%");
            stmt.setString(2, "%" + filtro + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alquiler alquiler = new Alquiler(
                    rs.getInt("id"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_vehiculo"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getDouble("costo")
                );
                alquileres.add(alquiler);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alquileres;
    }

    // 🔹 Método para actualizar un alquiler en MySQL
    public boolean actualizarAlquiler(Alquiler alquiler) {
        String sql = "UPDATE alquileres SET id_cliente=?, id_vehiculo=?, fecha_inicio=?, fecha_fin=?, costo=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alquiler.getIdCliente());
            stmt.setInt(2, alquiler.getIdVehiculo());
            stmt.setDate(3, new java.sql.Date(alquiler.getFechaInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(alquiler.getFechaFin().getTime()));
            stmt.setDouble(5, alquiler.getCosto());
            stmt.setInt(6, alquiler.getId());

            return stmt.executeUpdate() > 0; // Devuelve true si se actualizó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Método para eliminar un alquiler de la base de datos
    public boolean eliminarAlquiler(int id) {
        String sql = "DELETE FROM alquileres WHERE id=?";

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

    
