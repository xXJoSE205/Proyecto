package p3.mvc.interfaz;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class PanelComentario extends JPanel {
    PanelComentario(int y) {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel etiqueta1 = new JLabel("Autor: ");
        final JTextField nombre = new JTextField(" ", 20);
        nombre.setEditable(false);
        JLabel etiqueta2 = new JLabel("Comentario: ");
        final JTextArea comentario = new JTextArea(5, 20);
        comentario.setEditable(false);
        JLabel etiqueta3 = new JLabel("Valorar: ");
        String[] numeros = {"Val","0","1","2","3","4","5"};
        JComboBox lista = new JComboBox(numeros);
        lista.setSelectedIndex(0);
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Comentarios");
        DefaultTreeModel modeloDatos = new DefaultTreeModel(raiz);
        JTree arbol = new JTree (modeloDatos);

        JScrollPane scroll = new JScrollPane(arbol);
        JButton boton1 = new JButton("Valorar");
        JButton boton2 = new JButton("Atras");
        JButton boton3 = new JButton("Añadir Comentario");

        for(int i=0;i<y;i++){
            int x=i+1;
            modeloDatos.insertNodeInto(new DefaultMutableTreeNode("Comentario" + x), raiz, i);
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
        layout.putConstraint(SpringLayout.WEST, boton2, 0, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, boton2, 30, SpringLayout.SOUTH, scroll);
        layout.putConstraint(SpringLayout.WEST, boton1, 100, SpringLayout.WEST, boton2);
        layout.putConstraint(SpringLayout.NORTH, boton1, 30, SpringLayout.SOUTH, scroll);
        layout.putConstraint(SpringLayout.WEST, boton3, 100, SpringLayout.WEST, boton1);
        layout.putConstraint(SpringLayout.NORTH, boton3, 30, SpringLayout.SOUTH, scroll);

        this.setPreferredSize(new Dimension(200, 100));
        this.add(etiqueta1);
        this.add(etiqueta2);
        this.add(nombre);
        this.add(comentario);
        this.add(etiqueta3);
        this.add(lista);
        this.add(scroll);
        this.add(boton1);
        this.add(boton2);
        this.add(boton3);
    }
}
