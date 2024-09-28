package Componentes;

import javax.swing.*;

public class GenerarInformes extends JPanel {

    public GenerarInformes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createButton("Generar Informe Departamental", "Generando informe departamental..."));
        add(createButton("Generar Informe Individual", "Generando informe individual..."));
        add(createButton("Actualizar Informe", "Actualizando informe..."));
    }

    private JButton createButton(String text, String message) {
        JButton button = new JButton(text);
        button.addActionListener(e -> JOptionPane.showMessageDialog(this, message));
        return button;
    }
}
