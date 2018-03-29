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
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        cliente = new Demandante("Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
    }

    /**
     * Test del constructor, se espera un NullPointerException
     * La tarjerta es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor(){
        System.out.println("Cliente: test constructor");
        Cliente c1 = new Demandante("Tony","Stark","12345678Q","Contraseña",
                null );
    }

    /**
     * Test de getTarjeta, se comprueba que la tarjeta sea igual a "0123456789012345"
     */
    @Test
    public void getTarjeta() {
        System.out.println("Cliente: test getTarjeta");
        assertEquals(cliente.getTarjeta(), "0123456789012345");
    }
}