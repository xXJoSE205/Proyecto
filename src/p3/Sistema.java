package p3;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private double totalComisiones = 0;
    private List<Cliente> usuarios;
    private TeleChargeAndPaySystem pasarelaPago;
    private List<Inmueble> inmuebles;
    private List<Oferta> ofertas;
    private List<Opinion> opiniones;

    public Sistema(TeleChargeAndPaySystem pasarelaPago){
        this.usuarios = new ArrayList<>();
        this.pasarelaPago = pasarelaPago;
        this.inmuebles = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.opiniones = new ArrayList<>();
    }

    public double getTotalComisiones() {
        return totalComisiones;
    }

    public List<Cliente> getUsuarios() {
        return usuarios;
    }

    public TeleChargeAndPaySystem getPasarelaPago() {
        return pasarelaPago;
    }

    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public List<Opinion> getOpiniones() {
        return opiniones;
    }

    public void setTotalComisiones(double comisiones) {
        this.totalComisiones += comisiones;
    }

    public boolean anadirUsuario(Cliente usuario){
        return usuarios.add(usuario);
    }

    public boolean login(Cliente usuario, String nif, String password){
        if(usuarios.contains(usuario)) {
            for(Cliente x: usuarios) {
                if(x.isLogeado()) {
                    return false;
                }
            }
            if ((usuario.getNif() == nif) && (usuario.getPassword() == password)) {
                usuario.setLogeado(true);
                return true;
            }
        }
        return false;
    }

    public boolean logout(Cliente usuario){
        if(usuarios.contains(usuario)) {
            if(usuario.isLogeado()){
                usuario.setLogeado(false);
                return true;
            }
        }
        return false;
    }

    public boolean comprobarReservas(){
        for(Oferta o: ofertas){
            if(o.isReservado()){

            }
        }
    }
}
