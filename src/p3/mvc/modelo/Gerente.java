package p3.mvc.modelo;

import java.io.Serializable;

/**
 * Esta clase contiene la informacion de un Gerente
 *
 * @author Jorge Mateo Segura y Jose Antonio Muñoz Ortega
 */
public class Gerente extends UsuarioRegistrado implements Serializable {

    /**
     * Constructor de Gerente, llama al constructor de UsuarioRegistrado
     *
     * @param nombre Nombre del gerente
     * @param apellidos Apellidos del gerente
     * @param nif Identificador especial del gerente
     * @param password Contrasena especial del gerente
     */
    public Gerente(String nombre, String apellidos, String nif, String password) throws NullPointerException{
        super(nombre, apellidos, nif, password);
    }
}
