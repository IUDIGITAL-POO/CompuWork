package Componentes;

import Modelo.ReporteDesempenio;
import Modelo.Empleado;
import Modelo.Departamento;
import Servicios.ReporteDesempenioServicios;
import Servicios.ReporteDesempenioServicios;
import Servicios.EmpleadoServicios;
import Servicios.DepartamentoServicios;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FormularioReporte extends JDialog {
    private JComboBox<String> cboTipoReporte;
    private JComboBox<Empleado> cboEmpleado;
    private JComboBox<Departamento> cboDepartamento;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtMetrica1;
    private JTextField txtMetrica2;
    private JTextField txtMetrica3;
    private JTextArea txtComentarios;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private ReporteDesempenioServicios servicioReporte;
    private EmpleadoServicios empleadoServicios;
    private DepartamentoServicios departamentoServicios;
    private ReporteDesempenio reporteEditar;

    public FormularioReporte(JFrame parent, ReporteDesempenioServicios servicioReporte) {
        super(parent, "Formulario de Reporte", true);
        this.servicioReporte = servicioReporte;
        this.departamentoServicios = ReporteDesempenioServicios.getDepartamentoServicios();
        this.empleadoServicios = ReporteDesempenioServicios.getEmpleadoServicios();

        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cboTipoReporte = new JComboBox<>(new String[]{"Individual", "Departamental"});
        cboEmpleado = new JComboBox<>(empleadoServicios.getEmpleados().toArray(new Empleado[0]));
        cboDepartamento = new JComboBox<>(departamentoServicios.getDepartamentos().toArray(new Departamento[0]));
        txtFechaInicio = new JTextField(10);
        txtFechaFin = new JTextField(10);
        txtMetrica1 = new JTextField(10);
        txtMetrica2 = new JTextField(10);
        txtMetrica3 = new JTextField(10);
        txtComentarios = new JTextArea(5, 20);
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Tipo de Reporte:"), gbc);
        gbc.gridx = 1;
        add(cboTipoReporte, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Empleado:"), gbc);
        gbc.gridx = 1;
        add(cboEmpleado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Departamento:"), gbc);
        gbc.gridx = 1;
        add(cboDepartamento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Fecha Inicio:"), gbc);
        gbc.gridx = 1;
        add(txtFechaInicio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Fecha Fin:"), gbc);
        gbc.gridx = 1;
        add(txtFechaFin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Métrica 1:"), gbc);
        gbc.gridx = 1;
        add(txtMetrica1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Métrica 2:"), gbc);
        gbc.gridx = 1;
        add(txtMetrica2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Métrica 3:"), gbc);
        gbc.gridx = 1;
        add(txtMetrica3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Comentarios:"), gbc);
        gbc.gridx = 1;
        gbc.gridheight = 2;
        add(new JScrollPane(txtComentarios), gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, gbc);

        btnGuardar.addActionListener(e -> guardarReporte());
        btnCancelar.addActionListener(e -> dispose());

        cboTipoReporte.addActionListener(e -> actualizarCamposSegunTipo());

        actualizarCamposSegunTipo();

        pack();
        setLocationRelativeTo(getParent());
    }

    private void actualizarCamposSegunTipo() {
        boolean esIndividual = cboTipoReporte.getSelectedItem().equals("Individual");
        cboEmpleado.setVisible(esIndividual);
        cboDepartamento.setVisible(!esIndividual);
    }

    public void setReporteEditar(ReporteDesempenio reporte) {
        this.reporteEditar = reporte;
        if (reporte != null) {
            cboTipoReporte.setSelectedItem(reporte.getEmpleado() != null ? "Individual" : "Departamental");
            if (reporte.getEmpleado() != null) {
                cboEmpleado.setSelectedItem(reporte.getEmpleado());
            } else {
                cboDepartamento.setSelectedItem(reporte.getDepartamento());
            }
            txtFechaInicio.setText(reporte.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            txtFechaFin.setText(reporte.getFechaFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            Map<String, Double> metricas = reporte.getMetricas();
            if (metricas != null && !metricas.isEmpty()) {
                String[] metricasArray = metricas.keySet().toArray(new String[0]);
                if (metricasArray.length > 0) txtMetrica1.setText(metricas.get(metricasArray[0]).toString());
                if (metricasArray.length > 1) txtMetrica2.setText(metricas.get(metricasArray[1]).toString());
                if (metricasArray.length > 2) txtMetrica3.setText(metricas.get(metricasArray[2]).toString());
            }

            txtComentarios.setText(reporte.getComentarios());
        }
        actualizarCamposSegunTipo();
    }

    private void guardarReporte() {
        try {
            String tipoReporte = (String) cboTipoReporte.getSelectedItem();
            LocalDate fechaInicio = LocalDate.parse(txtFechaInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaFin = LocalDate.parse(txtFechaFin.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Map<String, Double> metricas = new HashMap<>();
            try {
                if (!txtMetrica1.getText().isEmpty()) metricas.put("Métrica 1", Double.parseDouble(txtMetrica1.getText()));
                if (!txtMetrica2.getText().isEmpty()) metricas.put("Métrica 2", Double.parseDouble(txtMetrica2.getText()));
                if (!txtMetrica3.getText().isEmpty()) metricas.put("Métrica 3", Double.parseDouble(txtMetrica3.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Las métricas deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String comentarios = txtComentarios.getText();

            ReporteDesempenio reporte;
            if (tipoReporte.equals("Individual")) {
                Empleado empleado = (Empleado) cboEmpleado.getSelectedItem();
                reporte = new ReporteDesempenio(empleado, fechaInicio, fechaFin);
            } else {
                Departamento departamento = (Departamento) cboDepartamento.getSelectedItem();
                reporte = new ReporteDesempenio(departamento, fechaInicio, fechaFin);
            }

            reporte.setMetricas(metricas);
            reporte.setComentarios(comentarios);

            if (reporteEditar == null) {
                servicioReporte.crearReporteIndividual(reporte.getEmpleado(), fechaInicio, fechaFin, metricas, comentarios);
            } else {
                servicioReporte.actualizarReporte(reporte, metricas, comentarios);
            }

            JOptionPane.showMessageDialog(this, "Reporte guardado exitosamente.");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}