package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;

import static org.junit.Assert.*;

public class DemandanteTest {
    /**
     * Demandante para realizar los test
     */
    private Demandante demandante;

    /**
     * Crea un demnadante para realizar los test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        demandante= new Demandante( "Tony","Stark","12345678Q","Contraseña","0123456789012345" );


    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor1(){
        System.out.println("Demandante: Prueba constructor1");
         Demandante demandate2 = new Demandante(null,"Stark","12345678Q","Contraseña","0123456789012345" );
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor2(){
        System.out.println("Demandante: Prueba constructor2");
        Demandante demandate2 = new Demandante("Tony",null,"12345678Q","Contraseña","0123456789012345" );
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor3(){
        System.out.println("Demandante: Prueba constructor3");
        Demandante demandate2 = new Demandante("Tony","Stark",null,"Contraseña","0123456789012345" );
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor4(){
        System.out.println("Demandante: Prueba constructor4");
        Demandante demandate2 = new Demandante("Tony","Stark","12345678Q",null,"0123456789012345" );
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor5(){
        System.out.println("Demandante: Prueba constructor5");
        Demandante demandate2 = new Demandante("Tony","Stark","12345678Q","Contraseña",null );
    }

    @Test
    public void getDeuda() {
        System.out.println("Demandante: test getDeuda");
        assertTrue(demandante.getDeuda()==0);
    }

    @Test
    public void setDeuda() {
        System.out.println("Demandante: test setDeuda");
        demandante.setDeuda(20.20);
        assertTrue(demandante.getDeuda()==20.20);
    }

    @Test
    public void isBloqueado() {
        System.out.println("Demandante: test isBloquedao");
        assertFalse(demandante.isBloqueado());
    }

    @Test
    public void setBloqueado() {
        System.out.println("Demandante: test setBloqueado");
        demandante.setBloqueado(true);
        assertTrue(demandante.isBloqueado());
    }

    @Test
    public void isReservaActiva() {
        System.out.println("Demandante: test isReservaActiva");
        assertFalse(demandante.isReservaActiva());
    }

    @Test
    public void setReservaActiva() {
        System.out.println("Demandante: test setReservaActiva");
        demandante.setReservaActiva(true);
        assertTrue(demandante.isReservaActiva());
    }

    @Test
    public void desbloquearUsuario1() {
        System.out.println("Demandante: test desbloquearUsuario1");
        demandante.setBloqueado(true);
        assertTrue(demandante.desbloquearUsuario());
    }

    @Test
    public void desbloquearUsuario2() {
        System.out.println("Demandante: test desbloquearUsuario2");
        assertFalse(demandante.desbloquearUsuario());
    }
}