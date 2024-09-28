package Servicios;

import Modelo.Departamento;
import Modelo.Empleado;
import Modelo.ReporteDesempenio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReporteDesempenioServicios {
    private static List<ReporteDesempenio> reportes;
    private static List<Empleado> empleados;
    private static List<Departamento> departamentos;
    private static final Scanner scanner = new Scanner(System.in);

    public ReporteDesempenioServicios(List<Empleado> empleados, List<Departamento> departamentos) {
        this.reportes = new ArrayList<>();
        this.empleados = empleados;
        this.departamentos = departamentos;
    }


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

}
