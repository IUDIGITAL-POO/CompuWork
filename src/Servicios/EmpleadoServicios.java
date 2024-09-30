package Servicios;

import Modelo.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoServicios {
    private static List<Empleado> empleados;
    private static List<Departamento> departamentos;

    public EmpleadoServicios(List<Departamento> departamentos) {
        this.empleados = new ArrayList<>();
        this.departamentos = departamentos;
    }

    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void actualizarEmpleado(Empleado empleado) {
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

    public void inicializarDatos(Departamento rrhh) {
        Empleado emp1 = new EmpleadoPermanente("Juan Pérez", 1, "Permanente", 50000.0);
        emp1.asignarDepartamento(rrhh);
        empleados.add(emp1);

        Empleado emp2 = new EmpleadoTemporal("María López", 2, "Temporal", 30000.0, "01/01/2024", "31/12/2024");
        emp2.asignarDepartamento(rrhh);
        empleados.add(emp2);
    }
}