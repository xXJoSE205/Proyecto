/**
 * Esta clase contiene el test de la clase Cliente
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Cliente;
import p3.src.Demandante;

import static org.junit.Assert.*;

public class ClienteTest {
    /**
     * Cliente general para realizar el test
     * Se instanciara como Demandante, clase descendiente de Cliente
     * La clase Cliente es abstracta y no es instanciable
     */
    private Cliente cliente;

    /**
     * Crea un Cliente como Demandante para los test
     */
    @Before
    public void setUp(){
        cliente = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * La tarjerta es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Cliente: test constructor1");
        Cliente c1 = new Demandante("Tony","Stark","12345678Q","Contraseña",
                null );
    }

    /**
     * Test 2 del constructor, se espera un IllegalArgumentException
     * El numero de la tarjeta es invalido
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructor2(){
        System.out.println("Cliente: test constructor2");
        Cliente c1 = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "102345678901234");
    }

    /**
     * Test de getTarjeta, se comprueba que la tarjeta sea igual a "0123456789012345"
     */
    @Test
    public void getTarjeta() {
        System.out.println("Cliente: test getTarjeta");
        assertEquals(cliente.getTarjeta(), "0123456789012345");
    }

    /**
     * Test 1 de setTarjeta, se espera true, la tarjeta se modifica correctamente
     */
    @Test
    public void setTarjeta1(){
        System.out.println("Cliente: test setTarjeta1");
        assertTrue(cliente.setTarjeta("1111222233334444"));
    }

    /**
     * Test 2 de setTarjeta, se espera false, el numero de tarjeta es invalida
     */
    @Test
    public void setTarjeta2(){
        System.out.println("Cliente: test setTarjeta2");
        assertFalse(cliente.setTarjeta("111222333444"));
    }

    /**
     * Test 3 de setTarjeta, se espera un NullPointerException
     * la tarjeta es null
     */
    @Test(expected = NullPointerException.class)
    public void setTarjeta3(){
        System.out.println("Cliente: test setTarjeta3");
        cliente.setTarjeta(null);
    }
}