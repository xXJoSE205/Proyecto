package p3.mvc.interfaz;

import p3.mvc.modelo.Comentario;
import p3.mvc.modelo.Opinion;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelComentario extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("Autor: ");
    private final JTextField nombre;

    private JLabel etiqueta2 = new JLabel("Comentario: ");
    private final JTextArea comentario ;

    private JLabel etiqueta3 = new JLabel("Valorar: ");
    private String[] numeros = {"Val","0","1","2","3","4","5"};
    private JComboBox lista = new JComboBox(numeros);

    private DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Comentarios");
    private DefaultTreeModel modeloDatos = new DefaultTreeModel(raiz);
    private JTree arbol = new JTree (modeloDatos);

    private JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JButton boton1 = new JButton("Valorar");
    private JButton boton2 = new JButton("Atras");
    private JButton boton3 = new JButton("Añadir Comentario");
    private JPanel select = new JPanel(new GridLayout(1, 3));
    private ButtonGroup grupo = new ButtonGroup();
    private GuiInmobiliaria gui;
    private Comentario com;

    PanelComentario(GuiInmobiliaria gui, Comentario com) {
        this.com=com;
        this.gui=gui;
        SpringLayout layout = new SpringLayout();
        nombre = new JTextField(com.getAutor().getNombre()+" "+com.getAutor().getApellidos(), 20);
        comentario = new JTextArea(com.getTexto(),5, 20);
        this.setLayout(layout);
        arbol.setVisibleRowCount(10);
        scroll.add(arbol);
        scroll.setViewportView(arbol);
        scroll.setPreferredSize(new Dimension(400,300));
        grupo.add(boton1);
        grupo.add(boton2);
        grupo.add(boton3);
        select.add(boton1);
        select.add(boton2);
        select.add(boton3);
        nombre.setEditable(false);
        comentario.setEditable(false);
        lista.setSelectedIndex(0);
        int i=0;
        for(Comentario comentario1 :com.getComentarios()){

            modeloDatos.insertNodeInto(new DefaultMutableTreeNode("Autor:"+comentario1.getAutor().getNombre()+", "+comentario1.getTexto() ), raiz, i);
            i++;
        }

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nombre, 5, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, nombre, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, etiqueta2, 30, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 10, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, comentario, 0, SpringLayout.WEST, nombre);
        layout.putConstraint(SpringLayout.NORTH, comentario, 30, SpringLayout.SOUTH, nombre);
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 100, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, lista, 0, SpringLayout.WEST, comentario);
        layout.putConstraint(SpringLayout.NORTH, lista, 30, SpringLayout.SOUTH, comentario);
        layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, lista);
        layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH, lista);
        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, select, 30, SpringLayout.SOUTH, scroll);

        this.setPreferredSize(new Dimension(200, 100));
        this.add(etiqueta1);
        this.add(etiqueta2);
        this.add(nombre);
        this.add(comentario);
        this.add(etiqueta3);
        this.add(lista);
        this.add(scroll);
        this.add(select);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==boton1){
            int x= Integer.parseInt((String)lista.getSelectedItem());
            gui.getControlador().valorar(x);
        } else if(e.getSource()==boton2){
            gui.getControlador().volverOferta();
        } else if(e.getSource()==boton3){
            gui.getControlador().anadirComentario(com);
        }

    }
}
