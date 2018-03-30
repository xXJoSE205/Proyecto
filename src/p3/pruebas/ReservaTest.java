/**
 * Esta clase contiene el test de la clase Reserva
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;
import p3.src.Reserva;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReservaTest {
    /** Reserva genereal para realizar el test*/
    private Reserva reserva;
    /** Demandante general para realizar el test*/
    private Demandante demandante;

    /**
     * Crea una reserva para realizar los test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        demandante = new Demandante("Bruce", "Wayne", "09128734D", "murciegalo",
                "1920384756028374");
        reserva = new Reserva(demandante);
    }

    /**
     * Test del constructor, se espera un NullPointerException
     * El demandante es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor(){
        System.out.println("Reserva: test constructor");
        Reserva reserva2 = new Reserva(null);
    }

    /**
     * Test de getFechaInicio, se comprueba que la fecha de la reserva del setUp sea igual a LocalDate.now()
     */
    @Test
    public void getFechaInicio() {
        System.out.println("Reserva: test getFechaInicio");
        assertEquals(reserva.getFechaInicio(), LocalDate.now());
    }

    /**
     * Test de getUsuario, se comprueba que el usuario es el mismo que el del setUp
     */
    @Test
    public void getUsuario() {
        System.out.println("Reerva: test getUsuario");
        assertEquals(reserva.getUsuario(), demandante);
    }
}