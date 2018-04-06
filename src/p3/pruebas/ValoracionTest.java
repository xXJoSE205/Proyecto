package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;
import p3.src.Valoracion;

import static org.junit.Assert.*;

public class ValoracionTest {

    /**
     * Valoracion general para el test
     */
    private Valoracion valoracion;
    /**
     * Demandante general para el test
     */
    private Demandante demandante;

    /**
     * Crea una valoracion para el test
     */
    @Before
    public void setUp(){
         demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345");
        valoracion = new Valoracion(demandante, 5);
    }

    /**
     * Test 1 del constructor, se espera un IllegalArgumentException
     * La puntuacion es mayor que 5
     */
    @Test (expected = IllegalArgumentException.class)
    public void constructor1(){
        System.out.println("Valoracion: test constructor1");
        Valoracion valoracion = new Valoracion(demandante, 7);
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El autor es NULL
     */
    @Test (expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Valoracion: test constructor2");
        Valoracion valoracion = new Valoracion(null, 5);
    }

    /**
     * Test de getPuntuacion, se comprueba que la puntuacion es igual a 5
     */
    @Test
    public void getPuntuacion() {
        System.out.println("Valoracion: test getPuntuacion");
        assertTrue(valoracion.getPuntuacion()==5);
    }

    /**
     * Test de setPuntuacion1, se comprueba que la nueva puntuacion es igual a 4
     */
    @Test
    public void setPuntuacion1() {
        System.out.println("Valoracion: test setPuntuacion1");
        valoracion.setPuntuacion(4);
        assertTrue(valoracion.getPuntuacion()==4);
    }

    /**
     * Test de setPuntuacion2, se espera IllegalArgumentException
     * La puntuacion es mayor que 5
     */
    @Test (expected = IllegalArgumentException.class)
    public void setPuntuacion2(){
        System.out.println("Valoracion: test setPuntuacion2");
        valoracion.setPuntuacion(7);
    }
}