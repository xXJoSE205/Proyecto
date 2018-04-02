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
        System.out.println("UsuarioRegistrado: test getNombre");
        assertTrue(demandante.getNombre()=="Tony");
    }

    @Test
    public void getApellidos() {
        System.out.println("UsuarioRegistrado: test getApellidos");
        assertTrue(demandante.getApellidos()=="Stark");
    }

    @Test
    public void getNif() {
        System.out.println("UsuarioRegistrado: test getNif");
        assertTrue(demandante.getNif()=="12345678Q");
    }

    @Test
    public void getPassword() {
        System.out.println("UsuarioRegistrado: test getPassword");
        assertTrue(demandante.getPassword()=="Contraseña");
    }

    @Test
    public void isLogeado() {
        System.out.println("UsuarioRegistrado: test isLogeado");
        assertFalse(demandante.isLogeado());
    }

    @Test
    public void setLogeado() {
        System.out.println("UsuarioRegistrado: test setLogeado");
        demandante.setLogeado(true);
        assertTrue(demandante.isLogeado());
    }
}