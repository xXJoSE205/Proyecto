/**
 * Esta clase contiene el test de la clase Reserva
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.*;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReservaTest {
    /** Reserva genereal para realizar el test*/
    private Reserva reserva;
    /** Demandante general para realizar el test*/
    private Demandante demandante;
    /** Ofertante general para realizar el test*/
    private Ofertante ofertante;
    /** Inmueble general para realizar el test*/
    private Inmueble inmueble;
    /** Oferta general para realizar el test*/
    private Oferta oferta;

    /**
     * Crea una reserva para realizar los test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        ofertante = new Ofertante("Vic", "Rattlehead", "66666666D", "PeaceSells",
                "6666999966669999");
        inmueble = new Inmueble(3,1,80,"Paseo Castellana",2,
                false,ofertante);
        oferta = new Oferta(1200,LocalDate.now(),LocalDate.now().plusMonths(4),false,
                200,inmueble);
        demandante = new Demandante("Bruce", "Wayne", "09128734D", "murciegalo",
                "1920384756028374");
        reserva = new Reserva(demandante, oferta);
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El demandante es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Reserva: test constructor1");
        Reserva reserva2 = new Reserva(null, oferta);
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * La oferta es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Reserva: test constructor2");
        Reserva reserva2 = new Reserva(demandante, null);
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