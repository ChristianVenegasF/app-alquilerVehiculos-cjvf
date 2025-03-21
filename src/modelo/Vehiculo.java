package modelo;

public class Vehiculo {
    private int id;
    private String marca;
    private String modelo;
    private String placa;
    private boolean disponible;

    // 🔹 Constructor vacío
    public Vehiculo() {}

    // 🔹 Constructor con parámetros
    public Vehiculo(int id, String marca, String modelo, String placa, boolean disponible) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.disponible = disponible;
    }

    // 🔹 Getters (Métodos para obtener los valores de los atributos)
    public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getPlaca() { return placa; }
    public boolean isDisponible() { return disponible; }

    // 🔹 Setters (Métodos para modificar los valores de los atributos)
    public void setId(int id) { this.id = id; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setPlaca(String placa) { this.placa = placa; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    // 🔹 Método toString() para mostrar información del vehículo
    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", disponible=" + (disponible ? "Sí" : "No") +
                '}';
    }
}
