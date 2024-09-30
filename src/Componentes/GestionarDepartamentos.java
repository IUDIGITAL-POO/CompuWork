package Componentes;

import Modelo.Departamento;
import Servicios.DepartamentoServicios;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionarDepartamentos extends JPanel {
    private DepartamentoServicios departamentoServicios;
    private JList<Departamento> listaDepartamentos;
    private DefaultListModel<Departamento> modeloLista;

    public GestionarDepartamentos(DepartamentoServicios departamentoServicios) {
        this.departamentoServicios = departamentoServicios;
        setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaDepartamentos = new JList<>(modeloLista);
        listaDepartamentos.setCellRenderer(new DepartamentoListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaDepartamentos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar Departamento");
        JButton btnEditar = new JButton("Editar Departamento");
        JButton btnEliminar = new JButton("Eliminar Departamento");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> mostrarFormularioAgregar());
        btnEditar.addActionListener(e -> editarDepartamentoSeleccionado());
        btnEliminar.addActionListener(e -> eliminarDepartamentoSeleccionado());

        cargarDepartamentos();
    }

    private void cargarDepartamentos() {
        modeloLista.clear();
        List<Departamento> departamentos = departamentoServicios.getDepartamentos();
        for (Departamento departamento : departamentos) {
            modeloLista.addElement(departamento);
        }
    }

    private void mostrarFormularioAgregar() {
        FormularioDepartamento formulario = new FormularioDepartamento((JFrame) SwingUtilities.getWindowAncestor(this), departamentoServicios);
        formulario.setVisible(true);
        cargarDepartamentos();
    }

    private void editarDepartamentoSeleccionado() {
        Departamento departamentoSeleccionado = listaDepartamentos.getSelectedValue();
        if (departamentoSeleccionado != null) {
            FormularioDepartamento formulario = new FormularioDepartamento((JFrame) SwingUtilities.getWindowAncestor(this), departamentoServicios);
            formulario.setDepartamentoEditar(departamentoSeleccionado);
            formulario.setVisible(true);
            cargarDepartamentos();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento para editar.");
        }
    }

    private void eliminarDepartamentoSeleccionado() {
        Departamento departamentoSeleccionado = listaDepartamentos.getSelectedValue();
        if (departamentoSeleccionado != null) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar el departamento " + departamentoSeleccionado.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                departamentoServicios.eliminarDepartamento(departamentoSeleccionado.getCodigo());
                cargarDepartamentos();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento para eliminar.");
        }
    }

    private class DepartamentoListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Departamento) {
                Departamento departamento = (Departamento) value;
                setText(departamento.getNombre() + " - " + departamento.getTipo());
            }
            return c;
        }
    }
}