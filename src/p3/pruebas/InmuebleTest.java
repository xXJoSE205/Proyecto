package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Inmueble;
import p3.src.Oferta;
import p3.src.Ofertante;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class InmuebleTest {

    private Inmueble inmueble;
    private Ofertante ofertante;


    @Before
    public void setUp() throws Exception {
        ofertante = new Ofertante("Tony","Stark", "12345678P","IronMan","0123456789012345");
        inmueble = new Inmueble(5,2,20,"C/ del diamante 5",5, true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor1(){
        System.out.println("Inmueble: test constructor1");
        Inmueble inmueble1 = new Inmueble(0,2,20,"C/ del diamante 5", 5, true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor2(){
        System.out.println("Inmueble: test constructor2");
        Inmueble inmueble1 = new Inmueble(5,0,20,"C/ del diamante 5",5, true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor3(){
        System.out.println("Inmueble: test constructor3");
        Inmueble inmueble1 = new Inmueble(5,2,0,"C/ del diamante 5",5, true);
    }

    @Test (expected = NullPointerException.class)
    public void constructor4(){
        System.out.println("Inmueble: test constructor4");
        Inmueble inmueble1 = new Inmueble(5,2,20,null,5, true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor5(){
        System.out.println("Inmueble: test constructor5");
        Inmueble inmueble1 = new Inmueble(5,2,20,"C/ del diamante 5",-1, true);
    }

    @Test
    public void getnHabitaciones() {
        System.out.println("Inmueble: test getnHabitaciones");
        assertTrue(inmueble.getnHabitaciones()==5);
    }

    @Test
    public void getnBanos() {
        System.out.println("Inmueble: test getnBanos");
        assertTrue(inmueble.getnBanos()==2);
    }

    @Test
    public void getDimensiones() {
        System.out.println("Inmueble: test getDimensiones");
        assertTrue(inmueble.getDimensiones()==20);
    }

    @Test
    public void getDireccion() {
        System.out.println("Inmueble: test getDireccion");
        assertTrue(inmueble.getDireccion()=="C/ del diamante 5");
    }

    @Test
    public void getPlanta() {
        System.out.println("Inmueble: test getPlanta");
        assertTrue(inmueble.getPlanta()==-1);
    }

    @Test
    public void getAscensor() {
        System.out.println("Inmueble: test getAscensor");
        assertTrue(inmueble.getAscensor()==true);
    }

    @Test
    public void getOfertas() {
        System.out.println("Inmueble: test getOfertas");
        Oferta oferta = new Oferta(20.20, LocalDate.now(), LocalDate.MAX,true,50);
        inmueble.anadirOferta(oferta);
        assertNotNull(inmueble.getOfertas());
    }

    @Test
    public void anadirOferta1() {
        System.out.println("Inmueble: test anadirOferta1");
        Oferta oferta = new Oferta(20.20, LocalDate.now(), LocalDate.MAX,true,50);
        assertTrue(inmueble.anadirOferta(oferta));
    }

    @Test (expected = NullPointerException.class)
    public void anadirOferta2(){
        System.out.println("Inmueble: test anadirOferta2");
        inmueble.anadirOferta(null);
    }
}