/**
 * Esta enumeracion contiene los posibles estados de una oferta
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;


import java.io.Serializable;

public enum Estado implements Serializable{
    DISPONIBLE,
    NO_DISPONIBLE,
    RECHAZADO,
    PENDIENTE
}
