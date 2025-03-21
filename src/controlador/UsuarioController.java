package controlador;

import conexion.Database;
import modelo.Usuario;
import java.sql.*;

public class UsuarioController {

    // 🔹 Método para validar el inicio de sesión
    public Usuario autenticarUsuario(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("usuario"), rs.getString("contrasena"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retorna null si las credenciales son incorrectas
    }

    // 🔹 Método para registrar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getContrasena());

            return stmt.executeUpdate() > 0; // Devuelve true si la inserción fue exitosa

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Método para actualizar la contraseña de un usuario
    public boolean actualizarContrasena(int id, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevaContrasena);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0; // Devuelve true si se actualizó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Método para eliminar un usuario
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

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
