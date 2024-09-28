package Servicios;

import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioServicios {
    private static List<Usuario> usuarios;
    private static final Scanner scanner = new Scanner(System.in);

    public UsuarioServicios() {
        usuarios = new ArrayList<>();
    }

    public static void registrarUsuario() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.println("Seleccione un rol:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");
        int rolOpcion;
        try {
            rolOpcion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Opción de rol inválida.");
        }

        if (rolOpcion != 1 && rolOpcion != 2) {
            System.out.println("Opción de rol inválida, registro fallido.");
            return;
        }

        String rol = (rolOpcion == 1) ? "Administrador" : "Empleado";

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, rol);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");
    }

    public static Usuario iniciarSesion() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = buscarUsuario(nombreUsuario, contrasena);

        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getNombreUsuario() + " (Rol: " + usuario.getRol() + ")");
            return usuario;
        } else {
            throw new SecurityException("Nombre de usuario o contraseña incorrectos.");
        }
    }

    private static Usuario buscarUsuario(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

    public void inicializarDatos() {
        usuarios.add(new Usuario("admin", "admin123", "Administrador"));
        usuarios.add(new Usuario("empleado1", "emp123", "Empleado"));
    }
}
