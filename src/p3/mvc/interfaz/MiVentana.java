package p3.mvc.interfaz;

import p3.mvc.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MiVentana extends JFrame  {

    public MiVentana() {
        super("Muzska");
        Container contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());

        Demandante demandante = new Demandante("Tony","Stark","12345678Q","Contrasena",
                "0123456789012345" );
        Comentario comentario = new Comentario(demandante, "prueba");
        Comentario comentario2 = new Comentario(demandante, "Hola");
        comentario.anadirComentario(comentario2);
        Image icon = new ImageIcon("definitivo3.png").getImage();
        setIconImage(icon);

        List<Inmueble> lista = new ArrayList<>();
        Ofertante ofertante = new Ofertante("Vic","Rattlehead","66666666D","PeaceSells",
                "6666999966669999");
        Inmueble inmueble = new Inmueble(3,1,80,"Paseo Castellana",2,
                false,ofertante);
        Oferta oferta = new Oferta(1200, LocalDate.now(),LocalDate.now().plusMonths(4),false,
                200,inmueble);
        Reserva reserva = new Reserva(demandante, oferta);
        lista.add(inmueble);
        List<Oferta> lista2 = new ArrayList<>();
        lista2.add(oferta);
        JPanel panel = new PanelLogin(new GuiInmobiliaria("Muzska"));

        contenedor.add(panel, BorderLayout.CENTER);

        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame ventana = new MiVentana();
    }
}
