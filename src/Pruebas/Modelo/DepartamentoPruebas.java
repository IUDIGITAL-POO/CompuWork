package Pruebas.Modelo;

import Modelo.Departamento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoPruebas {

    @Test
    public void testConstructor() {
        Departamento departamento = new Departamento(1, "Finanzas", "Gestiona las finanzas", "Edificio A", "Juan Perez");
        assertEquals(1, departamento.getId());
        assertEquals("Finanzas", departamento.getNombre());
        assertEquals("Gestiona las finanzas", departamento.getDescripcion());
        assertEquals("Edificio A", departamento.getUbicacion());
        assertEquals("Juan Perez", departamento.getJefe());
    }

    @Test
    public void testSettersAndGetters() {
        Departamento departamento = new Departamento(1, "Finanzas", "Gestiona las finanzas", "Edificio A", "Juan Perez");
        departamento.setId(2);
        departamento.setNombre("Recursos Humanos");
        departamento.setDescripcion("Gestiona el personal");
        departamento.setUbicacion("Edificio B");
        departamento.setJefe("Maria Lopez");
        assertEquals(2, departamento.getId());
        assertEquals("Recursos Humanos", departamento.getNombre());
        assertEquals("Gestiona el personal", departamento.getDescripcion());
        assertEquals("Edificio B", departamento.getUbicacion());
        assertEquals("Maria Lopez", departamento.getJefe());
    }

    @Test
    public void testToString() {
        Departamento departamento = new Departamento(1, "Finanzas", "Gestiona las finanzas", "Edificio A", "Juan Perez");
        String expected = "Departamento{id=1, nombre='Finanzas', descripcion='Gestiona las finanzas', ubicacion='Edificio A', jefe='Juan Perez'}";
        assertEquals(expected, departamento.toString());
    }
}