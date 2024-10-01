package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Departamento {

    private int codigo;
    private String nombre;
    private String tipo;
    private String ubicacion;
    private String telefono;
    private List<Empleado> empleados;

    // Constructor
    public Departamento(int codigo, String nombre, String tipo, String ubicacion, String telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.empleados = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter y Setter para ubicacion
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Getter y Setter para telefono
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void eliminarEmpleado(Empleado empleado) {
        empleados.remove(empleado);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void visualizarEmpleados() {
        System.out.println("Empleados del departamento " + nombre + ":");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en este departamento.");
        } else {
            for (Empleado empleado : empleados) {
                System.out.println("- " + empleado.getNombre());
            }
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
