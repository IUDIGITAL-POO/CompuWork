package Componentes;

import Servicios.EmpleadoServicios;
import Modelo.Departamento;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuPorRol extends JFrame {
    private JTabbedPane tabbedPane;
    private EmpleadoServicios empleadoServicios;

    public MenuPorRol(String rol) {
        setTitle("Sistema de Gestión");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Inicializar servicios
        List<Departamento> departamentos = new ArrayList<>(); // Esto debería venir de un DepartamentoServicios
        empleadoServicios = new EmpleadoServicios(departamentos);

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
        tabbedPane.addTab("Gestionar Departamentos", new GestionarDepartamentos());
        tabbedPane.addTab("Gestionar Métricas", new GestionarReportes());
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