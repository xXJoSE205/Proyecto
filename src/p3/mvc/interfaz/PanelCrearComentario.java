package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene la informacion del panel Crear comentario
 * Crea un panel en el que se muestra un recuadro para el comentario y unos botones para volver y publicar
 * Anade un comentario a la oferta
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelCrearComentario extends JPanel implements ActionListener {
    private final JTextArea texto = new JTextArea(20,100);
    private final GuiInmobiliaria gui;
    private final JButton volver = new JButton("Volver");
    private final JButton publicar = new JButton("Publicar");
    private final JLabel texto2 = new JLabel("");

    /**
     * Constructor de PanelCrearComentario
     * @param gui GUI de la inmobiliaria
     */
    PanelCrearComentario(GuiInmobiliaria gui){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(publicar);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.add(publicar);
        texto.setEditable(true);

        JLabel etiqueta1 = new JLabel("Texto: ");
        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, texto, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, texto, 20, SpringLayout.NORTH, etiqueta1);

        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, texto);
        layout.putConstraint(SpringLayout.NORTH, select, 350, SpringLayout.NORTH, texto);
        layout.putConstraint(SpringLayout.WEST, texto2, 5, SpringLayout.WEST, select);
        layout.putConstraint(SpringLayout.NORTH, texto2, 30, SpringLayout.NORTH, select);

        this.add(etiqueta1);
        this.add(select);
        this.add(texto);
        this.add(texto2);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        publicar.addActionListener(this);

    }

    /**
     * Manejador de las acciones de los botones
     * @param evento Accion que se activa
     */
    public void actionPerformed(ActionEvent evento){
        if(evento.getSource()==volver){
            texto2.setVisible(false);
            gui.getControlador().volverRAvanzada(1);
        } else if(evento.getSource()==publicar){
            texto2.setVisible(false);
            gui.getControlador().publicarComentario(texto.getText());
            texto.setText("");
            texto.setEditable(true);
        }
    }

    /**
     * Establece el mensaje que se va a mostrar por pantalla
     * @param texto Cadena con el mensaje a mostrar
     */
    public void creadoOK(String texto) {
        this.texto2.setText(texto);
        this.texto2.setVisible(true);
        this.texto2.setForeground(java.awt.Color.red);
    }
}
