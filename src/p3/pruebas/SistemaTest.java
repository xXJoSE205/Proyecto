package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.mvc.modelo.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Esta clase contiene el test de la clase Sistema
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class SistemaTest {
    /** Sistema general para realizar el test*/
    private Sistema sistema;
    /** Ofertante general para realizar el test*/
    private Cliente ofertante;
    /** Demandante general para realizar el test*/
    private Cliente demandante;
    /** Inmueble general para realizar el test*/
    private Inmueble inmueble;
    /** Oferta general para realizar el test*/
    private Oferta oferta;
    /** Comentario general para realizar el test*/
    private Opinion comentario;

    /**
     * Crea un sistema para realizar los test
     */
    @Before
    public void setUp(){
        sistema = new Sistema();
        ofertante = new Ofertante( "Tony","Stark","12345678Q","Contrasena",
                "0123456789012345" );
        inmueble = new Inmueble(5,2,150,"C/ del diamante 5",5,
                true, (Ofertante)ofertante);
        oferta = new Oferta(200, LocalDate.MIN, LocalDate.MAX,true,50, inmueble);
        demandante = new Demandante("Vic", "Rattlehead", "66666666D", "PeaceSells",
                "6666999966669999");
        comentario = new Comentario((Demandante)demandante,"Piso a muy buen precio para unos dias de vacaciones");
    }

    /**
     * Test de getTotalComisiones, se comprube que totalComisiones es igual a 0
     */
    @Test
    public void getTotalComisiones() {
        System.out.println("Sistema: test getTotalComisiones");
        assertTrue(sistema.getTotalComisiones()==0);
    }

    /**
     * Test de getUsuarios, se comprueba que la lista de usuarios del setUp no es NULL
     */
    @Test
    public void getUsuarios() {
        System.out.println("Sistema: test getUsuarios");
        sistema.anadirUsuario(ofertante);
        sistema.anadirUsuario(demandante);
        assertNotNull(sistema.getUsuarios());
    }

    /**
     * Test de getInmueble, se comprueba que la lista de inmuebles del setUp no  NULL
     */
    @Test
    public void getInmuebles() {
        System.out.println("Sistema: test getInmuebles");
        sistema.anadirInmueble(inmueble);
        assertNotNull(sistema.getInmuebles());
    }


    /**
     * Test de getOpiniones, se comprueba que la lista de opiniones del setUp no  NULL
     */
    @Test
    public void getOpiniones() {
        System.out.println("Sistema: test getOpiniones");
        sistema.anadirOpinion(comentario);
        assertNotNull(sistema.getOpiniones());
    }

    /**
     * Test de getGerente, se comprueba que el gerente no sea null
     */
    @Test
    public void getGerente() {
        System.out.println("Sistema: test getGerente");
        assertNotNull(sistema.getGerente());
    }

    /**
     * Test de setTotalComisione, se comprueba que la nueva comision es igual a 20
     */
    @Test
    public void setTotalComisiones() {
        System.out.println("Sistema: test setTotalComisiones");
        sistema.setTotalComisiones(20);
        assertTrue(sistema.getTotalComisiones()==20);
    }

    /**
     * Test anadirUsuario1, se comprueba que el metodo devuelve true
     */
    @Test
    public void anadirUsuario1() {
        System.out.println("Sistema: test anadirUsuario1");
        assertTrue(sistema.anadirUsuario(ofertante));
        assertTrue(sistema.anadirUsuario(demandante));
    }

    /**
     * Test anadirUsuario2, se espera NullPointerException
     * El usuario es null
     */
    @Test (expected = NullPointerException.class)
    public void anadirUsuario2() {
        System.out.println("Sistema: test anadirUsuario2");
        sistema.anadirUsuario(null);
    }

    /**
     * Test anadirInmueble1, se comprueba que el metodo devuelve true
     */
    @Test
    public void anadirInmueble1() {
        System.out.println("Sistema: test anadirInmueble1");
        assertTrue(sistema.anadirInmueble(inmueble));
    }

    /**
     * Test de anadirInmueble2, se espera NullPointerException
     * El inmueble es null
     */

    @Test (expected = NullPointerException.class)
    public void anadirInmueble2() {
        System.out.println("Sistema: test anadirInmueble2");
        sistema.anadirInmueble(null);
    }

    /**
     * Test de anadirOpinion1, se comprueba que metodo devuelve true
     */
    @Test
    public void anadirOpinion1() {
        System.out.println("Sistema: test anadirOpinion1");
        assertTrue(sistema.anadirOpinion(comentario));
    }

    /**
     * Test de anadirOpinion2, se espera NullPointerException
     * Opinion es NULL
     */
    @Test (expected = NullPointerException.class)
    public void anadirOpinion2() {
        System.out.println("Sistema: test anadirOpinion2");
        sistema.anadirOpinion(null);
    }

    /**
     * Test de login1_1, comprueba que el metodo devuelve true
     */
    @Test
    public void login1_1() {
        System.out.println("Sistema: test login1_1");
        sistema.anadirUsuario(ofertante);
        assertTrue(sistema.login(ofertante, "12345678Q", "Contrasena"));
    }

    /**
     * Test de login1_2, comprueba que el metodo devuelve false,
     * El nif no esta entre los usuarios
     */
    @Test
    public void login1_2() {
        System.out.println("Sistema: test login1_2");
        sistema.anadirUsuario(ofertante);
        assertFalse(sistema.login(ofertante, "87654321Q", "Contrasena"));
    }

    /**
     * Test login1_3, se espera NullPointerException
     * El usuario es NULL
     */
    @Test(expected = NullPointerException.class)
    public void login1_3() {
        System.out.println("Sistema: test login1_3");
        sistema.anadirUsuario(ofertante);
        sistema.login(null, "12345678Q", "Contrasena");
    }

    /**
     * Test de login1_4, se espera NullPointerException
     * El NIF es null
     */
    @Test(expected = NullPointerException.class)
    public void login1_4() {
        System.out.println("Sistema: test login1_4");
        sistema.anadirUsuario(ofertante);
        sistema.login(ofertante, null, "Contrasena");
    }

    /**
     * Test de login1_5, se espera NullPointerException
     * La contrasena es NULL
     */
    @Test(expected = NullPointerException.class)
    public void login1_5() {
        System.out.println("Sistema: test login1_5");
        sistema.anadirUsuario(ofertante);
        sistema.login(ofertante, "12345678Q",null);
    }

    /**
     * Test de login1_6, se comprueba que el metodo devuelve false
     * El usuario no se anade a la lista
     */
    @Test
    public void login1_6() {
        System.out.println("Sistema: test login1_6");
        assertFalse(sistema.login(ofertante, "12345678Q","Contrasena"));
    }

    /**
     * Test de login1_7, se comprueba que el metodo devuelve false
     * La contrasena no es correcta
     */
    @Test
    public void login1_7(){
        System.out.println("Sistema: test login1_7");
        sistema.anadirUsuario(ofertante);
        assertFalse(sistema.login("12345678Q","Ekisde"));
    }

    /**
     * Test login2_1, comprueba que el metodo devuelve true
     */
    @Test
    public void login2_1() {
        System.out.println("Sistema: test login2_1");
        assertTrue(sistema.login("00000000G", "Apruebanos"));
    }

    /**
     * Test login2_2, comprueba que el metodo devueve false
     * EL NIF es incorrecto
     */
    @Test
    public void login2_2() {
        System.out.println("Sistema: test login2_2");
        assertFalse(sistema.login("jajajajaj", "Apruebanos"));
    }

    /**
     * Test de login2_3, se espera NullPointerException
     * El NIF es NULL
     */
    @Test(expected = NullPointerException.class)
    public void login2_3() {
        System.out.println("Sistema: test login2_3");
        sistema.login(null, "Apruebanos");
    }

    /**
     * Test de login2_4, se espera NullPointerException
     * La contrasena en NULL
     */
    @Test(expected = NullPointerException.class)
    public void login2_4() {
        System.out.println("Sistema: test login2_4");
        sistema.login("SoyDios", null);
    }

    /**
     * Test de login2_5, se comprueba que el metodo devuelve false
     * La contrasena es erronea
     */
    @Test
    public void login2_5(){
        System.out.println("Sistema: test login2_5");
        assertFalse(sistema.login("SoyDios","Rick"));
    }

    /**
     * Test de logout1_1, se comprueba que metodo devuelve true
     */
    @Test
    public void logout1_1() {
        System.out.println("Sistema: test logout1_1");
        sistema.anadirUsuario(ofertante);
        sistema.login(ofertante, "12345678Q", "Contrasena");
        assertTrue(sistema.logout(ofertante));
    }

    /**
     * Test de logout1_2, se comprueba que el metodo devuelve false
     * El usuario no esta logeado
     */
    @Test
    public void logout1_2() {
        System.out.println("Sistema: test logout1_2");
        sistema.anadirUsuario(ofertante);
        assertFalse(sistema.logout(ofertante));
    }

    /**
     * Test de logout1_3, se comprueba que el metodo devuelve false
     * El usuario no esta en la lista
     */
    @Test
    public void logout1_3() {
        System.out.println("Sistema: test logout1_3");
        assertFalse(sistema.logout(ofertante));
    }

    /**
     * Test de logout1_4, se espera NullPointerException
     * El usuario es NULL
     */
    @Test(expected = NullPointerException.class)
    public void logout1_4() {
        System.out.println("Sistema: test logout1_1");
        assertTrue(sistema.logout(null));
    }

    /**
     * Test de logout2_1, se comprueba que metodo devuelve true
     */
    @Test
    public void logout2_1() {
        System.out.println("Sistema: test logout2_1");
        sistema.login("00000000G", "Apruebanos");
        assertTrue(sistema.logout());
    }

    /**
     * Test de logout2_2, se comprueba que el metodo devuelve false
     * El gerenhte no esta logeado
     */
    @Test
    public void logout2_2() {
        System.out.println("Sistema: test logout2_2");
        assertFalse(sistema.logout());
    }

    /**
     *
     */
    @Test
    public void comprobarReservas() {
        System.out.println("Sistema: test comprobarReserva");
        oferta.reservar((Demandante) demandante);

    }

    /**
     *Test de buscar, comprueba que el metodo no devuelve NULL
     */
    @Test
    public void buscar() {
        System.out.println("Sistema: test buscar1");
        assertNotNull(sistema.buscar(5,2,150,5,true,"C/ del diamante 5"));
    }

    /**
     * Test de avanzada1, comprueba que el metodo no devuelve NULL
     */
    @Test
    public void avanzada1() {
        System.out.println("Sistema: test avanzada1");
        sistema.anadirUsuario(demandante);
        sistema.anadirUsuario(ofertante);
        sistema.login("00000000G","Apruebanos");
        sistema.anadirInmueble(inmueble);
        oferta.aprobar();
        sistema.logout();
        sistema.login(demandante,"66666666D","PeaceSells");
        assertNotNull(sistema.avanzada(5,2,150,5,true,"C/ del diamante 5",
                200, true, demandante));
    }

    /**
     * Test de avanzada2, comprueba que el metodo devuelve NULL
     * El demandante no esta logeado
     */
    @Test
    public void avanzada2(){
        System.out.println("Sistema: test avanzada2");
        assertNull(sistema.avanzada(5,2,150,5,true,"C/ del diamante 5",
                200, true, demandante));
    }

    /**
     * Test avanzada3, se espera NullPointerException
     * El demandante es NULL
     */
    @Test (expected = NullPointerException.class)
    public void avanzada3(){
        System.out.println("Sistema: test avanzada3");
        sistema.avanzada(5,2,150,5,true,"C/ del diamante 5",200,
                true, null);
    }

    /**
     * Test alquilar 1, comprueba que alquilar devuelve true
     */
    @Test
    public void alquilar1() {
        System.out.println("Sistema: test alquilar1");
        Demandante demandante1 = new Demandante("Ichigo","Lluvia de Estrellas","02020202P",
                "1euroHamburger","0000000000000002");
        sistema.anadirUsuario(demandante1);
        sistema.anadirUsuario(ofertante);
        sistema.anadirInmueble(inmueble);
        sistema.login("000000G", "Apruebanos");
        oferta.aprobar();
        sistema.logout();
        sistema.login(demandante1, "02020202P", "1euroHambuerger");
        assertTrue(sistema.alquilar(demandante1,oferta));
    }

    /**
     * Test de alquilar2, se espera NullPointerException
     * demandante es null
     */
    @Test (expected = NullPointerException.class)
    public void alquilar2(){
        System.out.println("Sistema: test alquilar2");
        sistema.alquilar(null,oferta);
    }

    /**
     * Test de alquilar3, se espera NullPointerException
     * oferta es null
     */
    @Test (expected = NullPointerException.class)
    public void alquilar3(){
        System.out.println("Sistema: test alquilar3");
        Demandante demandante1 = new Demandante("Ichigo","Lluvia de Estrellas","02020202P",
                "1euroHamburger","0000000000000002");
        sistema.alquilar(demandante1,null);

    }

    /**
     * Test de alquilar4, se comprueba que alquiler devuelve false
     * El estado de la oferta es NO_DISPONIBLE
     */
    @Test
    public void alquilar4(){
        System.out.println("Sistema: test alquilar4");
        oferta.setEstado(Estado.NO_DISPONIBLE);
        Demandante demandante1 = new Demandante("Ichigo","Lluvia de Estrellas","02020202P",
                "1euroHamburger","0000000000000002");
        assertFalse(sistema.alquilar(demandante1,oferta));
    }

    /**
     * Test de alquilar5, se comprueba que alquilar devuelve false
     * La oferta esta reservada, pero no por el demandante
     */
    @Test
    public void alquilar5(){
        System.out.println("Sistema: test alquilar5");
        oferta.setReservado(true);
        Demandante demandante1 = new Demandante("Ichigo","Lluvia de Estrellas","02020202P",
                "1euroHamburger","0000000000000002");
        assertFalse(sistema.alquilar(demandante1,oferta));
    }
}

