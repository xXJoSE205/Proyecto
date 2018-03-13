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
