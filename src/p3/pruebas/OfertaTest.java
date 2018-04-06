/**
 * Esta clase contiene el test de la clase Oferta
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class OfertaTest {
    /** Oferta general para realizar el test*/
    private Oferta oferta;
    /** Inmueble general para realizar el test*/
    private Inmueble inmueble;

    /**
     * Crea una oferta para realizar los test
     */
    @Before
    public void setUp(){
        Ofertante ofertante = new Ofertante( "Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
        inmueble = new Inmueble(5,2,150,"C/ del diamante 5",5,
                true, ofertante);
        oferta = new Oferta(200, LocalDate.MIN, LocalDate.MAX,true,50, inmueble);
    }

    /**
     * Test 1 del constructor, se espera un IllegalArgumentException
     * El precio es menor que 0
     */
    @Test (expected = IllegalArgumentException.class)
    public void constructor1(){
        System.out.println("Oferta: test constructor1");
        Oferta oferta1 = new Oferta(-1, LocalDate.now(), LocalDate.MAX,true,50, inmueble);
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * La fechaInicio es null
     */
    @Test (expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Oferta: test constructor2");
        Oferta oferta1 = new Oferta(200, null, LocalDate.MAX,true,50, inmueble);
    }

    /**
     * Test 3 del constructor, se espera un NullPointerException
     * La fechaFin es null
     */
    @Test (expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("Oferta: test constructor3");
        Oferta oferta1 = new Oferta(200, LocalDate.now(),null,true,50, inmueble);
    }

    /**
     * Test 4 del constructor, se espera un IllegalArgumentException
     * La fianza es menor que 0
     */
    @Test (expected = IllegalArgumentException.class)
    public void constructor4(){
        System.out.println("Oferta: test constructor4");
        Oferta oferta1 = new Oferta(200, LocalDate.now(), LocalDate.MAX,true,-1, inmueble);
    }

    /**
     * Test 4 del constructor, se espera un NullPointerException
     * El inmueble es null
     */
    @Test (expected = NullPointerException.class)
    public void constructor5(){
        System.out.println("Oferta: test constructor5");
        Oferta oferta1 = new Oferta(200, LocalDate.now(), LocalDate.MAX,true,50, null);
    }

    /**
     * Test de getPrecio, se comprueba que el precio del setUp es igual a 200
     */
    @Test
    public void getPrecio() {
        System.out.println("Oferta: test getPrecio");
        assertTrue(oferta.getPrecio()==200);
    }

    /**
     * Test 1 de setPrecio, se comprueba que el precio nuevo es igual a 500
     */
    @Test
    public void setPrecio1() {
        System.out.println("Oferta: test setPrecio1");
        oferta.setPrecio(500);
        assertTrue(oferta.getPrecio()==500);
    }

    /**
     * Test 2 de setPrecio, se espera un IllegalArgumentException
     * El precio nuevo es menor que 0
     */
    @Test (expected = IllegalArgumentException.class)
    public void setPrecio2() {
        System.out.println("Oferta: test setPrecio2");
        oferta.setPrecio(-1);
    }

    /**
     * Test de isReservado, se comprueba que la oferta del setUp no esta reservada
     */
    @Test
    public void isReservado() {
        System.out.println("Oferta: test isReservado");
        assertFalse(oferta.isReservado());
    }

    /**
     * Test de setReservado, se comprueba que la oferta se haya reservado correctamente
     */
    @Test
    public void setReservado() {
        System.out.println("Oferta: test setReservado");
        oferta.setReservado(true);
        assertTrue(oferta.isReservado());

    }

    /**
     * Test de getFechaInicio, se comprueba que la fecha de inicio del setUp es igual a LocalDate.MIN
     */
    @Test
    public void getFechaInicio() {
        System.out.println("Oferta: test getFechaInicio");
        assertEquals(oferta.getFechaInicio(), LocalDate.MIN);
    }

    /**
     * Test 1 de setFechaInicio, se comprueba que la nueva fecha de inicio sea igual a 2018-03-29
     */
    @Test
    public void setFechaInicio1() {
        System.out.println("Oferta: test setFechaInicio1");
        oferta.setFechaInicio(LocalDate.of(2018,3,29));
        assertEquals(oferta.getFechaInicio(), LocalDate.of(2018,3,29));
    }

    /**
     * Test 2 de setFechaInicio, se espera un NullPointerException
     * La nueva fecha de inicio es null
     */
    @Test (expected = NullPointerException.class)
    public void setFechaIncio2(){
        System.out.println("Oferta: test setFechaInicio2");
        oferta.setFechaInicio(null);
    }

    /**
     * Test de getFechaFin, se comprueba que la fecha final del setUp es igual a LocalDate.MAX
     */
    @Test
    public void getFechaFin() {
        System.out.println("Oferta: test getFechaFin");
        assertEquals(oferta.getFechaFin(), LocalDate.MAX);
    }

    /**
     * Test 1 de setFechaFin, se comprueba que la nueva fecha fin sea igual a 2018-03-29
     */
    @Test
    public void setFechaFin1() {
        System.out.println("Oferta: test setFechaFin1");
        oferta.setFechaFin(LocalDate.of(2018,3,29));
        assertEquals(oferta.getFechaFin(), LocalDate.of(2018, 3,29));
    }

    /**
     * Test 2 de setFechaFin, se espera un NullPointerException
     * La nueva fecha final es null
     */
    @Test (expected = NullPointerException.class)
    public void setFechaFin2(){
        System.out.println("Oferta: test setFechaFin2");
        oferta.setFechaFin(null);
    }

    /**
     * Test de isVacacional, se somprueba que la oferta del setUp es del tipo vacacional
     */
    @Test
    public void isVacacional() {
        System.out.println("Oferta: test isVacacional");
        assertTrue(oferta.isVacacional());
    }

    /**
     * Test de setVacaciona, se comprueba que el nuevo tipo no sea vacaional
     */
    @Test
    public void setVacaciona() {
        System.out.println("Oferta: test setVacacional");
        oferta.setVacacional(false);
        assertFalse(oferta.isVacacional());
    }

    /**
     * Test de getFianza, se comprueba que la fianza del setUp es igual a 50
     */
    @Test
    public void getFianza() {
        System.out.println("Oferta: test getFianza");
        assertTrue(oferta.getFianza()==50);
    }

    /**
     * Test 1 de setFianza, se comprueba que la nueva fianza sea igual a 70
     */
    @Test
    public void setFianza1() {
        System.out.println("Oferta: test setFianza1");
        oferta.setFianza(70);
        assertTrue(oferta.getFianza()==70);
    }

    /**
     * Test 2 de setFianza, se espera un IllegalArgumentException
     * La nueva fianza es menor que 0
     */
    @Test (expected = IllegalArgumentException.class)
    public void setFianza2() {
        System.out.println("Oferta: test setFianza2");
        oferta.setFianza(-1);

    }

    /**
     * Test de getEstado, se comprueba que el estado de la oferta del setUp sea igual a Estado.PENDIENTE
     */
    @Test
    public void getEstado() {
        System.out.println("Oferta: test getEstado");
        assertTrue(oferta.getEstado() == Estado.PENDIENTE);

    }

    /**
     * Test de setEstado, se comprueba que el nuevo estado de la oferta sea igual a Estado.DISPONIBLE
     */
    @Test
    public void setEstado() {
        System.out.println("Oferta: test setEstado");
        oferta.setEstado(Estado.DISPONIBLE);
        assertTrue(oferta.getEstado() == Estado.DISPONIBLE);
    }

    /**
     * Test de getResrva, se comprueba que la Reserva de la oferta del setUp es null
     */
    @Test
    public void getReserva() {
        System.out.println("Oferta: test getReserva");
        assertNull(oferta.getReserva());
    }

    /**
     * Test 1 de reservar, se comprueba que la oferta se ha reservado correctamente,
     * que la oferta esta reservada, y que la Reserva no es null
     */
    @Test
    public void reservar1() {
        System.out.println("Oferta: test reservar1");
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
        assertTrue(oferta.reservar(demandante));
        assertTrue(oferta.isReservado());
        assertNotNull(oferta.getReserva());
    }

    /**
     * Test 2 de reservar, se espera un false, el demandante ya tiene una reserva activa
     */
    @Test
    public void reservar2() {
        System.out.println("Oferta: test reservar2");
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
        demandante.setReservaActiva(true);
        assertFalse(oferta.reservar(demandante));
    }

    /**
     * Test 3 de reservar, se espera un NullPointerException
     * El demandante que reserva es null
     */
    @Test(expected = NullPointerException.class)
    public void reservar3() {
        System.out.println("Oferta: test reservar3");
        oferta.reservar(null);
    }

    /**
     * Test 1 de cancelarReserva, se espera true, se ha cancelado una reserva activa
     */
    @Test
    public void cancelarReserva1() {
        System.out.println("Oferta: test cancelarReserva1");
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
        oferta.reservar(demandante);
        assertTrue(oferta.cancelarReserva());
    }

    /**
     * Test 2 de cancelarReserva, se espera false, no habia ninguna reserva activa
     */
    @Test
    public void cancelarReserva2() {
        System.out.println("Oferta: test cancelarReserva2");
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
        assertFalse(oferta.cancelarReserva());
    }

    /**
     * Test de getInmueble, se comprueba que el inmueble no es null
     */
    @Test
    public void getInmueble() {
        System.out.println("Oferta: test getInmueble");
        assertNotNull(oferta.getInmueble());
    }

    /**
     * Test de aprobar, se comprueba que el nuevo estado de la oferta es DISPONIBLE
     */
    @Test
    public void aprobar() {
        System.out.println("Oferta: test aprobar");
        oferta.aprobar();
        assertTrue(oferta.getEstado()==Estado.DISPONIBLE);
    }

    /**
     * Test de rechazar, se comprueba que el nuevo estado de la oferta es RECHAZADO
     */
    @Test
    public void rechazar() {
        System.out.println("Oferta: test rechazar");
        oferta.rechazar();
        assertTrue(oferta.getEstado()==Estado.RECHAZADO);
    }
}