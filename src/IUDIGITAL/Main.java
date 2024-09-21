package IUDIGITAL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<String> empleados = new ArrayList<>();
    private static List<String> departamentos = new ArrayList<>();
    private static List<String> metricas = new ArrayList<>();
    // Scanner como atributo privado para que se pueda aceder en toda la app
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> iniciarSesion();
                case 3 -> {
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                }
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        }

        scanner.close();
    }


    // Metodo para mostrar el menu principal
    private static void mostrarMenuPrincipal(){
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Iniciar Sesión");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarUsuario() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.println("Seleccione un rol:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");
        int rolOpcion = scanner.nextInt();
        scanner.nextLine();

        // Se puede usar un operador ternario para simplificar la asignación de la variable rol en base a la opción seleccionada por el usuario
        String rol = (rolOpcion == 1) ? "Administrador" : "Empleado";
        if(rolOpcion != 1 && rolOpcion != 2){
            System.out.println("Opción de rol inválida, registro fallido.");
            return;
        }


        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, rol);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");
    }


    // separe el metodo iniciarSesion en dos metodos para mejorar la legibilidad del codigo
    private static void iniciarSesion() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = buscarUsuario(nombreUsuario, contrasena);
        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getNombreUsuario() + " (Rol: " + usuario.getRol() + ")");
            mostrarMenuPorRol(usuario.getRol());
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    private static Usuario buscarUsuario(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
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

            switch (opcion){
                case 1 -> gestionarEmpleados();
                case 2 -> gestionarDepartamentos();
                case 3 -> gestionarMetricas();
                case 4 -> generarInformes();
                case 5 -> {
                    salir = true;
                    System.out.println("Cerrando sesión de administrador...");
                }
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }

        }
    }

    private static void gestionarEmpleados(){
        System.out.println("\n--- Gestionar Empleados ---");
        System.out.println("1. Registrar Empleado");
        System.out.println("2. Actualizar Empleado");
        System.out.println("3. Eliminar Empleado");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion){
            case 1 -> registrarEmpleado();
            case 2 -> actualizarEmpleado();
//            case 3 -> eliminarEmpleado();
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
            case 1 -> registrarDepartamento();
            case 2 -> actualizarDepartamento();
            case 3 -> System.out.println("Volviendo al menú anterior...");
        }
    }

    private static void gestionarMetricas() {
        System.out.println("\n--- Gestión de Métricas ---");
        System.out.println("1. Registrar Métrica");
        System.out.println("2. Actualizar Métrica");
        System.out.println("3. Volver al menú anterior");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> registrarMetrica();
            case 2 -> actualizarMetrica();
            case 3 -> System.out.println("Volviendo al menú anterior...");
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
            case 1 -> generarInformeDepartamental();
            case 2 -> generarInformeIndividual();
            case 3 -> System.out.println("Volviendo al menú anterior...");
        }
    }


    private static void registrarEmpleado() {
        System.out.print("Ingrese el nombre del empleado: ");
        String empleado = scanner.nextLine();
        empleados.add(empleado);
        System.out.println("Empleado registrado exitosamente.");
    }

    private static void actualizarEmpleado() {
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

    private static void registrarDepartamento() {
        System.out.print("Ingrese el nombre del departamento: ");
        String departamento = scanner.nextLine();
        departamentos.add(departamento);
        System.out.println("Departamento registrado exitosamente.");
    }

    private static void actualizarDepartamento() {
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

    private static void registrarMetrica() {
        System.out.print("Ingrese el nombre de la métrica: ");
        String metrica = scanner.nextLine();
        metricas.add(metrica);
        System.out.println("Métrica registrada exitosamente.");
    }

    private static void actualizarMetrica() {
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

    private static void generarInformeIndividual() {
        System.out.println("\n--- Generando Informe Individual ---");
        System.out.print("Ingrese el nombre del empleado: ");
        String empleado = scanner.nextLine();
        if (empleados.contains(empleado)) {
            System.out.println("Informe individual generado para el empleado: " + empleado);
        } else {
            System.out.println("Empleado no encontrado.");
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
                case 1:
                    consultarInformeRendimiento();
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

    private static void consultarInformeRendimiento() {
        System.out.println("\n--- Consultando Informe de Rendimiento ---");
        System.out.print("Ingrese su nombre: ");
        String empleado = scanner.nextLine();
        if (empleados.contains(empleado)) {
            System.out.println("Informe de rendimiento para " + empleado + ": [Detalles del informe aquí]");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }
    // prueba commit

}