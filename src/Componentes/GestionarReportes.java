package Componentes;

import Modelo.ReporteDesempenio;
import Modelo.ReporteDesempenio;
import Servicios.ReporteDesempenioServicios;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionarReportes extends JPanel {
    private ReporteDesempenioServicios servicioReporte;
    private JList<ReporteDesempenio> listaReportes;
    private DefaultListModel<ReporteDesempenio> modeloLista;

    public GestionarReportes(ReporteDesempenioServicios servicioReporte) {
        this.servicioReporte = servicioReporte;
        setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaReportes = new JList<>(modeloLista);
        listaReportes.setCellRenderer(new ReporteListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaReportes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Crear Reporte");
        JButton btnEditar = new JButton("Actualizar Reporte");
        JButton btnEliminar = new JButton("Eliminar Reporte");
        JButton btnVer = new JButton("Ver Reporte");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVer);

        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> mostrarFormularioCrear());
        btnEditar.addActionListener(e -> actualizarReporteSeleccionado());
        btnEliminar.addActionListener(e -> eliminarReporteSeleccionado());
        btnVer.addActionListener(e -> verReporteSeleccionado());

        cargarReportes();
    }

    private void cargarReportes() {
        modeloLista.clear();
        List<ReporteDesempenio> reportes = servicioReporte.obtenerTodosLosReportes();
        for (ReporteDesempenio reporte : reportes) {
            modeloLista.addElement(reporte);
        }
    }

    private void mostrarFormularioCrear() {
        FormularioReporte formulario = new FormularioReporte((JFrame) SwingUtilities.getWindowAncestor(this), servicioReporte);
        formulario.setVisible(true);
        cargarReportes(); // Actualizar la lista después de crear
    }

    private void actualizarReporteSeleccionado() {
        ReporteDesempenio reporteSeleccionado = listaReportes.getSelectedValue();
        if (reporteSeleccionado != null) {
            FormularioReporte formulario = new FormularioReporte((JFrame) SwingUtilities.getWindowAncestor(this), servicioReporte);
            formulario.setReporteEditar(reporteSeleccionado);
            formulario.setVisible(true);
            cargarReportes(); // Actualizar la lista después de editar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un reporte para actualizar.");
        }
    }

    private void eliminarReporteSeleccionado() {
        ReporteDesempenio reporteSeleccionado = listaReportes.getSelectedValue();
        if (reporteSeleccionado != null) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar este reporte?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                servicioReporte.eliminarReporte(reporteSeleccionado);
                cargarReportes(); // Actualizar la lista
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un reporte para eliminar.");
        }
    }

    private void verReporteSeleccionado() {
        ReporteDesempenio reporteSeleccionado = listaReportes.getSelectedValue();
        if (reporteSeleccionado != null) {
            String reporteString = reporteSeleccionado.toString();
            JTextArea textArea = new JTextArea(reporteString);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(this, scrollPane, "Detalle del Reporte", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un reporte para ver.");
        }
    }

    // Clase interna para renderizar los elementos de la lista
    private class ReporteListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof ReporteDesempenio) {
                ReporteDesempenio reporte = (ReporteDesempenio) value;
                String tipoReporte = (reporte.getEmpleado() != null) ? "Individual" : "Departamental";
                String entidad = (reporte.getEmpleado() != null) ? reporte.getEmpleado().getNombre() : reporte.getDepartamento().getNombre();
                setText(tipoReporte + " - " + entidad + " - " + reporte.getFechaInicio() + " a " + reporte.getFechaFin());
            }
            return c;
        }
    }
}