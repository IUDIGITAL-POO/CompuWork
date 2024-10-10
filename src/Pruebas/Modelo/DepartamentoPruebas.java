package Pruebas.Modelo;
import Modelo.Departamento;
import Modelo.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DepartamentoPruebas {

    private Departamento departamento;
    private Empleado empleado1;
    private Empleado empleado2;

    @BeforeEach
    public void setUp() {
        // Crear un departamento de prueba
        departamento = new Departamento(1, "Recursos Humanos", "Administrativo", "Oficina Central", "123-456");

        // Crear empleados de prueba con salario en double
        empleado1 = new Empleado("Juan Perez", 101, "Gerente", 3000.0);
        empleado2 = new Empleado("Ana Lopez", 102, "Asistente", 2000.0);
    }

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

    @Test
    public void testAgregarEmpleado() {
        // Asignar el departamento a empleado1 antes de agregarlo
        empleado1.asignarDepartamento(departamento);
        departamento.agregarEmpleado(empleado1);

        List<Empleado> empleados = departamento.getEmpleados();
        assertEquals(1, empleados.size());
        assertEquals("Juan Perez", empleados.get(0).getNombre());
        assertEquals(departamento, empleados.get(0).getDepartamento());  // Verificar que el departamento fue asignado
    }

    @Test
    public void testVisualizarEmpleados() {
        empleado1.asignarDepartamento(departamento);
        empleado2.asignarDepartamento(departamento);

        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);

        // Este test puede ser visual, deberás verificar en la consola la salida.
        departamento.visualizarEmpleados();
    }

    @Test
    public void testGettersAndSetters() {
        // Verificar los valores iniciales
        assertEquals(1, departamento.getCodigo());
        assertEquals("Recursos Humanos", departamento.getNombre());
        assertEquals("Administrativo", departamento.getTipo());
        assertEquals("Oficina Central", departamento.getUbicacion());
        assertEquals("123-456", departamento.getTelefono());

        // Cambiar los valores
        departamento.setNombre("Finanzas");
        departamento.setTipo("Operativo");
        departamento.setUbicacion("Sede Regional");
        departamento.setTelefono("654-321");

        // Verificar los nuevos valores
        assertEquals("Finanzas", departamento.getNombre());
        assertEquals("Operativo", departamento.getTipo());
        assertEquals("Sede Regional", departamento.getUbicacion());
        assertEquals("654-321", departamento.getTelefono());
    }
}