package modelo;

public class Usuario {
    private int id;
    private String usuario;
    private String contrasena;

    // 🔹 Constructor vacío
    public Usuario() {}

    // 🔹 Constructor con parámetros
    public Usuario(int id, String usuario, String contrasena) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // 🔹 Getters (Métodos para obtener los valores de los atributos)
    public int getId() { return id; }
    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }

    // 🔹 Setters (Métodos para modificar los valores de los atributos)
    public void setId(int id) { this.id = id; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    // 🔹 Método toString() para mostrar información del usuario
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
