package Pruebas.Servicios;

import Modelo.Departamento;
import Servicios.DepartamentoServicios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DepartamentoServiciosPruebas {

    private DepartamentoServicios departamentoServicios;
    private Departamento departamento1;
    private Departamento departamento2;

    @BeforeEach
    public void setUp() {
        // Inicializar el servicio y los departamentos de prueba
        departamentoServicios = new DepartamentoServicios();
        departamento1 = new Departamento(1, "Recursos Humanos", "Administrativo", "Piso 2", "123-456-7890");
        departamento2 = new Departamento(2, "Finanzas", "Operativo", "Piso 3", "098-765-4321");
    }

    @Test
    public void testAgregarDepartamento() {
        Departamento departamento = new Departamento(1, "Finanzas", "Descripción Finanzas", "Ubicación Finanzas", "Jefe Finanzas");
        departamentoServicios.registrarDepartamento(departamento);
        List<Departamento> departamentos = departamentoServicios.getDepartamentos();
        assertTrue(departamentos.contains(departamento));
    }

    @Test
    public void testObtenerDepartamentos() {
        Departamento departamento1 = new Departamento(1, "Finanzas", "Descripción Finanzas", "Ubicación Finanzas", "Jefe Finanzas");
        Departamento departamento2 = new Departamento(2, "Recursos Humanos", "Descripción RRHH", "Ubicación RRHH", "Jefe RRHH");
        departamentoServicios.registrarDepartamento(departamento1);
        departamentoServicios.registrarDepartamento(departamento2);
        List<Departamento> departamentos = departamentoServicios.getDepartamentos();
        assertEquals(2, departamentos.size());
    }

    @Test
    public void testBuscarDepartamentoPorId() {
        Departamento departamento = new Departamento(1, "Finanzas", "Descripción Finanzas", "Ubicación Finanzas", "Jefe Finanzas");
        departamentoServicios.registrarDepartamento(departamento);
        Departamento encontrado = departamentoServicios.buscarDepartamentoPorCodigo(1);
        assertNotNull(encontrado);
        assertEquals("Recursos Humanos", encontrado.getNombre());

        Departamento noEncontrado = departamentoServicios.buscarDepartamentoPorCodigo(999);
        assertNull(noEncontrado);  // Verificar que no se encuentra un departamento con un código inexistente
    }

    @Test
    public void testRegistrarDepartamento() {
        departamentoServicios.registrarDepartamento(departamento1);
        departamentoServicios.registrarDepartamento(departamento2);

        List<Departamento> departamentos = departamentoServicios.getDepartamentos();
        assertEquals(2, departamentos.size());
        assertEquals("Recursos Humanos", departamentos.get(0).getNombre());
        assertEquals("Finanzas", departamentos.get(1).getNombre());
    }

    @Test
    public void testActualizarDepartamento() {
        departamentoServicios.registrarDepartamento(departamento1);
        Departamento departamentoActualizado = new Departamento(1, "Recursos Humanos", "Administrativo", "Piso 4", "111-222-3333");
        departamentoServicios.actualizarDepartamento(departamentoActualizado);

        List<Departamento> departamentos = departamentoServicios.getDepartamentos();
        assertEquals(1, departamentos.size());
        assertEquals("Piso 4", departamentos.get(0).getUbicacion());
        assertEquals("111-222-3333", departamentos.get(0).getTelefono());
    }

    @Test
    public void testEliminarDepartamento() {
        departamentoServicios.registrarDepartamento(departamento1);
        departamentoServicios.registrarDepartamento(departamento2);

        departamentoServicios.eliminarDepartamento(departamento1.getCodigo());

        List<Departamento> departamentos = departamentoServicios.getDepartamentos();
        assertEquals(1, departamentos.size());
        assertEquals("Finanzas", departamentos.get(0).getNombre());
    }

    @Test
    public void testBuscarDepartamentoPorCodigo() {
        departamentoServicios.registrarDepartamento(departamento1);
        departamentoServicios.registrarDepartamento(departamento2);

        Departamento encontrado = departamentoServicios.buscarDepartamentoPorCodigo(1);
        assertNotNull(encontrado);
        assertEquals("Recursos Humanos", encontrado.getNombre());

        Departamento noEncontrado = departamentoServicios.buscarDepartamentoPorCodigo(999);
        assertNull(noEncontrado);  // Verificar que no se encuentra un departamento con un código inexistente
    }

    @Test
    public void testInicializarDatos() {
        departamentoServicios.inicializarDatos();
        List<Departamento> departamentos = departamentoServicios.getDepartamentos();
        assertEquals(1, departamentos.size());
        assertEquals("Recursos Humanos", departamentos.get(0).getNombre());
        assertEquals("Piso 2", departamentos.get(0).getUbicacion());
    }
}