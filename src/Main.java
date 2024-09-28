import Servicios.GestionServicios;
import Modelo.Usuario;

import java.util.Scanner;

public class Main {
    private static GestionServicios gestionServicios = new GestionServicios();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        gestionServicios.inicializarDatos();
        mostrarMenuPrincipal();
    }
    public static void mostrarMenuPrincipal() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestionServicios.registrarUsuario();
                    break;
                case 2:
                    Usuario usuario = gestionServicios.iniciarSesion();
                    mostrarMenuPorRol(usuario.getRol());
                case 3:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenuPorRol(String rol) {
        if (rol.equals("Administrador")) {
            mostrarMenuAdministrador();
        } else if (rol.equals("Empleado")) {
            mostrarMenuEmpleado();
        }
    }

    private static void mostrarMenuAdministrador() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Administrador ---");
            System.out.println("1. Gestionar Empleados");
            System.out.println("2. Gestionar Departamentos");
            System.out.println("3. Gestionar Métrica");
            System.out.println("4. Generar Informes");
            System.out.println("5. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> gestionarEmpleados();
                case 2 -> gestionarDepartamentos();
                case 3 -> gestionarReportes();
                case 4 -> generarInformes();
                case 5 -> {
                    salir = true;
                    System.out.println("Cerrando sesión de administrador...");
                }
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    private static void gestionarEmpleados() {
        System.out.println("\n--- Gestionar Empleados ---");
        System.out.println("1. Registrar Empleado");
        System.out.println("2. Actualizar Empleado");
        System.out.println("3. Eliminar Empleado");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> gestionServicios.registrarEmpleado();
            case 2 -> gestionServicios.actualizarEmpleado();
            case 3 -> gestionServicios.eliminarEmpleado();
            case 4 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción inválida, intente nuevamente.");
        }
    }

    private static void gestionarDepartamentos() {
        System.out.println("\n--- Gestión de Departamentos ---");
        System.out.println("1. Registrar Departamento");
        System.out.println("2. Actualizar Departamento");
        System.out.println("3. Volver al menú anterior");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> gestionServicios.registrarDepartamento();
            case 2 -> gestionServicios.actualizarDepartamento();
            case 3 -> System.out.println("Volviendo al menú anterior...");
            default -> System.out.println("Opción inválida, intente nuevamente.");
        }
    }

    private static void gestionarReportes() {
        System.out.println("\n--- Gestión de Métricas ---");
        System.out.println("1. Registrar Métrica");
        System.out.println("2. Actualizar Métrica");
        System.out.println("3. Volver al menú anterior");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> gestionServicios.registrarReporte();
            case 2 -> gestionServicios.actualizarReporte();
            case 3 -> System.out.println("Volviendo al menú anterior...");
            default -> System.out.println("Opción inválida, intente nuevamente.");
        }
    }

    private static void generarInformes() {
        System.out.println("\n--- Generación de Informes ---");
        System.out.println("1. Generar Informe Departamental");
        System.out.println("2. Generar Informe Individual");
        System.out.println("3. Volver al menú anterior");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> gestionServicios.generarInformeDepartamental();
            case 2 -> gestionServicios.generarInformeIndividual();
            case 3 -> System.out.println("Volviendo al menú anterior...");
            default -> System.out.println("Opción inválida, intente nuevamente.");
        }
    }

    private static void mostrarMenuEmpleado() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Empleado ---");
            System.out.println("1. Consultar Informe de Rendimiento");
            System.out.println("2. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> gestionServicios.consultarInformeRendimiento();
                case 2 -> {
                    salir = true;
                    System.out.println("Cerrando sesión de empleado...");
                }
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }



}
