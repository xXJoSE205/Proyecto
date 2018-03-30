/**
 * Esta clase contiene el test de la clase UsuarioRegistrado
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Gerente;
import p3.src.UsuarioRegistrado;

import static org.junit.Assert.*;

public class UsuarioRegistradoTest {
    /**
     * Usuario general para realizar el test
     * Se instanciara como Gerente, clase descendiente de UsuarioRegistrado
     */
    private UsuarioRegistrado usuario;

    /**
     * Crea un usuario registrado como gerente para realizar los test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        usuario = new Gerente("Luke", "Skywalker", "73582974K", "VivaYoda");
    }

    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El nombre es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("UsuarioRegistrado: test constructor1");
        UsuarioRegistrado user = new Gerente(null,"Skywalker","73582974K","VivaYoda");
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El apellidos es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("UsuarioRegistrado: test constructor2");
        UsuarioRegistrado user = new Gerente("Luke",null,"73582974K","VivaYoda");
    }

    /**
     * Test 3 del constructor, se espera un NullPointerException
     * El nif es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("UsuarioRegistrado: test constructor3");
        UsuarioRegistrado user = new Gerente("Luke","Skywalker",null,"VivaYoda");
    }

    /**
     * Test 4 del constructor, se espera un NullPointerException
     * La terjeta es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor4(){
        System.out.println("UsuarioRegistrado: test constructor4");
        UsuarioRegistrado user = new Gerente("Luke","Skywalker","73582974K",null);
    }

    /**
     * Test de getNombre, se comprueba que el nombre del setUp sea igual a "Luke"
     */
    @Test
    public void getNombre() {
        System.out.println("UsuarioRegistrado: test getNombre");
        assertEquals(usuario.getNombre(), "Luke");
    }

    /**
     * Test de getApellidos, se comprueba que el apellido del setUp sea igual a "Skywalker"
     */
    @Test
    public void getApellidos() {
        System.out.println("UsuarioRegistrado: test getApellidos");
        assertEquals(usuario.getApellidos(), "Skywalker");
    }

    /**
     * Test de getNif, se comprueba que el nif del setUp sea igual a "73582974K"
     */
    @Test
    public void getNif() {
        System.out.println("UsuarioRegistrado: test getNif");
        assertEquals(usuario.getNif(), "73582974K");
    }

    /**
     * Test de getPassword, se comprueba que la contraseña del setUp sea igual a "VivaYoda"
     */
    @Test
    public void getPassword() {
        System.out.println("UsuarioRegistrado: test getPassword");
        assertEquals(usuario.getPassword(), "VivaYoda");
    }

    /**
     * Test de isLogeado, se comprueba que el usuario del setUp no este logeado(false)
     */
    @Test
    public void isLogeado() {
        System.out.println("UsuarioRegistrado: test isLogeado");
        assertFalse(usuario.isLogeado());
    }

    /**
     * Test de setLogeado, se logea al usuario del setUp y se comprueba que efectivamente este logeado(true)
     */
    @Test
    public void setLogeado() {
        System.out.println("UsuarioRegistrado: test setLogeado");
        usuario.setLogeado(true);
        assertTrue(usuario.isLogeado());
    }
}