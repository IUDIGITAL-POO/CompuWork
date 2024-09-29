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
    private Color buttonColor = Color.BLUE; // Color del botón

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

        // Botón para registrar el usuario
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(buttonColor);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding en el botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el texto del campo de texto
                String nombreUsuario = txtNombreUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());
                String rol = (String) comboRol.getSelectedItem();

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

        gbc.gridx = 0; // Columna
        gbc.gridy = 3; // Fila
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(btnRegistrar, gbc);

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
