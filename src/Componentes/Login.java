package Componentes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Usuario;
import Servicios.UsuarioServicios;
import Componentes.MenuPorRol;

public class Login extends JFrame {
    private UsuarioServicios usuarioServicios;

    // Constructor de la clase Login
    public Login(UsuarioServicios usuarioServicios) {
        this.usuarioServicios = usuarioServicios; // Inicializar gestionServicios
        initializeUI(); // Método para inicializar la interfaz gráfica
    }

    // Método para configurar la interfaz de usuario
    private void initializeUI() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiqueta para el usuario
        JLabel labelUsuario = new JLabel("Usuario:");
        labelUsuario.setBounds(50, 30, 80, 30);
        add(labelUsuario);

        // Campo de texto para el usuario
        JTextField usuarioField = new JTextField();
        usuarioField.setBounds(140, 30, 180, 30);
        add(usuarioField);

        // Etiqueta para la contraseña
        JLabel labelPassword = new JLabel("Contraseña:");
        labelPassword.setBounds(50, 70, 80, 30);
        add(labelPassword);

        // Campo de texto para la contraseña
        JPasswordField contrasennaField = new JPasswordField();
        contrasennaField.setBounds(140, 70, 180, 30);
        add(contrasennaField);

        // Botón para iniciar sesión
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(140, 110, 180, 30);
        add(btnLogin);

        // Listener para el botón de inicio de sesión
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion(usuarioField, contrasennaField);
            }
        });
    }

    // Método para manejar la lógica de inicio de sesión
    private void iniciarSesion(JTextField usuarioField, JPasswordField contrasennaField) {
        String nombreUsuario = usuarioField.getText();
        String contrasena = new String(contrasennaField.getPassword());

        Usuario usuario = usuarioServicios.iniciarSesion(nombreUsuario, contrasena);
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");

            // Abrir el menú correspondiente basado en el rol del usuario
            new MenuPorRol(usuario.getRol()).setVisible(true); // Asegúrate de que la clase Usuario tenga un método getRol()

            dispose(); // Cerrar la ventana de login
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
}
