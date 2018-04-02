package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;

import static org.junit.Assert.*;

public class UsuarioRegistradoTest {

    /**
     * Cliente general para realizar el test
     * Se instanciara como Demandante, clase descendiente de Cliente, clase descendiente de UsuarioRegistradi
     * La clase UsuarioRegistrado es abstracta y no es instanciable
     */

    private Demandante demandante;

    /**
     * Crea un UsuarioRegistrado como demandante para los test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
            "0123456789012345");

    }

    /**
     * Test de getNombre, se comprueba que el nombre sea igual a "Tony"
     */

    @Test
    public void getNombre() {
        System.out.println("UsuarioRegistrado: test getNombre");
        assertTrue(demandante.getNombre()=="Tony");
    }

    /**
     * Test de getApellidos, se comprueba que el apellido sea igual a "Stark"
     */
    @Test
    public void getApellidos() {
        System.out.println("UsuarioRegistrado: test getApellidos");
        assertTrue(demandante.getApellidos()=="Stark");
    }

    /**
     * Test de getNif, se comprueba que el NIF sea igual a "12345678Q"
     */
    @Test
    public void getNif() {
        System.out.println("UsuarioRegistrado: test getNif");
        assertTrue(demandante.getNif()=="12345678Q");
    }

    /**
     * Test de getPassword, se comprueba que la contraseña sea igual a "Contraseña"
     */
    @Test
    public void getPassword() {
        System.out.println("UsuarioRegistrado: test getPassword");
        assertTrue(demandante.getPassword()=="Contraseña");
    }

    /**
     * Test de isLogeado, se comprueba que el UsuarioRegistrado del setUp no esta logeado
     */
    @Test
    public void isLogeado() {
        System.out.println("UsuarioRegistrado: test isLogeado");
        assertFalse(demandante.isLogeado());
    }

    /**
     * Test de setLogeado, se comprueba que el UsuarioRegistrado ahora esta logeado
     */
    @Test
    public void setLogeado() {
        System.out.println("UsuarioRegistrado: test setLogeado");
        demandante.setLogeado(true);
        assertTrue(demandante.isLogeado());
    }
}