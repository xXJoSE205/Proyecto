package p3.mvc.interfaz;

import p3.mvc.modelo.Comentario;
import p3.mvc.modelo.Opinion;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelComentario extends JPanel implements ActionListener {

    private JLabel etiqueta3 = new JLabel("Valorar: ");
    private String[] numeros = {"Val","0","1","2","3","4","5"};
    private JComboBox lista = new JComboBox(numeros);
    private JLabel etiqueta1 = new JLabel("Precio: ");
    private final JTextField val = new JTextField(2);
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
    private List<Comentario> com;
    private JLabel texto = new JLabel("");

    PanelComentario(GuiInmobiliaria gui) {
        this.gui=gui;
        this.com= gui.getControlador().getComentarios();
        SpringLayout layout = new SpringLayout();
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
        lista.setSelectedIndex(0);
        arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        int i=0;
        for(Comentario comentario1 :com){
            if(!comentario1.getComentarios().isEmpty()){
                DefaultMutableTreeNode node = new DefaultMutableTreeNode("Comentario "+i);
                modeloDatos.insertNodeInto(node,raiz,i);
                for(int j=0;j<comentario1.getComentarios().size();j++) {
                    modeloDatos.insertNodeInto(new DefaultMutableTreeNode("Autor:" + comentario1.getComentarios().get(j).getNombre() + ", " + comentario1.getComentarios().get(j).getTexto()), raiz, j);
                }
            }
            modeloDatos.insertNodeInto(new DefaultMutableTreeNode("Autor:"+comentario1.getAutor().getNombre()+", "+comentario1.getTexto() ), raiz, i);
            i++;
        }

        val.setText(gui.getControlador().getValoracion());

        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 100, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, lista, 0, SpringLayout.WEST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, lista, 30, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, lista);
        layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH, lista);
        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, select, 30, SpringLayout.SOUTH, scroll);
        layout.putConstraint(SpringLayout.EAST, etiqueta1,5, SpringLayout.WEST,lista);
        layout.putConstraint(SpringLayout.EAST, val,5, SpringLayout.WEST,etiqueta1);
        this.setPreferredSize(new Dimension(200, 100));
        this.add(etiqueta3);
        this.add(lista);
        this.add(scroll);
        this.add(select);
        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==boton1){
            int x= Integer.parseInt((String)lista.getSelectedItem());
            gui.getControlador().valorar(x);
        } else if(e.getSource()==boton2){
            gui.getControlador().volverRAvanzada();
        } else if(e.getSource()==boton3){
            gui.getControlador().anadirComentario((Comentario)arbol.getLastSelectedPathComponent());
        }

    }

    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
