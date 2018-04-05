/**
 * Esta clase contiene la información de un UsuarioRegistrado
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.io.Serializable;

public abstract class UsuarioRegistrado implements Serializable {
    /** Nombre del usuario*/
    private final String nombre;
    /** Apellidos del usuario*/
    private final String apellidos;
    /** Nimero de Identificacion Fiscal*/
    private final String nif;
    /** Contraseña de acceso*/
    private final String password;
    /** Si el usuario re ha logeado o no*/
    private boolean logeado = false;

    /**
     * Constructor de UsuarioRegistrado
     *
     * @param nombre Nombre del usuario
     * @param apellidos Apellidos del usuario
     * @param nif Numero de Identificacion Fiscal
     * @param password Constraseña de acceso
     * @throws NullPointerException si algun parametro es null
     */
    public UsuarioRegistrado(String nombre, String apellidos, String nif, String password) throws NullPointerException,
            IllegalArgumentException{
        if(nombre==null || apellidos==null || nif==null || password==null){
            throw new NullPointerException("Nombre, appelidos, nif o contraseña null");
        }
        if(nif.length()!=9){
            throw new IllegalArgumentException("NIF invalido: "+nif);
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

    /**
     * Pasa toda la informacion del usuario en una cadena
     *
     * @return Cadena con toda la informacion del usuario
     */
    @Override
    public String toString() {
        return "\tNombre: "+nombre+"\n\tApellidos: "+apellidos+"\n\tNIF: "+nif+"\n\tPassword: "+password;
    }
}
