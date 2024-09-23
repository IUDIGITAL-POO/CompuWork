package IUDIGITAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {

    private static List<Usuario> usuarios = new ArrayList<Usuario>();
    private static List<Empleado> empleados = new ArrayList<Empleado>();
    private static List<Departamento> departamentos = new ArrayList<Departamento>();
    private static List<ReporteDesempenio> reportes = new ArrayList<ReporteDesempenio>();
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

        if (rolOpcion != 1 && rolOpcion != 2) {
            System.out.println("Opción de rol inválida, registro fallido.");
            return;
        }

        String rol = (rolOpcion == 1) ? "Administrador" : "Empleado";

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, rol);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");
    }

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
            case 1 -> registrarEmpleado();
            case 2 -> actualizarEmpleado();
            case 3 -> System.out.println("Funcionalidad de eliminación no implementada.");
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
            case 1 -> registrarReporte();
            case 2 -> actualizarReporte();
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
            case 1 -> generarInformeDepartamental();
            case 2 -> generarInformeIndividual();
            case 3 -> System.out.println("Volviendo al menú anterior...");
            default -> System.out.println("Opción inválida, intente nuevamente.");
        }
    }

    private static void registrarEmpleado() {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el ID del empleado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el tipo de empleado: ");
        String tipoEmpleado = scanner.nextLine();
        System.out.print("Ingrese el salario del empleado: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Seleccione un departamento para el empleado:");
        for (int i = 0; i < departamentos.size(); i++) {
            System.out.println((i + 1) + ". " + departamentos.get(i).getNombre());
        }
        int opcionDepartamento;
        do {
            System.out.print("Ingrese el número del departamento: ");
            opcionDepartamento = scanner.nextInt();
        } while (opcionDepartamento < 1 || opcionDepartamento > departamentos.size());
        Departamento departamentoSeleccionado = departamentos.get(opcionDepartamento - 1);
        Empleado nuevoEmpleado = new Empleado(nombre, id, tipoEmpleado, salario);
        nuevoEmpleado.asignarDepartamento(departamentoSeleccionado);
        empleados.add(nuevoEmpleado);
        System.out.println("Empleado registrado exitosamente en el departamento: " + departamentoSeleccionado.getNombre());
    }

    private static void actualizarEmpleado() {
        System.out.print("Ingrese el ID del empleado a actualizar: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine();
        Empleado empleadoAActualizar = null;
        for (Empleado emp : empleados) {
            if (emp.getId() == idEmpleado) {
                empleadoAActualizar = emp;
                break;
            }
        }
        if (empleadoAActualizar != null) {
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
                double nuevoSalario = Double.parseDouble(nuevoSalarioStr);
                empleadoAActualizar.setSalario(nuevoSalario);
            }
            System.out.println("Empleado actualizado exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void registrarDepartamento() {
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


    private static void actualizarDepartamento() {
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

    private static void registrarReporte() {
        System.out.print("Ingrese el ID del empleado para el reporte: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea pendiente
        Empleado empleadoReportado = null;

        for (Empleado emp : empleados) {
            if (emp.getId() == idEmpleado) {
                empleadoReportado = emp;
                break;
            }
        }

        if (empleadoReportado != null) {
            // Obtener el departamento del empleado
            Departamento departamentoEmpleado = empleadoReportado.getDepartamento();

            System.out.print("Ingrese la fecha del reporte (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine();
            Date fecha = null;

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                fecha = sdf.parse(fechaStr);
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido. Reporte no registrado.");
                return;
            }

            System.out.print("Ingrese la métrica (ej. rendimiento): ");
            String metricaStr = scanner.nextLine();

            int metrica = 0;
            boolean metricaValida = false;

            while (!metricaValida) {
                System.out.print("Ingrese el valor de la métrica (número entero): ");
                try {
                    metrica = scanner.nextInt();
                    metricaValida = true; // Si la conversión es exitosa, salimos del bucle
                } catch (Exception e) {
                    System.out.println("Valor inválido. Por favor, ingrese un número entero válido.");
                    scanner.nextLine(); // Limpiar el buffer
                }
            }

            // Asumir que el ID del reporte es el tamaño actual de la lista más uno
            int idReporte = reportes.size() + 1;

            ReporteDesempenio nuevoReporte = new ReporteDesempenio(idReporte, empleadoReportado, departamentoEmpleado, fecha, metrica);
            reportes.add(nuevoReporte);
            System.out.println("Reporte registrado exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }


    private static void actualizarReporte() {
        System.out.print("Ingrese el ID del reporte a actualizar: ");
        // Aquí debería implementar la lógica para buscar y actualizar un reporte.
        System.out.println("Funcionalidad de actualización de reportes no implementada.");
    }

    private static void generarInformeDepartamental() {
        System.out.print("Ingrese el nombre del departamento para generar el informe: ");
        String nombreDepartamento = scanner.nextLine();
        for (Departamento dep : departamentos) {
            if (dep.getNombre().equalsIgnoreCase(nombreDepartamento)) {
                // Generar informe para el departamento.
                System.out.println("Generando informe para el departamento: " + dep.getNombre());
                return;
            }
        }
        System.out.println("Departamento no encontrado.");
    }

    private static void generarInformeIndividual() {
        System.out.print("Ingrese el ID del empleado para generar el informe: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine();
        Empleado empleado = null;
        for (Empleado emp : empleados) {
            if (emp.getId() == idEmpleado) {
                empleado = emp;
                break;
            }
        }
        if (empleado != null) {
            // Generar informe para el empleado.
            System.out.println("Generando informe para el empleado: " + empleado.getNombre());
            return;
        }
        System.out.println("Empleado no encontrado.");
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
                case 1 -> consultarInformeRendimiento();
                case 2 -> {
                    salir = true;
                    System.out.println("Cerrando sesión de empleado...");
                }
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    private static void consultarInformeRendimiento() {
        System.out.print("Ingrese su ID para consultar su informe: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine();
        Empleado empleado = null;
        for (Empleado emp : empleados) {
            if (emp.getId() == idEmpleado) {
                empleado = emp;
                break;
            }
        }
        if (empleado != null) {
            // Lógica para mostrar el informe de rendimiento del empleado.
            System.out.println("Consultando informe de rendimiento para el empleado: " + empleado.getNombre());
            return;
        }
        System.out.println("Empleado no encontrado.");
    }
}
