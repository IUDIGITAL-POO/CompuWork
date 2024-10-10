package Pruebas.Servicios;

import Modelo.Departamento; // Asegúrate de que la ruta del paquete sea correcta
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoServiciosPruebas {

    @Test
    public void testConstructor() {
        // Usa todos los parámetros requeridos por el constructor
        Departamento departamento = new Departamento(1, "Finanzas", "Administrativo", "Planta Baja", "123-456");
        assertEquals(1, departamento.getCodigo());
        assertEquals("Finanzas", departamento.getNombre());
        assertEquals("Administrativo", departamento.getTipo());
        assertEquals("Planta Baja", departamento.getUbicacion());
        assertEquals("123-456", departamento.getTelefono());
    }

    @Test
    public void testSettersAndGetters() {
        // Usa todos los parámetros requeridos por el constructor
        Departamento departamento = new Departamento(1, "Finanzas", "Administrativo", "Planta Baja", "123-456");
        departamento.setCodigo(2);
        departamento.setNombre("Recursos Humanos");
        departamento.setTipo("Administrativo");
        departamento.setUbicacion("Planta Alta");
        departamento.setTelefono("789-012");

        assertEquals(2, departamento.getCodigo());
        assertEquals("Recursos Humanos", departamento.getNombre());
        assertEquals("Administrativo", departamento.getTipo());
        assertEquals("Planta Alta", departamento.getUbicacion());
        assertEquals("789-012", departamento.getTelefono());
    }

    @Test
    public void testToString() {
        // Usa todos los parámetros requeridos por el constructor
        Departamento departamento = new Departamento(1, "Finanzas", "Administrativo", "Planta Baja", "123-456");
        String expected = "Finanzas"; // Cambia según la implementación de toString()
        assertEquals(expected, departamento.toString());
    }
}
