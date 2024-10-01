package Componentes;

import Servicios.EmpleadoServicios;
import Servicios.DepartamentoServicios;
import Modelo.Departamento;
import Servicios.ReporteDesempenioServicios;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuPorRol extends JFrame {
    private JTabbedPane tabbedPane;
    private EmpleadoServicios empleadoServicios;
    private DepartamentoServicios departamentoServicios;
    private ReporteDesempenioServicios reporteServicios;

    public MenuPorRol(String rol) {
        setTitle("Sistema de Gestión");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Inicializar servicios
        departamentoServicios = new DepartamentoServicios();
        empleadoServicios = new EmpleadoServicios(departamentoServicios.getDepartamentos());
        reporteServicios = new ReporteDesempenioServicios(departamentoServicios, empleadoServicios);
        Departamento prueba = new Departamento(2, "prueba", "Recursos", "Piso 2", "123-456-7890");
        departamentoServicios.registrarDepartamento(prueba);
        departamentoServicios.inicializarDatos();
        empleadoServicios.inicializarDatos(prueba);
        reporteServicios.inicializarDatos();


        if (rol.equals("Administrador")) {
            mostrarMenuAdministrador();
        } else if (rol.equals("Empleado")) {
            mostrarMenuEmpleado();
        }

        add(tabbedPane);
        setVisible(true);
    }

    private void mostrarMenuAdministrador() {
        tabbedPane.addTab("Gestionar Empleados", new GestionarEmpleados(empleadoServicios));
        tabbedPane.addTab("Gestionar Departamentos", new GestionarDepartamentos(departamentoServicios));
        tabbedPane.addTab("Gestionar Reportes", new GestionarReportes(reporteServicios));
    }

    private void mostrarMenuEmpleado() {
        tabbedPane.addTab("Consultar Informe", new ConsultarInforme());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuPorRol("Administrador");
        });
    }
}