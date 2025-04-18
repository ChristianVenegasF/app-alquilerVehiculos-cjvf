package modelo;

import java.util.Date;

public class Alquiler {
    private int id;
    private int idCliente;
    private int idVehiculo;
    private Date fechaInicio;
    private Date fechaFin;
    private double costo;
    
    private String nombreCliente;

public String getNombreCliente() {
    return nombreCliente;
}

public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
}
    // 🔹 Constructor vacío
    public Alquiler() {}
    
    public double getCostoTotal() {
    return costo; // Asegúrate de que 'costo' existe en la clase
}

    // 🔹 Constructor con parámetros
    public Alquiler(int id, int idCliente, int idVehiculo, Date fechaInicio, Date fechaFin, double costo) {
        this.id = id;
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
    }

    // 🔹 Getters (Métodos para obtener los valores de los atributos)
    public int getId() { return id; }
    public int getIdCliente() { return idCliente; }
    public int getIdVehiculo() { return idVehiculo; }
    public Date getFechaInicio() { return fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public double getCosto() { return costo; }

    // 🔹 Setters (Métodos para modificar los valores de los atributos)
    public void setId(int id) { this.id = id; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
    public void setCosto(double costo) { this.costo = costo; }

     /*public String getNombreCliente() {
        // Retorna un valor por defecto para evitar errores, ya que 'nombreCliente' no existe en la clase.
        return "Nombre no disponible"; 
    }*/
    // 🔹 Método toString() para mostrar información del alquiler
    @Override
    public String toString() {
        return "Alquiler{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", idVehiculo=" + idVehiculo +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", costo=" + costo +
                '}';
    }
}
