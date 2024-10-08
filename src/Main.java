import Componentes.Home;
import Servicios.UsuarioServicios; // Asegúrate de que la ruta del paquete sea correcta

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de GestionServicios si es necesario
        UsuarioServicios usuarioServicios = new UsuarioServicios();
        // Crear la ventana principal (Home)
        Home home = new Home(usuarioServicios);
        // Hacer visible la ventana de Home
        home.setVisible(true);
    }
}
