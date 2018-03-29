/**
 * Esta clase contiene el test de la clase Demandante
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;

import static org.junit.Assert.*;

public class DemandanteTest {
    /**
     * Demandante general para realizar el test
     */
    private Demandante demandante;

    /**
     * Crea un demnadante para realizar los test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        demandante = new Demandante( "Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El nombre es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Demandante: test constructor1");
        Demandante demandate2 = new Demandante(null,"Stark","12345678Q","Contraseña",
                "0123456789012345" );
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El apellidos es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Demandante: test constructor2");
        Demandante demandate2 = new Demandante("Tony",null,"12345678Q","Contraseña",
                "0123456789012345" );
    }

    /**
     * Test 3 del constructor, se espera un NullPointerException
     * El nif es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("Demandante: test constructor3");
        Demandante demandate2 = new Demandante("Tony","Stark",null,"Contraseña",
                "0123456789012345" );
    }

    /**
     * Test 4 del constructor, se espera un NullPointerException
     * La contraseña es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor4(){
        System.out.println("Demandante: test constructor4");
        Demandante demandate2 = new Demandante("Tony","Stark","12345678Q",null,
                "0123456789012345" );
    }

    /**
     * Test 5 del constructor, se espera un NullPointerException
     * La tarjeta es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor5(){
        System.out.println("Demandante: test constructor5");
        Demandante demandate2 = new Demandante("Tony","Stark","12345678Q","Contraseña",
                null );
    }

    /**
     * Test de getDeuda, se comprueba que la deuda del demandante del setUp es igual a 0
     */
    @Test
    public void getDeuda() {
        System.out.println("Demandante: test getDeuda");
        assertTrue(demandante.getDeuda()==0);
    }

    /**
     * Test de setDeuda, se comprueba que la nueva deuda es igual a 20.20
     */
    @Test
    public void setDeuda() {
        System.out.println("Demandante: test setDeuda");
        demandante.setDeuda(20.20);
        assertTrue(demandante.getDeuda()==20.20);
    }

    /**
     * Test de isBloqueado, se comprueba que el demandante del setUp no esta bloqueado, bloqueado = false
     */
    @Test
    public void isBloqueado() {
        System.out.println("Demandante: test isBloquedao");
        assertFalse(demandante.isBloqueado());
    }

    /**
     * Test de sewtBloqueado, se comprueba que el nuevo estado del demandante es bloqueado, bloqueado = true
     */
    @Test
    public void setBloqueado() {
        System.out.println("Demandante: test setBloqueado");
        demandante.setBloqueado(true);
        assertTrue(demandante.isBloqueado());
    }

    /**
     * Test de isReservaActiva, se comprueba que el demandante del setUp no tiene ninguna reserva activa
     */
    @Test
    public void isReservaActiva() {
        System.out.println("Demandante: test isReservaActiva");
        assertFalse(demandante.isReservaActiva());
    }

    /**
     * Test de setReservaActiva, se comprueba que el demandante tiene ahora una reserva activa
     */
    @Test
    public void setReservaActiva() {
        System.out.println("Demandante: test setReservaActiva");
        demandante.setReservaActiva(true);
        assertTrue(demandante.isReservaActiva());
    }

    /**
     * Test 1 de desbloquearUsuario, se espera true, el demandante ha sido desbloqueado
     */
    @Test
    public void desbloquearUsuario1() {
        System.out.println("Demandante: test desbloquearUsuario1");
        demandante.setBloqueado(true);
        assertTrue(demandante.desbloquearUsuario());
    }

    /**
     * Test 2 de desbloquearUsuario, se espera false, el demandante no estaba bloqueado
     */
    @Test
    public void desbloquearUsuario2() {
        System.out.println("Demandante: test desbloquearUsuario2");
        assertFalse(demandante.desbloquearUsuario());
    }
}