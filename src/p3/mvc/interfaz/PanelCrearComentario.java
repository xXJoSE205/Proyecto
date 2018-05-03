package p3.mvc.interfaz;

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

    public void actionPerformed(ActionEvent evento){

    }
}
