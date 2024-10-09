package Modelo;

public class Departamento {
    private int id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String jefe;
    private int codigo; // Asegúrate de tener este campo
    private String tipo; // Asegúrate de tener este campo
    private String telefono; // Asegúrate de tener este campo

    // Constructor
    public Departamento(int id, String nombre, String descripcion, String ubicacion, String jefe) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.jefe = jefe;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    // Métodos adicionales
    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    // Método toString
    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", jefe='" + jefe + '\'' +
                ", codigo=" + codigo +
                ", tipo='" + tipo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}