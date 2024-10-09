package Componentes;

import Servicios.EmpleadoServicios;
import Servicios.DepartamentoServicios;
import Modelo.Departamento;
import Servicios.ReporteDesempenioServicios;
import Servicios.UsuarioServicios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuPorRol extends JFrame {
    private JTabbedPane tabbedPane;
    private EmpleadoServicios empleadoServicios;
    private DepartamentoServicios departamentoServicios;
    private ReporteDesempenioServicios reporteServicios;
    private UsuarioServicios usuarioServicios;

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
        usuarioServicios = new UsuarioServicios();

        if (rol.equals("Administrador")) {
            mostrarMenuAdministrador();
        } else if (rol.equals("Empleado")) {
            mostrarMenuEmpleado();
        }
        agregarCerrarSesion();
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

    private void agregarCerrarSesion() {
        JPanel cerrarSesionPanel = new JPanel();

        tabbedPane.addTab("", cerrarSesionPanel);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new JLabel("Cerrar Sesión"));
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedComponent() == cerrarSesionPanel) {
                int respuesta = JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro que desea cerrar sesión?",
                        "Confirmar Cierre de Sesión",
                        JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    dispose();

                    new Home(usuarioServicios).setVisible(true);
                } else {
                    tabbedPane.setSelectedIndex(0);
                }
            }
        });
    }
}