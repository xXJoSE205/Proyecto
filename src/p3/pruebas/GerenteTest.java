package p3.pruebas;

import org.junit.Test;
import p3.mvc.modelo.Gerente;

/**
 * Esta clase contiene el test de la clase Gerente
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class GerenteTest {
    /**
     * Test 1 del constructor, se espera un NullPointerException
     * El nombre es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor1(){
        System.out.println("Gerente: test constructor1");
        new Gerente(null,"Parker","54852689S","Spiderman");
    }

    /**
     * Test 2 del constructor, se espera un NullPointerException
     * El apellidos es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Gerente: test constructor2");
        new Gerente("Peter",null,"54852689S","Spiderman");
    }

    /**
     * Test 3 del constructor, se espera un NullPointerException
     * El nif es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("Gerente: test constructor3");
        new Gerente("Peter","Parker",null,"Spiderman");
    }

    /**
     * Test 4 del constructor, se espera un NullPointerException
     * La contrasena es null
     */
    @Test(expected = NullPointerException.class)
    public void constructor4(){
        System.out.println("Gerente: test constructor4");
        new Gerente("Peter","Parker","54852689S",null);
    }
}