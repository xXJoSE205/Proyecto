/**
 * Esta clase contiene la información de un UsuarioRegistrado
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

public abstract class UsuarioRegistrado {
    /** Nombre del usuario*/
    private String nombre;
    /** Apellidos del usuario*/
    private String apellidos;
    /** Nimero de Identificacion Fiscal*/
    private String nif;
    /** Contraseñña de acceso*/
    private String password;
    /** Si el usuario re ha logeado o no*/
    private boolean logeado = false;

    /**
     * Constructor de UsuarioRegistrado
     *
     * @param nombre Nombre del usuario
     * @param apellidos Apellidos del usuario
     * @param nif Numero de Identificacion Fiscal
     * @param password Constraseña de acceso
     */
    public UsuarioRegistrado(String nombre, String apellidos, String nif, String password) {
        if(nombre==null || apellidos==null || nif==null || password==null){
            throw new NullPointerException("Nombre, appelidos, nif o contraseña nulos");
        }
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.password = password;
    }

    /**
     * Devuelve el nombre del usuario
     *
     * @return Cadena con el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve los apellidos del usuario
     *
     * @return Cadena con los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Devuelve el NIF del usuario
     *
     * @return Cadena con el NIF
     */
    public String getNif() {
        return nif;
    }

    /**
     * Devuelve la contraseña del usuario
     *
     * @return Cadena con la contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve true si el usuario esta logeado
     *
     * @return boolean, true si esta logeado, false en caso contrario
     */
    public boolean isLogeado() {
        return logeado;
    }

    /**
     * Modifica el estado del usuario
     *
     * @param logeado true si se va a logear, fals si se va a desconectar
     */
    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }
}
