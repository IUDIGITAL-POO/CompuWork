package Componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Usuario;
import Servicios.UsuarioServicios;

public class Login extends JFrame {
    private UsuarioServicios usuarioServicios;
    private Color buttonColorBLUE = Color.BLUE; // Color del botón
    private Color buttonColorGRAY = Color.GRAY; // Color del botón

    // Constructor de la clase Login
    public Login(UsuarioServicios usuarioServicios) {
        this.usuarioServicios = usuarioServicios; // Inicializar gestionServicios
        initializeUI(); // Método para inicializar la interfaz gráfica
    }

    // Método para configurar la interfaz de usuario
    private void initializeUI() {

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // Cambiar a GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Etiqueta para el usuario
        JLabel labelUsuario = new JLabel("Usuario:");
        labelUsuario.setBounds(50, 30, 80, 30);
        gbc.gridx = 0; // Columna
        gbc.gridy = 0; // Fila
        add(labelUsuario, gbc);

        // Campo de texto para el usuario
        JTextField usuarioField = new JTextField();
        styleTextField(usuarioField);
        usuarioField.setBounds(140, 30, 180, 30);
        gbc.gridx = 1; // Columna
        add(usuarioField, gbc);

        // Etiqueta para la contraseña
        JLabel labelPassword = new JLabel("Contraseña:");
        labelPassword.setBounds(50, 70, 80, 30);
        gbc.gridx = 0; // Columna
        gbc.gridy = 1; // Fila
        add(labelPassword, gbc);

        // Campo de texto para la contraseña
        JPasswordField contrasennaField = new JPasswordField();
        styleTextField(contrasennaField);
        contrasennaField.setBounds(140, 70, 180, 30);
        gbc.gridx = 1; // Columna
        add(contrasennaField, gbc);

        // Botón para iniciar sesión
        JPanel buttonPanel = new JPanel();
        JButton btnHome = new JButton("Inicio");
        JButton btnLogin = new JButton("Iniciar Sesión");

        btnLogin.setBackground(buttonColorBLUE);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion(usuarioField, contrasennaField);
            }
        });

        // Botón para iniciar sesión
        btnHome.setBackground(buttonColorGRAY);
        btnHome.setForeground(Color.WHITE);
        btnHome.setFocusPainted(false);
        btnHome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding en el botón
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear la ventana principal (Home)
                Home home = new Home(usuarioServicios);
                // Hacer visible la ventana de Home
                home.setVisible(true);
            }
        });

        buttonPanel.add(btnHome);
        buttonPanel.add(btnLogin);
        gbc.gridx = 0; // Columna
        gbc.gridy = 3; // Fila
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(buttonPanel, gbc);

        setVisible(true);
    }

    // Método para manejar la lógica de inicio de sesión
    private void iniciarSesion(JTextField usuarioField, JPasswordField contrasennaField) {
        String nombreUsuario = usuarioField.getText().trim(); // Usar trim() para eliminar espacios en blanco
        String contrasena = new String(contrasennaField.getPassword()).trim(); // Usar trim() para eliminar espacios en blanco

        // Validar si los campos están vacíos
        if (nombreUsuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan datos por diligenciar");
            return; // Salir del método si hay datos faltantes
        }

        // Intentar iniciar sesión
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

    private void styleTextField(JTextField textField) {
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.DARK_GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Borde gris
        textField.setPreferredSize(new Dimension(250, 30)); // Tamaño preferido
    }
}
