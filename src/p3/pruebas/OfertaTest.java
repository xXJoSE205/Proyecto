package p3.pruebas;

import org.junit.Before;
import org.junit.Test;
import p3.src.Demandante;
import p3.src.Estado;
import p3.src.Oferta;
import p3.src.Reserva;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class OfertaTest {

    private Oferta oferta;

    @Before
    public void setUp() throws Exception {
        oferta = new Oferta(200, LocalDate.MIN(),LocalDate.MAX,true,50);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor1(){
        System.out.println("Oferta: test constructor1");
        Oferta oferta1 = new Oferta(-1, LocalDate.now(),LocalDate.MAX,true,50);
    }

    @Test (expected = NullPointerException.class)
    public void constructor2(){
        System.out.println("Oferta: test constructor2");
        Oferta oferta1 = new Oferta(200, null,LocalDate.MAX,true,50);
    }

    @Test (expected = NullPointerException.class)
    public void constructor3(){
        System.out.println("Oferta: test constructor3");
        Oferta oferta1 = new Oferta(200, LocalDate.now(),null,true,50);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor4(){
        System.out.println("Oferta: test constructor4");
        Oferta oferta1 = new Oferta(200, LocalDate.now(),LocalDate.MAX,true,-1);
    }

    @Test
    public void getPrecio() {
        System.out.println("Oferta: test getPrecio");
        assertTrue(oferta.getPrecio()==200);
    }

    @Test
    public void setPrecio1() {
        System.out.println("Oferta: test setPrecio1");
        oferta.setPrecio(500);
        assertTrue(oferta.getPrecio()==500);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setPrecio2() {
        System.out.println("Oferta: test setPrecio2");
        oferta.setPrecio(-1);
    }

    @Test
    public void isReservado() {
        System.out.println("Oferta: test isReservado");
        assertFalse(oferta.isReservado());
    }

    @Test
    public void setReservado() {
        System.out.println("Oferta: test setReservado");
        oferta.setReservado(true);
        assertTrue(oferta.isReservado());

    }

    @Test
    public void getFechaInicio() {
        System.out.println("Oferta: test getFechaInicio");
        assertTrue(oferta.getFechaInicio().isEqual(LocalDate.MIN));
    }

    @Test
    public void setFechaInicio1() {
        System.out.println("Oferta: test setFechaInicio1");
        oferta.setFechaInicio(LocalDate.of(2018,03,29));
        assertTrue(oferta.getFechaInicio().isEqual(LocalDate.of(2018,03,29)));
    }

    @Test (expected = NullPointerException.class)
    public void setFechaIncio2(){
        System.out.println("Oferta: test setFechaInicio2");
        oferta.setFechaInicio(null);
    }

    @Test
    public void getFechaFin() {
        System.out.println("Oferta: test getFechaFin");
        assertTrue(oferta.getFechaFin().isEqual(LocalDate.MIN));
    }

    @Test
    public void setFechaFin1() {
        System.out.println("Oferta: test setFechaFin1");
        oferta.setFechaFin(LocalDate.of(2018,03,29));
        assertTrue(oferta.getFechaFin().isEqual(LocalDate.of(2018,03,29)));
    }

    @Test (expected = NullPointerException.class)
    public void setFechaFin2(){
        System.out.println("Oferta: test setFechaFin2");
        oferta.setFechaFin(null);
    }

    @Test
    public void isVacacional() {
        System.out.println("Oferta: test isVacacional");
        assertTrue(oferta.isVacacional());
    }

    @Test
    public void setVacacional() {
        System.out.println("Oferta: test setVacacional");
        oferta.setVacacional(false);
        assertFalse(oferta.isVacacional());
    }

    @Test
    public void getFianza() {
        System.out.println("Oferta: test getFianza");
        assertTrue(oferta.getFianza()==50);
    }

    @Test
    public void setFianza1() {
        System.out.println("Oferta: test setFianza1");
        oferta.setFianza(70);
        assertTrue(oferta.getFianza()==70);

    }

    @Test (expected = IllegalArgumentException.class)
    public void setFianza2() {
        System.out.println("Oferta: test setFianza2");
        oferta.setFianza(-1);

    }

    @Test
    public void getEstado() {
        System.out.println("Oferta: test getEstado");
        assertTrue(oferta.getEstado()== Estado.PENDIENTE);

    }

    @Test
    public void setEstado() {
        System.out.println("Oferta: test setEstado");
        oferta.setEstado(Estado.DISPONIBLE);
        assertTrue(oferta.getEstado()==Estado.DISPONIBLE);
    }

    @Test
    public void getReserva() {
        System.out.println("Oferta: test getReserva");
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña","0123456789012345" );
        oferta.reservar(demandante);
        assertNotNull(oferta.getReserva());
    }

    @Test
    public void reservar1() {
        System.out.println("Oferta: test reservar1");
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña","0123456789012345" );
        assertTrue(oferta.reservar(demandante));
    }

    @Test
    public void reservar2() {
        System.out.println("Oferta: test reservar2");
        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contraseña","0123456789012345" );
        demandante.setReservaActiva(true);
        assertFalse(oferta.reservar(demandante));
    }

    @Test(expected = NullPointerException.class)
    public void reservar3() {
        System.out.println("Oferta: test reservar3");
        oferta.reservar(null);
    }

    @Test
    public void cancelarReserva() {

    }

    @Test
    public void aprobarOferta() {
    }

    @Test
    public void rechazarOferta() {
    }

    @Test
    public void modificarOferta() {
    }
}