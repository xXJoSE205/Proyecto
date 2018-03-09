package p3;

public abstract class UsuarioRegistrado {
    private String nombre;
    private String apellidos;
    private String nif;
    private String password;
    private String tarjeta;
    private boolean logeado = false;

    public UsuarioRegistrado(String nombre, String apellidos, String nif, String password, String tarjeta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.password = password;
        this.tarjeta = tarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNif() {
        return nif;
    }

    public String getPassword() {
        return password;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
}
