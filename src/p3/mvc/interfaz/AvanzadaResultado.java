package p3.mvc.interfaz;

import p3.mvc.modelo.Oferta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AvanzadaResultado extends JPanel implements ActionListener {
    private String[] titulos = {"Habitaciones", "Banos", "Dimensiones", "Ascensor", "Planta","Direccion","Precio","Vacacional"};
    private Object filas[][] = {};
    private GuiInmobiliaria gui;
    private DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos){
        public boolean isCellEditable(int row, int colum){
            return false;
        }
    };
    private JButton volver = new JButton("Volver");
    private JButton alquilar = new JButton("Alquilar");
    private JButton comentario = new JButton("Anadir comentario");
    private JButton comentario2 = new JButton("Ver comentarios");
    private JButton reservar = new JButton("Reservar");
    private List<Oferta> lista;
    private JTable tabla = new JTable(modeloDatos);
    private JLabel texto = new JLabel("");


    AvanzadaResultado(GuiInmobiliaria gui) {
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.lista= gui.getControlador().getAvanzada();

        JScrollPane scrollPane = new JScrollPane(tabla);
        texto.setVisible(false);
        if(this.lista==null) {
            texto.setVisible(true);
            this.texto.setForeground(java.awt.Color.red);
            texto.setText("No hay resultados");
        }else{
            for (Oferta i : lista) {
                Object[] nuevaFila = {i.getInmueble().getnHabitaciones(), i.getInmueble().getnBanos(), i.getInmueble().getDimensiones(),
                        i.getInmueble().getAscensor(), i.getInmueble().getPlanta(),i.getInmueble().getDireccion(), i.getPrecio(), i.isVacacional()};
                modeloDatos.addRow(nuevaFila);
            }
        }

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(alquilar);
        grupo.add(comentario);
        grupo.add(comentario2);
        grupo.add(reservar);
        JPanel select = new JPanel(new GridLayout(1, 5));
        select.add(comentario);
        select.add(comentario2);
        select.add(volver);
        select.add(alquilar);
        select.add(reservar);
        select.setVisible(true);

        JLabel etiqueta1 = new JLabel("Resultados de la busqueda");
        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, scrollPane);
        layout.putConstraint(SpringLayout.NORTH, select, 450, SpringLayout.NORTH, scrollPane);
        layout.putConstraint(SpringLayout.NORTH,texto,400,SpringLayout.NORTH,scrollPane);
        layout.putConstraint(SpringLayout.WEST, texto, 0, SpringLayout.WEST, scrollPane);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setColumnSelectionAllowed(false);
        tabla.getSelectionModel().getLeadSelectionIndex();
        tabla.setRowSelectionAllowed(true);

        scrollPane.setPreferredSize(new Dimension(700,400));
        this.add(etiqueta1);
        this.add(select);
        this.add(scrollPane);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        this.add(texto);
        volver.addActionListener(this);
        comentario.addActionListener(this);
        comentario2.addActionListener(this);
        alquilar.addActionListener(this);
        reservar.addActionListener(this);

    }

    public void actionPerformed(ActionEvent evento){
        if(evento.getSource()==volver){
            gui.getControlador().volverAvanzada();
        } else if(evento.getSource()==alquilar){
            gui.getControlador().goAlquilar(lista.get(tabla.getSelectedRow()));
        } else if(evento.getSource()==comentario){
            gui.getControlador().anadirComentario(lista.get(tabla.getSelectedRow()));
        }else if(evento.getSource()==comentario2){
            gui.getControlador().goComentario(lista.get(tabla.getSelectedRow()));
        } else if(evento.getSource()==reservar){
            gui.getControlador().goReserva(lista.get(tabla.getSelectedRow()));
        }

    }

    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
