
package IUDIGITAL;

public class Departamento {

    //atributos
    private String nombre;
    private String ubicacion;
    private String telefono;
    private String correo;
    private String jefe;
    private String extension;
    private String horario;
    private String descripcion;
    private String mision;
    private String vision;

    //constructor
    public Departamento(String nombre, String ubicacion, String telefono, String correo, String jefe, String extension, String horario, String descripcion, String mision, String vision) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.correo = correo;
        this.jefe = jefe;
        this.extension = extension;
        this.horario = horario;
        this.descripcion = descripcion;
        this.mision = mision;
        this.vision = vision;
    }

    //getter y setter para nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //getter y setter para ubicacion
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    //getter y setter para telefono
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //getter y setter para correo
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //getter y setter para jefe
    public String getJefe() {
        return jefe;
    }
    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    //getter y setter para extension
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }

    //getter y setter para horario
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    //getter y setter para descripcion
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //getter y setter para mision
    public String getMision() {
        return mision;
    }
    public void setMision(String mision) {
        this.mision = mision;
    }

    //getter y setter para vision
    public String getVision() {
        return vision;
    }
    public void setVision(String vision) {
        this.vision = vision;
    }

    //metodo para imprimir informacion del departamento
    public void imprimirInformacionDepartamento() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo: " + correo);
        System.out.println("Jefe: " + jefe);
        System.out.println("Extensión: " + extension);
        System.out.println("Horario: " + horario);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Misión: " + mision);
        System.out.println("Visión: " + vision);
    }
}
