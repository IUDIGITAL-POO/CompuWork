package Componentes;

import javax.swing.*;
import java.awt.*;

public class GestionarDepartamentos extends JPanel {
    public GestionarDepartamentos() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createButton("Registrar Departamento", "Registrando departamento..."));
        add(createButton("Actualizar Departamento", "Actualizando departamento..."));
    }

    private JButton createButton(String text, String message) {
        JButton button = new JButton(text);
        button.addActionListener(e -> JOptionPane.showMessageDialog(this, message));
        return button;
    }
}
