package Componentes;

import javax.swing.*;

public class ConsultarInforme extends JPanel {

        public ConsultarInforme() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            add(createButton("Consultar Informe", "Consultando informe..."));
            add(createButton("Actualizar Informe", "Actualizando informe..."));
        }

        private JButton createButton(String text, String message) {
            JButton button = new JButton(text);
            button.addActionListener(e -> JOptionPane.showMessageDialog(this, message));
            return button;
        }
}
