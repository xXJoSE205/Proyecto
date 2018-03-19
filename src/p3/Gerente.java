/**
 * Esta clase contiene la información de un Gerente
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

public class Gerente extends UsuarioRegistrado{

    /**
     * Constructor de Gerente, llama al constructor de UsuarioRegistrado
     *
     * @param nombre Nombre del gerente
     * @param apellidos Apellidos del gerente
     * @param nif Identificador especial del gerente
     * @param password Contraseña especial del gerente
     */
    public Gerente(String nombre, String apellidos, String nif, String password) {
        super(nombre, apellidos, nif, password);
    }
}
