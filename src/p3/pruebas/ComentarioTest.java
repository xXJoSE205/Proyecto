package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.mvc.modelo.Comentario;
import p3.mvc.modelo.Demandante;

import static org.junit.Assert.*;

/**
 * Esta clase contiene el test de la clase Comentario
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class ComentarioTest {
    /** Comentario general para realizar el test*/
    private Comentario comentario;
    /** Autor general del comentario*/
    private Demandante demandante;

    /**
     * Crea un Comentario y un Demandante como autor para realizar los test
     */
    @Before
    public void setUp(){
        demandante = new Demandante("Tony","Stark","12345678Q","Contrasena",
                "0123456789012345" );
        comentario = new Comentario(demandante, "prueba");
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El autor es null
     */
    @Test (expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Comentario: test constructor1");
        new Comentario(null,"prueba");
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El texto es null
     */
    @Test (expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Comentario: test constructor2");
        new Comentario(demandante,null);
    }

    /**
     * Test de getTexto, se comprueba que el texto del comnetario del setUp sea igual a "prueba"
     */
    @Test
    public void getTexto() {
        System.out.println("Comentario: test getTexto");
        assertEquals(comentario.getTexto(), "prueba");
    }
}