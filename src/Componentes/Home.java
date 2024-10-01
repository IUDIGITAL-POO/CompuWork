package Componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Servicios.UsuarioServicios;

public class Home extends JFrame {
    private UsuarioServicios usuarioServicios;
    private Color buttonColorBLUE = Color.BLUE; // Color del botón
    private Color buttonColorGRAY = Color.GRAY; // Color del botón

    public Home(UsuarioServicios usuarioServicios) {
        this.usuarioServicios = usuarioServicios;

        setTitle("Inicio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear un panel para el logo
        LogoPanel logoPanel = new LogoPanel();
        add(logoPanel, BorderLayout.CENTER); // Agregar el panel del logo al centro

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnRegister = new JButton("Registrar Usuario");

        // Action Listener para el botón de iniciar sesión
        btnLogin.setBackground(buttonColorBLUE);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear la ventana de login y pasarle la instancia de UsuarioServicios
                Login login = new Login(usuarioServicios);
                // Hacer visible la ventana de login
                login.setVisible(true);
                dispose(); // Cerrar la ventana Home
            }
        });

        // Action Listener para el botón de registrar usuario
        btnRegister.setBackground(buttonColorGRAY);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear la ventana de registro y pasarle la instancia de UsuarioServicios
                RegistrarUsuario register = new RegistrarUsuario(usuarioServicios); // Asegúrate de usar la clase correcta
                register.setVisible(true); // Mostrar la ventana de registro
                dispose(); // Cerrar la ventana Home
            }
        });


        // Agregar botones al panel
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        // Agregar el panel de botones a la ventana principal
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Clase interna para el panel del logo
    private class LogoPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Establecer el color y la fuente para el texto
            g.setColor(new Color(0xFF5733)); // Color para "Compu"
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Compu", 50, 100);

            g.setColor(new Color(0x337AFF)); // Color para "Work"
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Work", 180, 100);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear instancias de GestionServicios y UsuarioServicios
            UsuarioServicios usuarioServicios = new UsuarioServicios();

            // Crear la ventana Home y pasar las instancias
            Home home = new Home(usuarioServicios);
            home.setVisible(true); // Hacer visible la ventana Home
        });
    }
}
