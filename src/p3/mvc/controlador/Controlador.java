package p3.mvc.controlador;

/*import p3.mvc.modelo.Aplicacion;*/
import p3.mvc.interfaz.GuiSimple;
import p3.src.*;

import java.util.List;

public class Controlador {
    private GuiSimple gui;
    private Sistema muzska;

    public Controlador(GuiSimple gui, Sistema muzska) {
        this.gui = gui; this.muzska = muzska;
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

    public void volverLogin() {
        this.gui.volverLogin();
    }

    public void goLogin() {
        this.gui.goLogin();
    }
}