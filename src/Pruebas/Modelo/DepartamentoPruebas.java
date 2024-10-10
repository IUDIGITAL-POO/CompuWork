package Pruebas.Modelo;

import Modelo.Departamento;
import Modelo.Empleado; // Asegúrate de que la clase Empleado esté en el paquete correcto
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoPruebas {

    private Departamento departamento;
    private Empleado empleado1;
    private Empleado empleado2;

    @BeforeEach
    public void setUp() {
        // Inicializa el departamento y algunos empleados para las pruebas
        departamento = new Departamento(1, "Finanzas", "Administrativo", "Planta Baja", "123-456");
        empleado1 = new Empleado("John Doe", 1, "Vendedor", 50000); // Ajusta los parámetros según tu clase Empleado
        empleado2 = new Empleado("Jane Smith", 2, "Analista", 60000); // Ajusta los parámetros según tu clase Empleado
    }

    @Test
    public void testConstructor() {
        assertEquals(1, departamento.getCodigo());
        assertEquals("Finanzas", departamento.getNombre());
        assertEquals("Administrativo", departamento.getTipo());
        assertEquals("Planta Baja", departamento.getUbicacion());
        assertEquals("123-456", departamento.getTelefono());
    }

    @Test
    public void testSettersAndGetters() {
        departamento.setNombre("Recursos Humanos");
        departamento.setTipo("Administrativo");
        departamento.setUbicacion("Planta Alta");
        departamento.setTelefono("789-012");

        assertEquals("Recursos Humanos", departamento.getNombre());
        assertEquals("Administrativo", departamento.getTipo());
        assertEquals("Planta Alta", departamento.getUbicacion());
        assertEquals("789-012", departamento.getTelefono());
    }

    @Test
    public void testAgregarEmpleado() {
        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);

        List<Empleado> empleados = departamento.getEmpleados();
        assertEquals(2, empleados.size());
        assertTrue(empleados.contains(empleado1));
        assertTrue(empleados.contains(empleado2));
    }

    @Test
    public void testEliminarEmpleado() {
        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);

        departamento.eliminarEmpleado(empleado1);

        List<Empleado> empleados = departamento.getEmpleados();
        assertEquals(1, empleados.size());
        assertFalse(empleados.contains(empleado1));
        assertTrue(empleados.contains(empleado2));
    }

    @Test
    public void testVisualizarEmpleados() {
        // Para probar visualizarEmpleados, puedes redirigir la salida estándar o simplemente probar que se ejecute sin excepciones
        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);

        // Aquí puedes verificar que el método no lanza excepciones, pero verificar el contenido impreso puede requerir redirigir la salida estándar.
        assertDoesNotThrow(() -> departamento.visualizarEmpleados());
    }

    @Test
    public void testToString() {
        String expected = "Finanzas"; // Esto depende de cómo hayas implementado el toString()
        assertEquals(expected, departamento.toString());
    }
}
