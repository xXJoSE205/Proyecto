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

    public void quitarLogin(){
        this.usr = null;
    }
}