package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.mvc.modelo.Inmueble;
import p3.mvc.modelo.Oferta;
import p3.mvc.modelo.Ofertante;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Esta clase contiene el test de la clase Inmueble
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class InmuebleTest {
    /** Inmueble general para realizar el test*/
    private Inmueble inmueble;
    /** Ofertante general para realizar el test*/
    private Ofertante ofertante;

    /**
     * Crea un Inmueble y un Ofertante como dueno para realizar los test
     */
    @Before
    public void setUp(){
        ofertante = new Ofertante("Tony","Stark", "12345678P","IronMan",
                "0123456789012345");
        inmueble = new Inmueble(5,2,150,"C/ del diamante 5",5,
                true, ofertante);
    }

    /**
     * Test 1 del constructor, se espera un IllegalArgumentException
     * El numero de habitaciones es menor que 1
     */
    @Test (expected = IllegalArgumentException.class)
    public void constructor1(){
        System.out.println("Inmueble: test constructor1");
        new Inmueble(0,2,150,"C/ del diamante 5",
                5, true, ofertante);
    }

    /**
     * Test 2 del constructor, se espera un IllegalArgumentException
     * El numero de banos es menor que 1
     */
    @Test (expected = IllegalArgumentException.class)
    public void constructor2(){
        System.out.println("Inmueble: test constructor2");
        new Inmueble(5,0,15,"C/ del diamante 5",
                5, true, ofertante);
    }

    /**
     * Test 3 del constructor, se espera un IllegalArgumentException
     * Las dimensiones son menores que 1
     */
    @Test (expected = IllegalArgumentException.class)
    public void constructor3(){
        System.out.println("Inmueble: test constructor3");
        new Inmueble(5,2,0,"C/ del diamante 5",
                5, true, ofertante);
    }

    /**
     * Test 4 del constructor, se espera un NullPointerException
     * La direccion es null
     */
    @Test (expected = NullPointerException.class)
    public void constructor4(){
        System.out.println("Inmueble: test constructor4");
        new Inmueble(5,2,150,null,5,
                true, ofertante);
    }

    /**
     * Test 5 del constructor, se espera un IllegalArgumentException
     * El numero de la planta es menor que 0(bajo)
     */
    @Test (expected = IllegalArgumentException.class)
    public void constructor5(){
        System.out.println("Inmueble: test constructor5");
       new Inmueble(5,2,150,"C/ del diamante 5",
                -1, true, ofertante);
    }

    /**
     * Test 6 del constructor, se espera un NullPointerException
     * El dueno es null
     */
    @Test (expected = NullPointerException.class)
    public void constructor6(){
        System.out.println("Inmueble: test constructor6");
        new Inmueble(5,2,150,"C/ del diamante 5",
                5, true, null);
    }

    /**
     * Test de getNHabitaciones, se comprueba que el numero de habitaciones del setUp sea igual a 5
     */
    @Test
    public void getNHabitaciones() {
        System.out.println("Inmueble: test getNHabitaciones");
        assertTrue(inmueble.getnHabitaciones()==5);
    }

    /**
     * Test de getNBanos, se comprueba que el numero de banos del setUp sea igual a 2
     */
    @Test
    public void getNBanos() {
        System.out.println("Inmueble: test getNBanos");
        assertTrue(inmueble.getnBanos()==2);
    }

    /**
     * Test de getDimensiones, se comprueba que las dimensiones del setUp sean iguales a 150 m2
     */
    @Test
    public void getDimensiones() {
        System.out.println("Inmueble: test getDimensiones");
        assertTrue(inmueble.getDimensiones()==150);
    }

    /**
     * Test de getDireccion, se comprueba que la direccion del setUp sea igual a "C/ del diamante 5"
     */
    @Test
    public void getDireccion() {
        System.out.println("Inmueble: test getDireccion");
        assertEquals(inmueble.getDireccion(),"C/ del diamante 5");
    }

    /**
     * Test de getPlanta, se comprueba que la planta del setUp sea igual a 5
     */
    @Test
    public void getPlanta() {
        System.out.println("Inmueble: test getPlanta");
        assertTrue(inmueble.getPlanta()==5);
    }

    /**
     * Test de getAscensor, se comprueba que el inmueble del setUp tiene ascensor (true)
     */
    @Test
    public void getAscensor() {
        System.out.println("Inmueble: test getAscensor");
        assertTrue(inmueble.getAscensor());
    }

    /**
     * Test de getOfertas, se anade una Oferta al Inmueble y se comprueba que la lista de ofertas no es null
     */
    @Test
    public void getOfertas() {
        System.out.println("Inmueble: test getOfertas");
        Oferta oferta = new Oferta(20.20, LocalDate.now(), LocalDate.MAX,true,50,inmueble);
        inmueble.anadirOferta(oferta);
        assertNotNull(inmueble.getOfertas());
    }

    /**
     * Test 1 de anadirOferta, se espera true, se ha anadido la oferta correctamente
     */
    @Test
    public void anadirOferta1() {
        System.out.println("Inmueble: test anadirOferta1");
        Oferta oferta = new Oferta(20.20, LocalDate.now(), LocalDate.MAX,true,50, inmueble);
        assertTrue(inmueble.anadirOferta(oferta));
    }

    /**
     * Test 2 de anadirOferta, se espera un NullPointerException
     * La oferta a anadir es null
     */
    @Test (expected = NullPointerException.class)
    public void anadirOferta2(){
        System.out.println("Inmueble: test anadirOferta2");
        inmueble.anadirOferta(null);
    }


}