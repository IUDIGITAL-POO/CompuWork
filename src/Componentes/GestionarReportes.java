package Componentes;

import javax.swing.*;

public class GestionarReportes extends JPanel {
    public GestionarReportes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createButton("Registrar Métrica", "Registrando métrica..."));
        add(createButton("Actualizar Métrica", "Actualizando métrica..."));
    }

    private JButton createButton(String text, String message) {
        JButton button = new JButton(text);
        button.addActionListener(e -> JOptionPane.showMessageDialog(this, message));
        return button;
    }
}
