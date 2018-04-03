package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Comentario;
import p3.src.Demandante;
import p3.src.Opinion;

import static org.junit.Assert.*;

/**
 * Hacerlo
 */
public class OpinionTest {

    private Comentario comentario;
    private Opinion opinion;

    @Before
    public void setUp() throws Exception {
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345");
        comentario = new Comentario(demandante,"prueba");


    }

    @Test (expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Comentario: test constructor1");
        Comentario comentario1 = new Comentario(null,"prueba");
    }
    @Test
    public void getAutor() {
        System.out.println("Comentario: test getAutor");
        assertNotNull(comentario.getAutor());
    }

    @Test
    public void anadirComentario() {
    }
}