package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;

import static org.junit.Assert.*;

public class UsuarioRegistradoTest {

    private Demandante demandante;

    @Before
    public void setUp() throws Exception {
    demandante = new Demandante("Tony","Stark","12345678Q","Contraseña",
            "0123456789012345");

    }

    @Test
    public void getNombre() {

    }

    @Test
    public void getApellidos() {
    }

    @Test
    public void getNif() {
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void isLogeado() {
    }

    @Test
    public void setLogeado() {
    }
}