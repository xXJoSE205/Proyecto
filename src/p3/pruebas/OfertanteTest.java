/**
 * Esta clase contiene el test de la clase Ofertante
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Ofertante;

import static org.junit.Assert.*;

public class OfertanteTest {
    /** Ofertante general para realizar el test*/
    private Ofertante ofertante;

    /**
     * Crea un Ofertante para realizar los test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        ofertante = new Ofertante("Vic", "Rattlehead", "66666666D", "PeaceSells",
                "6666999966669999");
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El nombre es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Ofertante: test constructor1");
        Ofertante ofertante2 = new Ofertante(null, "Rattlehead", "66666666D",
                "PeaceSells", "6666999966669999");
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El apellidos es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Ofertante: test constructor2");
        Ofertante ofertante2 = new Ofertante("Vic", null, "66666666D", "PeaceSells",
                "6666999966669999");
    }

    /**
     * Test 3 del constructor, se espera un NullPointerException
     * El nif es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("Ofertante: test constructor3");
        Ofertante ofertante2 = new Ofertante("Vic", "Rattlehead", null, "PeaceSells",
                "6666999966669999");
    }

    /**
     * Test 4 del constructor, se espera un NullPointerException
     * La contraseña es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor4(){
        System.out.println("Ofertante: test constructor4");
        Ofertante ofertante2 = new Ofertante("Vic", "Rattlehead", "66666666D", null,
                "6666999966669999");
    }

    /**
     * Test 5 del constructor, se espera un NullPointerException
     * El nombre es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor5(){
        System.out.println("Ofertante: test constructor5");
        Ofertante ofertante2 = new Ofertante("Vic", "Rattlehead", "66666666D",
                "PeaceSells", null);
    }

    /**
     * Test de getCargo, se comprueba que el cargo del ofertante del setUp sea igual a 0
     */
    @Test
    public void getCargo() {
        System.out.println("Ofertante: test getCargo");
        assertTrue(ofertante.getCargo()==0);
    }

    /**
     * Test de setCargo, se comprueba que el nuevo cargo es igual a -15.8
     */
    @Test
    public void setCargo() {
        System.out.println("Ofertante: test setCargo");
        ofertante.setCargo(-15.8);
        assertTrue(ofertante.getCargo()==-15.8);
    }

    /**
     * Test de getModificaciones, se comprueba que las modificaciones del setUp son null
     */
    @Test
    public void getModificaciones() {
        System.out.println("Ofertante: test getModificaciones");
        assertNull(ofertante.getModificaciones());
    }

    /**
     * Test 1 de anadirModificaciones, se establecen unas modificaciones
     * Se comprueba que sean iguales a "Direccion inexistente"
     */
    @Test
    public void anadirModificaciones1() {
        System.out.println("Ofertante: test anadirModificaciones1");
        ofertante.anadirModificaciones("Direccion inexistente", false);
        assertEquals(ofertante.getModificaciones(), "Direccion inexistente");
    }

    /**
     * Test 2 de anadirModificaciones, se establecen unas modificaciones y luego se añaden otras
     * Se comprueba que sean iguales a "Direccion inexistente, precio bajo"
     */
    @Test
    public void anadirModificaciones2() {
        System.out.println("Ofertante: test anadirModificaciones2");
        ofertante.anadirModificaciones("Direccion inexistente", false);
        ofertante.anadirModificaciones(", precio bajo", true);
        assertEquals(ofertante.getModificaciones(), "Direccion inexistente, precio bajo");
    }

    /**
     * Test 3 de anadirModificaciones, se espera un NullPointerException
     * La cadena de modificaciones a añadir es null
     */
    @Test(expected = NullPointerException.class)
    public void anadirModificaciones3() {
        System.out.println("Ofertante: test anadirModificaciones3");
        ofertante.anadirModificaciones(null, false);
    }

    /**
     * Test de quitarModificaciones, se establecen unas modificaciones y luego se quitan
     * Se comprueba que la cadena de modificaciones al final sea null
     */
    @Test
    public void quitarModificaciones() {
        System.out.println("Ofertante: test quitarModificaciones");
        ofertante.anadirModificaciones("Direccion inexistente", false);
        ofertante.quitarModificaciones();
        assertNull(ofertante.getModificaciones());
    }
}