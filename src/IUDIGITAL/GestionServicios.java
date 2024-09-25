package IUDIGITAL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GestionServicios {
    private static List<Usuario> usuarios;
    private static List<Empleado> empleados;
    private static List<Departamento> departamentos;
    private static List<ReporteDesempenio> reportes;
    private static final Scanner scanner = new Scanner(System.in);

    public GestionServicios() {
        usuarios = new ArrayList<>();
        empleados = new ArrayList<>();
        departamentos = new ArrayList<>();
        reportes = new ArrayList<>();
    }

    //  *  Metodos para gestionar los usuarios
    public static void registrarUsuario() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.println("Seleccione un rol:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");
        int rolOpcion;
        try {
            rolOpcion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Opción de rol inválida.");
        }

        if (rolOpcion != 1 && rolOpcion != 2) {
            System.out.println("Opción de rol inválida, registro fallido.");
            return;
        }

        String rol = (rolOpcion == 1) ? "Administrador" : "Empleado";

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, rol);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");
    }

    public static Usuario iniciarSesion() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = buscarUsuario(nombreUsuario, contrasena);

        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getNombreUsuario() + " (Rol: " + usuario.getRol() + ")");
            return usuario;
        } else {
            throw new SecurityException("Nombre de usuario o contraseña incorrectos.");
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
    // * Fin de metodos para gestionar usuarios

    //    Metodos para gestionar empleados
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
    //    Fin de metodos para gestionar empleados

    // Métodos para gestionar departamentos

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

    // Métodos para gestionar reportes

    public static void registrarReporte() {
        System.out.print("Ingrese el ID del empleado para el reporte: ");
        int idEmpleado;
        try {
            idEmpleado = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de empleado inválido.");
        }
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


    public static void actualizarReporte() {
        System.out.print("Ingrese el ID del reporte a actualizar: ");
        // Aquí debería implementar la lógica para buscar y actualizar un reporte.
        System.out.println("Funcionalidad de actualización de reportes no implementada.");
    }
    //    Fin de metodos para gestionar reportes

    //    Metodos para visualizar reportes
    public static void generarInformeDepartamental() {
        System.out.print("Ingrese el nombre del departamento para generar el informe: ");
        String nombreDepartamento = scanner.nextLine();
        for (Departamento dep : departamentos) {
            if (dep.getNombre().equalsIgnoreCase(nombreDepartamento)) {
                System.out.println("Generando informe para el departamento: " + dep.getNombre());
                new ReporteDesempenio(0, null, null, null, 0).imprimirReporteDepartamento(reportes);
                return;
            }
        }
        System.out.println("Departamento no encontrado.");
    }

    public static void generarInformeIndividual() {
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
            System.out.println("Generando informe para el empleado: " + empleado.getNombre());
            new ReporteDesempenio(0, empleado, null, null, 0).imprimirReporteEmpleado(reportes, empleado);
            return;
        }
        System.out.println("Empleado no encontrado.");
    }

    public static void consultarInformeRendimiento() {
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
            System.out.println("Consultando informe de rendimiento para el empleado: " + empleado.getNombre());
            new ReporteDesempenio(0, empleado, null, null, 0).imprimirReporteEmpleado(reportes, empleado);
            return;
        }
        System.out.println("Empleado no encontrado.");
    }
    //    Fin de metodos para visualizar reportes

    //    Datos iniciales para probar el programa
    public static void inicializarDatos() {
        usuarios.add(new Usuario("admin", "admin123", "Administrador"));
        usuarios.add(new Usuario("empleado1", "emp123", "Empleado"));

        Departamento rrhh = new Departamento(1, "Recursos Humanos", "Administrativo", "Piso 2", "123-456-7890");
        departamentos.add(rrhh);

        Empleado emp1 = new Empleado("Juan Pérez", 1, "Permanente", 50000.0);
        emp1.asignarDepartamento(rrhh);
        empleados.add(emp1);

        ReporteDesempenio reporte1 = new ReporteDesempenio(1, emp1, rrhh, new Date(), 90);
        reportes.add(reporte1);

        Empleado emp2 = new Empleado("María López", 2, "Temporal", 30000.0);
        emp2.asignarDepartamento(rrhh);
        empleados.add(emp2);

        ReporteDesempenio reporte2 = new ReporteDesempenio(2, emp2, rrhh, new Date(), 75);
        reportes.add(reporte2);
    }
}
