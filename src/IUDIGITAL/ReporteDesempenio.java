package IUDIGITAL;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class ReporteDesempenio {

    private int idReporte;
    private Empleado empleado;
    private Departamento departamento;
    private Date fechaReporte;
    private String metricas;

    public ReporteDesempenio(int idReporte, Empleado empleado, Departamento departamento, Date fechaReporte, String metricas) {
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
    public String getMetricas() {
        return metricas;
    }
    public void setMetricas(String metricas) {
        this.metricas = metricas;
    }
}