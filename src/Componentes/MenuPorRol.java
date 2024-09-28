package Componentes;

import javax.swing.*;

public class MenuPorRol extends JFrame {
    private JTabbedPane tabbedPane;

    public MenuPorRol(String rol) {
        setTitle("Sistema de Gestión");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        if (rol.equals("Administrador")) {
            mostrarMenuAdministrador();
        } else if (rol.equals("Empleado")) {
            mostrarMenuEmpleado();
        }

        add(tabbedPane);
        setVisible(true);
    }

    private void mostrarMenuAdministrador() {
        tabbedPane.addTab("Gestionar Empleados", new GestionarEmpleados());
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