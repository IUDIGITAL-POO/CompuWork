package Modelo;

import Modelo.Departamento;
import Modelo.Empleado;

import java.time.LocalDate;
import java.util.Map;

public class ReporteDesempenio {
    private Empleado empleado;
    private Departamento departamento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Map<String, Double> metricas;
    private double puntuacionTotal;
    private String comentarios;

    public ReporteDesempenio(Empleado empleado, LocalDate fechaInicio, LocalDate fechaFin) {
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado cannot be null for individual report");
        }
        this.empleado = empleado;
        this.departamento = null;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ReporteDesempenio(Departamento departamento, LocalDate fechaInicio, LocalDate fechaFin) {
        if (departamento == null) {
            throw new IllegalArgumentException("Departamento cannot be null for departmental report");
        }
        this.departamento = departamento;
        this.empleado = null;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Map<String, Double> getMetricas() {
        return metricas;
    }

    public void setMetricas(Map<String, Double> metricas) {
        this.metricas = metricas;
    }

    public double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(double puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void agregarMetrica(String nombreMetrica, Double valor) {
        this.metricas.put(nombreMetrica, valor);
    }

    public void calcularPuntuacionTotal() {
        this.puntuacionTotal = this.metricas.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    @Override
    public String toString() {
        String tipoReporte = (empleado != null) ? "Individual" : "Departamental";
        String entidad = (empleado != null) ? empleado.getNombre() : (departamento != null ? departamento.getNombre() : "Desconocido");
        return "Reporte de Desempeño " + tipoReporte + ":\n" +
                "Entidad: " + entidad + "\n" +
                "Período: " + fechaInicio + " - " + fechaFin + "\n" +
                "Métricas: " + metricas + "\n" +
                "Puntuación Total: " + puntuacionTotal + "\n" +
                "Comentarios: " + comentarios;
    }
}