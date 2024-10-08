package Pruebas.Servicios;

import Modelo.Departamento;
import Servicios.DepartamentoServicios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoServiciosPruebas {

    private DepartamentoServicios departamentoServicios;

    @BeforeEach
    public void setUp() {
        departamentoServicios = new DepartamentoServicios();
    }

    @Test
    public void testAgregarDepartamento() {
        Departamento departamento = new Departamento(1, "Finanzas");
        departamentoServicios.agregarDepartamento(departamento);
        List<Departamento> departamentos = departamentoServicios.obtenerDepartamentos();
        assertTrue(departamentos.contains(departamento));
    }

    @Test
    public void testObtenerDepartamentos() {
        Departamento departamento1 = new Departamento(1, "Finanzas");
        Departamento departamento2 = new Departamento(2, "Recursos Humanos");
        departamentoServicios.agregarDepartamento(departamento1);
        departamentoServicios.agregarDepartamento(departamento2);
        List<Departamento> departamentos = departamentoServicios.obtenerDepartamentos();
        assertEquals(2, departamentos.size());
    }

    @Test
    public void testBuscarDepartamentoPorId() {
        Departamento departamento = new Departamento(1, "Finanzas");
        departamentoServicios.agregarDepartamento(departamento);
        Departamento encontrado = departamentoServicios.buscarDepartamentoPorId(1);
        assertNotNull(encontrado);
        assertEquals("Finanzas", encontrado.getNombre());
    }
}
