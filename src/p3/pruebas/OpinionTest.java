package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.mvc.modelo.Comentario;
import p3.mvc.modelo.Demandante;
import p3.mvc.modelo.Opinion;

import static org.junit.Assert.*;

/**
 * Esta clase contiene el test de la clase Opinion
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class OpinionTest {
    /** Opinion general para realizar el test*/
    private Opinion opinion;
    /** Demandante general para realizar el test*/
    private Demandante demandante;

    /**
     * Crea una opinion para realizar los test
     */
    @Before
    public void setUp(){
        demandante = new Demandante("Tony","Stark","12345678Q","Contrasena",
                "0123456789012345");
        opinion = new Comentario(demandante,"prueba");
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El autor es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Opinion: test constructor1");
        new Comentario(null,"prueba");
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El texto es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor2() {
        System.out.println("Opinion: test constructor2");
        new Comentario(demandante,null);
    }

    /**
     * Test de getAutor, se comprueba que el autor de la opinion no es null
     */
    @Test
    public void getAutor() {
        System.out.println("Opinion: test getAutor");
        assertNotNull(opinion.getAutor());
    }

    /**
     * Test 1 de anadirComentario, se espera true, se ha anadido correctamente
     */
    @Test
    public void anadirComentario1() {
        System.out.println("Opinion: test anadirComentario1");
        Comentario comen = new Comentario(demandante, "prueba2");
        assertTrue(opinion.anadirComentario(comen));
    }

    /**
     * Test 2 de anadirComentario, se esperea un NullPointerException
     * El comentario a annadir es null
     */
    @Test(expected = NullPointerException.class)
    public void anadirComentario2() {
        System.out.println("Opinion: test anadirComentario1");
        assertTrue(opinion.anadirComentario(null));
    }
}