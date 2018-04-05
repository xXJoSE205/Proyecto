/**
 * Esta clase contiene la información de un Cliente
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.io.Serializable;

public abstract class Cliente extends UsuarioRegistrado implements Serializable {
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
     * @throws NullPointerException si la tarjeta es null
     * @throws IllegalArgumentException si la tarjeta no tiene 16 digitos
     */
    public Cliente(String nombre, String apellidos, String nif, String password, String tarjeta)
            throws NullPointerException, IllegalArgumentException{
        super(nombre, apellidos, nif, password);
        if(tarjeta==null){
            throw new NullPointerException("Tarjeta null");
        }
        if(tarjeta.length()!=16){
            throw new IllegalArgumentException("Numero de tarjeta invalido: "+tarjeta);
        }
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

    /**
     * Modifica la tarjeta del cliente
     *
     * @param tarjeta Cadena con el numero de tarjeta, 16 digitos
     * @return boolean, true si se modifica correctamente, false en caso contrario
     * @throws NullPointerException Si la tarjeta es null
     */
    public boolean setTarjeta(String tarjeta) throws NullPointerException{
        if(tarjeta==null){
            throw new NullPointerException("Tarjeta null");
        }
        if(tarjeta.length()!=16){
            return false;
        }
        this.tarjeta = tarjeta;
        return true;
    }
}
