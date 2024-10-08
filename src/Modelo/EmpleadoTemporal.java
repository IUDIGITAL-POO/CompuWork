package Modelo;

public class EmpleadoTemporal extends Empleado{
    private String fechaInicio;
    private String fechaFin;

    public EmpleadoTemporal(String nombre, int id, String tipoEmpleado, double salario, String fechaInicio, String fechaFin) {
        super(nombre, id, tipoEmpleado, salario);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }


}
