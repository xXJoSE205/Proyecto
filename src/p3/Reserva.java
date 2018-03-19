/**
 * Esta clase contiene la información de una Reserva
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

import java.time.LocalDateTime;

public class Reserva {
    private LocalDateTime fechaInicio;

    public Reserva(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
}
