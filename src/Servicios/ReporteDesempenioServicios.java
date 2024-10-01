package Servicios;

import Modelo.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ReporteDesempenioServicios {
    private static List<ReporteDesempenio> reportes;
    private static DepartamentoServicios departamentoServicios;
    private static EmpleadoServicios empleadoServicios;
    public ReporteDesempenioServicios(DepartamentoServicios departamentoServicios, EmpleadoServicios empleadoServicios) {
        reportes = new ArrayList<>();
        ReporteDesempenioServicios.departamentoServicios = departamentoServicios;
        ReporteDesempenioServicios.empleadoServicios = empleadoServicios;
    }

    public static DepartamentoServicios getDepartamentoServicios() {
        return departamentoServicios;
    }

    public static EmpleadoServicios getEmpleadoServicios() {
        return empleadoServicios;
    }

    public ReporteDesempenio crearReporteIndividual(Empleado empleado, LocalDate fechaInicio, LocalDate fechaFin, Map<String, Double> metricas, String comentarios) {
        ReporteDesempenio reporte = new ReporteDesempenio(empleado, fechaInicio, fechaFin);
        reporte.setMetricas(metricas);
        reporte.setComentarios(comentarios);
        reporte.calcularPuntuacionTotal();
        reportes.add(reporte);
        return reporte;
    }


    public ReporteDesempenio crearReporteDepartamental(Departamento departamento, LocalDate fechaInicio, LocalDate fechaFin, Map<String, Double> metricas, String comentarios) {
        ReporteDesempenio reporte = new ReporteDesempenio(departamento, fechaInicio, fechaFin);
        reporte.setMetricas(metricas);
        reporte.setComentarios(comentarios);
        reporte.calcularPuntuacionTotal();
        reportes.add(reporte);
        return reporte;
    }

    public void actualizarReporte(ReporteDesempenio reporte, Map<String, Double> nuevasMetricas, String nuevosComentarios) {
        reporte.setMetricas(nuevasMetricas);
        reporte.setComentarios(nuevosComentarios);
        reporte.calcularPuntuacionTotal();
    }

    public void eliminarReporte(ReporteDesempenio reporte) {
        reportes.remove(reporte);
    }

    // Imprimir reporte individual
    public String imprimirReporteIndividual(ReporteDesempenio reporte) {
        if (reporte.getEmpleado() == null) {
            return "Error: Este no es un reporte individual.";
        }
        return reporte.toString();
    }

    // Imprimir reporte departamental
    public String imprimirReporteDepartamental(ReporteDesempenio reporte) {
        if (reporte.getDepartamento() == null) {
            return "Error: Este no es un reporte departamental.";
        }
        return reporte.toString();
    }

    // Obtener todos los reportes de un empleado
    public  List<ReporteDesempenio> obtenerReportesEmpleado(Empleado empleado) {
        return reportes.stream()
                .filter(r -> r.getEmpleado() != null && r.getEmpleado().equals(empleado))
                .collect(Collectors.toList());
    }

    // Obtener todos los reportes de un departamento
    public List<ReporteDesempenio> obtenerTodosLosReportes() {
        return new ArrayList<>(reportes);
    }

    public List<Departamento> obtenerTodosLosDepartamentos() {
        return departamentoServicios.getDepartamentos();
    }
    public List<ReporteDesempenio> obtenerReportesDepartamento(Departamento departamento) {
        return reportes.stream()
                .filter(reporte -> reporte.getDepartamento() != null
                        && reporte.getDepartamento().equals(departamento))
                .collect(Collectors.toList());
    }

    public void inicializarDatos() {
        // Crear reportes de desempeño
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Reporte individual para Juan Pérez
        Map<String, Double> metricasJuan = new HashMap<>();
        metricasJuan.put("Productividad", 85.5);
        metricasJuan.put("Puntualidad", 95.0);
        metricasJuan.put("Trabajo en equipo", 90.0);

        ReporteDesempenio reporteJuan = new ReporteDesempenio(
                empleadoServicios.getEmpleados().get(0),
                LocalDate.parse("01/01/2024", formatter),
                LocalDate.parse("30/06/2024", formatter)
        );
        reporteJuan.setMetricas(metricasJuan);
        reporteJuan.setComentarios("Juan ha demostrado un excelente desempeño en el primer semestre del año.");
        reporteJuan.calcularPuntuacionTotal();
        reportes.add(reporteJuan);

        // Reporte departamental para RRHH
        Map<String, Double> metricasRRHH = new HashMap<>();
        metricasRRHH.put("Eficiencia en contrataciones", 88.0);
        metricasRRHH.put("Satisfacción del empleado", 92.5);
        metricasRRHH.put("Tiempo de respuesta a solicitudes", 87.0);

        ReporteDesempenio reporteRRHH = new ReporteDesempenio(
                departamentoServicios.getDepartamentos().get(0),
                LocalDate.parse("01/01/2024", formatter),
                LocalDate.parse("30/06/2024", formatter)
        );
        reporteRRHH.setMetricas(metricasRRHH);
        reporteRRHH.setComentarios("El departamento de RRHH ha mostrado una mejora significativa en la eficiencia de sus procesos.");
        reporteRRHH.calcularPuntuacionTotal();
        reportes.add(reporteRRHH);
    }
}
