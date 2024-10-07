package Pruebas.Modelo;

import Modelo.Departamento;
import Modelo.Empleado;
import Modelo.ReporteDesempenio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteDesempenioPruebas {

    private Empleado empleado1;
    private Empleado empleado2;
    private Departamento departamento;
    private List<ReporteDesempenio> reportes;

    @BeforeEach
    public void setUp() {
        empleado1 = new Empleado("John Doe", 1, "Gerente", 50000);
        empleado2 = new Empleado("Jane Smith", 2, "Asistente", 40000);

        departamento = new Departamento(1, "Ventas", "Comercial", "Ciudad", "123456789");

        reportes = new ArrayList<>();

        ReporteDesempenio reporte1 = new ReporteDesempenio(empleado1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 31));
        ReporteDesempenio reporte2 = new ReporteDesempenio(empleado2, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 31));

        Map<String, Double> metricas1 = new HashMap<>();
        metricas1.put("Ventas", 80.0);
        reporte1.setMetricas(metricas1);
        reporte1.calcularPuntuacionTotal();

        Map<String, Double> metricas2 = new HashMap<>();
        metricas2.put("Ventas", 90.0);
        reporte2.setMetricas(metricas2);
        reporte2.calcularPuntuacionTotal();

        reportes.add(reporte1);
        reportes.add(reporte2);
    }

    public void testCalcularPromedioMetrica() {
        ReporteDesempenio reporte = new ReporteDesempenio(empleado1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 31));

        double suma = 0;
        for (ReporteDesempenio r : reportes) {
            suma += r.getPuntuacionTotal();
        }
        double promedio = suma / reportes.size();

        assertEquals(85.0, promedio, 0.01);
    }

    @Test
    public void testContarEmpleados() {
        int numEmpleados = (int) reportes.stream()
                .map(ReporteDesempenio::getEmpleado)
                .distinct()
                .count();

        assertEquals(2, numEmpleados);
    }
}