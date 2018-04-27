package p3.mvc.controlador;

import p3.mvc.interfaz.GuiInmobiliaria;
import p3.mvc.modelo.*;

import java.util.List;

public class Controlador {
    private GuiInmobiliaria gui;
    private Sistema muzska;

    public Controlador(GuiInmobiliaria gui, Sistema muzska) {
        this.gui = gui;
        this.muzska = muzska;
    }

    public void login(String nif, String passwd, String option) {
        Cliente usr;
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
                            usr = d;
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
                            usr = d;
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
        if(nHab == null){
            int nHab2 = -1;
        } else {
            int nHab2 = Integer.parseInt(nHab);
        }
        if(nBan == null){
            int nBan2 = -1;
        } else {
            int nBan2 = Integer.parseInt(nBan);
        }
        if(dim == null){
            int dim2 = -1;
        } else {
            int dim2 = Integer.parseInt(dim);
        }
        if(planta == null){
            int planta2 = -1;
        } else {
            int planta2 = Integer.parseInt(planta);
        }
        List<Inmueble> lista;

    }
    public void volverLogin() {
        this.gui.volverLogin();
    }

    public void goLogin() {
        this.gui.goLogin();
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
}