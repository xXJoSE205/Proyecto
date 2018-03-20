/**
 * Esta clase contiene la información de una Reserva
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

import java.time.LocalDate;

public class Reserva {
    private LocalDate fechaInicio;

    public Reserva(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
}
