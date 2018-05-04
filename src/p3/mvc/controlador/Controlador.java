package p3.mvc.controlador;

import p3.mvc.interfaz.GuiInmobiliaria;
import p3.mvc.modelo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private GuiInmobiliaria gui;
    private Sistema muzska;
    private Cliente usr;
    private List<Inmueble> busqueda;
    private List<Oferta> avanzada;
    private List<Comentario> comentarios;
    private Oferta oferta;
    private Comentario comentario;

    public Controlador(GuiInmobiliaria gui, Sistema muzska) {
        this.gui = gui;
        this.muzska = muzska;
    }

    public void login(String nif, String passwd, String option) {
        if(nif==null || passwd==null || option==null){
            this.gui.loginResult(false);
        }
        try {
            assert option != null;
            switch (option) {
                case "Demandante": {
                    List<Cliente> clientes = muzska.getUsuarios();
                    for (Cliente d : clientes) {
                        if (d.getNif().equals(nif)) {
                            if (d instanceof Demandante) {
                                this.usr = d;
                                this.gui.loginResult(muzska.login(usr, nif, passwd));
                            }else{
                                this.gui.loginResult(false);
                            }
                        }
                        this.gui.loginResult(false);
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
                            }else{
                                this.gui.loginResult(false);
                            }
                        }
                        this.gui.loginResult(false);
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
        }catch (Exception e){
            quitarLogin();
            this.gui.loginResult(false);
        }
    }

    public void buscar (int nHab, int nBan, int dim, int planta, boolean ascensor, String dir){
        String texto;
        try{
            this.busqueda=muzska.buscar(nHab,nBan,dim,planta,ascensor,dir);
            if(this.busqueda==null){
                texto ="Error, no hay inmuebles que coincidan con las condiciones";
                this.gui.errorBusqueda(texto);
            }else {
                this.gui.goBusquedaResultado();
            }
        } catch (Exception e){
            this.gui.errorBusqueda(e.getMessage());
        }

    }

    public void volverLogin() {
        this.gui.volverLogin();
    }

    public void goLogin() {
        this.gui.goLogin();
    }

    public void volverDeBusqueda() {
        this.gui.volverDeBusqueda(usr);
    }

    public void volverBusqueda() {
        this.gui.volverBusqueda();
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

    public void goAvanzada(){
        this.gui.goAvanzada();
    }

    public void volverDemandante(){
        this.gui.volverDemandante();
    }

    public void cancelarReserva(){
        if(usr instanceof Demandante){
            try{
                ((Demandante) usr).quitarReserva();
                this.gui.cancelarReservaOK("Reserva cancelada");
            } catch (Exception e){
                this.gui.cancelarReservaOK(e.getMessage());
            }
        }
    }

    public void avanzada(int nHab, int nBan, int dim, int planta, boolean ascensor, String dir, int precio, boolean vacacional){
        String texto;
        try{
            this.avanzada=muzska.avanzada(nHab,nBan, dim,planta,ascensor,dir,precio,vacacional,usr);
            if(this.avanzada==null){
                texto = "Error, no hay ofertas que coincidan con las condiciones";
                this.gui.avanzadaError(texto);
            } else {
                this.gui.goRAvanzada();
            }
        }catch (Exception e){
            this.gui.avanzadaError(e.getMessage());
        }


    }
    public void alquilar(Oferta oferta){
        try{
            if(muzska.alquilar((Demandante)usr,oferta)){
                this.gui.alquilerOK("Alquiler realizado correctamente");
            } else{
                this.gui.alquilerOK("Error al alquilar");
            }
        } catch (Exception e){
            this.gui.alquilerOK(e.getMessage());
        }


    }

    public void valorar(int x){
        Valoracion valoracion = new Valoracion((Demandante)usr,x);
        try {
            if(oferta.anadirOpinion(valoracion)){
                this.gui.valoracionOK("Valoracion realizada correctamente");
            } else {
                this.gui.valoracionOK("Error al valorar");
            }
        } catch(Exception e){
            this.gui.valoracionOK(e.getMessage());
        }
    }

    public void volverOferta(){
        this.gui.volverOferta();
    }

    public void anadirComentario(Comentario comentario){
        String texto;
        this.comentario=comentario;
        this.gui.goAnadirComentario();
    }

    public void anadirComentario(Oferta oferta){
        this.oferta=oferta;
        this.gui.goCrearComentario();

    }
    public void quitarLogin(){
        this.usr = null;
    }

    public List<Inmueble> getBusqueda(){return busqueda;}

    public List<Oferta> getAvanzada(){return avanzada;}

    public List<Comentario> getComentarios(){
        List<Comentario> lista= new ArrayList<>();
        for(Opinion o :oferta.getOpiniones()){
            if(o instanceof Comentario){
                lista.add((Comentario)o);
            }
        }
        return lista;
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
        if(dir.equals("")){
            this.gui.creadoOK("La direccion no es valida");
        }
        try {
            if (usr instanceof Ofertante) {
                Inmueble inmueble = new Inmueble(nHab, nBanos, dim, dir, planta, ascensor, (Ofertante) usr);
                if(muzska.anadirInmueble(inmueble)){
                    this.gui.creadoOK("El inmueble se ha creado correctamente");
                }else{
                    this.gui.creadoOK("Error al añadir el inmueble");
                }

            }
        }catch(Exception e){
            this.gui.creadoOK(e.getMessage());
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
        try {
            if (usr instanceof Ofertante) {
                Oferta oferta = new Oferta(precio, fIni, fFin, vacacional, fianza, inmueble);
                if(inmueble.anadirOferta(oferta)){
                    this.gui.creadaOK("La oferta se ha creado correctamente");
                }else{
                    this.gui.creadaOK("Error al añadir la oferta");
                }

            }
        }catch(Exception e){
            this.gui.creadaOK(e.getMessage());
        }
    }

    public Sistema getSistema() {
        return muzska;
    }

    public Oferta getOferta(){return oferta;}

    public void volverRAvanzada(){
        this.gui.volverRAvanzada();
    }

    public void volverAvanzada(){
        this.gui.volverBAvanzada();
    }

    public void goDesbloquearUsuarios(Demandante demandante) {
        this.gui.goDesbloquearUsuarios(demandante);
    }

    public void goComprobarOfertas() {
        this.gui.goComprobarOfertas();
    }

    public List<Demandante> getUsuariosBloqueados() {
        List<Demandante> usuarios = new ArrayList<>();

        for(Cliente c: muzska.getUsuarios()){
            if(c instanceof Demandante){
                if(((Demandante) c).isBloqueado())
                    usuarios.add((Demandante)c);
            }
        }
        return usuarios;
    }

    public void volverGerente() {
        this.gui.volverGerente();
    }

    public List<Oferta> getOfertasPendientes() {
        List<Oferta> ofertas = new ArrayList<>();

        for(Inmueble i: muzska.getInmuebles()){
            for(Oferta o: i.getOfertas()){
                if(o.getEstado()==Estado.PENDIENTE){
                    ofertas.add(o);
                }
            }
        }
        return ofertas;
    }

    public void aceptarOferta(Oferta oferta) {
        oferta.setEstado(Estado.DISPONIBLE);
        this.gui.aceptarOferta("La oferta se ha aceptado");
    }

    public void rechazarOferta(Oferta oferta, String modificaciones) {
        oferta.getInmueble().getDueno().anadirModificaciones(modificaciones, true);
        this.gui.rechazarOferta("La oferta se ha rechazado");
    }

    public void goUsuariosBloqueados() {
        this.gui.goUsuariosBloqueados();
    }

    public void volverUsuariosBloqueados(){
        this.gui.volverUsuariosBloqueados();
    }

    public void goAlquilar(Oferta oferta){
        this.oferta=oferta;
        this.gui.goAlquilar();
    }

    public void volverBAvanzada(){
        this.gui.volverBAvanzada();
    }

    public void volverComentario(){
        this.gui.volverComentario();
    }

    public Comentario getComentario(){return comentario;}

    public String getValoracion(){
        double x=0;
        int i=0;
        for(Opinion o :oferta.getOpiniones()){
            if(o instanceof Valoracion){
                x+=((Valoracion) o).getPuntuacion();
                i++;
            }
        }
        x=x/i;
        return String.valueOf(x);

    }

    public void publicarComentario(String texto){
        String texto2;
        try{
            if(oferta.anadirOpinion(new Comentario((Demandante)usr,texto))){
                texto2 = "Comentario publicado";
                this.gui.publicarOK(texto2);
            } else {
                texto2 = "Error al publicar";
                this.gui.publicarOK(texto2);
            }
        }catch (Exception e){
            this.gui.publicarOK(e.getMessage());
        }

    }

    public void publicarComentario(Comentario comentario){
        try{
            if(this.comentario.anadirComentario(comentario)){
                this.gui.anadirCOmentarioOK("Comentario publicado correctamente");
            } else {
                this.gui.anadirCOmentarioOK("Error al publicar el comentario");
            }
        } catch (Exception e){
            this.gui.anadirCOmentarioOK(e.getMessage());
        }
    }

    public void goComprobarReserva(){
        this.gui.goComprobarReserva();
    }

    public List<Oferta> getOfertasRechazadas() {
        List<Oferta> ofertas = new ArrayList<>();

        for(Inmueble i: ((Ofertante)usr).getInmuebles()){
            for(Oferta o: i.getOfertas()){
                if(o.getEstado()==Estado.RECHAZADO){
                    ofertas.add(o);
                }
            }
        }

        return ofertas;
    }

    public void goVerModificaciones() {
        this.gui.goVerModificaciones();
    }

    public void goModificarOferta(Oferta oferta) {
        this.gui.goModifcarOferta(oferta);
    }

    public void modificarOferta(double precio, LocalDate fIni, LocalDate fFin, boolean vacacional
            , double fianza, Oferta oferta) {
        String texto;

        try {
            oferta.setPrecio(precio);
            oferta.setFechaInicio(fIni);
            oferta.setFechaFin(fFin);
            oferta.setVacacional(vacacional);
            oferta.setFianza(fianza);
            oferta.setEstado(Estado.PENDIENTE);
            texto = "La oferta se ha modificado correctamente";
            this.gui.modificada(texto);
        }catch (Exception e){
            texto = e.getMessage();
            this.gui.modificada(texto);
        }
    }

    public void desbloquearUsuario(Demandante demandante) {
        String texto;
        if(demandante.desbloquear()){
            texto = "El usuaario ha sido desbloqueado";
            this.gui.desbloqueado(texto);
        }else{
            texto = "Error al desbloquear";
            this.gui.desbloqueado(texto);
        }
    }

    public void desbloquearUsuario(Demandante demandante, String tarjeta) {
        String texto;
        try {
            if (demandante.desbloquear()) {
                demandante.setTarjeta(tarjeta);
                texto = "El usuaario ha sido desbloqueado y la tarjeta se ha modificado";
                this.gui.desbloqueado(texto);
            } else {
                texto = "Error al desbloquear";
                this.gui.desbloqueado(texto);
            }
        }catch (Exception e){
            texto = e.getMessage();
            this.gui.desbloqueado(texto);
        }
    }

    public void volverPrincipal() {
        this.gui.volverPrincipal();
    }
}