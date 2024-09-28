package Servicios;

import Modelo.Departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartamentoServicios {
    private static List<Departamento> departamentos;
    private static final Scanner scanner = new Scanner(System.in);

    public DepartamentoServicios() {
        departamentos = new ArrayList<>();
    }

    public static void registrarDepartamento() {
        System.out.print("Ingrese el código del departamento: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del departamento: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el tipo del departamento: ");
        String tipo = scanner.nextLine();
        System.out.print("Ingrese la ubicación del departamento: ");
        String ubicacion = scanner.nextLine();
        System.out.print("Ingrese el teléfono del departamento: ");
        String telefono = scanner.nextLine();
        Departamento nuevoDepartamento = new Departamento(codigo, nombre, tipo, ubicacion, telefono);
        departamentos.add(nuevoDepartamento);
        System.out.println("Departamento registrado exitosamente.");
    }


    public static void actualizarDepartamento() {
        System.out.print("Ingrese el nombre del departamento a actualizar: ");
        String nombreDepartamento = scanner.nextLine();
        Departamento departamentoAActualizar = null;

        for (Departamento dep : departamentos) {
            if (dep.getNombre().equalsIgnoreCase(nombreDepartamento)) {
                departamentoAActualizar = dep;
                break;
            }
        }

        if (departamentoAActualizar != null) {
            System.out.print("Ingrese el nuevo nombre del departamento: ");
            String nuevoNombre = scanner.nextLine();
            departamentoAActualizar.setNombre(nuevoNombre);
            System.out.println("Departamento actualizado exitosamente.");
        } else {
            System.out.println("Departamento no encontrado.");
        }
    }

    public static void eliminarDepartamento(){
        System.out.print("Ingrese el nombre del departamento a eliminar: ");
        String nombreDepartamento = scanner.nextLine();
        Departamento departamentoAEliminar = null;
        for (Departamento dep : departamentos) {
            if (dep.getNombre().equalsIgnoreCase(nombreDepartamento)) {
                departamentoAEliminar = dep;
                break;
            }
        }
        if (departamentoAEliminar != null) {
            departamentos.remove(departamentoAEliminar);
            System.out.println("Departamento eliminado exitosamente.");
        } else {
            System.out.println("Departamento no encontrado.");
        }
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void inicializarDatos(){
        Departamento rrhh = new Departamento(1, "Recursos Humanos", "Administrativo", "Piso 2", "123-456-7890");
        departamentos.add(rrhh);
    }
}
