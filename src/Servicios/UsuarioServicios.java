package Servicios;

import Modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioServicios {
    private List<Usuario> usuarios;
    private final Scanner scanner = new Scanner(System.in);
    private final String archivoUsuarios = "usuarios.txt";  // Nombre del archivo

    public UsuarioServicios() {
        usuarios = new ArrayList<>();
        leerUsuariosDesdeArchivo();  // Leer usuarios del archivo al iniciar el servicio
    }

    public void registrarUsuario(Usuario nuevoUsuario) {
        if (nuevoUsuario != null) {
            usuarios.add(nuevoUsuario);
            escribirUsuarioEnArchivo(nuevoUsuario);  // Guardar usuario en archivo
            System.out.println("Usuario registrado exitosamente: " + nuevoUsuario);
        } else {
            System.out.println("El usuario no puede ser nulo.");
        }
    }

    private void escribirUsuarioEnArchivo(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            writer.write(usuario.getNombreUsuario() + "," + usuario.getContrasena() + "," + usuario.getRol());
            writer.newLine();  // Añadir nueva línea después de cada usuario
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario en el archivo: " + e.getMessage());
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

    private void leerUsuariosDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");  // Separar por comas
                if (datos.length == 3) {
                    String nombreUsuario = datos[0];
                    String contrasena = datos[1];
                    String rol = datos[2];
                    usuarios.add(new Usuario(nombreUsuario, contrasena, rol));  // Agregar usuario a la lista
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo, se creará uno nuevo al registrar un usuario.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
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

    public boolean existeUsuario(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                return true; // El usuario ya existe
            }
        }
        return false; // El usuario no existe
    }
}
