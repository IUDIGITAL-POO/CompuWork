package Pruebas.Servicios;

import Modelo.Departamento;
import Modelo.Empleado;
import Modelo.EmpleadoPermanente;
import Modelo.EmpleadoTemporal;
import Servicios.EmpleadoServicios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmpleadoServiciosPruebas {

    private EmpleadoServicios empleadoServicios;
    private Departamento rrhh;

    @BeforeEach
    public void setUp() {
        rrhh = new Departamento(1, "Recursos Humanos", "Administración", "Planta Baja", "123-456");
        empleadoServicios = new EmpleadoServicios(List.of(rrhh));
        empleadoServicios.inicializarDatos(rrhh);
    }

    @Test
    public void testRegistrarEmpleado() {
        Empleado nuevoEmpleado = new EmpleadoPermanente("Carlos Gómez", 3, "Permanente", 45000.0);
        empleadoServicios.registrarEmpleado(nuevoEmpleado);
        List<Empleado> empleados = empleadoServicios.getEmpleados();
        assertTrue(empleados.contains(nuevoEmpleado));
    }

    @Test
    public void testActualizarEmpleado() {
        Empleado empleadoActualizado = new EmpleadoPermanente("Juan Pérez", 1, "Permanente", 55000.0);
        empleadoServicios.actualizarEmpleado(empleadoActualizado);
        List<Empleado> empleados = empleadoServicios.getEmpleados();
        assertEquals(55000.0, empleados.get(0).getSalario());
    }

    @Test
    public void testEliminarEmpleado() {
        empleadoServicios.eliminarEmpleado(1);
        List<Empleado> empleados = empleadoServicios.getEmpleados();
        assertTrue(empleados.stream().noneMatch(emp -> emp.getId() == 1));
    }

    @Test
    public void testGetEmpleados() {
        List<Empleado> empleados = empleadoServicios.getEmpleados();
        assertEquals(2, empleados.size());
    }
}
