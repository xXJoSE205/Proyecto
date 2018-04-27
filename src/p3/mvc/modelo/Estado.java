package p3.mvc.modelo;

import java.io.Serializable;

/**
 * Esta enumeracion contiene los posibles estados de una oferta
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public enum Estado implements Serializable{
    DISPONIBLE,
    NO_DISPONIBLE,
    RECHAZADO,
    PENDIENTE
}
