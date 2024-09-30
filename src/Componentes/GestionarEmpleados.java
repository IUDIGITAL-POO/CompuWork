package Componentes;

import Modelo.Empleado;
import Servicios.EmpleadoServicios;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionarEmpleados extends JPanel {
    private EmpleadoServicios empleadoServicios;
    private JList<Empleado> listaEmpleados;
    private DefaultListModel<Empleado> modeloLista;

    public GestionarEmpleados(EmpleadoServicios empleadoServicios) {
        this.empleadoServicios = empleadoServicios;
        setLayout(new BorderLayout());

        // Crear modelo de lista y JList
        modeloLista = new DefaultListModel<>();
        listaEmpleados = new JList<>(modeloLista);
        listaEmpleados.setCellRenderer(new EmpleadoListCellRenderer());

        // Agregar JList a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(listaEmpleados);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar Empleado");
        JButton btnEditar = new JButton("Editar Empleado");
        JButton btnEliminar = new JButton("Eliminar Empleado");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        // Agregar ActionListeners
        btnAgregar.addActionListener(e -> mostrarFormularioAgregar());
        btnEditar.addActionListener(e -> editarEmpleadoSeleccionado());
        btnEliminar.addActionListener(e -> eliminarEmpleadoSeleccionado());

        // Cargar empleados iniciales
        cargarEmpleados();
    }

    private void cargarEmpleados() {
        modeloLista.clear();
        List<Empleado> empleados = empleadoServicios.getEmpleados();
        for (Empleado empleado : empleados) {
            modeloLista.addElement(empleado);
        }
    }

    private void mostrarFormularioAgregar() {
        // Implementar formulario para agregar empleado
        JOptionPane.showMessageDialog(this, "Formulario de agregar empleado (por implementar)");
    }

    private void editarEmpleadoSeleccionado() {
        Empleado empleadoSeleccionado = listaEmpleados.getSelectedValue();
        if (empleadoSeleccionado != null) {
            // Implementar lógica de edición
            JOptionPane.showMessageDialog(this, "Editando empleado: " + empleadoSeleccionado.getNombre());
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un empleado para editar.");
        }
    }

    private void eliminarEmpleadoSeleccionado() {
        Empleado empleadoSeleccionado = listaEmpleados.getSelectedValue();
        if (empleadoSeleccionado != null) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar a " + empleadoSeleccionado.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                empleadoServicios.eliminarEmpleado(empleadoSeleccionado.getId());
                cargarEmpleados(); // Actualizar la lista
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un empleado para eliminar.");
        }
    }

    // Clase interna para renderizar los elementos de la lista
    private class EmpleadoListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Empleado) {
                Empleado empleado = (Empleado) value;
                setText(empleado.getNombre() + " - " + empleado.getTipoEmpleado() + " - $" + empleado.getSalario());
            }
            return c;
        }
    }
}