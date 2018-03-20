/**
 * Esta clase contiene la información de un UsuarioRegistrado
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

public abstract class UsuarioRegistrado {
    private String nombre;
    private String apellidos;
    private String nif;
    private String password;
    private boolean logeado = false;

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

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }
}
