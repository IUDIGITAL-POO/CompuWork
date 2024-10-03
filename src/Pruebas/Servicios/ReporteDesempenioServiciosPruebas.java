package Pruebas.Servicios;

import Modelo.Departamento;
import Modelo.Empleado;
import Modelo.ReporteDesempenio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteDesempenioServiciosPruebas {

    private Empleado empleado1;
    private Empleado empleado2;
    private Departamento departamento;
    private List<ReporteDesempenio> reportes;

    @BeforeEach
    public void setUp() {
        empleado1 = new Empleado("John Doe", 1, "Vendedor", 50000);
        empleado2 = new Empleado("Jane Smith", 2, "Vendedor", 52000);
        departamento = new Departamento(1, "Ventas", "Comercial", "Planta Baja", "123-456");

        reportes = new ArrayList<>();

        ReporteDesempenio reporte1 = new ReporteDesempenio(departamento, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 31));
        Map<String, Double> metricas1 = new HashMap<>();
        metricas1.put("Ventas", 80.0);
        reporte1.setMetricas(metricas1);
        reporte1.calcularPuntuacionTotal();

        ReporteDesempenio reporte2 = new ReporteDesempenio(departamento, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 6, 30));
        Map<String, Double> metricas2 = new HashMap<>();
        metricas2.put("Ventas", 90.0);
        reporte2.setMetricas(metricas2);
        reporte2.calcularPuntuacionTotal();

        reportes.add(reporte1);
        reportes.add(reporte2);
    }
}