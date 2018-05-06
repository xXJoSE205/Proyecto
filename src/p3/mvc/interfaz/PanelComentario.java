package p3.mvc.interfaz;

import p3.mvc.modelo.Comentario;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class PanelComentario extends JPanel implements ActionListener {

    private final String[] numeros = {"Val","0","1","2","3","4","5"};
    private final JComboBox lista = new JComboBox(numeros);
    private final DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Comentarios");
    private final DefaultTreeModel modeloDatos = new DefaultTreeModel(raiz);
    private final JTree arbol = new JTree (modeloDatos);

    private final JButton boton1 = new JButton("Valorar");
    private final JButton boton2 = new JButton("Atras");
    private final JButton boton3 = new JButton("Añadir Comentario");
    private final GuiInmobiliaria gui;
    private final JLabel texto = new JLabel("");

    PanelComentario(GuiInmobiliaria gui) {
        this.gui=gui;
        List<Comentario> com = gui.getControlador().getComentarios();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);


        arbol.setVisibleRowCount(10);
        JScrollPane scroll = new JScrollPane(arbol,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(arbol);
        scroll.setPreferredSize(new Dimension(400,300));
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(boton1);
        grupo.add(boton2);
        grupo.add(boton3);
        JPanel select = new JPanel(new GridLayout(1, 3));
        select.add(boton1);
        select.add(boton2);
        select.add(boton3);
        lista.setSelectedIndex(0);
        arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        int i=0;
        for(Comentario comentario1 : com){
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

        JTextField val = new JTextField(2);

        JLabel etiqueta3 = new JLabel("Valorar: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 100, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, lista, 0, SpringLayout.WEST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, lista, 30, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, lista);
        layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH, lista);
        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, select, 30, SpringLayout.SOUTH, scroll);
        JLabel etiqueta1 = new JLabel("Valoracion media: "+gui.getControlador().getValoracion());
        layout.putConstraint(SpringLayout.EAST, etiqueta1,5, SpringLayout.WEST,lista);
        layout.putConstraint(SpringLayout.EAST, val,5, SpringLayout.WEST, etiqueta1);
        this.add(etiqueta3);
        this.add(lista);
        this.add(scroll);
        this.add(select);
        this.add(val);

        this.setPreferredSize(new Dimension(800, 600));

        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==boton1){
            int x= Integer.parseInt((String)lista.getSelectedItem());
            gui.getControlador().valorar(x);
        } else if(e.getSource()==boton2){
            gui.getControlador().volverRAvanzada(3);
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
