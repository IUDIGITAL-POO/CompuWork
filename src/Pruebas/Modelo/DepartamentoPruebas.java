package Pruebas.Modelo;

import Modelo.Departamento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoPruebas {

    @Test
    public void testConstructor() {
        Departamento departamento = new Departamento(1, "Finanzas");
        assertEquals(1, departamento.getId());
        assertEquals("Finanzas", departamento.getNombre());
    }

    @Test
    public void testSettersAndGetters() {
        Departamento departamento = new Departamento(1, "Finanzas");
        departamento.setId(2);
        departamento.setNombre("Recursos Humanos");
        assertEquals(2, departamento.getId());
        assertEquals("Recursos Humanos", departamento.getNombre());
    }

    @Test
    public void testToString() {
        Departamento departamento = new Departamento(1, "Finanzas");
        String expected = "Departamento{id=1, nombre='Finanzas'}";
        assertEquals(expected, departamento.toString());
    }
}
