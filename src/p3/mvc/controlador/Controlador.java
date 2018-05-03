package p3.mvc.controlador;

import p3.mvc.interfaz.GuiInmobiliaria;
import p3.mvc.modelo.*;

import java.time.LocalDate;
import java.util.List;

public class Controlador {
    private GuiInmobiliaria gui;
    private Sistema muzska;
    private Cliente usr;
    private List<Inmueble> busqueda;
    private List<Oferta> avanzada;
    private Oferta oferta;

    public Controlador(GuiInmobiliaria gui, Sistema muzska) {
        this.gui = gui;
        this.muzska = muzska;
    }

    public void login(String nif, String passwd, String option) {
        if(nif==null || passwd==null || option==null){
            this.gui.loginResult(false);
        }
        assert option != null;
        switch (option) {
            case "Demandante": {
                List<Cliente> clientes = muzska.getUsuarios();
                for (Cliente d : clientes) {
                    if (d.getNif().equals(nif)) {
                        if (d instanceof Demandante) {
                            this.usr = d;
                            this.gui.loginResult(muzska.login(usr, nif, passwd));
                        }
                    }
                }
                break;
            }
            case "Ofertante": {
                List<Cliente> clientes = muzska.getUsuarios();
                for (Cliente d : clientes) {
                    if (d.getNif().equals(nif)) {
                        if (d instanceof Ofertante) {
                            this.usr = d;
                            this.gui.loginResult(muzska.login(usr, nif, passwd));
                        }
                    }
                }
                break;
            }
            case "Gerente":
                this.gui.loginResult(muzska.login(nif, passwd));
                break;
            default:
                this.gui.loginResult(false);
                break;
        }
    }

    public void buscar (int nHab, int nBan, int dim, int planta, boolean ascensor, String dir){

    }

    public void volverLogin() {
        this.gui.volverLogin();
    }

    public void goLogin() {
        this.gui.goLogin();
    }

    public void volverBusqueda() {
        this.gui.volverBusqueda(usr);
    }

    public void goBusqueda() {
        this.gui.goBusqueda();
    }

    public void saveData() {
        this.gui.guardarDatos(this.muzska);
    }

    public void logout() {
        if(this.muzska.getGerente().isLogeado()) {
            this.gui.logout(this.muzska.logout());
        }else{
            for(Cliente c: this.muzska.getUsuarios()){
                if(c.isLogeado()){
                    this.gui.logout(this.muzska.logout(c));
                }
            }
            this.gui.logout(false);
        }
    }

    public void goBuscar(){
        this.gui.goBuscar();
    }

    public void goAvanzada(){
        this.gui.goAvanzada();
    }

    public void volverDemandante(){
        this.gui.volverDemandante();
    }

    public void cancelarReserva(){
        for(Cliente c: this.muzska.getUsuarios()){
            if(c.isLogeado()){
                ((Demandante)c).quitarReserva();
                this.gui.volverDemandante();
            }
        }
    }

    public void avanzada(String nHab, String nBan, String dim, String planta, String ascensor, String dir, String precio, String vacacional){
        int nHab2=-1;
        int nBan2=-1;
        int dim2=-1;
        int planta2=-1;
        double precio2=-1;
        if(nHab!=null){
            nHab2=Integer.parseInt(nHab);
        }
        if(nBan!=null){
            nBan2=Integer.parseInt(nBan);
        }
        if(dim!=null){
            dim2=Integer.parseInt(dim);
        }
        if(planta!=null){
            planta2=Integer.parseInt(planta);
        }
        if(precio!=null){
            precio2=Double.parseDouble(precio);
        }
        boolean ascensor2 = Boolean.parseBoolean(ascensor);
        boolean vacacional2 = Boolean.parseBoolean(vacacional);
        List<Oferta> lista;
        this.avanzada=muzska.avanzada(nHab2,nBan2, dim2,planta2,ascensor2,dir,precio2,vacacional2,usr);
        //this.gui.goAvanzadaResultado(lista);

    }
    public void alquilar(Oferta oferta){

    }

    public void valorar(int x){

    }

    public void volverOferta(){
        this.gui.volverOferta();
    }

    public void anadirComentario(Comentario comentario){
        //this.gui.goAnadirComentario(comentario);
    }

    public void anadirComentario(Oferta oferta){

    }
    public void quitarLogin(){
        this.usr = null;
    }

    public List<Inmueble> getBusqueda(){return busqueda;}

    public List<Oferta> getAvanzada(){return avanzada;}

    public List<Comentario> getComentarios(){
        return (List<Comentario>)(Comentario)oferta.getOpiniones();
    }

    public Cliente getCliente() {
        return usr;
    }

    public void goCrearInmueble() {
        this.gui.goCrearInmueble();
    }

    public void goCrearOferta(Inmueble inmueble) {
        this.gui.goCrearOferta(inmueble);
    }

    public void volverOfertante(){
        this.gui.volverOfertante();
    }

    public void volverVerInmuebles(){
        this.gui.volverVerInmuebles();
    }

    public void crearInmueble(int nHab, int nBanos, int dim, String dir, int planta, boolean ascensor) {
        String texto;
        try {
            if (usr instanceof Ofertante) {
                Inmueble inmueble = new Inmueble(nHab, nBanos, dim, dir, planta, ascensor, (Ofertante) usr);
                if(muzska.anadirInmueble(inmueble)){
                    texto = "El inmueble se ha creado correctamente";
                    this.gui.creadoOK(texto);
                }else{
                    texto = "Error al añadir el inmueble";
                    this.gui.creadoOK(texto);
                }

            }
        }catch(Exception e){
            texto = e.getMessage();
            this.gui.creadoOK(texto);
        }
    }

    public void goVerInmuebles() {
        this.gui.goVerInmuebles();
    }

    public void goComentario(Oferta oferta){
        this.oferta=oferta;
    }

    public void crearOferta(double precio, LocalDate fIni, LocalDate fFin, boolean vacacional
            , double fianza, Inmueble inmueble) {
        String texto;
        try {
            if (usr instanceof Ofertante) {
                Oferta oferta = new Oferta(precio, fIni, fFin, vacacional, fianza, inmueble);
                if(inmueble.anadirOferta(oferta)){
                    texto = "La oferta se ha creado correctamente";
                    this.gui.creadaOK(texto);
                }else{
                    texto = "Error al añadir la oferta";
                    this.gui.creadaOK(texto);
                }

            }
        }catch(Exception e){
            texto = e.getMessage();
            this.gui.creadaOK(texto);
        }
    }

    public Sistema getSistema() {
        return muzska;
    }
}