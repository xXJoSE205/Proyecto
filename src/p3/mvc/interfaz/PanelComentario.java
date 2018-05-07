package p3.mvc.interfaz;

import p3.mvc.modelo.Comentario;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Esta clase contiene la informacion del panel Comentario
 * Crea un panel en el que se muestran los comentarios de la oferta y unos botones para volver(atras), valorar
 * y anadir comentario
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
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
    private final JTextField texto = new JTextField("");

    /**
     * Constructor de PanelComentario
     * @param gui GUI de la inmobiliaria
     */
    PanelComentario(GuiInmobiliaria gui) {
        DefaultMutableTreeNode node;
        this.gui=gui;
        List<Comentario> com = gui.getControlador().getComentarios();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(boton1);
        grupo.add(boton2);
        grupo.add(boton3);
        JPanel select = new JPanel(new GridLayout(1, 3));
        select.add(boton1);
        select.add(boton2);
        select.add(boton3);

        JScrollPane scroll = new JScrollPane(arbol,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(arbol);
        scroll.setPreferredSize(new Dimension(400,300));

        lista.setSelectedIndex(0);
        arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        int i=0;
        for(Comentario comentario1 : com){
            if(!comentario1.getComentarios().isEmpty()){
                if(!comentario1.getComentarios().isEmpty()){
                    node = new DefaultMutableTreeNode("Autor:"+comentario1.getAutor().getNombre()+", "+comentario1.getTexto());
                    modeloDatos.insertNodeInto(node,raiz,i);
                }else {
                    node = new DefaultMutableTreeNode("Comentario"+i);
                    modeloDatos.insertNodeInto(node, raiz, i);
                }

                for(int j=0;j<comentario1.getComentarios().size();j++) {
                    modeloDatos.insertNodeInto(new DefaultMutableTreeNode("Autor:" + comentario1.getComentarios().get(j).getAutor().getNombre() + ", " + comentario1.getComentarios().get(j).getTexto()), node, j);
                }
            }
            modeloDatos.insertNodeInto(new DefaultMutableTreeNode("Autor:"+comentario1.getAutor().getNombre()+", "+comentario1.getTexto() ), raiz, i);
            i++;
        }
        texto.setVisible(false);
        texto.setEditable(false);
        arbol.setVisibleRowCount(10);
        JLabel etiqueta3 = new JLabel("Valorar: ");
        JLabel etiqueta2 = new JLabel("COMENTARIOS");
        String x= gui.getControlador().getValoracion();
        JLabel etiqueta1 = new JLabel("Valoracion media: "+x);

        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 10, SpringLayout.SOUTH, scroll);
        layout.putConstraint(SpringLayout.WEST, etiqueta3, 0, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, lista, 10, SpringLayout.SOUTH, scroll);
        layout.putConstraint(SpringLayout.WEST, lista, 50, SpringLayout.WEST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 20, SpringLayout.SOUTH, scroll);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.SOUTH, lista);
        layout.putConstraint(SpringLayout.WEST, select, 50, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, texto, 20, SpringLayout.SOUTH, select);
        layout.putConstraint(SpringLayout.WEST, texto, 0, SpringLayout.WEST, select);
        layout.putConstraint(SpringLayout.WEST, etiqueta1, 60, SpringLayout.WEST, lista);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 20, SpringLayout.SOUTH, scroll);


        this.add(etiqueta3);
        this.add(etiqueta2);
        this.add(etiqueta1);
        this.add(lista);
        this.add(scroll);
        this.add(select);
        this.add(texto);

        this.setPreferredSize(new Dimension(800, 600));

        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
        this.setVisible(true);
    }

    /**
     * Manejador de las acciones de los botones
     * @param e Accion que se activa
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==boton1){
            texto.setVisible(false);
            try{
                int x= Integer.parseInt((String)lista.getSelectedItem());
                gui.getControlador().valorar(x);
            } catch (Exception ex){
                setError("Seleccione un numero");
            }
        } else if(e.getSource()==boton2){
            texto.setVisible(false);
            gui.getControlador().volverRAvanzada(3);
        } else if(e.getSource()==boton3){
            texto.setVisible(false);
            try{
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
                if(node==null){
                    setError("selecciona un comentario");
                }
                Object comen = node.getUserObject();
                String comentario =(String)comen;

                for(Comentario com: gui.getControlador().getComentarios()){
                    String comnetario2=("Autor:" + com.getAutor().getNombre() + ", " + com.getTexto());
                    if(comentario.equals(comnetario2)){
                        gui.getControlador().anadirComentario(com);
                    }
                }
            } catch (Exception ex){
                setError("Seleccione un comentario");
            }
        }
    }

    /**
     * Establece el mensaje que se va a mostrar por pantalla
     * @param texto Cadena con el mensaje a mostrar
     */
    public void setError(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
