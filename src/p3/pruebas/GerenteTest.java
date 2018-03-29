package p3.pruebas;

import org.junit.Before;
import p3.src.Gerente;

import static org.junit.Assert.*;

public class GerenteTest {

    private Gerente gerente;

    @Before
    public void setUp() throws Exception {
        gerente = new Gerente("Peter","Parker","54852689S","Spiderman");
    }

}