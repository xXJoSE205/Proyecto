/**
 * Esta clase contiene la información del Sistema
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private double totalComisiones = 0;
    private List<Cliente> usuarios;
    private TeleChargeAndPaySystem pasarelaPago;
    private List<Inmueble> inmuebles;
    private List<Oferta> ofertas;
    private List<Opinion> opiniones;
    private Gerente gerente;

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
        LocalDate fecha;
        for(Oferta o: ofertas){
            if(o.isReservado()){
                fecha = o.getReserva().getFechaInicio();
                fecha = fecha.plusDays(5);
                if(fecha.isEqual(LocalDate.now()) || fecha.isBefore(LocalDate.now())){
                    o.setReservado(false);
                }
            }
        }
        return true;
    }

    public List<Inmueble> buscar(int nHab, int nBan, int dim, int planta, boolean ascensor, String dir ){
        List<Inmueble> busqueda = new ArrayList<>();
        for(Inmueble inmueble: inmuebles){
            if(inmueble.getnHabitaciones()==nHab && nHab>-1){
                busqueda.add(inmueble);
            }
            if(inmueble.getnBanos()==nBan && nBan>-1){
                busqueda.add(inmueble);
            }
            if(inmueble.getDimensiones()==dim && dim>-1){
                busqueda.add(inmueble);
            }
            if(inmueble)
        }
        return busqueda;
    }

}
