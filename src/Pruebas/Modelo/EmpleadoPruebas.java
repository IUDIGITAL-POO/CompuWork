package Pruebas.Modelo;

import Modelo.Departamento;
import Modelo.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EmpleadoPruebas {

    private Empleado empleado;
    private Departamento departamento;

    @BeforeEach
    public void setUp() {
        departamento = new Departamento(1, "Ventas", "Comercial", "Planta Baja", "123-456");
        empleado = new Empleado("John Doe", 1, "Vendedor", 50000);
    }

    @Test
    public void testGetNombre() {
        assertEquals("John Doe", empleado.getNombre());
    }

    @Test
    public void testGetId() {
        assertEquals(1, empleado.getId());
    }

    @Test
    public void testGetTipoEmpleado() {
        assertEquals("Vendedor", empleado.getTipoEmpleado());
    }

    @Test
    public void testGetSalario() {
        assertEquals(50000, empleado.getSalario());
    }

    @Test
    public void testAsignarDepartamento() {
        empleado.asignarDepartamento(departamento);
        assertEquals(departamento, empleado.getDepartamento());
    }

    @Test
    public void testCambiarDepartamento() {
        Departamento nuevoDepartamento = new Departamento(2, "Marketing", "Comercial", "Planta Alta", "789-012");
        empleado.asignarDepartamento(departamento);
        empleado.cambiarDepartamento(nuevoDepartamento);
        assertEquals(nuevoDepartamento, empleado.getDepartamento());
    }

    @Test
    public void testEliminarEmpleado() {
        empleado.asignarDepartamento(departamento);
        empleado.eliminarEmpleado();
        assertNull(empleado.getDepartamento());
    }
}
