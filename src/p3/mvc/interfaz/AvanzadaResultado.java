package p3.mvc.interfaz;

import p3.mvc.modelo.Inmueble;
import p3.mvc.modelo.Oferta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AvanzadaResultado extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("Resultados de la busqueda");
    private String[] titulos = {"Habitaciones", "Banos", "Dimensiones", "Ascensor", "Planta","Precio","Vacacional"};
    private Object filas[][] = {};
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(1, 2));
    private ButtonGroup grupo = new ButtonGroup();
    private DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos){
        public boolean isCellEditable(int row, int colum){
            return false;
        }
    };
    private JButton volver = new JButton("Volver");
    private JButton alquilar = new JButton("Alquilar");
    private JButton comentario = new JButton("Anadir comentario");
    private List<Oferta> lista;

    AvanzadaResultado(GuiInmobiliaria gui) {
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.lista= gui.getControlador().getAvanzada();
        JTable tabla = new JTable(modeloDatos);
        JScrollPane scrollPane = new JScrollPane(tabla);

        for (Oferta i : lista) {
            Object[] nuevaFila = {i.getInmueble().getnHabitaciones(), i.getInmueble().getnBanos(), i.getInmueble().getDimensiones(),
                    i.getInmueble().getAscensor(), i.getInmueble().getPlanta(),i.getPrecio(),i.isVacacional()};
            modeloDatos.addRow(nuevaFila);
        }

        grupo.add(volver);
        grupo.add(alquilar);
        grupo.add(comentario);
        select.add(comentario);
        select.add(volver);
        select.add(alquilar);
        select.setVisible(true);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, scrollPane);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, scrollPane);

        this.add(etiqueta1);
        this.add(select);
        this.add(tabla);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent evento){

    }
}
