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
    public static void registrarEmpleado() {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el ID del empleado: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de empleado inválido.");
        }
        System.out.print("Ingrese el tipo de empleado(temporal/permanente): ");
        String tipoEmpleado = scanner.nextLine().toLowerCase();
        System.out.print("Ingrese el salario del empleado: ");
        double salario;
        try {
            salario = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Salario inválido.");
        }

        if (departamentos.isEmpty()) {
            throw new IllegalStateException("No hay departamentos registrados. Por favor, registre un departamento primero.");
        }

        System.out.println("Seleccione un departamento para el empleado:");
        for (int i = 0; i < departamentos.size(); i++) {
            System.out.println((i + 1) + ". " + departamentos.get(i).getNombre());
        }
        int opcionDepartamento;
        do {
            System.out.print("Ingrese el número del departamento: ");
            opcionDepartamento = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea restante
        } while (opcionDepartamento < 1 || opcionDepartamento > departamentos.size());
        Departamento departamentoSeleccionado = departamentos.get(opcionDepartamento - 1);

        Empleado nuevoEmpleado = null;
        if (tipoEmpleado.equals("temporal")) {
            System.out.print("Ingrese la fecha de inicio (dd/mm/aaaa): ");
            String fechaInicio = scanner.nextLine();

            System.out.print("Ingrese la fecha de fin (dd/mm/aaaa): ");
            String fechaFin = scanner.nextLine();

            nuevoEmpleado = new EmpleadoTemporal(nombre, id, tipoEmpleado, salario, fechaInicio, fechaFin);
        } else if (tipoEmpleado.equals("permanente")) {
            nuevoEmpleado = new EmpleadoPermanente(nombre, id, tipoEmpleado, salario);
        } else {
            throw new IllegalArgumentException("Tipo de empleado inválido.");
        }
        nuevoEmpleado.asignarDepartamento(departamentoSeleccionado);
        empleados.add(nuevoEmpleado);
        System.out.println("Empleado registrado exitosamente en el departamento: " + departamentoSeleccionado.getNombre());
    }

    public static void actualizarEmpleado() throws IllegalArgumentException {
        System.out.print("Ingrese el ID del empleado a actualizar: ");
        int idEmpleado;
        try {
            idEmpleado = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de empleado inválido.");
        }

        Empleado empleadoAActualizar = null;
        for (Empleado emp : empleados) {
            if (emp.getId() == idEmpleado) {
                empleadoAActualizar = emp;
                break;
            }
        }

        if (empleadoAActualizar == null) {
            throw new IllegalArgumentException("Empleado no encontrado.");
        }

        System.out.println("Empleado encontrado: " + empleadoAActualizar.getNombre());
        System.out.print("Ingrese el nuevo nombre (dejar vacío para mantener el nombre actual): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            empleadoAActualizar.setNombre(nuevoNombre);
        }
        System.out.print("Ingrese el nuevo tipo de empleado (dejar vacío para mantener el actual): ");
        String nuevoTipoEmpleado = scanner.nextLine();
        if (!nuevoTipoEmpleado.isEmpty()) {
            empleadoAActualizar.setTipoEmpleado(nuevoTipoEmpleado);
        }
        System.out.print("Ingrese el nuevo salario (dejar vacío para mantener el actual): ");
        String nuevoSalarioStr = scanner.nextLine();
        if (!nuevoSalarioStr.isEmpty()) {
            try {
                double nuevoSalario = Double.parseDouble(nuevoSalarioStr);
                empleadoAActualizar.setSalario(nuevoSalario);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Salario inválido.");
            }
        }
        System.out.println("Empleado actualizado exitosamente.");
    }

    public void eliminarEmpleado() {
        System.out.println("Ingrese el ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        Empleado empleadoAEliminar = null;
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                empleadoAEliminar = empleado;
                break;
            }
        }

        if (empleadoAEliminar != null) {
            empleados.remove(empleadoAEliminar);
            System.out.println("Empleado con ID: " + id + " ha sido eliminado.");
        } else {
            System.out.println("No se encontró un empleado con ID: " + id);
        }
    }

    public Empleado buscarEmpleadoPorId(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
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
