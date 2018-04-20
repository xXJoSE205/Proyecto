package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;

import static org.junit.Assert.*;

/**
 * Esta clase contiene el test de la clase UsuarioRegistrado
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class UsuarioRegistradoTest {

    /**
     * Cliente general para realizar el test
     * Se instanciara como Demandante, clase descendiente de Cliente, clase descendiente de UsuarioRegistradi
     * La clase UsuarioRegistrado es abstracta y no es instanciable
     */
    private Demandante demandante;

    /**
     * Crea un UsuarioRegistrado como demandante para los test
     */
    @Before
    public void setUp(){
        demandante = new Demandante("Tony","Stark","12345678Q","Contrasena",
            "0123456789012345");

    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El nombre es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("UsuarioRegistrado: test constructor1");
        new Demandante(null,"Stark","12345678Q","Contrasena",
                "1023456789012345");
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El apellidos es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("UsuarioRegistrado: test constructor2");
        new Demandante("Tony",null,"12345678Q","Contrasena",
                "1023456789012345");
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El nif es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("UsuarioRegistrado: test constructor3");
        new Demandante("Tony","Stark",null,"Contrasena",
                "1023456789012345");
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * La contrasena es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor4(){
        System.out.println("UsuarioRegistrado: test constructor4");
       new Demandante("Tony","Stark","12345678Q",null,
                "1023456789012345");
    }

    /**
     * Test 1 del constructor, se espera un IllegalArgumentException
     * El nif no es valido
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructor5(){
        System.out.println("UsuarioRegistrado: test constructor5");
        new Demandante("Tony","Stark","123458Q","Contrasena",
                "1023456789012345");
    }

    /**
     * Test de getNombre, se comprueba que el nombre sea igual a "Tony"
     */

    @Test
    public void getNombre() {
        System.out.println("UsuarioRegistrado: test getNombre");
        assertEquals(demandante.getNombre(), "Tony");
    }

    /**
     * Test de getApellidos, se comprueba que el apellido sea igual a "Stark"
     */
    @Test
    public void getApellidos() {
        System.out.println("UsuarioRegistrado: test getApellidos");
        assertEquals(demandante.getApellidos(), "Stark");
    }

    /**
     * Test de getNif, se comprueba que el NIF sea igual a "12345678Q"
     */
    @Test
    public void getNif() {
        System.out.println("UsuarioRegistrado: test getNif");
        assertEquals(demandante.getNif(), "12345678Q");
    }

    /**
     * Test de getPassword, se comprueba que la contrasena sea igual a "Contrasena"
     */
    @Test
    public void getPassword() {
        System.out.println("UsuarioRegistrado: test getPassword");
        assertEquals(demandante.getPassword(), "Contrasena");
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