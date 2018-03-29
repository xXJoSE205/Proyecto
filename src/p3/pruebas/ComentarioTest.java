package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Comentario;
import p3.src.Demandante;

import static org.junit.Assert.*;

public class ComentarioTest {

    private Comentario comentario;
    private Demandante demandante;

    @Before
    public void setUp() throws Exception {
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña","0123456789012345" );
        comentario = new Comentario(demandante, "prueba");
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor1(){
        System.out.println("Comentario: test constructor1");
        Comentario comentario2 = new Comentario(null,"prueba");

    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor2(){
        System.out.println("Comentario: test constructor2");
        Comentario comentario2 = new Comentario(demandante,null);

    }
    @Test
    public void getTexto() {
        System.out.println("Comentario: test getTexto");
        assertTrue(comentario.getTexto()=="prueba");
    }
}