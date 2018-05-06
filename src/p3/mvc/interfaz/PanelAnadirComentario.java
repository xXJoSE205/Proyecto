package p3.mvc.interfaz;

import p3.mvc.modelo.Comentario;
import p3.mvc.modelo.Demandante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelAnadirComentario extends JPanel implements ActionListener {
    private final JTextArea texto = new JTextArea(20,100);
    private final GuiInmobiliaria gui;
    private final JButton volver = new JButton("Volver");
    private final JButton publicar = new JButton("Publicar");
    private final JLabel texto2 = new JLabel("");

    PanelAnadirComentario(GuiInmobiliaria gui){
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
        texto2.setVisible(false);

        JLabel etiqueta1 = new JLabel("Texto: ");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, etiqueta1);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, select, 8, SpringLayout.SOUTH, texto2);

        layout.putConstraint(SpringLayout.NORTH,texto2,8,SpringLayout.SOUTH,texto);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto2, 0, SpringLayout.HORIZONTAL_CENTER, this);

        this.add(etiqueta1);
        this.add(select);
        this.add(texto);
        this.add(texto2);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        publicar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento){
        if(evento.getSource()==volver){
            texto2.setVisible(false);
            gui.getControlador().volverComentario();
        } else if(evento.getSource()==publicar){
            texto2.setVisible(false);
            gui.getControlador().publicarComentario(new Comentario((Demandante)gui.getControlador().getCliente(),texto.getText()));
        }
    }

    public void setError(String texto) {
        this.texto2.setText(texto);
        this.texto2.setVisible(true);
        this.texto2.setForeground(java.awt.Color.red);
    }
}
