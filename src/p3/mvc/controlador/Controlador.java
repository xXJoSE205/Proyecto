package p3.mvc.controlador;

import p3.mvc.interfaz.GuiInmobiliaria;
import p3.mvc.modelo.*;

import java.util.List;

public class Controlador {
    private GuiInmobiliaria gui;
    private Sistema muzska;
    private Cliente usr;

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

    public void buscar (String nHab, String nBan, String dim, String planta, String ascensor, String dir){
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
        boolean ascensor2 = Boolean.parseBoolean(ascensor);
        List<Inmueble> lista;
        lista=muzska.buscar(nHab2,nBan2,dim2,planta2,ascensor2,dir);


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
        Demandante demandante = null;
        for(UsuarioRegistrado usuario : muzska.getUsuarios()){
            if(usuario.isLogeado()){
                demandante = (Demandante)usuario;
                break;
            }
        }
        lista=muzska.avanzada(nHab2,nBan2, dim2,planta2,ascensor2,dir,precio2,vacacional2,demandante);
        //this.gui.goAvanzadaResultado(lista);

    }
    public void alquilar(List<Oferta> lista){

    }

    public void valorar(int x){

    }

    public void volverOferta(){
        this.gui.volverOferta();
    }

    public void anadirComentario(Comentario comentario){
        //this.gui.goAnadirComentario(comentario);
    }

    public void quitarLogin(){
        this.usr = null;
    }
}