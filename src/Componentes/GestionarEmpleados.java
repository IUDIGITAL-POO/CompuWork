package Componentes;

import javax.swing.*;
import java.awt.*;

public class GestionarEmpleados extends JPanel{

    public GestionarEmpleados() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createButton("Registrar Empleado", "Registrando empleado..."));
        add(createButton("Actualizar Empleado", "Actualizando empleado..."));
        add(createButton("Eliminar Empleado", "Eliminando empleado..."));
    }

    private JButton createButton(String text, String message) {
        JButton button = new JButton(text);
        button.addActionListener(e -> JOptionPane.showMessageDialog(this, message));
        return button;
    }
}
