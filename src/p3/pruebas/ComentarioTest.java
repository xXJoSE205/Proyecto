/**
 * Esta clase contiene el test de la clase Comentario
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Comentario;
import p3.src.Demandante;

import static org.junit.Assert.*;

public class ComentarioTest {
    /** Comentario general para realizar el test*/
    private Comentario comentario;
    /** Autor general del comentario*/
    private Demandante demandante;

    /**
     * Crea un Comentario y un Demandante como autor para realizar los test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
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
        Comentario comentario2 = new Comentario(null,"prueba");

    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El texto es null
     */
    @Test (expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Comentario: test constructor2");
        Comentario comentario2 = new Comentario(demandante,null);

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