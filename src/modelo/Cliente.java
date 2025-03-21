package modelo;

public class Cliente {
    private int id;
    private String nombre;
    private String documento;
    private String telefono;
    private String direccion;

    // 🔹 Constructor vacío
    public Cliente() {}

    // 🔹 Constructor con parámetros
    public Cliente(int id, String nombre, String documento, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // 🔹 Getters (Métodos para obtener los valores de los atributos)
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }

    // 🔹 Setters (Métodos para modificar los valores de los atributos)
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // 🔹 Método toString() para mostrar información del cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
