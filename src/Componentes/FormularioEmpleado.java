package Componentes;

import Modelo.Empleado;
import Modelo.EmpleadoPermanente;
import Modelo.EmpleadoTemporal;
import Servicios.EmpleadoServicios;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormularioEmpleado extends JDialog {
    private JTextField txtNombre;
    private JTextField txtId;
    private JComboBox<String> cboTipoEmpleado;
    private JTextField txtSalario;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JLabel lblFechaInicio;
    private JLabel lblFechaFin;
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
        txtFechaInicio = new JTextField(10);
        txtFechaFin = new JTextField(10);
        lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaFin = new JLabel("Fecha Fin:");
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
        add(lblFechaInicio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtFechaInicio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblFechaFin, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(txtFechaFin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, gbc);

        btnGuardar.addActionListener(e -> guardarEmpleado());
        btnCancelar.addActionListener(e -> dispose());

        cboTipoEmpleado.addActionListener(e -> actualizarCamposFecha());

        actualizarCamposFecha(); // Llamar inicialmente para configurar la visibilidad

        pack();
        setLocationRelativeTo(getParent());
    }

    private void actualizarCamposFecha() {
        boolean esTemporal = cboTipoEmpleado.getSelectedItem().equals("Temporal");
        lblFechaInicio.setVisible(esTemporal);
        txtFechaInicio.setVisible(esTemporal);
        lblFechaFin.setVisible(esTemporal);
        txtFechaFin.setVisible(esTemporal);
        pack(); // Ajusta el tamaño de la ventana según los componentes visibles
    }

    public void setEmpleadoEditar(Empleado empleado) {
        this.empleadoEditar = empleado;
        if (empleado != null) {
            txtNombre.setText(empleado.getNombre());
            txtId.setText(String.valueOf(empleado.getId()));
            txtId.setEditable(false);
            cboTipoEmpleado.setSelectedItem(empleado.getTipoEmpleado());
            txtSalario.setText(String.valueOf(empleado.getSalario()));

            if (empleado instanceof EmpleadoTemporal) {
                EmpleadoTemporal temporal = (EmpleadoTemporal) empleado;
                txtFechaInicio.setText(temporal.getFechaInicio());
                txtFechaFin.setText(temporal.getFechaFin());
            }
        } else {
            txtNombre.setText("");
            txtId.setText("");
            txtId.setEditable(true);
            cboTipoEmpleado.setSelectedIndex(0);
            txtSalario.setText("");
            txtFechaInicio.setText("");
            txtFechaFin.setText("");
        }
        actualizarCamposFecha();
    }

    private void guardarEmpleado() {
        try {
            String nombre = txtNombre.getText();
            int id = Integer.parseInt(txtId.getText());
            String tipoEmpleado = (String) cboTipoEmpleado.getSelectedItem();
            double salario = Double.parseDouble(txtSalario.getText());

            Empleado empleado;
            if (tipoEmpleado.equals("Temporal")) {
                String fechaInicio = txtFechaInicio.getText();
                String fechaFin = txtFechaFin.getText();
                validarFechas(fechaInicio, fechaFin);
                empleado = new EmpleadoTemporal(nombre, id, tipoEmpleado, salario, fechaInicio, fechaFin);
            } else {
                empleado = new EmpleadoPermanente(nombre, id, tipoEmpleado, salario);
            }

            if (empleadoEditar == null) {
                empleadoServicios.registrarEmpleado(empleado);
            } else {
                empleadoServicios.actualizarEmpleado(empleado);
            }

            JOptionPane.showMessageDialog(this, "Empleado guardado exitosamente.");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para ID y Salario.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validarFechas(String fechaInicio, String fechaFin) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        sdf.parse(fechaInicio);
        sdf.parse(fechaFin);
    }
}