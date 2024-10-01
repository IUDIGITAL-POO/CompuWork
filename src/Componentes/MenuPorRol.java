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
        departamentoServicios.inicializarDatos();
        empleadoServicios = new EmpleadoServicios(departamentoServicios.getDepartamentos());
        reporteServicios = new ReporteDesempenioServicios(departamentoServicios, empleadoServicios);
        Departamento rrhh = new Departamento(2, "rrhh", "Recursos", "Piso 2", "123-456-7890");
        departamentoServicios.registrarDepartamento(rrhh);
        empleadoServicios.inicializarDatos(rrhh);

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
        tabbedPane.addTab("Gestionar Métricas", new GestionarReportes(reporteServicios));
        tabbedPane.addTab("Generar Informes", new GenerarInformes());
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