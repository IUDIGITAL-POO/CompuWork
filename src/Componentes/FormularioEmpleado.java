package Componentes;

import Modelo.Empleado;
import Servicios.EmpleadoServicios;
import javax.swing.*;
import java.awt.*;

public class FormularioEmpleado extends JDialog {
    private JTextField txtNombre;
    private JTextField txtId;
    private JComboBox<String> cboTipoEmpleado;
    private JTextField txtSalario;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private EmpleadoServicios empleadoServicios;
    private Empleado empleadoEditar;

    public FormularioEmpleado(JFrame parent, EmpleadoServicios empleadoServicios) {
        super(parent, "Formulario de Empleado", true);
        this.empleadoServicios = empleadoServicios;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(20);
        txtId = new JTextField(10);
        cboTipoEmpleado = new JComboBox<>(new String[]{"Permanente", "Temporal"});
        txtSalario = new JTextField(10);
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Tipo:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(cboTipoEmpleado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Salario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtSalario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, gbc);

        btnGuardar.addActionListener(e -> guardarEmpleado());
        btnCancelar.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(getParent());
    }

    public void setEmpleadoEditar(Empleado empleado) {
        this.empleadoEditar = empleado;
        if (empleado != null) {
            txtNombre.setText(empleado.getNombre());
            txtId.setText(String.valueOf(empleado.getId()));
            txtId.setEditable(false);
            cboTipoEmpleado.setSelectedItem(empleado.getTipoEmpleado());
            txtSalario.setText(String.valueOf(empleado.getSalario()));
        } else {
            txtNombre.setText("");
            txtId.setText("");
            txtId.setEditable(true);
            cboTipoEmpleado.setSelectedIndex(0);
            txtSalario.setText("");
        }
    }

    private void guardarEmpleado() {
        try {
            String nombre = txtNombre.getText();
            int id = Integer.parseInt(txtId.getText());
            String tipoEmpleado = (String) cboTipoEmpleado.getSelectedItem();
            double salario = Double.parseDouble(txtSalario.getText());

            if (empleadoEditar == null) {
                // Crear nuevo empleado
                Empleado nuevoEmpleado = new Empleado(nombre, id, tipoEmpleado, salario);
                empleadoServicios.registrarEmpleado(nuevoEmpleado);
            } else {
                // Actualizar empleado existente
                empleadoEditar.setNombre(nombre);
                empleadoEditar.setTipoEmpleado(tipoEmpleado);
                empleadoEditar.setSalario(salario);
                empleadoServicios.actualizarEmpleado(empleadoEditar);
            }

            JOptionPane.showMessageDialog(this, "Empleado guardado exitosamente.");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para ID y Salario.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}