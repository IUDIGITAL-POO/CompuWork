package Componentes;

import Modelo.ReporteDesempenio;
import Modelo.ReporteDesempenio;
import Servicios.ReporteDesempenioServicios;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.awt.print.*;

public class GestionarReportes extends JPanel {
    private ReporteDesempenioServicios servicioReporte;
    private JList<ReporteDesempenio> listaReportesIndividuales;
    private DefaultListModel<ReporteDesempenio> modeloListaIndividuales;
    private JList<ReporteDesempenio> listaReportesDepartamentales;
    private DefaultListModel<ReporteDesempenio> modeloListaDepartamentales;


    public GestionarReportes(ReporteDesempenioServicios servicioReporte) {
        this.servicioReporte = servicioReporte;
        setLayout(new BorderLayout());

        modeloListaIndividuales = new DefaultListModel<>();
        listaReportesIndividuales = new JList<>(modeloListaIndividuales);
        listaReportesIndividuales.setCellRenderer(new ReporteListCellRenderer());


        modeloListaDepartamentales = new DefaultListModel<>();
        listaReportesDepartamentales = new JList<>(modeloListaDepartamentales);
        listaReportesDepartamentales.setCellRenderer(new ReporteListCellRenderer());

        JPanel panelListas = new JPanel();
        panelListas.setLayout(new GridLayout(1, 2));

        JPanel panelIndividuales = new JPanel();
        panelIndividuales.setLayout(new BorderLayout());
        panelIndividuales.add(new JScrollPane(listaReportesIndividuales), BorderLayout.CENTER);
        panelIndividuales.setBorder(BorderFactory.createTitledBorder("Reportes Individuales"));

        JPanel panelDepartamentales = new JPanel();
        panelDepartamentales.setLayout(new BorderLayout());
        panelDepartamentales.add(new JScrollPane(listaReportesDepartamentales), BorderLayout.CENTER);
        panelDepartamentales.setBorder(BorderFactory.createTitledBorder("Reportes Departamentales"));

        panelListas.add(panelIndividuales);
        panelListas.add(panelDepartamentales);

        listaReportesIndividuales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaReportesDepartamentales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaReportesIndividuales.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    listaReportesDepartamentales.clearSelection();
                }
            }
        });


        listaReportesDepartamentales.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    listaReportesIndividuales.clearSelection();
                }
            }
        });

        add(panelListas, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Crear Reporte");
        JButton btnEditar = new JButton("Actualizar Reporte");
        JButton btnEliminar = new JButton("Eliminar Reporte");
        JButton btnVer = new JButton("Ver Reporte");
        JButton btnImprimir = new JButton("Imprimir Reporte");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVer);
        panelBotones.add(btnImprimir);

        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> mostrarFormularioCrear());
        btnEditar.addActionListener(e -> actualizarReporteSeleccionado());
        btnEliminar.addActionListener(e -> eliminarReporteSeleccionado());
        btnVer.addActionListener(e -> verReporteSeleccionado());
        btnImprimir.addActionListener(e -> imprimirReporteSeleccionado());

        cargarReportes();
    }

    private void cargarReportes() {
        modeloListaIndividuales.clear();
        modeloListaDepartamentales.clear();
        List<ReporteDesempenio> reportes = servicioReporte.obtenerTodosLosReportes();
        for (ReporteDesempenio reporte : reportes) {
            if (reporte.getEmpleado() != null) {
                modeloListaIndividuales.addElement(reporte);
            } else {
                modeloListaDepartamentales.addElement(reporte);
            }
        }
    }

    private void mostrarFormularioCrear() {
        FormularioReporte formulario = new FormularioReporte((JFrame) SwingUtilities.getWindowAncestor(this), servicioReporte);
        formulario.setVisible(true);
        cargarReportes();
    }

    private void actualizarReporteSeleccionado() {
        ReporteDesempenio reporteSeleccionado = null;
        if (listaReportesIndividuales.getSelectedValue() != null) {
            reporteSeleccionado = listaReportesIndividuales.getSelectedValue();
        } else if (listaReportesDepartamentales.getSelectedValue() != null) {
            reporteSeleccionado = listaReportesDepartamentales.getSelectedValue();
        }
        if (reporteSeleccionado != null) {
            FormularioReporte formulario = new FormularioReporte((JFrame) SwingUtilities.getWindowAncestor(this), servicioReporte);
            formulario.setReporteEditar(reporteSeleccionado);
            formulario.setVisible(true);
            cargarReportes();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un reporte para actualizar.");
        }
    }


    private void eliminarReporteSeleccionado() {
        ReporteDesempenio reporteSeleccionado = null;
        if (listaReportesIndividuales.getSelectedValue() != null) {
            reporteSeleccionado = listaReportesIndividuales.getSelectedValue();
        } else if (listaReportesDepartamentales.getSelectedValue() != null) {
            reporteSeleccionado = listaReportesDepartamentales.getSelectedValue();
        }
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
        ReporteDesempenio reporteSeleccionado = null;
        if (listaReportesIndividuales.getSelectedValue() != null) {
            reporteSeleccionado = listaReportesIndividuales.getSelectedValue();
        } else if (listaReportesDepartamentales.getSelectedValue() != null) {
            reporteSeleccionado = listaReportesDepartamentales.getSelectedValue();
        }
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

    private void imprimirReporteSeleccionado() {
        ReporteDesempenio reporteSeleccionado = obtenerReporteSeleccionado();

        if (reporteSeleccionado != null) {
            try {
                String reporteImprimible = servicioReporte.generarReporteImprimible(reporteSeleccionado);

                PrinterJob job = PrinterJob.getPrinterJob();
                PageFormat pageFormat = job.defaultPage();
                Paper paper = pageFormat.getPaper();

                // Establecer márgenes (1 pulgada = 72 puntos)
                double margin = 72;
                paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
                pageFormat.setPaper(paper);

                job.setPrintable((graphics, pf, pageIndex) -> {
                    if (pageIndex > 0) {
                        return Printable.NO_SUCH_PAGE;
                    }

                    Graphics2D g2d = (Graphics2D) graphics;
                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 10));

                    int y = (int) pf.getImageableY();
                    for (String line : reporteImprimible.split("\n")) {
                        y += g2d.getFontMetrics().getHeight();
                        g2d.drawString(line, (int) pf.getImageableX(), y);
                    }

                    return Printable.PAGE_EXISTS;
                });

                // Mostrar diálogo de vista previa
                JTextArea previewArea = new JTextArea(reporteImprimible);
                previewArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                previewArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(previewArea);
                scrollPane.setPreferredSize(new Dimension(500, 400));

                int option = JOptionPane.showOptionDialog(
                        this,
                        scrollPane,
                        "Vista previa del reporte",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"Imprimir", "Cancelar"},
                        "Imprimir"
                );

                if (option == JOptionPane.OK_OPTION && job.printDialog()) {
                    job.print();
                    JOptionPane.showMessageDialog(this, "Reporte enviado a imprimir exitosamente.");
                }
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al imprimir: " + ex.getMessage(),
                        "Error de Impresión",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un reporte para imprimir.");
        }
    }

    private ReporteDesempenio obtenerReporteSeleccionado() {
        if (listaReportesIndividuales.getSelectedValue() != null) {
            return listaReportesIndividuales.getSelectedValue();
        } else if (listaReportesDepartamentales.getSelectedValue() != null) {
            return listaReportesDepartamentales.getSelectedValue();
        }
        return null;
    }

    // Clase interna para renderizar los elementos de la lista
    private static class ReporteListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof ReporteDesempenio reporte) {
                String entidad;
                if (reporte.getEmpleado() != null) {
                    entidad = reporte.getEmpleado().getNombre();
                } else if (reporte.getDepartamento() != null) {
                    entidad = reporte.getDepartamento().getNombre();
                } else {
                    entidad = "Sin asignar";
                }
                setText(entidad + " - " + reporte.getFechaInicio() + " a " + reporte.getFechaFin());
            }
            return c;
        }
    }
}