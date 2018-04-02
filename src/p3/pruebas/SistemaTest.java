package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.*;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class SistemaTest {

    private Sistema sistema;
    private Cliente ofertante;
    private Cliente demandante;
    private Inmueble inmueble;
    private Oferta oferta;
    private TeleChargeAndPaySystem pasarelaPago;
    private Opinion comentario;

    @Before
    public void setUp() throws Exception {
        pasarelaPago = new TeleChargeAndPaySystem();
        sistema = new Sistema(pasarelaPago);
        ofertante = new Ofertante( "Tony","Stark","12345678Q","Contraseña",
                "0123456789012345" );
        inmueble = new Inmueble(5,2,150,"C/ del diamante 5",5,
                true, (Ofertante)ofertante);
        oferta = new Oferta(200, LocalDate.MIN, LocalDate.MAX,true,50);
        demandante = new Demandante("Vic", "Rattlehead", "66666666D", "PeaceSells",
                "6666999966669999");
        comentario = new Comentario((Demandante)demandante,"Piso a muy buen precio para unos dias de vacaciones");
    }

    @Test(expected = NullPointerException.class)
    public void constructor(){
        System.out.println("Sistema: test constructor");
        Sistema sis2 = new Sistema(null);
    }

    @Test
    public void getTotalComisiones() {
        System.out.println("Sistema: test getTotalComisiones");
        assertTrue(sistema.getTotalComisiones()==0);
    }

    @Test
    public void getUsuarios() {
        System.out.println("Sistema: test getUsuarios");
        sistema.anadirUsuario(ofertante);
        sistema.anadirUsuario(demandante);
        assertNotNull(sistema.getUsuarios());
    }

    @Test
    public void getPasarelaPago() {
        System.out.println("Sistema: test getPasarelaPago");
        assertNotNull(sistema.getPasarelaPago());
    }

    @Test
    public void getInmuebles() {
        System.out.println("Sistema: test getInmuebles");
        sistema.anadirInmueble(inmueble);
        assertNotNull(sistema.getInmuebles());
    }

    @Test
    public void getOfertas() {
        System.out.println("Sistema: test getOfertas");
        sistema.anadirOferta(oferta);
        assertNotNull(sistema.getOfertas());
    }

    @Test
    public void getOpiniones() {
        System.out.println("Sistema: test getOpiniones");
        sistema.anadirOpinion(comentario);
        assertNotNull(sistema.getOpiniones());
    }

    @Test
    public void setTotalComisiones() {
        System.out.println("Sistema: test setTotalComisiones");
        sistema.setTotalComisiones(20);
        assertTrue(sistema.getTotalComisiones()==20);
    }

    @Test
    public void anadirUsuario() {
        System.out.println("Sistema: test anadirUsuario");
        assertTrue(sistema.anadirUsuario(ofertante));
        assertTrue(sistema.anadirUsuario(demandante));
    }

    @Test
    public void anadirInmueble() {
        System.out.println("Sistema: test anadirInmueble");
        assertTrue(sistema.anadirInmueble(inmueble));
    }

    @Test
    public void anadirOferta() {
        System.out.println("Sistema: test anadirOferta");
        assertTrue(sistema.anadirOferta(oferta));
    }

    @Test
    public void anadirOpinion() {
        System.out.println("Sistema: test anadirOpinion");
        assertTrue(sistema.anadirOpinion(comentario));
    }

    @Test
    public void login1_1() {
        System.out.println("Sistema: test login1_1");
        sistema.anadirUsuario(ofertante);
        assertTrue(sistema.login(ofertante, "12345678Q", "Contraseña"));
    }

    @Test
    public void login1_2() {
        System.out.println("Sistema: test login1_2");
        sistema.anadirUsuario(ofertante);
        assertFalse(sistema.login(ofertante, "87654321Q", "Contraseña"));
    }

    @Test(expected = NullPointerException.class)
    public void login1_3() {
        System.out.println("Sistema: test login1_3");
        sistema.anadirUsuario(ofertante);
        sistema.login(null, "12345678Q", "Contraseña");
    }

    @Test(expected = NullPointerException.class)
    public void login1_4() {
        System.out.println("Sistema: test login1_4");
        sistema.anadirUsuario(ofertante);
        sistema.login(ofertante, null, "Contraseña");
    }

    @Test(expected = NullPointerException.class)
    public void login1_5() {
        System.out.println("Sistema: test login1_5");
        sistema.anadirUsuario(ofertante);
        sistema.login(ofertante, "12345678Q",null);
    }

    @Test
    public void login1_6() {
        System.out.println("Sistema: test login1_6");
        assertFalse(sistema.login(ofertante, "12345678Q","Contraseña"));
    }

    @Test
    public void login2_1() {
        System.out.println("Sistema: test login2_1");
        assertTrue(sistema.login("SoyDios", "Apruebanos"));
    }

    @Test
    public void login2_2() {
        System.out.println("Sistema: test login2_2");
        assertFalse(sistema.login("jajajajaj", "Apruebanos"));
    }

    @Test(expected = NullPointerException.class)
    public void login2_3() {
        System.out.println("Sistema: test login2_3");
        sistema.login(null, "Apruebanos");
    }

    @Test(expected = NullPointerException.class)
    public void login2_4() {
        System.out.println("Sistema: test login2_4");
        sistema.login("SoyDios", null);
    }

    @Test
    public void logout1_1() {
        System.out.println("Sistema: test logout1_1");
        sistema.anadirUsuario(ofertante);
        sistema.login(ofertante, "12345678Q", "Contraseña");
        assertTrue(sistema.logout(ofertante));
    }

    @Test
    public void logout1_2() {
        System.out.println("Sistema: test logout1_2");
        sistema.anadirUsuario(ofertante);
        assertFalse(sistema.logout(ofertante));
    }

    @Test
    public void logout1_3() {
        System.out.println("Sistema: test logout1_3");
        assertFalse(sistema.logout(ofertante));
    }

    @Test(expected = NullPointerException.class)
    public void logout1_4() {
        System.out.println("Sistema: test logout1_1");
        assertTrue(sistema.logout(null));
    }

    @Test
    public void logout2_1() {
        System.out.println("Sistema: test logout2_1");
        sistema.login("SoyDios", "Apruebanos");
        assertTrue(sistema.logout());
    }

    @Test
    public void logout2_2() {
        System.out.println("Sistema: test logout2_2");
        assertFalse(sistema.logout());
    }

    @Test
    public void comprobarReservas() {
    }

    @Test
    public void buscar() {
    }

    @Test
    public void avanzada() {
    }
}