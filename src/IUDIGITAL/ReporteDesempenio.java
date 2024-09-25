package IUDIGITAL;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ReporteDesempenio {
    private int idReporte;
    private Empleado empleado;
    private Departamento departamento;
    private Date fechaReporte;
    private int metricas;

    public ReporteDesempenio(int idReporte, Empleado empleado, Departamento departamento, Date fechaReporte, int metricas) {
        this.idReporte = idReporte;
        this.empleado = empleado;
        this.departamento = departamento;
        this.fechaReporte = fechaReporte;
        this.metricas = metricas;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public int getMetricas() {
        return metricas;
    }

    public void setMetricas(int metricas) {
        this.metricas = metricas;
    }


    public double calcularPromedioMetrica(List<ReporteDesempenio> reportes) {
            double suma = 0;
            for (ReporteDesempenio reporte : reportes) {
                suma += reporte.getMetricas();
            }
            return suma / reportes.size();
    }

    public int contarEmpleados(List<ReporteDesempenio> reportes) {
            int count = 0;

            List<Empleado> empleadosContados = new ArrayList<>();

            for (ReporteDesempenio reporte : reportes) {
                Empleado empleadoActual = reporte.getEmpleado();
                if (!empleadosContados.contains(empleadoActual)) {
                    empleadosContados.add(empleadoActual);
                    count++;
                }
            }
            return count;
    }

    public void imprimirReporteDepartamento(List<ReporteDesempenio> reportes) {
            if (reportes.isEmpty()) {
                System.out.println("No hay reportes disponibles.");
                return;
            }

            String nombreDepartamento = reportes.get(0).getDepartamento().getNombre();
            System.out.println("Reporte Departamento: " + nombreDepartamento);
            System.out.println("Número de empleados: " + contarEmpleados(reportes));
            System.out.println("Promedio de la métrica: " + calcularPromedioMetrica(reportes));

    }
    public void imprimirReporteEmpleado(List<ReporteDesempenio> reportes, Empleado empleadoSolicitado) {
            for (ReporteDesempenio reporte : reportes) {
                if (reporte.getEmpleado().equals(empleadoSolicitado)) {
                    System.out.println("Reporte de Desempeño");
                    System.out.println("Empleado: " + reporte.getEmpleado().getNombre());
                    System.out.println("Departamento: " + reporte.getDepartamento().getNombre());
                    System.out.println("Fecha del Reporte: " + reporte.getFechaReporte());
                    System.out.println("Metricas: " + reporte.getMetricas());
                    return;
                }
            }
            System.out.println("No se encontró ningún reporte para el empleado: " + empleadoSolicitado.getNombre());
    }

}