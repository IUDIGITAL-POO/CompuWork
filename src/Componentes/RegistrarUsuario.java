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

    // Constructor que recibe UsuarioServicios
    public RegistrarUsuario(UsuarioServicios usuarioServicios) {
        this.usuarioServicios = usuarioServicios; // Inicializar la instancia
        setTitle("Registrar Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        // Estilos generales
        Color backgroundColor = new Color(240, 240, 240);
        Color buttonColor = new Color(60, 120, 240);
        Color textColor = Color.DARK_GRAY;

        // Establecer color de fondo
        getContentPane().setBackground(backgroundColor);

        // Etiquetas y campos para ingresar datos
        JLabel labelNombreUsuario = new JLabel("Nombre de Usuario:");
        labelNombreUsuario.setForeground(textColor); // Cambiar color de texto
        txtNombreUsuario = new JTextField();
        styleTextField(txtNombreUsuario);

        JLabel labelContrasena = new JLabel("Contraseña:");
        labelContrasena.setForeground(textColor);
        txtContrasena = new JPasswordField();
        styleTextField(txtContrasena);

        JLabel labelRol = new JLabel("Rol:");
        labelRol.setForeground(textColor);
        String[] roles = {"Administrador", "Empleado"};
        comboRol = new JComboBox<>(roles);

        // Estilo para el comboBox
        comboRol.setBackground(Color.WHITE);
        comboRol.setForeground(textColor);

        // Botón para registrar el usuario
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(buttonColor);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding en el botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Asegúrate de obtener el texto del campo de texto
                String nombreUsuario = txtNombreUsuario.getText(); // txtNombreUsuario debe ser de tipo JTextField
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

        // Agregar los componentes a la ventana
        add(labelNombreUsuario);
        add(txtNombreUsuario);
        add(labelContrasena);
        add(txtContrasena);
        add(labelRol);
        add(comboRol);
        add(new JLabel()); // Espacio vacío
        add(btnRegistrar);

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
