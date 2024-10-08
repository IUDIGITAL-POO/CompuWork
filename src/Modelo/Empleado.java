package Modelo;

public class Empleado {
    // Atributos
    private String nombre;
    private int id;
    private String tipoEmpleado;
    private double salario;
    private Departamento departamento;  // Relación con Departamento

    // Constructor
    public Empleado(String nombre, int id, String tipoEmpleado, double salario) {
        this.nombre = nombre;
        this.id = id;
        this.tipoEmpleado = tipoEmpleado;
        this.salario = salario;
        this.departamento = null;  // Inicialmente sin asignación
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para tipoEmpleado
    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    // Getter y Setter para salario
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // Metodo para asignar un departamento
    public void asignarDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // Metodo para cambiar el departamento del empleado
    public void cambiarDepartamento(Departamento nuevoDepartamento) {
        this.departamento = nuevoDepartamento;
    }

    // Metodo para obtener el departamento actual del empleado
    public Departamento getDepartamento() {
        return this.departamento;
    }

    // Metodo para actualizar el salario del empleado
    public void actualizarSalario(double nuevoSalario) {
        this.salario = nuevoSalario;
    }

    // Metodo para eliminar al empleado
    public void eliminarEmpleado() {
        // Lógica de eliminación. Por ejemplo, quitar al empleado de listas o sistemas.
        System.out.println("Empleado con ID: " + id + " ha sido eliminado.");
        this.departamento = null;  // Elimina la referencia del departamento si es necesario
    }

    @Override
    public String toString() {
        return "Empleado: " + nombre + " (ID: " + id + ")";
    }
}




