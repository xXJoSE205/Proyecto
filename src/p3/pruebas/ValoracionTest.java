package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;
import p3.src.Valoracion;

import static org.junit.Assert.*;

public class ValoracionTest {

    private Valoracion valoracion;
    private Demandante demandante;
    @Before
    public void setUp() throws Exception {
         demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345");
        valoracion = new Valoracion(demandante, 5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor2(){
        System.out.println("Valoracion: test constructor2");
        Valoracion valoracion = new Valoracion(demandante, 7);
    }

    @Test (expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("Valoracion: test constructor3");
        Valoracion valoracion = new Valoracion(null, 5);
    }
    
    @Test
    public void getPuntuacion() {
        System.out.println("Valoracion: test getPuntuacion");
        assertTrue(valoracion.getPuntuacion()==5);
    }

    @Test
    public void setPuntuacion1() {
        System.out.println("Valoracion: test setPuntuacion1");
        valoracion.setPuntuacion(4);
        assertTrue(valoracion.getPuntuacion()==4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setPuntuacion2(){
        System.out.println("Valoracion: test setPuntuacion2");
        valoracion.setPuntuacion(7);
    }
}