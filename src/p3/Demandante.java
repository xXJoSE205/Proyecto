package p3;

public class Demandante extends Cliente {
    private double deuda = 0;
    private boolean bloqueado = false;
    private boolean reservaActiva = false;


    public Demandante(String nombre, String apellidos, String nif, String password, String tarjeta) {
        super(nombre, apellidos, nif, password, tarjeta);
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda += deuda;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean isReservaActiva() {
        return reservaActiva;
    }

    public void setReservaActiva(boolean reserva) {
        this.reservaActiva = reserva;
    }

    public boolean desbloquearUsuario(){
        if(bloqueado==true){
            bloqueado=false;
            return true;
        }
        return false;
    }
}
