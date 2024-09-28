package Servicios;

import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioServicios {
    private List<Usuario> usuarios;
    private final Scanner scanner = new Scanner(System.in);

    public UsuarioServicios() {
        usuarios = new ArrayList<>();
        inicializarDatos(); // Inicializar datos de ejemplo en el constructor
    }

    public void registrarUsuario(Usuario nuevoUsuario) {
        if (nuevoUsuario != null) {
            usuarios.add(nuevoUsuario);
            System.out.println("Usuario registrado exitosamente: " + nuevoUsuario);
        } else {
            System.out.println("El usuario no puede ser nulo.");
        }
    }
    private String seleccionarRol() {
        System.out.println("Seleccione un rol:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");
        int rolOpcion;

        try {
            rolOpcion = Integer.parseInt(scanner.nextLine());
            if (rolOpcion == 1) {
                return "Administrador";
            } else if (rolOpcion == 2) {
                return "Empleado";
            } else {
                return null; // Opción inválida
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            return null; // Opción inválida
        }
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return usuario; // Devuelve el usuario si coincide el nombreUsuario y contrasena
            }
        }
        System.out.println("Nombre de usuario o contraseña incorrectos.");
        return null; // Retorna null si no hay coincidencia
    }

    private void inicializarDatos() {
        usuarios.add(new Usuario("admin", "admin123", "Administrador"));
        usuarios.add(new Usuario("empleado1", "emp123", "Empleado"));
    }
}
