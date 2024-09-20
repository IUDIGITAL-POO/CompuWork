package IUDIGITAL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<String> empleados = new ArrayList<>();
    private static List<String> departamentos = new ArrayList<>();
    private static List<String> metricas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                    registrarUsuario(scanner);
                    break;
                case 2:
                    iniciarSesion(scanner);
                    break;
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

    private static void registrarUsuario(Scanner scanner) {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.println("Seleccione un rol:");
        System.out.println("1. Administrador");
        System.out.println("2. Departamento");
        System.out.println("3. Empleado");
        int rolOpcion = scanner.nextInt();
        scanner.nextLine();

        String rol = "";
        switch (rolOpcion) {
            case 1:
                rol = "Administrador";
                break;
            case 2:
                rol = "Departamento";
                break;
            case 3:
                rol = "Empleado";
                break;
            default:
                System.out.println("Opción de rol inválida, registro fallido.");
                return;
        }

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, rol);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");
    }

    private static void iniciarSesion(Scanner scanner) {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getNombreUsuario() + " (Rol: " + usuario.getRol() + ")");
                if (usuario.getRol().equals("Administrador")) {
                    mostrarMenuAdministrador(scanner);
                } else if (usuario.getRol().equals("Departamento")) {
                    mostrarMenuDepartamento(scanner);
                }else if (usuario.getRol().equals("Empleado")) {
                    mostrarMenuEmpleado(scanner);
                }
                return;
            }
        }
        System.out.println("Nombre de usuario o contraseña incorrectos.");
    }

    private static void mostrarMenuAdministrador(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Administrador ---");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Actualizar Empleado");
            System.out.println("3. Registrar Departamento");
            System.out.println("4. Actualizar Departamento");
            System.out.println("5. Registrar Métrica");
            System.out.println("6. Actualizar Métrica");
            System.out.println("7. Generar Informe Departamental");
            System.out.println("8. Generar Informe Individual");
            System.out.println("9. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarEmpleado(scanner);
                    break;
                case 2:
                    actualizarEmpleado(scanner);
                    break;
                case 3:
                    registrarDepartamento(scanner);
                    break;
                case 4:
                    actualizarDepartamento(scanner);
                    break;
                case 5:
                    registrarMetrica(scanner);
                    break;
                case 6:
                    actualizarMetrica(scanner);
                    break;
                case 7:
                    generarInformeDepartamental();
                    break;
                case 8:
                    generarInformeIndividual(scanner);
                    break;
                case 9:
                    salir = true;
                    System.out.println("Cerrando sesión de administrador...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    private static void mostrarMenuDepartamento(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Departamento ---");
            System.out.println("1. Generar Informe Departamental");
            System.out.println("2. Generar Informe Individual");
            System.out.println("3. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    generarInformeDepartamental();
                    break;
                case 2:
                    generarInformeIndividual(scanner);
                    break;
                case 3:
                    salir = true;
                    System.out.println("Cerrando sesión de departamento...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    private static void registrarEmpleado(Scanner scanner) {
        System.out.print("Ingrese el nombre del empleado: ");
        String empleado = scanner.nextLine();
        empleados.add(empleado);
        System.out.println("Empleado registrado exitosamente.");
    }

    private static void actualizarEmpleado(Scanner scanner) {
        System.out.print("Ingrese el nombre del empleado a actualizar: ");
        String empleado = scanner.nextLine();
        if (empleados.contains(empleado)) {
            System.out.print("Ingrese el nuevo nombre del empleado: ");
            String nuevoNombre = scanner.nextLine();
            empleados.set(empleados.indexOf(empleado), nuevoNombre);
            System.out.println("Empleado actualizado exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void registrarDepartamento(Scanner scanner) {
        System.out.print("Ingrese el nombre del departamento: ");
        String departamento = scanner.nextLine();
        departamentos.add(departamento);
        System.out.println("Departamento registrado exitosamente.");
    }

    private static void actualizarDepartamento(Scanner scanner) {
        System.out.print("Ingrese el nombre del departamento a actualizar: ");
        String departamento = scanner.nextLine();
        if (departamentos.contains(departamento)) {
            System.out.print("Ingrese el nuevo nombre del departamento: ");
            String nuevoNombre = scanner.nextLine();
            departamentos.set(departamentos.indexOf(departamento), nuevoNombre);
            System.out.println("Departamento actualizado exitosamente.");
        } else {
            System.out.println("Departamento no encontrado.");
        }
    }

    private static void registrarMetrica(Scanner scanner) {
        System.out.print("Ingrese el nombre de la métrica: ");
        String metrica = scanner.nextLine();
        metricas.add(metrica);
        System.out.println("Métrica registrada exitosamente.");
    }

    private static void actualizarMetrica(Scanner scanner) {
        System.out.print("Ingrese el nombre de la métrica a actualizar: ");
        String metrica = scanner.nextLine();
        if (metricas.contains(metrica)) {
            System.out.print("Ingrese el nuevo nombre de la métrica: ");
            String nuevoNombre = scanner.nextLine();
            metricas.set(metricas.indexOf(metrica), nuevoNombre);
            System.out.println("Métrica actualizada exitosamente.");
        } else {
            System.out.println("Métrica no encontrada.");
        }
    }

    private static void generarInformeDepartamental() {
        System.out.println("\n--- Generando Informe Departamental ---");
        System.out.println("Informe departamental generado exitosamente.");
    }

    private static void generarInformeIndividual(Scanner scanner) {
        System.out.println("\n--- Generando Informe Individual ---");
        System.out.print("Ingrese el nombre del empleado: ");
        String empleado = scanner.nextLine();
        if (empleados.contains(empleado)) {
            System.out.println("Informe individual generado para el empleado: " + empleado);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void mostrarMenuEmpleado(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Empleado ---");
            System.out.println("1. Consultar Informe de Rendimiento");
            System.out.println("2. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    consultarInformeRendimiento(scanner);
                    break;
                case 2:
                    salir = true;
                    System.out.println("Cerrando sesión de empleado...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    private static void consultarInformeRendimiento(Scanner scanner) {
        System.out.println("\n--- Consultando Informe de Rendimiento ---");
        System.out.print("Ingrese su nombre: ");
        String empleado = scanner.nextLine();
        if (empleados.contains(empleado)) {
            System.out.println("Informe de rendimiento para " + empleado + ": [Detalles del informe aquí]");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

}