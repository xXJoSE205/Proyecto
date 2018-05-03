package p3.mvc.interfaz;

import p3.mvc.modelo.Oferta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearComentario extends JPanel implements ActionListener {

    private JLabel etiqueta1 = new JLabel("Texto: ");
    private final JTextArea texto = new JTextArea(20,100);
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(1, 2));
    private ButtonGroup grupo = new ButtonGroup();
    private JButton volver = new JButton("Volver");
    private JButton publicar = new JButton("Publicar");
    private JLabel texto2 = new JLabel("");

    PanelCrearComentario(GuiInmobiliaria gui){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        grupo.add(volver);
        grupo.add(publicar);
        select.add(volver);
        select.add(publicar);
        texto.setEditable(true);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, texto, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, texto, 20, SpringLayout.NORTH, etiqueta1);

        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, texto);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, texto);

        this.add(etiqueta1);
        this.add(select);
        this.add(texto);
        this.setVisible(true);
        volver.addActionListener(this);
        publicar.addActionListener(this);

    }

    public void actionPerformed(ActionEvent evento){
        if(evento.getSource()==volver){
            gui.getControlador().volverBAvanzada();
        } else if(evento.getSource()==publicar){
            gui.getControlador().anadirComentario(gui.getControlador().getOferta());
        }
    }

    public void creadaOK(String texto) {
        this.texto2.setText(texto);
        this.texto2.setVisible(true);
        this.texto2.setForeground(java.awt.Color.red);
    }
}