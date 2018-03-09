package p3;

public class Ofertante extends UsuarioRegistrado{
    private double cargo = 0;
    private String modificaciones;

    public Ofertante(String nombre, String apellidos, String nif, String password, String tarjeta) {
        super(nombre, apellidos, nif, password, tarjeta);
    }

    public double getCargo() {
        return cargo;
    }

    public void setCargo(double cargo) {
        this.cargo += cargo;
    }

    public String getModificaciones() {
        return modificaciones;
    }

    public void setModificaciones(String modificaciones) {
        this.modificaciones = modificaciones;
    }
}
