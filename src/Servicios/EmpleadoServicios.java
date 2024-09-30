package Servicios;

import Modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpleadoServicios {
    private static List<Empleado> empleados;
    private static List<Departamento> departamentos;
    private static final Scanner scanner = new Scanner(System.in);

    public EmpleadoServicios(List<Departamento> departamentos) {
        this.empleados = new ArrayList<>();
        this.departamentos = departamentos;
    }

    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void actualizarEmpleado(Empleado empleado) {
        // Buscar el empleado existente por ID y actualizarlo
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == empleado.getId()) {
                empleados.set(i, empleado);
                break;
            }
        }
    }

    public void eliminarEmpleado(int id) {
        empleados.removeIf(emp -> emp.getId() == id);
    }

    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }

    public void inicializarDatos(Departamento rrhh){
        Empleado emp1 = new Empleado("Juan Pérez", 1, "Permanente", 50000.0);
        emp1.asignarDepartamento(rrhh);
        empleados.add(emp1);

        Empleado emp2 = new Empleado("María López", 2, "Temporal", 30000.0);
        emp2.asignarDepartamento(rrhh);
        empleados.add(emp2);

    }
}
