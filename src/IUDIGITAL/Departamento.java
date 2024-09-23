package IUDIGITAL;

// Grupo 8.
// Alejandro Urrea.

// Atributos de 5 departamentos.
public class Departamento {

    private in codigo;
    private String nombre;
    private String tipo;
    private String ubicacion;
    private String telefono;
    private list<Empleado> empleados new ArrayList<Empleado>();

    // Constructores, getters y setters.
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }
    public void eliminarEmpleado(Empleado empleado) {
        empleados.remove(empleado);
    }
    public list<Empleado> getEmpleados() {
        return empleados;
    }

    // Metodo para visualizar 10 empleados.
    public void visualizarEmpleados() {
        System.out.println("Empleados del departamento " + nombre + ":");
        for (Empleado empleado : empleados) {
            System.out.println("- " + empleado.getNombre());
        }
    }

}
