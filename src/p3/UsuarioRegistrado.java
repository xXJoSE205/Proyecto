package p3;

public abstract class UsuarioRegistrado {
    private String nombre;
    private String apellidos;
    private String nif;
    private String password;

    public UsuarioRegistrado(String nombre, String apellidos, String nif, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNif() {
        return nif;
    }

    public String getPassword() {
        return password;
    }
}
