package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;

import static org.junit.Assert.*;

public class ClienteTest {
    /**
     * Se instancia como clase demandante que es una clase descendiente de Cliente, debido a que la clase Cliente es abstracta y no es instanciable
     */
    private Demandante demandante;

    @Before
    public void setUp() throws Exception {
        demandante = new Demandante("Tony","Stark","12345678Q","Contraseña","0123456789012345" );
    }

    @Test
    public void getTarjeta() {
        System.out.println("Cliente: test getTarjeta");
        assertTrue(demandante.getTarjeta()=="0123456789012345");
    }
}