/**
 * Esta clase contiene la información de un Cliente
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

public abstract class Cliente extends UsuarioRegistrado{
    /** Tarjeta de credito*/
    private String tarjeta;

    /**
     * Constructor de Cliente, se llama al constructor de UsuarioRegistrado
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param nif Numero de Identificacion Fiscal
     * @param password Contraseña de acceso
     * @param tarjeta Cadena con la tarjeta de credito
     */
    public Cliente(String nombre, String apellidos, String nif, String password, String tarjeta) {
        super(nombre, apellidos, nif, password);
        this.tarjeta = tarjeta;
    }

    /**
     * Devuelve la tarjeta de credito del cliente
     *
     * @return Cadena con el numero de la tarjeta
     */
    public String getTarjeta() {
        return tarjeta;
    }
}
