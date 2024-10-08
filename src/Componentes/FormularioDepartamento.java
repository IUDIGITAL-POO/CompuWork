package Componentes;

import Modelo.Departamento;
import Servicios.DepartamentoServicios;
import javax.swing.*;
import java.awt.*;

public class FormularioDepartamento extends JDialog {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtTipo;
    private JTextField txtUbicacion;
    private JTextField txtTelefono;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private DepartamentoServicios departamentoServicios;
    private Departamento departamentoEditar;

    public FormularioDepartamento(JFrame parent, DepartamentoServicios departamentoServicios) {
        super(parent, "Formulario de Departamento", true);
        this.departamentoServicios = departamentoServicios;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtCodigo = new JTextField(10);
        txtNombre = new JTextField(20);
        txtTipo = new JTextField(15);
        txtUbicacion = new JTextField(20);
        txtTelefono = new JTextField(15);
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Código:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Tipo:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtTipo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Ubicación:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtUbicacion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, gbc);

        btnGuardar.addActionListener(e -> guardarDepartamento());
        btnCancelar.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(getParent());
    }

    public void setDepartamentoEditar(Departamento departamento) {
        this.departamentoEditar = departamento;
        if (departamento != null) {
            txtCodigo.setText(String.valueOf(departamento.getCodigo()));
            txtCodigo.setEditable(false);
            txtNombre.setText(departamento.getNombre());
            txtTipo.setText(departamento.getTipo());
            txtUbicacion.setText(departamento.getUbicacion());
            txtTelefono.setText(departamento.getTelefono());
        } else {
            txtCodigo.setText("");
            txtCodigo.setEditable(true);
            txtNombre.setText("");
            txtTipo.setText("");
            txtUbicacion.setText("");
            txtTelefono.setText("");
        }
    }

    private void guardarDepartamento() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = txtNombre.getText();
            String tipo = txtTipo.getText();
            String ubicacion = txtUbicacion.getText();
            String telefono = txtTelefono.getText();

            Departamento departamento = new Departamento(codigo, nombre, tipo, ubicacion, telefono);

            if (departamentoEditar == null) {
                departamentoServicios.registrarDepartamento(departamento);
            } else {
                departamentoServicios.actualizarDepartamento(departamento);
            }

            JOptionPane.showMessageDialog(this, "Departamento guardado exitosamente.");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor válido para el código.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el departamento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}