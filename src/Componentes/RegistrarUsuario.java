package Componentes;

import Modelo.Usuario;
import Servicios.UsuarioServicios; // Asegúrate de importar la clase UsuarioServicios
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarUsuario extends JFrame {

    private JTextField txtNombreUsuario;
    private JPasswordField txtContrasena;
    private JComboBox<String> comboRol;
    private UsuarioServicios usuarioServicios; // Instancia de UsuarioServicios
    private Color textColor = Color.DARK_GRAY; // Color de texto
    private Color buttonColorBLUE = Color.BLUE; // Color del botón
    private Color buttonColorGRAY = Color.GRAY; // Color del botón

    // Constructor que recibe UsuarioServicios
    public RegistrarUsuario(UsuarioServicios usuarioServicios) {
        this.usuarioServicios = usuarioServicios; // Inicializar la instancia
        setTitle("Registrar Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // Cambiar a GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Etiquetas y campos para ingresar datos
        JLabel labelNombreUsuario = new JLabel("Nombre de Usuario:");
        labelNombreUsuario.setForeground(textColor);
        gbc.gridx = 0; // Columna
        gbc.gridy = 0; // Fila
        add(labelNombreUsuario, gbc);

        txtNombreUsuario = new JTextField();
        styleTextField(txtNombreUsuario);
        gbc.gridx = 1; // Columna
        add(txtNombreUsuario, gbc);

        JLabel labelContrasena = new JLabel("Contraseña:");
        labelContrasena.setForeground(textColor);
        gbc.gridx = 0; // Columna
        gbc.gridy = 1; // Fila
        add(labelContrasena, gbc);

        txtContrasena = new JPasswordField();
        styleTextField(txtContrasena);
        gbc.gridx = 1; // Columna
        add(txtContrasena, gbc);

        JLabel labelRol = new JLabel("Rol:");
        labelRol.setForeground(textColor);
        gbc.gridx = 0; // Columna
        gbc.gridy = 2; // Fila
        add(labelRol, gbc);

        String[] roles = {"Administrador", "Empleado"};
        comboRol = new JComboBox<>(roles);
        comboRol.setBackground(Color.WHITE);
        comboRol.setForeground(textColor);
        gbc.gridx = 1; // Columna
        add(comboRol, gbc);

        JPanel buttonPanel = new JPanel();
        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrar");
        // Botón para registrar el usuario
        btnRegistrar.setBackground(buttonColorBLUE);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding en el botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el texto del campo de texto y eliminar espacios en blanco
                String nombreUsuario = txtNombreUsuario.getText().trim();
                String contrasena = new String(txtContrasena.getPassword()).trim();
                String rol = (String) comboRol.getSelectedItem();

                // Validar que los campos no estén vacíos
                if (nombreUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacío.");
                    return; // Salir del método si el campo está vacío
                }

                if (contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía.");
                    return; // Salir del método si el campo está vacío
                }

                if (rol == null || rol.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un rol.");
                    return; // Salir del método si no se ha seleccionado un rol
                }

                // Validar si el nombre de usuario ya está registrado
                if (usuarioServicios.existeUsuario(nombreUsuario)) {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya está registrado. Elija otro.");
                    return; // Salir del método si el nombre de usuario ya existe
                }

                // Crear el usuario usando UsuarioServicios
                Usuario usuario = new Usuario(nombreUsuario, contrasena, rol);
                usuarioServicios.registrarUsuario(usuario); // Llama al método para registrar el usuario

                JOptionPane.showMessageDialog(null, "Usuario registrado: " + usuario.toString());

                // Limpiar campos después del registro
                txtNombreUsuario.setText("");
                txtContrasena.setText("");
                comboRol.setSelectedIndex(0); // Resetear a "Administrador"
            }

        });
        // Botón para iniciar sesión
        btnLogin.setBackground(buttonColorGRAY);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding en el botón
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
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegistrar);
        gbc.gridx = 0; // Columna
        gbc.gridy = 3; // Fila
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(buttonPanel, gbc);

        setVisible(true);
    }

    // Método para aplicar estilo a los JTextField
    private void styleTextField(JTextField textField) {
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.DARK_GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Borde gris
        textField.setPreferredSize(new Dimension(250, 30)); // Tamaño preferido
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Inicializa UsuarioServicios antes de crear RegistrarUsuario
            UsuarioServicios usuarioServicios = new UsuarioServicios();
            new RegistrarUsuario(usuarioServicios);
        });
    }
}
