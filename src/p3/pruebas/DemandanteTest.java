/**
 * Esta clase contiene el test de la clase Demandante
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DemandanteTest {
    /** Demandante general para realizar el test*/
    private Demandante demandante;

    /**
     * Crea un demnadante para realizar los test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        demandante = new Demandante( "Tony","Stark","12345678Q","Contraseña",
                "1023456789012345" );
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El nombre es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Demandante: test constructor1");
        Demandante demandate2 = new Demandante(null,"Stark","12345678Q","Contraseña",
                "1023456789012345" );
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El apellidos es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Demandante: test constructor2");
        Demandante demandate2 = new Demandante("Tony",null,"12345678Q","Contraseña",
                "1023456789012345" );
    }

    /**
     * Test 3 del constructor, se espera un NullPointerException
     * El nif es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("Demandante: test constructor3");
        Demandante demandate2 = new Demandante("Tony","Stark",null,"Contraseña",
                "1023456789012345" );
    }

    /**
     * Test 4 del constructor, se espera un NullPointerException
     * La contraseña es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor4(){
        System.out.println("Demandante: test constructor4");
        Demandante demandate2 = new Demandante("Tony","Stark","12345678Q",null,
                "1023456789012345");
    }

    /**
     * Test 5 del constructor, se espera un NullPointerException
     * La tarjeta es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor5(){
        System.out.println("Demandante: test constructor5");
        Demandante demandate2 = new Demandante("Tony","Stark","12345678Q","Contraseña",
                null );
    }

    /**
     * Test 6 del constructor, se espera un IllegalArgumentException
     * El numero de la tarjeta es invalido
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructor6(){
        System.out.println("Demandante: test constructor6");
        Demandante demandate2 = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "102345678901234");
    }

    /**
     * Test de isBloqueado, se comprueba que el demandante del setUp no esta bloqueado, bloqueado = false
     */
    @Test
    public void isBloqueado() {
        System.out.println("Demandante: test isBloquedao");
        assertFalse(demandante.isBloqueado());
    }

    /**
     * Test 1 de Bloquear, se espera true, el demandante ha sido bloqueado
     */
    @Test
    public void bloquear1() {
        System.out.println("Demandante: test setBloqueado1");
        assertTrue(demandante.bloquear());
    }

    /**
     * Test 2 de Bloquear, se espera false, el demandante ya estaba bloqueado
     */
    @Test
    public void bloquear2() {
        System.out.println("Demandante: test setBloqueado1");
        demandante.bloquear();
        assertFalse(demandante.bloquear());
    }

    /**
     * Test de isReservaActiva, se comprueba que el demandante del setUp no tiene ninguna reserva activa
     */
    @Test
    public void isReservaActiva() {
        System.out.println("Demandante: test isReservaActiva");
        assertFalse(demandante.isReservaActiva());
    }

    /**
     * Test de setReservaActiva, se comprueba que el demandante tiene ahora una reserva activa
     */
    @Test
    public void setReservaActiva() {
        System.out.println("Demandante: test setReservaActiva");
        demandante.setReservaActiva(true);
        assertTrue(demandante.isReservaActiva());
    }

    /**
     * Test 1 de desbloquear, se espera true, el demandante ha sido desbloqueado
     */
    @Test
    public void desbloquear1() {
        System.out.println("Demandante: test desbloquear1");
        demandante.bloquear();
        assertTrue(demandante.desbloquear());
    }

    /**
     * Test 2 de desbloquear, se espera false, el demandante no estaba bloqueado
     */
    @Test
    public void desbloquear2() {
        System.out.println("Demandante: test desbloquear2");
        assertFalse(demandante.desbloquear());
    }

    /**
     * Test de getReserva, se asegura que el metodo devuelve algo distinto de NULL
     */
    @Test
    public void getReserva(){
        System.out.println("Demandante: test getReserva");

        Ofertante ofertante = new Ofertante("Natasha","Romanov","78965412A","ViudaNegra","4849849848948445");
        Inmueble inmueble = new Inmueble(5,2,500,"C/Hola",7,true,ofertante);
        Oferta oferta = new Oferta(500, LocalDate.now(),LocalDate.MAX,true,50,inmueble);
        Reserva r = new Reserva(demandante, oferta);
        demandante.setReserva(r);
        assertNotNull(demandante.getReserva());
    }

    /**
     * Test de setReserva1, se comprueba que el usuario ahora tiene una reserva activa
     */
    @Test
    public void setReserva1(){
        System.out.println("Demandante: test setReserva1");
        Ofertante ofertante = new Ofertante("Natasha","Romanov","78965412A","ViudaNegra","4849849848948445");
        Inmueble inmueble = new Inmueble(5,2,500,"C/Hola",7,true,ofertante);
        Oferta oferta = new Oferta(500, LocalDate.now(),LocalDate.MAX,true,50,inmueble);
        Reserva r = new Reserva(demandante,oferta);
        demandante.setReserva(r);
        assertNotNull(demandante.getReserva());

    }

    /**
     * Test de setReserva2, se espera NullPointerException
     */
    @Test (expected = NullPointerException.class)
    public void setReserva2(){
        System.out.println("Demandante: test setReserva2");
        Ofertante ofertante = new Ofertante("Natasha","Romanov","78965412A","ViudaNegra","4849849848948445");
        Inmueble inmueble = new Inmueble(5,2,500,"C/Hola",7,true,ofertante);
        Oferta oferta = new Oferta(500, LocalDate.now(),LocalDate.MAX,true,50,inmueble);
        demandante.setReserva(null);
    }

}