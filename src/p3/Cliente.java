package p3;

public abstract class Cliente extends UsuarioRegistrado{
    private String tarjeta;

    public Cliente(String nombre, String apellidos, String nif, String password, String tarjeta) {
        super(nombre, apellidos, nif, password);
        this.tarjeta = tarjeta;
    }

    public String getTarjeta() {
        return tarjeta;
    }
}
